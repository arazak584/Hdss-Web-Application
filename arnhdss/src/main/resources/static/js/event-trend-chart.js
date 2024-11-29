let chart;
let loadingIndicator = document.getElementById('loadingIndicator');

function showLoading() {
    loadingIndicator.style.display = 'block';
}

function hideLoading() {
    loadingIndicator.style.display = 'none';
}

function resetChart() {
    // Destroy existing chart if it exists
    if (chart) {
        chart.destroy();
        chart = null;
    }

    // Clear the chart canvas
    const ctx = document.getElementById('eventTrendChart').getContext('2d');
    ctx.clearRect(0, 0, ctx.canvas.width, ctx.canvas.height);
}

// Load Village Options
function loadVillages() {
    fetch('/api/villages')
        .then(response => {
            if (!response.ok) throw new Error('Network response was not ok');
            return response.json();
        })
        .then(data => {
            var villageSelect = document.getElementById('villageSelect');
            villageSelect.innerHTML = '<option value="">Select a village</option>';
            data.forEach(village => {
                var option = document.createElement('option');
                option.value = village.name;
                option.textContent = village.name;
                villageSelect.appendChild(option);
            });
            $('.js-states').select2();
        })
        .catch(error => console.error('Error fetching villages:', error));
}

// Load Subvillage Options
function loadSubVillages() {
    fetch('/api/subvillages')
        .then(response => {
            if (!response.ok) throw new Error('Network response was not ok');
            return response.json();
        })
        .then(data => {
            var subvillageSelect = document.getElementById('subvillageSelect');
            subvillageSelect.innerHTML = '<option value="">Select a subvillage</option>';
            data.forEach(subvillage => {
                var option = document.createElement('option');
                option.value = subvillage.name;
                option.textContent = subvillage.name;
                subvillageSelect.appendChild(option);
            });
            $('.js-states').select2();
        })
        .catch(error => console.error('Error fetching subvillages:', error));
}

// Update Chart
function updateChart() {
    const village = document.getElementById('villageSelect').value;
    const subvillage = document.getElementById('subvillageSelect').value;
    const selectedName = subvillage || village;

    // Reset chart
    resetChart();

    // Handle village selection
    if (village) {
        // Reset subvillage dropdown to first option
        document.getElementById('subvillageSelect').selectedIndex = 0;
        $('.js-states').select2();
    }

    // Handle subvillage selection
    if (subvillage) {
        // Reset village dropdown to first option
        document.getElementById('villageSelect').selectedIndex = 0;
        $('.js-states').select2();
    }

    // If no selection, just reset and return
    if (!selectedName) return;

    showLoading();

    fetch(`/api/event-trend-village?village=${selectedName}`)
        .then(response => response.json())
        .then(data => {
            hideLoading();

            const trends = data.data;
            if (trends.length === 0) {
                alert('No data available for the selected location.');
                return;
            }

            let roundNumbers = [];
            let deathCounts = [];
            let pregnancyCounts = [];
            let outcomeCounts = [];
            let inMigrationCounts = [];
            let outMigrationCounts = [];

            if (!subvillage && village) {
                // Sum counts for village data
                const aggregated = trends.reduce((acc, item) => {
                    if (!acc[item.roundNumber]) {
                        acc[item.roundNumber] = {
                            roundNumber: item.roundNumber,
                            death_count: 0,
                            pregnancy_count: 0,
                            outcome_count: 0,
                            inmigration_count: 0,
                            outmigration_count: 0,
                        };
                    }
                    acc[item.roundNumber].death_count += item.death_count;
                    acc[item.roundNumber].pregnancy_count += item.pregnancy_count;
                    acc[item.roundNumber].outcome_count += item.outcome_count;
                    acc[item.roundNumber].inmigration_count += item.inmigration_count;
                    acc[item.roundNumber].outmigration_count += item.outmigration_count;
                    return acc;
                }, {});

                const aggregatedData = Object.values(aggregated);
                roundNumbers = aggregatedData.map(item => item.roundNumber);
                deathCounts = aggregatedData.map(item => item.death_count);
                pregnancyCounts = aggregatedData.map(item => item.pregnancy_count);
                outcomeCounts = aggregatedData.map(item => item.outcome_count);
                inMigrationCounts = aggregatedData.map(item => item.inmigration_count);
                outMigrationCounts = aggregatedData.map(item => item.outmigration_count);
            } else {
                roundNumbers = trends.map(item => item.roundNumber);
                deathCounts = trends.map(item => item.death_count);
                pregnancyCounts = trends.map(item => item.pregnancy_count);
                outcomeCounts = trends.map(item => item.outcome_count);
                inMigrationCounts = trends.map(item => item.inmigration_count);
                outMigrationCounts = trends.map(item => item.outmigration_count);
            }

            const ctx = document.getElementById('eventTrendChart').getContext('2d');
            chart = new Chart(ctx, {
                type: 'line',
                data: {
                    labels: roundNumbers,
                    datasets: [
                        { label: 'Death Count', data: deathCounts, borderColor: 'red', fill: false },
                        { label: 'Pregnancy Count', data: pregnancyCounts, borderColor: 'blue', fill: false },
                        { label: 'Outcome Count', data: outcomeCounts, borderColor: 'green', fill: false },
                        { label: 'Inmigration Count', data: inMigrationCounts, borderColor: 'purple', fill: false },
                        { label: 'Outmigration Count', data: outMigrationCounts, borderColor: 'orange', fill: false },
                    ],
                },
                options: {
                    responsive: true,
                    plugins: {
                        title: { display: true, text: `Event Trends for ${selectedName}` },
                    },
                    scales: {
                        x: { title: { display: true, text: 'Round Number' } },
                        y: { title: { display: true, text: 'Count' } },
                    },
                },
            });
        })
        .catch(error => {
            hideLoading();
            console.error('Error fetching event trends:', error);
        });
}

// Call the function to populate village/subvillage on page load
window.onload = function() {
    loadVillages();
    loadSubVillages();
};
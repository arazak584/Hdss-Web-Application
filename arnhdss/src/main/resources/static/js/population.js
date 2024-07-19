$(document).ready(function() {
    fetchPopulationData('/api/pop', updatePopulationChart, '#loadingSpinnerPopulation');
});

function fetchPopulationData(url, updateFunction, loaderId) {
    $(loaderId).show(); // Show the loader before the AJAX request

    return $.ajax({
        url: url,
        method: 'GET',
        success: function(response) {
            console.log('Data fetched:', response); // Log the fetched data
            updateFunction(response.data);
            $(loaderId).hide(); // Hide the loader after the AJAX request is completed
        },
        error: function(error) {
            console.error('Error loading data from ' + url + ':', error);
            $(loaderId).hide(); // Hide the loader in case of an error
        }
    });
}

function updatePopulationChart(data) {
    console.log('Data for chart:', data); // Log the data passed to the chart function

    var labels = data.map(function(item) { return item.ageGroup; });
    var maleData = data.map(function(item) { return -item.male; }); // Negate male values for left side
    var femaleData = data.map(function(item) { return item.female; });

    var canvas = document.getElementById('populationPyramid');
    if (!canvas) {
        console.error('Canvas element not found.');
        return;
    }

    if (!(canvas instanceof HTMLCanvasElement)) {
        console.error('Element with ID populationPyramid is not a canvas.');
        return;
    }

    var ctx = canvas.getContext('2d');
    if (!ctx) {
        console.error('Canvas context not found.');
        return;
    }

    var chart = new Chart(ctx, {
        type: 'bar', // Use bar chart with indexAxis set to y for horizontal bars
        data: {
            labels: labels,
            datasets: [
                {
                    label: 'Males',
                    backgroundColor: '#36A2EB',
                    data: maleData,
                    borderWidth: 1,
                    borderColor: '#36A2EB'
                },
                {
                    label: 'Females',
                    backgroundColor: '#FF6384',
                    data: femaleData,
                    borderWidth: 1,
                    borderColor: '#FF6384'
                }
            ]
        },
        options: {
            indexAxis: 'y', // Horizontal bars
            scales: {
                x: {
                    ticks: {
                        callback: function(value) {
                            return Math.abs(value);
                        }
                    },
                    title: {
                        display: true,
                        text: 'Population'
                    },
                    grid: {
                        display: true
                    }
                },
                y: {
                    title: {
                        display: true,
                        text: 'Age Group'
                    },
                    reverse: true, // Ensure the y-axis is reversed to display age groups correctly
                    grid: {
                        display: true
                    }
                }
            },
            plugins: {
                tooltip: {
                    callbacks: {
                        label: function(tooltipItem) {
                            return tooltipItem.dataset.label + ': ' + Math.abs(tooltipItem.raw);
                        }
                    }
                },
                legend: {
                    position: 'bottom' // Position the legend at the bottom
                }
            },
            responsive: true,
            maintainAspectRatio: false
        }
    });
}

$(document).ready(function() {
    fetchOutcomeData('/api/outcome-year', updateOutcomeChart, '#loadingSpinnerOutcome');
});

function fetchOutcomeData(url, updateFunction, loaderId) {
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

function updateOutcomeChart(data) {
    console.log('Data for chart:', data); // Log the data passed to the chart function

    var labels = data.map(function(item) { return item.year; });
    var liv = data.map(function(item) { return item.lbr; });
    var sbr = data.map(function(item) { return item.sbr; });
    var mis = data.map(function(item) { return item.mis; });
    var abt = data.map(function(item) { return item.abt; });

    var ctx = document.getElementById('outcomeChart').getContext('2d');
    if (!ctx) {
        console.error('Canvas context not found.');
        return;
    }

    new Chart(ctx, {
        type: 'line',
        data: {
            labels: labels,
            datasets: [
                {
                    label: 'LiveBirth',
                    data: liv,
                    borderColor: 'green',
                    fill: false
                },
                {
                    label: 'StillBirth',
                    data: sbr,
                    borderColor: 'blue',
                    fill: false
                },
                {
                    label: 'Miscarriage',
                    data: mis,
                    borderColor: 'yellow',
                    fill: false
                },
                {
                    label: 'Abortion',
                    data: abt,
                    borderColor: 'red',
                    fill: false
                }
            ]
        },
        options: {
            legend: {
                display: true
            }
        }
    });
}

$(document).ready(function() {
    fetchDeathData('/api/dth-year', updateDeathChart, '#loadingSpinnerDeath');
});

function fetchDeathData(url, updateFunction, loaderId) {
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

function updateDeathChart(data) {
    console.log('Data for chart:', data); // Log the data passed to the chart function

    var labels = data.map(function(item) { return item.year; });
    var maleDeaths = data.map(function(item) { return item.male; });
    var femaleDeaths = data.map(function(item) { return item.female; });

    var ctx = document.getElementById('myChart').getContext('2d');
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
                    label: 'Male Deaths',
                    data: maleDeaths,
                    borderColor: 'blue',
                    fill: false
                },
                {
                    label: 'Female Deaths',
                    data: femaleDeaths,
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

// dashboard.js
$(document).ready(function() {
    // Make an AJAX request to fetch data asynchronously
    $.ajax({
        url: '/hdss/asyncReport/query',
        method: 'GET',
        success: function(data) {
            // Update the DOM with the received data
            updateDashboard(data);
        },
        error: function(error) {
            console.error('Error loading asynchronous data:', error);
        }
    });
});

function updateDashboard(data) {
    // Update your dashboard components with the received data
    // For example, assuming you have HTML elements with specific IDs:
    $('#itemsContainer').html(renderItems(data.items));
    
    //Query
    $('#nomemb').text(data.nomemb);
    $('#minor').text(data.minor);
    $('#dupres').text(data.dupres);
    $('#dobs').text(data.dobs);
    $('#lag').text(data.lag);
    $('#minors').text(data.minors);
    
    $('#errors').text(data.nomemb+data.minor+data.dupres+data.dobs+data.lag+data.minors);
}

function renderItems(items) {
    // Implement a function to render the items as HTML
    // This depends on the structure of your data and how you want to display it
    // For example, you can loop through the items and create HTML elements
    var html = '';
    for (var i = 0; i < items.length; i++) {
        html += '<div>' + items[i].someProperty + '</div>';
    }
    return html;
}

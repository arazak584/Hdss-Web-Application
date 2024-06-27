// dashboard.js
$(document).ready(function() {
    // Make separate AJAX requests for different sections of the dashboard
    $.when(
        getData('/hdss/asyncReport/hierarchy', updateHierarchy),
        getData('/hdss/asyncReport/query', updateQuery)
        // Add more calls for other sections as needed
    ).then(function() {
        // All updates are complete
        console.log('All updates completed successfully.');
    }).fail(function(error) {
        console.error('Error loading asynchronous data:', error);
    });
});


function getData(url, updateFunction) {
    // Show the loader for the specific section
    return $.ajax({
        url: url,
        method: 'GET',
        success: function(data) {
            updateFunction(data);
            // Hide the loader for the specific section after the AJAX request is completed
        },
        error: function(error) {
            console.error('Error loading data from ' + url + ':', error);
            // Hide the loader for the specific section in case of an error
        }
    });
}

function updateHierarchy(data) {
    // Update the DOM with the received data for updateResidents
    $('#itemsContainer').html(renderItems(data.items));
    
    // Hierarchy
    $('#cnt1').text(data.cnt1);
    $('#cnt2').text(data.cnt2);
    $('#cnt3').text(data.cnt3);
    $('#cnt4').text(data.cnt4);
    $('#cnt5').text(data.cnt5);
    $('#cnt6').text(data.cnt6);

$('#cntall').text(data.cnt1+data.cnt2+data.cnt3+data.cnt4+data.cnt5+data.cnt6 + '+');
}

function updateQuery(data) {
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
    $('#out').text(data.out);
    $('#dthoh').text(data.dthoh);
    
    $('#errors').text(data.nomemb+data.minor+data.dupres+data.dobs+data.lag+data.minors+data.out+data.dthoh);
}

$(document).ready(function() {
    getData('/hdss/asyncReport/hierarchy', updateHierarchy);
    getData('/hdss/asyncReport/query', updateQuery);
    // Add more calls for other sections as needed
});

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


$(document).ready(function() {
    // Show the loader
    $('#loadingSpinner').show();

    // Make an AJAX request to fetch data asynchronously
    $.ajax({
        url: '/hdss/async',
        method: 'GET',
        success: function(data) {
            // Hide the loader
            $('#loadingSpinner').hide();

            // Update the DOM with the received data
            updateDashboards(data);
        },
        error: function(error) {
            console.error('Error loading asynchronous data:', error);
            $('#loadingSpinner').hide();
        }
    });
});

function updateDashboards(data) {
    // Update your dashboard components with the received data
    // For example, assuming you have HTML elements with specific IDs:
    $('#itemsContainer').html(renderItems(data.items));

    // Inmigration
    $('#img0').text(data.img0);
    $('#img1').text(data.img1);
    $('#img2').text(data.img2);
    $('#img3').text(data.img3);
    // Outmigration
    $('#omg0').text(data.omg0);
    $('#omg1').text(data.omg1);
    $('#omg2').text(data.omg2);
    $('#omg3').text(data.omg3);

    // Death
    $('#dth0').text(data.dth0);
    $('#dth1').text(data.dth1);
    $('#dth2').text(data.dth2);
    $('#dth3').text(data.dth3);

    // Pregnancy
    $('#preg0').text(data.preg0);
    $('#preg1').text(data.preg1);
    $('#preg2').text(data.preg2);
    $('#preg3').text(data.preg3);

    // Outcome
    $('#out0').text(data.out0);
    $('#out1').text(data.out1);
    $('#out2').text(data.out2);
    $('#out3').text(data.out3);

    // Demographic
    $('#demo0').text(data.demo0);
    $('#demo1').text(data.demo1);
    $('#demo2').text(data.demo2);
    $('#demo3').text(data.demo3);

    // Relationship
    $('#rel0').text(data.rel0);
    $('#rel1').text(data.rel1);
    $('#rel2').text(data.rel2);
    $('#rel3').text(data.rel3);
    
    // SES
    $('#ses0').text(data.ses0);
    $('#ses1').text(data.ses1);
    $('#ses2').text(data.ses2);
    $('#ses3').text(data.ses3);
    
    // Vaccination
    $('#vac0').text(data.vac0);
    $('#vac1').text(data.vac1);
    $('#vac2').text(data.vac2);
    $('#vac3').text(data.vac3);
    
    // Morbidity
    $('#mor0').text(data.mor0);
    $('#mor1').text(data.mor1);
    $('#mor2').text(data.mor2);
    $('#mor3').text(data.mor3);
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

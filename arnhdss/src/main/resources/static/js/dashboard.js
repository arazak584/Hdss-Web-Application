// dashboard.js
$(document).ready(function() {
    // Make an AJAX request to fetch data asynchronously
    $.ajax({
        url: '/hdss/asyncReport',
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
    
    //Active Residents
    $('#countResContainer').text(data.countRes);
    $('#countRespercent').text(data.perres);
    $('#myProgressBar').css('width', data.perres + '%');
    $('#myProgressBar').attr('aria-valuenow', data.perres);
    
    //Total Individuals
    $('#countInd').text(data.ind);
    $('#perInd').text(data.perind);
    $('#myInd').css('width', data.perind + '%');
    $('#myInd').attr('aria-valuenow', data.perind);
    //Compounds
    $('#cntcomp').text(data.countcomp);
    $('#pertcomp').text(data.percomp);
    $('#mycomp').css('width', data.comploc + '%');
    $('#mycomp').attr('aria-valuenow', data.comploc);   
    //Socialgroup
    $('#cnthh').text(data.counthh);
    $('#perthh').text(data.perhh);
    $('#myhh').css('width', data.perhh + '%');
    $('#myhh').attr('aria-valuenow', data.perhh); 
    //hh visit
    $('#cntvisit').text(data.hhvisit);
    $('#cnthoh').text(data.hh);
    $('#visited').text(data.visit);
    $('#rvisit').text(data.visit);
    //comp visit
    $('#cntcmp').text(data.comp);
    $('#cmpvisit').text(data.compvisit);
    $('#cpmvisited').text(data.compper);
    $('#cpmnew').text(data.comploc);
    $('#listing').text(data.compvisit);
    
    $('#cntpreg').text(data.pregs);
    $('#cntdth').text(data.death);
    $('#cntrel').text(data.rel);
    $('#cntses').text(data.sess);
    $('#cntperson').text(data.newperson);
    $('#cntsoc').text(data.newhh);
    $('#cntloc').text(data.newloc);
    $('#cntvac').text(data.vacc);
    
    $('#cntimg').text(data.img);
    $('#cntomg').text(data.omg);
    $('#cntoutcome').text(data.outcome);
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

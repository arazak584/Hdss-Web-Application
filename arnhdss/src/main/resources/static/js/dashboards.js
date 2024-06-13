$(document).ready(function() {
    // Make separate AJAX requests for different sections of the dashboard
    $.when(
        fetchData('/hdss/asyncReport', updateResidents),
        fetchData('/hdss/asyncReport/report', updateReport)
        // Add more calls for other sections as needed
    ).then(function() {
        // All updates are complete
        console.log('All updates completed successfully.');
    }).fail(function(error) {
        console.error('Error loading asynchronous data:', error);
    });
});

function fetchData(url, updateFunction, loaderId) {
    // Show the loader for the specific section
    $(loaderId).show();

    return $.ajax({
        url: url,
        method: 'GET',
        success: function(data) {
            updateFunction(data);
            // Hide the loader for the specific section after the AJAX request is completed
            $(loaderId).hide();
        },
        error: function(error) {
            console.error('Error loading data from ' + url + ':', error);
            // Hide the loader for the specific section in case of an error
            $(loaderId).hide();
        }
    });
}

function updateResidents(data) {
    // Update the DOM with the received data for updateResidents
    $('#itemsContainer').html(renderItems(data.items));
    
    // Active Residents
    $('#countResContainer').text(data.countRes);
    $('#countRespercent').text(data.perres);
    $('#myProgressBar').css('width', data.perres + '%');
    $('#myProgressBar').attr('aria-valuenow', data.perres);
    
    // Total Individuals
    $('#countInd').text(data.ind);
    $('#perInd').text(data.perind);
    $('#myInd').css('width', data.perind + '%');
    $('#myInd').attr('aria-valuenow', data.perind);
    
    // Compounds
    $('#cntcomp').text(data.countcomp);
    $('#pertcomp').text(data.percomp);
    $('#mycomp').css('width', data.percomp + '%');
    $('#mycomp').attr('aria-valuenow', data.percomp);   
    
    // Active Households
    $('#cnthh').text(data.counthh);
    $('#perthh').text(data.perhh);
    $('#myhh').css('width', data.perhh + '%');
    $('#myhh').attr('aria-valuenow', data.perhh); 
}

function updateReport(data) {
    // Update the DOM with the received data for updateReport
    $('#iteContainer').html(renderItems(data.items));
    
    $('#cntvisit').text(data.hhvisit);
    $('#cnthoh').text(data.hh);
    $('#visited').text(data.visit);
    $('#rvisit').text(data.visit);
    // comp visit
    $('#cntcmp').text(data.comp);
    $('#cmpvisit').text(data.compvisit);
    $('#cpmvisited').text(data.compper);
    $('#cpmnew').text(data.comploc);
    $('#listing').text(data.compvisit);
    
    // $('#allvisit').text(data.comp1/data.comp2+data.comp3);
    $('#allvisit').text(((data.comp1 / (data.comp2 + data.comp3)) * 100).toFixed(2) + '%');
    $('#allhh').text(data.comp2+data.comp3);
    
    $('#cntpreg').text(data.pregs);
    $('#cntdth').text(data.death);
    $('#cntrel').text(data.rel);
    $('#cntses').text(data.sess);
    $('#cnteses').text(data.eses);
    $('#cntperson').text(data.newperson);
    $('#cntsoc').text(data.newhh);
    $('#cntloc').text(data.newloc);
    $('#cntvac').text(data.vacc);
    
    $('#cntimg').text(data.img);
    $('#cntomg').text(data.omg);
    $('#cntoutcome').text(data.outcome);
}

$(document).ready(function() {
    fetchData('/hdss/asyncReport', updateResidents, '#loadingSpinnerResidents');
    fetchData('/hdss/asyncReport/report', updateReport, '#loadingSpinnerReport');
    // Add more calls for other sections as needed
});

// Add more update functions for other sections as needed

// The renderItems function remains the same...
function renderItems(items) {
    var html = '';
    for (var i = 0; i < items.length; i++) {
        html += '<div>' + items[i].someProperty + '</div>';
    }
    return html;
}

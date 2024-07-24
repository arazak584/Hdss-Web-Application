$(document).ready(function() {
    fetchData('/hdss/asyncReport', updateResidents, '#loadingSpinnerResident1');
    fetchData('/hdss/asyncReport/report', updateReport, '#loadingSpinnerReport1');
    // Add more calls for other sections as needed
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
            console.log('Success loading data from ' + ':', data);
        },
        error: function(error) {
            console.error('Error loading data from ' + ':', error);
            // Hide the loader for the specific section in case of an error
            $(loaderId).hide();
        }
    });
}

function updateResidents(data) {
    // Update the DOM with the received data for updateResidents
    $('#resident1').html(renderItem(data.items[0]));  // Assuming data.items[0] corresponds to resident1
    $('#loadingSpinnerResident1').hide();

    $('#resident2').html(renderItem(data.items[1]));  // Assuming data.items[1] corresponds to resident2
    $('#loadingSpinnerResident2').hide();
    
    $('#resident3').html(renderItem(data.items[2]));  // Assuming data.items[2] corresponds to resident3
    $('#loadingSpinnerResident3').hide();
    
    $('#resident4').html(renderItem(data.items[3]));  // Assuming data.items[3] corresponds to resident4
    $('#loadingSpinnerResident4').hide();
    
    // Add updates for more residents as needed

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
	console.log('Updating report with data:', data);
	console.log('Number of report items:', data.items.length);
	
	 if (data.items && data.items.length > 0) {
        for (var i = 0; i < data.items.length; i++) {
            $('#report' + (i + 1)).html(renderItem(data.items[i]));
            $('#loadingSpinnerReport' + (i + 1)).hide();
        }
    } else {
        console.warn('No report data received');
        console.warn('Skipping empty report data at index', i);
    }
    // Update the DOM with the received data for updateReport
//     $('#report1').html(renderItem(data.items[0]));  // Assuming data.items[0] corresponds to report1
//     $('#loadingSpinnerReport1').hide();
//
//     $('#report2').html(renderItem(data.items[1]));  // Assuming data.items[1] corresponds to report2
//     $('#loadingSpinnerReport2').hide();
//    
//     $('#report3').html(renderItem(data.items[2]));
//     $('#loadingSpinnerReport3').hide();
//    
//     $('#report4').html(renderItem(data.items[3]));
//     $('#loadingSpinnerReport4').hide();
//    
//     $('#report5').html(renderItem(data.items[4]));
//     $('#loadingSpinnerReport5').hide();   
    
    // Add updates for more reports as needed

    $('#cntvisit').text(data.hhvisit);
    $('#cnthoh').text(data.hh);
    $('#visited').text(data.visit);
    $('#rvisit').text(data.comp1);
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

// Add more update functions for other sections as needed

function renderItem(item) {
    return '<div>' + item.someProperty + '</div>';
}

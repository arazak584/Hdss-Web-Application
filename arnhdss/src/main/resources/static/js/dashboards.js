$(document).ready(function() {
    // Make separate AJAX requests for different sections of the dashboard
    $.when(
      //fetchData('/hdss/asyncReport/query', updateItems),
      fetchData('/hdss/asyncReport', updateResidents),
      fetchData('/hdss/asyncReport/report', updateReport),
        // Add more calls for other sections as needed
    ).then(function() {
        // All updates are complete
        console.log('All updates completed successfully.');
    }).fail(function(error) {
        console.error('Error loading asynchronous data:', error);
    });
});

function fetchData(url, updateFunction) {
    return $.ajax({
        url: url,
        method: 'GET',
        success: function(data) {
            updateFunction(data);
        },
        error: function(error) {
            console.error('Error loading data from ' + url + ':', error);
        }
    });
}
/*
function updateItems(data) {
    $('#itemContainer').html(renderItems(data.items));
    $('#nomemb').text(data.nomemb);
    $('#minor').text(data.minor);
    $('#dupres').text(data.dupres);
    $('#dobs').text(data.dobs);
    $('#lag').text(data.lag);
    $('#minors').text(data.minors);
    
     $('#errors').text(data.nomemb+data.minor+data.dupres+data.dobs+data.lag+data.minors);
}*/

function updateResidents(data) {
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
    $('#mycomp').css('width', data.percomp + '%');
    $('#mycomp').attr('aria-valuenow', data.percomp);   
    //Socialgroup
    $('#cnthh').text(data.counthh);
    $('#perthh').text(data.perhh);
    $('#myhh').css('width', data.perhh + '%');
    $('#myhh').attr('aria-valuenow', data.perhh); 
}

function updateReport(data) {
    $('#iteContainer').html(renderItems(data.items));
    
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

// The renderItems function remains the same...
function renderItems(items) {
    var html = '';
    for (var i = 0; i < items.length; i++) {
        html += '<div>' + items[i].someProperty + '</div>';
    }
    return html;
}

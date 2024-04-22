    //fieldreport.js
    // Function to fetch table data via AJAX with pagination parameters
function fetchDatas(page, pageSize) {
    // AJAX request to fetch table data with pagination parameters
    $.ajax({
        url: '/hdss/report/work',
        method: 'GET',
        data: { page: page, pageSize: pageSize }, // Pass pagination parameters
        success: function(response) {
            // Update table with fetched data
            updateTable(response);
        },
        error: function() {
            console.error('Failed to fetch data.');
        }
    });
}

// Function to update table with fetched data
function updateTable(data) {
    // Remove loading indicator
    $('#order-table tbody').empty();

    // Append fetched data to the table
    // Replace the loop with your data formatting logic
    data.forEach(function(item) {
        $('#order-table tbody').append(`
            <tr>
                <td>${item.id}</td>
                <td>${item.id1}</td>
                <td>${item.id4}</td>
                <td>${item.id5}</td>
                <td>${item.id6}</td>
                <td>${item.id7}</td>
                <td>${item.id8}</td>
                <td>${item.id9}</td>
            </tr>
        `);
    });
}

// Call fetchDatas function with initial pagination parameters when the document is ready
$(document).ready(function() {
    // Initial pagination parameters (page 1, page size 10)
    var page = 1;
    var pageSize = 10;
    
    fetchDatas(page, pageSize);
});

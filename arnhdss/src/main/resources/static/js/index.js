function ExportToExcel(type, fn, dl) {
    var table = $('#order-table').DataTable();

    // Extracting correct column names
    var header = table.columns().header().toArray().map(header => $(header).text().trim());

    // Iterate over all pages and gather data
    var allRows = [];
    var seenRowIds = new Set();

    for (var i = 0; i < table.page.info().pages; i++) {
        table.page(i).draw('page');
        var rows = table.rows({ search: 'applied' }).data().toArray();

        // Filter out rows that have already been added
        rows = rows.filter(row => {
            // Use a unique identifier for each row
            var rowId = generateRowId(row);
            if (!seenRowIds.has(rowId)) {
                seenRowIds.add(rowId);
                return true;
            }
            return false;
        });

        allRows.push(...rows);
    }


    // Convert numeric values to numbers in the data array
    //allRows = allRows.map(row => row.map(cell => {
        // Attempt to parse as a number, fallback to original value
       // return isNaN(cell) ? cell : Number(cell);
    //}));
    // Convert numeric values to numbers in the data array
	allRows = allRows.map(row => row.map(cell => {
	    // If cell is null or not a number, keep it as it is
	    if (cell === null || cell === "") {
	        return cell; // Return empty string for null or empty values
	    } else if (!isNaN(cell)) {
	        return Number(cell); // Parse as number if it's a numeric string
	    } else {
	        return cell; // Return original value for non-numeric strings
	    }
	}));


    // Add the header and rows to the worksheet
    var wsData = [header, ...allRows];

    // Create a worksheet with column types
    var ws = XLSX.utils.aoa_to_sheet(wsData);

    // Set the column types to 'n' (numeric) for all columns
    //ws['!cols'] = header.map(() => ({ t: 'n' }));

    // Create a workbook and add the worksheet
    var wb = XLSX.utils.book_new();
    XLSX.utils.book_append_sheet(wb, ws, 'data');

    // Save or download the workbook
    if (dl) {
        var wbout = XLSX.write(wb, { bookType: type, bookSST: true, type: 'binary' });
        saveAs(new Blob([s2ab(wbout)], { type: 'application/octet-stream' }), fn || ('MySheetName.' + (type || 'xlsx')));
    } else {
        XLSX.writeFile(wb, fn || ('MySheetName.' + (type || 'xlsx')));
    }
}

// Function to generate a unique identifier for each row
function generateRowId(row) {
    // Customize this based on your data structure to generate a unique identifier
    // Example: Concatenate values from different columns
    return row.join('-');
}

// Function to convert data to array buffer
function s2ab(s) {
    var buf = new ArrayBuffer(s.length);
    var view = new Uint8Array(buf);
    for (var i = 0; i < s.length; i++) view[i] = s.charCodeAt(i) & 0xFF;
    return buf;
}

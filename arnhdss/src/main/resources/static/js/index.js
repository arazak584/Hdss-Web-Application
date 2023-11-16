function ExportToExcel(type, fn, dl) {
    var table = $('#order-table').DataTable();

    // Extracting correct column names
    var header = table.columns().header().toArray().map(header => $(header).text().trim());

    // Iterate over all pages and gather data
    var allRows = [];
    for (var i = 0; i < table.page.info().pages; i++) {
        table.page(i).draw('page');
        var rows = table.rows({ search: 'applied' }).data().toArray();
        allRows.push(...rows);
    }

    // Add the header and rows to the worksheet
    var wsData = [header, ...allRows];

    // Create a worksheet
    var ws = XLSX.utils.aoa_to_sheet(wsData);

    // Create a workbook and add the worksheet
    var wb = XLSX.utils.book_new();
    XLSX.utils.book_append_sheet(wb, ws, 'Sheet 1');

    // Save or download the workbook
    return dl ?
        XLSX.write(wb, { bookType: type, bookSST: true, type: 'base64' }) :
        XLSX.writeFile(wb, fn || ('MySheetName.' + (type || 'xlsx')));
}

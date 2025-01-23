// Wait for the DOM to be fully loaded
document.addEventListener('DOMContentLoaded', function() {
    // Get references to the filter elements
    const academicYearFilter = document.getElementById('academicYearFilter');
    const semesterFilter = document.getElementById('semesterFilter');
    const tableBody = document.querySelector('table tbody');
    const noResultsMessage = document.getElementById('noResultsMessage');

    // Function to filter table rows
    function filterTable() {
        const selectedYear = academicYearFilter.value.toLowerCase();
        const selectedSemester = semesterFilter.value.toLowerCase();
        let hasVisibleRows = false;

        // Get all rows in the table body
        const rows = tableBody.getElementsByTagName('tr');

        // Loop through each row and check if it matches the filter criteria
        for (let row of rows) {
            const yearCell = row.cells[3].textContent.toLowerCase(); // Academic Year column
            const semesterCell = row.cells[4].textContent.toLowerCase(); // Semester column

            // Show row if both filters match or if the respective filter is empty
            const yearMatch = !selectedYear || yearCell.includes(selectedYear);
            const semesterMatch = !selectedSemester || semesterCell.includes(selectedSemester);
            
            if (yearMatch && semesterMatch) {
                row.style.display = '';
                hasVisibleRows = true;
            } else {
                row.style.display = 'none';
            }
        }

        // Show/hide the "No results" message
        if (noResultsMessage) {
            noResultsMessage.style.display = hasVisibleRows ? 'none' : 'block';
        }
    }

    // Add event listeners to both filters
    if (academicYearFilter) {
        academicYearFilter.addEventListener('change', filterTable);
    }
    if (semesterFilter) {
        semesterFilter.addEventListener('change', filterTable);
    }
});
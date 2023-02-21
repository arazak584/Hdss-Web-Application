document.getElementById("zip-individual-btn").addEventListener("click", function() {
  // Send an AJAX GET request to the server-side endpoint
  var xhr = new XMLHttpRequest();
  xhr.open('GET', '/api/zip/individual');
  xhr.responseType = 'arraybuffer';
  xhr.onloadstart = function() {
    // Show the progress bar
    document.getElementById("progress-bar-container").style.display = "block";
  };
  xhr.onprogress = function(event) {
    // Update the progress bar
    var percent = event.loaded / event.total * 100;
    document.getElementById("progress-bar").style.width = percent + "%";
  };
  xhr.onload = function() {
    if (this.status === 200) {
      // Handle the response by initiating the download
      var blob = new Blob([this.response], {type: 'application/zip'});
      var link = document.createElement('a');
      link.href = window.URL.createObjectURL(blob);
      link.download = 'individual.zip';
      link.click();
    }
    // Hide the progress bar
    document.getElementById("progress-bar-container").style.display = "none";
  };
  xhr.send();
});

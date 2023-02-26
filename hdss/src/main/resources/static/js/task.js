//Individual
function downloadCsv() {
  var xhr = new XMLHttpRequest();
  xhr.open('GET', '/api/zip/individual', true);
  xhr.responseType = 'blob';

  xhr.onloadstart = function(e) {
    document.querySelector('.progress-bar').style.width = '0%';
  };

  xhr.onprogress = function(e) {
    if (e.lengthComputable) {
      var percentComplete = (e.loaded / e.total) * 100;
      document.querySelector('.progress-bar').style.width = percentComplete + '%';
      document.querySelector('.progress-bar').setAttribute('aria-valuenow', percentComplete);
    }
  };

  xhr.onloadend = function(e) {
    document.querySelector('.progress-bar').style.width = '100%';
    document.querySelector('.message').textContent = 'Individual task generated successfully.';
  };

  xhr.send();
}



//Location
function downloadLocation() {
  var xhr = new XMLHttpRequest();
  xhr.open('GET', '/api/zip/location', true);
  xhr.responseType = 'blob';

  xhr.onloadstart = function(e) {
    document.querySelector('.location-progress').style.width = '0%';
  };

  xhr.onprogress = function(e) {
    if (e.lengthComputable) {
      var percentComplete = (e.loaded / e.total) * 100;
      document.querySelector('.location-progress').style.width = percentComplete + '%';
      document.querySelector('.location-progress').setAttribute('aria-valuenow', percentComplete);
    }
  };

  xhr.onloadend = function(e) {
    document.querySelector('.location-progress').style.width = '100%';
    document.querySelector('.locmessage').textContent = 'Location task generated successfully.';
  };

  xhr.send();
}


//Residency
function downloadResidency() {
  var xhr = new XMLHttpRequest();
  xhr.open('GET', '/api/zip/residency', true);
  xhr.responseType = 'blob';

  xhr.onloadstart = function(e) {
    document.querySelector('.residency-progress').style.width = '0%';
  };

  xhr.onprogress = function(e) {
    if (e.lengthComputable) {
      var percentComplete = (e.loaded / e.total) * 100;
      document.querySelector('.residency-progress').style.width = percentComplete + '%';
      document.querySelector('.residency-progress').setAttribute('aria-valuenow', percentComplete);
    }
  };

  xhr.onloadend = function(e) {
    document.querySelector('.residency-progress').style.width = '100%';
    document.querySelector('.resmessage').textContent = 'Residency task generated successfully.';
  };

  xhr.send();
}


//Socialgroup
function downloadSocialgroup() {
  var xhr = new XMLHttpRequest();
  xhr.open('GET', '/api/zip/socialgroup', true);
  xhr.responseType = 'blob';

  xhr.onloadstart = function(e) {
    document.querySelector('.socialgroup-progress').style.width = '0%';
  };

  xhr.onprogress = function(e) {
    if (e.lengthComputable) {
      var percentComplete = (e.loaded / e.total) * 100;
      document.querySelector('.socialgroup-progress').style.width = percentComplete + '%';
      document.querySelector('.socialgroup-progress').setAttribute('aria-valuenow', percentComplete);
    }
  };

  xhr.onloadend = function(e) {
    document.querySelector('.socialgroup-progress').style.width = '100%';
    document.querySelector('.somessage').textContent = 'Socialgroup task generated successfully.';
  };

  xhr.send();
}


//Relationship
function downloadRelationship() {
  var xhr = new XMLHttpRequest();
  xhr.open('GET', '/api/zip/relationship', true);
  xhr.responseType = 'blob';

  xhr.onloadstart = function(e) {
    document.querySelector('.relationship-progress').style.width = '0%';
  };

  xhr.onprogress = function(e) {
    if (e.lengthComputable) {
      var percentComplete = (e.loaded / e.total) * 100;
      document.querySelector('.relationship-progress').style.width = percentComplete + '%';
      document.querySelector('.relationship-progress').setAttribute('aria-valuenow', percentComplete);
    }
  };

  xhr.onloadend = function(e) {
    document.querySelector('.relationship-progress').style.width = '100%';
    document.querySelector('.relmessage').textContent = 'Relationship task generated successfully.';
  };

  xhr.send();
}


//Relationship
function downloadPregnancy() {
  var xhr = new XMLHttpRequest();
  xhr.open('GET', '/api/zip/pregnancy', true);
  xhr.responseType = 'blob';

  xhr.onloadstart = function(e) {
    document.querySelector('.pregnancy-progress').style.width = '0%';
  };

  xhr.onprogress = function(e) {
    if (e.lengthComputable) {
      var percentComplete = (e.loaded / e.total) * 100;
      document.querySelector('.pregnancy-progress').style.width = percentComplete + '%';
      document.querySelector('.pregnancy-progress').setAttribute('aria-valuenow', percentComplete);
    }
  };

  xhr.onloadend = function(e) {
    document.querySelector('.pregnancy-progress').style.width = '100%';
    document.querySelector('.pregmessage').textContent = 'Pregnancy task generated successfully.';
  };

  xhr.send();
}

function getName() {
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      document.getElementById("test").innerHTML = this.responseText;
    }
  };
  xhttp.open("GET", "ProfileServlet", true);
  xhttp.send();
}

function displayTable(){
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			document.getElementById("reimtble").innerHTML = this.responseText;
	    }
	};
	xhttp.open("GET", "EmployeeTableServlet", true);
	xhttp.send();
	
	
}
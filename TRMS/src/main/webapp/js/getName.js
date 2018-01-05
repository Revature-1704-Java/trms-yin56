//get the name of the current employee

function displayPage(){
	getName();
	displayTable();
	getApprovedTable();
	displayApproveTable();
}

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

//display pending reimbursemnts for employees to view
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

//get of employees approved requests
function getApprovedTable(){
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			document.getElementById("aprtble").innerHTML = this.responseText;
	    }
	};
	xhttp.open("GET", "ApprovedTableServlet", true);
	xhttp.send();
	
	
}

//get table for managers to approve
function displayApproveTable(){
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			document.getElementById("ptble").innerHTML = this.responseText;
	    }
	};
	xhttp.open("GET", "SupervisorTableServlet", true);
	xhttp.send();
	
	
}


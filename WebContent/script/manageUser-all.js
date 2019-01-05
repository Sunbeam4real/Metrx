//search quiz by quiz name, access code
$(document).ready(function(){
	$("#searchUser").on("keyup", function() {
	    var value = $(this).val().toLowerCase();
	    $("#userTable tr").filter(function() {
	      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
	    });
	  });
	});
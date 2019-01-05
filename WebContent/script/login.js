//Login validation
$(document).ready(function() {
	
	$('#loginform').submit(function(e) {
		
		var username = $('#username').val();
		var password = $('#password').val();
		
		var isValid = true;
		
		$('#username').removeClass("border border-danger");
		$('#password').removeClass("border border-danger");
		
		$(".error").remove();
		
		if (username.length < 1) {
			$('#username').addClass("border border-danger");
			$('#username').after('<small class="error form-text text-danger">This field is required</small>');
			isValid = false;
		}
		if (password.length < 1) {
			$('#password').addClass("border border-danger");
			$('#password').after('<small class="error form-text text-danger">This field is required</small>');
			isValid = false;
		}
	
		if(!isValid){
			return false;
		}
		
	});  
});
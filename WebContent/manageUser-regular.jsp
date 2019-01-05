<jsp:include page="header.jsp"></jsp:include>
<%@page import="com.fdmgroup.model.T_User"

 %>
<%

	T_User user = (T_User) session.getAttribute("loggedinuser");


%>

<div class="container pt-5 pb-5">
	<div class="row">
		<div class="col-2"></div>
		<div class="col-8">
			<form method="post" action="updateUserNorm">
				<fieldset>
					<legend>
						<i class="fas fa-user-edit mr-2"></i>Edit Profile
					</legend>
					<div class="form-group">
						<h5><i class="fas fa-user mr-2"></i>User Information</h5>
						<label for="firstname">First name:</label> <input type="text"
							class="form-control" id="firstname" placeholder="First name"
							name="firstname" value="<%=user.getFirstName() %>" disabled="disabled"> 
						<label for="lastname">Last name:</label>
						<input type="text" class="form-control" id="lastname"
							placeholder="Last name" name="lastname" value="<%=user.getLastName() %>" disabled="disabled"> 
						<label for="position">Position:</label> 
						<input type="text"
							class="form-control" id="position" placeholder="Position"
							name="position" value="<%=user.getPosition() %>" disabled="disabled">
					</div>
					<br>
					<hr>
					<br>
					<div class="form-group">
						<h5><i class="fas fa-key mr-2"></i>Change Password</h5>
						<label for="currentPW">Current Password:</label> <input type="password"
							class="form-control" id="currentPW" placeholder="Current password"
							name="currentPW"> 
						<label for="newPW">New Password:</label>
						<input type="password" class="form-control" id="newPW"
							placeholder="New password" name="newPW"S> 
						<label for="confirmPW">Confirm New Password:</label> 
						<input type="password"
							class="form-control" id="confirmPW" placeholder="Confirm new password"
							name="confirmPW">
					</div>

					<div class="row mt-4 text-center">
						<div class="col">
						<button class="btn btn-metrx-blue ml-3 mr-3" type="submit">Save</button>
						<button class="btn btn-secondary ml-3 mr-3" type="reset">Clear</button>
						</div>
					</div>
				</fieldset>
				
			</form>
			${message}
		</div>
		<div class="col-2"></div>
	</div>
</div>

    <script type="text/javascript">
     $(document).ready(function(e) {
        $('#viewDoc').lc_switch();
    
        // triggered each time a field changes status
        $(document).on('lcs-statuschange', '.lcs_check', function() {
            var status 	= ($(this).is(':checked')) ? 'checked' : 'unchecked',
				subj 	= ($(this).attr('type') == 'radio') ? 'radio #' : 'checkbox #',
				num		= $(this).val(); 
        });
        
        
        // triggered each time a field is checked
        $(document).on('lcs-on', '.lcs_check', function() {
			var subj 	= ($(this).attr('type') == 'radio') ? 'radio #' : 'checkbox #',
				num		= $(this).val(); 
        });
        
        
        // triggered each time a is unchecked
        $(document).on('lcs-off', '.lcs_check', function() {
            var subj 	= ($(this).attr('type') == 'radio') ? 'radio #' : 'checkbox #',
				num		= $(this).val(); 
        });
    });

    </script>

<jsp:include page="footer.jsp"></jsp:include>
<jsp:include page="header.jsp"></jsp:include>

<div class="container pt-5 pb-5">
	<div class="row">
		<div class="col-2"></div>
		<div class="col-8">
			<form method="post" action="register">
				<fieldset>
					<legend>
						<i class="fas fa-user-plus mr-2"></i>Add User
					</legend>
					<div class="form-group">
						<label for="firstname">First name:</label> 
						<input type="text"
							class="form-control" id="firstname" placeholder="First name"
							name="firstname"> 
						<label for="lastname">Last name:</label>
						<input type="text" class="form-control" id="lastname"
							placeholder="Last name" name="lastname"> 
							<label for="firstname">Username:</label> 
						<input type="text"
							class="form-control" id="username" placeholder="Username"
							name="username"> 
						<label for="position">Position:</label> 
						<input type="text"
							class="form-control" id="position" placeholder="Position"
							name="position">
						<label for="password">Password:</label> 
						<input type="password"
							class="form-control" id="password" placeholder="Password"
							name="password"  >
					</div>
					<label for="firstname">Privilege:</label>
					<div class="accordion" id="privilege">
<!-- DOCUMENT privilege-->
						<div class="card">
							<div class="card-header" id="headingDocument">
								<div class="mb-0"
										data-toggle="collapse" data-target="#collapseDocument"
										aria-expanded="true" aria-controls="collapseDocument">
										Document
								</div>
							</div>
							<div id="collapseDocument" class="collapse"
								aria-labelledby="headingDocument" data-parent="#privilege">
								<div class="card-body">
									<ul class="list-group list-group-flush">
									
										<li class="list-group-item">
											<div class="row">
												<div class="col-6">
													View Document
												</div>
												<div class="col text-right">
													<div class="lcs_wrap">
														<input type="checkbox" name="priv" value="ViewDoc" id="ViewDoc"
															class="lcs_check" autocomplete="off">
														<div class="lcs_switch  lcs_checkbox_switch lcs_off">
															<div class="lcs_cursor"></div>
															<div class="lcs_label lcs_label_on">ON</div>
															<div class="lcs_label lcs_label_off">OFF</div>
														</div>
													</div>
												</div>
											</div>
										</li>
										
										<li class="list-group-item">
											<div class="row">
												<div class="col-6">Export Document</div>
												<div class="col text-right">
													<div class="lcs_wrap">
														<input type="checkbox" name="priv" value="ExportDoc"
															id="ExportDoc" class="lcs_check" autocomplete="off">
														<div class="lcs_switch  lcs_checkbox_switch lcs_off">
															<div class="lcs_cursor"></div>
															<div class="lcs_label lcs_label_on">ON</div>
															<div class="lcs_label lcs_label_off">OFF</div>
														</div>
													</div>
												</div>
											</div>
										</li>
										
										<li class="list-group-item">
											<div class="row">
												<div class="col-6">Upload Document</div>
												<div class="col text-right">
													<div class="lcs_wrap">
														<input type="checkbox" name="priv" value="UploadDoc"
															id="UploadDoc" class="lcs_check" autocomplete="off">
														<div class="lcs_switch  lcs_checkbox_switch lcs_off">
															<div class="lcs_cursor"></div>
															<div class="lcs_label lcs_label_on">ON</div>
															<div class="lcs_label lcs_label_off">OFF</div>
														</div>
													</div>
												</div>
											</div>
										</li>
									</ul>
								</div>
							</div>
						</div>
<!-- USER privilege -->
						<div class="card">
							<div class="card-header" id="headingUser">
								<div class="mb-0"
										data-toggle="collapse" data-target="#collapseUser"
										aria-expanded="true" aria-controls="collapseUser">
										User
								</div>
							</div>
							<div id="collapseUser" class="collapse"
								aria-labelledby="headingUser" data-parent="#privilege">
								<div class="card-body">
									<ul class="list-group list-group-flush">
									
										<li class="list-group-item">
											<div class="row">
												<div class="col-6">
													View Self
												</div>
												<div class="col text-right">
													<div class="lcs_wrap">
														<input type="checkbox" name="priv" value="ViewSelf" id="ViewSelf"
															class="lcs_check" autocomplete="off">
														<div class="lcs_switch  lcs_checkbox_switch lcs_off">
															<div class="lcs_cursor"></div>
															<div class="lcs_label lcs_label_on">ON</div>
															<div class="lcs_label lcs_label_off">OFF</div>
														</div>
													</div>
												</div>
											</div>
										</li>
										
										<li class="list-group-item">
											<div class="row">
												<div class="col-6">
													View User Profiles
												</div>
												<div class="col text-right">
													<div class="lcs_wrap">
														<input type="checkbox" name="priv" value="ViewProfiles" id="ViewProfiles"
															class="lcs_check" autocomplete="off">
														<div class="lcs_switch  lcs_checkbox_switch lcs_off">
															<div class="lcs_cursor"></div>
															<div class="lcs_label lcs_label_on">ON</div>
															<div class="lcs_label lcs_label_off">OFF</div>
														</div>
													</div>
												</div>
											</div>
										</li>
									
										<li class="list-group-item">
											<div class="row">
												<div class="col-6">
													Create User
												</div>
												<div class="col text-right">
													<div class="lcs_wrap">
														<input type="checkbox" name="priv" value="CreateUser" id="CreateUser"
															class="lcs_check" autocomplete="off">
														<div class="lcs_switch  lcs_checkbox_switch lcs_off">
															<div class="lcs_cursor"></div>
															<div class="lcs_label lcs_label_on">ON</div>
															<div class="lcs_label lcs_label_off">OFF</div>
														</div>
													</div>
												</div>
											</div>
										</li>
										
										<li class="list-group-item">
											<div class="row">
												<div class="col-6">Edit Self</div>
												<div class="col text-right">
													<div class="lcs_wrap">
														<input type="checkbox" name="priv" value="EditUser"
															id="EditUser" class="lcs_check" autocomplete="off">
														<div class="lcs_switch  lcs_checkbox_switch lcs_off">
															<div class="lcs_cursor"></div>
															<div class="lcs_label lcs_label_on">ON</div>
															<div class="lcs_label lcs_label_off">OFF</div>
														</div>
													</div>
												</div>
											</div>
										</li>
										
										<li class="list-group-item">
											<div class="row">
												<div class="col-6">Edit Users</div>
												<div class="col text-right">
													<div class="lcs_wrap">
														<input type="checkbox" name="priv" value="EditUserAdmin"
															id="EditUserAdmin" class="lcs_check" autocomplete="off">
														<div class="lcs_switch  lcs_checkbox_switch lcs_off">
															<div class="lcs_cursor"></div>
															<div class="lcs_label lcs_label_on">ON</div>
															<div class="lcs_label lcs_label_off">OFF</div>
														</div>
													</div>
												</div>
											</div>
										</li>
										
									</ul>
								</div>
							</div>
						</div>
<!-- ACTIVITY privilege -->						
						<div class="card">
							<div class="card-header" id="headingActivity">
								<div class="mb-0"
										data-toggle="collapse" data-target="#collapseActivity"
										aria-expanded="true" aria-controls="collapseActivity">
										Activity
								</div>
							</div>
							<div id="collapseActivity" class="collapse"
								aria-labelledby="headingActivity" data-parent="#privilege">
								<div class="card-body">
									<ul class="list-group list-group-flush">
										<li class="list-group-item">
											<div class="row">
												<div class="col-6">
													View All Activities
												</div>
												<div class="col text-right">
													<div class="lcs_wrap">
														<input type="checkbox" name="priv" value="ViewAllActivities" id="ViewAllActivities"
															class="lcs_check" autocomplete="off">
														<div class="lcs_switch  lcs_checkbox_switch lcs_off">
															<div class="lcs_cursor"></div>
															<div class="lcs_label lcs_label_on">ON</div>
															<div class="lcs_label lcs_label_off">OFF</div>
														</div>
													</div>
												</div>
											</div>
										</li>
										
										<li class="list-group-item">
											<div class="row">
												<div class="col-6">Create Business Cases</div>
												<div class="col text-right">
													<div class="lcs_wrap">
														<input type="checkbox" name="priv" value="CreateBusinessCase"
															id="CreateBusinessCase" class="lcs_check" autocomplete="off">
														<div class="lcs_switch  lcs_checkbox_switch lcs_off">
															<div class="lcs_cursor"></div>
															<div class="lcs_label lcs_label_on">ON</div>
															<div class="lcs_label lcs_label_off">OFF</div>
														</div>
													</div>
												</div>
											</div>
										</li>
										<!-- <li class="list-group-item">
											<div class="row">
												<div class="col-6">Upload Activity</div>
												<div class="col text-right">
													<div class="lcs_wrap">
														<input type="checkbox" name="uploadActivity" value="1"
															id="uploadActivity" class="lcs_check" autocomplete="off">
														<div class="lcs_switch  lcs_checkbox_switch lcs_off">
															<div class="lcs_cursor"></div>
															<div class="lcs_label lcs_label_on">ON</div>
															<div class="lcs_label lcs_label_off">OFF</div>
														</div>
													</div>
												</div>
											</div>
										</li> -->
									</ul>
								</div>
							</div>
						</div>
					</div><!-- end of privilege -->
				<div  class="form-group" id="mes">
				<%String login_msg=(String)request.getAttribute("message");  
						if(login_msg!=null)
						out.println("<font color=red size=4px>"+login_msg+"</font>");
					%>
				</div> 
					<div class="row mt-4 text-center">
						<div class="col">
						<button class="btn btn-metrx-blue ml-3 mr-3" type="submit">Save</button>
						<button class="btn btn-secondary ml-3 mr-3" type="reset">Clear</button>
						</div>
					</div>
				</fieldset>
			</form>
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
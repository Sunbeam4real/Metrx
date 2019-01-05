<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="header.jsp"></jsp:include>
<div class="container pt-5 pb-5">
	<div class="row">
		<div class="col-2"></div>
		<div class="col-8">
			<form method="post" action="updateUserOp">
				<fieldset>
					<legend>
						<i class="fas fa-user-edit mr-2"></i>Manage User
					</legend>
					<div>
						<h5>
							<i class="fas fa-user mr-2"></i>User Information
						</h5>
						<label for="firstname">First name:</label> <input type="text"
							class="form-control" id="firstname" placeholder="First name"
							name="firstname" value="${user.firstName}"> <label
							for="lastname">Last name:</label> <input type="text"
							class="form-control" id="lastname" placeholder="Last name"
							name="lastname" value="${user.lastName}"> <label
							for="position">Position:</label> <input type="text"
							class="form-control" id="position" placeholder="Position"
							name="position" value="${user.position}">
					</div>
					<br>
					<hr>
					<br>
					<h5>
						<i class="fas fa-trophy mr-2"></i>Goals
					</h5>


				<div class="form-group row">
					<label class="col-2 col-form-label text-right" for="call">Call:</label>
					<div class="col-2">
						<input type="text" class="form-control" id="call" name="call"
							value="">
					</div>
					<label class="col-2 col-form-label text-right" for="email">Email:</label>
					<div class="col-2">
						<input type="text" class="form-control" id="email" name="email"
							value="">
					</div>
					<label class="col-2 col-form-label text-right" for="Meeting">Meeting:</label>
					<div class="col-2">
						<input type="text" class="form-control" id="Meeting"
							name="meeting" value="">
					</div>
					<label class="col-2 col-form-label text-right" for="Interview">Interview:</label>
					<div class="col-2">
						<input type="text" class="form-control" id="Interview"
							name="interview" value="">
					</div>
					<label class="col-2 col-form-label text-right" for="ClientVisited">ClientVisit:</label>
					<div class="col-2">
						<input type="text" class="form-control" id="ClientVisited"
							name="clientVisited" value="">
					</div>
				</div>
					<div class="form-group">
						<label for="dateOfMessage">Date</label> <input type="date"
							class="form-control" id="dateOfMessage" name="dateOfMessage"
							pattern="[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])"
							placeholder="yyyy-mm-dd">
					</div>

					<br>
					<hr>
					<br>
					<h5>
						<i class="fas fa-unlock-alt mr-2"></i>Privilege
					</h5>
					<div class="accordion" id="privilege">
						<!-- DOCUMENT privilege-->
						<div class="card">
							<div class="card-header" id="headingDocument">
								<div class="mb-0" data-toggle="collapse"
									data-target="#collapseDocument" aria-expanded="true"
									aria-controls="collapseDocument">Document</div>
							</div>
							<div id="collapseDocument" class="collapse"
								aria-labelledby="headingDocument" data-parent="#privilege">
								<div class="card-body">
									<ul class="list-group list-group-flush">
										<li class="list-group-item">
											<div class="row">
												<div class="col-6">View Document</div>
												<div class="col text-right">

													<div class="lcs_wrap">
														<c:choose>
															<c:when test="${ViewDoc == 1}">
																<input type="checkbox" name="enumIndexes"
																	value="ViewDoc" id="viewDoc" class="lcs_check"
																	autocomplete="off" checked="checked">
																<div class="lcs_switch  lcs_checkbox_switch lcs_on">
																	<div class="lcs_cursor"></div>
																	<div class="lcs_label lcs_label_on">ON</div>
																	<div class="lcs_label lcs_label_off">OFF</div>
																</div>
															</c:when>
															<c:otherwise>
																<input type="checkbox" name="enumIndexes"
																	value="ViewDoc" id="viewDoc" class="lcs_check"
																	autocomplete="off">

																<div class="lcs_switch  lcs_checkbox_switch lcs_off">
																	<div class="lcs_cursor"></div>
																	<div class="lcs_label lcs_label_on">ON</div>
																	<div class="lcs_label lcs_label_off">OFF</div>
																</div>
															</c:otherwise>
														</c:choose>
													</div>
												</div>
											</div>
										</li>
										<li class="list-group-item">
											<div class="row">
												<div class="col-6">Export Document</div>
												<div class="col text-right">
													<div class="lcs_wrap">
														<c:choose>
															<c:when test="${ExportDoc == 1}">
																<input type="checkbox" name="enumIndexes"
																	value="ExportDoc" id="ExportDoc" class="lcs_check"
																	autocomplete="off" checked="checked">
																<div class="lcs_switch  lcs_checkbox_switch lcs_on">
																	<div class="lcs_cursor"></div>
																	<div class="lcs_label lcs_label_on">ON</div>
																	<div class="lcs_label lcs_label_off">OFF</div>
																</div>
															</c:when>
															<c:otherwise>
																<input type="checkbox" name="enumIndexes"
																	value="ExportDoc" id="ExportDoc" class="lcs_check"
																	autocomplete="off">
																<div class="lcs_switch  lcs_checkbox_switch lcs_off">
																	<div class="lcs_cursor"></div>
																	<div class="lcs_label lcs_label_on">ON</div>
																	<div class="lcs_label lcs_label_off">OFF</div>
																</div>
															</c:otherwise>
														</c:choose>
													</div>
												</div>
											</div>
										</li>
										<li class="list-group-item">
											<div class="row">
												<div class="col-6">Upload Document</div>
												<div class="col text-right">
													<div class="lcs_wrap">
														<c:choose>
															<c:when test="${UploadDoc == 1}">
																<input type="checkbox" name="enumIndexes"
																	value="UploadDoc" id="UploadDoc" class="lcs_check"
																	autocomplete="off" checked="checked">
																<div class="lcs_switch  lcs_checkbox_switch lcs_on">
																	<div class="lcs_cursor"></div>
																	<div class="lcs_label lcs_label_on">ON</div>
																	<div class="lcs_label lcs_label_off">OFF</div>
																</div>
															</c:when>
															<c:otherwise>
																<input type="checkbox" name="enumIndexes"
																	value="UploadDoc" id="UploadDoc" class="lcs_check"
																	autocomplete="off">
																<div class="lcs_switch  lcs_checkbox_switch lcs_off">
																	<div class="lcs_cursor"></div>
																	<div class="lcs_label lcs_label_on">ON</div>
																	<div class="lcs_label lcs_label_off">OFF</div>
																</div>
															</c:otherwise>
														</c:choose>
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
								<div class="mb-0" data-toggle="collapse"
									data-target="#collapseUser" aria-expanded="true"
									aria-controls="collapseUser">User</div>
							</div>
							<div id="collapseUser" class="collapse"
								aria-labelledby="headingUser" data-parent="#privilege">
								<div class="card-body">
									<ul class="list-group list-group-flush">
										<li class="list-group-item">
											<div class="row">
												<div class="col-6">View Self</div>
												<div class="col text-right">
													<div class="lcs_wrap">

														<c:choose>
															<c:when test="${ViewSelf == 1}">
																<input type="checkbox" name="enumIndexes"
																	value="ViewSelf" id="ViewSelf" class="lcs_check"
																	autocomplete="off" checked="checked">
																<div class="lcs_switch  lcs_checkbox_switch lcs_on">
																	<div class="lcs_cursor"></div>
																	<div class="lcs_label lcs_label_on">ON</div>
																	<div class="lcs_label lcs_label_off">OFF</div>
																</div>
															</c:when>
															<c:otherwise>
																<input type="checkbox" name="enumIndexes"
																	value="ViewSelf" id="ViewSelf" class="lcs_check"
																	autocomplete="off">

																<div class="lcs_switch  lcs_checkbox_switch lcs_off">
																	<div class="lcs_cursor"></div>
																	<div class="lcs_label lcs_label_on">ON</div>
																	<div class="lcs_label lcs_label_off">OFF</div>
																</div>
															</c:otherwise>
														</c:choose>
													</div>
												</div>
											</div>
										</li>
										<li class="list-group-item">
											<div class="row">
												<div class="col-6">View User Profiles</div>
												<div class="col text-right">
													<div class="lcs_wrap">

														<c:choose>
															<c:when test="${ViewProfiles == 1}">
																<input type="checkbox" name="enumIndexes"
																	value="ViewProfiles" id="ViewProfiles"
																	class="lcs_check" autocomplete="off" checked="checked">
																<div class="lcs_switch  lcs_checkbox_switch lcs_on">
																	<div class="lcs_cursor"></div>
																	<div class="lcs_label lcs_label_on">ON</div>
																	<div class="lcs_label lcs_label_off">OFF</div>
																</div>
															</c:when>
															<c:otherwise>
																<input type="checkbox" name="enumIndexes"
																	value="ViewProfiles" id="ViewProfiles"
																	class="lcs_check" autocomplete="off">

																<div class="lcs_switch  lcs_checkbox_switch lcs_off">
																	<div class="lcs_cursor"></div>
																	<div class="lcs_label lcs_label_on">ON</div>
																	<div class="lcs_label lcs_label_off">OFF</div>
																</div>
															</c:otherwise>
														</c:choose>
													</div>
												</div>
											</div>
										</li>
										<li class="list-group-item">
											<div class="row">
												<div class="col-6">CreateUser</div>
												<div class="col text-right">
													<div class="lcs_wrap">
														<c:choose>
															<c:when test="${CreateUser == 1}">
																<input type="checkbox" name="enumIndexes"
																	value="CreateUser" id="CreateUser" class="lcs_check"
																	autocomplete="off" checked="checked">
																<div class="lcs_switch  lcs_checkbox_switch lcs_on">
																	<div class="lcs_cursor"></div>
																	<div class="lcs_label lcs_label_on">ON</div>
																	<div class="lcs_label lcs_label_off">OFF</div>
																</div>
															</c:when>
															<c:otherwise>
																<input type="checkbox" name="enumIndexes"
																	value="CreateUser" id="CreateUser" class="lcs_check"
																	autocomplete="off">
																<div class="lcs_switch  lcs_checkbox_switch lcs_off">
																	<div class="lcs_cursor"></div>
																	<div class="lcs_label lcs_label_on">ON</div>
																	<div class="lcs_label lcs_label_off">OFF</div>
																</div>
															</c:otherwise>
														</c:choose>
													</div>
												</div>
											</div>
										</li>
										<li class="list-group-item">
											<div class="row">
												<div class="col-6">Edit Self</div>
												<div class="col text-right">
													<div class="lcs_wrap">
														<c:choose>
															<c:when test="${EditUser == 1}">
																<input type="checkbox" name="enumIndexes"
																	value="EditUser" id="EditUser" class="lcs_check"
																	autocomplete="off" checked="checked">
																<div class="lcs_switch  lcs_checkbox_switch lcs_on">
																	<div class="lcs_cursor"></div>
																	<div class="lcs_label lcs_label_on">ON</div>
																	<div class="lcs_label lcs_label_off">OFF</div>
																</div>
															</c:when>
															<c:otherwise>
																<input type="checkbox" name="enumIndexes"
																	value="EditUser" id="EditUser" class="lcs_check"
																	autocomplete="off">
																<div class="lcs_switch  lcs_checkbox_switch lcs_off">
																	<div class="lcs_cursor"></div>
																	<div class="lcs_label lcs_label_on">ON</div>
																	<div class="lcs_label lcs_label_off">OFF</div>
																</div>
															</c:otherwise>
														</c:choose>
													</div>
												</div>
											</div>
										</li>
										<li class="list-group-item">
											<div class="row">
												<div class="col-6">Edit Users</div>
												<div class="col text-right">
													<div class="lcs_wrap">
														<c:choose>
															<c:when test="${EditUserAdmin == 1}">
																<input type="checkbox" name="enumIndexes"
																	value="EditUserAdmin" id="EditUserAdmin"
																	class="lcs_check" autocomplete="off" checked="checked">
																<div class="lcs_switch  lcs_checkbox_switch lcs_on">
																	<div class="lcs_cursor"></div>
																	<div class="lcs_label lcs_label_on">ON</div>
																	<div class="lcs_label lcs_label_off">OFF</div>
																</div>
															</c:when>
															<c:otherwise>
																<input type="checkbox" name="enumIndexes"
																	value="EditUserAdmin" id="EditUserAdmin"
																	class="lcs_check" autocomplete="off">
																<div class="lcs_switch  lcs_checkbox_switch lcs_off">
																	<div class="lcs_cursor"></div>
																	<div class="lcs_label lcs_label_on">ON</div>
																	<div class="lcs_label lcs_label_off">OFF</div>
																</div>
															</c:otherwise>
														</c:choose>
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
								<div class="mb-0" data-toggle="collapse"
									data-target="#collapseActivity" aria-expanded="true"
									aria-controls="collapseActivity">Activity</div>
							</div>
							<div id="collapseActivity" class="collapse"
								aria-labelledby="headingActivity" data-parent="#privilege">
								<div class="card-body">
									<ul class="list-group list-group-flush">

										<li class="list-group-item">
											<div class="row">
												<div class="col-6">Export Activity</div>
												<div class="col text-right">
													<div class="lcs_wrap">
														<c:choose>
															<c:when test="${ViewAllActivities == 1}">
																<input type="checkbox" name="enumIndexes"
																	value="ViewAllActivities" id="ViewAllActivities"
																	class="lcs_check" autocomplete="off" checked="checked">
																<div class="lcs_switch  lcs_checkbox_switch lcs_on">
																	<div class="lcs_cursor"></div>
																	<div class="lcs_label lcs_label_on">ON</div>
																	<div class="lcs_label lcs_label_off">OFF</div>
																</div>
															</c:when>
															<c:otherwise>
																<input type="checkbox" name="enumIndexes"
																	value="ViewAllActivities" id="ViewAllActivities"
																	class="lcs_check" autocomplete="off">
																<div class="lcs_switch  lcs_checkbox_switch lcs_off">
																	<div class="lcs_cursor"></div>
																	<div class="lcs_label lcs_label_on">ON</div>
																	<div class="lcs_label lcs_label_off">OFF</div>
																</div>
															</c:otherwise>
														</c:choose>
													</div>
												</div>
											</div>
										</li>

										<li class="list-group-item">
											<div class="row">
												<div class="col-6">Create Business Cases</div>
												<div class="col text-right">
													<div class="lcs_wrap">
														<c:choose>
															<c:when test="${createBusinessCase == 1}">
																<input type="checkbox" name="enumIndexes"
																	value="CreateBusinessCase" id="CreateBusinessCase"
																	class="lcs_check" autocomplete="off" checked="checked">
																<div class="lcs_switch  lcs_checkbox_switch lcs_on">
																	<div class="lcs_cursor"></div>
																	<div class="lcs_label lcs_label_on">ON</div>
																	<div class="lcs_label lcs_label_off">OFF</div>
																</div>
															</c:when>
															<c:otherwise>
																<input type="checkbox" name="enumIndexes"
																	value="CreateBusinessCase" id="CreateBusinessCase"
																	class="lcs_check" autocomplete="off">
																<div class="lcs_switch  lcs_checkbox_switch lcs_off">
																	<div class="lcs_cursor"></div>
																	<div class="lcs_label lcs_label_on">ON</div>
																	<div class="lcs_label lcs_label_off">OFF</div>
																</div>
															</c:otherwise>
														</c:choose>
													</div>
												</div>
											</div>
										</li>
									</ul>
								</div>
							</div>
						</div>
					</div>
					<!-- end of privilege -->
					<div class="row mt-4 text-center">
						<div class="col">

							<button class="btn btn-metrx-blue ml-3 mr-3" type="submit"
								aria-label="userId" name="userid" value="${user.userId }">Save</button>
							<button class="btn btn-secondary ml-3 mr-3" type="reset">Clear</button>

						</div>
					</div>
				</fieldset>
			</form>
			<div class="row">
				<div class="col-6 text-right">
					<form action="deleteUser">
						<button class="btn btn-metrx-red ml-3 mr-3" type="submit"
							aria-label="userId" name="deleteUser" value="${user.userId }">Delete</button>
					</form>
				</div>
				<div class="col-6">
					<c:if test="${user.isActive == 'true'}">
						<form action="altActivateUser">
							<button class="btn btn-metrx-orange ml-3 mr-3" type="submit"
								aria-label="userId" name="deleteUser" value="${user.userId }">Deactivate</button>
						</form>
					</c:if>
					<c:if test="${user.isActive == 'false'}">
						<form action="altActivateUser">
							<button class="btn btn-success ml-3 mr-3" type="submit"
								aria-label="userId" name="deleteUser" value="${user.userId }">Activate</button>
						</form>
					</c:if>
				</div>
			</div>
		</div>
		<div class="col-2"></div>
	</div>
</div>
<script type="text/javascript">
	$(document)
			.ready(
					function(e) {
						$('#viewDoc').lc_switch();
						// triggered each time a field changes status
						$(document)
								.on(
										'lcs-statuschange',
										'.lcs_check',
										function() {
											var status = ($(this)
													.is(':checked')) ? 'checked'
													: 'unchecked', subj = ($(
													this).attr('type') == 'radio') ? 'radio #'
													: 'checkbox #', num = $(
													this).val();
										});
						// triggered each time a field is checked
						$(document)
								.on(
										'lcs-on',
										'.lcs_check',
										function() {
											var subj = ($(this).attr('type') == 'radio') ? 'radio #'
													: 'checkbox #', num = $(
													this).val();
										});
						// triggered each time a is unchecked
						$(document)
								.on(
										'lcs-off',
										'.lcs_check',
										function() {
											var subj = ($(this).attr('type') == 'radio') ? 'radio #'
													: 'checkbox #', num = $(
													this).val();
										});
					});
</script>

<jsp:include page="footer.jsp"></jsp:include>





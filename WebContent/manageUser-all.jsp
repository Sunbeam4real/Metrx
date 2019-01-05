<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp"></jsp:include>
<%@ page import="java.util.List"
import="com.fdmgroup.model.T_User"
import="java.util.ArrayList"%>
<% List<T_User> users = (ArrayList<T_User>)request.getAttribute("users"); %>
<div class="container pt-5 pb-5">
	<div class="row">
		<div class="col-2"></div>
		<div class="col-8">
			<div class="row">
				<div class="col">
					<legend>
						<i class="fas fa-users-cog mr-2"></i>Manage User
					</legend>
				</div>
				<div class="col-auto">
					<label class="sr-only" for="searchUser">Search User</label>
					<div class="input-group mb-2">
						<div class="input-group-prepend">
							<div class="input-group-text">
								<i class="fas fa-search"></i>
							</div>
						</div>
						<input type="text" class="form-control" id="searchUser"
							placeholder="Search User">
					</div>
				</div>
				<div class="col-auto text-right">
					<a href="addUser.jsp" class="btn text-rights btn-metrx-blue">Add
						new User</a>
				</div>
			</div>
			<table class="table table-hover mt-4">
				<thead class="thead-dark">
					<tr>
						<th scope="col">First</th>
						<th scope="col">Last</th>
						<th scope="col">Designation</th>
						<th scope="col">Edit</th>
					</tr>
				</thead>
				<tbody id="userTable">
					<c:forEach items="${users}" var="user">
						<c:if test = "${user.isActive  == 'false'}">
							<tr class="table-active" >
						</c:if>
						<c:if test = "${user.isActive  == 'true'}">
							<tr>
						</c:if>
								<td>${user.firstName }</td>
								<td>${user.lastName }</td>
								<td>${user.position }</td>
								<td>
									<form method="post" action="goToEditProfileOp">
										<button type="submit" class="btn btn-metrx-blue" aria-label="userId" name="userId" value="${user.userId }" >
										Edit<i class="fas fa-edit ml-2"></i>
										</button>
									</form>
								</td>
							</tr>
					</c:forEach>
				</tbody>
			</table>
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
<!-- external JS -->
<script src="script/manageUser-all.js"></script>	
<jsp:include page="footer.jsp"></jsp:include>
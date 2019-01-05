<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp"></jsp:include>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
    	<script type="text/javascript"> 
      			$(document).ready( function() {
        		$('#mes').delay(2000).fadeOut();
      	});
    	</script>
<div class="container pt-5 pb-5">
	<div class="row">
		<div class="col-2"></div>
		<div class="col-8">
			<form method="post" action="login" id="loginform">
			<fieldset>
			<legend>Login</legend>
				<c:if test="${(error != null)}">
					<small id="backend-error" class="error form-text text-danger">${error}</small>
				</c:if>
				<div class="form-group">
					<label for="username">Username</label> 
					<input type="text" class="form-control" id="username" placeholder="Username" name="username"> 
				</div>
				<div  class="form-group">
					<label for="password">Password</label> 
					<input type="password" class="form-control" id="password"
						placeholder="Password" name="password">
				</div>
				<div class="row">
					<div class="col text-right">
						<button type="submit" class="btn btn-metrx-blue pl-4 pr-4">Login</button>
					</div>
				</div>
<%-- 				<div  class="form-group" id="mes">
				<%String login_msg=(String)request.getAttribute("error");  
						if(login_msg!=null)
						out.println("<font color=red size=4px>"+login_msg+"</font>");
					%>
				</div> --%>
			</fieldset>
			
			</form>
		</div>
		<div class="col-2"></div>
	</div>
</div>
<!-- external JS -->
<script src="script/login.js"></script>
<jsp:include page="footer.jsp"></jsp:include>
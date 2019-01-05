<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"
import="com.fdmgroup.model.BusinessCase"
%>
<%
BusinessCase bc = (BusinessCase) request.getAttribute("businessCase");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>sentBusinessCaseDetail</title>
<style>
	/* Style the tab */
	.tab {
	  overflow: hidden;
	}
	
	/* Style the buttons inside the tab */
	.tab button {
	  float: left;
	  border: 0.5px solid #DAE1E1;
	  outline: none;
	  cursor: pointer;
	  padding: 14px 16px;
	  transition: 0.3s;
	  font-size: 17px;
	  width: 47%;
	  
	}
	.newBC {
		float:right;
	}
	
	/* Change background color of buttons on hover */
	.tab button:hover {
	  background-color: #1daded;
	}
	
	/* Create an active/current tablink class */
	.tab button.active {
	  background-color: #1daded;
	}
</style>
</head>
<body>
	<div id="message-detail" class="message-detail col-7">
        	<div class="sender col-4"><%=bc.getRecUser().getUsername() %></div>
        	<div class="msg-date col-3"><%=bc.getCaseDate() %></div>
        	<div class="msg-row mb-2">
        		<div class="msg-subject col-4"><%=bc.getSubject() %></div>
	        	<!-- <div class="newBC col-8">
					<button class="newBC btn btn-metrx-blue m-4" type="submit">Approve</button>
				</div> -->
				<div class="newBC col-8"><%=bc.getProgress()%> </div>
        	</div>
        	<hr style="clear: both;">
        	<p class="message"><%=bc.getMessage() %>
            </p>
            <hr>
            <div class="comments">
            	<h5 class="comments-title col-4">Comment</h5>
            	
            	<div class="comment col-12">
            		<div class="commentText"><span class="commentator"><%=bc.getRecUser().getUsername() %>: </span><%=bc.getComments()%></div>
            	</div> 
            	
            	
            </div>    
        </div>
</body>
</html>
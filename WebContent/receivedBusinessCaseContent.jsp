<jsp:include page="header.jsp"></jsp:include>

<%@ page import="java.util.List"
import="com.fdmgroup.model.BusinessCase"
import="com.fdmgroup.daoJpa.UserDAOImpl"
%>
<%
	BusinessCase bc = (BusinessCase) request.getAttribute("businessCase");
	UserDAOImpl udao = new UserDAOImpl();
%>

<style>
/* 	/* Style the tab */
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
	  
	} */
	.newBC {
		float:right;
	}
	
/* 	/* Change background color of buttons on hover */
	.tab button:hover {
	  background-color: #1daded;
	}
	
	/* Create an active/current tablink class */
	.tab button.active {
	  background-color: #1daded; */
	}
</style>

<div class="container pt-5 pb-5">
    <div class="row">
	    <div class="col-2"></div>
		<div id="message-detail" class="message-detail col-8">
	<!-- 		<div clas="row"> -->
		      	<div class="sender col-8"><%=udao.readById(bc.getSentUserId()).getUsername() %></div>
		       	<div class="msg-date col-4"><%=bc.getCaseDate() %></div>
	<!--         </div> -->
	        <div class="msg-row mb-2 row">
	        		<div class="msg-subject col-8 pl-5"><%=bc.getSubject() %></div>
	        		<div class="newBC col-4">
		        	<!-- <form action="businessCaseChangeProgress"> -->
		        	
						<button class="newBC btn btn-metrx-blue m-4" type="submit" value="<%=bc.getBusinessCaseId()%>" name="businessCaseId">Approve</button>
					
					<!-- </form> -->
					</div>
			</div>	
	        	<hr style="clear: both;">
	        	<p class="message"><%=bc.getMessage() %>
	            </p>
	            <hr>
	            <form action="AddCommentBusinessCase">
	        		<p><label>Add Comment: </label><input type="text" id="comment" class="text_field" name="comment" placeholder="comment"/></p>
	        		<%-- <button type="submit"  value="<%=bc.getBusinessCaseId()%>" name="businessCaseId" placeholder="add"/> --%>
	 				<button type="submit" class="btn-icon" aria-label="businessCaseId" name="businessCaseId" value="<%=bc.getBusinessCaseId()%>" >
					Add
					</button>
	        	</form>
	   			<div class="comment col-12">
	            		<div class="commentText"><span class="commentator"><%=bc.getRecUser().getUsername() %>: </span><%=bc.getComments()%></div>
	            	</div> 
	    </div>
	        ${Message}</br>
	        ${Message1}
	        
	        
	        
	 <div class="col-2"></div>        
	                <div id="message-detail" class="message-detail col-8">
        	<div class="sender col-4"><%=udao.readById(bc.getSentUserId()).getUsername() %></div>
        	<div class="msg-date col-3"><%=bc.getCaseDate() %></div>
        	<div class="msg-row mb-2">
        		<div class="msg-subject col-4">RBC Meeting</div>
	        	<div class="newBC col-8">
					<button class="newBC btn btn-metrx-blue m-4" type="submit">Approve</button>
				</div>
        	</div>
        	<hr style="clear: both;">
        	<p class="message">Hi Charlie, <br/><br/>
        		I have a meeting with RBC on monday. It is regarding the recruitment for Business Analysts from our latest training batch.<br/><br/>
                Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
            	<br/><br/>
            	Regards,<br/>
            	Sean
            </p>
            <hr>
            <div class="comments">
            	<h5 class="comments-title col-4">Comments</h5>
            	<input type="text" id="inputComment" class="inputComment col-9" onkeydown="if (event.keyCode == 13) addComment()">
				<button type="button" class="btn btn-dark col-2">Comment</button>
            	<div class="comment col-12">
            		<div class="commentText"><span class="commentator">Sean Batts: </span>I will send another message regarding that!</div>
            		<small class="dateComment">Dec 4, 2018</small>
            	</div> 
            	<div class="comment col-12">
            		<div class="commentText"><span class="commentator">Charlie Drewett: </span>When is the TD meeting?</div>
            		<small class="dateComment">Dec 4, 2018</small>
            	</div>
            	<div class="comment col-12">
            		<div class="commentText"><span class="commentator">Sean Batts: </span>Thank you Charlie</div>
            		<small class="dateComment">Dec 4, 2018</small>
            	</div>
            	<div class="comment col-12">
            		<div class="commentText"><span class="commentator">Charlie Drewett: </span>Yeah Approved!</div>
            		<small class="dateComment">Dec 4, 2018</small>
            	</div>
            </div>    
        </div>
	        
	        
	    <div class="col-2"></div>
 	</div>
 </div>
<jsp:include page="footer.jsp"></jsp:include>

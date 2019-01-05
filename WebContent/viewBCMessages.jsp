<jsp:include page="header.jsp"></jsp:include>
<%@ page import="java.util.List"
import="com.fdmgroup.model.BusinessCase"
import="java.util.ArrayList"
import="com.fdmgroup.daoJpa.UserDAOImpl"%>
<% 
	List<BusinessCase> sentBusinessCases = (ArrayList<BusinessCase>)request.getAttribute("sentBusinessCases");
	List<BusinessCase> receivedBusinessCases = (ArrayList<BusinessCase>)request.getAttribute("receivedBusinessCases");
	UserDAOImpl udao = new UserDAOImpl();
%>

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
<div class="container pt-5 pb-5">
    <div class="row">
    	<div class="tab col-4"> 
		  <button class="tablinks active"  onclick="openMessages(event, 'Received')">Received</button>
		  <button class="tablinks" onclick="openMessages(event, 'Sent')">Sent</button>
		</div>
		<div class="col-8">
			<form action="goToBusinessCase">
				<button class="newBC btn btn-metrx-blue ml-3 mr-5" type="submit">New Business Case</button>
			</form>
		</div>
    </div>
    <div class="row">
		<div id="Received" class="tabcontent col-4" style="max-height: 85vh;overflow:scroll;margin: 0;">
	        <div class="list-group" >
	       		 
	        	<%
	        		for(BusinessCase bc : receivedBusinessCases)
	        		{
	        	%>		
	        		<%-- <form action="viewDetailForBusinessCaseReceived">
	        			<button type="submit"  aria-label="businessCaseId" class="list-group-item list-group-item-action flex-column align-items-start" value="<%=bc.getBusinessCaseId()%>" name="businessCaseId">
	        			<div class="d-flex w-100 justify-content-between">
	                    <h5 class="mb-1"><%=udao.readById(bc.getSentUserId()).getUsername()%></h5>
	                	</div>
	        		
		        		<p class="mb-1 msg"><%=bc.getMessage() %>
		                </p>
		                <small><%=bc.getCaseDate() %></small>
		                </button>
		              </form> --%>
	        			<div class="list-group-item list-group-item-action flex-column align-items-start" value="<%=bc.getBusinessCaseId()%>" id="receievedBusinessCaseId<%=bc.getBusinessCaseId()%>">
		        			<div class="d-flex w-100 justify-content-between">
		                    	<h5 class="mb-1"><%=udao.readById(bc.getSentUserId()).getFirstName()%> <%=udao.readById(bc.getSentUserId()).getLastName()%></h5>
		                	</div>
		        		
			        		<p class="mb-1 msg"><%=bc.getMessage() %>
			                </p>
			                <small><%=bc.getCaseDate() %></small>
		                </div>
	        	<%
	        		} 
	        	%>
	        	
	        	
	        
	        
	        
	        
	        </div>
	    </div>
	    <div id="Sent" class="tabcontent col-4" style="display:none;max-height: 85vh;overflow:scroll;margin: 0;"> 
		   <div class="list-group" >
		   
		   
		   	<%
	        		for(BusinessCase bc : sentBusinessCases)
	        		{
	        	%>
	        		<!-- <form>  action="viewDetailForBusinessCaseSent" -->
	        		
	        			<div aria-label="businessCaseId" class="list-group-item list-group-item-action flex-column align-items-start" value="<%=bc.getBusinessCaseId()%>" name="businessCaseId" id="businessCaseId<%=bc.getBusinessCaseId()%>">
		        			<div class="d-flex w-100 justify-content-between">
		                    	<h5 class="mb-1"><%=bc.getRecUser().getFirstName()%> <%=bc.getRecUser().getLastName()%></h5>
		                	</div>
		        		
			        		<p class="mb-1 msg"><%=bc.getMessage() %>
			                </p>
			                <small><%=bc.getCaseDate() %></small>
		                </div>
	        		<!-- </form> -->
	        	<%
	        		} 
	        	%>
		   
	
	        </div>
		</div>
        <div id="message-detail" class="message-detail col-7">
        	<!-- <div class="sender col-4">Sean Batts</div>
        	<div class="msg-date col-3">December 4, 2018</div>
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
            </div>  -->   
        </div>
    </div>
</div>
<script src="//code.jquery.com/jquery-2.1.3.min.js"></script>
<script src="script/business-case.js"></script>
<script>
	function openMessages(evt, tabId) {
	  var i, tabcontent, tablinks;
	  tabcontent = document.getElementsByClassName("tabcontent");
	  for (i = 0; i < tabcontent.length; i++) {
	    tabcontent[i].style.display = "none";
	  }
	  tablinks = document.getElementsByClassName("tablinks");
	  for (i = 0; i < tablinks.length; i++) {
	    tablinks[i].className = tablinks[i].className.replace(" active", "");
	  }
	  document.getElementById(tabId).style.display = "block";
	  evt.currentTarget.className += " active";
	}
</script>
<jsp:include page="footer.jsp"></jsp:include>
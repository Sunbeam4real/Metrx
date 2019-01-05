<jsp:include page="header.jsp"></jsp:include>
<%@ page import="java.util.List"
import="com.fdmgroup.model.T_User"
import="java.util.ArrayList"%>
<% List<T_User> users = (ArrayList<T_User>)request.getAttribute("users"); %>
<div class="container-fluid pt-5 pb-5">
    <div class="row">
        <div class="col-3"></div>
        <div class="col-6">
            <h3>Business Case</h3>
            <form method="post" action="sendBusinessCase">
                <div class="form-group">
                    <label for="receiver">To</label> 
                    <select class="form-control" id="receiver" name="selectedRecord">
			<%
              for ( T_User u : users)
              {
              %>
              
              <option value="<%=u.getUserId()%>"> <%=u.getUsername()%></option>
              
              
              <%       
              }
              %>
                    </select>
                </div>
                <div  class="form-group">
                    <label for="subject">Subject</label> 
                    <input type="text" class="form-control" id="subject" placeholder="subject" name="subject">
                </div>
                <div class="form-group">
                    <label for="dateOfMessage">Date</label> 
                    <input type="date" class="form-control" id="dateOfMessage" name="dateOfMessage" pattern="[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])" required placeholder="yyyy-mm-dd"> 
                </div>
                <div  class="form-group">
                    <label for="message">Message</label> 
                    <textarea class="form-control" id="message"
                              placeholder="Message" name="message"></textarea>
                </div>
                <input type="submit" class="businessCaseBtn btn btn-metrx" value="Submit" />
            </form>
        </div>
        <div class="col-3"></div>
    </div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
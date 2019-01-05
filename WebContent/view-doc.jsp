<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp"></jsp:include>
<style>
	.user-names{
		border: none; background: none; font-weight: 700; color: currentColor;
	}
	
	.user-names:hover {
		cursor: pointer;
		color: #1daded;
	}
</style>
<div class="container pt-5 pb-5">
    <div class="row col-12">
    	<div class="col-12">
    		<div class="row ">
    			<div class="col text-right">
    			<form method="get" action="export_action">
    				<button type="submit" class="btn btn-metrx-blue pl-4 pr-4 m-3"><i class="fas fa-file-export mr-2"></i>Export</button>
    			</form>
    			</div>
    		</div>
        	<table class="table table-sm table-bordered">
			  <thead class="thead-dark">
			    <tr>
			      <th scope="col">Activity Type</th>
			      <th scope="col">Assigned</th>
			      <th scope="col">Jan</th>
			      <th scope="col">Feb</th>
			      <th scope="col">Mar</th>
			      <th scope="col">Apr</th>
			      <th scope="col">May</th>
			      <th scope="col">Jun</th>
			      <th scope="col">Jul</th>
			      <th scope="col">Aug</th>
			      <th scope="col">Sep</th>
			      <th scope="col">Oct</th>
			      <th scope="col">Nov</th>
			      <th scope="col">Dec</th>
			    </tr>
			  </thead>
			  <tbody id="doc-data">
			  	<tr>
			  	<th rowspan="${totalActivitiesSize + 1}" scope="row">Call</th>
			   	<c:forEach items="${totalActivities}" var="entry">
				  <tr class='clickable-row' data-href='url://'>
				  <th scope="row" class="user-activity" value="${entry.key.userId}">
				  	<form method="post" action="activities">
				  		<input type="hidden" value="${entry.key.userId}" name="userId">
				  		<input type="submit" class="user-names" value="${entry.key.firstName} ${entry.key.lastName}">	
				  	</form>
				  	
				  </th>
                  <c:forEach items="${entry.value}" var="item" varStatus="loop">
                      <td>${item[0]} </td>
                  </c:forEach>               
                </tr>
               </c:forEach>
               </tr>
               
			    
			    <tr >
			      <th rowspan="${totalActivitiesSize + 1}" scope="row">Deal</th>
			      
			     <c:forEach items="${totalActivities}" var="entry">
				  <tr class='clickable-row' data-href='url://'>
				  <th scope="row">
				  	<form method="post" action="activities">
				  		<input type="hidden" value="${entry.key.userId}" name="userId">
				  		<input type="submit" class="user-names" value="${entry.key.firstName} ${entry.key.lastName}">	
				  	</form>
				  </th>
                  <c:forEach items="${entry.value}" var="item" varStatus="loop">
                      <td>${item[1]} </td>
                  </c:forEach>               
                </tr>
               </c:forEach>
             	</tr>
             	
			    <tr >
			      <th rowspan="${totalActivitiesSize + 1}" scope="row">Email</th>
			      <c:forEach items="${totalActivities}" var="entry">
				  <tr class='clickable-row' data-href='url://'>
				  <th scope="row">
					<form method="post" action="activities">
				  		<input type="hidden" value="${entry.key.userId}" name="userId">
				  		<input type="submit" class="user-names" value="${entry.key.firstName} ${entry.key.lastName}">	
				  	</form>
				  </th>
                  <c:forEach items="${entry.value}" var="item" varStatus="loop">
                      <td>${item[2]} </td>
                  </c:forEach>               
                </tr>
               </c:forEach>
			 	</tr>
			 	
			  </tbody>
			</table>
        </div>
    </div>
    
</div>
<script src="//code.jquery.com/jquery-2.1.3.min.js"></script>
<script>
	 
	
	/* $("#input-date").datepicker({
	    onSelect: function(date, instance) {
	    	alert("hey");
	    	$.ajax
	        ({
	              type: "Post",
	              url: "www.example.com",
	              data: "date="+date,
	              success: function(result)
	              {
	                  
	              }
	         });  
	     }
	}); */
</script>

<jsp:include page="footer.jsp"></jsp:include>
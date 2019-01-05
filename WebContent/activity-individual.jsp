<jsp:include page="header.jsp"></jsp:include>
<div class="container pt-5 pb-5">
    <div class="row">
    	<form class="col-10 inputPeriod" action="">
    		<input type="hidden" value="${userId}" id="userId">
    		<h3 class="mb-4">Choose one of the below:</h3>
	    	<div class="form-row col-12">

			      <input type="text" class="form-control col-md-3 mr-3" id="input-date" placeholder="Date">
				  <select class="form-control col-md-3 mr-3" id="month">
				    <option value="1">January</option>
	    			<option value="2">February</option>
	    			<option value="3">March</option>
	    			<option value="4">April</option>
	    			<option value="5">May</option>
	    			<option value="6">June</option>
	    			<option value="7">July</option>
	    			<option value="8">August</option>
	    			<option value="9">September</option>
	    			<option value="10">October</option>
	    			<option value="11">November</option>
	    			<option value="12">December</option>
				  </select>
    			<select class="form-control col-md-3" id="yearForMonthly">
				    <option value="2019">2019</option>
				    <option value="2018" selected>2018</option>
	    			<option value="2017">2017</option>

				 </select>
	    	</div>
    	</form>
    </div>
    <div class="row col-12">
    	<div class="col-12 table-activities">
        	<table class="table">
			  <thead class="thead-dark">
			    <tr>
			      <th scope="col">Date</th>
			      <th scope="col">Call</th>
			      <th scope="col">Email</th>
			      <th scope="col">Meeting</th>
			      <th scope="col">Interview</th>
			      <th scope="col">Client Visit</th>
			    </tr>
			  </thead>
			  <tbody id="activity-data">
			  <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %> 
              <c:forEach items="${myMap}" var="entry">
                <tr>
                  <th scope="row"> ${entry.key}</th>
                  <c:forEach items="${entry.value}" var="item" varStatus="loop">
                      <td>${item} </td>
                  </c:forEach>               
                </tr>
               </c:forEach>
			  </tbody>
			</table>
        </div>
    </div>
    <h3 class="yearly">Yearly Performance</h3>
    <form class="col-10 inputPeriod" action="">
    		<div class="form-row col-12">
    			<select class="form-control col-md-3" id="year">
				    <option>2019</option>
	    			<option selected>2018</option>
	    			<option>2017</option>
				 </select>
	    	</div>
    	</form>
    <div class="row" style="height: 400px;" id="activity-barchart">
    	
    </div>
</div>
<script src="//code.jquery.com/jquery-2.1.3.min.js"></script>
<script src="http://code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
<script>

$(document).ready(function() {

	//on load bar chart
	var userId = $("#userId").val()
	loadChart(2018, userId);
	
	// on change of date
	$("#input-date").focus(function () {
		$(".ui-datepicker").css('display', 'block');
	})
	
	$("#input-date").datepicker({
	    onSelect: function(date, instance) {
	    	var month = date.split("/")[0]
	    	var day = date.split("/")[1]
	    	var year = date.split("/")[2]
	    	$.ajax({
              url: "activitiesPerDay",
              dataType: 'json',
  			  type: 'GET',
  			  data: {
  				month : month,
  				year : year,
  				userId : userId,
  				day : day
  			  }, 
              success: function(result){
            	  var output = "";
  				$.each(result, function(index, value) {
  					output += "<tr>" +
	                    	"<th scope='row'>" + index + "</th>" +
	                    	"<td>" + value[0] + "</td>" +            
	                    	"<td>" + value[1] + "</td>" +            
	                    	"<td>" + value[2] + "</td>" +            
	                    	"<td>" + value[3] + "</td>" +            
	                    	"<td>" + value[4] + "</td>" +            
	                  	"</tr>" 
	  				}) 
	  			  	
	               $("#activity-data").html(output); 
	          }
	         });  
	     }
	}); 

	// Table data - on change of month
	$("#month").change(function () {
		
		 $.ajax({
			url: 'activitiesPerMonth',
			dataType: 'json',
			type: 'GET',
			data: {
				month : $("#month").val(),
				year : $("#yearForMonthly").val(),
				userId : userId
			}, 
			success: function (result) {
				var output = "";
				$.each(result, function(index, value) {
					output += "<tr>" +
                  	"<th scope='row'>" + index + "</th>" +
                  	"<td>" + value[0] + "</td>" +            
                  	"<td>" + value[1] + "</td>" +            
                  	"<td>" + value[2] + "</td>" +            
                  	"<td>" + value[3] + "</td>" +            
                  	"<td>" + value[4] + "</td>" +            
                	"</tr>" 
				}) 
			  	
             $("#activity-data").html(output); 
				
			}
		}) 
	})
	
	// Table data - on change of year
	$("#yearForMonthly").change(function () {
		
		 $.ajax({
			url: 'activitiesPerMonth',
			dataType: 'json',
			type: 'GET',
			data: {
				month : $("#month").val(),
				year : $("#yearForMonthly").val(),
				userId : userId
			}, 
			success: function (result) {
				var output = "";
				$.each(result, function(index, value) {
					output += "<tr>" +
                  	"<th scope='row'>" + index + "</th>" +
                  	"<td>" + value[0] + "</td>" +            
                  	"<td>" + value[1] + "</td>" +            
                  	"<td>" + value[2] + "</td>" +            
                  	"<td>" + value[3] + "</td>" +            
                  	"<td>" + value[4] + "</td>" +            
                	"</tr>" 
				}) 
			  	
             $("#activity-data").html(output); 
				
			}
		}) 
	})
	
	// barchart change on year change
	$("#year").change(function() {
		loadChart($("#year").val(), userId)
	})
	
	function loadChart(year, userId) {
		google.charts.load('current', {
			'packages' : [ 'bar' ],
			callback : drawChart
		});
		
		function drawChart() {
			
			var months = [ 'January', 'February', 'March', 'April', 'May', 'June',
					'July', 'August', 'September', 'October', 'November', 'December' ];
		
			$.ajax({
				url : 'activitiesPerYear',
				type : 'GET',
				data: {
					year : year,
					userId : userId
				},
				dataType : 'json',
				success : function(results) {
					$.each(months, function(index, name) {
						$.each(results, function(month, value) {
							if (index == month) {
								value.unshift(name)
							}
						})
					})
		
					results.unshift([ 'Month', 'Call', 'Email', 'Meeting', 'Interview', 'Client Visit' ]);
					var dataMonth = google.visualization.arrayToDataTable(results);
					var options = {
						bars : 'vertical' // Required for Material Bar Charts.
					};
		
					var chart = new google.charts.Bar(document
							.querySelector('#activity-barchart'));
		
					chart.draw(dataMonth, google.charts.Bar.convertOptions(options));
				}
			})
		}
	}
});
</script>

<jsp:include page="footer.jsp"></jsp:include>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp"></jsp:include>
<div class="container pt-5 pb-5">

	<c:if test="${PrivList.contains('ViewSelf') == true || PrivList.contains('ViewAllActivities') == true }">
	<h1 style="text-align:center"> Monthly Progress </h1>
	<div class="row dash-row">
		<div class="col-4 text-center">
		<div class="box">
			<div class="chart" data-percent="${Call}">${Call}%</div>
		</div>
		<i class="fas fa-phone fa-lg"></i>
		<span class="lbl-dash">call</span>
		</div>
		<div class="col-4 text-center">
		<div class="box">
			<div class="chart" data-percent="${Deal}">${Deal}%</div>
		</div>
		<i class="far fa-handshake fa-lg"></i>
		<span class="lbl-dash">deal</span>
		</div>
		<div class="col-4 text-center">
		<div class="box">
			<div class="chart" data-percent="${Email}">${Email}%</div>
		</div>
		<i class="far fa-envelope fa-lg"></i>
		<span class="lbl-dash">email</span>
		</div>
	</div>
	<hr>
	
	<div id="dash-regular" class="row dash-row">
		<!-- Business Case -->
		<div class="col-4 text-center">
			<form method="post" action="goToBCMessages">
				<button type="submit" class="btn-icon" aria-label="businessCase" name="businessCase">
					<i class="fas fa-briefcase fa-5x dash-icon"></i><br>
					<span class="lbl-dash">Business case</span>
				</button>
			</form>
		</div>

		<!-- Activity Button -->
		<div class="col-4 text-center">
			<form method="post" action="activities">
				<input type="hidden" value="${loggedinuser.getUserId()}" name="userId">
				<button type="submit" class="btn-icon" aria-label="activity" name="activity">
					<i class="fas fa-chart-pie fa-5x dash-icon"></i><br>
					<span class="lbl-dash">Activity</span>
				</button>
			</form>
		</div>
		
		<!-- Achievement -->
		<div class="col-4 text-center">
			<form method="post" action="activities">
				<input type="hidden" value="${loggedinuser.getUserId()}" name="userId">
				<button type="submit" class="btn-icon" aria-label="achievement" name="achievement">
					<i class="fas fa-chart-line fa-5x dash-icon"></i><br>
						<span class="lbl-dash">Achievement</span>
				</button>
			</form>
		</div>
	</div>
	</c:if>
	<div id="dash-admin" class="row dash-row">

		<!-- Upload Document Button -->
		<c:if test="${PrivList.contains('UploadDoc') == true}">	
		<div class="col-4 text-center">
			<form method="get" action="upload">
				<button type="submit" class="btn-icon" aria-label="uploadDoc" name="uploadDoc">
					<i class="fas fa-file-upload fa-5x dash-icon"></i><br>
					<span class="lbl-dash">Upload Document</span>
				</button>
			</form>
		</div>
		</c:if>

		<!-- View Document Button -->
		<c:if test="${PrivList.contains('ViewDoc') == true}">	
		<div class="col-4 text-center">
			<form method="get" action="userviewcontroller">
				<button type="submit" class="btn-icon" aria-label="ViewDoc" name="ViewDoc">
					<i class="far fa-file-alt fa-5x dash-icon"></i><br>
					<span class="lbl-dash">View Document</span>
				</button>
			</form>
		</div>
		</c:if>
		
		<!-- Manage User -->
		<c:if test="${PrivList.contains('EditUserAdmin') == true}">	
		<div class="col-4 text-center">
			<form method="post" action="listAllUser">
				<button type="submit" class="btn-icon" aria-label="manageUser" name="manageUser">
					<i class="fas fa-users-cog fa-5x dash-icon"></i><br>
					<span class="lbl-dash">Manage User</span>
				</button>
			</form>
		</div>
		</c:if>
	</div>

	<div>
	</div>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script src="script/jquery.easypiechart.js"></script>
<script>
    $(function() {
        $('.chart').easyPieChart({
            size: 180,
            barColor: '#1daded',
            scaleColor: false,
            lineWidth: 15,
            trackColor: '#E5E4E2',
/*             lineCap: 'circle',
            animate: 2000 */
        });
    });
</script>

<jsp:include page="footer.jsp"></jsp:include>
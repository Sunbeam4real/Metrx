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
			<form method="post" action="upload_action" enctype="multipart/form-data">
				<fieldset class="pb-5">
					<legend>
						<i class="fas fa-file-upload mr-2"></i>Upload Document
					</legend>
					
					<div class="custom-file">
						<div class="row">
							<div class="col-9">
								<input type="file" class="custom-file-input"
									id="uploadedFile" required placeholder="Choose an Excel file" name="uploadedFilegi"> <label
									class="custom-file-label" for="uploadedFile"></label>
							</div>
							<div class="col-3 text-right">
								<button type="submit" class="btn btn-metrx-blue pl-4 pr-4">Upload</button>
							</div>
						</div>
						<div class="row">
						<div class="invalid-feedback">Please select excel file</div>
						</div>
					</div>	
<!-- Simple message feedback, need to polish this one -->	
<div id="mes">				
					<%
		 String file_name=(String)request.getParameter("filename");
		 if(file_name!=null){ 			
		 if (file_name.equals("error")){
			 out.println("This file type is not supported");
		 }
		 else if(file_name.equals("wrongB")){
			 out.println("Try another browser(IE or Edge)");
		 }
		 else{
			 out.println("File uploaded successfully");
			 }
		 }
		 %>
</div>		
<!-- end   -->			
				</fieldset>
			</form>
		</div>
		<div class="col-2"></div>
	</div>
</div>
<script>
	$('#uploadedFile').on('change', function() {
		//get the file name
		var fileName = $(this).val();
		//replace the "Choose a file" label
		$(this).next('.custom-file-label').html(fileName);
	})
</script>
<jsp:include page="footer.jsp"></jsp:include>
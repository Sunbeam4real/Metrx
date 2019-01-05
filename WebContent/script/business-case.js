$(document).ready(function() {
	
	$('div[id^="businessCaseId"]').click(function() {

		 $.ajax({
			url: 'viewDetailForBusinessCaseSent',
			type: 'POST',
			dataType: 'json',
			data: {
				businessCaseId : $(this).attr('value')
			},
			success: function (result) {
				var output = '';
				var commentDiv = '';
				$("#message-detail").empty();
				console.log(result);
				//console.log(result["message"]);
				
				if(result[0]["comments"]) {
					
					commentDiv = '<div class="comment col-12">' +
	            		'<div class="commentText"><span class="commentator">' + result[0]["recUser"]["firstName"]+ ' ' + result[0]["recUser"]["lastName"] + ': </span>' + result[0]["comments"] + '</div>' +
	            	'</div>' 
				}
				
				
				output += '<div class="sender col-4">' + result[0]["recUser"]["firstName"]+ ' ' + result[0]["recUser"]["lastName"] + '</div>' +
		        	'<div class="msg-row mb-2">' +
		        		'<div class="msg-subject col-4">' + result[0]["subject"] + '</div>' +
			        	'<div class="newBC col-8">' +
							'<button class="newBC btn btn-metrx-blue m-4" type="submit">Approve</button>' +
						'</div>' +
		        	'</div>' +
		        	'<hr style="clear: both;">' +
		        	'<p class="message">' +
		        	result[0]["message"] +
		        	'</p>' +
		            '<hr>' +
		            '<div class="comments">' +
		            	'<h5 class="comments-title col-4">Comments</h5>' +
		            	commentDiv
		            	
		            '</div>'
		            	
		            $("#message-detail").append(output); 
			}
		}) 
	})

	$('div[id^="receievedBusinessCaseId"]').click(function() {
		
		 $.ajax({
			url: 'viewDetailForBusinessCaseReceived',
			type: 'POST',
			dataType: 'json',
			data: {
				businessCaseId : $(this).attr('value')
			},
			success: function (result) {
				console.log(result[1]["firstName"])
				var output = '';
				var commentDiv = '';
				$("#message-detail").empty();
				console.log(result);
				//console.log(result["message"]);
				
				if(!result[0]["comments"]) {
					commentDiv = 
					'<form method="post" action="AddCommentBusinessCase">' +
	            	'<input type="text" id="comment" class="inputComment col-9" name="comment">' +
					'<button type="submit" id="comment-btn" class="btn btn-dark col-2" name="businessCaseId" value="' + result[0]["businessCaseId"] +'">Comment</button>' +
					'</form>'
				} else {
					commentDiv = '<div class="comment col-12">' +
	            		'<div class="commentText"><span class="commentator">' + result[0]["recUser"]["firstName"]+ ' ' + result[0]["recUser"]["lastName"] + ': </span>' + result[0]["comments"] + '</div>' +
	            	'</div>' 
				}
				
				
				output += '<div class="sender col-4">' + result[1]["firstName"]+ ' ' + result[1]["lastName"] + '</div>' +
		        	
		        	'<div class="msg-row mb-2">' +
		        		'<div class="msg-subject col-4">' + result[0]["subject"] + '</div>' +
			        	'<div class="newBC col-8">' +
							'<button class="newBC btn btn-metrx-blue m-4" type="submit">Approve</button>' +
						'</div>' +
		        	'</div>' +
		        	'<hr style="clear: both;">' +
		        	'<p class="message">' +
		        	result[0]["message"] +
		        	'</p>' +
		            '<hr>' +
		            '<div class="comments">' +
		            	'<h5 class="comments-title col-4">Comments</h5>' +
		            	commentDiv
		            	
		            '</div>'
		            	
		            $("#message-detail").append(output); 
			}
		}) 
	})
})
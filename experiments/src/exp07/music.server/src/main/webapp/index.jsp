<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="css/bootstrap-3.3.5-dist/css/bootstrap.min.css"
	rel="stylesheet">
<script type="text/javascript" src="js/jquery-2.1.4.js"></script>
<title>Music Sheet Upload by AJSAX with JSON</title>
</head>
<body>
	<div class="container-fluid">

		<div class="row">
			<div class="col-md-12">
				<h3 class="page-header" style="">Music Sheet</h3>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="alert alert-info" role="alert" id="messageDiv">
					<p>Hello, is there anybody in there? Just nod if you can hear
						me. Is there anyone at home? Come on, now, I hear you're feeling
						down. Well I can ease your pain. Get you on your feet again.</p>
					<p align="right">from Comfortably Numb by Pink Floyd</p>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="panel panel-default">
					<div class="panel-heading">Create a music sheet</div>
					<div class="panel-body">
						<form>
							<div class="form-group">
								<label for="name">Name</label> <input type="text"
									class="form-control" id="name" placeholder="name">
							</div>
							<div class="form-group">
								<label for="creatorId">Creator Id</label> <input type="text"
									class="form-control" id="creatorId" placeholder="creator id">
							</div>

							<div class="form-group">
								<label for="creator">Creator</label> <input type="text"
									class="form-control" id="creator" placeholder="creator">
							</div>
							<div class="form-group">
								<button type="button" class="btn btn-default" id="sender">Submit</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		$('#sender').click(function() {
			var name = document.getElementById('name').value;
			var creatorId = document.getElementById('creatorId').value;
			var creator = document.getElementById('creator').value;
			var musicSheet = {
				name : name,
				creatorId : creatorId,
				creator : creator
			};
			var url = "sheetUpload";
			$.post(url, JSON.stringify(musicSheet), function(data) {
				console.log(data);
				var json = JSON.parse(data);
				$("#messageDiv").attr("class", "alert alert-danger");
				$("#messageDiv").html(json.message);
			});

			/* $.ajax({
			type:'post',
			url:url,
			dataType:"json",			
			data:JSON.stringify(user),
			success: function (data) {
				var user=data.user;
				 $("#messageDiv").html(JSON.stringify(user));
			        alert(data.message);
			   },
			   error: function (data) {
			        alert(data.message);
			        $("#messageDiv").html("");
			   }
			});  */

		});
	</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="css/bootstrap-3.3.5-dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="css/bootstrap-3.3.5-dist/css/bootstrap-theme.css"
	rel="stylesheet">
<script type="text/javascript" src="js/jquery-2.1.4.js"></script>
<title>Music Sheet Upload by AJSAX with JSON</title>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<h3 class="page-header" style="">Music Sheet</h3>
			<div class="col-md-7">
				<div class="panel panel-primary">
					<div class="panel-heading">Parameters</div>
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
								<button type="submit" class="btn btn-default" id="sender">Submit</button>
							</div>
						</form>
					</div>
				</div>

				<div class="row">
					<div class="col-md-7">
						<div class="alert alert-warning" role="alert" id="messageDiv"></div>
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
					$("#messageDiv").html(data);
					var json = JSON.parse(data);
					alert(json.message);
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
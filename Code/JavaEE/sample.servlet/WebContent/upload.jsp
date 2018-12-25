<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" type="text/css"
	href="bootstrap/4.1.3/css/bootstrap.min.css" />
<style type="text/css">
body {
	font: "Microsoft YaHei";
}
</style>
<title>文件上传(基于Servlet 3.0)</title>
</head>

<body>
	<%-- 注意：文件上传时必须要设置表单form的 enctype="multipart/form-data" --%>
	<div class="container">
		<div class="row m-4 p-2">
			<div class="col-md-6">
				<div class="card bg-light mb-3">
					<div class="card-header">上传单个文件</div>
					<div class="card-body">
						<form action="${pageContext.request.contextPath}/upload"
							method="post" enctype="multipart/form-data">
							<div class="form-group">
								<input type="file" name="file" class="form-control-file">
							</div>

							<div class="form-group">
								<input class="btn btn-secondary btn-block" type="submit"
									value="上 传">
							</div>
						</form>
					</div>
				</div>
			</div>

			<div class="col-md-6">
				<div class="card bg-light border-info mb-3">
					<div class="card-header">上传多个文件</div>
					<div class="card-body">
						<form action="${pageContext.request.contextPath}/upload"
							method="post" enctype="multipart/form-data">
							<div class="form-group">
								<input type="file" name="file1" class="form-control-file">
							</div>
							<div class="form-group">
								<input type="file" name="file1" class="form-control-file">
							</div>
							<div class="form-group">
								<input class="btn btn-info btn-block" type="submit" value="上 传">
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
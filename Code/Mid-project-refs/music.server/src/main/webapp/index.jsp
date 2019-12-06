<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="css/bootstrap-3.3.5-dist/css/bootstrap.min.css"
	rel="stylesheet">
<script src="js/angular/1.6.3/angular.js"></script>
<script type="text/javascript" src="js/jquery-2.1.4.js"></script>
<title>Music Sheet Management</title>
</head>
<body ng-app="musicApp">
	<div class="container-fluid">

		<div class="row">
			<div class="col-md-12">
				<h3 class="page-header" style="">API Introduction</h3>
			</div>
		</div>

		<div class="row">
			<div class="col-md-10">
				<table class="table table-bordered table-condensed">
					<caption>请参考服务器提供的API，点击API超链接可测试接口有效性</caption>
					<thead>
						<tr>
							<th>API</th>
							<th>Description</th>
							<th>Type of response</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><a
								href="http://service.uspacex.com/music.server/queryMusicSheets?type=all">http://service.uspacex.com/music.server/queryMusicSheets?type=all</a></td>
							<td>查询返回所有音乐单</td>
							<td>JSON</td>
						</tr>
						<tr>
							<td><a
								href="http://service.uspacex.com/music.server/queryMusicSheets?type=top20">http://service.uspacex.com/music.server/queryMusicSheets?type=top20</a></td>
							<td>查询返回Top20所有音乐单</td>
							<td>JSON</td>
						</tr>
						<tr>
							<td><a
								href="http://service.uspacex.com/music.server/queryMusicSheets?type=top1">http://service.uspacex.com/music.server/queryMusicSheets?type=top1</a></td>
							<td>查询返回最新的1个音乐单（测试使用）</td>
							<td>JSON</td>
						</tr>
						<tr>
							<td><a
								href="http://service.uspacex.com/music.server/downloadMusic?md5=77008b41f4c692808ac7b414722269e0">http://service.uspacex.com/music.server/downloadMusic?md5={md5value
									of music}</a></td>
							<td>下载md5值所代表的音乐文件</td>
							<td>File stream</td>
						</tr>
						<tr>
							<td><a
								href="http://service.uspacex.com/music.server/music?md5=77008b41f4c692808ac7b414722269e0">http://service.uspacex.com/music.server/music?md5={md5value
									of music}</a></td>
							<td>在线流媒体播放md5值所代表的音乐文件</td>
							<td>Audio Stream</td>
						</tr>
						<tr>
							<td><a
								href="http://service.uspacex.com/music.server/downloadPicture?uuid=2a2fa72e16c640d6a1e23a5cce67e98d">http://service.uspacex.com/music.server/downloadPicture?uuid={uuid
									of music sheet}</a></td>
							<td>下载uuid值所代表的音乐单封面图像</td>
							<td>File stream</td>
						</tr>
					</tbody>
				</table>

			</div>
		</div>

		<div class="row">
			<div class="col-md-12">
				<h3 class="page-header" style="">Music Sheet List</h3>
			</div>
		</div>

		<div class="row" ng-controller="musicSheetListCtrl">
			<div class="col-md-10">
				<table class="table table-striped table-condensed">
					<caption>小宝贝们上传的歌单</caption>
					<thead>
						<tr>
							<th>NO.</th>
							<th>UUID</th>
							<th>名 称</th>
							<th>创建者ID</th>
							<th>创建者</th>
							<th>创建时间</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="sheet in musicSheetList">
							<td>{{$index+1}}</td>
							<td>{{sheet.uuid}}</td>
							<td>{{sheet.name}}</td>
							<td>{{sheet.creatorId}}</td>
							<td>{{sheet.creator}}</td>
							<td>{{sheet.dateCreated}}</td>
						</tr>
					</tbody>
				</table>

			</div>
		</div>

		<div class="row">
			<div class="col-md-12">
				<h3 class="page-header" style="">
					Create Music Sheet <span class="label label-danger">NOT WORK</span>
				</h3>
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
		var app = angular.module('musicApp', []);
		app.controller('musicSheetListCtrl', function($scope, $http) {

			$scope.musicSheetList = [ {
				uuid : "cddc055bfa33439a889cb611c1cb6db2",
				name : "Beyond Band",
				creatorId : "2011022",
				creator : "Wang Xiaodong",
				dateCreated : "2017-11-17 18:26:38",
			} ];

			$scope.queryMusicSheetList = function() {
				$http({
					method : 'GET',
					url : 'queryMusicSheets?type=top20',
				}).then(function(resp, status) {
					console.log(resp.data);
					$scope.musicSheetList = resp.data.musicSheetList;
				}, function(resp, status) {
					console.log(resp.data);
				});
			}();
		});
	</script>

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
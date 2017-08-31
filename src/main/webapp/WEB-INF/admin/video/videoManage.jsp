<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="zhr" uri="http://zhiyou100.com/common/" %>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>视频管理</title>
    <link href="${pageContext.request.contextPath }/static/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath }/static/js/jquery-1.12.4.min.js"></script>
    <script src="${pageContext.request.contextPath }/static/js/bootstrap.min.js"></script>
    <script type="text/javascript">
    		var time = 0;
    		function count(check){
    			if(check.checked){
    				time++;
    			}else if(time>0){
    				time--;
    			}
    			document.getElementById("delete").innerHTML=time;
    		}
    		
			function SelectAll() {
				time = 0;
			var ck = document.getElementById("checkbox")
 			var checkboxs=document.getElementsByName("checkbox")
 			for (var i=0;i<checkboxs.length;i++) {
  			var e=checkboxs[i];
  			e.checked=ck.checked;
  			count(e);
 				}
			}
			
			function deleteVideo(){
				if(!confirm("你确定要删除吗?")){
					window.event.returnValue = false;
				}
			}
			
			function deleteVideos(){
				if(!confirm("你确定要删除这"+time+"条吗?")){
					window.event.returnValue = false;
				}else{
					document.getElementById("videoform").action="${pageContext.request.contextPath }/admin/video/deleteVideos.action"
					document.getElementById("videoform").submit();
					return true;
				}
			}				
		</script>
</head>

<body>
	
	<div class="row">
		<div class="jumbotron col-md-offset-2 col-md-8" style="border-radius: 10px;">
			<div class="container">
				<h2>视频列表-视频管理</h2>
			</div>
		</div>

		<form class="form-inline" action="${pageContext.request.contextPath }/admin/video/videoManage.action" id="videoform">
			<a href="${pageContext.request.contextPath }/admin/video/addVideo.action" class="btn btn-primary col-md-offset-2"
				role="button">添加视频</a> 
			<a  onclick="return deleteVideos()" class="btn btn-primary" role="button">
					批量删除<span class="badge" id="delete">0</span>
			</a>
			<div class="form-group col-md-offset-2">
				<input type="text" class="form-control" id="exampleInputName2"
					name="videoTitle" placeholder="视频标题">
			</div>
			<div class="form-group">
				<div class="col-md-8">
					<select class="form-control" name="speakerName">
						<option value="">请选择主讲人</option>
						<c:forEach items="${sList }" var="s">
							<option ${speakerName eq s.speakerName ? "selected":""}>${s.speakerName }</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-8">
					<select class="form-control" name="courseName">
						<option value="">请选择课程</option>
						<c:forEach items="${cList }" var="c">
							<option ${courseName eq c.courseName ? "selected":""}>${c.courseName }</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<input type="submit" class="btn btn-primary"/>

			<div class="bs-example col-md-8" data-example-id="hoverable-table">
				<table class="table table-hover col-md-offset-3"
					style="text-align: center;">
					<thead>
						<tr>
							<td><label class="checkbox-inline"> <input
									type="checkbox" id="checkbox" onclick="SelectAll()">序号
							</label></td>
							<td>名称</td>
							<td>介绍</td>
							<td>讲师</td>
							<td>课程</td>
							<td>时长(秒)</td>
							<td>播放次数</td>
							<td>编辑</td>
							<td>删除</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.rows }" var="v" varStatus="status">
							<tr>
								<td><label class="checkbox-inline"> <input
										type="checkbox" name="checkbox" onclick="count(this)"
										value="${v.id }">${status.count }
								</label></td>
								<td>${v.videoTitle }</td>
								<td>${v.videoDescr }</td>
								<td>${v.speakerName }</td>
								<td>${v.courseName }</td>
								<td>${v.videoLength }</td>
								<td>${v.videoPlayTimes }</td>
								<td><a
									href="${pageContext.request.contextPath }/admin/video/updateVideo.action?id=${v.id}">
										<span class="glyphicon glyphicon-edit" aria-hidden="true"></span>
								</a></td>
								<td><a
									href="${pageContext.request.contextPath }/admin/video/deleteVideo.action?id=${v.id}">
										<span onclick="deleteVideo()" class="glyphicon glyphicon-trash" aria-hidden="true"></span>
								</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="col-md-offset-3">
					<zhr:page
						url="${pageContext.request.contextPath }/admin/video/videoManage.action">
					</zhr:page>
				</div>
			</div>
		</form>
	</div>
</body>
</html>
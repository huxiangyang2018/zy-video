<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
  </head>

  <body>  
    
    <div class="row">
  	<div class="col-md-8 col-md-offset-2">
	<div class="jumbotron" >
  			<h2>编辑视频信息-视频管理</h2>
	</div>
	</div>
	</div>
	

	<form class="form-horizontal" action="${pageContext.request.contextPath }/admin/video/updateVideo.action" method="post">
  		<div class="form-group">
    		<label for="inputEmail3" class="col-md-3 control-label">视频标题</label>
    		<div class="col-md-7">
      			<input type="text" class="form-control" name="videoTitle" value="${ video.videoTitle }" id="inputEmail3" placeholder="视频标题">
    		</div>
  		</div>
  
  		<div class="form-group">
  			<label for="inputEmail3" class="col-md-3 control-label">主讲人</label>
  			<div class="col-md-7">
				<select class="form-control" name="speakerId">
					<option value="">选择主讲人</option>
   				<c:forEach var="s" items="${ sList }">
							<option value="${ s.id }" ${ s.id==video.speakerId?"selected":"" }>${ s.speakerName }</option>
	  			</c:forEach>
				</select>
			</div>
		</div>
		
		<div class="form-group">
  			<label for="inputEmail3" class="col-md-3 control-label">所属课程</label>
  			<div class="col-md-7">
				<select class="form-control" name="courseId">
					<option value="">请选择课程</option>
   				<c:forEach var="c" items="${ cList }">
							<option value="${ c.id }" ${ c.id==video.courseId?"selected":"" }>${ c.courseName }</option>
	  			</c:forEach>
				</select>
			</div>
		</div>
		
		<div class="form-group">
    		<label for="inputEmail3" class="col-md-3 control-label">视频时长</label>
    		<div class="col-md-7">
      			<input type="text" class="form-control" name="videoLength" value="${ video.videoLength }" id="inputEmail3" placeholder="视频时长(秒)">
    		</div>
  		</div>
		
		<div class="form-group">
    		<label for="inputEmail3" class="col-md-3 control-label">封面图片</label>
    		<div class="col-md-7">
      			<input type="text" class="form-control" name="videoImageUrl" value="${ video.videoImageUrl }" id="inputEmail3" placeholder="封面图片">
    		</div>
  		</div>
		
		<div class="form-group">
    		<label for="inputEmail3" class="col-md-3 control-label">视频播放地址</label>
    		<div class="col-md-7">
      			<input type="text" class="form-control" name="videoUrl" value="${ video.videoUrl }" id="inputEmail3" placeholder="视频播放地址">
    		</div>
  		</div>
		
		<div class="form-group">
    		<label for="inputEmail3" class="col-md-3 control-label">视频简介</label>
    		<div class="col-md-7">
      			<textarea class="form-control" name="videoDescr" rows="3">${ video.videoDescr }</textarea>
    		</div>
  		</div>
  		<div class="actions">
  		<div class="col-md-offset-3 col-md-2">
  		<input type="hidden" name="id" value="${  video.id }">
  		<input type="hidden" name="videoPlayTimes" value="${  video.videoPlayTimes }">
		<button type="submit" class="btn btn-default">保存</button>
		<a class="btn btn-default" href="javascript:history.go(-1)">返回</a>
		</div>
		</div>
	</form>
	
	
    
  </body>
</html>
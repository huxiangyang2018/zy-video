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
    <title>主讲人管理</title>
    <link href="${pageContext.request.contextPath }/static/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath }/static/js/jquery-1.12.4.min.js"></script>
    <script src="${pageContext.request.contextPath }/static/js/bootstrap.min.js"></script>
  </head>

  <body>  
    
    <div class="row">
  	<div class="col-md-8 col-md-offset-2">
	<div class="jumbotron" >
  			<h2>编辑课程-课程管理</h2>
	</div>
	</div>
	</div>
	
	
	
	
	<form class="form-horizontal" action="${pageContext.request.contextPath }/course/updateCourse.action" method="post">
  		
  		<div class="form-group">
  			<label for="inputEmail3" class="col-md-3 control-label">所属学科</label>
  			<div class="col-md-7">
				<select class="form-control" name="subjectId">
					<option value="">请选择所属学科</option>
   				<c:forEach var="sb" items="${ sbList }">
							<option value="${ sb.id }" ${ sb.id==course.subjectId?"selected":"" }>${ sb.subjectName }</option>
	  			</c:forEach>
				</select>
			</div>
		</div>
		
		<div class="form-group">
    		<label for="inputEmail3" class="col-md-3 control-label">标题</label>
    		<div class="col-md-7">
      			<input type="text" class="form-control" name="courseName" value="${ course.courseName }" id="inputEmail3" placeholder="课程标题">
    		</div>
  		</div>
		
		<div class="form-group">
    		<label for="inputEmail3" class="col-md-3 control-label">简介</label>
    		<div class="col-md-7">
      			<textarea class="form-control" name="courseDescr" rows="3">${ course.courseDescr }</textarea>
    		</div>
  		</div>
  		<div class="actions">
  		<div class="col-md-offset-3 col-md-2">
  		<input type="hidden" name="id" value="${  course.id }">
		<button type="submit" class="btn btn-default">保存</button>
		<a class="btn btn-default" href="javascript:history.go(-1)">返回</a>
		</div>
		</div>
	</form>
	
	
    
  </body>
</html>
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
    <title>课程管理</title>
    <link href="${pageContext.request.contextPath }/static/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath }/static/js/jquery-1.12.4.min.js"></script>
    <script src="${pageContext.request.contextPath }/static/js/bootstrap.min.js"></script>
  </head>
  <body>  
    <div class="row">
  	    <div class="jumbotron col-md-offset-2 col-md-8" style="border-radius: 10px;">
			<div class="container">
  				<h2>课程列表-课程管理</h2>
		    </div>
		</div>
  	</div>
  <div class="nav-bar row">	
	<div class="col-md-2 col-md-offset-2">
		<div class="actions">
			<a class="btn btn-primary" href="${pageContext.request.contextPath }/course/addCourse.action">添加课程</a>
		</div>
	</div>
  </div>
    <br />
    <div class="col-md-8">
    <table class="table table-striped col-md-offset-3">
    	    <tr>
                <th>序号</th>
                <th>标题</th>
                <th>学科</th>
                <th>简介</th>
                <th>编辑</th>
                <th>删除</th>
            </tr>
            <c:forEach var="c" items="${ cList }" varStatus="status">
            <tr>
                <td>${ status.count }</td>
                <td>${ c.courseName }</td>
                <td>${ c.subjectName }</td>
                <td>${ c.courseDescr }</td>
                <td><a href="${pageContext.request.contextPath }/course/updateCourse.action?id=${c.id}">
					<span class="glyphicon glyphicon-edit" aria-hidden="true"></span>
				</a></td>
				<td><a href="${pageContext.request.contextPath }/course/deleteCourse.action?id=${c.id}">
					<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
				</a></td>
            </tr>
            </c:forEach>
    </table>
   </div>
    
  </body>
</html>
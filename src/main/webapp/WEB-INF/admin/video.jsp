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
		<nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">视频管理系统</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li><a target="pageBox"  href="<c:url value="/admin/video/videoManage.action" />">
                <i class=" fa fa-circle-thin"></i>视频管理</a>
            </li>
            <li><a target="pageBox"  href="<c:url value="/admin/speaker/speakerManage.action" />">
                <i class=" fa fa-circle-thin"></i>主讲人管理</a>
            </li>
            <li><a target="pageBox"  href="<c:url value="/admin/course/courseManage.action" />">
                <i class=" fa fa-circle-thin"></i>课程管理</a>
            </li>
            <li><a target="pageBox"  href="<c:url value="/admin/count/countManage.action" />">统计分析</a></li>
          </ul>
          
          <ul class="nav navbar-nav navbar-right">
        		<li><a href="#">${admin.loginName }<span class="glyphicon glyphicon-log-out" aria-hidden="true"></span></a></li>
      		</ul>
      		
        </div>
      </div>
    </nav>
    <div class="embed-responsive embed-responsive-4by3">
  <iframe name="pageBox" class="embed-responsive-item" src="<c:url value="/admin/video/videoManage.action" />"></iframe>
</div>
  </body>
</html>
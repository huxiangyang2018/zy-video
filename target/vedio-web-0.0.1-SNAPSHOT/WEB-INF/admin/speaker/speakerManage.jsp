<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="zhr" uri="http://zhiyou100.com/common/" %>
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
  	<div class="jumbotron col-md-offset-2 col-md-8"
			style="border-radius: 10px;">
			<div class="container">
  				<h2>主讲人列表-主讲人管理</h2>
		</div>
		</div>
		</div>
<div class="nav-bar row">
	
	<div class="col-md-2 col-md-offset-2">
		<div class="actions">
					<a class="btn btn-primary" href="${pageContext.request.contextPath }/speaker/addSpeaker.action">添加主讲人</a>
		</div>
		</div>
		<div class="col-md-offset-3 col-md-4">
		<div class="actions">
		<form class="form-inline">
			<div class="form-group">
                        名称<input type="text" class="form-control" name="speakerName" value="${speakerName}" placeholder="主讲人名称">
      </div>
			<div class="form-group">
                         职位<input type="text" class="form-control" name="speakerJob" value="${speakerJob}" placeholder="主讲人职位">
      </div>
			<button type="submit" class="btn btn-default">查寻</button>
		</form>
	  </div>
    </div>
    </div>
    <br />
 	<div class="col-md-8">
    <table class="table table-striped col-md-offset-3">
    	    <tr>
                <th>序号</th>
                <th>名称</th>
                <th>职位</th>
                <th>简介</th>
                <th>编辑</th>
                <th>删除</th>
            </tr>
            <c:forEach var="speaker" items="${page.rows }" varStatus="status">
            <tr>
                <td>${ status.count }</td>
                <td>${ speaker.speakerName }</td>
                <td>${ speaker.speakerJob }</td>
                <td>${ speaker.speakerDescr }</td>
                <td><a href="${pageContext.request.contextPath }/speaker/updateSpeaker.action?id=${speaker.id}">
					<span class="glyphicon glyphicon-edit" aria-hidden="true"></span>
				</a></td>
				<td><a href="${pageContext.request.contextPath }/speaker/deleteSpeaker.action?id=${speaker.id}">
					<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
				</a></td>
            </tr>
            </c:forEach>
    </table>
    <div class="col-md-offset-3">
    <zhr:page url="${pageContext.request.contextPath}/speaker/speakerManage.action"></zhr:page>
    </div>
    </div>
  </body>
</html>
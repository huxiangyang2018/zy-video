<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
  <base href="<%=basePath%>">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>登录界面</title>
    <link href="${pageContext.request.contextPath }/static/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath }/static/js/jquery-1.12.4.min.js"></script>
    <script src="${pageContext.request.contextPath }/static/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath }/static/js/jquery.validate.min.js"></script>
    <script src="${pageContext.request.contextPath }/static/js/messages_zh.min.js"></script>
    <script type="text/javascript">
    $(function(){
    	$("#checkForm").validate({
    		rules:{
    			loginName:{
    				required:true,
    				minlength:2
    			},
    			loginPwd:{
    				required:true
    			}
    		},
    		message:{
    			loginName:{
    				required:"用户名不能为空",
    				minlength:"长度不能小于2"
    			},
    			loginPwd:{
    				required:"密码不能为空"
    			}
    		}	
    	});	 	
    });
    </script>
  </head>
  	<style type="text/css">
	  	body{
				margin: 0 auto;
				text-align: center;
	 			margin-top: 350px;
	 			width: 309px;
	 		}
  	</style>
  <body>
  		<div class="login" >
            <img src="img/logo.png" alt="智游" class="img-rounded">
        </div>
  		<form action="<c:url value="/admin/index.action"/>" method="post" id="checkForm">
  			<div class="form-group">
    			<input type="text" class="form-control" name="loginName" id="loginName" placeholder="用户名" value="admin">
  			</div>
  			<div class="form-group">
    			<input type="password" class="form-control" name="loginPwd" id="loginPwd" placeholder="登录密码" value="admin">
  			</div>
  			<p style="color: red; font-size: 15px;">${errorMessage }</p>
  		    <button type="submit" class="btn btn-success form-control">登录</button>
		</form>
  </body>
</html>
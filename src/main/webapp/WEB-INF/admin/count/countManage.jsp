<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>统计列表</title>
<link href="${pageContext.request.contextPath }/static/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="${pageContext.request.contextPath }/static/js/jquery-1.12.4.min.js"></script>
<script src="${pageContext.request.contextPath }/static/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath }/static/js/echarts.min.js"></script>
</head>

<body>

	<div class="row">
		<div class="jumbotron col-md-offset-2 col-md-8"
			style="border-radius: 10px;">
			<div class="container">
				<h2>统计-统计分析</h2>
			</div>
		</div>

    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="main" style="width: 1200px;height:560px;" class="col-md-offset-2 col-md-8"></div>
    
    <script type="text/javascript">
    
         var myChart = echarts.init(document.getElementById('main'));
         // 显示标题，图例和空的坐标轴
         myChart.setOption({
             title: {
            	 left: 'center',
                 text: '课程平均播放次数'
                 
             },
             legend: {
            	 bottom: -5,
            	 
                 data:['播放次数']
             },
             color: ['#3398DB'],
             tooltip : {
                 trigger: 'axis',
                 axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                     type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                 }
             },
             grid: {
                 left: '3%',
                 right: '4%',
                 bottom: '3%',
                 containLabel: true
             },
             xAxis : [
                 {
                     type : 'category',
                     data : [],
                     axisTick: {
                         alignWithLabel: true
                     }
                 }
             ],
             yAxis : [
                 {
                     type : 'value'
                 }
             ],
             series : [
                 {
                     name:'播放次数',
                     type:'bar',
                     barWidth: '60%',
                     data:[]
                 }
             ]
         }); 
         
         myChart.showLoading();    //数据加载完之前先显示一段简单的loading动画
         
         var names=[];    //类别数组（实际用来盛放X轴坐标值）
         var nums=[];    //播放次数数组（实际用来盛放Y坐标值）
        
         $.ajax({ 
        	 type : "post",
         	async : true,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
         	url : "${pageContext.request.contextPath }/admin/count/countManag.action",    //请求发送到TestServlet处
         	data : {},
        	 dataType : "json",        //返回数据形式为json
         	success : function(result) {
            	 //请求成功时执行该函数内容，result即为服务器返回的json对象
             	if (result) {
                  	for(var i=0;i<result.length;i++){       
                      	 names.push(result[i].name);    //挨个取出类别并填入类别数组
                     	}
                    for(var i=0;i<result.length;i++){       
                    	 nums.push(result[i].id); //挨个取出销量并填入销量数组
                     	 }
                   	myChart.hideLoading();    //隐藏加载动画
                    myChart.setOption({        //加载数据图表
                        xAxis: {
                            data: names
                        },
                        yAxis: {},
                        series: [{
                            name: '播放次数',
                            data: nums
                        }]
                    });
                    
             }
         
        },
         error : function(errorMsg) {
             //请求失败时执行该函数
         alert("图表请求数据失败!");
         myChart.hideLoading();
         }
    })

         
    </script>
   </div> 
</body>
</html> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>服务器端推送支持</title>
</head>
<body>
	
	<script type="text/javascript" src="assets/js/jquery.min.js"></script>
	<script type="text/javascript">
		if(!!window.EventSource){//EventSource 对象只有比较新式的浏览器才有，它是 SSE 的客户端
			var source = new EventSource('push');
			s='';
			source.addEventListener('message',function(e){//添加 SSE 客户端监听，在此获取服务器端推送的消息
				s+e.data+"<br/>";
				$('#msgFromPush').html(s);
			});
			
			source.addEventListener('open',function(e){
				console.log('连接打开.');
			},false);
			
			source.addEventListener('error',function(e){
				if(e.readState == EventSource.CLOSED){
					console.log('连接关闭')
				}else{
					console.log(e.readState);
				}
			},false);
		}
		else{
			console.log("你的浏览器不支持 SSE");
		}
	</script>
</body>
</html>
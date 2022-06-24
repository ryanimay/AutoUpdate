<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Coin AutoUpdate</title>
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<style type="text/css">
td{
	border: 1px solid black;
	padding: 2px;	
}

.l{
	color:red;
}

.r{
	color:blue;
}
</style>
</head>
<body>
	<div>
		<table id='show'>
			
		</table>
	</div>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script type="text/javascript">
 		
		$(document).ready(function(){
			updateCoin();
			setTimeout('changeColor()',5000);
		})
		
		//開始時直接載入，之後60刷新一次
		function updateCoin(){
			$.ajax({
				url:"http://localhost:8080/AutoUpdate/getCoin",
				contentType:"application/x-www-form-urlencoded; charset=UTF-8",
				dataType:"json",
				method:"get",
				success:function(list){
					let re = '';
					re += '<tr>'
					re += '<td>ID</td>'
					re += '<td>Name</td>'
					re += '<td>USD</td>'
					re += '<td>LastUpdate</td>'
					re += '</tr>'
					$.each(list,function(index,coin){
						re += '<tr>'
						re += '<td class="l">'+coin.coinId+'</td>'
						re += '<td class="r">'+coin.coinName+'</td>'
						re += '<td class="l">'+coin.usd+'</td>'
						re += '<td class="r">'+coin.lastUpdated+'</td>'
						re += '</tr>'
					})
					$('#show').html(re);
					setTimeout('updateCoin()',10000);
				},
				error:function(err){
					console.log(err);
				}
			})
		}
 		
 		//單純實驗一下顏色
		function changeColor(){
			$('.l').css('color','blue')
			$('.r').css('color','red')
			setTimeout('changeColor()',10000);
		}
	
	</script>
</body>
</html>
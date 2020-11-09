<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		// 이벤트 등록 
		$("#makeJsonBtn").on("click", function(){
			console.log("makeJsonBtn click");

			$("#jsonString").html("");
			// 1. json 객체를 생성
			// 2. 문자열로 변경
			var a = {userid : $("#userid").val(), usernm : $("#usernm").val()};
			$("#jsonString").html(JSON.stringify(a));
		});

		$("#callAjax").on("click", function(){
			console.log("callAjax click");

			// makeJsonBtn 클릭이벤트 강제 발생
			$("#makeJsonBtn").trigger("click");
			
			$.ajax({url : "/ajax/json",
					data : JSON.stringify({userid : $("#userid").val(), usernm : $("#usernm").val()}),
					contentType : "application/json; charset=uth-8",
					method : "post",
					dataType= $("#dataType").val(), // 서버로 부터 받기 희망라는 데이터 타입
					success : function(data){
						$("#respJsonString").html("");

							if("#dataType").val() == "json")
								$("#respJsonString").html(JSON.stringify(data));
							else
								$("#respJsonString").html((new XMLSerializer()).serializeToString(data));
						$("#respJsonString").html(JSON.staringfiy(data));
					}
				});
		})
	});
</script>
</head>
<body>
	<div id="jsonString">
		전송 json : <div id="jsonString"></div>
		응답 json: <div id="respjsonString"></div>
		userid : <input type="text" id="userid" name="userid" value="brown" />
		usernm : <input type="text" id="usernm" name="usernm" value="브라운" />
		<select id="dataType">
			<option value="json">json</option>
			<option value="xml">xml</option> 
		</select>
		<button type="button" id="makeJsonBtn">json 문자열 생성</button><br>
		<button type="button" id="callAjax">ajax 전송</button><br>
	</div>
	
</body>
</html>
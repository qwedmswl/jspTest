<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<script>
	$(document).ready(function(){
		$("#zipcodeBtn").on("click", function(){
			new daum.Postcode({
				oncomplete: function(data) {
					console.log(data)
					// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
					// 예제를 참고하여 다양한 활용법을 확인해 보세요.
					$("#addr1").val(data.roadAddress);
					$("#zipcode").val(data.zonecode);
				}
			}).open();
		});
	
		$("#regBtn").on("click", function(){
			// client side - validation
			// server side - validation
			// validation 로직은 일단 생략

			$("#frm").submit();
		});
		
// 		initData();
		
	});
	function initData(){
		$("#userid").val("lej");
		$("#usernm").val("이은지");
		$("#alias").val("DY");
		$("#pass").val("pass1234");
		$("#addr1").val("대전 중구 중앙로 76");
		$("#addr2").val("영민빌딩 4층 404호");
		$("#zipcode").val("34904");
	}
</script>

	<form id="frm" class="form-horizontal" role="form"
		action="${cp }/memberRegist" method="Post"
		enctype="multipart/form-data">

		<div class="form-group">
			<label for="userNm" class="col-sm-2 control-label">사용자 사진</label>
			<div class="col-sm-10">
				<input type="file" name="profile" />
			</div>
		</div>

		<div class="form-group">
			<label for="userid" class="col-sm-2 control-label">사용자 아이디</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="userid" name="userid"
					placeholder="사용자 아이디" value="${param.userid }">
			</div>
		</div>

		<div class="form-group">
			<label for="userNm" class="col-sm-2 control-label">사용자 이름</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="usernm" name="usernm"
					placeholder="사용자 이름" value="${param.usernm }">
					<span style="color:red;"><form:errors path="memberVo.usernm"/></span>
			</div>
		</div>
		<div class="form-group">
			<label for="alias" class="col-sm-2 control-label">별명</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="alias" name="alias"
					placeholder="별명" value="${param.alias }">
			</div>
		</div>
		<div class="form-group">
			<label for="pass" class="col-sm-2 control-label">Password</label>
			<div class="col-sm-10">
				<input type="password" class="form-control" id="pass" name="pass"
					placeholder="사용자 비밀번호" value="${param.pass }">
			</div>
		</div>


		<div class="form-group">
			<label for="addr1" class="col-sm-2 control-label">주소</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="addr1" name="addr1"
						placeholder="사용자 주소" READONLY value="${param.addr1}">
				<button id="zipcodeBtn" type="button" class="btn btn-default">우편번호 찾기</button>
			</div>
		</div>
		<div class="form-group">
			<label for="addr2" class="col-sm-2 control-label">상세주소</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="addr2" name="addr2"
					placeholder="사용자 상세주소" value="${param.addr2 }">
			</div>
		</div>
		<div class="form-group">
			<label for="zipcode" class="col-sm-2 control-label">우편번호</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="zipcode"
					name="zipcode" placeholder="사용자 우편번호" READONLY value="${param.zipcode }">
			</div>
		</div>

		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button id="regBtn" type="button" class="btn btn-default">사용자 등록</button>
			</div>
		</div>
	</form>
</body>
</html>
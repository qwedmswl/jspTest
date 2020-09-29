<%@ page language="java" contentType="application/vnd.ms-excel; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 응답을 엑셀 파일로 만들기
	1. jsp : contentType 수정, header 정보를 통해 파일명을 설정
			주의점 xls만 가능, xlsx는 html코드를 인식하지 못함
			엑셀 파일 다운로드 기능을 빠르게 구현하고자 할때 적당 
	2. poi 라이브러리 : xls, xlsx 상관 없이 응답을 생성해 내는 것이 가능
				단점 : 위의 방식에 비해 손이 많이 간다
	3. itext 라이브러리...(스프링 최근 지원이 중단)
 --%>
<% response.setHeader("Content-Disposition", "attahment; filename=excel.xls"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr>
			<th>id</th>
			<th>name</th>
		</tr>
		<tr>
			<th>brwon</th>
			<th>브라운</th>
		</tr>
		<tr>
			<th>cony</th>
			<th>코니</th>
		</tr>
		<tr>
			<th>sally</th>
			<th>샐리</th>
		</tr>
	</table>
</body>
</html>
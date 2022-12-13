<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix ="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>공지사항 등록</h3>
<!--연결될화면 insert  -->
<form method='post' action='insert.no'>
<table>
 <tr><th>사번</th>
 	<td><input type='text' name='writer'></td>	 
 </tr>
  <tr><th>글제목</th>
 	<td><input type='text' name='title'></td>	 
 </tr>
 
  <tr><th>내용입력</th>
 	<td><input type='text' name='content'></td>	 
 </tr>
 
</table>
</form>
<div class = "btnSet">
	<a class ='btn-fill' onclick="$('form').submit()">저장</a>
	<a class ='btn-empty' href='list.no'>취소</a>
</div>
</body>

</html>
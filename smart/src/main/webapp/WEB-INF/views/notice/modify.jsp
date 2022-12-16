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
<h3>공지글 수정</h3>
<!--제목 클릭시 정보수정화면 요청 -->

<form method='post' action='update.no'>
<table>
 <tr><th>사번</th>
 	<td><input type='text' name='writer' value='${vo.writer }'></td>	 
 </tr>
  <tr><th>글제목</th>
 	<td><input type='text' name='title' value='${vo.title }'></td>	 
 </tr>
 
  <tr><th>내용</th>
 	<td><input type='text' name='content' value='${vo.content }'></td>	 
 </tr>
 
</table>
</form>
<div class = "btnSet">
	<a class ='btn-fill' onclick="$('form').submit()">저장</a>
	<a class ='btn-empty' href='info.no?id=${vo.id}'>취소</a>
</div>
</body>
</html>
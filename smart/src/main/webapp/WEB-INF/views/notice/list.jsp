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
<h3>공지사항</h3>

<div id='list-top'>
<ul>
 	<li><a class='btn-fill' href='new.no'>새 공지사항</a></li>
</ul>
</div>
<div class ='tb=wrap w-px1000'>
<table class = 'tb-list'>
<colgroup>
	<col width ='50px'>
	<col width ='150px'>
	<col>
	<col width = '100px'>
	<col width ='120px'>
</colgroup>
	<tr>
		 <th>사번</th>
		 <th>제목</th>
		 <th>내용</th>
		 <th>작성자</th>
		 <th>작성일</th>
		 <th>조회수</th>	 
	</tr>
	<c:forEach items ='${list }' var ='to'>
	<tr>
		<td>${to.id}</td>
		<td>${to.title}</td>
		<td><a href = 'info.no?content=${to.id}'>${to.content}</a></td>
		<td>${to.writer}</td>
		<td>${to.writedate}</td>
		<td>${to.readcnt}</td>
		
	</tr>
	</c:forEach>

</table>
</div>
</body>
</html>
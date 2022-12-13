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
<table>
	<tr>
		<th>사번</th>
		<td>${to.id}</td>
	</tr>
	<tr>
		<th>제목</th>
		<td>${to.title}</td>
	</tr>
	<tr>
		<th>내용</th>
		<td>${to.content}</td>
	</tr>
	<tr>
		<th>작성자</th>
		<td>${to.writer}</td>
	</tr>
	<tr>
		<th>작성일</th>
		<td>${to.writedate}</td>
	</tr>
	<tr>
		<th>조회수</th>
		<td>${to.readcnt}</td>
	</tr>
	
</table>
<div class = 'btnSet'>
	<a class ='btn-fill' href ='modify.no?id=${vo.id }'>정보수정</a>
	<a class ='btn-fill' onclick="fn_delete()">정보삭제</a>
<!-- 	<a class ='btn-fill' remove>정보삭제</a> -->
	<a class ='btn-fill' href ='list.no'>목록창</a>
</div>

<script>
	function fn_delete(){
		if(confirm ('[${to.name}] 정말 삭제?')){
			location ='delete.no?id=${vo.id}';
			
		}
	}
	


</script>
</body>
</html>
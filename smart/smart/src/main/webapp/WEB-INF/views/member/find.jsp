<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class='center'>
	<a href='<c:url value="/"/>'><img src='img/hanul.logo.png'></a>
	<div class='box'>
	<form method='post' action='reset'>
	<ul>
		<li><input type='text' name='userid' class='chk'  placeholder="아이디"></li>	
		<li><input type='text' name='email' class='chk' placeholder="이메일"></li>
		<li><input type='reset' value='다시입력'></li>
		<li><input type='button' value='비밀번호 재발급' class='password'></li>	
	</ul>
	</form>
	</div>
</div>
<script>

$('.password').click(function(){
	alert('alert');
	if(emptyCheck())$('form').submit();
});

</script>
</body>
</html>

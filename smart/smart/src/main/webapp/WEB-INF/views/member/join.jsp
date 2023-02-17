<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix ="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
<style>
table td{text-align: left; padding: 5px 0 5px 10px;}
p {width: 600px; text-align: right; margin: 0 auto; color: #ff0000;}
form span{
	color: #ff0000; margin-right: 5px;
}
[name =addrress]{margin-top: 3px; }
.ui-datepicker td{height: inherit;}

.font {font-size: 20px;}
.font-r {font-size: 20px; color: #ff0000;}
.font-g {font-size: 20px; color: #008000;}
.font-b {font-size: 20px; color: #3367d6;}
.font-c {font-size: 20px; color: #333;}

#delete, #attach-file , #delete-file {display: none;}
.date + #delete {position: relative; right: 40px;}
</style>
</head>
<body>
<h3>회원가입</h3>


<p>*은 필수 입력사항입니다</p>
<form method='post' action='join' enctype='multipart/form-data'>
<table class ='w-px600'>
	<tr> <th class ='w-px140'><span>*</span>성명</th>
	<td><input type='text' name='name' autofocus></td>
	</tr>
	<tr>
		<th><span>*</span>아이디 </th>
		<td>
		<input type='text' name ='userid' class ='chk'>
		<a class ='btn-fill' id='btn-userid'>아이디 중복확인</a>
		<div class ='valid'>아이디를 입력하세요(영문소문자, 숫자만)
		</td>
		</div>
	</tr>
	<tr>
		<th><span>*</span>비밀번호 </th>
		<td>
		<input type='password' name ='userpw' class='chk'>
		<div class ='valid'>비밀번호 입력하세요(영문소문자, 숫자만)</div>		
		</td>
	</tr>
	<tr>
		<th><span>*</span>비밀번호 확인 </th>
		<td>
			<input type='password' name ='userpw_ck' class='chk'>
			<div class ='valid'>비밀번호 입력하세요(영문소문자, 숫자만)</div>
		</td>
	</tr>
	<tr>
		<th>프로필이미지 </th>
		<td><div class ='align'>
		<label>
		<input type='file' name ='profile_img' accept="image/*" id='attach-file'>
		<a><i class="fa-regular fa-id-badge"></i></a>
		</label>
		<span id ='preview'></span>
		<a id ='delete-file'><i class="fa-solid fa-trash-can"></i></a>
		</div>
		</td>
	</tr>
	<tr>
		<th><span>*</span>성별</th>
		<td>
		<label><input type='radio' name ='gender'  value ='남' >남</label>
		<label><input type='radio' name ='gender'  value ='여' checked>여</label>
		</td>
	</tr>
		<tr>
		<th>이메일 </th>
		<td>
		<input type='text' name='email' class='chk'>
		<div class ='valid'>비밀번호 입력하세요(영문소문자, 숫자만)
		</td>		
	</tr>
		<tr>
		<th>생년월일 </th>
		<td><input type ='text' name='birth' class='date' readonly>
			<a id='delete'><i class="fa-sharp fa-solid fa-trash"></i></a>
		</td>
	</tr>
		<tr>
		<th>전화번호 </th>
		<td><input type ='text' name='phone' maxlength='13'></td>
	
	</tr>
	<tr>
		<th>주소 </th>
		<td>
			<a class ='btn-fill' id='post'>우편번호 찾기</a>
			<input type ='text' name='post'  class ='w-px60' readonly>
			<input type ='text' name='address' class='full' readonly>
			<input type ='text' name='address'class='full' >		
		</td>	
	</tr>
	
</table>

</form>
<div class='btnSet'>
	<a class='btn-fill join'>회원가입</a>
	<a class='btn-empty' href='<c:url value="/"/>'>취소</a>
</div>

<script src ='js/member.js?<%=new java.util.Date() %>'></script>
<!-- 생년월일  https://jqueryui.com/datepicker/찾아서 넣음  -->
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>


$('.join').click(function(){
	if( $.trim($('[name=name]').val())=='' ){
		alert('성명을 입력하세요');
		$('[name=name]').focus();
		$('[name=name]').val('');
		return;
	}
	//유효성확인
	//중복확인 했고 이미사용중인 경우 가입불가
	//중복확인하지 않은경우 가입불가
	var _userid = $('[name=userid]');
	if( _userid.hasClass('chked') ){
		if( _userid.siblings('div').hasClass('invalid') ){
			alert('회원가입 불가!\n' + member.userid.unUsable.desc);
			_userid.focus();
			return;
		}
		
	}else{
		//유효하지 않게 입력해서 회원가입불가
		if( tagIsInvalid( _userid ) ) return;
		else{
			//중복확인하지 않아서 회원가입불가			
			alert('회원가입 불가!\n' + member.userid.valid.desc);
			_userid.focus();
			return;
		}
	}
		
	if( tagIsInvalid( $('[name=userpw]') ) ) return;
	if( tagIsInvalid( $('[name=userpw_ck]') ) ) return;
	if( tagIsInvalid( $('[name=email]') ) ) return;
	$('form').submit();
});

//유효성확인
function tagIsInvalid( tag ){
	var status = member.tag_status( tag );
	if( status.code=='invalid' ){
		alert('회원가입 불가!\n' + status.desc);
		tag.focus();
		return true;
	}else
		return false;
}

//아이디 중복확인
$('#btn-userid').click(function(){
	idcheck();
});





function idcheck(){
		var $userid = $('[name=userid]');
		//이미 중복확인 했다면 재확인 불필요
		if ($userid.hasClass('chked')) return;
		
		
		var status = member.tag_status($userid);
		if(status.code == 'invalid'){
			alert('아이디 중복화인 불필요!\n' + status.desc);
			$userid.focus();
		}else{
			$.ajax({
				url:'idcheck',
				data: {id: $userid.val()},
				success: function(response){
					//false 아이디 없음,  true 아이디 존재
					status = response ? member.userid.unUsable : member.userid.usable;
					$userid.siblings('div').text(status.desc).removeClass().addClass(status.code);
					
					//중복확인완료 지정
					$userid.addClass('chked');
		
					
				}, error: function(req, text){
					alert(text + ':' + req.status);
				}		
	
				
			});
		}
	}


$('.chk').keyup(function(e){
	//keyCode == 13 은 enter key 앤터 중복확인
	if( $(this).attr('name')== 'userid' && e.keyCode == 13){
		idcheck();
	}else {
		$(this).removeClass('chked');
	}
	var status = member.tag_status($(this));
	$(this).siblings('div').text(status.desc).removeClass().addClass(status.code);
});

//날짜 변경시 삭제 버튼 나오게선택시 보이게
$('.date').change(function(){
	$(this).next().css('display', 'inline');
});

//날짜 삭제 버튼클릭시 날짜 없애고, 날짜 삭제 버튼도 안나오게
$('#delete').click(function(){
	$(this).css('display', 'none').val('');
})


//년도표시 범위지정: 생년월일 기준을 특정날짜까지만 선택가능하도록 제한 
var today = new Date();
var endDay = new Date( today.getFullYear()-13, today.getMonth(), today.getDate()-1 );
//50년 전부터 지정 13년 전까지마 지정
var range = today.getFullYear()-50 + ':' + endDay.getFullYear();
$('.date').datepicker({
	yearRange: range,
	maxDate: endDay,
});
	
		
$('#post').click(function(){
	//다음 우편번호찾기 api로 우편번호와 기본주소를 조회해온다.
	new daum.Postcode({
		oncomplete: function(data) {
			console.log( data )
			//지번인지 아닌지
			$('[name=post]').val( data.zonecode );
			var address = data.userSelectedType == 'R' ? data.roadAddress : data.jibunAddress;
			//건물주소가 있으면 
			if( data.buildingName != '' ) address += ' ('+data.buildingName+')';
			$('[name=address]').eq(0).val( address );
		}
    }).open();
});
</script>
</body>
</html>

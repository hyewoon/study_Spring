<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
table td { text-align: left }
#comment-regist, #comment-list {
	width: 600px; margin: 0 auto; text-align: left
}
#comment-regist div { display: flex; justify-content: space-between;}
#comment { height: 80px;  margin-top: 5px }
#comment-list span{float:right; }
.modify{display: none }
</style>
</head>
<body>
<h3>방명록 안내</h3>
<table class='w-px1200'>
<colgroup>
	<col width='140px'>
	<col>
	<col width='160px'>
	<col width='160px'>
	<col width='100px'>
	<col width='100px'>
</colgroup>
<tr><th>제목</th>
	<td colspan='5'>${vo.title}</td>
</tr>
<tr><th>작성자</th>
	<td>${vo.name}</td>
	<th>작성일자</th>
	<td>${vo.writedate}</td>
	<th>조회수</th>
	<td>${vo.readcnt}</td>
</tr>
<tr><th>내용</th>
	<td colspan='5'>${fn: replace(vo.content, crlf, '<br>')}</td>
</tr>
<tr><th>첨부파일</th>
	<td colspan='5'>
	<c:forEach items='${vo.fileList}' var='file'>
	<div class='align'>
		<span>${file.filename}
			<a class='download' data-file='${file.id}'><i class="font-b fa-solid fa-file-arrow-down"></i></a>
		</span>
		<span class='preview'></span>
	</div>	
	</c:forEach>
	
	</td>
</tr>
</table>

<div class='btnSet'>
<a class='btn-fill list'>목록으로</a>
<!-- 작성자가 로그인한 경우만 수정/삭제 가능 -->
<c:if test='${loginInfo.userid eq vo.writer}'>
<a class='btn-fill modify'>정보수정</a>
<a class='btn-fill remove'>정보삭제</a>
</c:if>
</div>

<div id='comment-regist'>
	<div>
		<span>댓글작성</span>
		<a class='btn-fill-s btn-regist'>댓글등록</a>
	</div>
	<textarea id='comment' class='full'></textarea>
</div>
<div id='comment-list'></div>

<form method='post'>
<input type='hidden' name='id' value='${vo.id}'>
<input type='hidden' name='url'>
<input type='hidden' name='file'>
<input type='hidden' name='curPage' value='${page.curPage}'>
<input type='hidden' name='search' value='${page.search}'>
<input type='hidden' name='keyword' value='${page.keyword}'>
<input type='hidden' name='viewType' value='${page.viewType}'>
<input type='hidden' name='pageList' value='${page.pageList}'>
</form>
<div id='popup-background'></div>
<div id='popup' class='center'></div>

<script>
$('.btn-regist').on('click', function(){
	if( ${empty loginInfo} ){
		alert('댓글을 등록하려면 로그인하세요')
	}else if( $.trim($('#comment').val())=='' ){
		alert('댓글을 입력하세요')
		$('#comment').val('');
		$('#comment').focus();
	}else{
		$.ajax({
			url: 'board/comment/insert',
			data: { content:$('#comment').val(), board_id:${vo.id}, writer:'${loginInfo.userid}' },
			success: function( response ){
				if( response ){
					alert('댓글이 등록되었습니다');
					$('#comment').val('');
					comment_list();
				}else
					alert('댓글등록 실패ㅠㅠ');
			},error: function(req, text){
				alert(text+':'+req.status)
			}			
		});
	}
});

comment_list();

function comment_list(){
	$.ajax({
		url: 'board/comment/list/${vo.id}',
		success: function( response ){
			$('#comment-list').html( response );
		},error: function(req,text){
			alert(text+':'+req.status);
		}
	});
}


$('.modify').on('click', function(){
	$('form').attr('action', 'modify.bo').submit();
});

$('.remove').on('click', function(){
	if( confirm('정말 삭제?') ) {		
		$('form').attr('action','remove.bo').submit();
	}
});

<c:forEach items='${vo.fileList}' var='f' varStatus='state'>
if( isImage( '${f.filename}' ) ){
	$('.preview').eq( ${state.index} ).html( '<img src="${f.filepath}">' );
}
</c:forEach>


$('.download').on('click', function(){
	$('[name=url]').val( $(location).attr('href'));
	$('[name=file]').val( $(this).data('file') );
	$('form').attr('action', 'download.bo').submit();	
});

$('#popup-background').on('click', function(){
	$('#popup').empty();
	$('#popup, #popup-background').css('display', 'none');
});

//이미지 클릭시 크게 보여지게
$('.preview img').on('click', function(){
	$('#popup, #popup-background').css('display', 'block');
	$('#popup').append( $(this).clone() );
});

$('.list').on('click', function(){
	$('form').attr('action', 'list.bo');
	$('form').submit();
});
</script>
</body>
</html>



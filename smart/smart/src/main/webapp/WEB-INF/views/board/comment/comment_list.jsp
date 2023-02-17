<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 

<c:forEach items='${list}' var='vo' varStatus="state">
<!-- 첫째줄도 밑줄표현 -->
${state.first ? '<hr>' : '' }
<div data-id='${vo.id}'>${vo.name} [ ${vo.writedate} ]
<!--댓글 쓴 사람이 로그인할때만  버튼 나오도록  -->
<c:if test='${loginInfo.userid eq vo.writer}'>
<span>
	<a class='btn-fill s btn-modify-save'>수정</a>
	<a class='btn-fill s btn-delete-cancel'>삭제</a>
</span>
</c:if>
<!--엔터적용, 줄바꿈 적용  -->
	<div class='view'>${fn: replace(   fn: replace(vo.content, lf, '<br>')   , crlf, '<br>')}</div>
	<textarea class='modify'></textarea>
	
</div>
<hr>
</c:forEach>

<script>

//삭제/취소버튼 클릭시
$('.btn-delete-cancel').on('click', function(){
	var div = $(this).closest('div');
	if( $(this).text()=='취소' ){
		modifyStatus( div, false );
	}else{
		if(confirm('정말 삭제?')){
			$.ajax({
				url: 'board/comment/delete/' + div.data('id'),
				success: function(){
					comment_list();
				}
		
			});
		}
		
	}
});
 
		//수정/저장버튼 클릭시
		$('.btn-modify-save').on('click', function(){
			var div = $(this).closest('div');
			
			if( $(this).text()=='수정' ){
				//원글의 내용이 textarea에 보이게
				div.children('.modify').val( div.children('.view').html().replace(/<br>/g, '\n') );
				modifyStatus( div, true );
			}else{
				var comment = 
				JSON.stringify( { id: div.data('id'), content: div.children('.modify').val() } );
				
				$.ajax({
					url: 'board/comment/update',
//		 			data: { id: div.data('id'), content: div.children('.modify').val() },
					data: comment,
					type: 'post',
					contentType: 'application/json',
					success: function( response ){
						alert('댓글 변경 ' + response);
						comment_list();
					},error: function(req,text){
						alert(text+':'+req.status);
					}
				});
			}
		});
// 	//원글의 내용이 textarea에 보이도록 
	
// });
	
function modifyStatus(div, is){
	//수정 상태인 경우 : 저장 취소 버튼이 보이게, 원글은 안보이게 , 수정글 보이게 
	//수정 상태 아닌 경우 : 수정 삭제 버튼이 보이게, 원글보이게, 수정 글 안보이게
	 div.find('.btn-modify-save').text(is ? '저장':'수정');
	 div.find('.btn-delete-cancel').text(is ? '취소':'삭제');
	 div.children('.view').css('display', is ? 'none':'block');
	 div.children('.modify').css('display', is ? 'block':'none');
	
}

</script>

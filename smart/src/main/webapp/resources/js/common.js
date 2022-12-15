/**
 * 
 */
//필수입력항목 입력여부확인 함수 
//공백 일 경우 (trim)
//입력하지 않을 경우 입력하세요 뜨게 하기 위해서
function emptyCheck(){
	var ok = true;
	$('.chk').each(function(){
		if( $.trim($(this).val())=='' ){
			var title = $(this).attr('placeholder');
			alert(title + ' 입력하세요');
			$(this).val('');
			$(this).focus();
			ok = false;
			return ok;
		}		
	});
	return ok;
} 
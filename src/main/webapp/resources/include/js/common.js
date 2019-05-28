/* 함수명: chkSubmit(유효성 체크 대상, 메시지 내용)
 * 출력영역: alert
 * 예시: if(!chkSubmit($("#keyword"), "검색어를")) return;
 */
function chkSubmit(item, msg){
	if(item.val().replace(/\s/g,"")==""){
		alert(msg+ " 입력해 주세요.");
		item.val("");
		item.focus();
		return false;
	} else {
		return true;
	}
}

/* 함수명: checkForm(유효성 체크 대상, 메시지 내용)
 * 출력영역: placeholder 속성이용
 * 예시: if(!checkForm("#keyword", "검색어를")) return;
 */
function checkForm(item, msg){
	var message ="";
	if($(item).val().replace(/\s/g, "")==""){
		message = msg + "입력해 주세요.";
		$(item).attr("placeholder", message);
		return false;
	}else{
		return true;
	}
}


/* 함수명: chkData(유효성 체크 대상, 메시지 내용)
 * 출력영역: alert
 * 예시: if(!chkData("#keyword", "검색어를")) return;
 */
function chkData(item, msg){
	if($(item).val().replace(/\s/g,"")==""){
		alert(msg + " 입력해 주세요.");
		$(item).val("");
		$(item).focus();
		return false;
	} else {
		return true;
	}
}

/*formCheck(유효성 체크 대상, 출력 영역, 메시지 내용)*/
function formCheck(main, item, msg){
	if(main.val().replace(/\s/g,"")==""){
		item.html(msg+" 입력해주세요.").css("color", "blue");
		main.val("");
		return false;
	} else {
		return true;
	}
}

/* 함수명: chkFile(파일명)
 * 설명: 이미지 파일 여부를 확인하기 위해 확장자 체크 함수
 */
function chkFile(item){
	var ext = item.val().split('.').pop().toLowerCase();
	if(jQuery.inArray(ext, ['gif', 'png', 'jpg', 'jpeg']) == -1){
		alert('gif, png, jpg, jpeg 파일만 업로드 할 수 있습니다.');
		return false;
	} else {
		return true;
	}
}
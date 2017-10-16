$(document).ready(function(){
	//------------------------------------------------
	// セッションタイムアウト防止用のタイマー
	//------------------------------------------------
	setInterval("saveSession()", 55 * 60 * 1000);
});

function saveSession() {
	$('#saveSessionBtn').click();
}
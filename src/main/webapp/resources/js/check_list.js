$(document).ready(function(){
	setInterval("saveSession()", 55 * 60 * 1000);
});

function saveSession() {
	$('#saveSessionBtn').click();
}
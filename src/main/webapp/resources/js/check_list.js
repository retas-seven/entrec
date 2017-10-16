$(document).ready(function(){
	//------------------------------------------------
	// セッションタイムアウト防止用のタイマー
	//------------------------------------------------
	setInterval("saveSession()", 55 * 60 * 1000);

	//------------------------------------------------
	// 一覧のタイトル行のfixed
	//------------------------------------------------
    var nav = jQuery('#right_component');

    // メニューのtop座標を取得する
    var offsetTop = nav.offset().top - 45;

    var floatMenu = function() {
        // スクロール位置がメニューのtop座標を超えたら固定にする
        if (jQuery(window).scrollTop() > offsetTop) {
        	nav.addClass('fixed');
        } else {
        	nav.removeClass('fixed');
        }
    }
    jQuery(window).scroll(floatMenu);
    jQuery('body').bind('touchmove', floatMenu);
});

function saveSession() {
	$('#saveSessionBtn').click();
}
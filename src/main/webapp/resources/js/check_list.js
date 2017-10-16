$(document).ready(function(){
	//------------------------------------------------
	// セッションタイムアウト防止用のタイマー
	//------------------------------------------------
	setInterval("saveSession()", 55 * 60 * 1000);

	//------------------------------------------------
	// 画面右側コンポーネントのfixed
	//------------------------------------------------
    var nav = jQuery('#right_component');
    var offsetTop = nav.offset().top - 45;
    var floatMenu = function() {
        // スクロール位置による固定
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
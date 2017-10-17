$(document).ready(function(){
	//------------------------------------------------
	// セッションタイムアウト防止用のタイマー
	//------------------------------------------------
	setInterval("saveSession()", 55 * 60 * 1000);

	//------------------------------------------------
	// 画面右側コンポーネントのfixed
	//------------------------------------------------
    var nav = jQuery('#right_component');
    var offsetTop = nav.offset().top - 25;
    var floatMenu = function() {
        // スクロール位置による固定
        if (jQuery(window).scrollTop() > offsetTop) {
        	nav.addClass('fixed');
        } else {
        	nav.removeClass('fixed');
        }
    }
    jQuery(window).scroll(floatMenu);

	//------------------------------------------------
	// 一覧表ヘッダのfixed
	//------------------------------------------------
    var tbl = jQuery('#tbl');
    var header = jQuery('#tbl_header_only');
    var tblTop = tbl.offset().top;
    var floatHeader = function() {
        // スクロール位置による固定
        if (jQuery(window).scrollTop() > tblTop) {
        	header.addClass('fixed_header');
        } else {
        	header.removeClass('fixed_header');
        }
    }
    jQuery(window).scroll(floatHeader);

});

function saveSession() {
	$('#saveSessionBtn').click();
}
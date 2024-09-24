//var closing_window = false;
//
//$(window).on('focus', function() {
//	closing_window = false;
//});
//
//$(window).on('blur', function() {
//	closing_window = true;
//	// when the window is being minimized
//	if (!document.hidden) {
//		closing_window = false;
//	}
//	// when the window is being maximized
//	$(window).on('resize', function(e) {
//		closing_window = false;
//	});
//	// avoid multiple listening
//	$(window).off('resize');
//});
//
//$('html').on('mouseleave', function() {
//	closing_window = true;
//});
//
//$('html').on('mouseenter', function() {
//	closing_window = false;
//});
//
//$(document).on('keydown', function(e) {
//	if (e.keyCode == 91 || e.keyCode == 18) {
//		closing_window = false;
//
//	}
//	if (e.keyCode == 116 || (e.ctrlKey && e.keyCode == 82)) {
//		closing_window = false;
//
//	}
//});
//
//$(document).on("click", "a", function() {
//	closing_window = false;
//});
//
//$(document).on("click", "button", function() {
//	closing_window = false;
//});
//
//$(document).on("submit", "form", function() {
//	closing_window = false;
//});
//
//$(document).on("click", "input[type=submit]", function() {
//	closing_window = false;
//});
//
//var toDoWhenClosing = function() {
//	$.ajax({
//		type : "POST",
//		url : "/user/host/logout.do",
//		async : false
//	});
//	return;
//};
//window.addEventListener("beforeunload", function(e) {
//	if (closing_window) {
//		toDoWhenClosing();
//	}
//});
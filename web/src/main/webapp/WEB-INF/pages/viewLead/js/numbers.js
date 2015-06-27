//TODO add comments
(function($) {
	$.numbers = {
		id : 70,
		save : function() {
			var form = $("<form action='../service/numbers'></form>");
        	var elems = $("#identifying-numbers-form").find("input,select").filter(function(){
        		return $(this).attr("name") !== "undefined" && $(this).attr("name") !== "";
        	});
        	$(elems).clone().appendTo($(form));
        	$.autosave($(form), success, error);

        	function success(xml){}
        	function error(xml){}
		}
	};
})(jQuery);
$(function() {
});
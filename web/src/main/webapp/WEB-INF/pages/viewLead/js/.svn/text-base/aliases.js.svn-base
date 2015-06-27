//TODO add comments
(function($) {
	$.aliases = {
		id : 60,
		save : function() {
			var form = $("<form action='../service/aliases'></form>");
        	var elems = $("#aliases-section-form").find("input,select").filter(function(){
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
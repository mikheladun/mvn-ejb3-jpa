//TODO add comments
(function($) {
	$.associates = {
		id : 90,
		save : function() {
			var form = $("<form action='../service/associates'></form>");
        	var elems = $("#associates-section-form").find("input,select").filter(function(){
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
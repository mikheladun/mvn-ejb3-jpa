// TODO add comments
(function($) {
	$.travel = {
	id : 80,
	save : function() {
		var form = $("<form action='../service/travel'></form>");

		if ($("#travel-information-form").checkForm()) {
			var elems = $("#travel-information-form").find("input,select,textarea").filter(function() {
				return $(this).attr("name") !== "undefined" && $(this).attr("name") !== "";
			});
			$(elems).each(function() {
				$($(this).clone().attr("name", $(this).attr("name"))).val($(this).val()).appendTo($(form));
			});
			$.autosave($(form), success, error);
			function success(xml) {
				console.log("$.travel.save()->success()");
			}
			function error(xml) {
				console.log("$.travel.save()->error()");
			}
		}
	}
	};
})(jQuery);
$(function() {
	$("#travel-information-form input.carrier").bind("keyup", function() {
		$(this).val($(this).val().toUpperCase());
	});
});
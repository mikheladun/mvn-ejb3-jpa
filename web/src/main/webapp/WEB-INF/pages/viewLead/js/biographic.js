//TODO add comments
(function($) {
	$.biographic = {
		id : 30,
		save : function() {
			var form = $("<form action='../service/biographic'></form>");
			if($("#biographic-information-form").checkForm()) {
	        	var elems = $("#biographic-information-form").find("input,select").filter(function(){
	        		return $(this).attr("name") !== "undefined" && $(this).attr("name") !== "";
	        	});
	        	$(elems).each(function(){
		        	console.log("$.biographic.save() " + $(this).attr("name") + ":" + $(this).val());
	        		$($(this).clone().attr("name",$(this).attr("name"))).val($(this).val()).appendTo($(form));
	        	});
	        	console.log($(form));
	        	$.autosave($(form), success, error);
	
	        	function success(xml){
	        		console.log("$.biographic.save()->success()");
	        		console.log(xml);
	        	}
	        	function error(xml){
	        		console.log("$.biographic.save()->error()");
	        		console.log(xml);
	        	}
			}
		}
	};
})(jQuery);
$(function() {
    $("#biographic-information-form input.date.dob").off("blur").on("blur",function() {
        if ($(this).val() === '__/__/____') {
            $(this).val('');
            $(this).hideError();
        } else if(!regexFormatted.test($(this).val())) {
        	$(this).checkDateFormat();
        } else {
        	$.when($(this).checkCurrentDate())
        	.then($(this).checkBeforeDate($("#entry-information-form input.date.entrydate")));
        }
    });
});
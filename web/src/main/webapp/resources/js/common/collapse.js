(function($) {

	$.fn.extend({
        expand : function(e) {
        	if(this.isNew()) {
	        	var id = this.id();
	            var clone = this.find("div.expand-collapse:first").clone().removeClass("expand-collapse").addClass("hidden expand-collapse-div expand-collapse-div-" + id);
	            if(this.find("div.expand-collapse-div").length === 0) {
	            	$(clone).insertAfter(this.find("div.expand-collapse").siblings("div.buttons"));
	            } else {
	            	$(clone).insertAfter(this.find("div.expand-collapse-div:last"));
	            }
	            $(clone).find(".buttons,.expand-collapse-div span.expand-collapse-link").removeClass("hidden");
	            $(clone).find("input,select,textarea").not(".buttons input").attr("name","").attr("id","").attr("readonly","readonly").addClass("readonly");

	            //replace all select with input.readonly
	            $(clone).find("select").replaceWith(function(){
	            	return $("<input type='text' />").val($(this).val()).attr("disabled","disabled").addClass("readonly");
	            });

		    	$(clone).find("span.expand-collapse-link a").off("click").on("click", function(e) {
		    		$(this).parent().parent().fadeOut().addClass("hidden");
		    		e.preventDefault();
		    	});

		    	this.children("span.expand-collapse-link").removeClass("hidden").attr("");
        	}
        }
	});
	$.expand = {
        open : function(e) {
			$(e).siblings("div.expand-collapse-div").add("div.expand-collapse-div .buttons,div.expand-collapse-div .buttons input,.expand-collapse-div span.expand-collapse-link").removeClass("hidden").hide().fadeIn();
			$(e).children("a").text("Collapse All");
        },
        close : function(e) {
        	$(e).siblings("div.expand-collapse-div").add("div.expand-collapse-div .buttons,div.expand-collapse-div .buttons input").fadeOut().addClass("hidden");
			$(e).children("a").text("Expand All");
        },
    };
})(jQuery);
$(function() {
	$("span.expand-collapse-link a").on("click", function(e){
		if($(this).text().toUpperCase() === "EXPAND ALL") {
			$.expand.open($(this).parent());
		} else if($(this).text().toUpperCase() === "COLLAPSE ALL") {
			$.expand.close($(this).parent());
		}
		e.preventDefault();
	});
});
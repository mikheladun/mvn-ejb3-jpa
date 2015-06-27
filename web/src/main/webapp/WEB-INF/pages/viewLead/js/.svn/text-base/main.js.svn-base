//TODO add comments
(function($) {

})(jQuery);

$(function() {
	$("div.left div.icons img.diamond").on("click",function() {
		$(this).parent().siblings("div.baseball").addClass("hidden");
		$(this).parent().siblings("div.update-nav").removeClass("hidden");
	});
	$("div.left div.icons img.person").on("click", function() {
		$(this).parent().siblings("div.update-nav").addClass("hidden");
		$(this).parent().siblings("div.baseball").removeClass("hidden");
	});
	$("div.right div.icons img.dots").on("click",function() {
		$(this).parent().siblings("div.indices").addClass("hidden");
		$(this).parent().siblings("div.lead-disposition").removeClass("hidden");
	});
	$("div.right div.icons img.check").on("click", function() {
		$(this).parent().siblings("div.lead-disposition").addClass("hidden");
		$(this).parent().siblings("div.indices").removeClass("hidden");
	});
	$("div.right div.icons img.check").on("click", function() {
		$(this).parent().siblings("div.lead-disposition").addClass("hidden");
		$(this).parent().siblings("div.indices").removeClass("hidden");
	});

	// navigation controls
	$('div.left div.update-nav li input[type="checkbox"]').on("click",function() {
		var div = $("#content").find("div." + $(this).attr("name"));
		console.log("show/hide " + $(this).attr("name"));
		if ($(this).is(":checked")) {
			$(div).removeClass("hidden").hide().fadeIn();
		} else {
			$(div).addClass("hidden").fadeOut();
		}
	});
	$('div.left div.update-nav li input[type="checkbox"]:first').off("click").on("click",function() {
		if ($(this).attr("checked") === "checked") {
			$('div.left div.update-nav li input[type="checkbox"]').attr("checked", "checked");
			$.each($("#content").find("div.view-lead").children("div"),function(){
				if($(this).is(":hidden") || $(this).hasClass("hidden")) {
					$(this).removeClass("hidden").hide().fadeIn();
				}
			});
		} else {
			$('div.left div.update-nav li input[type="checkbox"]').removeAttr("checked");
			$.each($("#content").find("div.view-lead").children("div"),function(){
				if(!$(this).is(":hidden") | $(this).hasClass("hidden")) {
					$(this).addClass("hidden").fadeOut();
				}
			});
		}
		// TODO load data for each panel
	});
	$("div.left div.update-nav li a").on("click",function() {
		var div = $("#content").find("div." + $(this).prev().attr("name"));
		$(this).prev().attr("checked","checked");
		if ($(div).is(":hidden") || $(div).hasClass("hidden")) {
			$(div).removeClass("hidden").hide().fadeIn();
		}
		// TODO scroll to div
		if($(div).length > 0) {
			$("html,body").animate({
				scrollTop : $(div).offset().top - 100
			}, 800, function() {
				$(div).find("input[type!='image'][type!='hidden'],select,textarea").not(".readonly").filter(":first").focus();
			});
		}
	});
	$('div.left div.update-nav li a:first').off("click").on("click",function() {
		console.log("update-nav li a -> view all ");
		$('div.left div.update-nav li input[type="checkbox"]').attr("checked", "checked");
		$.each($("#content").find("div.view-lead").children("div"),function(){
			if($(this).is(":hidden") || $(this).hasClass("hidden")) {
				$(this).removeClass("hidden").hide().fadeIn();
			}
		});
		// TODO load data for each panel
	});

	//auto-save
	var currAutoSaveId = -1;
	var prevAutoSaveId = -1;
	$(".view-lead").children("div").find("input,select,textarea,a").on("click focus keyup", function(e) {
        var tabIndex = $(this).attr("tabindex");
        if(currAutoSaveId == -1) {
            currAutoSaveId = tabIndex;
            prevAutoSaveId = currAutoSaveId;
        }
        if(currAutoSaveId != tabIndex) {
            prevAutoSaveId = currAutoSaveId;
            currAutoSaveId = tabIndex;
            //console.log($(this).attr("name") + ":" + tabIndex);
            //console.log(prevAutoSaveId + ":" + currAutoSaveId);
            switch(parseInt(prevAutoSaveId)) {
                case $.biographic.id : //save biographic information
                    $.biographic.save();
                    break;
                case $.entry.id : //save entry information
                    $.entry.save();
    				$.entry.navUpdate();
                    break;
                case $.comments.id : //save lead comments
                    $.comments.save();
    				$.comments.navUpdate();
                    break;
                case $.aliases.id : //save aliases section
                    $.aliases.save();
                    break;
                case $.numbers.id : //save identifying numbers
                    $.numbers.save();
                    break;
                case $.travel.id : //save travel information
                    $.travel.save();
                    break;
                case $.associates.id : //save associates section
                    $.associates.save();
                    break;
            }
        }
        //e.preventDefault();
	});
});
//-------------BEGIN disposition.js
(function($) {
	$.disposition = {
		id : 300,
		save : function() {
			var form = $("<form action='../service/disposition'></form>");
			if($("#lead-disposition-form").checkForm()) {
	        	var elems = $("#lead-disposition-form").find("input.date,input[name='ltLead.id'],select,textarea").filter(function() {
	        		return $(this).attr("name") !== "undefined" && $(this).attr("name") !== "" && !$(this).parent().hasClass("hidden");
	        	});
	        	$(elems).each(function(){
		        	console.log("$.disposition.save() " + $(this).attr("name") + ":" + $(this).val());
		        	$($(this).clone().attr("name",$(this).attr("name"))).val($(this).val()).appendTo($(form));
	        	});
	        	$.autosave($(form), success, error);
	
	        	function success(xml){
	        		self.location.href = "/leadtrac-web/workQueue";
	        	}
	        	function error(xml){
	        		console.log("$.disposition.save()->error");
	        		console.log(xml);
	        	}
			}
		},

		dispositionText : function() {
			return $("#lead-disposition-form select:first option:selected").text();
		},

		closureReasonText : function() {
			return $("#lead-disposition-form select.closure-reason option:selected").text();
		},

		pendingReasonText : function() {
			return $("#lead-disposition-form select.pending-reason option:selected").text();
		},

		returnReasonText : function() {
			return $("#lead-disposition-form select.return-reason option:selected").text();
		},

		holdReasonText : function() {
			return $("#lead-disposition-form select.hold-reason option:selected").text();
		},

		closureSystemText : function() {
			return $("#lead-disposition-form select.closure-system option:selected").text();
		},

		callupDateText : function() {
			return $("#lead-disposition-form input.date").val();
		},

		supervisorText : function() {
			return $("#lead-disposition-form select.supervisor option:selected").text();
		},

		analystText : function() {
			return $("#lead-disposition-form select.analyst option:selected").text();
		},

		detailsText : function() {
			return $("#lead-disposition-form textarea.details").val();
		}
	};
})(jQuery);

$(function() {
	$("#lead-disposition-form select.disposition").on("change",function() {
		var form = $("#lead-disposition-form");
		$(form).find("input[type=text],select,textarea").not($(this)).parent().addClass("hidden").fadeOut();
		$(form).find("input[type='button'],span.error").addClass("hidden").fadeOut();
		$(form).find("input,select,textarea").removeClass("error");

		var disp = $(this).children("option:selected").text();
		if(disp === "Close Lead") {
			$(form).find("#closureReason,#closureSystem").parent().removeClass("hidden").hide().fadeIn();

			if($(form).find("input[value='SU_SA']").length > 0) {
				$(form).find("input[name='closeLead']").removeClass("hidden").hide().fadeIn();
			} else {
				$(form).find("select.supervisor").parent().removeClass("hidden").hide().fadeIn();
				$(form).find("input[name='submitForReview']").removeClass("hidden").hide().fadeIn();
			}
		}
		else if(disp === "Place Lead in Pending") {
			
			
			$(form).find("#pendingReason,#callUpDate").parent().removeClass("hidden").hide().fadeIn();
			$(form).find("input[name='placeInPending']").removeClass("hidden").hide().fadeIn();
			
			// fix	- kalyan		
			$('input#dispositionSubmitForReviewButton').removeClass("hidden");
			$('select#dispositionSupervisor').parent().removeClass("hidden");
			//end			
			
		}
		else if(disp === "Place Lead on Hold") {
			$(form).find("#holdReason,#dispositionDetails").parent().removeClass(" 	hidden").hide().fadeIn();
			$(form).find("input[name='placeOnHold']").removeClass("hidden").hide().fadeIn();
		}
		else if(disp === "Return Lead") {
			$(form).find("#returnReason,select.analyst,#dispositionDetails").parent().removeClass("hidden").hide().fadeIn();
			$(form).find("input[name='returnLead']").removeClass("hidden").hide().fadeIn();
		}
		//else, disposition == "Select..."
		//else {
		//	$(form).find("input,select,textarea").removeClass("error");
		//}
	});

	$("#lead-disposition-form a[title='Cancel']").on("click",function(){
		var form = $("#lead-disposition-form");
		$(form).find("input[type='text'],select,textarea").removeClass("error").val("");
		$(form).find("select").prop("selectedIndex",0);
		$(form).find("span.error").hide();
	});

	//Close Lead
	$("#lead-disposition-form input[name='closeLead']").on("click",function(e){
    	var message = '<ul class="message">';
			message += '<li class="label">Closure Reason:</li>';
			message += '<li>' + $.disposition.closureReasonText() + "</li>";
			message += '<li class="label">Closure System:</li>';
			message += "<li>" + $.disposition.closureSystemText() + "</li>";
			message += "</ul>";

			if($("#lead-disposition-form").checkForm()) {
		        $(this).confirmation("Close Lead", message, function(e,t) {
		            switch (t) {
		                case $.dialog.CONFIRM: 
		                case $.dialog.OK:
		                	$.disposition.save();
		                	break;
		                case $.dialog.CANCEL: // do nothing
		                default:
		            }
		        });
			}
	});
	//Place In Pending
	$("#lead-disposition-form input[name='placeInPending']").on("click",function(e){
    	var message = '<ul class="message">';
		message += '<li class="label">Pending Reason:</li>';
		message += "<li>" + $.disposition.pendingReasonText() + "</li>";
		message += '<li class="label">Call-Up Date:</li>';
		message += "<li>" + $.disposition.callupDateText() + "</li>";
		message += "</ul>";
		if($("#lead-disposition-form").checkForm()) {
	        $(this).confirmation("Place Lead in Pending", message, function(e,t) {
	            switch (t) {
	                case $.dialog.CONFIRM:
	                case $.dialog.OK:
	                	$.disposition.save();
	                	break;
	                case $.dialog.CANCEL: // do nothing
	                default:
	            }
	        });
		}
	});
	//Submit For Review
	$("#lead-disposition-form input[name='submitForReview']").on("click",function(e){
    	var message = '<ul class="message">';
		message += '<li class="label">Disposition</li>';
		message += "<li>" + $.disposition.dispositionText() + "</li>";
		if($.disposition.dispositionText() === "Close Lead") {
			message += '<li class="label">Closure Reason:</li>';
			message += "<li>" + $.disposition.closureReasonText() + "</li>";
			message += '<li class="label">Closure System:</li>';
			message += "<li>" + $.disposition.closureSystemText() + "</li>";
		} else if($.disposition.dispositionText() === "Place Lead in Pending") {
			message += '<li class="label">Pending Reason:</li>';
			message += "<li>" + $.disposition.pendingReasonText() + "</li>";
			message += '<li class="label">Call-up Date:</li>';
			message += "<li>" + $.disposition.callupDateText() + "</li>";
		}
		message += '<li class="label">Supervisor</li>';
		message += "<li>" + $.disposition.supervisorText() + "</li>";
		message += "</ul>";
		if($("#lead-disposition-form").checkForm()) {
	        $(this).confirmation("Submit Lead for Review", message, function(e,t) {
	            switch (t) {
	                case $.dialog.CONFIRM:
	                case $.dialog.OK:
	                	$.disposition.save();
	                	break;
	                case $.dialog.CANCEL: // do nothing
	                default:
	            }
	        });
		}
	});
	//Return Lead
	$("#lead-disposition-form input[name='returnLead']").on("click",function(e){
    	var message = '<ul class="message">';
		message += '<li class="label">Return Reason:</li>';
		message += "<li>" + $.disposition.returnReasonText() + "</li>";
		message += '<li class="label">Analyst:</li>';
		message += "<li>" + $.disposition.analystText() + "</li>";
		message += '<li class="label">Details:</li>';
		message += "<li>" + $.disposition.detailsText() + "</li>";
		message += "</ul>";

		if($("#lead-disposition-form").checkForm()) {
			$(this).confirmation("Place Lead in Pending", message, function(e,t) {
	            switch (t) {
	                case $.dialog.CONFIRM:
	                case $.dialog.OK:
	                	$.disposition.save();
	                	break;
	                case $.dialog.CANCEL: // do nothing
	                default:
	            }
	        });
		}
	});
	//Place on Hold
	$("#lead-disposition-form input[name='placeOnHold']").on("click",function(e){
    	var message = '<ul class="message">';
		message += '<li class="label">Reason:</li>';
		message += "<li>" + $.disposition.holdReasonText() + "</li>";
		message += '<li class="label">Details:</li>';
		message += "<li>" + $.disposition.detailsText() + "</li>";
		message += "</ul>";

		if($("#lead-disposition-form").checkForm()) {
			$(this).confirmation("Place Lead in Pending", message, function(e,t) {
	            switch (t) {
	                case $.dialog.CONFIRM:
	                case $.dialog.OK:
	                	$.disposition.save();
	                	break;
	                case $.dialog.CANCEL: // do nothing
	                default:
	            }
	        });
		}
	});
});
//-------------END disposition.js
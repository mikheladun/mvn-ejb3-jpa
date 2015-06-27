// TODO add comments
(function($) {
	$.entry = {
	id : 40,
	navUpdate : function() {
		var hasInfo = false;
		$("#entry-information-form").find("input[type!='hidden'],select").each(function() {
			if (($(this).is("input[type='text']") && $.trim($(this).val()) !== "") 
					|| ($(this).is("select") && $(this).val() !== "Select...") 
					|| ($(this).is("input[type='checkbox']") && $(this).is(":checked"))) {
				hasInfo = true;
				return false;
			}
		});
		if (hasInfo) {
			$(".left .update-nav li.entry-information-nav").addClass("bg").find("a").addClass("bold");
		} else {
			$(".left .update-nav li.entry-information-nav").removeClass("bg").find("a").removeClass("bold");
		}
	},
	save : function() {
		var form = $("<form action='../service/entry'></form>");
		if ($("#entry-information-form").checkForm()) {
			var elems = $("#entry-information-form").find("input,select").filter(function() {
				return $(this).attr("name") !== "undefined" && $(this).attr("name") !== "";
			});
			$(elems).each(function() {
				//console.log("$.entry.save() " + $(this).attr("name") + ":" + $(this).val());
				if ($(this).attr("name") === "entryModel.durationStatus.value") {
					var clone = $($(this).clone()).attr("name", $(this).attr("name"));
					$(clone).val($(this).is(":checked") ? "Y" : "N");
					$(form).append($(clone));
					//console.log("$.entry.save() " + $(clone).attr("name") + ":" + $(clone).val());
				} else {
					$($(this).clone().attr("name", $(this).attr("name"))).val($(this).val()).appendTo($(form));
				}
			});

			$.autosave($(form), success, error);

			function success(xml) {
				console.log("$.entry.save()->success()");
				console.log(xml);
				//$(form).empty();
			}
			function error(xml) {
				console.log("$.entry.save()->error()");
				console.log(xml);
				//$(form).empty();
			}
		}
	}
	};
})(jQuery);
$(function() {
	$.entry.navUpdate();

	$("#entry-information-form input.date.entryDate").off("blur").on("blur", function() {
		if ($(this).val() === '__/__/____') {
			$(this).val('');
			$(this).hideError();
		} else if (!regexFormatted.test($(this).val())) {
			$(this).checkDateFormat();
		} else {
			$.when($(this).checkCurrentDate()).then($(this).checkAfterDate($("#biographic-information-form input.date.dob"))).then($(this)
					.checkBeforeDate($("#entry-information-form input.date.admitUntilDate"))).then($(this)
					.checkBeforeDate($("#entry-information-form input.date.adjustedAdmitUntilDate")));
		}
	});
	$("#entry-information-form input.date.admitUntilDate").off("blur").on("blur", function() {
		if ($(this).val() === '__/__/____') {
			$(this).val('');
			$(this).hideError();
		} else if (!regexFormatted.test($(this).val())) {
			$(this).checkDateFormat();
		} else {
			$(this).checkAfterDate($("#entry-information-form input.date.entryDate"));
		}

		if (regexFormatted.test($(this).val()) && regexFormatted.test($('input#subjectEntryDate').val())) {
			var d1 = new Date($(this).val()); // admit until date
			var d2 = new Date($('input#subjectEntryDate').val()); // entry date

			if (d2 > d1) $(this).showCustomError('Admit until Date should be greater than the Entry Date');
		}
	});
	$("#entry-information-form input.date.adjustedAdmitUntilDate").off("blur").on("blur", function() {
		if ($(this).val() === '__/__/____') {
			$(this).val('');
			$(this).hideError();
		} else if (!regexFormatted.test($(this).val())) {
			$(this).checkDateFormat();
		} else {
			$(this).checkAfterDate($("#entry-information-form input.date.entryDate"));
		}

		if (regexFormatted.test($(this).val()) && regexFormatted.test($('input#subjectEntryDate').val())) {
			var d1 = new Date($(this).val()); // adjusted admit until date
			var d2 = new Date($('input#subjectEntryDate').val()); // entry date

			if (d2 > d1) $(this).showCustomError('Adjusted Admit until Date should be greater than than Entry Date');
		}
	});
});
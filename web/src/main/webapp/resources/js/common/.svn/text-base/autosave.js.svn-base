(function($) {
	$.autosave = function(form, success, error) {
		$.ajax({
			type : "POST",
			url : $(form).attr("action"),
			data : $(form).serialize(),
		}).done(function(r) {
			success(r);
		}).fail(function(r) {
			error(r);
		});
	};
	$.fn.extend({
		autoSave : function(callback) {
			fn = callback;
			$.ajax({
				type : "POST",
				url : this.attr("action"),
				data : this.serialize(),
			}).done(function(r) {
				if (typeof fn === "function") {
					fn(r);
				}
			}).fail(function(r) {
				if (typeof fn === "function") {
					fn(r);
				}
			});
		}
	});
})(jQuery);
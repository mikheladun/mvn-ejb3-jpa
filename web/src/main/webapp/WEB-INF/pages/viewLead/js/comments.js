// TODO ----------------BEGIN comments.js
(function($) {

	$.comments = {
	id : 50,
	model : $("<form action='../service/comments'></form>"),
	xml : "<xml>" + commentsXml + "</xml>",

	init : function(e) {
		var xmlDoc = $.parseXML($.comments.xml);
		$.comments.xml = $(xmlDoc);
		var comments = $.comments.xml.find("ltLeadComment");
		$.each(comments, function(i, c) {
			var user = $(c).children("ltUserByCreateBy").children("id").text();
			user += ":" + $(c).children("ltUserByCreateBy").children("lastname").text();
			user += ", " + $(c).children("ltUserByCreateBy").children("firstname").text();
			var date = $(c).children("createDate").text();
			var id = $(c).children("id").text();
			var text = $(c).children("leadComment").text();
			$.comments.model.append($('<input type="text" name="commentModel.comments[' + i + '].user"/>').val(user));
			$.comments.model.append($('<input type="text" name="commentModel.comments[' + i + '].date.value"/>').val(date));
			$.comments.model.append($('<input type="text" name="commentModel.comments[' + i + '].id"/>').val(id));
			$.comments.model.append($('<input type="text" name="commentModel.comments[' + i + '].text"/>').val(text));
			$.comments.model.append($('<input type="text" name="commentModel.comments[' + i + '].editable"/>').val(user === $.comments.currentUser() ? "true" : "false"));

			$("#lead-comments-form .expand-collapse-div-"+id).attachEvent("click", 'input[name="delete"]', function(e) {
				$(".lead-comments-data-table").deleteRow(id, function(e) {
		        	$.comments.destroy(id);
		        });
		        e.preventDefault();
		    });
		});
		//console.log($.comments.model);

		$("#lead-comments-form").find("input[name='commentModel.comments[0].editable']").val("true");
		var id = parseInt($(".lead-comments-data-table").find("tbody tr").length);
		$("#lead-comments-form").append($('<input type="hidden" name="id" />').val(id + ":true"));
	},
	navUpdate: function(){
        if($("#lead-comments-form").find("table:visible").length > 0){
            $(".left .update-nav li.lead-comments-nav").addClass("bg").find("a").addClass("bold");
        }
        else{
            $(".left .update-nav li.lead-comments-nav").removeClass("bg").find("a").removeClass("bold");
        }
      },
	save : function(e) {
		if($.trim($.comments.commentText()) !== "" && $.comments.editable() === "true") {
		var user = $('<input type="text" name="commentModel.comments[0].user"/>').val($.comments.commentUser());
		var date = $('<input type="text" name="commentModel.comments[0].date.value"/>').val($.comments.commentDate());
		var id = $('<input type="text" name="commentModel.comments[0].id"/>').val($.comments.commentId());
		var text = $('<input type="text" name="commentModel.comments[0].text"/>').val($.comments.commentText());
		var editable = $('<input type="text" name="commentModel.comments[0].editable"/>').val($.comments.editable());
		var arr = [user,date,id,text,editable];
		$(arr).each(function() {
			var name = $(this).attr("name").replace(/(\w+\.\w+)\[0\](.*)/, "$1[" + $("#lead-comments-form").id() + "]$2");
			//console.log("$.comments.save() " + name + ":" + $(this).val());
			$(this).attr("name", name).val($(this).val()).appendTo($.comments.model);
		});

		if($.comments.model.find("input[name='ltLead.id']").length === 0) {
			$.comments.model.append($("<input name='ltLead.id' />").val($("#lead-comments-form").find("input[name='ltLead.id']").val()));
		}

		$.autosave($.comments.model, success, error);

		function success(xml) {
			var t = $(".lead-comments-data-table").dataTable();
			var data = [ '<a href="#">' + $.comments.commentUsername() + '</a>', 
			             $.comments.commentDate(), 
			             '<span class="ellipsis" title="' + $.comments.commentText() + '">' + $.comments.commentText() + '</span>'];
			$(t).insertRow($("#lead-comments-form").id(), data, function(e) {
				$(t).attachEvent("click", "tbody tr td a", function(e) {
					var row = $(this).parent().siblings("td:last").find('input[type="hidden"][name="row"]').val();
					$.comments.reset();
					$.comments.show(row);
					e.preventDefault();
				});
				$(t).attachEvent("click", 'tbody tr td input[type="image"][name="del"]', function(e) {
					var row = $(this).siblings('input[type="hidden"][name="row"]').val();
					$(".lead-comments-data-table").deleteRow(row, function(e) {
						$("#lead-comments-form").find(".expand-collapse-div-" + row).remove();
						$.comments.destroy(row);
					});
					e.preventDefault();
				});
			});

			var response = $(xml).find("response");
			if($(response).attr("status").toUpperCase() === "SUCCESS") {
				var comments = $(response).find("ltLeadComment");
				$.each(comments, function(i,c){
					var id = $(c).children("id").text();
					//console.log("$.comments.save()->success() : " + id);
					var elems = $.comments.model.find("input").filter(function() {
						return /^commentModel\.comments\[\d\]\.id$/.test($(this).attr("name")) && $(this).val() === id;
					});
					//console.log(elems);
					if(elems.length === 0) {
						elems = $.comments.model.find("input").filter(function() {
							return /^commentModel\.comments\[\d\]\.id$/.test($(this).attr("name")) && $(this).val() === "";
						});
						//console.log(elems);
						$(elems).val(id);
					}
				});
			}		

			$.comments.navUpdate();
			$.comments.expand();
			$.comments.reset();
		}
		function error(xml) {
		}
	  }
	},

	update : function(fn) {
		if($.comments.editable() === "true") {
			var elems = $("#lead-comments-form").find("input[name^='commentModel.comments[0]'],textarea[name^='commentModel.comments[0]']");
			elems.each(function() {
				var name = $(this).attr("name");
				name = name.replace(/(\w+\.\w+)\[0\](.*)/, "$1[" + $("#lead-comments-form").id() + "]$2");
				if ($.comments.model.find(':input[name="' + name + '"]').length === 0) {
					$($(this).clone().attr("name", name)).val($(this).val()).removeAttr("disabled").appendTo($.comments.model);
				} else {
					$.comments.model.find(':input[name="' + name + '"]').val($(this).val()).removeAttr("disabled");
				}
				//console.log("$.comments.update() " + name + ":" + $(this).val());
			});
	
			if($.comments.model.find("input[name='ltLead.id']").length === 0) {
				$.comments.model.append($("<input name='ltLead.id' />").val($("#lead-comments-form").find("input[name='ltLead.id']").val()));
			}
	
			$.autosave($.comments.model, success, error);
	
			function success(xml) {
				var t = $(".lead-comments-data-table").dataTable();
				var data = [ '<a href="#">' + $.comments.commentUsername() + '</a>', 
				             $.comments.commentDate(), 
				             '<span class="ellipsis" title="' + $.comments.commentText() + '">' + $.comments.commentText() + '</span>' ];
				$(t).updateRow($("#lead-comments-form").id(), data, function(e) {
					$(t).attachEvent("click", "tbody tr td a", function(e) {
						var row = $(this).parent().siblings("td:last").find('input[type="hidden"][name="row"]').val();
						$.comments.reset();
						$.comments.show(row);
						e.preventDefault();
					});
				});
	
				$.comments.expand();
				$.comments.reset();
			}
			function error(xml) {
			}
		}
	},

	destroy : function(id) {
		//console.log("$.comments.destroy() : " + id);
		var elems = $.comments.model.find("input").filter(function() {
			return new RegExp("commentModel.comments\\[" + id + "\\].+").test($(this).attr("name"));
		});
		$.each(elems, function(i) {
			var name = $(this).attr("name");
			//console.log("$.comments.destroy() " + name + " : " + $(this).val());
			$(this).remove();
		});

		$("#lead-comments-form").find(".expand-collapse-div-" + id).remove();

		if($.comments.model.find("input[name='ltLead.id']").length === 0) {
			$.comments.model.append($("<input name='ltLead.id' />").val($("#lead-comments-form").find("input[name='ltLead.id']").val()));
		}

		//console.log($.comments.model);

		$.autosave($.comments.model, success, error);

		function success(xml) {
			$.comments.navUpdate();
		}
		function error(xml) {
		}

		this.reset();
	},
	show : function(id) {
		var editable = true;
		//console.log("show() " + id);
		// reset form id
		$("#lead-comments-form").children('input[type="hidden"][name="id"]').val(id + ":false");
		var elems = $.comments.model.find(':input[name^="commentModel.comments[' + id + ']"]');
		// loop thru elements to retrieve its value
		$.each(elems, function() {
			var name = $(this).attr("name");
			name = name.replace(/(\w+)\[\d+\](.*)/, "$1[0]$2");
			// console.log("show() " + name + " : " + $(this).val());
			// set elements value into form value
			$("#lead-comments-form").find(':input[name="' + name + '"]').val($(this).val());
			if(name === "commentModel.comments[0].user" && $(this).val() !== $.comments.currentUser()) {
				editable = false;
			}	
			//console.log("$.comments.show() " + name + ":" + $(this).val() + ":" + editable);
		});
		if(editable) {
			// show update/delete button, hide add button
			$("#lead-comments-form").find('input[name="add"]').addClass("hidden").fadeOut();
			$("#lead-comments-form").children("div.buttons").find('input[name="update"],input[name="delete"]').removeClass("hidden").hide().fadeIn();
		} else {
			$("#lead-comments-form").find('textarea[name="commentModel.comments[0].text"]').attr("readonly","readonly").addClass("readonly");
			$("#lead-comments-form").children("div.buttons").find(':input').addClass("hidden").fadeOut();
		}
	},
	expand : function() {
		var id = $("#lead-comments-form").id();
		//console.log("$.comments.expand() " + id + ":" + $("#lead-comments-form").isNew());

		$("#lead-comments-form").expand();
		$("#lead-comments-form").find(".expand-collapse-div-" + id).find("h5.title").html($.comments.commentUsername() + " : " + $.comments.commentDate());
		$("#lead-comments-form").find(".expand-collapse-div-" + id).find("textarea").html($.comments.commentText());

		$("#lead-comments-form .expand-collapse-div-" + id).attachEvent("click", 'input[name="delete"]', function(e) {
			$(".lead-comments-data-table").deleteRow(id, function(e) {
				$.comments.destroy(id);
			});
			e.preventDefault();
		});

	},
	reset : function(all) {
		// reset subject form
		if ($(".lead-comments-data-table").find("tbody tr").length === 0) {
			$("#lead-comments-form").find("span.expand-collapse-link").addClass("hidden").fadeOut();
		}

		$("#lead-comments-form").find('input[name="add"]').removeClass("hidden").hide().fadeIn();
		$("#lead-comments-form").find('input[name="update"],.expand-collapse input[name="delete"]').addClass("hidden").fadeOut();

		$("#lead-comments-form").find(".expand-collapse :input[name^='commentModel.comments[0]']").val("");
		$("#lead-comments-form").find("input[name='commentModel.comments[0].user']").val($.comments.currentUser());
		$("#lead-comments-form").find('.expand-collapse :input[name="commentModel.comments[0].text"]').removeAttr("readonly").removeClass("readonly");
		$("#lead-comments-form").find('.expand-collapse :input[name="commentModel.comments[0].editable"]').val("true");
		if (all) {
			$("#lead-comments-form").find(".expand-collapse-div").remove();
			$("#lead-comments-form").find(".expand-collapse-link").addClass("hidden").fadeOut();
			$("#lead-comments-form").find($(".lead-comments-data-table")).find("tbody tr").remove();
			$("#lead-comments-form").find($(".lead-comments-data-table")).find("table").fadeOut();
		}

		var id = parseInt($(".lead-comments-data-table").find("tbody tr").length);
		$("#lead-comments-form").find('input[type="hidden"][name="id"]').val(id + ":true");
	},

	isValid : function() {
		var valid = false;
		if ($.trim(this.commentText()) !== "") {
			valid = true;
		}
		return valid;
	},
	isBlank : function() {
		var blank = !this.isValid();
		return blank;
	},
	commentId : function() {
		return $("#lead-comments-form").find("textarea[name='commentModel.comments[0].id']").val();
	},
	commentText : function() {
		return $("#lead-comments-form").find("textarea[name='commentModel.comments[0].text']").val();
	},
	commentUser : function() {
		var user = $("#lead-comments-form").find("input[name='commentModel.comments[0].user']").val();
		if ($.trim(user) === "") {
			user = $.comments.currentUser();
		}
		return user;
	},
	commentUserId : function() {
		var user = $("#lead-comments-form").find("input[name='commentModel.comments[0].user']").val();
		if ($.trim(user) === "") {
			user = $.comments.currentUser();
		}
		return user.split(":")[0];
	},
	commentUsername : function() {
		var user = $("#lead-comments-form").find("input[name='commentModel.comments[0].user']").val();
		if ($.trim(user) === "") {
			user = $.comments.currentUser();
		}
		return user.split(":")[1];
	},
	currentUser : function() {
		return $("#lead-comments-form").find("input[name='currentUser']").val();
	},
	commentDate : function() {
		var date = $("#lead-comments-form").find("input[name='commentModel.comments[0].date.value']").val();
		if ($.trim(date) === "") {
			var date = new Date();
			var M = (date.getMonth() + 1) < 10 ? '0' + (date.getMonth() + 1) : (date.getMonth() + 1);
			var d = date.getDate() < 10 ? '0' + date.getDate() : date.getDate();
			var y = date.getFullYear();
			var h = date.getHours() < 10 ? '0' + date.getHours() : date.getHours();
			var m = date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes();
			date = (M + "/" + d + "/" + y + " - " + h + "" + m);
			$("#lead-comments-form").find("input[name='commentModel.comments[0].date.value']").val(date);
		}

		return date;
	},
	editable : function() {
		return $("#lead-comments-form").find("input[name='commentModel.comments[0].editable']").val();
	}
  };
})(jQuery);

$(document).ready(function() {
	$.comments.init();
	$.comments.navUpdate();

	$("#lead-comments-form").find('input[name="add"],input[name="update"]').on("click", function(e) {
		if ($.comments.isValid() && !$.comments.isBlank()) {
			//console.log("#lead-comments-form add/update");
			if ($("#lead-comments-form").isNew()) {
				$.comments.save(e);
			} else {
				$.comments.update(e);
			}
		}
		e.preventDefault();
	});

	$("#lead-comments-form").find('tbody tr td input[type="image"][name="del"]').on("click", function(e) {
		var row = $(this).siblings('input[type="hidden"][name="row"]').val();
		$(".lead-comments-data-table").deleteRow(row, function(e) {
			$("#lead-comments-form").find(".expand-collapse-div-" + row).remove();
			$.comments.destroy(row);
		});
		e.preventDefault();
	});
	$("#lead-comments-form").find("tbody tr td a").on("click", function(e) {
		var row = $(this).parent().siblings("td:last").find('input[type="hidden"][name="row"]').val();
		$.comments.reset();
		$.comments.show(row);
		e.preventDefault();
	});

	$("#lead-comments-form").find('input[name="delete"]').on("click", function(e) {
		var id = -1;
		if($(this).parent().parent().hasClass("expand-collapse-div")) {
			var id = $(this).attr("id").split(":")[0];
		} else {
			id = $("#lead-comments-form").id();
		}
		//console.log("#lead-comments-form delete " + id);
		$(".lead-comments-data-table").deleteRow(id, function(e) {
			$.comments.destroy(id);
		});
		e.preventDefault();
	});
	$("#lead-comments-form").find('a[title="Cancel lead comment"]').on("click", function(e) {
		//console.log("#lead-comments-form cancel");
		if(!$.comments.isBlank() && $("#lead-comments-form").find('textarea[name="commentModel.comments[0].text"]').attr("readonly") !== "readonly") {
			var text = "Are you sure? All data will be lost?";
	        $(this).confirmation("Cancel Lead Comment",text,function(e,t) {
	            switch (t) {
	                case "CONFIRM": case "OK":
	                    // autoSave : function(form, data, success, dataType)
	                    /* get some values from elements on the page: */
	                	$.comments.reset();
	                    e.preventDefault(); 
	                    break;
	                default :
	            }
	            e.preventDefault();
	        });
		}
	
		e.preventDefault();
	});
	
	$("#lead-comments-form .expand-collapse-div").find("span.expand-collapse-link a").off("click").on("click", function(e) {
		$(this).parent().parent().fadeOut().addClass("hidden");
		e.preventDefault();
	});

});

// TODO ----------------END comments.js

//TODO add comments
(function($){

	$.associates = {
			model : $("<form></form>"),

	        save : function(e) {
	        	var elems = $("#associated-subjects-form").find("select").filter(function(){
	        		return /^ltLeadsModel\[\d+\]\.associateModel\[\d+\].+$/.test($(this).attr("name"));
	        	});
	            elems.each(function(){
	            	var name = $(this).attr("name").replace(/(\w+)\[\d+\](.*)\[\d+\](.*)/, "$1[" + $("form#model").id() + "]$2[" + $("#associated-subjects-form").id() + "]$3");
	            	console.log("$.associates.save() " + name);
	        		if($.associates.model.find('select[name="' + name + '"]').length === 0) {
	        			$($(this).clone()).attr("name",name).val($(this).val()).removeAttr("disabled").appendTo($.associates.model);
	        		} else {
	        			$.associates.model.find('select[name="' + name + '"]').val($(this).val()).removeAttr("disabled");
	        		}
	            });
	        	console.log($.associates.model);

            	$("#associated-subjects-form select:first option:selected").attr("disabled","disabled");

                $.when(this.table(e)).then(this.expand()).then(this.reset());
	        },
	        destroy : function(id) {
	        	console.log("$.associates.destroy() : " + id);
	        	var elems = $.associates.model.find("select").filter(function() { 
	        		return new RegExp("ltLeadsModel\\[" + $("form#model").id() + "\\]\\.associateModel\\[" + id + "\\].+").test($(this).attr("name"));
	        	});
            	$("#associated-subjects-form select:first").find("option").filter(function(){
            		return $(this).val() === $(elems).filter(function() { 
    	        		return /^ltLeadsModel\[\d+\]\.associateModel\[\d+\]\.value$/.test($(this).attr("name"));
    	        	}).val();
            	}).removeAttr("disabled");
	        	$.each(elems,function(i) {
	        		$(this).remove();
	        	});

	        	//reset expand-collapse div
            	$("#associated-subjects-form").find(".expand-collapse-div-" + id).remove();
            	$("#associated-subjects-form .expand-collapse-div").each(function(i){
            		$(this).attr("class",$(this).attr("class").replace(/(.*\sexpand\-collapse\-div\-)\d+/, "$1" + i));
                	$("#associated-subjects-form .expand-collapse-div-" + i).attachEvent("click", 'input[name="delete"]', function(e) {
                		$(".associated-subjects-data-table").deleteRow(i, function(e) {
                        	$.associates.destroy(i);
                        });
                        e.preventDefault();
                    });
            	});
            	//reset table
            	$("#associated-subjects-form .associated-subjects-data-table tr td:last input[name='row']").each(function(i){
            		$(this).val(i);
            	});
            	//reset model
	        	$.associates.model.find("select").filter(function(){
	        		return new RegExp("ltLeadsModel\\[" + $("form#model").id() + "\\]\\.associateModel\\[\\d+]\\.value").test($(this).attr("name"));
	        	}).each(function(i){
		        	$.associates.model.find("select").filter(function(){
		        		return new RegExp("ltLeadsModel\\[" + $("form#model").id() + "\\]\\.associateModel\\[\\d+].+").test($(this).attr("name"));
		        	}).each(function(){
		        		$(this).attr("name",$(this).attr("name").replace(/(\w+\[\d+\].*)\[\d+\](.*)/, "$1[" + i + "]$2"));	
		        	});
		        });

	        	//reset form
            	this.reset();
        	},
		    show : function(id) {
	    		console.log("show() " + id);
	    		//reset form id
	            $("#associated-subjects-form").children('input[type="hidden"][name="id"]').val(id + ":false");
	            //find all elements where name begins with ltLeadsModel[id].associateModel[id]
	            var formId = $("form#model").id();
	        	var elems = $.associates.model.find("select").filter(function() { 
	        		return new RegExp("ltLeadsModel\\[" + formId + "\\]\\.associateModel\\[" + id + "\\].+").test($(this).attr("name"));
	        	});
            	$("#associated-subjects-form select:first").find("option").filter(function(){
            		return $(this).val() === $(elems).filter(function() { 
    	        		return /^ltLeadsModel\[\d+\]\.associateModel\[\d+\]\.value$/.test($(this).attr("name"));
    	        	}).val();
            	}).removeAttr("disabled");
	            
	            //loop thru elements to retrieve its value
	            $.each(elems,function() {
	            	var name = $(this).attr("name").replace(/(\w+)\[\d+\](.*)\[\d+\](.*)/, "$1[0]$2[0]$3");
	                //set elements value into form value
	            	$("#associated-subjects-form").find('select[name="' + name + '"]').val($(this).val());
	            });
	            //show update/delete button, hide add button
	            $("#associated-subjects-form").find('input[name="add"]').addClass("hidden").fadeOut();
	            $("#associated-subjects-form").children("div.buttons").find('input[name="update"],input[name="delete"]').removeClass("hidden").hide().fadeIn();
		    },
		    expand : function() {
		    	var id = $("#associated-subjects-form").id();
		    	console.log("$.associates.expand() " + id + ":" + $("#associated-subjects-form").isNew());

            	$("#associated-subjects-form").expand();
            	$("#associated-subjects-form .expand-collapse-div-"+id).find("span.expand-collapse-link a").attr("id", "collapseAssociatedLeadLink-" + id);
            	$("#associated-subjects-form .expand-collapse-div-"+id).find("span.expand-collapse-link input[name='delete']").attr("id", "deleteAssociatedLeadButton-" + id);

            	$("#associated-subjects-form .expand-collapse-div-"+id).attachEvent("click", 'input[name="delete"]', function(e) {
            		$(".associated-subjects-data-table").deleteRow(id, function(e) {
                    	$.associates.destroy(id);
                    });
                    e.preventDefault();
                });

            	$("#associated-subjects-form .expand-collapse-div-" + id).find("input:eq(0)").val(this.subject());
            	$("#associated-subjects-form .expand-collapse-div-" + id).find("input:eq(1)").val(this.relationship());
		    },
		    notify : function(id) {
		    	console.log("$.associates.notify() " + id);

		    	this.reset(true);

	        	$.associates.model.find("select").filter(function() { 
	        		return new RegExp("ltLeadsModel\\[" + id + "\\]\\.associateModel\\[\\d+].+").test($(this).attr("name"));
	        	}).each(function() {
		        	var name = $(this).attr("name").replace(/(\w+)\[\d+\](.*)\[\d+\](.*)/, "$1[0]$2[0]$3");
		        	if(/^ltLeadsModel\[0\]\.associateModel\[0\]\.value$/.test(name)) {
		        		$("#associated-subjects-form").find('select[name="ltLeadsModel[0].associateModel[0].value"]:first').val($(this).val());
		        		$("#associated-subjects-form").find('select[name="ltLeadsModel[0].associateModel[0].relationshipCode.abbreviation"]:first').val($(this).next().val());

		            	$("#associated-subjects-form select:first option:selected").attr("disabled","disabled");
		        		$.when($.associates.table()).then($.associates.expand());

	                    $("#associated-subjects-form").find("select").prop("selectedIndex", 0);
	    		        var id = parseInt($(".associated-subjects-data-table").find("tbody tr").length);
	    		        $("#associated-subjects-form").find('input[type="hidden"][name="id"]').val(id+":true");
		        	}
		        });

	        	//subject cannot be an associate
	        	$("#associated-subjects-form").find('select:first option').filter(function() {
	        		return $(this).val() === id;
	        	}).attr("disabled","disabled");
		    },
		    reset : function(all) {
				//reset subject form
			    $("#associated-subjects-form").find("select").prop("selectedIndex", 0);
            	if($(".associated-subjects-data-table").find("tbody tr").length === 0) {
            		$("#associated-subjects-form").find("span.expand-collapse-link").addClass("hidden").fadeOut();
            	}

                $("#associated-subjects-form").find('input[name="add"]').removeClass("hidden").hide().fadeIn();
                $("#associated-subjects-form").find('input[name="update"],input[name="delete"]').not(".expand-collapse input[name='delete']").addClass("hidden").fadeOut();

			    if(all) {
			    	$("#associated-subjects-form").find("select option").removeAttr("disabled");
				    $("#associated-subjects-form").find(":input").not("input[type=button]").val("");
				    $("#associated-subjects-form").find(".expand-collapse-div").remove();
				    $("#associated-subjects-form").find(".expand-collapse-link").addClass("hidden").fadeOut();
				    $("#associated-subjects-form").find($(".associated-subjects-data-table")).find("tbody tr").remove();
				    $("#associated-subjects-form").find($(".associated-subjects-data-table")).find("table").fadeOut();
				    //$.associates.model.find(":input").remove();
			    }

			    $("#associated-subjects-form").find("select:first").focus();
		        var id = parseInt($(".associated-subjects-data-table").find("tbody tr").length);
		        $("#associated-subjects-form").find('input[type="hidden"][name="id"]').val(id+":true");
		    },
		    table : function(e) {
                var t = $(".associated-subjects-data-table").dataTable();
                var data = ['<a href="javascript:;">' + this.subject() + '</a>', this.relationship()];
                var id = $("#associated-subjects-form").id();
                if($("#associated-subjects-form").isNew()) {
	                $(t).insertRow(id, data, function(e) {
	                	linkEvent(e);
	                	deleteEvent(e);
	                });
                } else  {
	                $(t).updateRow(id, data, function(e){
	                	linkEvent(e);
	                });
                }
                function deleteEvent(e){
                	$(".associated-subjects-data-table").attachEvent("click", 'tbody tr td input[type="image"][name="del"]', function(e){
                        var row = $(this).siblings('input[type="hidden"][name="row"]').val();
                        $(".associated-subjects-data-table").deleteRow(row, function(e) {
    	            		$("#associated-subjects-form").find(".expand-collapse-div-" + row).remove();
                        	$.associates.destroy(row);
                        });
                        e.preventDefault();
                    });
                }
                function linkEvent(e){
                	$(".associated-subjects-data-table").attachEvent("click", "tbody tr td a", function(e){
                        var row = $(this).parent().siblings("td:last").find('input[type="hidden"][name="row"]').val();
	                    $.associates.reset();
	                    $.associates.show(row);
	                    e.preventDefault();
                    });
                }
		    },
		    checkForm : function() {
				var valid = false;
				console.log("$.associates.checkForm() : " + $("#associated-subjects-form select:first option:selected").is(":disabled"));
				if(!$("#associated-subjects-form select:first option:selected").is(":disabled") && this.subject() !== "" && this.relationship() !== "") {
					valid = true;
				}
				return valid;
		    },
		    isBlank : function() {
				var blank = !this.checkForm();
				return blank;
		    },
		    subject : function() {
				var name = $("#associated-subjects-form").find('select[name="ltLeadsModel[0].associateModel[0].value"]:first option:selected').text();
				return name === "Select..." ? "" : name;
		    },
		    relationship : function() {
				var relation = $("#associated-subjects-form").find('select[name="ltLeadsModel[0].associateModel[0].relationshipCode.abbreviation"]:first option:selected').text();
				return relation === "Select..." ? "" : relation;
		    }
		};
})(jQuery);

$(function() {
	$("#associated-subjects-form").append($('<input type="hidden" name="id" />').val("0:true"));
	$("#associated-subjects-form").find('input[name="add"],input[name="update"]').on("click",function(e){
		if($.associates.checkForm()) {
			console.log("add/update");
            $.associates.save(e);
		}
        e.preventDefault();
    });
	$("#associated-subjects-form").find('input[name="delete"]').on("click",function(e){
		console.log("delete");
		var id = $("#associated-subjects-form").id();
		$(".associated-subjects-data-table").deleteRow(id, function(e) {
        	$.associates.destroy(id);
        });
        e.preventDefault();
    });
	$("#associated-subjects-form").find('a[title="Cancel associated lead"]').on("click", function(e) {
    	console.log("cancel");
    	$.associates.reset();
    	e.preventDefault();
    });
});
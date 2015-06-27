//TODO add comments
(function($){

	$.numbers = {
			model : $("<form></form>"),

	        save : function(e) {
	        	var elems = $("#identifying-numbers-form").find("input,select,textarea").filter(function(){
	        		return /^ltLeadsModel\[\d+\]\.identifyingNumberModel\[\d+\].+$/.test($(this).attr("name"));
	        	});
	        	console.log(elems);
	            elems.each(function(){
	            	var name = $(this).attr("name").replace(/(\w+)\[\d+\](.*)\[\d+\](.*)/, "$1[" + $("form#model").id() + "]$2[" + $("#identifying-numbers-form").id() + "]$3");
	            	console.log("$.numbers.save() " + name);
	        		if($.numbers.model.find(':input[name="' + name + '"]').length === 0) {
	        			$($(this).clone()).attr("name",name).val($(this).val()).removeAttr("disabled").appendTo($.numbers.model);
	        		} else {
	        			$.numbers.model.find(':input[name="' + name + '"]').val($(this).val()).removeAttr("disabled");
	        		}
	            });
	        	console.log($.numbers.model);

            	$("#identifying-numbers-form select:first option:selected").filter(function(){
            		return $(this).text() === "ADIS PID" || $(this).text() === "FIN" || $(this).text() === "LSID";
            	}).attr("disabled","disabled");

                $.when(this.table(e)).then(this.expand()).then(this.reset());
	        },
	        destroy : function(id) {
	        	console.log("$.numbers.destroy() : " + id);
	        	var elems = $.numbers.model.find("select").filter(function() { 
	        		return new RegExp("ltLeadsModel\\[" + $("form#model").id() + "\\]\\.identifyingNumberModel\\[" + id + "\\].+").test($(this).attr("name"));
	        	});
            	$("#identifying-numbers-form select:first").find("option").filter(function(){
            		return $(this).val() === $(elems).filter(function() { 
    	        		return /^ltLeadsModel\[\d+\]\.identifyingNumberModel\[\d+\]\.value$/.test($(this).attr("name"));
    	        	}).val();
            	}).removeAttr("disabled");
	        	$.each(elems,function(i) {
	        		$(this).remove();
	        	});

	        	//reset expand-collapse div
            	$("#identifying-numbers-form").find(".expand-collapse-div-" + id).remove();
            	$("#identifying-numbers-form .expand-collapse-div").each(function(i){
            		$(this).attr("class",$(this).attr("class").replace(/(.*\sexpand\-collapse\-div\-)\d+/, "$1" + i));
                	$("#identifying-numbers-form .expand-collapse-div-" + i).attachEvent("click", 'input[name="delete"]', function(e) {
                		$(".identifying-numbers-data-table").deleteRow(i, function(e) {
                        	$.numbers.destroy(i);
                        });
                        e.preventDefault();
                    });
            	});
            	//reset table
            	$("#identifying-numbers-form .identifying-numbers-data-table tr td:last input[name='row']").each(function(i){
            		$(this).val(i);
            	});
            	//reset model
	        	$.numbers.model.find("select").filter(function(){
	        		return new RegExp("ltLeadsModel\\[" + $("form#model").id() + "\\]\\.identifyingNumberModel\\[\\d+]\\.value").test($(this).attr("name"));
	        	}).each(function(i){
		        	$.numbers.model.find("select").filter(function(){
		        		return new RegExp("ltLeadsModel\\[" + $("form#model").id() + "\\]\\.identifyingNumberModel\\[\\d+].+").test($(this).attr("name"));
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
	            $("#identifying-numbers-form").children('input[type="hidden"][name="id"]').val(id + ":false");
	            //find all elements where name begins with ltLeadsModel[id].associateModel[id]
	            var formId = $("form#model").id();
	        	var elems = $.numbers.model.find("select").filter(function() { 
	        		return new RegExp("ltLeadsModel\\[" + formId + "\\]\\.identifyingNumberModel\\[" + id + "\\].+").test($(this).attr("name"));
	        	});
            	$("#identifying-numbers-form select:first").find("option").filter(function(){
            		return $(this).val() === $(elems).filter(function() { 
    	        		return /^ltLeadsModel\[\d+\]\.identifyingNumberModel\[\d+\]\.value$/.test($(this).attr("name"));
    	        	}).val();
            	}).removeAttr("disabled");
	            
	            //loop thru elements to retrieve its value
	            $.each(elems,function() {
	            	var name = $(this).attr("name").replace(/(\w+)\[\d+\](.*)\[\d+\](.*)/, "$1[0]$2[0]$3");
	                //set elements value into form value
	            	$("#identifying-numbers-form").find('select[name="' + name + '"]').val($(this).val());
	            });
	            //show update/delete button, hide add button
	            $("#identifying-numbers-form").find('input[name="add"]').addClass("hidden").fadeOut();
	            $("#identifying-numbers-form").children("div.buttons").find('input[name="update"],input[name="delete"]').removeClass("hidden").hide().fadeIn();
		    },
		    expand : function() {
		    	var id = $("#identifying-numbers-form").id();
		    	console.log("$.numbers.expand() " + id + ":" + $("#identifying-numbers-form").isNew());

            	$("#identifying-numbers-form").expand();
            	$("#identifying-numbers-form .expand-collapse-div-"+id).find("span.expand-collapse-link a").attr("id", "collapseIdentifyingNumberLink-" + id);
            	$("#identifying-numbers-form .expand-collapse-div-"+id).find("span.expand-collapse-link input[name='delete']").attr("id", "deleteIdentifyingNumberButton-" + id);

            	$("#identifying-numbers-form .expand-collapse-div-"+id).attachEvent("click", 'input[name="delete"]', function(e) {
            		$(".identifying-numbers-data-table").deleteRow(id, function(e) {
                    	$.numbers.destroy(id);
                    });
                    e.preventDefault();
                });

            	//TODO populate expand-collapse-div
            	//$("#identifying-numbers-form .expand-collapse-div-" + id).find("input:eq(0)").val(this.subject());
            	//$("#identifying-numbers-form .expand-collapse-div-" + id).find("input:eq(1)").val(this.relationship());
		    },
		    notify : function(id) {
		    	console.log("$.numbers.notify() " + id);

		    	this.reset(true);

	        	$.numbers.model.find("select").filter(function() { 
	        		return new RegExp("ltLeadsModel\\[" + id + "\\]\\.identifyingNumberModel\\[\\d+].+").test($(this).attr("name"));
	        	}).each(function() {
		        	var name = $(this).attr("name").replace(/(\w+)\[\d+\](.*)\[\d+\](.*)/, "$1[0]$2[0]$3");
		        	if(/^ltLeadsModel\[0\]\.identifyingNumberModel\[0\]\.value$/.test(name)) {
		        		//TODO populate identifying numbers section
//		        		$("#identifying-numbers-form").find('select[name="ltLeadsModel[0].identifyingNumberModel[0].value"]:first').val($(this).val());
		        		//$("#identifying-numbers-form").find('select[name="ltLeadsModel[0].identifyingNumberModel[0].relationshipCode.abbreviation"]:first').val($(this).next().val());

//		            	$("#identifying-numbers-form select:first option:selected").attr("disabled","disabled");
		        		$.when($.numbers.table()).then($.numbers.expand());

	                    $("#identifying-numbers-form").find("select").prop("selectedIndex", 0);
	    		        var id = parseInt($(".identifying-numbers-data-table").find("tbody tr").length);
	    		        $("#identifying-numbers-form").find('input[type="hidden"][name="id"]').val(id+":true");
		        	}
		        });

	        	//subject cannot be an associate
//	        	$("#identifying-numbers-form").find('select:first option').filter(function() {
//	        		return $(this).val() === id;
//	        	}).attr("disabled","disabled");
		    },
		    reset : function(all) {
				//reset subject form
			    $("#identifying-numbers-form").find("select").prop("selectedIndex", 0);
            	if($(".identifying-numbers-data-table").find("tbody tr").length === 0) {
            		$("#identifying-numbers-form").find("span.expand-collapse-link").addClass("hidden").fadeOut();
            	}

                $("#identifying-numbers-form").find('input[name="add"]').removeClass("hidden").hide().fadeIn();
                $("#identifying-numbers-form").find('input[name="update"],input[name="delete"]').not(".expand-collapse input[name='delete']").addClass("hidden").fadeOut();

			    if(all) {
			    	$("#identifying-numbers-form").find("select option").removeAttr("disabled");
				    $("#identifying-numbers-form").find(":input").not("input[type=button]").val("");
				    $("#identifying-numbers-form").find(".expand-collapse-div").remove();
				    $("#identifying-numbers-form").find(".expand-collapse-link").addClass("hidden").fadeOut();
				    $("#identifying-numbers-form").find($(".identifying-numbers-data-table")).find("tbody tr").remove();
				    $("#identifying-numbers-form").find($(".identifying-numbers-data-table")).find("table").fadeOut();
			    }

		        var id = parseInt($(".identifying-numbers-data-table").find("tbody tr").length);
		        $("#identifying-numbers-form").find('input[type="hidden"][name="id"]').val(id+":true");
		    },
		    table : function(e) {
                var t = $(".identifying-numbers-data-table").dataTable();
                var data = this.data();
                data = ['<a href="#">' + data[0] + '</a>', data[1], data[2], data[3], data[4], data[5], data[6]];

                var id = $("#identifying-numbers-form").id();
                if($("#identifying-numbers-form").isNew()) {
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
                	$(".identifying-numbers-data-table").attachEvent("click", 'tbody tr td input[type="image"][name="del"]', function(e){
                        var row = $(this).siblings('input[type="hidden"][name="row"]').val();
                        $(".identifying-numbers-data-table").deleteRow(row, function(e) {
    	            		$("#identifying-numbers-form").find(".expand-collapse-div-" + row).remove();
                        	$.numbers.destroy(row);
                        });
                        e.preventDefault();
                    });
                }
                function linkEvent(e){
                	$(".identifying-numbers-data-table").attachEvent("click", "tbody tr td a", function(e){
                        var row = $(this).parent().siblings("td:last").find('input[type="hidden"][name="row"]').val();
	                    $.numbers.reset();
	                    $.numbers.show(row);
                    });
                }
		    },
		    isValid : function() {
				var valid = false;
				if(this.data[0] !== "" && this.data[1] !== "") {
					valid = true;
				}
				return valid;
		    },
		    isBlank : function() {
				var blank = !this.isValid();
				return blank;
		    },
		    data : function(i) {
		    	var arr  = [];
		    	switch(i){
		    		case 0 : 
		    			arr.push($("#identifing-numbers-form").find('select[name="ltLeadsModel[0].identifyingNumberModel[0].ltIdentifierNumber.numberTypeCode.abbreviation"] option:selected').text());
		    		break;
		    		case 1 :
		    			arr.push($("#identifing-numbers-form").find('input[name="ltLeadsModel[0].identifyingNumberModel[0].ltIdentifierNumber.identifyingNumber"]').val());
		    		break;
		    		case 2 : 
		    			arr.push($("#identifing-numbers-form").find('select[name="ltLeadsModel[0].identifyingNumberModel[0].ltIdentifierNumber.countryCode.abbreviation"] option:selected').text());
		    		break;
		    		case 3 : 
		    			arr.push($("#identifing-numbers-form").find('select[name="ltLeadsModel[0].identifyingNumberModel[0].ltIdentifierNumber.stateProvinceCode.abbreviation"] option:selected').text());
		    		break;
		    		case 4 : 
		    			arr.push($("#identifing-numbers-form").find('input[name="ltLeadsModel[0].identifyingNumberModel[0].issueDate.value"]').val());
		    		break;
		    		case 5 : 
		    			arr.push($("#identifing-numbers-form").find('input[name="ltLeadsModel[0].identifyingNumberModel[0].expirationDate.value"]').val());
		    		break;
		    		case 6 : 
		    			arr.push($("#identifing-numbers-form").find('select[name="ltLeadsModel[0].identifyingNumberModel[0].sourceCodes.sourceCode.abbreviation"]').val());
		    		break;
		    	}
		    	console.log("$.numbers.data " + i);
		    	console.log(arr);
		    	return arr;
		    }
		};
})(jQuery);

$(function() {
	$("#identifying-numbers-form").append($('<input type="hidden" name="id" />').val("0:true"));
	$("#identifying-numbers-form").find('input[name="add"],input[name="update"]').on("click",function(e){
		if($.numbers.isValid() && !$.numbers.isBlank()) {
			console.log("add/update");
            $.numbers.save(e);
		}
        e.preventDefault();
    });
	$("#identifying-numbers-form").find('input[name="delete"]').on("click",function(e){
		console.log("delete");
		var id = $("#identifying-numbers-form").id();
		$(".identifying-numbers-data-table").deleteRow(id, function(e) {
        	$.numbers.destroy(id);
        });
        e.preventDefault();
    });
	$("#identifying-numbers-form").find('a[title="Cancel identifying number"]').on("click", function(e) {
    	console.log("cancel");
    	$.numbers.reset();
    	e.preventDefault();
    });
	$("#identifying-numbers-form").find('select:first').on("change", function(e) {
    	console.log("change");
    	if($(this).val() !== "Select...") {
	    	$("#identifying-numbers-form .identifying-numbers-group").removeClass("hidden").hide().fadeIn();
	    	$("#identifying-numbers-form .identifying-numbers-group :input").parent().addClass("hidden").fadeOut();
	    	var data = $.numbers.data();
	    	var type = data[0].toLowerCase().replace("/\s+/","-");
	    	console.log("$.numbers->change " + type);
	    	$("#identifying-numbers-form").find(".type").removeClass("hidden").hide().fadeIn();
	    	$("#identifying-numbers-form").find("input,select").filter(function() {
	    		return /^ltLeadsModel\[\d+\]\.identifyingNumberModel\[\d+\].+$/.test($(this).attr("name")) && $(this).hasClass(type);
	    	}).removeClass("hidden").hide().fadeIn();
	    	//show source and comments
	    	$("#identifying-numbers-form .identifying-numbers.column-two .identifying-numbers-group").removeClass("hidden").hide().fadeIn();

	    	//reset source
	    	$("#identifying-numbers-form .identifying-numbers.column-two .identifying-numbers-group select:first").prop("selectedIndex", -1);
	    	$("#identifying-numbers-form .multiselect").remove();

	    	//TODO default source
	    	var source = "";
	    	if(type === "adis-pid") {
	    		source = "ADIS";
	    	} else if(type === "enforce-event") {
	    		source = "ENFORCE";
	    	} else if(type === "lsid") {
	    		source = "LSID";
	    	} else if(type === "tecs-case") {
	    		source = "TECS";
	    	} else if(type === "tecs-ilog") {
	    		source = "TECS";
	    	} else if(type === "tecs-subject-record") {
	    		source = "TECS";
	    	}
	    	console.log("source: " + source);
    		$("#identifying-numbers-form .identifying-numbers.column-two .identifying-numbers-group select:first option").filter(function(){
    			return $(this).text() === source;
    		}).attr("selected", "selected");
    	} else {
    		$("#identifying-numbers-form .identifying-numbers-group").addClass("hidden").fadeOut();
    	}
    	e.preventDefault();
    });
});
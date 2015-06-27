//TODO add comments
$(function(){
	$("form input:first").focus();
	// --------------------------------------------------------------------------------
	// ----------- Source Contact Type validation
	// --------------------------------------------------------------------------------
    $("#leadSourceContact").on("focus",function() {
        $(this).removeClass('placeholder error');
        if ($(this).val() === $.leadtrac.emailMask) {
            $(this).val("");
        }
    });
    $("#leadSourceContact").on("blur", function() {
        var opt = $(this).parent().prev().find("option:selected");
        if ($(opt).text() === "Phone") {
        	if($.trim($(this).val()) === $.leadtrac.phoneBlank) {
        		$(this).val("");
        	} if($.trim($(this).val()) === "") {
        		$(this).checkRequired();
        	} else {
        		$(this).checkPhone();
        	}
        }
        if ($(opt).text() === "E-mail") {
        	if($.trim($(this).val()) === "") {
        		$(this).checkRequired();
        	} else {
	            $(this).checkEmail();
        	}
        }
        //console.log("source contact [" + $(opt).text() +"] val[" + $(this).val() +"]");
    });
    $("#leadSourceContactType").on("change", function() {
        $(input).removeClass('placeholder error');
        var input = $(this).parent().next().children("input:first");
        var opt = $(this).children("option:selected");
    	$(input).val("");
        if ($(opt).text() === "Phone") {
            $(input).mask($.leadtrac.phoneMask);
            $(input).checkPhone();
        } else if ($(opt).text() === "E-mail") {
            $(input).unmask($.leadtrac.phoneMask);
            if($.trim($(input).val()) === "") {
                $(input).addClass('placeholder').val($.leadtrac.emailMask);
                $(input).hideError();
            } else {
                $(input).checkEmail();
            }
        } else {
        	$(input).unmask($.leadtrac.phoneMask).unmask($.leadtrac.emailMask).val("");
        }
    });

    // --------------------------------------------------------------------------------
    // ----------- Subject DOB & Entry Date validation
    // --------------------------------------------------------------------------------
    $("input.date.dob").off("blur").on("blur",function() {
        if ($(this).val() === '__/__/____') {
            $(this).val('');
            $(this).hideError();
        } else if(!regexFormatted.test($(this).val())) {
        	$(this).checkDateFormat();
        } else {
        	$.when($(this).checkCurrentDate())
        	.then($(this).checkBeforeDate($("input.date.entrydate")));
        }
    });

    $("input.date.entrydate").off("blur").on("blur",function() {
        if ($(this).val() === '__/__/____') {
            $(this).val('');
            $(this).hideError();
        } else if(!regexFormatted.test($(this).val())) {
        	$(this).checkDateFormat();
        } else {
        	$.when($(this).checkCurrentDate())
        	.then($(this).checkAfterDate($("input.date.dob")));
        }
    });

 // --------------------------------------------------------------------------------
 // ----------- Form submission
 // --------------------------------------------------------------------------------
    $("form#model").append($('<input type="hidden" name="id" />').val("0:true"));
    $("#formId").val($("form#model").id());
    $('.initiate-lead-buttons input[name="add"]').on("click",function(e){
		console.log("add");
		if(isValidForm()) {
	    	copyFormToModel();
	    	resetForm();
		}
        e.preventDefault();
    });
    $('.initiate-lead-buttons input[name="update"]').on("click",function(e){
		console.log("update");
    	if(isValidForm()) {
	    	copyFormToModel();
	    	resetForm();
    	}
        e.preventDefault();
    });
    $('.initiate-lead-buttons input[name="delete"]').on("click",function(e){
    	if(isValidForm()) {
			//console.log("delete");
			var id = $("form#model").id();
			$(".lead-subject-data-table").deleteRow(id, function(e) {
	        	$.model.destroy(id,function(e){
	                var newid = parseInt($(".lead-subject-data-table tbody tr").length);
	        		//console.log('save->insertRow->fn(e)->attachEvent()->deleteRow()->$.model.destroy(' + id + ') newid:' + newid);
	        		//TODO remove from associated leads
	        		$(".associated-subjects select:first option[value='"+id+"']").remove();
	            	resetForm(true);
	        	});
	        });
    	}
        e.preventDefault();
    });
    $('a[title="Cancel lead information"]').on("click", function(e) {
    	//console.log("cancel");
    	resetForm(true);
    });

    $('input[value="Initiate"]').on("click", function(e) {
    	if(isBlankForm() || isValidForm()) {
    		copyFormToModel();
    		resetForm(true);
        	var text = confirmText();
        	//console.log("initiate [" + text + "]");
            $(this).confirmation("Initiate Lead(s)",text,function(e,t) {
                switch (t) {
                    case "CONFIRM": case "OK":
                        // autoSave : function(form, data, success, dataType)
                        /* get some values from elements on the page: */
                    	$.model.autoSave(function(r) {
                            //console.log("autosave()->callback->");
                            //console.log(r);
                            postSave(r);
                        });
                        e.preventDefault(); break;
                    default :
                }
                e.preventDefault();
            });
    	}
        /* stop form from submitting normally */
        e.preventDefault();
    });

    $('a[title="Cancel Initiation"]').on("click", function(e) {
    	var message = "Are you sure you want to cancel initiation?<br/><strong>All data will be lost.</strong>";
        $(this).confirmation("Cancel Confirmation", message, function(e,t) {
            switch (t) {
                case "CONFIRM": case "OK":
                    self.location.href = "/leadtrac-web/workQueue"; break;
                case "CANCEL": // do nothing
                default:
            }
        });
    });

	function assignTo(){
		return $(".assign-to option.active").text();
	}
	function source(){
		return $("form#model").find('input[name="ltLeadsModel[0].ltLeadSource.name"]').val();
	}
	function leadType(){
		return $("form#model").find('select[name="ltLeadsModel[0].ltLead.leadTypeCode.abbreviation"] option:selected').text();
	}
	function birthDate(){
		return $("form#model").find('input[name="ltLeadsModel[0].birthDateModel.value"]').val();
	}
	function gender(){
		var gender = $("form#model").find('select[name="ltLeadsModel[0].ltLead.ltSubject.genderCode.abbreviation"] option:selected').text();
		return gender === "Select..." ? "" : gender;
	}
	function fullname(){
	    var name1 = $("form#model").find('input[name="ltLeadsModel[0].ltLead.ltSubject.lastname"]').val();
	    var name2 = $("form#model").find('input[name="ltLeadsModel[0].ltLead.ltSubject.firstname"]').val();
		return name1 + ", " + name2;
	}
	function resetForm(all) {
		//reset subject form
		$("#subjectId").val("");
	    $("#subject-information-form :input").val("");
	    $("#subject-information-form select").prop("selectedIndex", 0);
	    $("#subject-information-form .multiselect select option").removeAttr("disabled");
	    $("#subject-information-form span.multiselect select").prop("selectedIndex", 0);
	    $("#subject-information-form span.multiselect").remove();

	    //reset associated subjects
	    $.associates.reset(true);
	    $.numbers.reset(true);

	    //reset identifying numbers fields
	    $("#identifying-numbers-form select").prop("selectedIndex", 0);
	    $("#identifying-numbers-form :input").not("input[type=button]").val("");
	    $("#identifying-numbers-form .multiselect select option").removeAttr("disabled");
	    $("#identifying-numbers-form span.multiselect select").prop("selectedIndex", 0);
	    $("#identifying-numbers-form span.multiselect").remove();
	    $("#identifying-numbers-data-table tbody tr").remove();

	    //reset assign-to
	    $("#assign-to-form select").prop("selectedIndex", 0);
	    $("#assign-to-form label").removeClass("active");
	    $("#assign-to-form select.mission").assignTo("mission", "None");

	    //reset error fields
	    $("span.error").fadeOut().hide().addClass("hidden");
	    $("#subject-information-form :input").removeClass("error");

	    $("#subject-information-form :input:disabled").removeClass("readonly").removeAttr("disabled");

	    //reset lead form
	    if(typeof all !== "undefined" && all === true) {
		    $("#lead-information-form :input").val("");
		    $("#lead-information-form select").prop("selectedIndex", 0);
		    $("#lead-information-form :input").removeClass("error");
	    }

        var id = parseInt($(".lead-subject-data-table tbody tr").length);
        //console.log("cancel id: " + id);
        $("form#model").children('input[type="hidden"][name="id"]').val(id+":true");
        $("#formId").val($("form#model").id());

        $('.initiate-lead-buttons input[name="add"]').removeClass("hidden").hide().fadeIn();
        $('.initiate-lead-buttons input[name="update"]').addClass("hidden").fadeOut();
        $('.initiate-lead-buttons input[name="delete"]').addClass("hidden").fadeOut();

        $("#subject-information-form input#lsid").focus();
	}
	function showForm(id) {
		//console.log("showForm() " + id);
		//reset form id
        $("form#model").children('input[type="hidden"][name="id"]').val(id + ":false");
        $("#formId").val($("form#model").id());
        //find all elements where name begins with ltLeadsModel[id]
        var elems = $.model.children().filter(function(){
    		return new RegExp("ltLeadsModel\\[" + id + "\\].+").test($(this).attr("name"));
    	});
        //loop thru elements to retrieve its value
        $.each(elems,function(i,el){
        	var name = $(el).attr("name");
        	name = name.replace(/(\w+)\[\d\](.*)/, "$1[0]$2");
        	//console.log("show() " + $(el).attr("name") + " : " + $(el).val());
            //set elements value into form value
        	var elem = $("form#model").find("input,select,textarea").filter(function(){
        		return ($(this).attr("name") === name);
        	});

            //multiselect
        	//ltLeadsModel[0].ltSubjectCitizenshipCountries[0].countryCode.abbreviation
        	if(/^ltLeadsModel\[0\]\.ltSubjectCitizenshipCountries.+$/.test(name) && $(el).val() !== "Select...") {
        		elem = $("form#model").find('select[name="ltLeadsModel[0].ltSubjectCitizenshipCountries[0].countryCode.abbreviation"]');
            	$(elem).val($(el).val());
            	if($.model.find('select[name^="ltLeadsModel['+id+'].ltSubjectCitizenshipCountries"]').length > 1) {
            		$(elem).prev().trigger("click");
            	}
            }

        	//ltLeadsModel[0].ltLeadSpecialProjects[0].specialProjectCode.abbreviation
        	else if(/^ltLeadsModel\[0\]\.ltLeadSpecialProjects.+$/.test(name) && $(el).val() !== "Select...") {
        		elem = $("form#model").find('select[name="ltLeadsModel[0].ltLeadSpecialProjects[0].specialProjectCode.abbreviation"]');
            	$(elem).val($(el).val());
            	var span = $("form#model div#specialProjects span.multiselect");
            	var sels = $.model.find('select[name^="ltLeadsModel['+id+'].ltLeadSpecialProjects"]');
            	if(sels.length > (span.length+1)) {
            		$(elem).prev().trigger("click");
            	}
        	}

            //set assign-to active
        	else if(/^ltLeadsModel\[0\]\.(supervisorModel|analystModel|ltLead\.missionCode).+$/.test(name) && $.trim($(el).val()) !== "") {
        		//console.log("show() Assign To field");
                $("#assign-to-form label, #assign-to-form option").removeClass("active");

            	if(name === "ltLeadsModel[0].supervisorModel.value" && $.trim($(el).val()) !== "") {
            		$(elem).assignTo("mission", "None");
                    $(elem).assignTo("supervisor", $(el).val());
            	} else if(name === "ltLeadsModel[0].analystModel.value" && $.trim($(el).val()) !== "") {
            		$(elem).assignTo("mission", "None");
            	} else if(name === "ltLeadsModel[0].ltLead.missionCode.abbreviation") {
                    $(elem).assignTo('mission', $(el).val().split(":")[0]);
            	}
        		$(elem).val($(el).val());
                $(elem).prev().addClass("active").next().find("option:selected").addClass("active");
        	}

            //associated leads
        	else if(/^ltLeadsModel\[0\]\.associateModel.+$/.test(name)) {
        		//ignore
        	}

        	//other fields
        	else {
        		$(elem).val($(el).val());
        		if($(elem).hasClass("required")) { $(elem).checkRequired(); }
        		if($(elem).hasClass("date")) { $(elem).checkDate(); }
        	}
        });

        //associated leads
        $.associates.notify(id);
        //identifying numbers
        $.numbers.notify(id);

        if($.trim($("#subjectId").val()) !== "") {
        	$("#lsid,#subjectSurname,#subjectGivenName").attr("disabled","disabled").addClass("readonly");
        }
        //show update/delete button, hide add button
        $('.initiate-lead-buttons input[name="add"]').addClass("hidden").fadeOut();
        $('.initiate-lead-buttons input[name="update"]').removeClass("hidden").hide().fadeIn();
        $('.initiate-lead-buttons input[name="delete"]').removeClass("hidden").hide().fadeIn();
	}
	function confirmText(){
		var text = '<ul class="message">';
		if($(".lead-subject-data-table tbody tr").length === 0) { //single leads, maybe
			text += fullname();
		} else { //multiple leads, probably
	    	$.each($(".lead-subject-data-table tbody tr td:first-child"), function(i){
	    		text += "<li style='width:100%'>" + $(this).find("a").html() + "</li>";
	    	});
		}
		text += "</ul>";
		//console.log("confirmText() " + text);
		return text;
	}

	function copyFormToModel(){
		//console.log("copyFormToModel() " + isBlankForm());
		if(!isBlankForm()){

    	if($("form#model").isNew()) {
            $("form#model").save(function(e){
                var table = $(".lead-subject-data-table").dataTable();
                var data = ['<a href="#">'+fullname()+'</a>', gender(), birthDate(), leadType(), source(), assignTo()];
                $(table).insertRow($("form#model").id(),data,function(e) {
                    //console.log('save->insertRow->fn(e)');
                    $(table).attachEvent("click", "tbody tr td a", function(e){
                        var row = $(this).parent().siblings("td:last").find('input[type="hidden"][name="row"]').val();
	                    //console.log("save->insertRow->fn(e)->attachEvent()->fn(e) row[" + row + "]");
	                    resetForm();
	                    showForm(row);
                        e.preventDefault();
                    });
                    $(table).attachEvent("click", 'tbody tr td input[type="image"][name="del"]', function(e){
                        var row = $(this).siblings('input[type="hidden"][name="row"]').val();
	                        //console.log('save->insertRow->fn(e)->attachEvent()->fn(e) row['+ row +']');
	                        $(".lead-subject-data-table").deleteRow(row, function(e) {
	                        	$.model.destroy(row,function(e){
	                        		//console.log('save->insertRow->fn(e)->attachEvent()->deleteRow()->$.model.destroy('+row+')');
	            	        		//TODO remove from associated leads
	            	        		$(".associated-subjects select:first option[value='"+row+"']").remove();
	                        		var id = $("form#model").id();
	                        		if(id === row) {
	                        			resetForm();
	                        		}
	                        	});
	                        });
                        e.preventDefault();
                    });
                });

                copyAssociatedToModel();
                copyIdentifyingNumberToModel();

                var id = parseInt($("form#model").id());
                //console.log("after save id: " + id);
                $("form#model").children('input[type="hidden"][name="id"]').val((id+1)+":true");
                $("#formId").val($("form#model").id());
            });
        } else {
            $("form#model").update(function(e){
                var table = $(".lead-subject-data-table").dataTable();
                var data = ['<a href="#">'+fullname()+'</a>', gender(), birthDate(), leadType(), source(), assignTo()];
                $(table).updateRow($("form#model").id(),data,function(e) {
                    //console.log('update->updateRow->fn(e)');
                    $(table).attachEvent("click", "tbody tr td a", function(e){
                        var row = $(this).parent().siblings("td:last").find('input[type="hidden"][name="row"]').val();
	                    //console.log("save->insertRow->fn(e)->attachEvent()->fn(e) row[" + row + "]");
	                    resetForm();
	                    showForm(row);
                        e.preventDefault();
                    });
                });

                copyAssociatedToModel();
                copyIdentifyingNumberToModel();
            });
          }
		}
	}

	function copyAssociatedToModel(){
  		console.log("copyAssociatedToModel->form#model.id " + $("form#model").id());
        $(".associated-subjects:hidden").removeClass("hidden").hide().fadeIn();
        //TODO update associated subjects dropdown
        if($(".associated-subjects select:first option[value=" + $("form#model").id() + "]").length === 0) {
	        $(".associated-subjects select:first").append($("<option></option>").val($("form#model").id()).text(fullname()));
        } else {
	        $(".associated-subjects select:first option[value=" + $("form#model").id() + "]").text(fullname());
        }

        var elems = $.model.find(':input[name^="ltLeadsModel['+ $("form#model").id() + '].associateModel"]');
  		$.each(elems,function(){
  			$(this).remove();
  		});
  		elems = $.associates.model.find(':input[name^="ltLeadsModel['+ $("form#model").id() + '].associateModel"]');
  		$.each(elems,function(){
  			var name = $(this).attr("name");
      		if($.model.find(':input[name="' + name + '"]').length === 0) {
      			$($(this).clone().attr("name",name)).val($(this).val()).appendTo($.model);
      		} else {
      			$.model.find(':input[name="' + name + '"]').val($(this).val());
      		}
      	});
	}

	function copyIdentifyingNumberToModel() {
  		console.log("copyIdentifyingNumberToModel->form#model.id " + $("form#model").id());
        $(".identifying-numbers:hidden").removeClass("hidden").hide().fadeIn();
        var elems = $.model.find(':input[name^="ltLeadsModel['+ $("form#model").id() + '].identifyingNumberModels"]');
  		$.each(elems,function(){
  			$(this).remove();
  		});
  		elems = $.numbers.model.find(':input[name^="ltLeadsModel['+ $("form#model").id() + '].identifyingNumberModels"]');
  		$.each(elems,function(){
  			var name = $(this).attr("name");
      		if($.model.find(':input[name="' + name + '"]').length === 0) {
      			$($(this).clone().attr("name",name)).val($(this).val()).appendTo($.model);
      		} else {
      			$.model.find(':input[name="' + name + '"]').val($(this).val());
      		}
      	});
	}

	function isBlankForm() {
		var blank = true;
		var elems = $("#lead-information-form :input, #subject-information-form :input, #assign-to-form :input");
		$.each(elems, function(i,el) {
			//console.log("isBlankForm() " + $(el).attr("name") + ":" + $(el).val());
			var val = $.trim($(el).val());
			if(val !== "" && val !== "Select..." && val !== "None" && val !== $.leadtrac.emailMask){
				blank = false;
			}
		});
		//console.log("isBlankForm() " + blank);
		return blank;
	}

	function isValidForm() {
		var valid = true;
		var elems = $("form#model").find(':input[name^="ltLeadsModel[0]"].required');
		$.each(elems,function(i,el) {
			//console.log("isValidForm() " + $(el).attr("name") + ":" + $(el).val());
			if($(el).attr("name").indexOf('identifyingNumberModel') === -1){
				var val = $.trim($(el).val());
			if(val === "" || val === "Select..." || val === $.leadtrac.emailMask) {
				valid = false;
				if(val === $.leadtrac.emailMask) {
					$(this).showError(" is required");
				} else if($(el).is("input")) {
					$(el).checkRequired();
				} else if($(el).is("select")) {
					$(this).checkRequired($.leadtrac.selectRegex);
				}
			}
			}
		});

		if(valid) { 
			valid = ($("form#model").find(".lead-information span.error,.subject-information span.error").filter(function(){ return (! $(this).hasClass("hidden")); }).length > 0) ? false : true;
		}

		var assign = checkAssignTo();

		return valid && assign;
	}

	function postSave(xml) {
		//console.log("postSave()");
		var response = $(xml).find("response");
		switch($(response).attr("status").toUpperCase()){
			case "SUCCESS" : 
				ajaxSuccess(xml); break;
			case "ERROR" :  
				ajaxError(xml); break;
		}

		function ajaxSuccess(xml) {
	        text = "<ul class='message subjects'>";
	        var subjects = $(xml).find("subject");
	        $.each(subjects, function(i,s) {
	        	//console.log("subject name["+$(s).attr("name")+"] id["+$(s).attr("id")+"]");
	        	text += "<li class='name'>" + $(s).attr("name") + "</li>";
	        	text += "<li class='id'>" + $(s).attr("id") + "</li>";
	        });
	        text += "</ul>";
	        $('input[value="Initiate"]').confirmation("Complete",text,function(e,t) {
	            //console.log("postSave()->confirm()->callback ");
	            self.location.href = "/leadtrac-web/workQueue";
	        });
	        var buttons = $("#dialog-confirm").next().find("button").hide();
	        $(buttons[2]).show(); //show OK button
		}
		
		function ajaxError(xml) {
	        text = "<ul class='error'>";
	        var errors = $(xml).find("error");
	        $.each(errors, function(i,er) {
	        	//console.log("error name["+$(er).attr("code")+"] id["+$(er).attr("message")+"]");
	        	text += "<li>" + $(er).attr("message") + "</li>";
	        });
	        text += "</ul>";
	        $('input[value="Initiate"]').confirmation("Error",text,function(e,t) {
	            //console.log("postSave()->confirm()->callback ");
	        });
	        var buttons = $("#dialog-confirm").next().find("button").hide();
	        $(buttons[2]).show(); //show OK button
		}
	}
});
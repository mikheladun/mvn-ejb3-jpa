var subjectToLeadMap;
var lastFocusedItem;

$(function() {
	//console.log("Entered Sidebar java script!!!!");
	// perform search when user type any of the following input
	// Last name, first name and or LSID.
    $('#subjectSurname, #subjectGivenName').off("blur").on("blur",function(e){
    	$(this).checkRequired();
		if($.trim($(this).val()) !== ""){
			performSearch($('#subjectSurname').val(), $('#subjectGivenName').val(), $('#lsid').val());
		}
    });
	$('#lsid').on("blur",function(e) {
		if($.trim($(this).val()) !== ""){
			//console.log("you enter " + $(this).val());
			performSearch($('#subjectSurname').val(), $('#subjectGivenName').val(), $('#lsid').val());
		}
	});
    $('#subjectSurname, #subjectGivenName').off("keyUp").on("keyUp",function(e){
            $(this).checkRequired(/^.+$/);
        	if($.trim($(this).val()) !== ""){
	        	performSearch($('#subjectSurname').val(), $('#subjectGivenName').val(), $('#lsid').val());
        	}
    });
	$('#lsid').on("keyUp",function(e) {
		if (e.which == 13 || e.which == 9) {
			//console.log("you enter " + $(this).val());
			if($.trim($(this).val()) !== ""){
				performSearch($('#subjectSurname').val(), $('#subjectGivenName').val(), $('#lsid').val());
			}
		}
	});

	// perform search in the background
	function performSearch(lastName, firstName, lsid) {
		// make a call to search service
		$.ajax(
				{
					url : "service/search/subject?lastName=" + lastName
							+ "&firstName=" + firstName + "&lsid=" + lsid,
					method : "GET",
					dataType : "xml"
				}).done(function(xml) {
			//console.log(xml);
			subjectToLeadMap = xml;
			populateSideBar();
		});

	}
	// populate the subject metadata on sidebar
	function populateSideBar() {
		// reset the list
		$('#searchresultDiv').empty();
		var searchResultCount = $(subjectToLeadMap).find("subject").length;
		console.log("Search count =" + searchResultCount);

		// show search result count
		$('#searchResultCount').html("Search Results - " +  searchResultCount);	
		// populate the subject list
		$(subjectToLeadMap).find("subject").each(
				function(i, e) {
					// Get all the details of the subject
					//console.log($(e).find("firstname").text());
					//console.log($(e).find("lastname").text());
					$('#searchresultDiv').append(
							"<div class="
									+ "search-person JohnHover p solid-top"
									+ ">" + '<a' + " id=" + $(e).attr("id")
									+ " track=" + "yes" + " href=" + "#" + '>'
									+ $(e).find("lastname").text() + ",&nbsp"
									+ $(e).find("firstname").text()
									+ "</a><br>" + "LSID:&nbsp "
									+ $(e).find("lsid").text() + "<br>"
									+ "Leads:&nbsp"
									+ $(e).find("numberofopenleads").text()
									+ " Open |&nbsp"
									+ $(e).find("numberofclosedleads").text()
									+ " Closed<br>" + "</div>");
				});
		// intercept subject link selection
		$("a[track]").click(function(e) {
			e.preventDefault();
			console.log("Subject selected id=" + $(this).attr('id'));
			getSubjectConfirmation($(this).attr('id'));
			//populateSubjectInfoOnInitialLead($(this).attr('id'));
		});

	}
	
	/**
	 * Ask for confirmation before updating the Subject info on initial lead screen
	 */
	function getSubjectConfirmation(selectedId) {
		var message = getSubjectInfoString(selectedId);
		$('input, textarea').focus( function() { 
			lastFocusedItem = this; 
		});
    $(this).confirmation("Select Existing Subject Confirmation", 
    	message, 
    	function(e) {
    	var action = $(e.target).is("button") ? $(e.target).find("span").html() : $(e.target).html();
        switch (action) {
            case "Confirm":
            case "OK":
            	populateSubjectInfoOnInitialLead(selectedId);
                break;
            case "Cancel": // set focus back to last input field
            	$(lastFocusedItem).focus();
            default:
        }
    });

	}
	
	/**
	 * find the subject that is selected by the user in xml and populate the subject
	 * portion of the initial lead screen
	 */
	function populateSubjectInfoOnInitialLead(selectedId) {

		// reset the mulitselect field on the subject form
		 $("#subject-information-form").find("span.multiselect").prop("selectedIndex", 0);
		$($(subjectToLeadMap).find("subject").each(function(i, e) {
			// Get all the details of the subject
			if ($(e).attr('id') == selectedId) {
				$('#lsid').val('');
				$('#lsid').val($(e).find("lsid").text());
				$('#lsid').hideError();
				
				// make the lsid read only
				$('#lsid').attr("disabled", "disabled").addClass("readonly");
				$('#subjectSurname').val('');
				$('#subjectSurname').val($(e).find("lastname").text());
				$('#subjectSurname').hideError();
				//update the subject ID on hidden field
				$('#subjectId').val($(e).attr("id") );
				
				// make the surname read only
				$('#subjectSurname').attr("disabled", "disabled").addClass("readonly");
				$('#subjectGivenName').val('');
				$('#subjectGivenName').val($(e).find("firstname").text());
				$('#subjectGivenName').hideError();
				
				// make the GivenName read only
				$('#subjectGivenName').attr("disabled", "disabled").addClass("readonly");
			
				$("#subjectGender option").each(function() {
	        		  this.selected = $(this).text() == $(e).find("gender").text();
				});
				$('#subjectDOB').val($(e).find("birthdate").text());
				//$("#subjectCOB option:selected").text($(e).find("countryofbirth").text());
				$("#subjectCOB option").each(function() {
			        		  this.selected = $(this).text() == $(e).find("countryofbirth").text();
        			});
				
				populateCountryOfCitizenship( e);
			}
		}));
	}
	
		
	
	/**
	 * Populate the country of citizenship selection(s) on subject panel
	 */
	function populateCountryOfCitizenship( subjElem ) {
	var countryCount = $(subjElem).find("countryname").length;
	console.log("Country count =" + countryCount);
	
	$(subjElem).find("countryname").each(function(i, e) {
		console.log($(e).text() + "index=" + i);
        var span = $("<span></span>");
        var label = $('<label for="">' + $(e).text() + "</label>");
       // subjectCOCs
        if ( i == 0) {
        	//$("#subjectCOCs option:selected").text($(e).text());
        	$("#subjectCOCs option").each(function() {
        		  this.selected = $(this).text() == $(e).text();
        		});
        	//fire a click event to add the country to the bottom of the COC list
        	$("#addCountryOfCitizenshipButton1").click();
        	//('.my-button').trigger("click");
		}
        else {
        	//$("#subjectCOCs option:selected").text($(e).text());
        	$("#subjectCOCs option").each(function() {
      		  this.selected = $(this).text() == $(e).text();
        	});
        }
       

	});
	
}

	/**
	 * Create a message for Subject confirmation dialogue box
	 */
	function getSubjectInfoString(selectedId) {
		var dialogueMsg = null;
		
		$($(subjectToLeadMap).find("subject").each(function(i, e) {
			// Get all the details of the subject
			if ($(e).attr('id') == selectedId) {
				dialogueMsg =
				'<!DOCTYPE html>' +
				 '<html>' +
				 '<body>' +
				 
				'<div id="container" style="width:200px">' + 				 
				 '<div id="dialogueHeader" style="height:200px;width:100px;float:left;">' +
				 	"LSID:" + 	 "<br><br>"  +
					"Surname:" + "<br><br>"  +
					"Given Name:" +  "<br><br>"  +
					"Gender:" +  "<br><br>"  +
					"DOB:" +  "<br><br>"  +
					"COB:" +  "<br><br>"  +
					"COC:" +  "<br><br>"  +
					 "</div>" +
				 
					 '<div id="msgDialog" style="height:200px;width:100px;float:left;">' +
					 $(e).find("lsid").text()  + "<br><br>"  +
					 $(e).find("lastname").text()  + "<br><br>"  +
					 $(e).find("firstname").text()  + "<br><br>"  +
					 $(e).find("gender").text()  + "<br><br>"  +
					 $(e).find("birthdate").text()  + "<br><br>"  +
					 $(e).find("countryofbirth").text()  + "<br><br>"  +
					 getListOfCOCfromXML( e ) + "<br><br>"  +
					"</div>" +
					'</div>'+
				 
				'</body>' +
				'</html>';
			}
		}));
		//console.log("Message=" + dialogueMsg);
		return dialogueMsg;
	}
	
	/**
	 * Get country of citizenship list
	 */
	function getListOfCOCfromXML( subjElem) {
		var cocList = "";
		var cocCount = $(subjElem).find("countryname").length;
		$(subjElem).find("countryname").each(function(i, e) {
			cocList += $(e).text();
			if (cocCount > 1 && i == 0) {
				cocList += (", ");
			}
		});
		return cocList;
		
	}
});
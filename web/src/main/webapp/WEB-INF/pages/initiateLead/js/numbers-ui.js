(function($) {
	$.fn.extend({});
})(jQuery);

// --------------------------------------------------------------------------------
// ----------- Identifying Numbers
// --------------------------------------------------------------------------------
$(function() {
	var datamap_array = new Array();

	var rowedited = false;
	var rownumedited = 0;
	var doprompt = true;

	var disable_list = [ "ADIS PID", "LSID", "FIN" ];

	var bindpref = "ltLeadsModel[].identifyingNumberModels[]";

	$('input[type="image"]#identifying-numbers-source-add').click(
			function(event) {
				event.preventDefault();
				$(this).siblings('span.error').each(function() {
					$(this).remove();
				});
			});

	function enableSourceSelection() {
		$(
				'input[type="image"]#identifying-numbers-source-add + select#identifying-numbers-source option')
				.each(function(i) {
					if ($(this).val() != '')
						$(this).removeAttr("disabled");
				});
	}

	$('select#identifying-numbers-country').on("change", function(e) {
		if ($(this).hasClass('error'))
			$(this).hideError();
	});

	$('input[type="button"][name="delete"][title="Delete identifying numbers"]')
			.click(
					function(i) {
						if (!rowedited)
							return;

						$(
								'table#IdNumTable tr[rowindex="'
										+ rownumedited
										+ '"] td.no-table-style input[type="image"][name="del"]')
								.trigger("click");

						$(
								'table#IdNumTable tr[rowindex="' + rownumedited
										+ '"]').remove();

						// POC
						compactArray(datamap_array, rowindex);

						resetEditMode();
						resetSelections();
						identifying_number_initAll();
						$(
								'input[type="button"][title="Add identifying numbers"][name="add"]')
								.val("Add");
						$(
								'input[type="button"][title="Delete identifying numbers"]')
								.hide();

					});

	function addHiddenFieldToForm(bindsuffix, rowindex, value) {
		// alert ("forms container count = " +
		// $('div.identifyting-numbers-input-fieldsets').length);

		if ($('div.identifyting-numbers-input-fieldsets').length == 0) {
			// alert("Creating forms container");
			$('div.identifying-numbers.column-two:last').after(
					'<div class="identifying-numbers-input-fieldset"></div>');
		}
		// alert( "formset count = " +
		// $('div.identifying-numbers-input-fieldset[row="' + rowindex +
		// '"]').length);
		if ($('div.identifying-numbers-input-fieldset[row="' + rowindex + '"]').length == 0) {
			// alert ("Creating fieldsetset " + rowindex);
			$('div.identifying-numbers-input-fieldsets').append(
					'<div class="identifying-numbers-input-fieldset" fsid="'
							+ rowindex + '"></div>');
		}

		var leadid = $('form#model').id();

		var $fieldset = $('div.identifying-numbers-input-fieldset[fsid="'
				+ rowindex + '"]');

		var elemname = bindpref.replace(
				/(ltLeadsModel\[)\]\.(identifyingNumberModels\[)\]/, "$1"
						+ leadid + "]" + "." + "$2" + rowindex + "]");

		var elemname = elemname + "." + bindsuffix;

		$fieldset.append('<input type="hidden" name="' + elemname + '" value="'
				+ value + '"/>');
	}

	function getFormFields() {

		var leadid = $('form#model').id();
		var i;
		var j;
		var k;
		var elemname;
		var tabrow;
		var tabelement;
		var hiddenfields = "";

		for (i = 0; i < datamap_array.length; i++) {

			tabrow = datamap_array[i];

			for (j = 0; j < tabrow.length; j++) {

				if (j == 2) {

					var l = 0;

					for (l = 0; l < tabrow[2].length; l++) {
						elemname = bindpref
								.replace(
										/(ltLeadsModel\[)\]\.(identifyingNumberModels\[)\]/,
										"$1" + leadid + "]" + "." + "$2" + i
												+ "]");
						elemname = elemname + '.sourceCodes[' + l + ']';
						hiddenfields += '<input type="hidden" name = "'
								+ elemname + '" value="' + tabrow[2][l]
								+ '" />';
					}
				}

				tabelement = tabrow[j];

				for (k = 0; k < tabelement.length; k++) {

					elemname = bindpref
							.replace(
									/(ltLeadsModel\[)\]\.(identifyingNumberModels\[)\]/,
									"$1" + leadid + "]" + "." + "$2" + i + "]");

					if (k == 1) {

						if (tabelement[k] == 'identifyingNumbersType') {
							elemname = elemname + ".numberTypeCode";
							value = tabelement[2];
							hiddenfields += '<input type="hidden" name = "'
									+ elemname + '" value="' + value + '" />';
						}

						if (tabelement[k] == 'identifying-numbers-number') {
							elemname = elemname + ".number";
							value = tabelement[2];
							hiddenfields += '<input type="hidden" name = "'
									+ elemname + '" value="' + value + '" />';
						}

					}

				}

			}
		}

		return hiddenfields;

	}

	function saveDataMap(rowindex) {
		var numbertype = $('select#identifyingNumbersType option:selected')
				.text();

		datamap_array[rowindex] = new Array();
		datamap_array[rowindex][0] = new Array();
		datamap_array[rowindex][0] = [ 'select', 'identifyingNumbersType',
				$('select#identifyingNumbersType option:selected').val() ];

		datamap_array[rowindex][1] = new Array();
		datamap_array[rowindex][1] = [ 'input', 'identifying-numbers-number',
				$('input#identifying-numbers-number').val() ];

		var sourcearray = new Array();
		var i = 0;

		var sourcetop = $(
				'select#identifying-numbers-source.required.source:not(.hidden)')
				.val();

		if (sourcetop != '') {
			sourcearray[i] = sourcetop;
			i = i + 1;
		}

		$(
				'input#identifying-numbers-source-add+select#identifying-numbers-source.required.source option[disabled="disabled"]')
				.each(function(index) {
					sourcearray[i] = $(this).val();
					i = i + 1;
				});

		datamap_array[rowindex][2] = new Array();
		datamap_array[rowindex][2] = sourcearray;

		// Additional Fields based on number types

		if (numbertype == 'Alien Registration') {
			datamap_array[rowindex][3] = new Array();
			datamap_array[rowindex][3] = [ 'select',
					'identifying-numbers-country',
					$('select#identifying-numbers-country').val() ];

			datamap_array[rowindex][4] = new Array();
			datamap_array[rowindex][4] = [ 'textarea',
					'identifying-numbers-comments',
					$('textarea#identifying-numbers-comments').val() ];
			clearComments();
			return;
		}

		if (numbertype == "Driver's License") {
			datamap_array[rowindex][3] = new Array();
			datamap_array[rowindex][3] = [ 'select',
					'identifying-numbers-country',
					$('select#identifying-numbers-country').val() ];
			datamap_array[rowindex][4] = new Array();
			datamap_array[rowindex][4] = [ 'select', 'stateprovince',
					$('select#stateprovince').val() ];

			datamap_array[rowindex][5] = new Array();
			datamap_array[rowindex][5] = [ 'input', 'dlIssueDate',
					$('input#dlIssueDate').val() ];

			datamap_array[rowindex][6] = new Array();
			datamap_array[rowindex][6] = [ 'input', 'dlExpDate',
					$('input#dlExpDate').val() ];

			datamap_array[rowindex][7] = new Array();
			datamap_array[rowindex][7] = [ 'textarea',
					'identifying-numbers-comments',
					$('textarea#identifying-numbers-comments').val() ];
			clearComments();
			return;
		}

		if (numbertype == 'FIN') {
			datamap_array[rowindex][3] = new Array();
			datamap_array[rowindex][3] = [ 'select',
					'identifying-numbers-country',
					$('select#identifying-numbers-country').val() ];
			datamap_array[rowindex][4] = new Array();
			datamap_array[rowindex][4] = [ 'textarea',
					'identifying-numbers-comments',
					$('textarea#identifying-numbers-comments').val() ];
			clearComments();
			return;
		}

		if (numbertype == 'Naturalization') {
			datamap_array[rowindex][3] = new Array();
			datamap_array[rowindex][3] = [ 'select',
					'identifying-numbers-country',
					$('select#identifying-numbers-country').val() ];
			datamap_array[rowindex][4] = new Array();
			datamap_array[rowindex][4] = [ 'input', 'naturalizationDate',
					$('input#naturalizationDate').val() ];

			datamap_array[rowindex][5] = new Array();
			datamap_array[rowindex][5] = [ 'textarea',
					'identifying-numbers-comments',
					$('textarea#identifying-numbers-comments').val() ];
			clearComments();
			return;
		}

		if (numbertype == "Other") {
			datamap_array[rowindex][3] = new Array();
			datamap_array[rowindex][3] = [ 'select',
					'identifying-numbers-country',
					$('select#identifying-numbers-country').val() ];

			datamap_array[rowindex][4] = new Array();
			datamap_array[rowindex][4] = [ 'input', 'other-type',
					$('input#other-type').val() ];

			datamap_array[rowindex][5] = new Array();
			datamap_array[rowindex][5] = [ 'textarea',
					'identifying-numbers-comments',
					$('textarea#identifying-numbers-comments').val() ];
			clearComments();
			return;
		}

		if (numbertype == "Passport") {
			datamap_array[rowindex][3] = new Array();
			datamap_array[rowindex][3] = [ 'select',
					'identifying-numbers-country',
					$('select#identifying-numbers-country').val() ];

			datamap_array[rowindex][4] = new Array();
			datamap_array[rowindex][4] = [ 'input', 'passportIssueDate',
					$('input#passportIssueDate').val() ];

			datamap_array[rowindex][5] = new Array();
			datamap_array[rowindex][5] = [ 'input', 'passportExpDate',
					$('input#passportExpDate').val() ];

			datamap_array[rowindex][6] = new Array();
			datamap_array[rowindex][6] = [ 'textarea',
					'identifying-numbers-comments',
					$('textarea#identifying-numbers-comments').val() ];
			clearComments();
			return;
		}

		if (numbertype == 'SSN') {
			datamap_array[rowindex][3] = new Array();
			datamap_array[rowindex][3] = [ 'select',
					'identifying-numbers-country',
					$('select#identifying-numbers-country').val() ];
			datamap_array[rowindex][4] = new Array();
			datamap_array[rowindex][4] = [ 'textarea',
					'identifying-numbers-comments',
					$('textarea#identifying-numbers-comments').val() ];
			clearComments();
			return;
		}

		if (numbertype == "State ID Card") {
			datamap_array[rowindex][3] = new Array();
			datamap_array[rowindex][3] = [ 'select',
					'identifying-numbers-country',
					$('select#identifying-numbers-country').val() ];
			datamap_array[rowindex][4] = new Array();
			datamap_array[rowindex][4] = [ 'select', 'stateprovince',
					$('select#stateprovince').val() ];

			datamap_array[rowindex][5] = new Array();
			datamap_array[rowindex][5] = [ 'input', 'stateIdIssueDate',
					$('input#stateIdIssueDate').val() ];

			datamap_array[rowindex][6] = new Array();
			datamap_array[rowindex][6] = [ 'input', 'stateIdExpDate',
					$('input#stateIdExpDate').val() ];

			datamap_array[rowindex][7] = new Array();
			datamap_array[rowindex][7] = [ 'textarea',
					'identifying-numbers-comments',
					$('textarea#identifying-numbers-comments').val() ];
			clearComments();
			return;
		}

		if (numbertype == 'TECS - ILOG') {

			datamap_array[rowindex][3] = new Array();
			datamap_array[rowindex][3] = [ 'input', 'ilogIncidentDate',
					$('input#ilogIncidentDate').val() ];

			datamap_array[rowindex][4] = new Array();
			datamap_array[rowindex][4] = [ 'textarea',
					'identifying-numbers-comments',
					$('textarea#identifying-numbers-comments').val() ];
			clearComments();
			return;
		}

		if (numbertype == 'TECS Case') {
			datamap_array[rowindex][3] = new Array();
			datamap_array[rowindex][3] = [ 'select', 'tecs-case-status',
					$('select#tecs-case-status').val() ];
			datamap_array[rowindex][4] = new Array();
			datamap_array[rowindex][4] = [ 'textarea',
					'identifying-numbers-comments',
					$('textarea#identifying-numbers-comments').val() ];
			clearComments();
			return;
		}

		if (numbertype == 'TECS Subject Record') {
			datamap_array[rowindex][3] = new Array();
			datamap_array[rowindex][3] = [ 'select', 'tecs-subj-rec-status',
					$('select#tecs-subj-rec-status').val() ];
			datamap_array[rowindex][4] = new Array();
			datamap_array[rowindex][4] = [ 'input', 'tsrcCreationDate',
					$('input#tsrcCreationDate').val() ];

			datamap_array[rowindex][5] = new Array();
			datamap_array[rowindex][5] = [ 'input', 'tsrcUpdateDate',
					$('input#tsrcUpdateDate').val() ];

			datamap_array[rowindex][6] = new Array();
			datamap_array[rowindex][6] = [ 'textarea',
					'identifying-numbers-comments',
					$('textarea#identifying-numbers-comments').val() ];
			clearComments();
			return;
		}

		if (numbertype == 'Visa') {
			datamap_array[rowindex][3] = new Array();
			datamap_array[rowindex][3] = [ 'select', 'visa-class',
					$('select#visa-class').val() ];
			datamap_array[rowindex][4] = new Array();
			datamap_array[rowindex][4] = [ 'input', 'visaIssueDate',
					$('input#visaIssueDate').val() ];

			datamap_array[rowindex][5] = new Array();
			datamap_array[rowindex][5] = [ 'input', 'visaExpDate',
					$('input#visaExpDate').val() ];

			datamap_array[rowindex][6] = new Array();
			datamap_array[rowindex][6] = [ 'textarea',
					'identifying-numbers-comments',
					$('textarea#identifying-numbers-comments').val() ];
			clearComments();
			return;
		}

		if (numbertype == 'Visa Control #') {
			datamap_array[rowindex][3] = new Array();
			datamap_array[rowindex][3] = [ 'select', 'visa-class',
					$('select#visa-class').val() ];
			datamap_array[rowindex][4] = new Array();
			datamap_array[rowindex][4] = [ 'input', 'vcEventDate',
					$('input#vcEventDate').val() ];

			datamap_array[rowindex][5] = new Array();
			datamap_array[rowindex][5] = [ 'select', 'vcEventType',
					$('select#vcEventType').val() ];

			datamap_array[rowindex][6] = new Array();
			datamap_array[rowindex][6] = [ 'select', 'vcAppLoc',
					$('select#vcAppLoc').val() ];

			datamap_array[rowindex][7] = new Array();
			datamap_array[rowindex][7] = [ 'textarea',
					'identifying-numbers-comments',
					$('textarea#identifying-numbers-comments').val() ];
			clearComments();
			return;
		}

		if (numbertype == 'FAA License') {
			datamap_array[rowindex][3] = new Array();
			datamap_array[rowindex][3] = [ 'input', 'licIssueDate',
					$('input#licIssueDate').val() ];

			datamap_array[rowindex][4] = new Array();
			datamap_array[rowindex][4] = [ 'input', 'licExpDate',
					$('input#licExpDate').val() ];

			datamap_array[rowindex][5] = new Array();
			datamap_array[rowindex][5] = [ 'input', 'licIssueLoc',
					$('input#licIssueLoc').val() ];

			datamap_array[rowindex][6] = new Array();
			datamap_array[rowindex][6] = [ 'select', 'licCode',
					$('select#licCode').val() ];

			datamap_array[rowindex][7] = new Array();
			datamap_array[rowindex][7] = [ 'textarea',
					'identifying-numbers-comments',
					$('textarea#identifying-numbers-comments').val() ];
			clearComments();
			return;
		}

		else {
			datamap_array[rowindex][3] = new Array();
			datamap_array[rowindex][3] = [ 'textarea',
					'identifying-numbers-comments',
					$('textarea#identifying-numbers-comments').val() ];
			clearComments();
			return;
		}

	}

	function disableDeleteButtons() {
		$('div.identifying-numbers-data-table').find(
				'input[type="image"][name="del"].image.remove').each(
				function(i) {
					$(this).attr("disabled", "disabled");
				});
	}

	function enableDeleteButtons() {
		$('div.identifying-numbers-data-table').find(
				'input[type="image"][name="del"].image.remove').each(
				function(i) {
					$(this).removeAttr("disabled");
				});
	}

	function populateDropDown(selid, value) {
		$('select#' + selid).val(value);
	}

	function populateTextBox(inpid, value) {
		$('input#' + inpid).val(value);
	}

	function populateTextArea(inpid, value) {
		$('textarea#' + inpid).val(value);
	}

	function populateSource(value) {
		$('select#identifying-numbers-source.required.source option').each(
				function() {
					if ($(this).val() == value) {
						$(this).removeAttr("disabled");
					}
				});
		if (value != '') {
			$('select#identifying-numbers-source.required.source').val(value);
			$('input[type="image"]#identifying-numbers-source-add').trigger(
					'click');
		}
	}

	function resetSelections() {
		var numtypetxt = '';
		var i = 0;
		var len = datamap_array.length;
		var numtypeval = '';

		$('select#identifyingNumbersType option').each(function() {
			for (i = 0; i < len; i++) {
				numtypeval = datamap_array[i][0][2];
				if ($(this).val() == numtypeval) {
					numtypetxt = $(this).text();
					if ($.inArray(numtypetxt, disable_list) != -1)
						$(this).attr("disabled", "disabled");
				}
			}
		});

	}

	function resetEditMode() {
		rowedited = false;
		$('input[type="button"][title="Add identifying numbers"][name="add"]')
				.val("Add");
		$('input[type="button"][title="Delete identifying numbers"]').hide();
		enableDeleteButtons();
	}

	function enableNumTypeSelection(value) {
		$('select#identifyingNumbersType option').each(function() {
			if ($(this).val() == value)
				$(this).removeAttr("disabled");
		});
	}

	function disableNumTypeSelection(value) {
		$('select#identifyingNumbersType option').each(function() {
			if ($(this).val() == value)
				$(this).attr("disabled", "disabled");
		});
	}

	function removeAllSources() {
		$('select#identifying-numbers-source.required.source.hidden').parent()
				.each(function(index) {
					$(this).remove();
				});
	}

	$('select#identifying-numbers-source.required.source:not(.hidden)').on(
			"change", function() {
				var thisobj = $(this);
				if (thisobj.val() != '')
					thisobj.siblings('span.error').each(function() {
						$(this).remove();
					});
			});

	function populateFormFromDataMap(index) {
		var datamap = datamap_array[index];
		var i, j;

		$('select#identifyingNumbersType').val('');

		// Re-enable Number type selection
		enableNumTypeSelection(datamap[0][2]);

		for (i = 0; i < datamap.length; i++) {

			if (i == 2) { // process Source Selections
				removeAllSources();
				for (j = 0; j < datamap[2].length; j++) {
					if (j > 0) {
						populateSource(datamap[2][j]);
					}
				}

				$(
						'select#identifying-numbers-source.required.source:not(.hidden)')
						.val(datamap[2][0]);
			}

			else if (datamap[i][0] == 'select')
				populateDropDown(datamap[i][1], datamap[i][2]);
			else if (datamap[i][0] == 'input')
				populateTextBox(datamap[i][1], datamap[i][2]);
			else if (datamap[i][0] == 'textarea')
				populateTextArea(datamap[i][1], datamap[i][2]);

			if (i == 0)
				$('select#identifyingNumbersType').trigger("change");
		}

	}

	// ----------- initialize Identifying number Field values
	function identifying_number_initAll() {
		$('select#identifyingNumbersType').val('');
		$('select#identifying-numbers-source').val('');
		$('input#identifying-numbers-number').val('');
		$('select#identifying-numbers-country').val('');
		$('select#visa-class').val('');
		$('select#stateprovince').val('');
		$('select#licCode').val('');
		$('select#tecs-case-status').val('');
		$('select#tecs-subj-rec-status').val('');
		$('select#vcEventType').val('');
		$('select#vcAppLoc').val('');
		$('textarea#identifying-numbers-comments').val('');
		$('input#other-type').val('');
		$('input#licIssueDate').val('');
		$('input#licExpDate').val('');
		$('input#licIssueLoc').val('');
		$('textarea#identifying-numbers-comments').val('');

		$('select#identifying-numbers-source.required.source.hidden').parent()
				.each(function(index) {
					$(this).remove();
				});

		$('input.mdate').each(function(index) {
			$(this).val('');
		});

		$('select#identifyingNumbersType').focus();

	}

	// ------Show/Hide Fields -->

	function identifying_number_hideAll() {
		$('div.identifying-numbers-group').each(function(index) {
			$(this).hide();
		});
	}

	function identifying_number_showAll() {
		$('div.identifying-numbers-group').each(function(index) {
			$(this).show();
		});
	}

	function getSource(elem) {
		var sourcelist = "";
		var delim;
		var source = '';

		sourcelist = $(
				'select#identifying-numbers-source.required.source:not(.hidden) option:selected')
				.text();

		if (sourcelist == 'Select...')
			sourcelist = '';
		$(elem)
				.find(
						"select#identifying-numbers-source.required.source.hidden+label")
				.each(function(index) {
					if (sourcelist == '')
						delim = '';
					else
						delim = '<br/>';

					source = $(this).text();
					if (source != undefined && source != 'Select...') {
						sourcelist += delim + source;
					}

				});
		return sourcelist;
	}

	function getExpireDate(elem) {
		var v = $('select#identifyingNumbersType option:selected').text();
		if (v == undefined)
			return "&nbsp;";

		if (v == "Driver's License")
			return $('input#dlExpDate').val();
		if (v == "Passport")
			return $('input#passportExpDate').val();
		if (v == "State ID Card")
			return $('input#stateIdExpDate').val();
		if (v == "Visa")
			return $('input#visaExpDate').val();
		if (v == "FAA License")
			return $('input#licExpDate').val();
		return "&nbsp;";
	}

	function getStateProv(elem) {
		var v = $('select#identifyingNumbersType option:selected').text();
		if (v == undefined || /Select[ .]*/.test(v))
			return "&nbsp;"
		var stateprov;

		if (v == "Driver's License" || v == 'State ID Card')
			stateprov = $('select#stateprovince option:selected').text();

		if (stateprov != undefined && stateprov != 'Select...')
			return stateprov;

		else
			return "&nbsp;";
	}

	function getIssueDate(elem) {
		var v = $('select#identifyingNumbersType option:selected').text();
		if (v == undefined)
			return "&nbsp;";

		if (v == "Driver's License")
			return $('input#dlIssueDate').val();
		if (v == "Naturalization")
			return $('input#naturalizationDate').val();
		if (v == "Passport")
			return $('input#passportIssueDate').val();
		if (v == "State ID Card")
			return $('input#stateIdIssueDate').val();
		if (v == "Visa")
			return $('input#visaIssueDate').val();
		if (v == "FAA License")
			return $('input#licIssueDate').val();
		return "&nbsp;";
	}

	function getCountry(elem) {
		var v = $('select#identifying-numbers-country option:selected').text();
		if (v == undefined || v == 'Select...')
			return "&nbsp;";
		else
			return v;
	}
	function getType(elem) {
		return $(elem).find("select.type option:selected").text();
	}
	function getNumber(elem) {
		return $(elem).find("input.number").val();
	}
	function clearComments() {
		$('textarea#identifying-numbers-comments').val('');
	}

	// --------Add button handler -->

	$('input[type="button"][title="Add identifying numbers"]')
			.on(
					"click",
					function(event) {
						event.preventDefault();
						var isValid = validationPassed();

						if (isValid) {

							// TODO: populate datatable
							var table = $(".identifying-numbers-data-table")
									.dataTable();
							// $(table).insertRow($("form"), function(tr) {
							$(table)
									.createRow(
											$("form"),
											function(tr) {
												// console.log('identifyNumbers->insertRow->callback()');
												// console.log($(tr));
												var indiv = $("form")
														.find(
																"div.identifying-numbers:first");
												var type = getType($(indiv));

												// get an unique row index and
												// attach it to the row as an
												// attribute
												var rowindex = getNextRowIndex();

												// new
												if (rowedited)
													rowindex = rownumedited;

												$(tr)
														.attr('rowindex',
																rowindex);

												var col1 = $(
														"<a href='#identifying-numbers-form'></a>")
														.append(type)
														.on(
																"click",
																function(e) {
																	if (rowedited == false) {
																		rowedited = true;
																		disableDeleteButtons();
																		rownumedited = $(
																				this)
																				.parent()
																				.parent()
																				.attr(
																						"rowindex");
																		populateFormFromDataMap(rownumedited);
																		$(
																				'input[type="button"][title="Add identifying numbers"][name="add"]')
																				.val(
																						"Update");
																		$(
																				'input[type="button"][title="Delete identifying numbers"]')
																				.show();
																	}

																	else {
																		// promptToUpdate();
																		resetSelections();
																		resetEditMode();

																		rowedited = true;
																		disableDeleteButtons();
																		rownumedited = $(
																				this)
																				.parent()
																				.parent()
																				.attr(
																						"rowindex");
																		populateFormFromDataMap(rownumedited);
																		$(
																				'input[type="button"][title="Add identifying numbers"][name="add"]')
																				.val(
																						"Update");
																		$(
																				'input[type="button"][title="Delete identifying numbers"]')
																				.show();
																		return;
																	}

																});
												// TODO populate table columns
												// with form values
												// append td columns in reverse
												// order because the last td
												// column
												// was added by insertRow()

												if (!rowedited) {
													$(tr)
															.prepend(
																	"<td>"
																			+ getSource($(indiv))
																			+ "</td>");
													$(tr)
															.prepend(
																	"<td>"
																			+ getExpireDate($(indiv))
																			+ "</td>");
													$(tr)
															.prepend(
																	"<td>"
																			+ getIssueDate($(indiv))
																			+ "</td>");
													$(tr)
															.prepend(
																	"<td>"
																			+ getStateProv($(indiv))
																			+ "</td>");
													$(tr)
															.prepend(
																	"<td>"
																			+ getCountry($(indiv))
																			+ "</td>");
													$(tr)
															.prepend(
																	"<td>"
																			+ getNumber($(indiv))
																			+ "</td>");
													$(tr)
															.prepend(
																	$(
																			"<td></td>")
																			.append(
																					col1));

												} else {
													$(
															'table#IdNumTable tbody tr')
															.each(
																	function() {
																		if ($(
																				this)
																				.attr(
																						"rowindex") == rowindex) {
																			var i = 0;
																			$(
																					this)
																					.find(
																							"td")
																					.each(
																							function(
																									i) {
																								// if
																								// (i
																								// ==
																								// 0)
																								// $(this).text(col1);
																								if (i == 1)
																									$(
																											this)
																											.html(
																													getNumber($(indiv)));
																								if (i == 2)
																									$(
																											this)
																											.html(
																													getCountry($(indiv)));
																								if (i == 3)
																									$(
																											this)
																											.html(
																													getStateProv($(indiv)));
																								if (i == 4)
																									$(
																											this)
																											.html(
																													getIssueDate($(indiv)));
																								if (i == 5)
																									$(
																											this)
																											.html(
																													getExpireDate($(indiv)));
																								if (i == 6)
																									$(
																											this)
																											.html(
																													getSource($(indiv)));
																							});

																		}
																	});

													$(tr).remove();
													rowedited = false;
													enableDeleteButtons();
													$(
															'select#identifyingNumbersType option')
															.each(
																	function() {
																		if ($(
																				this)
																				.text() == getNumber($(indiv)))
																			$(
																					this)
																					.attr(
																							"disabled",
																							"disabled");
																	});

													$(
															'input[type="button"][title="Add identifying numbers"][name="add"]')
															.val("Add");
													$(
															'input[type="button"][title="Delete identifying numbers"]')
															.hide();
												}

												saveDataMap(rowindex);

												// alert(getFormFields());

												$(tr)
														.find("td:last")
														.find(
																'input[type="image"][name="del"]')
														.on(
																"click",
																function(e) {
																	var imgobj = this;
																	e
																			.preventDefault();
																	$(this)
																			.confirmation(
																					"Delete Confirmation",
																					"Are you sure you want to delete?",
																					function(
																							e) {

																						var action = $(
																								e.target)
																								.is(
																										"button") ? $(
																								e.target)
																								.find(
																										"span")
																								.html()
																								: $(
																										e.target)
																										.html();

																						switch (action) {
																						case "Confirm":
																						case "OK":
																							$(
																									imgobj)
																									.parent()
																									.parent()
																									.remove();

																							// POC
																							compactArray(
																									datamap_array,
																									rowindex);
																							rowedited = false;
																							$(
																									'select#identifyingNumbersType option')
																									.each(
																											function(
																													i) {
																												if ($(
																														this)
																														.text() == type)
																													$(
																															this)
																															.removeAttr(
																																	"disabled");
																											});
																							resetEditMode();
																							break;
																						case "Cancel": // do
																										// nothing
																						default:
																						}
																					});
																	e
																			.preventDefault();
																});
											});
						}
						if (isValid) {
							if ($
									.inArray(
											$(
													'select#identifyingNumbersType option:selected')
													.text(), disable_list) != -1)
								$(
										'select#identifyingNumbersType option:selected')
										.attr("disabled", "disabled");
							identifying_number_initAll();
							$('select#identifying-numbers-source option').each(
									function(index) {
										$(this).removeAttr("disabled");
									});

						}
					});

	function getNextRowIndex() {
		var i = datamap_array.length;
		if (i == undefined || i == 0)
			return 0;
		else
			return i;
	}

	function compactArray(arr, i) {

		var len = arr.length;
		var count;
		if (i < len - 1) {
			for (count = i; count < len; ++count) {
				arr[count] = arr[count + 1];
			}
		}
		arr[len - 1] = null;
		arr.length = len - 1;

		var i = 0;

		$('table#IdNumTable tbody tr').each(function(i) {
			$(this).attr("rowindex", i);
			i = i + 1;
		});

	}

	// ------adds a row to the identifying number table on top. Add Delete Row
	// handler-->

	function addTabRow() {

		$('table#IdNumTable > tbody:last')
				.append(
						'<tr>' + '<td>'
								+ getNumber()
								+ '</td>'
								+ '<td>'
								+ $(
										'select#identifyingNumbersType option:selected')
										.text()
								+ '</td>'
								+ '<td>'
								+ getCountry()
								+ '</td>'
								+ '<td>'
								+ '&nbsp;'
								+ '</td>'
								+ '<td>'
								+ '&nbsp;'
								+ '</td>'
								+ '<td>'
								+ '&nbsp;'
								+ '</td>'
								+ '<td>'
								+ getNumSource()
								+ '</td>'
								+ '<td class="no-table-style"><img id="rmIdentNum" title="Click to remove this entry" alt="Click to remove this entry" src="/leadtrac-web/resources/images/m.png"/></td>'
								+ '</tr>');

		$('img#rmIdentNum').on("click", function() {
			var obj = this;

			$("#dialog-confirm").dialog({
				resizable : false,
				height : 140,
				modal : true,
				buttons : {
					"Delete this row" : function() {
						$(this).dialog("close");
						$(obj).parent().parent().remove();
					},
					Cancel : function() {
						$(this).dialog("close");
					}
				}
			});

		});
	}

	// -------Returns the Identifying number sources -->

	function getNumSource() {
		var sources = '';
		$('select#identifying-numbers-source[class="required hidden"]+label')
				.each(function() {
					sources += $(this).text() + '<br/>';
				});
		return sources;
	}

	// ------ Returns the Identifying number -->

	function getNumber() {
		if ($('select#identifyingNumbersType option:selected').text() == 'Other')
			return $('input#other-type').val();
		else
			return $('input#identifying-numbers-number').val();
	}

	// ---- Returns the Country selection -->

	function getCountry() {
		var numtypetxt = $('select#identifyingNumbersType option:selected')
				.text();
		if (!doHideCountry(numtypetxt)) {
			var countrytext = $(
					'select#identifying-numbers-country option:selected')
					.text();
			if (countrytext == 'Select...')
				return '&nbsp;';
			else
				return countrytext;
		} else
			return '&nbsp;';

	}
	// ---- Show/Hide the identifying number table -->

	function showTab() {
		$('div.identifying-numbers-data-table').show();
		if ($('table#IdNumTable').hasClass('data-table hidden'))
			$('table#IdNumTable').removeClass('data-table hidden');
	}

	function hideTab() {
		$('div.identifying-numbers-data-table').hide();
		if (!$('table#IdNumTable').hasClass('data-table hidden'))
			$('table#IdNumTable').addClass('data-table hidden');
	}

	// ---- returns true if all the mandatory fields have been entered -->

	function validationPassed() {
		if (sourceAdded() == false) {
			$('select#identifying-numbers-source').val('').trigger('blur');
			return false;
		}

		// is a valid Identifying number selected?

		var numtype = $('select#identifyingNumbersType').val();
		if (numtype == '') {
			// $('select#identifyingNumbersType').trigger('blur');
			return false;
		}
		// is selection type other? and if so does other type has a value

		var identnumtype = $('select#identifyingNumbersType option:selected')
				.text();
		if (identnumtype == 'Other') {
			var othertype = $('input#other-type').val();
			if (othertype == '') {
				$('input#other-type').trigger('blur');
				return false;
			}
		}

		// has a valid number been entered for the number type?

		var identnum = $('input#identifying-numbers-number').val();

		if (identnum == '') {
			$('input#identifying-numbers-number').trigger('blur');
			return false;
		}

		// Drivers License date fields check

		if (identnumtype == "Driver's License") {
			if ($('select#identifying-numbers-country').val() == '') {
				$('select#identifying-numbers-country').showError(
						' is required');
				return false;
			}
		}

		// TECS Case validation

		if (identnumtype == 'TECS Case') {
			var tecs_case_status = $('select#tecs-case-status').val();
			if (tecs_case_status == '') {
				$('select#tecs-case-status').trigger('blur');
				return false;
			}
		}

		// Naturalization date field check

		// Passport date field checks

		if (identnumtype == "Passport") {
			if ($('select#identifying-numbers-country').val() == '') {
				$('select#identifying-numbers-country').showError(
						' is required');
				return false;
			}
		}

		// State ID Card date field checks

		// TECS Subject Record dates
		if (identnumtype == "TECS Subject Record") {
			var tecs_subj_rec_status = $('select#tecs-subj-rec-status').val();
			if (tecs_subj_rec_status == '') {
				$('select#tecs-subj-rec-status').trigger('blur');
				return false;
			}
		}

		// Visa Control Event

		if (identnumtype == 'Visa Control #') {

			var idnum = $('select#identifying-numbers-number').val();
			idnum = idnum.replace(/ /g, '');

			if (/\d{1,14}/.test(idnum) == false) {
				$('select#identifying-numbers-number').showCustomError(
						"Visa Control # should have max 14 digits")
				return false;
			}
		}

		return true;

	}

	// ---- returns true if at least one source has been added -->
	function sourceAdded() {
		var sourceval;
		var added = false;

		var sourcetop = $(
				'select#identifying-numbers-source.required.source:not(.hidden)')
				.val();
		if (sourcetop != 'Select...') {
			return true;
		}

		$('span.multiselect')
				.each(
						function(index) {
							if ($(this).css('display') == 'block') {
								$(
										'select#identifying-numbers-source.required.source.hidden+label')
										.each(function(index1) {
											sourceval = $(this).text();
											if (xbrowserTrim(sourceval) != '') {
												added = true;
											}
										});
							}
						});
		return added;
	}

	// ---- Cross browser trim function -->

	function xbrowserTrim(str) {
		return str.replace(/(^\s*)|(\s*$)/g, "");
	}

	// ---- Cancel button handler -->

	$('a[title="Cancel identifying numbers"]')
			.on(
					"click",
					function() {
						// if (rowedited) promptToUpdate();
						if (rowedited) {
							resetEditMode();
							resetSelections();
							identifying_number_initAll();
						} else {
							identifying_number_initAll();
						}
						$(
								'input[type="button"][title="Add identifying numbers"][name="add"]')
								.val("Add");
						$(
								'input[type="button"][title="Delete identifying numbers"]')
								.hide();
						enableSourceSelection();

						clearErrors();
					});

	function clearErrors() {
		$('div.identifying-numbers').find(':input,select').each(function(i) {
			$(this).hideError();
		});

		$('input[type="image"]#identifying-numbers-source-add').siblings(
				'span.error').each(function(i) {
			$(this).remove();
		});
	}

	// ---- Show/Hide Country Selection drop down -->

	function hideCountrySelect() {
		$('select#identifying-numbers-country').parent().hide();
	}

	function doHideCountry(numbertype) {
		if (numbertype == 'ADIS PID' || numbertype == 'Credit Card'
				|| numbertype == 'ENFORCE Event' || numbertype == 'LSID'
				|| numbertype == 'NUIN' || numbertype == 'TSC'
				|| numbertype == 'TSC' || numbertype == 'TECS Case'
				|| numbertype == 'TECS - ILOG'
				|| numbertype == 'TECS Subject Record'
				|| numbertype == 'Visa Control #' || numbertype == 'Visa'
				|| numbertype == 'FAA License')
			return true;
		else
			return false;
	}

	function showCountrySelect() {
		$('select#identifying-numbers-country').parent().show();
	}

	// ---- Show/Hide TECS Case Status drop down -->

	function doHideTecsCaseStatus(numbertype) {
		if (numbertype == 'TECS Case')
			return false;
		else
			return true;
	}

	function hideTecsCaseStatus() {
		$('select#tecs-case-status').parent().hide();
	}

	function showTecsCaseStatus() {
		$('select#tecs-case-status').parent().show();
	}

	// ---- Show/Hide TECS Subj Rec Status drop down -->

	function doHideTecsSubjRecStatus(numbertype) {
		if (numbertype == 'TECS Subject Record')
			return false;
		else
			return true;
	}

	function hideTecsSubjRecStatus() {
		$('select#tecs-subj-rec-status').parent().hide();
		$('input#tsrcCreationDate').parent().hide();
		$('input#tsrcUpdateDate').parent().hide();
	}

	function showTecsSubjRecStatus() {
		$('select#tecs-subj-rec-status').parent().show();
		$('input#tsrcCreationDate').parent().show();
		$('input#tsrcUpdateDate').parent().show();
	}

	function doHideVisaFields(numbertype) {
		if (numbertype == 'Visa')
			return false;
		else
			return true;
	}

	function hideVisaFields() {
		$('select#visa-class').parent().hide();
		$('input#visaIssueDate').parent().hide();
		$('input#visaExpDate').parent().hide();
	}

	function showVisaFields() {
		$('select#visa-class').parent().show();
		$('input#visaIssueDate').parent().show();
		$('input#visaExpDate').parent().show();

	}

	function doHidePassportDates(numbertype) {
		if (numbertype == 'Passport')
			return false;
		else
			return true;
	}

	function hidePassportDates() {
		$('input#passportIssueDate').parent().hide();
		$('input#passportExpDate').parent().hide();
	}

	function showPassportDates() {
		$('input#passportIssueDate').parent().show();
		$('input#passportExpDate').parent().show();
		$('select#identifying-numbers-country').addClass("required");
	}

	// ---- Show/Hide TECS ILog incident Date

	function doHideTecsILogDate(numbertype) {
		if (numbertype == 'TECS - ILOG')
			return false;
		else
			return true;
	}

	function hideTecsILogDate() {
		$('input#ilogIncidentDate').parent().hide();
	}

	function showTecsILogDate() {
		$('input#ilogIncidentDate').parent().show();
	}

	// Sow/Hide Drivers License Fields

	function doHideDLFields(numbertype) {
		if (numbertype == "Driver's License")
			return false;
		else
			return true;
	}

	function hideDLFields() {
		$('input#dlIssueDate').parent().hide();
		$('input#dlExpDate').parent().hide();
		$('select#stateprovince').parent().hide();
	}

	function showDLFields() {
		$('input#dlIssueDate').parent().show();
		$('input#dlExpDate').parent().show();
		$('select#stateprovince').parent().show();
		$('select#identifying-numbers-country').addClass("required");
	}

	// Show/Hide Naturalization Date

	function doHideNaturalizationDate(numbertype) {
		if (numbertype == 'Naturalization')
			return false;
		else
			return true;
	}

	function hideNaturalizationDate() {
		$('input#naturalizationDate').parent().hide();
	}

	function showNaturalizationDate() {
		$('input#naturalizationDate').parent().show();
	}

	// ---- show/hide Other type text box depending on whether number type Other
	// has been selected -->

	function doHideOtherType(numbertype) {
		if (numbertype == 'Other')
			return false;
		else
			return true;
	}

	function hideOtherType() {
		$('input#other-type').parent().hide();
	}

	function showOtherType() {
		$('input#other-type').parent().show();
	}

	// Show/Hide State ID Fields

	function doHideStateIDFields(numbertype) {
		if (numbertype == 'State ID Card')
			return false;
		else
			return true;
	}

	function hideStateIDFields() {
		$('input#stateIdIssueDate').parent().hide();
		$('input#stateIdExpDate').parent().hide();
		$('select#stateprovince').parent().hide();
	}

	function showStateIDFields() {
		$('input#stateIdIssueDate').parent().show();
		$('input#stateIdExpDate').parent().show();
		$('select#stateprovince').parent().show();
	}

	// Visa Control

	function doHideVisaControl(numbertype) {
		if (numbertype == 'Visa Control #')
			return false;
		else
			return true;
	}

	function hideVisaControl() {
		$('select#visa-class').parent().hide();
		$('input#vcEventDate').parent().hide();
		$('select#vcEventType').parent().hide();
		$('select#vcAppLoc').parent().hide();
	}

	function showVisaControl() {
		$('input#vcEventDate').parent().show();
		$('select#vcEventType').parent().show();
		$('select#vcAppLoc').parent().show();
		$('select#visa-class').parent().show();
	}

	function doHideFAALicense(numbertype) {
		if (numbertype == 'FAA License')
			return false;
		else
			return true;
	}

	function hideFAALicense() {
		$('input#licIssueDate').parent().hide();
		$('input#licExpDate').parent().hide();
		$('input#licIssueLoc').parent().hide();
		$('select#licCode').parent().hide();
	}

	function showFAALicense() {
		$('input#licIssueDate').parent().show();
		$('input#licExpDate').parent().show();
		$('input#licIssueLoc').parent().show();
		$('select#licCode').parent().show();
	}

	// ---- Sets default source for any particular number type -->
	function setDefIdentNumSource(numtype) {
		if (numtype == 'Alien Registration' || numtype == 'FIN'
				|| numtype == 'SSN') {
			$('select#identifying-numbers-country option').each(function(i) {
				if ($(this).text() == 'United States')
					$(this).attr('selected', 'selected');
			});
		}

		else if (numtype == 'ADIS PID')
			setIdentNumSource('ADIS');
		else if (numtype == 'ENFORCE Event')
			setIdentNumSource('ENFORCE');
		else if (numtype == 'LSID')
			setIdentNumSource('Leadtrac');
		else if (numtype == 'TECS Case')
			setIdentNumSource('TECS');
		else if (numtype == 'TECS Subject Record')
			setIdentNumSource('TECS');
		else if (numtype == 'TECS - ILOG')
			setIdentNumSource('TECS');
		else
			$('select#identifying-numbers-source').val('');
	}

	function hideAll() {
		hideTecsCaseStatus();
		hideOtherType();
		hideNaturalizationDate();
		hideTecsILogDate();
		hideTecsSubjRecStatus();
		hidePassportDates();
		hideVisaFields();
		hideDLFields();
		hideStateIDFields();
		hideVisaControl();
		hideFAALicense();
	}

	// ---- Select a particular Source in the drop down with matching option
	// text -->
	function setIdentNumSource(source) {
		$('select#identifying-numbers-source option').each(function(index) {
			if ($(this).text() == source)
				$(this).attr('selected', true);
		});
	}

	function setCountry(countryname) {
		$('select#identifying-numbers-country option').each(function(index) {
			if ($(this).text() == countryname)
				$(this).attr('selected', 'selected');
		});
	}

	function setspcCascade() {
		$('select#stateprovince').val('');
		$('select#identifying-numbers-country').val('');

		$('select#identifying-numbers-country').on(
				"change",
				function() {

					var sptype = $('select#stateprovince option:selected').val()
					sptype = sptype.split(":")[0];
					if (sptype == 'state') {
						setCountry('United States');
					}
					if (sptype == 'province') {
						setCountry('Canada');
					}
				});

		$('select#stateprovince').on("change", function() {
			if ($(this).val() == '' || $(this).val() == 'Select...')
				$('select#identifying-numbers-country').val('');
		});

	}

	function setValidationMask(identnumtype) {
		// var numtypetxt = $('select#identifyingNumbersType
		// option:selected').text();
		var numtypetxt = identnumtype;
		var numelement = $('input#identifying-numbers-number');

		numelement.unmask();
		numelement.val('');
		numelement.hideError();
		numelement.unbind("blur");

		var countryelem = $('select#identifying-numbers-country');
		countryelem.on("blur", function() {
			if (numtypetxt == 'Passport' || numtypetxt == "Driver's License") {
				if (countryelem.val() == '')
					countryelem.showError(" is required");
			} else
				countryelem.hideError();
		});

		if (numtypetxt == 'Passport' || numtypetxt == "Driver's License")
			$('span.asterisk#reqch').show();
		else
			$('span.asterisk#reqch').hide();

		if (numtypetxt == 'SSN') {
			numelement.mask("999-99-9999");
			numelement
					.on(
							"blur",
							function() {
								var num = numelement.val();
								if (num == '')
									numelement.showError(" is required");
								else if (!(/[0-9]{3}-[0-9]{2}-[0-9]{4}/
										.test(num)))
									numelement
											.showCustomError("incorrect SSN.should have 9 digits.");
							});

			return;
		}

		else

		if (numtypetxt == 'ENFORCE Event') {
			// numelement.mask("*************");
			numelement
					.on(
							"blur",
							function() {
								var num = numelement.val();
								if (num == '')
									numelement.showError(" is required");
								else if (!(/[0-9a-zA-Z]{13}/.test(num)))
									numelement
											.showCustomError("incorrect ENFORCE Event #.should have 13 alphanumeric characters.");
							});

			return;
		}

		else

		if (numtypetxt == 'TECS Case' || numtypetxt == 'TECS - ILOG'
				|| numtypetxt == 'TECS Subject Record') {
			// numelement.mask("**************");
			numelement
					.on(
							"blur",
							function() {
								var num = numelement.val();
								if (num == '')
									numelement.showError(" is required");
								else if (!(/[0-9a-zA-Z]{14}/.test(num)))
									numelement
											.showCustomError("incorrect TECS #.should have 14 alphanumeric characters.");
							});

			return;
		}

		else

		if (numtypetxt == 'Alien Registration') {
			// numelement.mask("999999999");
			numelement
					.on(
							"blur",
							function() {
								var num = numelement.val();
								if (num == '')
									numelement.showError(" is required");
								else if (!(/[0-9]{9}/.test(num)))
									numelement
											.showCustomError("incorrect Alien Registration #.should have 9 numeric characters.");
							});

			return;
		}

		else

		if (numtypetxt == 'FIN') {
			// numelement.mask("9999999999");
			numelement
					.on(
							"blur",
							function() {
								var num = numelement.val();
								if (num == '')
									numelement.showError(" is required");
								else if (!(/[0-9]{10}/.test(num)))
									numelement
											.showCustomError("incorrect FIN #.should have 10 numeric characters.");
							});

			return;
		}

		else

		if (numtypetxt == 'Visa') {
			// numelement.mask("********");
			numelement
					.on(
							"blur",
							function() {
								var num = numelement.val();
								if (num == '')
									numelement.showError(" is required");
								else if (!(/[0-9a-zA-Z]{8}/.test(num)))
									numelement
											.showCustomError("incorrect Visa #.should have 8 alphanumeric characters.");
							});

			return;
		}

		else {

			numelement.on("blur", function() {
				var num = numelement.val();
				if (num == '')
					numelement.showError(" is required");
			});
		}

	}

	$('select#stateprovince')
			.on(
					"change",
					function() {
						var numtypetxt = $(
								'select#identifyingNumbersType option:selected')
								.text();

						if (numtypetxt == "Driver's License"
								|| numtypetxt == "State ID Card") {
							var cat = $(this).find('option:selected').val();
							cat = cat.split(":")[0];
							if (cat == 'state') {
								setCountry('United States');
							} else {
								setCountry('Canada');
							}
						}
					});

	function detachspcevents() {
		$('select#identifying-numbers-country').unbind("change");
		$('select#stateprovince').val('');
		$('select#stateprovince').unbind("change");
		$('select#identifying-numbers-country').val('');
	}

	// ---- Number Type drop down on-change handler -->
	$('.identifying-numbers #identifyingNumbersType')
			.on(
					"change",
					function(event) {

						if (rowedited) {
							var numedited = datamap_array[rownumedited][0][2];
							var newselection = $(
									'select#identifyingNumbersType').val();
							if (newselection != numedited) {
								resetEditMode();
								removeAllSources();
							}
							resetSelections();
						}

						var identnumtype = $(
								'select#identifyingNumbersType option:selected')
								.text();

						if (identnumtype != "Driver's License"
								&& identnumtype != "State ID Card")
							detachspcevents();

						else
							setspcCascade();

						if (identnumtype == "Driver's License"
								|| identnumtype == "Passport") {
							$('select#identifying-numbers-country').addClass(
									"required");
						} else {
							$('select#identifying-numbers-country')
									.removeClass("required");
						}

						setDefIdentNumSource(identnumtype);

						setValidationMask(identnumtype);

						if (doHideCountry(identnumtype)) {
							hideCountrySelect();
						} else {
							showCountrySelect();
						}

						if ($.trim($(this).find("option:selected").val() != '')) {
							$('div.identifying-numbers div.hidden')
									.removeClass("hidden");
							$('div.identifying-numbers input[name="delete"]')
									.hide();
						}

						$('input.mdate').removeClass('error');

						hideAll();

						if (doHideTecsCaseStatus(identnumtype))
							hideTecsCaseStatus();
						else {
							showTecsCaseStatus();
							return;
						}

						if (doHideOtherType(identnumtype))
							hideOtherType();
						else {
							showOtherType();
							return;
						}

						if (doHideNaturalizationDate(identnumtype))
							hideNaturalizationDate();
						else {
							showNaturalizationDate();
							return;
						}

						if (doHideTecsILogDate(identnumtype))
							hideTecsILogDate();
						else {
							showTecsILogDate();
							return;
						}

						if (doHideTecsSubjRecStatus(identnumtype))
							hideTecsSubjRecStatus();
						else {
							showTecsSubjRecStatus();
							return;
						}

						if (doHidePassportDates(identnumtype))
							hidePassportDates();
						else {
							showPassportDates();
							return;
						}

						if (doHideVisaFields(identnumtype))
							hideVisaFields();
						else {
							showVisaFields();
							return;
						}

						if (doHideDLFields(identnumtype))
							hideDLFields();
						else {
							showDLFields();
							return;
						}

						if (doHideStateIDFields(identnumtype))
							hideStateIDFields();
						else {
							showStateIDFields();
							return;
						}

						if (doHideVisaControl(identnumtype))
							hideVisaControl();
						else {
							showVisaControl();
							return;
						}

						if (doHideFAALicense(identnumtype))
							hideFAALicense();
						else {
							showFAALicense();
							return;
						}

					});

	// ---- Old validation code using JQuery validator plug-in -->

	$("#identnum").keyup(function() {
		$(this).val($(this).val().toUpperCase());
	});
});

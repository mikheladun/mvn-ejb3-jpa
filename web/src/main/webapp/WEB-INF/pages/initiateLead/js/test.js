$(function() {
	$(".lead-information select, .subject-information select").prop("selectedIndex", 2);
	$("input[name='ltLeadsModel[0].ltLeadSource.name']").val("Adun, Mikhel");
	$("input[name='ltLeadsModel[0].ltLeadSource.title']").val("Agent");
	$("select[name='ltLeadsModel[0].ltLeadSource.contactTypeCode.abbreviation']").prop("selectedIndex", 2);
	$("input[name='ltLeadsModel[0].ltLeadSource.contact']").val("(123) 234-5678");
	$("input[name='ltLeadsModel[0].leadComment']").val("Lorem ipsum dolore");

	$("input[name='ltLeadsModel[0].ltLead.ltSubject.lastname']").val("Smith");
	$("input[name='ltLeadsModel[0].ltLead.ltSubject.firstname']").val("John");
	$("input[name='ltLeadsModel[0].birthDateModel.value']").val("01/01/1986");
	$("input[name='ltLeadsModel[0].entryDateModel.value']").val("01/01/2011");
});
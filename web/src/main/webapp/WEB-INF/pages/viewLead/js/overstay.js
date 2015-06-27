$(function() {
	function overstayCalc() {
		var ED = (new Date($("#subjectEntryDate").val())).getTime();
		var AUD = (new Date($("#admitUntilDate").val())).getTime();
		var AAUD = (new Date($("#adjustedAdmitDate").val())).getTime();
		var DD = (new Date($("#departureDate").val())).getTime();
		var overstay;

		//if AUD and AAUD are missing or if DOS is checked then don't do calculation
		if((isNaN(AUD)&&isNaN(AAUD)) || $("#durationOfStatus").prop("checked") === true ){
			$('label#dispoverstay').html("N/A");
		}
		// Current formula: (DD -> Today) - (AAUD -> AUD), arrows indicate fields to use per availability
		else{
			overstay = Math.floor( ((!isNaN(DD)?DD:(new Date()).getTime()) - (!isNaN(AAUD)?AAUD:AUD)) / (1000*3600*24) );
			if(overstay == 0){
				$('label#dispoverstay').html("0");
			}
			else{
				overstay = Math.max(0, overstay);
				$('label#dispoverstay').html(overstay==0?"N/A":overstay);
			}
		}
	}

	//uncheck DOS when AAUD or AUD is modified
	$("#admitUntilDate,#adjustedAdmitDate").bind("keyup keydown", function(){
		if($("#durationOfStatus").prop("checked") == true){
			$("#durationOfStatus").prop("checked", false);
		}
	});

	//clear AAUD and AUD when DOS is checked (also runs calculation)
	$("#durationOfStatus").change(function(){
		if($("#durationOfStatus").prop("checked") == true){
			$("#admitUntilDate").val("");
			$("#adjustedAdmitDate").val("");
		}
		overstayCalc();
	});

	//run once when the page loads
	overstayCalc();

	//also run every time related field blurs
	$("#adjustedAdmitDate,#departureDate,#admitUntilDate").on("blur", function() {
		overstayCalc();
	});
});
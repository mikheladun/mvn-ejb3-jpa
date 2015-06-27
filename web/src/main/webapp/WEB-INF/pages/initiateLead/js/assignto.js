(function($) {
    var mxml;
    var sxml;

    $.ajax({
        url : "service/org/missionCodeUsers",
        dataType : "xml"
    }).done(function(xml) {
        //console.log(xml);
        mxml = xml;
    });
    $.ajax({
        url : "service/user/userManagementAssoc",
        dataType : "xml"
    }).done(function(xml) {
        //console.log(xml);
        sxml = xml;
    });

    $.fn.extend({
        assignTo : function(item, id) {
            var supSel = $('.assign-to select.supervisor');
            var anaSel = $('.assign-to select.analyst');
            //console.log("assignTo item[" + item + "] id[" + id + "]");
            //console.log("assignTo preParse mxml[" + mxml + "] sxml["+sxml+"]");
            if (item == "mission") {
                $(supSel).empty().append('<option value="">None</option>');
                $(anaSel).empty().append('<option value="">None</option>');

                if (id === "None") { //Mission->None
                    reset(supervisors, supSel);
                    reset(analysts, anaSel);
                } else {
                    var missions = $(mxml).find("missionCode");
                    $.each(missions, function(i, m) {
                        if ($(m).attr("id") === id) {
                            $.each($(m).children(), function(j, u) {
                                var elem = $(u).text();
                                if(isSupervisorRole(u)){
                                    // populate supervisors
                                	populate(supervisors, supSel, elem);
                                } else if(isAnalystRole(u)) {
	                                // populate analysts
	                                populate(analysts, anaSel, elem);
                                }
                            });
                        }
                    });
                }
            } else if (item == "supervisor") {
                if (id === "" && getMission() == "") { //Supervisor->None and Mission->None
                    reset(analysts, anaSel);
                    $(anaSel).prop("selectedIndex",0);
                } else {
                    $(anaSel).empty().append('<option value="">None</option>');
                    var arr;
                    if (id === "") { // Supervisor->None selection
                        arr = $(mxml).find("missionCode");
                        id = getMission();
                    } else {
                        arr = $(sxml).find("supervisor");
                    }
                    $.each(arr, function(i, s) {
                        if ($(s).attr("id") === id) {
                            $.each($(s).children(), function(j, u) {
                                // populate if analyst belongs to mission
                                if(isAnalystRole(u) && isInMission(u)) {
                                    var elem = $(u).text();
                                	populate(analysts, anaSel, elem);
                                }
                            });
                        }
                    });
                }
            } else if (item == "analyst") {
            }

            function reset(arr, sel) {
                //console.log("reset arr[" + arr + "] sel[" + sel + "]");
                $(sel).empty().append('<option value="">None</option>');
                $.each($(arr), function(k, s) {
                    if (k != 0) {
                        var uid = s.substring(0, s.indexOf(":"));
                        uid = s.split(":")[0];
                        var name = s.split(":")[1];
                        $(sel).append($("<option></option>").val(uid).text(name));
                    }
                });
            }

            function populate(arr, sel, elem) {
                //console.log("populate arr[" + arr + "] sel[" + sel + "] elem[" + elem + "]");
                $.each($(arr), function(k, s) {
                    var uid = s.substring(0, s.indexOf(":"));
                    if (uid === elem) {
                        uid = s.split(":")[0];
                        var name = s.split(":")[1];
                        //console.log("uid[" + uid + "] elem[" + elem + "] name[" + name + "]");
                        $(sel).append($("<option></option>").val(uid).text(name));
                    }
                });
            }

            function getMission() {
                var m = $('.assign-to select.mission').find("option:selected").val();
                if (m !== null && m !== "None")
                    return m.split(":")[0];
                else
                    return "";
            }

            function isInMission(elem) {
            	var inMission = false;
                var id = getMission();
                if(id === "") {
                    inMission = true;
                } else {
                    var missions = $(mxml).find("missionCode");
                    $.each(missions, function(i, m) {
                        if ($(m).attr("id") === id) {
                            $.each($(m).children(), function(j, u) {
                                if($(u).text() === $(elem).text()) {
                                    inMission = true;
                                }
                            });
                        }
                    });
                }

                //console.log(" mission["+id+"] elem[" + $(elem).text() + "] inMission["+inMission+"]");
                return inMission;
            }

            function isSupervisorRole(elem) {
            	var isRole = ($(elem).attr("role") == "su");
            	//console.log("isSupervisorRole elem[" + $(elem).text() + "] isRole["+isRole+"]");
            	return isRole;
            }

            function isAnalystRole(elem){
            	var isRole = ($(elem).attr("role") == "an" || $(elem).attr("role") == "sa");
            	//console.log("isAnalystRole elem[" + $(elem).text() + "] isRole["+isRole+"]");
            	return isRole;
            }
        }
    });
})(jQuery);

// --------------------------------------------------------------------------------
// ----------- Assign To selection
// --------------------------------------------------------------------------------
var missionCodes = [];
var supervisors = [];
var analysts = [];
$(function() {
    $(".assign-to select.mission").find("option").each(function(e) {
        missionCodes.push("'" + $(this).val() + "'");
    });
    $(".assign-to select.supervisor").find("option").each(function(e) {
        supervisors.push($(this).val() + ":" + $(this).text());
    });
    $(".assign-to select.analyst").find("option").each(function(e) {
        analysts.push($(this).val() + ":" + $(this).text());
    });

    // Update the supervisors drop down with the current supervisors for the mission
    $('.assign-to select.mission').on("change", function() {
    	$(".assign-to label, .assign-to option").removeClass("active");
    	$(this).prev().addClass("active");
    	$(this).find("option:selected").addClass("active");
        var mission = $(this).find("option:selected").val();
        $(this).assignTo('mission', mission.split(":")[0]);
        checkAssignTo();
    });
    // Update the analysts drop down with the current supervisors for the mission
    $('.assign-to select.supervisor').on("change", function() {
    	$(".assign-to label, .assign-to option").removeClass("active");
    	$(this).prev().addClass("active");
    	$(this).find("option:selected").addClass("active");
        var supervisorId = $(this).find("option:selected").addClass("active").val();
        $(this).assignTo("supervisor", supervisorId.split(":")[0]);
        checkAssignTo();
    });
    $('.assign-to select.analyst').on("change", function() {
    	$(".assign-to label, .assign-to option").removeClass("active");
    	$(this).prev().addClass("active");
    	$(this).find("option:selected").addClass("active");
        //$(this).assignTo("analyst");
    	checkAssignTo();
    });
});
function checkAssignTo() {
	var valid = true;
	var mission = $("#assign-to-form :input.mission").val();
	var supervisor = $("#assign-to-form :input.supervisor").val();
	var analyst = $("#assign-to-form :input.analyst").val();
	if(mission === "None" && supervisor === "" && analyst === "") {
		valid = false;
		$("#assign-to-form div:last").showCustomError("Assign To is required");
	} else {
		$("#assign-to-form div:last").hideError();
	}

	return valid;
}
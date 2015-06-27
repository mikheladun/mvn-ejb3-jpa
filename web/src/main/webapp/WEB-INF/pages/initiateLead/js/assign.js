var missionToUserMap;
var supervisorToAnalsystMapping;
$(function() {
    $.ajax({
        url : "service/org/missionCodeUsers",
    }).done(function(xml) {
        console.log(xml);
        missionToUserMap = xml;
    });
    $.ajax({
        url : "service/user/userManagementAssoc",
    }).done(function(xml) {
        console.log(xml);
        supervisorToAnalsystMapping = xml;
    });

    console.log("Entered Assign java script!!!!");

    var missions = [];
    var i = 0;
    var myObject = {};
    // store the current Mission options
    $('#MissionSelect option').each(function() {
        // console.log("Inside!!!");
        myObject = new Object();
        myObject.val = $(this).val();
        myObject.text = $(this).text();
        missions[i] = myObject;
        i++;
    });
    console.log("Mission count =" + missions.length);

    // store all the current supervisors options
    var supervisors = [];
    i = 0;
    $('#SupervisorSelect option').each(function() {
        myObject = new Object();
        myObject.val = $(this).val();
        myObject.text = $(this).text();
        supervisors[i] = myObject;
        i++;
    });
    console.log("Supervisor count= " + supervisors.length);

    // store all the current analyst options
    var analysts = [];
    i = 0;
    $('#AnalystSelect option').each(function() {
        // myObject = new NameValue($(this).val(), $(this).text() );
        myObject = new Object();
        myObject.val = $(this).val();
        myObject.text = $(this).text();
        analysts[i] = myObject;
        i++;
    });

    console.log("Total Analyst Count=" + analysts.length);
    // Update the supervisors drop down with the current supervisors for the
    // mission
    $('.assign-to select.mission').on("change",function() {
        $(this).prev().addClass("active").parent().find("label").not($(this).prev()).removeClass("active");
        console.log("Mission Drop Down change!!!");
        console.log(" selected text=" + $('#MissionSelect :selected').val() + " selected value=" + $('#MissionSelect :selected').text());
        var missionId = $('#MissionSelect :selected').val();
        console.log("selected item is " + missionId);
        if (missionId !== null && missionId !== "None") {
            console.log(" Mission Id is not null ");
            missionId = missionId.split(":")[0];
            console.log(" Mission Id [" + missionId + "]");
            var analystInMission = getAllAnalystInMission(missionId);
            if (analystInMission !== null) {
                filterAnalystsDropDown(analystInMission);
                filterSupervisorInMissionDropDown(analystInMission);
            }
        }
    });

    // Update the supervisors drop down with the current supervisors //for the
    // mission
    $('.assign-to select.supervisor').on("change", function() {
        $(this).prev().addClass("active").parent().find("label").not($(this).prev()).removeClass("active");
        console.log("Supervisor Drop Down change!!!");
        console.log(" selected text=" + $('#SupervisorSelect :selected').val() + " selected value=" + $('#SupervisorSelect :selected').text());
        var supervisorId = $('#SupervisorSelect :selected').val();
        console.log("selected supervisor id is " + supervisorId);
        if (supervisorId !== null && supervisorId !== "None") {
            console.log(" supervisorId Id is not null ");
            supervisorId = supervisorId.split(":")[0];
            console.log(" supervisorId Id [" + supervisorId + "]");
            var analystForSupervisor = getAllAnalystForSupervisor(supervisorId);
            if (analystForSupervisor !== null) {
                filterAnalystsDropDown(analystForSupervisor);
            }
        }
    });
    $('.assign-to select.analyst').on("change", function() {
        $(this).prev().addClass("active").parent().find("label").not($(this).prev()).removeClass("active");
        console.log("assignTo analyst[" + $(this).find("option:selected").text() + "]");
    });

    // get the list of analyst assign to a mission
    function getAllAnalystInMission(missionId) {
        console.log("Entered getAllAnalystInMission fcn...");
        var id;
        var userObject = {};
        var userList = [];
        i = 0;
        $(missionToUserMap).find("missionCode").each(function() {
            id = $(this).attr("id");
            if (id == missionId) {
                // Get all the users associated with this
                $(this).find("id").each(function() {
                    userObject = new Object();
                    userObject.id = $(this).text();
                    userList[i] = userObject;
                    i++;
                });
                // break;
            }
        });

        return userList;
    }
    // Get all analyst for a given Superviors
    function getAllAnalystForSupervisor(supervisorId) {
        console.log("Entered getAllAnalystForSupervisor fcn...id=" + supervisorId);
        var id;
        var userList = [];
        var userObject = {};
        i = 0;
        $(supervisorToAnalsystMapping).find("supervisorId").each(function() {
            id = $(this).attr("id");
            if (id == supervisorId) {
                console.log("Supervisor Id matched in xml" + id);
                // Get all the users assocated with this
                $(this).find("id").each(function() {
                    userObject = new Object();
                    userObject.id = $(this).text();
                    userList[i] = userObject;
                    i++;
                    console.log("User id " + userObject.id + " in Suprvisor Id " + id);
                });
                // break;
            }
        });

        return userList;
    }
    // Filter analsysts Drop Down with analsts assigned to mission
    function filterAnalystsDropDown(analystsList) {
        console.log("Inside filterAnalystsDropDown..count" + analystsList.length);
        // clear the analyst drop down and set the first option to None
        if (analystsList !== null) {
            $("#AnalystSelect").empty();
            $("#AnalystSelect").append("<option>None</option>");
        }
        // Filter analyst that are in mission
        var startIndex = 1;
        var mId = null;
        for (index = 0; index < analystsList.length; ++index) {
            mId = analystsList[index];
            // find the name of the analsyst from the master list
            var item = null;
            for (idx = 0; idx < analysts.length; ++idx) {
                item = analysts[idx];
                if (item.val == mId.id) {
                    console.log("Analyst id match=" + item.val);
                    $("#AnalystSelect").append($("<option></option>").val(item.val).text(item.text));
                    startIndex++;
                    break;
                }
            }
        }
    }

    // Filter analysts that are in mission but have supervisor role
    function filterSupervisorInMissionDropDown(analystInMission) {
        console.log("Inside filterAnalystsDropDown..count" + analystInMission.length);
        // clear the analyst drop down and set the first option to None
        if (analystInMission !== null) {
            $("#SupervisorSelect").empty();
            $("#SupervisorSelect").append("<option>None</option>");
        }
        // Filter analyst that are in mission
        var startIndex = 1;
        var mId = null;
        for (index = 0; index < analystInMission.length; ++index) {
            mId = analystInMission[index];
            // match the name of the analyst from the master list
            var item = null;
            for (idx = 0; idx < supervisors.length; ++idx) {
                item = supervisors[idx];
                if (item.val == mId.id) {
                    console.log("Analyst id=" + mId.id + " supervisor id" + "match=" + item.val);
                    $("#SupervisorSelect").append($("<option></option>").val(item.val).text(item.text));
                    startIndex++;
                    break;
                }
            }
        }
    }
});
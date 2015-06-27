(function($) {
	$.dialog = { CANCEL : "CANCEL", OK : "OK", CONFIRM : "CONFIRM" };

	$.fn.extend({
    	confirmation : function(t, m, fn) {
            $("#dialog-confirm").attr("title", t);
            $("#dialog-confirm .body").attr("id","dialogContent").html(m);
            $("#dialog-confirm").removeClass("hidden").dialog({
                resizable : false,
                modal : true,
                buttons : {
                    Cancel : function(e) {
                        $(this).dialog("close");
                        if(typeof fn === "function") fn(e, $.dialog.CANCEL);
                        e.preventDefault();
                    },
                    "Confirm" : function(e) {
                        $(this).dialog("close");
                        if(typeof fn === "function") fn(e, $.dialog.CONFIRM);
                        e.preventDefault();
                    },
                    "OK": function(e) {
                        $(this).dialog("close");
                        if(typeof fn === "function") fn(e, $.dialog.OK);
                        e.preventDefault();
                    }
                }
            });
            $("#dialog-confirm").prev().find("span.ui-dialog-title").attr("id","dialogTitle").html(t);
            $("#dialog-confirm").next().children("div:first").attr("id","dialogButton");
            $("#dialog-confirm").next().find("button").each(function(e){
                var span = $(this).find("span.ui-button-text");
                if("Cancel" == $(span).html()) {
                    $(span).parent().addClass("cancel");
                }
            });

            var buttons = $("#dialog-confirm").next().find("button");
            $(buttons[0]).attr("id","dialogCancelLink"); //cancel link
            $(buttons[1]).attr("id","dialogConfirmButton"); //confirm button
            $(buttons[2]).attr("id","dialogOkButton").hide(); //hide OK button
            return $("#dialog-confirm");
        }
    });
})(jQuery);
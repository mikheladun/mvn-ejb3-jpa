//TODO add comments
(function($) {
    $.fn.extend({
        // --------------------------------------------------------------------------------
        // ----------- Data-table population
        // --------------------------------------------------------------------------------
        dataTable : function() {
            var table = this.find("table.data-table");
            this.removeClass("hidden");
            $(table).removeClass("hidden").hide().fadeIn();
            return $(table);
        },
        insertRow : function(row,data,fn) {
            var tr = $("<tr></tr>").addClass(((this.find("tbody").children().length + 1) % 2 == 0) ? "table-even" : "table-odd");
            var btn = $('<input type="image" name="del" class="image remove" src="/leadtrac-web/resources/images/m.png" />');
            var rowNum = $('<input type="hidden" name="row" />').val(row);

    		$.each(data,function(i,col){
				$(tr).append($("<td></td>").append(col));
    		});

            $(tr).append($('<td class="no-table-style"></td>').append(rowNum).append($(btn)));
            this.children("tbody").append($(tr));
            $(tr).hide().fadeIn();

            if(typeof fn === "function") fn();
        },
        updateRow : function(row,data,fn) {
            this.children("tbody").children("tr").each(function() {
                var rowNum = $(this).children("td:last").find('input[type="hidden"][name="row"]').val();
                if(rowNum === row) {
            		$.each($(this).children("td"),function(i,col){
            			//TODO don't override <a> in first
        				$(col).html(data[i]);
            		});
                }
            });

            if(typeof fn === "function") fn();
        },
        deleteRow : function(row,fn) {
            var c = this;
            this.confirmation("Delete Confirmation","Are you sure you want to delete?",function(e,t) {
                switch(t) {
	                case "CONFIRM" : case "OK" :
	                	$.each(c.find("tbody tr"), function(i,el){
	                        var rowNum = $(el).children("td:last").find('input[type="hidden"][name="row"]').val();
	                        console.log("rowNum:"+rowNum + " row:"+row);
	                        if(parseInt(rowNum) === parseInt(row)) {
	    	                    $(el).fadeOut(400, function() {
		    	                    if ($(el).siblings().length == 0) {
		    	                        $(c).find("table.data-table").fadeOut().hide();
		    	                    }
	    	                        $(el).remove();
		    	                    if(typeof fn === "function") fn();
	    	                    });
	                        }
	                    });
                }
                e.preventDefault();
            });
        },
        createRow : function(elem, callback) {
            var tr = $("<tr></tr>").addClass(((this.find("tbody").children().length + 1) % 2 == 0) ? "table-even" : "table-odd");
            var btn = $('<input type="image" name="del" class="image remove" src="/leadtrac-web/resources/images/m.png" />');
            var rowNum = $('<input type="hidden" name="rowNum" />').val($(elem).id());
            $(tr).append($('<td class="no-table-style"></td>').append(rowNum).append($(btn)));
            this.children("tbody").append($(tr));
            $(tr).hide().fadeIn();

            if(typeof callback === "function") callback($(tr));
        },

        attachEvent : function(evt,target,fn) {
        	this.find(""+target).off(""+evt).on(""+evt,fn);
        }
    });
})(jQuery);
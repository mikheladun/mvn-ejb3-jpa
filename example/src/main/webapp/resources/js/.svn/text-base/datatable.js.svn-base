//TODO add comments
(function($) {
    $.tableArray = [];

    $.fn.extend({
        // --------------------------------------------------------------------------------
        // ----------- Data-table population
        // --------------------------------------------------------------------------------
        dataTable : function() {
            console.log("datatable: ");
            var table = this.find("table.data-table");
            var tbody = $(table).children("tbody");
            if($(tbody).children().length == 0) {
                $(table).removeClass("hidden").hide().fadeIn();
            }
            return $(table);
        },
        insertRow : function(elem, callback) {
            console.log("insertRow: " + elem);
            console.log(elem);
            var tr = $("<tr></tr>").addClass(((this.find("tbody").children().length + 1) % 2 == 0) ? "table-even" : "table-odd");
            var btn = $('<input type="image" name="del" class="image remove" src="/leadtrac-web/resources/images/m.png" />');
            var rowNum = $('<input type="hidden" name="rowNum" />').val($(elem).id());
            $(tr).append($('<td class="no-table-style"></td>').append(rowNum).append($(btn)));
            this.children("tbody").append($(tr));
            $(tr).hide().fadeIn();

            if(typeof callback === "function") callback($(tr));
        },
        updateRow : function(elem, callback) {
            console.log("updateRow: " + elem);
            console.log(elem);
            var table = this;
            var tr;
            console.log(this);
            this.children("tr").each(function() {
                console.log($(this));
                var rowNum = $(table).children("td:last").find('input[type="hidden"][name="rowNum"]').val();
                console.log("updateRow rowNum["+rowNum +"]");
                if(rowNum == $(elem).id()) {
                    tr = $(this);
                }
            });
            if(typeof callback === "function") callback($(tr));
        },
        deleteRow : function(elem, callback) {
            console.log("removeRow: " + elem);
            console.log(elem);
            if (confirm('Are you sure you want to delete?')) {
                var row = $(elem).parent().parent();
                $(row).fadeOut(400, function() {
                    this.remove();
                });
                if (this.find("tbody").children().length == 1) {
                    this.parent().fadeOut();
                }

                if(typeof callback === "function") callback();
            }
        }
    });
})(jQuery);

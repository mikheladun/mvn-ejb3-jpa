//TODO add comments
(function($) {
    $.fn.extend({
        // --------------------------------------------------------------------------------
        // ----------- Dynamic drop-down selection
        // --------------------------------------------------------------------------------
        multiSelect : function(max) {
            var name = this.attr("name");
            var sel = this.next("select");
            var opt = $(sel).find("option:selected");
            var spanItems = $(this).parent().children("span.multiselect").length;
            var selectOptions = $(sel).children("option").length - 1;
            if((spanItems == max) || (spanItems == selectOptions)) {
                e.preventDefault();
            } else {
                if (name == "add") {
                    add(this);
                } else if (name == "del") {
                    del(this);
                }
            }

            function add(elem) {
                if ($.trim($(opt).val()) != '') {
                    var src = $(elem).attr("src");
                    var alt = $(elem).attr("alt");
                    var img = $(elem).clone().removeClass("add").addClass("remove").attr({
                                id : '', name : 'del',
                                src : src.substring(0, src.length - 5) + 'm.png',
                                alt : alt.replace('add a new', 'remove'),
                                title : alt.replace('add a new', 'remove')
                            }).on("click", function(e) {
                                $(this).multiSelect();
                                //$.Leadtrac.multiselect($(this), max, e);
                            });
                   var newsel = $(sel).clone().prop("selectedIndex", $(sel).prop("selectedIndex"));
                   $(newsel).addClass("hidden");
                   var span = $("<span></span>");
                   var label = $('<label for="">' + $(opt).text() + "</label>");
                   $(span).addClass("multiselect").append($(newsel)).append($(label)).append($(img)).hide();
                   $(elem).parent().append($(span));
                   $(span).fadeIn();
                   $(elem).next().find('option[value="' + $(opt).val() + '"]').attr("disabled", "disabled");
                   $(sel).prop("selectedIndex", 0);

                   if((spanItems + 1) == 1) {
                       var delImg = $(elem).next().next().removeClass("hidden").hide();
                       $(delImg).fadeIn();
                   }
                   if((spanItems + 1) == max) {
                       $(elem).next().find("option").not(":first").attr("disabled", "disabled");
                   }
                }
            };

            function del(elem) {
                var container = $(elem).parent().parent(); 
                var sel = $(container).children("select:first");
                spanItems = $(container).children("span.multiselect").length;
                selectOptions = $(sel).children("option").length - 1;
                if(selectOptions == -1) {
                    $(elem).prev().prop('selectedIndex', 0);
                } else {
                    if(confirm('Are you sure you want to delete?')) {
                        $(elem).parent().children().fadeOut();
                        $(elem).parent().remove();

                        elem = $(container).children("input.image").first();
                        if(spanItems == 1) {
                            var delImg = $(elem).next().next().fadeOut();
                            $(delImg).hide().addClass("hidden");
                        }
                        $(sel).find("option[disabled]").removeAttr("disabled");
                        $(container).children("span.multiselect").find("label").each(function() {
                            var label = $(this);
                            $(sel).find("option").filter(function() { return $.trim( $(this).text() ) == $(label).text(); }).attr("disabled", "disabled");
                        });
                    } else {
                        $(elem).prev().focus();
                        jQuery.event.preventDefault();
                    }
                }
            };
        }
    });
})(jQuery);

$(function(){
    // --------------------------------------------------------------------------------
    // ----------- Multiselect drop-down selection
    // --------------------------------------------------------------------------------
    $('.multiselect input[type="image"][name="del"]').on("click", function(e) {
        $(this).multiSelect();
        e.preventDefault();
    });
    $('.multiselect input[type=image][name="add"]').on("click", function(e) {
        if($(this).next("select").attr("name") == "coc") {
            //max selection for COC(s) fields 
            var max = 2;
            $(this).multiSelect(max);
        } else {
            $(this).multiSelect();
        }
        e.preventDefault();
    });
});
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
            var spans = this.parent().children("span.multiselect");
            var spanItems = spans.length;
            var selectOptions = $(sel).children("option").length - 1;
            //console.log("multiSelect ["+name+"] max["+max+"] spanItems["+spanItems+"] selectOptions["+selectOptions+"] opt["+$(opt).val()+"]");
            if((spanItems === (max-1)) || (spanItems === selectOptions)) {
                //e.preventDefault();
            } else if("disabled" === $(opt).attr("disabled")) {
                $(sel).prop("selectedIndex", 0);
            } else {
                if (name === "add") {
                    addOption(this);
                    resetOptions(this.parent());
                } else if (name === "del") {
                    removeOption(this);
                }
            }

            function addOption(elem) {
            	///console.log("multiselect->addOption()");
                if ($.trim($(opt).val()) !== 'Select...') {
                    var src = $(elem).attr("src");
                    var alt = $(elem).attr("alt");
                    var img = $(elem).clone().removeClass("add").addClass("remove").attr({
                                id : '', name : 'del',
                                src : src.substring(0, src.length - 5) + 'm.png',
                                alt : alt.replace('add new', 'remove'),
                                title : alt.replace('add new', 'remove')
                            }).on("click", function(e){
                                $(this).confirmation("Delete Confirmation","Are you sure you want to delete?",function(e,a) {
                                    switch (a) {
                                        case "CONFIRM": case "OK":
                                            $(img).multiSelect();
                                            break;
                                        default :
                                            $(img).parent().siblings("select").focus();
                                    }
                                    e.preventDefault();
                                });
                                e.preventDefault();
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

            function removeOption(elem) {
            	//console.log("multiselect->removeOption()");
                var container = $(elem).parent().parent(); 
                var sel = $(container).children("select:first");
                spanItems = $(container).children("span.multiselect").length;
                selectOptions = $(sel).children("option").length - 1;
                //console.log("multiSelect->removeOption() selectOptions["+selectOptions+"]");
                if(selectOptions == -1) {
                    $(elem).prev().prop('selectedIndex', 0);
                } else {
                        $(elem).parent().children().fadeOut();
                        $(elem).parent().remove();

                        elem = $(container).children("input.image").first();
                        if(spanItems == 1) {
                            var delImg = $(elem).next().next().fadeOut();
                            $(delImg).hide().addClass("hidden");
                        }
                        $(sel).find("option[disabled]").removeAttr("disabled");
                        disableOptions($(container));
                }
                resetOptions($(container));
            };

            function disableOptions(c) {
            	//console.log("multiselect->disableOptions()");
                $(c).children("span.multiselect").find("label").each(function() {
                    var label = $(this);
                    $(c).children("select:first").find("option").filter(function() { return $.trim( $(this).text() ) === $(label).text(); }).attr("disabled", "disabled");
                });
            };

            function resetOptions(c) {
            	//console.log("multiselect->resetOptions()");
            	//console.log(c);
        		var index = 0;
            	$.each($(c).find("span.multiselect select"),function(){
            		var name = $(this).attr("name");
            		if(typeof name !== "undefined" && name !== null && $.trim(name) !== "") {
	                	name = name.replace(/(\w+)(.*)\[\d+\](.*)/, "$1$2["+(++index)+"]$3");
	            		$(this).attr("name",name).val($(this).val());
	            		//console.log(name + " : " + $(this).val());
            		}
            	});
            };
        }
    });
})(jQuery);

$(function(){
    // --------------------------------------------------------------------------------
    // ----------- Multiselect drop-down selection
    // --------------------------------------------------------------------------------
    $('.multiselect input[type="image"][name="del"]').on("click", function(e) {
    	var img = $(this);
        $(this).confirmation("Delete Confirmation","Are you sure you want to delete?",function(e,a) {
            switch (a) {
                case "CONFIRM": case "OK":
                    $(img).multiSelect();
                    break;
                default :
                    $(img).parent().siblings("select").focus();
            }
            e.preventDefault();
        });
        e.preventDefault();
    });
    $('.multiselect input[type=image][name="add"]').on("click", function(e) {
        if($(this).next("select").hasClass("coc")) {
            //max selection for COC(s) fields 
            var max = 2;
            $(this).multiSelect(max);
        } else {        		
            $(this).multiSelect();
        }
        e.preventDefault();
    });
    $(".multiselect select").on("change",function(e){
    	if($.leadtrac.selectRegex.test($(this).val())) {
    		$(this).next(":hidden").removeClass("hidden").hide().fadeIn();
    	} else if($(this).siblings("span.multiselect").length == 0) {
    		$(this).next().addClass("hidden").show().fadeOut();
    	}
    });
});
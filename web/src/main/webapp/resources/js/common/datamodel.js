(function($) {
    $.model = $("<form></form>").attr("id","datamodel");

    $.fn.extend({
        save : function(fn) {
        	var id = this.id();
            //console.log('save() : ' + id);
        	var elems = this.find("input,select,textarea").not("input[type=image],input[type=button],input[type=submit],input[type=hidden][name=id]");
        	$.each(elems, function(){
        		var name = $(this).attr("name");
        		if(typeof name !== "undefined") {
	        		name = name.replace(/(\w+)\[0\](.*)/, "$1[" + id + "]$2");
	        		$($(this).clone().attr("name",name)).val($(this).val()).removeAttr("disabled").appendTo($.model);
        		}
        	});

            if(typeof fn === "function") {
            	fn();
            }
        },
        update : function(fn) {
            //console.log('update() : ' + this.id());
        	var id = this.id();
        	var elems = this.find("input,select,textarea").not("input[type=image],input[type=button],input[type=submit],input[type=hidden][name=id]");
            $.each(elems, function() {
            	var name = $(this).attr("name");
            	if(typeof name !== "undefined") {
	        		name = name.replace(/(\w+)\[0\](.*)/, "$1[" + id + "]$2");
            		var el = $.model.find("input,select,textarea").filter(function() {
	        			return ($(this).attr("name") === name);
	        		});
	        		if(el.length === 0) {
	        			$($(this).clone().attr("name",name)).val($(this).val()).removeAttr("disabled").appendTo($.model);
	        		} else {
	        			$(el).val($(this).val()).removeAttr("disabled");
	        		}
            	}
            });

            if(typeof fn === "function") {
            	fn();
            }
        },
        destroy : function(id,fn) {
        	//console.log("destroy() : " + id);
        	var elems = this.find("input,select,textarea").filter(function() { 
        		return new RegExp("ltLeadsModel\\[" + id + "\\].+").test($(this).attr("name"));
        	});
        	$.each(elems, function(i) {
        		var name = $(this).attr("name");
        		//console.log("destroy() " + name + " : " + $(this).val());
        		$(this).remove();
        	});

        	if(typeof fn === "function") {
        		fn();
        	}
        },
        id : function() {
            //console.log('id() ' + this.children('input[type="hidden"][name="id"]').val());
            return this.children('input[type="hidden"][name="id"]').val().split(":")[0];
        },
        isNew : function() {
        	//console.log("isNew() " + this.children('input[type="hidden"][name="id"]').val());
        	return (this.children('input[type="hidden"][name="id"]').val().split(":")[1] === "true"); 
        },
        isValid : function() {
            //console.log('isValid : ' + this);
            return true;
        },
        errors : function() {
            console.log('errors : ' + this);
        }
    });
})(jQuery);

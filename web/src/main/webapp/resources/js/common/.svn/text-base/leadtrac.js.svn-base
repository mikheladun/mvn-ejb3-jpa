// --------------------------------------------------------------------------------
// ----------- Leadtrac namespace
// --------------------------------------------------------------------------------
(function($) {
    $.leadtrac = {
        phoneMask : '(###) ###-####',
        phoneBlank : '(___) ___-____',
        phoneRegex : /^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/,
        emailMask : 'sample123@abc.com',
        emailRegex : /^[^@]*@[^@]*$/,
        selectRegex : /^(?!Select\.\.\.$).*$/,
        devMode : true,
        formElems : "input,select,textarea"
    };

    $.fn.extend({
        // --------------------------------------------------------------------------------
        // ----------- Basic Form validation
        // --------------------------------------------------------------------------------
        checkRequired : function(regex) {
            var expr = typeof regex === 'undefined' ? /^.+$/ : regex;
            //console.log("checkRequired regex[" + regex + "] expr[" + expr + "] name[" + this.attr("name") + "] val[" + this.val() + "] valid[" + expr.test(this.val()) +"]");
            if ($.trim(this.val()) === '' || !expr.test(this.val())) {
                this.showError(" is required");
            } else {
                this.hideError();
            }
        },
        checkFormat : function(expr, msg) {
            //console.log("checkFormat expr[" + expr + "] val[" + this.val() + "] valid[" + expr.test(this.val()) +"]");
        	if (!expr.test(this.val())) {
        		if(typeof msg === "undefined") {
        			this.showError(" is incorrect");
        		} else {
	                this.showCustomError(msg);
        		}
            } else {
                this.hideError();
            }
        },
        checkPhone : function() {
        	if($.trim(this.val()) !== "") {
        		this.checkFormat($.leadtrac.phoneRegex);
        	}
        },
        checkEmail : function() {
        	if($.trim(this.val()) !== "") {
        		this.checkFormat($.leadtrac.emailRegex);
        	}
        },
        checkForm : function() {
        	var elems = this.find("input.required,select.required,textarea.required").filter(function() {
        		return $(this).attr("name") !== "undefined" && $(this).attr("name") !== "" && !$(this).parent().hasClass("hidden");
        	});
        	$.each(elems,function(){
        		if($(this).is("select")) {
        			$(this).checkRequired($.leadtrac.selectRegex);
        		} else {
        			$(this).checkRequired();
        		}
        	});
        	var errors = this.find("span.error").filter(function(){
        		return !$(this).hasClass("hidden");
        	});
        	return errors.length === 0 ? true : false;
        },
        showCustomError : function(msg) {
        	var elemId = $(this).attr("id");
            var span = '<span id="' + elemId + '-error" class="error">' + msg + '</span>';
            if (this.next('span.error').length == 0) {
                this.parent().append($(span).hide());
                this.next('span.error').fadeIn();
            } else {
            	this.next('span.error').removeClass("hidden").show().html(msg);
            }
            this.addClass('error');
        },
        showError : function(msg) {
            var msg = getLabel(this) + msg;
        	this.showCustomError(msg);

            function getLabel(elem) {
                var name = $(elem).parent().find('label:first').text();
                name = name.substring(0, name.lastIndexOf(':'));
                return name;
            };
        },
        hideError : function() {
            this.next('span.error').fadeOut().hide().addClass("hidden");
            this.removeClass('error');
        },
        attachEvent : function(evt,target,fn) {
        	this.find(""+target).off(""+evt).on(""+evt,fn);
        },
    });
})(jQuery);

$(function() {
    // --------------------------------------------------------------------------------
    // ----------- Required form fields validation
    // --------------------------------------------------------------------------------
    $("input.required,textarea.required").on("blur", function(e) {
        $(this).checkRequired();
    });
    $("input.required,textarea.required").on("keyup", function(e) {
        if ($.trim($(this).val()) !== '') {
            $(this).checkRequired(/^.+$/);
        }
    });
    $("select.required").on("blur", function(e) {
        $(this).checkRequired($.leadtrac.selectRegex);
    });
    $("select.required").on("change", function(e) {
        $(this).checkRequired($.leadtrac.selectRegex);
    });
});
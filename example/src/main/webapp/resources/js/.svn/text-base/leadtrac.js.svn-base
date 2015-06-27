// --------------------------------------------------------------------------------
// ----------- Leadtrac namespace
// --------------------------------------------------------------------------------
(function($) {
    $.leadtrac = {
        pageModel : jQuery.parseJSON('{}'),
        phoneMask : '(###) ###-####',
        phoneRegex : /^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/,
        emailMask : 'sample123@abc.com',
        emailRegex : /^[^@]+@[^@]+$/,
    };

    $.fn.extend({
        // --------------------------------------------------------------------------------
        // ----------- Basic Form validation
        // --------------------------------------------------------------------------------
        checkRequired : function(regex) {
            //console.log("checkRequired obj[" + $(this) + "] regex[" + regex + "]");
            var expr = typeof regex === 'undefined' ? /^.+$/ : regex;
            if ($.trim(this.val()) == '' || !expr.test(this.val())) {
                var msg = (typeof regex === 'undefined') ? " is required" : " is invalid";
                this.showError(msg);
            } else {
                this.hideError();
            }
        },

        checkPhone : function() {
            this.checkRequired($.leadtrac.phoneRegex);
        },

        checkEmail : function() {
            this.checkRequired($.leadtrac.emailRegex);
        },

        showError : function(msg,custom) {
            var label = getLabel(this);
            var m = typeof custom === "undefined" ? label + msg : custom;
            var span = '<span class="error">' + m + '</span>';
            if (this.next('span.error').length == 0) {
                this.parent().append($(span).hide());
            }
            this.next('span.error').fadeIn();
            this.addClass('error');

            function getLabel(elem) {
                var name = $(elem).parent().find('label:first').text();
                name = name.substring(0, name.lastIndexOf(':'));
                return name;
            };
        },

        hideError : function() {
            this.next('span.error').fadeOut();
            this.removeClass('error');
        }
    });

})(jQuery);

$(function() {
    // --------------------------------------------------------------------------------
    // ----------- Required form fields validation
    // --------------------------------------------------------------------------------
    $("input.required,select.required,textarea.required").on("blur", function(e) {
        $(this).checkRequired();
    });
    $("input.required,select.required,textarea.required").on("keyup", function(e) {
        if ($.trim($(this).val()) != '') {
            $(this).checkRequired(/^.+$/);
        }
    });
    $("select.required").on("change", function(e) {
        $(this).checkRequired();
    });
});
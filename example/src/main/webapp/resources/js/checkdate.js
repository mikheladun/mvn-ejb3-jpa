/*******************************************************************************
 * ** Date Validator *** ** Author: Daniel Kolb ***
 ******************************************************************************/
// the generic mask, for normal input masking:
// Type 1: DD-MON-YYYY
var regexType1 = /(\d{2})-(\w{3})-(\d{4})/;
// Type 2: only numbers (DDMMYYYY [standard] or YYYYMMDD [exception])
// This kind of date requires a decision to be made on which format it's in.
// That decision can be made based on the first two digits.
var regexStandard = /\d{8}/;
var regexChooser = /\d{2}/;
// Once the decision is made, the excpetion regex can be applied if
// necessary.
var regexType2 = /(\d{4})(\d{2})(\d{2})/;

// normal format regex
var regexFormatted = /(\d{2})\/(\d{2})\/(\d{4})/;

var dateMask = '##/##/####';

(function($) {
    $.fn.extend({
        // General use function for checking the date; use this when
        // you have the formatted string in place. This version adds
        // a placeholder validation text like before.
        checkDate : function() {
            var dateString = this.val();
            var month = dateString.replace(regexFormatted, '$1');
            var day = dateString.replace(regexFormatted, '$2');
            var year = dateString.replace(regexFormatted, '$3');
            //console.log("checkDate dateString["+dateString+"] month[" + month + "] day[" + day + "] year[" + year + "]");
            if($.trim(dateString) != '' && dateString != '__/__/____') {
                if (!isDateValid(month, day, year)) {
                    this.showError(" is invalid");
                } else {
                    this.hideError();
                }
            }
            // Function to make sure an entered date is valid by
            // creating a Date object and checking the Date object
            // against the input. For use with checkDate(elem).
            // accepts: month, day, year
            // returns: true, false
            function isDateValid(m, d, y) {
                var date = new Date(y, m - 1, d);
                if (date.getFullYear() == y && date.getMonth() + 1 == m && date.getDate() == d) {
                    return true;
                } else {
                    return isNaN(date.getTime());
                }
            }
        },

        // ////////////////////////////////////////////////////////////////
        // pre-post paste code found at:
        // www.mattbenton.net/2012/01/jquery-plugin-paste-events/
        // ////////////////////////////////////////////////////////////////
        pasteEvents : function(delay) {
            if (delay == undefined)
                delay = 20;
            return this.each(function() {
                var $el = $(this);
                $el.on("paste", function() {
                    $el.trigger("prepaste");
                    setTimeout(function() {
                        $el.trigger("postpaste");
                    }, delay);
                });
            });
        }
    // ////////////////////////////////////////////////////////////////

    });
})(jQuery);

$(function() {
    // apply the mask to start with
    $("input.date").mask(dateMask);

    // clear out on blur
    $("input.date").blur(function() {
        if ($(this).val() == '__/__/____') {
            $(this).val('');
            $(this).hideError();
        } else {
            $(this).checkDate();
        }
    });

    // validate on keyup and paste
    $("input.date").bind('keyup paste', function() {
        $(this).checkDate();
    });

    // Before the paste happens, remove the mask.
    $("input.date").bind('prepaste', function() {
        $(this).unmask(dateMask).val('');
    }).pasteEvents();

    // After the paste...
    $("input.date").bind('postpaste', function() {
        // output var
        var out;
        // month dictionary
        var monthDictionary = {
            'JAN' : '01',
            'FEB' : '02',
            'MAR' : '03',
            'APR' : '04',
            'MAY' : '05',
            'JUN' : '06',
            'JUL' : '07',
            'AUG' : '08',
            'SEP' : '09',
            'OCT' : '10',
            'NOV' : '11',
            'DEC' : '12'
        };

        // type 1 check:
        if (regexType1.test($(this).val())) {

            // Grab the month text, check to see if it's in the dictionary,
            // and replace with the numerical value of the month.
            // Format, then replace text and mask.

            var m = $(this).val().replace(regexType1, '$2');
            if (m.toUpperCase() in monthDictionary) {
                // month exists, safe to replace
                var month = monthDictionary[m.toUpperCase()];
            } else {
                // month characters are invalid; replace with __
                // $('label[for="test"]').text('Error - MON
                // invalid.').css('color',
                // 'red');
                month = '__';
            }
            out = $(this).val().replace(regexType1, month + '$1$3');
            $(this).val(out).mask(dateMask);
        }

        // type 2 filter: (make decision on type)
        // Note: this applies to the only numbers input
        else if (regexStandard.test($(this).val())) {

            // Choose which type it is based on the first two digits
            // of the input (years will always have the first digits > 12).
            // Format if necessary, then reapply mask.

            var firstDigits = regexChooser.exec($(this).val());
            if (firstDigits >= 13) { // matches exception (YYYYMMDD)
                out = $(this).val().replace(regexType2, '$2$3$1');
                $(this).val(out).mask(dateMask);
            } else { // this is standard format (MMDDYYYY), so reapply the
                // mask
                $(this).mask(dateMask);
            }
        }

        else if (regexFormatted.test($(this).val())) {
            // paste matches MM/DD/YYYY, so reapply mask
            $(this).mask(dateMask);
        }

        // invalidate input on any other kind of paste, and focus the field
        else {
            // $('label[for="test"]').text('Not a valid paste.').css('color',
            // 'red');
            $(this).val('').mask(dateMask).focus();
        }
    });
    // This is to check validation against standard input
    $("input.date").change(function() {
        if ($(this).val() != '__/__/____' || $(this).val() != '') {
            $(this).checkDate();
        }
    });
});
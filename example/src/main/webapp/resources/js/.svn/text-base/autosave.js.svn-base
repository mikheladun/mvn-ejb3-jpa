(function($) {
    $.fn.extend({
        autoSave : function(data, callback) {
            var jqxhr = $.ajax({
                type : "POST",
                url : $(this).attr("action"),
                data : data,
                processData : false,
                success : success,
                dataType : "json"
            }).done(function(m) {
                alert("done : " + m.response);
            }).fail(function(m) {
                alert("fail : " + m.response);
            }).always(function(m) {
                alert("always : " + m.response);
            });
        },

        submit : function(form) {
            alert('submit: ' + form);
        },
    });
})(jQuery);
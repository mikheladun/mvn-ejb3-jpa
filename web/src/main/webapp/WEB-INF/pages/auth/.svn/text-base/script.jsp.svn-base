<script type="text/javascript">
    $(function() {
    	$("form input:first").focus();
        $(".agree input[type=button]").click(function() {
            $('.agree').hide();
            $('.login').removeClass('hidden').addClass('visible').show();
            $('.login input[name="j_username"]').focus();
        });

        $('.agree a[title="Cancel"]').on('click', function() {
            if ($.browser.msie) {
                window.close();
            }
        });

        $('.login .yui3-control-group input').on("keypress", function(e) {
            if (e.which == '13') {
                $("form").find('input[type="submit"]').click();
            }
        });

    });
</script>
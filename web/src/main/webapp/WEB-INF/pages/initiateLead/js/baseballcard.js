$(function() {
    $('.JohnHover').mouseover(function() {
        $('.JohnShow').show(0);
        $('.JaneShow').hide(0);
        $('.JimShow').hide(0);
        $('.JohnHover').css('background', '#CDCDCD');
        $('.JaneHover').css('background', '#f2f2f2');
        $('.JimHover').css('background', '#f2f2f2');
    });

    $('.JaneHover').mouseover(function() {
        $('.JohnShow').hide(0);
        $('.JaneShow').show(0);
        $('.JimShow').hide(0);
        $('.JohnHover').css('background', '#f2f2f2');
        $('.JaneHover').css('background', '#CDCDCD');
        $('.JimHover').css('background', '#f2f2f2');
    });

    $('.JimHover').mouseover(function() {
        $('.JohnShow').hide(0);
        $('.JaneShow').hide(0);
        $('.JimShow').show(0);
        $('.JohnHover').css('background', '#f2f2f2');
        $('.JaneHover').css('background', '#f2f2f2');
        $('.JimHover').css('background', '#CDCDCD');
    });

    $('.wrapper').mouseover(function() {
        $('.JohnShow').hide(0);
        $('.JaneShow').hide(0);
        $('.JimShow').hide(0);
        $('.JohnHover').css('background', '#f2f2f2');
        $('.JaneHover').css('background', '#f2f2f2');
        $('.JimHover').css('background', '#f2f2f2');
    });

    $('.top').mouseover(function() {
        $('.JohnShow').hide(0);
        $('.JaneShow').hide(0);
        $('.JimShow').hide(0);
        $('.JohnHover').css('background', '#f2f2f2');
        $('.JaneHover').css('background', '#f2f2f2');
        $('.JimHover').css('background', '#f2f2f2');
    });

    $('.right').mouseover(function() {
        $('.JohnShow').hide(0);
        $('.JaneShow').hide(0);
        $('.JimShow').hide(0);
        $('.JohnHover').css('background', '#f2f2f2');
        $('.JaneHover').css('background', '#f2f2f2');
        $('.JimHover').css('background', '#f2f2f2');
    });
});
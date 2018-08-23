$(function () {

    //Button Portfolio
    $(function(){
        $("#includedContent").load("button-rotate.html");
    });

    // Parallax
    $('.intro-section').parallax({
        imageSrc: 'img/bg-1.jpg',
        speed: 0.2
    });
    $('.services-section').parallax({
        imageSrc: 'img/preview.jpg',
        speed: 0.2
    });
    $('.contact-section').parallax({
        imageSrc: 'img/3d-modeli.jpg',
        speed: 0.2
    });

    // jQuery Scroll Up / Back To Top Image
    $.scrollUp({
        scrollName: 'scrollUp',      // Element ID
        scrollDistance: 300,         // Distance from top/bottom before showing element (px)
        scrollFrom: 'top',           // 'top' or 'bottom'
        scrollSpeed: 1000,            // Speed back to top (ms)
        easingType: 'linear',        // Scroll to top easing (see http://easings.net/)
        animation: 'fade',           // Fade, slide, none
        animationSpeed: 300,         // Animation speed (ms)
        scrollText: '', // Text for element, can contain HTML
        scrollImg: true            // Set true to use image
    });

    // ScrollUp Placement
    $(window).on('scroll', function () {

        // If the height of the document less the height of the document is the same as the
        // distance the window has scrolled from the top...
        if ($(document).height() - $(window).height() === $(window).scrollTop()) {

            // Adjust the scrollUp image so that it's a few pixels above the footer
            $('#scrollUp').css('bottom', '80px');

        } else {
            // Otherwise, leave set it to its default value.
            $('#scrollUp').css('bottom', '30px');
        }
    });

    $('.single-page-nav').singlePageNav({
        offset: $('.single-page-nav').outerHeight(),
        speed: 1500,
        filter: ':not(.external)',
        updateHash: true
    });

    $('.navbar-toggle').click(function () {
        $('.single-page-nav').toggleClass('show');
    });

    $('.single-page-nav a').click(function () {
        $('.single-page-nav').removeClass('show');
    });


    $("#box").click(function () {
        $("#box").animate({opacity: "0.1", left: "+=400"}, 1200)
            .animate({opacity: "0.4", top: "+=160", height: "20%", width: "20%"}, "slow")
            .animate({opacity: "1", left: "0", height: "100%", width: "100%"}, "slow")
            .animate({top: "0"}, "fast")
            .slideUp()
            .slideDown("slow").text('Изпратен') ;

        return false;

    });

});
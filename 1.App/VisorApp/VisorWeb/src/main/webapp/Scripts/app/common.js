/*--------------------------------------------------------------------------------------------------------- 
AJAX NAVEGACION - LOAD EFECTO
**//*---------------------------------------------------------------------------------------------------- */

// $.ajaxSetup({
//     beforeSend: function () {
//         $('body').addClass('loading');
//     },
//     complete: function () {
//         $('body').removeClass('loading');
//     }
// });

// var navSlide = function(){
    
// };

// navSlide.prototype = {
//   selectores : function(){
//   this.$html = $('html'),
//         this.$wrap = $('.view-wrap'),
//         this.$btnAjx = $('.nav_ajx');
//         this.eventAjx();
//     },
//     eventAjx : function(){
//     var that = this;
//     this.$html.on('click','.nav_ajx',function(event){
//     event.preventDefault();
//     var url = $(this).attr('href'),
//               $el = $(this);
//     if(!url || url == '' || url == '#'){
//     alert("Ingrese Ruta")
//     }else{
//             if(!$el.hasClass('btn_inner')){
//               that.$btnAjx.removeClass('active');
//             }          
//             $el.addClass('active');
//             that.navAjx(url);
//           }  
//     })
//     },
//     navAjx : function(url){ 
//         var that = this; 
//         $.ajax({
//           url : url,
//           success:function(data){    
//             that.$wrap.empty();
//             that.$wrap.html(data);
//           }
//         });  
//     },
//     init : function(){
//     this.selectores();  
//     }
// }

// var navSlide = new navSlide();
// navSlide.init();

// navSlide.navAjx('cliente.html');


/*--------------------------------------------------------------------------------------------------------- 
RESPONSIVE PANEL TABLET
**//*---------------------------------------------------------------------------------------------------- */

var tablet = function(){


    $(document).ready( function() {

        if ($(window).width() < 769) {
            $('[data-toggle=offcanvas]').on('click', function () {
                $('.content-colum').toggleClass('active')          
            });
        }
        else {}
    });

    $(window).resize(function() {

        if ($(window).width() < 769) {
            $('[data-toggle=offcanvas]').off('click');
            $('[data-toggle=offcanvas]').on('click', function () {
                $('.content-colum').toggleClass('active') 
            });
        }
        else {$('.content-colum').removeClass('active');}
    });
    
};

tablet();

var generales = function(){
    $('ul.list-actividad').find('li:nth-child(even)').addClass('interlineado');
};



/*--------------------------------------------------------------------------------------------------------- 
CLICK REMOVE ASIDE PANEL
**//*---------------------------------------------------------------------------------------------------- */

var MenuPanel = function(){

    $('body').on('click', '.btn-show', function () {
        var $el = $(this);
        if (!$el.hasClass('active')) {
            $('.menu').animate({
                left: '-185'
            });
            $el.addClass('active');
        } else {
            $('.menu').animate({
                left: '0'
            });
            $el.removeClass('active');
        }
    });        

}
MenuPanel();

/*--------------------------------------------------------------------------------------------------------- 
NAV
**//*---------------------------------------------------------------------------------------------------- */

var nav = function(){

    var $wrap = $('.nav-wrap'),        
    $btn = $wrap.find('li.nav-01 > span'),
    $content = $wrap.find('ul.nav-collapse');

    $('li.nav-01 > span').on('click', function () {
        var $el = $(this),
            $elContent = $el.next('ul.nav-collapse');

        if(!$el.hasClass('active')){
            $content.slideUp();
            $btn.removeClass('active');
            $el.addClass('active');                             
            $elContent.slideDown();                
        }else{
            $el.removeClass('active'); 
            $elContent.slideUp();
        }
    });
}   

nav();

/*--------------------------------------------------------------------------------------------------------- 
RESIZE WINDOW SCROLL
**//*---------------------------------------------------------------------------------------------------- */

$(function (bodyResize) {
    $(window).resize(function () {
        $('.main-content').css("height", $(window).innerHeight() - 52);
    });
    $(window).trigger('resize');
});



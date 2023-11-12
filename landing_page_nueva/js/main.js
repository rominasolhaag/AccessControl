

(function($) {

	"use strict";

	var cfg = {		
		scrollDuration : 800, // Duración de SmoothScroll
		mailChimpURL   : 'https://facebook.us8.list-manage.com/subscribe/post?u=cdb7b577e41181934ed6a6a44&amp;id=e6957d85dc'  // mailchimp url
	},	

	$WIN = $(window);	

   // Agregue el agente de usuario a la carpeta <html>
   // se utilizará para la detección de IE10 (Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2; Tridente/6.0))
	var doc = document.documentElement;
	doc.setAttribute('data-useragent', navigator.userAgent);

	
	/* Preloader 
	 * -------------------------------------------------- */
	var ssPreloader = function() {

		$WIN.on('load', function() {	

			// Forzar la posición de desplazamiento de la página a la parte superior al actualizar la página
			$('html, body').animate({ scrollTop: 0 }, 'normal');

         // desvanecerá todo el DIV del precargador que cubre el sitio web.
	      $("#preloader").delay(500).fadeOut('slow');
	  
	  	});
	};


	/* Menu Móvil
	 * ---------------------------------------------------- */ 
	var ssMobileMenu = function() {

  		var toggleButton = $('.header-menu-toggle'),
          nav = $('#header-nav-wrap');

		toggleButton.on('click', function(event){
			event.preventDefault();

			toggleButton.toggleClass('is-clicked');
			nav.slideToggle();
		});

		if (toggleButton.is(':visible')) nav.addClass('mobile');

		$(window).resize(function() {
			if (toggleButton.is(':visible')) nav.addClass('mobile');
			else nav.removeClass('mobile');
		});

		$('#header-nav-wrap').find('a').on("click", function() {  

			if (nav.hasClass('mobile')) {   		
				toggleButton.toggleClass('is-clicked'); 
				nav.slideToggle();   		
			}     
		});

	}; 


	/* FitVids
	 * ---------------------------------------------------- */
	var ssFitVids = function() {
		$(".fluid-video-wrapper").fitVids();
	}; 



  /* Owl Carousel
	* ------------------------------------------------------ */
	var ssOwlCarousel = function() {

		$(".owl-carousel").owlCarousel({	
	      loop: true,
  			nav: false,
			autoHeight: true,
  			items: 1
		});

	};  	


  /* Resalte la sección actual en la barra de navegación
	* ------------------------------------------------------ */
	var ssWaypoints = function() {

		var sections = $("section"),
		navigation_links = $(".header-main-nav li a");	

		sections.waypoint( {

	       handler: function(direction) {

			   var active_section;

				active_section = $('section#' + this.element.id);

				if (direction === "up") active_section = active_section.prev();

				var active_link = $('.header-main-nav li a[href="#' + active_section.attr("id") + '"]');			

	         navigation_links.parent().removeClass("current");
				active_link.parent().addClass("current");

			}, 

			offset: '25%'

		});
	};


  /* Smooth Scrolling
	* ------------------------------------------------------ */
	var ssSmoothScroll = function() {

		$('.smoothscroll').on('click', function (e) {
			var target = this.hash,
			$target    = $(target);
	 	
		 	e.preventDefault();
		 	e.stopPropagation();	  

			$('html, body').stop().animate({
				'scrollTop': $target.offset().top
			}, cfg.scrollDuration, 'swing', function () {
				window.location.hash = target;
			});

	  	});

	};



  /* Configuración del plugin Placeholder 
	* ------------------------------------------------------ */
	var ssPlaceholder = function() {
		$('input, textarea, select').placeholder();  
	};



  	/* Alert Boxes
  	------------------------------------------------------- */
  	var ssAlertBoxes = function() {

  		$('.alert-box').on('click', '.close', function() {
		  $(this).parent().fadeOut(500);
		}); 

  	};	  	
	


  /* Animación On Scroll
  	* ------------------------------------------------------ */
	var ssAOS = function() {

		AOS.init( {
      	offset: 200,
      	duration: 600,
      	easing: 'ease-in-sine',
      	delay: 300,
			once: true,
			disable: 'mobile'
    	});

	};

	


  /* AjaxChimp
	* ------------------------------------------------------ */
	var ssAjaxChimp = function() {

		$('#mc-form').ajaxChimp({
			language: 'es',
		   url: cfg.mailChimpURL
		});

		// Mailchimp 
		//
		// Defectos:
        // 'submit': 'Enviando...',
        // 0: 'Hemos enviado un correo de confirmación',
        // 1: 'Por favor, ingrese un valor',
        // 2: 'Una dirección de correo electrónico debe contener un solo @',
        // 3: 'La parte del dominio de la dirección de correo electrónico no es válida (la parte después de @: )',
        // 4: 'La parte del nombre de usuario de la dirección de correo electrónico no es válida (la parte antes de @: )',
        // 5: 'Esta dirección de correo electrónico parece falsa o inválida. Por favor, ingrese una dirección de correo real'

		$.ajaxChimp.translations.es = {
		  'submit': 'Submitting...',
		  0: '<i class="fa fa-check"></i> We have sent you a confirmation email',
		  1: '<i class="fa fa-warning"></i> You must enter a valid e-mail address.',
		  2: '<i class="fa fa-warning"></i> E-mail address is not valid.',
		  3: '<i class="fa fa-warning"></i> E-mail address is not valid.',
		  4: '<i class="fa fa-warning"></i> E-mail address is not valid.',
		  5: '<i class="fa fa-warning"></i> E-mail address is not valid.'
		} 

	};


 
  /* Back to Top
	* ------------------------------------------------------ */
	var ssBackToTop = function() {

		var pxShow  = 500,         // altura en la que se mostrará el botón
		fadeInTime  = 400,         // qué tan lento/rápido desea que se muestre el botón
		fadeOutTime = 400,         // qué tan lento/rápido desea que se oculte el botón
		scrollSpeed = 300,         // qué tan lento/rápido desea que el botón se desplace hacia arriba. puede ser un valor, 'lento', 'normal' o 'rápido'
		goTopButton = $("#go-top")

		// Mostrar u ocultar el botón de pie de página fijo
		$(window).on('scroll', function() {
			if ($(window).scrollTop() >= pxShow) {
				goTopButton.fadeIn(fadeInTime);
			} else {
				goTopButton.fadeOut(fadeOutTime);
			}
		});
	};	

  
   /* Inicializar
	* ------------------------------------------------------ */
	(function ssInit() {

		ssPreloader();
		ssMobileMenu();
		ssFitVids();
		ssOwlCarousel();
		ssWaypoints();
		ssSmoothScroll();
		ssPlaceholder();
		ssAlertBoxes();
		ssAOS();
		ssBackToTop();

		// Para usar el formulario de Mailchimp, descomenta la
        // llamada a la función ssAjaxChimp() a continuación:
        // ssAjaxChimp();

	})();
 

})(jQuery);
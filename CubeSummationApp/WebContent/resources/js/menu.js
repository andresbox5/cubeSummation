//Identifica y cierra los submenus que se encuentren abiertos
var ocultarSubmenuActualmenteVisible = function() {
	jQuery('.mainMenuBarSubmenu, .moreMenu').each(function() {
		if (jQuery(this).css('display') !== 'none') {
			jQuery(this).slideUp(100);
			return;
		}
	});
};

// Función que soluciona problemas de redireccionamiento en el menú principal.
// Nota: Este fue el único método que me funcionó para que se hiciera el
// redireccionamiento al hacer click sobre uno de los elementos de los
// submenús.
var parcharRedireccionamiento = function(elemento_clickeado) {
	if (elemento_clickeado.parent().hasClass('mainMenuBarSubmenu-link')) {
		elemento_clickeado = elemento_clickeado.parent();
	}
	if (elemento_clickeado.hasClass('mainMenuBarSubmenu-link')
			&& elemento_clickeado.attr('href') !== '#') {
		var href = elemento_clickeado.attr('href');
		window.location.href = href;
		return;
	}
	return elemento_clickeado;
};

// Muestra/Oculta los submenús
var asignarVisibilidadSubmenus = function(event) {
	var elemento_clickeado = jQuery(event.target);
	var menu;

	elemento_clickeado = parcharRedireccionamiento(elemento_clickeado);

	// Se intenta obtener el elemento (submenú) del menú principal sobre el que
	// se
	// encuentra el usuario.
	if (elemento_clickeado.hasClass('mainMenuBar-label')) {
		menu = elemento_clickeado.parent().parent();
	} else if (elemento_clickeado.hasClass('mainMenuBar-link')) {
		menu = elemento_clickeado.parent();
	} else {
		ocultarSubmenuActualmenteVisible();
		return true;
	}

	var submenu = menu.find('.mainMenuBarSubmenu, .moreMenu').eq(0);
	if (submenu.css('display') === 'none') {
		ocultarSubmenuActualmenteVisible();
		submenu.slideDown(100);
	} else {
		submenu.slideUp(100);
	}

	return true;
};

var ajustarAnchoDelMenu = function() {
	jQuery('.mainMenuBar-item:first-child').addClass(
			'mainMenuBar-item_order_first');
	// Ancho barra de menú
	var anchoMenu = jQuery('.mainMenuBar').width();

	// Se crea el elemento Más para determinar el ancho calculado por el
	// navegador
	var itemMostrarMas = $('<li />').addClass(
			'mainMenuBar-item mainMenuBar-item_order_last');
	var link = $('<a />', {
		'href' : '#',
		'class' : 'mainMenuBar-link'
	}).html('M&aacute;s &#9662;');
	link.css('visibility', 'hidden');
	link.appendTo(itemMostrarMas);
	itemMostrarMas.appendTo('.mainMenuBar');
	var anchoElementoMas = itemMostrarMas.outerWidth();
	itemMostrarMas.remove(); // Una vez obtenida la información se quita
	// el elemento

	// Buscar el elemento del menú que provoca el desbordamiento
	var itemsMenuPpal = jQuery('.mainMenuBar-item');
	var anchoElementosMenuPpal = 0;
	var elementoQueDesborda = -1;
	for ( var idx = 0; idx < itemsMenuPpal.length; idx++) {
		var itemMenu = itemsMenuPpal.eq(idx);
		// Se van sumando los anchos de los elementos hasta que superen el ancho
		// del menú
		anchoElementosMenuPpal += itemMenu.outerWidth();
		if (anchoElementosMenuPpal > anchoMenu) {
			elementoQueDesborda = idx;
			anchoElementosMenuPpal -= itemMenu.width();
			break;
		}
	}

	if (elementoQueDesborda === -1) {
		return;
	}

	// Determina si al agregar el item Más al menu se vuelve a desbordar
	if ((anchoElementosMenuPpal + anchoElementoMas) > anchoMenu) {
		elementoQueDesborda -= 1;
	}

	var itemsMenuMas = itemsMenuPpal.slice(elementoQueDesborda);
	itemsMenuMas.remove();

	// Se adecuan los nombres de las clases para que correspondan a las de los
	// hijos del menú
	itemsMenuMas.find('.mainMenuBar-item').addClass('moreMenu-item');
	itemsMenuMas.find('.mainMenuBar-item').removeClass('mainMenuBar-item');
	itemsMenuMas.find('.mainMenuBar-link').addClass('moreMenu-link');
	itemsMenuMas.find('.mainMenuBar-link').removeClass('mainMenuBar-link');
	itemsMenuMas.find('.mainMenuBarSubmenu').addClass('moreMenuSubmenu');
	itemsMenuMas.find('.mainMenuBarSubmenu').removeClass('mainMenuBarSubmenu');
	itemsMenuMas.find('.mainMenuBarSubmenu-label').addClass(
			'moreMenuSubmenu-label');
	itemsMenuMas.find('.mainMenuBarSubmenu-label').removeClass(
			'mainMenuBarSubmenu-label');

	var moreMenu = $('<ul class="moreMenu" />');
	itemsMenuMas.appendTo(moreMenu);
	moreMenu.appendTo(itemMostrarMas);
	itemMostrarMas.appendTo('.mainMenuBar');
	$('.mainMenuBar-link').eq(-1).css('visibility', 'visible');

	// Se adecuan algunos de los nombres que no pudieron ser ajustados
	// previamente
	// para que corresnpndan a las de los hijos del menú
	$('.moreMenu').eq(-1).find('.mainMenuBar-item').addClass('moreMenu-item');
	$('.moreMenu').eq(-1).find('.mainMenuBar-item').removeClass(
			'mainMenuBar-item');
	$('.moreMenu').eq(-1).find('.mainMenuBar-label').addClass('moreMenu-label');
	$('.moreMenu').eq(-1).find('.mainMenuBar-label').removeClass(
			'mainMenuBar-label');

	ajustarPosicionMenuMas();
};

// Ajusta la posición del menú más para que se muestre desplazado hacia la
// izquierda en vez de mostrarse desplazado hacia la derecha (que es el
// comportamiento convencional).
var ajustarPosicionMenuMas = function() {
	var anchoContenedor = $('.moreMenu').parent().eq(-1).width();
	var anchoMenu = $('.moreMenu').width();
	var posicion = anchoMenu - anchoContenedor;
	$('.moreMenu').css('margin-left', -posicion);
};

jQuery('document').ready(function() {
	jQuery('html').mousedown(asignarVisibilidadSubmenus);

	ajustarAnchoDelMenu();
});
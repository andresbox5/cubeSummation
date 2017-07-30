function al(index) {
	var item = jQuery("#formAdministrar\\:tabAdminBandejas\\:tblTramites_selection");
	item.val(index);
}

function paginar(page) {
	tblAsignaciones.getPaginator().setPage(page);
}

var firstCheckA = true;
var itemsArray = [];

function testAsig() {
	if (!firstCheckA) {
		var item = jQuery("#formAdministrar\\:tabAdminBandejas\\:tblTramites_selection");
		var tr = jQuery("#formAdministrar\\:tabAdminBandejas\\:tblTramites_data > tr");
		tr.each(function(i, v) {
			var aria = v.getAttribute("aria-selected");
			if (aria === 'true') {
				if (!contains(v.getAttribute("data-rk"), itemsArray)) {
					itemsArray.push(v.getAttribute("data-rk"));
				}
			} else {
				if (contains(v.getAttribute("data-rk"), itemsArray)) {
					var index = itemsArray.indexOf(v.getAttribute("data-rk"));
					itemsArray.splice(index, 1);
				}
			}
		});
		item.val(itemsArray.join(","));
	} else {
		firstCheckA = false;
	}
}

function guarda() {
	itemsArray = [];
	var item = jQuery("#formAdministrar\\:tabAdminBandejas\\:tblTramites_selection");
	var txt = jQuery("#formAdministrar\\:tabAdminBandejas\\:txtUsuarioAsignacion");
	item.val("");
	txt.text("");
	tblAsignaciones.clearSelection();
	firstCheckA = true;
}

function contains(value, array) {
	var tama = array.length;
	for ( var i = 0; tama > i; i++) {
		if (array[i] === value)
			return true;
	}
	return false;
}

function scrollar(jq) {
	var alturaMaxima = obtenerAlto();
	var alturaComponente = jq.outerHeight();

	if (alturaComponente > alturaMaxima) {
		var contenido = jq.find(".ui-dialog-content");
		// alert("Altura Maxima alcanzada");
		jq.css("height", alturaMaxima + "px");
		contenido.css({
			"height" : parseInt(alturaMaxima - 40) + "px",
			"overflow-y" : "scroll"
		});
	}

}

function obtenerAlto() {
	var altura = parseInt(screen.availHeight) * 60 / 100;
	return altura;
}

function selectAllTT() {
	var tr = jQuery("#tabPeticiones\\:frmPeticionesCoord\\:tblPeticionesAsignacion_data > tr");
	var count = 0;
	tr.each(function(i, v) {
		v.attr('aria-selected', 'true');
		count++;
	});
	console.log("Conteo -> " + count);
}

var arregloSeleccion = [];
function guardarSeleccion() {
	var tr = jQuery("#tabPeticiones\\:frmPeticionesCoord\\:tblPeticionesCoor_data > tr");
	cargarSeleccion();
	tr.each(function(i, v) {
		var aria = v.getAttribute("aria-selected");
		if (aria === 'true') {
			if (!contains(v.getAttribute("data-rk"), arregloSeleccion)) {
				arregloSeleccion.push(v.getAttribute("data-rk"));
			}
		} else {
			if (contains(v.getAttribute("data-rk"), itemsArray)) {
				var index = itemsArray.indexOf(v.getAttribute("data-rk"));
				itemsArray.splice(index, 1);
			}
		}
	});
	cargarSeleccion();
}

function eliminarBoton() {
	jQuery(
			"#tabPeticiones\\:frmPeticionesCoord\\:tblPeticionesCoor .ui-chkbox-all")
			.css({
				'display' : 'none'
			});
}

function leerArregloSeleccionDesdeJava(hp) {
	if (hp === "" || hp === " ") {
		arregloSeleccion.length = 0;
	} else {
		arregloSeleccion = hp.split(",");
	}
}

function cargarSeleccion() {
	console.log(arregloSeleccion.length);
	if (arregloSeleccion.length == 0) {
		return;
	}
	var tr = jQuery("#tabPeticiones\\:frmPeticionesCoord\\:tblPeticionesCoor_data > tr");
	tr.each(function(i, v) {
		if (contains(v.getAttribute("data-rk"), arregloSeleccion)) {
			var checkBox = jQuery(v).find(".ui-chkbox .ui-chkbox-box");
			_tblPeticionesCoor.selectRowWithCheckbox(jQuery(checkBox), true);
		}
	});
}

function cargarSelecionAsignacion() {
	console.log(arregloSeleccion.length);
	if (arregloSeleccion.length == 0) {
		return;
	}

	var tr = jQuery("#tabPeticiones\\:frmPeticionesCoord\\:tblPeticionesAsignacion_data > tr");
	tr.each(function(i, v) {
		if (contains(v.getAttribute("data-rk"), arregloSeleccion)) {
			var checkBox = jQuery(v).find(".ui-chkbox .ui-chkbox-box");
			_tblPeticionesCoor.selectRowWithCheckbox(jQuery(checkBox), true);
		}
	});
}

function eliminarBarraDeProgreso() {
	jQuery(".ui-fileupload-files").hide();
}

/** ************************************************************************** */

PrimeFaces.locales['es'] = {
	closeText : 'Cerrar',
	prevText : 'Anterior',
	nextText : 'Siguiente',
	monthNames : [ 'Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio',
			'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre',
			'Diciembre' ],
	monthNamesShort : [ 'Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun', 'Jul', 'Ago',
			'Sep', 'Oct', 'Nov', 'Dic' ],
	dayNames : [ 'Domingo', 'Lunes', 'Martes', 'Miércoles', 'Jueves',
			'Viernes', 'Sábado' ],
	dayNamesShort : [ 'Dom', 'Lun', 'Mar', 'Mie', 'Jue', 'Vie', 'Sab' ],
	dayNamesMin : [ 'D', 'L', 'M', 'X', 'J', 'V', 'S' ],
	weekHeader : 'Semana',
	firstDay : 1,
	isRTL : false,
	showMonthAfterYear : false,
	yearSuffix : '',
	timeOnlyTitle : 'Sólo hora',
	timeText : 'Tiempo',
	hourText : 'Hora',
	minuteText : 'Minuto',
	secondText : 'Segundo',
	currentText : 'Fecha actual',
	ampm : false,
	month : 'Mes',
	week : 'Semana',
	day : 'Día',
	allDayText : 'Todo el día'
};

/** *************************************************************************** */
/** ********************* ********** RESIZE DIALOG ********** ***************** */
/** *************************************************************************** */

function handleResizeDialog(dialog) {
	var el = $(dialog.jqId);
	var doc = $('body');
	var win = $(window);
	var elPos = '';
	var bodyHeight = '';
	var bodyWidth = '';
	// position:fixed is maybe cool, but it makes the dialog not scrollable on
	// browser level, even if document is big enough
	if (el.height() > win.height()) {
		bodyHeight = el.height() + 'px';
		elPos = 'absolute';
	}
	if (el.width() > win.width()) {
		bodyWidth = el.width() + 'px';
		elPos = 'absolute';
	}
	el.css('position', elPos);
	doc.css('width', bodyWidth);
	doc.css('height', bodyHeight);
	var pos = el.offset();
	if (pos.top + el.height() > doc.height())
		pos.top = doc.height() - el.height();
	if (pos.left + el.width() > doc.width())
		pos.left = doc.width() - el.width();
	var offsetX = 0;
	var offsetY = 0;
	if (elPos != 'absolute') {
		offsetX = $(window).scrollLeft();
		offsetY = $(window).scrollTop();
	}
	// scroll fix for position fixed
	if (pos.left < offsetX)
		pos.left = offsetX;
	if (pos.top < offsetY)
		pos.top = offsetY;
	el.offset(pos);
}
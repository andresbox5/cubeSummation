function preparacionExito(xhr, status, args) {
	var isValid = args.isValid;
	if (isValid) {
		wvIncluirAlertasAlarmas.hide();
		wvEditarAlertasAlarmas.hide();
	}
}

function permitirGuardarBloqueo(xhr, status, args) {
	var isValid = args.isValid;
	if (isValid) {
		dlgRegBitacora.hide();
		dlgCambEstado.hide();
	}
}

function buscadorActorUsuario(xhr, status, args) {
	var isValid = args.isValid;
	if (isValid) {
		wvBuscarUsuario.show();
	}
}

function buscarUsuario(xhr, status, args) {
	var isValid = args.isValid;
	if (isValid) {
		wdVarBUscarUsuario.show();
	}
}

function permitirBitacora(xhr, status, args) {
	var isValid = args.isValid;
	if (isValid) {
		dlgRegBitacora.show();

	}
}

function validGuardEmails(xhr, status, args) {

	var isValid = args.isValid;
	if (isValid) {
		cerrarPopus();
	}
}

function cerrarPopup(xhr, status, args) {

	var isValid = args.isValid;
	if (isValid) {
		dlgCambPass.hide();
	}
}

function alertCerrarPopup(xhr, status, args) {

	var isValid = args.isValid;
	if (isValid) {
		dlgMsjConf.show();
	}
}



/**
 * *Esta funcion permite cerrar los popup de Incluir, editar Usuario y el
 * mensaje de confirmacion
 */
function cerrarPopus() {
	dlgMsjConf.hide();
	
	dlgEditarUsuario.hide();
	dlgRegistrarEmail.hide();
	dlgRegBitacora.hide();
}

function limpiarCajasPF(control, valor) {
	control.jq.val(valor); 
	control.jq.removeClass('ui-state-error');
}

/* funciones de perfiles */

function cerrarRolIncluir(xhr, status, args) {
	var exito = args.exito;
	if (exito) {
		dlgIncluir.hide();
	}
}


function limpiarControlUploadFirma() {
	txtFirmaWV.uploadContent.find('tr.ui-state-error').remove();
}

function limpiarControlUploadSoporte() {
	fileSoporteWV.uploadContent.find('tr.ui-state-error').remove();
}

function mostrarBitacora(xhr, status, args) {
	var exito = args.exito;
	if (exito) {
		dlgBitacoraWV.show();
	}
}

function mostrarBitacoraUsuario(xhr, status, args) {
	var isValid = args.isValid;
	if (isValid) {
		dlgRegBitacora.show();
	}
}

/* Divisa */
function mostrarBitacoraDivisa(xhr, status, args) {
	var isValid = args.isValid;
	if (isValid) {
		dlgBitacoraWV.show();
	}
}


function cerrarDivisaIncluirBitacora(xhr, status, args) {
	var isValid = args.isValid;
	if (isValid) {
		dlgBitacoraWV.hide();
	}
}

function cerrarDialogDivisa(){
	dlgConfimCerrarDivisa.hide();
}


/*Tipos Fiscales*/
function cerrarPopusFiscales(xhr, status, args) {
	var isValid = args.isValid;
    if (isValid) {
    	dlgConfirmacion.hide();
    	dlgIncluirTipoFiscal.hide();
    	dlgBitacoraWV.hide();
    }
}

function mantenerFiscales(){
	dlgConfirmacion.hide();
	dlgIncluirTipoFiscal.show();
}


/* TasasImpuesto */
function cerrarPopupTasas(xhr, status, args) {
	var isValid = args.isValid;
    if (isValid) {
    	dlgConfirmTasa.hide();
    	dlgIncluirTasa.hide();
    	dlgBitacoraWV.hide();
    }
}

function mantenerTasas(){
	dlgConfirmTasa.hide();
	dlgIncluirTasa.show();
}

function cerrarDialogTasas() {	
	dlgConfirmTasa.hide();
    dlgIncluirTasa.hide();
    dlgBitacoraWV.hide();    
}

/* TiposImpuesto */
function cerrarPopupTipos(xhr, status, args) {
	var isValid = args.isValid;
    if (isValid) {
    	dlgConfirmTipo.hide();
		dlgIncluirTipoImpuesto.hide();
		dlgBitacoraTiposWV.hide();
    }
}

function mantenerTipos(){
	dlgConfirmTipo.hide();
	dlgIncluirTipoImpuesto.show();
}

function cerrarDialogTipos() {
	dlgConfirmTipo.hide();
	dlgIncluirTipoImpuesto.hide();
	dlgBitacoraTiposWV.hide();    
}


function mostrarBitacoraTipos(xhr, status, args) {
	var isValid = args.isValid;
	if (isValid) {
		dlgBitacoraTiposWV.show();
	}
}

function cerrarTiposIncluirBitacora(xhr, status, args) {
	var isValid = args.isValid;
	if (isValid) {
		dlgBitacoraTiposWV.hide();
	}
}

function cerrarPopusDivisa(xhr, status, args) {
    var isValid = args.isValid;
    if (isValid) {
        dlgMsjConf.hide();
        dlgEditarDivisa.hide();
        dlgBitacoraWV.hide();
    }
}

function cerrarPopusParidad(xhr, status, args) {
    var isValid = args.isValid;
    if (isValid) {
        dlgMsjConf.hide();
        dlgEditarParidad.hide();
        dlgBitacoraWV.hide();
    }
}


//uso desde este
function verDivisa(xhr, status, args) {
		dlgEditarDivisa.show();
}

function verParidad(xhr, status, args) {
	dlgEditarParidad.show();
}

function mantenerDivisa(){
	dlgMsjConf.hide();
	dlgEditarDivisa.show();
}
function mantenerParidad(){
	dlgMsjConf.hide();
	dlgEditarParidad.show();
}

function limpiarCampo(){
	jQuery('#calendarioLimpiar input').attr('value',null);
}

/**
 * Funciones para el modulo de capturar solicitud folios
 */

//Cierra el pdialog de incluirDocumento
function actualizarWizardDocumentos(xhr, status, args) {
	var isValid = args.isValid;
    if (isValid) {
    	wVarDocumento.loadStep(wVarDocumento.cfg.steps[0], true);
    }
}

//Cierra el pdialog de confirmacion
function cerrarPopupConfirmacion(xhr, status, args) {
	var isValid = args.isValid;
    if (isValid) {
    	dlgConfirmacion.hide();   	
    }
}



// NO MODIFICAR ESTE ARCHIVO 
// SI SE REQUIERE SCRIPTS ADICIONALES AGREGARLOS EN EL HEAD DE CADA PAGINA
//
// CLASES DISPONIOBLES
// .validaNumerico 		solo numeros sin puntos ni comas
// .alfanumerico  		letras, numeros @,.,ñ,Ü
// .alfabetico  		solo letras
// .numeroDecimal[2,1]	numero con decimales 2->parte entera  1->parte decimal
//
// Autor: Carlos andres chaguendo
function validacioncampos() {
	validacionCampos();
}
function validacionCampos() {
	jQuery(".ui-column-filter").each(function() {
		this.oninput = function() {
			sinAsentos(this, '@');
			sinAsentos(this, ',');
			sinAsentos(this, '/');
		};
	});
	// // desactiva la funcion de arrastrar y solatar para los campos
	jQuery("input,textarea").each(function() {
		this.oninput = function() {
			sinAsentos(this, ',');
		};
		this.ondrop = function() {
			return false;
		};
		this.onpaste = function(event) {
			return false;
		};
	});

	jQuery(".validaNumerico").each(function() {
		this.oninput = function() {
			onlyNumbers(this);
		};
	});

	jQuery(".email").each(function() {
		this.oninput = function() {
			sinAsentos(this, '%');
			sinAsentos(this, '/');
			sinAsentos(this, ',');
			sinAsentos(this, ' ');
		};
	});

	jQuery(".alfanumerico").each(function() {
		this.oninput = function() {
			sinAsentos(this, '@');
			sinAsentos(this, '%');
			sinAsentos(this, '/');
			sinAsentos(this, ',');
		};
	});

	jQuery(".alfabetico").each(function() {
		this.oninput = function() {
			sinAsentos(this, ',');
			sinAsentos(this, '.');
			sinAsentos(this, '@');
			sinAsentos(this, '%');
			sinAsentos(this, '/');
			sinAsentos(this, '0');
			sinAsentos(this, '1');
			sinAsentos(this, '2');
			sinAsentos(this, '3');
			sinAsentos(this, '4');
			sinAsentos(this, '5');
			sinAsentos(this, '6');
			sinAsentos(this, '7');
			sinAsentos(this, '8');
			sinAsentos(this, '9');
		};
	});

	jQuery(".HdsDatePicker").each(function() {
		jQuery(this).datepicker({
			dateFormat : 'dd/mm/yy',
			onSelect : function(date) {
				jQuery(this).keyup();
				jQuery(this).blur();
			}

		});
	});

	iniciarValidarDecimal();
}

String.prototype.replaceAt = function(index, character) {
	return this.substr(0, index) + character
			+ this.substr(index + character.length);
};

function sinAsentos(contenido, replace) {
	var i = contenido.selectionStart;
	contenido.value = contenido.value.toUpperCase();
	var texto = contenido.value;
	texto = texto.slice(i - 1, i);
	contenido.value = contenido.value.replace(replace, '');
	if (!texto
			.match('[\/]|[@,%]|[0-9]|[a, ,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,ñ,ü]|[A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z,Ñ,Ü]|[\d.]')) {
		contenido.value = contenido.value.replace(texto, '');
		contenido.selectionStart = i - 1;
		contenido.selectionEnd = i - 1;
	} else {
		contenido.selectionStart = i;
		contenido.selectionEnd = i;
	}
}

function onlyNumbers(contenido) {
	var i = contenido.selectionStart;
	contenido.value = contenido.value.toUpperCase();
	var texto = contenido.value;
	texto = texto.slice(i - 1, i);
	if (!texto.match('[0-9]')) {
		contenido.value = contenido.value.replace(texto, '');
		contenido.selectionStart = i - 1;
		contenido.selectionEnd = i - 1;
	} else {
		contenido.selectionStart = i;
		contenido.selectionEnd = i;
	}
}

function iniciarValidarDecimal() {
	jQuery("*[class*=numeroDecimal]").each(function() {
		this.oninput = function() {
			var enteros = 6;
			var deci = 4;
			jQuery(this.classList).each(function(e, a) {
				if (a.contains('numeroDecimal')) {
					var inc = a.indexOf('[');
					var fin = a.indexOf(',');
					enteros = a.slice(inc + 1, fin);
					inc = a.indexOf(']');
					deci = a.slice(fin + 1, inc);
				}
			});
			validarDecimal(this, enteros, deci);
		};
	});
}

function validarDecimal(contenido, enteros, decimas) {
	var i = contenido.selectionStart;
	// solo numeros
	if (isNaN(contenido.value)) {
		contenido.value = contenido.value.replaceAt(i - 1, '*');
	}
	var i = contenido.selectionStart;
	var texto = contenido.value;
	var car = texto.slice(i - 1, i);
	var indice = texto.indexOf('.');
	if (texto.length <= enteros || (indice <= enteros && indice >= 1)) {
	} else {
		contenido.value = texto.replaceAt(i - 1, '.');
	}
	if (indice > 0 && indice <= enteros) {
		var dec = texto.slice(indice + 1, i);
		// despus del primer punto ya no permita infgresar mas
		if (car == '.' && dec.contains('.')) {
			contenido.value = texto.replaceAt(i - 1, '*');
		}
		// maximos decimales
		if (dec.length <= decimas) {
		} else {
			contenido.value = texto.replaceAt(i - 1, '*');
		}
	}
	if (contenido.value.indexOf('*') >= 0) {
		contenido.value = contenido.value.replace('*', '');
	}
	if (contenido.value.indexOf('.') == 0) {
		contenido.value = contenido.value.replace('.', '');
	}
}

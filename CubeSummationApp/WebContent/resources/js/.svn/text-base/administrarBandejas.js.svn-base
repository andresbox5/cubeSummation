
;jQuery('.ui-column-filter.ui-inputtext').bind(
		'keypress',
		function(event) {
			// var regex = new
			// RegExp("^[a-zA-Z0-9]+$");
			var regex = new RegExp("^[a-zA-Z0-9ñÑÜü\\s]+$");
			var key = String.fromCharCode(!event.charCode ? event.which
					: event.charCode);
			if (event.keyCode != 8 && event.keyCode != 9 && event.keyCode != 46
					&& event.keyCode != 37 && event.keyCode != 39
					&& !regex.test(key)) {
				event.preventDefault();
				return false;
			}
		});


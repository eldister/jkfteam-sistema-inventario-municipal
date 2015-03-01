$(document).ready(function() {

	$('#btnasignar').click(function() {
		return !$('#cmbpermisosdisponibles option:selected').remove().appendTo('#cmbpermisosasignados');
	});
	$('#btndesasignar').click(function() {
		return !$('#cmbpermisosasignados option:selected').remove().appendTo('#cmbpermisosdisponibles');
	});

	$("#cmbusuario").change(function() {
		var thiselement = $(this);
		$.ajax({
			type : 'POST',
			url :  SIMUNI_SERVER + "/permiso?proceso=perasignados&cmbusuario=" + thiselement.val(),
			cache : false
		}).success(function(html) {
			//
		//	alert(html);//
			if (html.length > 0) {
				$("#cmbpermisosasignados").html(html);
				//console.info(html)
			}
			console.info(html);
		}).error(function(html) {
			//no se pudo validar.
			alert('error');
			//console.info(html);
		});

		$.ajax({
			type : 'POST',
			url : SIMUNI_SERVER + "/permiso?proceso=pernoasignados&cmbusuario=" + thiselement.val(),
			cache : false
		}).success(function(html) {
			//
			if (html.length > 0) {
				$("#cmbpermisosdisponibles").html(html);
			}
			console.info(html);
		}).error(function(html) {
			//no se pudo validar.
			alert('error');
			//console.info(html);
		});
	});
	$("#cmbusuario").trigger('change');

	$("#formulario_mantenimiento_asignacionpermisos").submit(function(e) {
		e.preventDefault();
		if ($('select#cmbpermisosasignados option').length > 0) {
			if (confirm("¿Desea continuar guardando los cambios?")) {
				$('select#cmbpermisosasignados option').attr('selected','selected');
				$.ajax({
					type : "POST",
					url : SIMUNI_SERVER + "/permiso?proceso=asignar",
					data : $(this).serialize(),
					success : function(responde) {
						$("#respuesta").html(responde);
						setTimeout(function(){$(".resultado_operacion").fadeOut('slow');},2000);
					}
				});

			}
		}else{
                    alert('El usuario debe tener almenos un permiso ')
                }

	})
});


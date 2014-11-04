var $001ERGLOB = true;
function fnMostrarImagen(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function(e) {
            $('#imgImagenCargada')
                    .attr('src', e.target.result).width(300).height(300);
        };
        reader.readAsDataURL(input.files[0]);

    }
}

function addEventosACamposDeTexto() {
    $('input[name=txtNumeroPlaca]').change(verificarSiPlacaYaRegistrada);
    $("#txtProveedor").change(validaProveedorElegido);
    $("#txtPorcentajeRescate").change(function() {
        if (validaPorcentaje($("#txtPorcentajeRescate").val())) {
            $("#txtPorcentajeRescate").css('border', 'solid 1px green');
        }
        else {
            $("#txtPorcentajeRescate").css('border', 'solid 1px red');
        }
    });
    $("#txtPorcentajeDepreciacion").change(function() {
        if (validaPorcentaje($("#txtPorcentajeDepreciacion").val())) {
            $("#txtPorcentajeDepreciacion").css('border', 'solid 1px green');
        }
        else {
            $("#txtPorcentajeDepreciacion").css('border', 'solid 1px red');

        }
    });
    $("#txtPrecioCompra").change(validaPrecioCompra);
}
function validaPrecioCompra() {

    if (isNaN($("#txtPrecioCompra").val()))
    {
        //alert($("#txtPrecioCompra").val());
        $("#txtPrecioCompra").css('border', 'solid 1px red');
        return false;
    }
    else {
        $("#txtPrecioCompra").css('border', 'solid 1px green');
        return true;
    }
}
function verificarSiPlacaYaRegistrada() {

    $.ajax({
        url: "/SIMUNI/modulos/activos?proceso=verificarsiactivoexiste&codigoactivo=" + $(this).val(),
        cache: false
    })
            .done(function(html) {
                if (html.length > 0) {
//hay error
                    $("#txtnumplacainfo").addClass('lblcampoinvalido');
                    $("#txtnumplacainfo").removeClass('lblcampovalido');
                    $("#txtnumplacainfo").removeClass('lblcampowarning');
                    $("#txtnumplacainfo").attr('title', html);
                    $("#txtnumplaca").css('border', 'solid 1px red');
                    $001ERGLOB = true;
                }
                else {
//correcto
                    $("#txtnumplacainfo").removeClass('lblcampoinvalido');
                    $("#txtnumplacainfo").addClass('lblcampovalido');
                    $("#txtnumplacainfo").removeClass('lblcampowarning');
                    $("#txtnumplaca").css('border', 'solid 1px green');
                    $("#txtnumplacainfo").attr('title', 'El identificador es Valido');
                    $001ERGLOB = false;


                }
            }

            ).error(function(html) {
//no se pudo validar.
        $("#txtnumplacainfo").removeClass('lblcampoinvalido');
        $("#txtnumplacainfo").removeClass('lblcampovalido');
        $("#txtnumplacainfo").addClass('lblcampowarning');
        $("#txtnumplacainfo").attr('title', 'No se pudo validar');
        $("#txtnumplaca").css('border', 'solid 1px yellow');
        $001ERGLOB = true;
    });

}

function verificarSiPlacaYaRegistrada2(valu) {

    $.ajax({
        url: "/SIMUNI/modulos/activos?proceso=verificarsiactivoexiste&codigoactivo=" + valu,
        cache: false
    })
            .done(function(html) {
                if (html.length > 0) {
//hay error
                    $("#txtnumplacainfo").addClass('lblcampoinvalido');
                    $("#txtnumplacainfo").removeClass('lblcampovalido');
                    $("#txtnumplacainfo").removeClass('lblcampowarning');
                    $("#txtnumplacainfo").attr('title', html);
                    $("#txtnumplaca").css('border', 'solid 1px red');
                    return false;
                }
                else {
//correcto
                    $("#txtnumplacainfo").removeClass('lblcampoinvalido');
                    $("#txtnumplacainfo").addClass('lblcampovalido');
                    $("#txtnumplacainfo").removeClass('lblcampowarning');
                    $("#txtnumplaca").css('border', 'solid 1px green');
                    $("#txtnumplacainfo").attr('title', 'El identificador es Valido');
                    return true;
                }
            }
            ).error(function(html) {
//no se pudo validar.
        $("#txtnumplacainfo").removeClass('lblcampoinvalido');
        $("#txtnumplacainfo").removeClass('lblcampovalido');
        $("#txtnumplacainfo").addClass('lblcampowarning');
        $("#txtnumplacainfo").attr('title', 'No se pudo validar');
        $("#txtnumplaca").css('border', 'solid 1px yellow');
        return false;
    });
    return true;
}

/* para registrar  lo de*/

function setEventoSeleccionarProveedor() {
    $("#txtbtnseleccionarproveedor").click(function() {

        seleccionarProveedor();

    });
}

function mostrarventanamodal() {
    $("#sm_body_ventanamodal").show('slow');
    $("#sm_body_ventanamodal").dialog({
        modal: true,
        width: "70%",
        position: {my: "left-10 top", at: "left top", of: sm_body_mainsection},
        maxWidth: "768px"});


}

function seleccionarProveedor() {

    $.ajax({
        url: "/SIMUNI/modulos/proveedores?proceso=obtenerregistroproveedor",
        cache: false
    })
            .done(function(html) {
                $("#sm_body_ventanamodal").html(html);
                mostrarventanamodal();
                setManejadorSeleccionProveedor();
            });


}


function setManejadorSeleccionProveedor() {
    $(".sm_popup_identificadorproveedor").click(function() {
        $("input[name=hiddenidProveedor]").val($(this).text());


        $("#txtProveedor").val($(this).text());

        // alert(   $("input[name=hiddenidProveedor]").val())
        $("#sm_body_ventanamodal").dialog('close');

    });
}

function validaPlacaVehiculo() {
    var placaactual = $("#txtnumplaca").val();
    if (!placaactual) {
        $("#txtnumplacainfo").addClass('lblcampoinvalido');
        $("#txtnumplacainfo").removeClass('lblcampovalido');
        $("#txtnumplacainfo").removeClass('lblcampowarning');
        $("#txtnumplacainfo").attr('title', 'El campo esta vacio');
        $("#txtnumplaca").css('border', 'solid 1px red');
        return false;
    }
    else {
        return  verificarSiPlacaYaRegistrada2(placaactual);
    }
    return true;
}
function validaProveedorElegido() {
    if (!$("#txtProveedor").val()) {
        $("#txtProveedor").css('border', 'solid 1px red');
        return false;
    }
    $("#txtProveedor").css('border', 'solid 1px green');
    return true;
}
function validaImagenArchivo() {
    if (document.getElementById('btnImagenActivo').value == '') {
        $("#sm_fs_imagen").css('border', 'solid 1px red');
        return false;
    }
    $("#sm_fs_imagen").css('border', 'solid 1px green');
    return true;
}
function validaPorcentaje(val) {
    if (val <= 100 && val > 0) {
        return true;
    }
    return false;
}
function formularioRegistroSubmitEvent() {
    $("#sm_div_formulario").submit(function(event) {
        if ($001ERGLOB === true) {
            event.preventDefault();
            alert("Debes corregir los campos bordeados con rojo" +
                    "o elegir un proveedor!")
        }
        else {
            if (!validaPlacaVehiculo()) {
                event.preventDefault();
                alert("El número de placa esta incorrecto!");

            } else if (!validaProveedorElegido()) {
                event.preventDefault();
                alert("Debes elegir un proveedor")

            }/* else if (!validaImagenArchivo()) {  alert("El archivo esta faltando!")
                event.preventDefault();}*/
            else if (!validaPorcentaje($("#txtPorcentajeDepreciacion").val())) {
                $("#txtPorcentajeDepreciacion").css('border', 'solid 1px red');
                event.preventDefault();
                alert("El porcentaje de depreciacion es invalido")

            } else if (!validaPorcentaje($("#txtPorcentajeRescate").val())) {
                $("#txtPorcentajeRescate").css('border', 'solid 1px red');
                event.preventDefault();
                alert("El porcentaje de rescate es invalido")
            } else if (!validaPrecioCompra()) {
                event.preventDefault();
                $("#txtPrecioCompra").css('border', 'solid 1px red');
                alert("El precio es invalido")
            } else {
                $("#txtPorcentajeDepreciacion").css('border', 'solid 1px red');
                $("#txtPorcentajeRescate").css('border', 'solid 1px green');
                $("#hiddenidCategoria").val($("#cmbCategoria").val());
                $("#hiddenidTipoPago").val($("#cmbTipoPago").val());
                $("#hiddenidDepartamento").val($("#cmbDepartamento").val());
                $('#sm_div_formulario').unbind('submit').submit();
                // $("#sm_div_formulario").submit();

            }
        }



    });
}


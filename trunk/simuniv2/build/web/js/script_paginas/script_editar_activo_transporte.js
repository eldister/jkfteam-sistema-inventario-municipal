var sm_tamanioprevisualizacionimagen = 200;
var sm_registroactivo_tipoproceso = 0;
var Error_Existente = false;
//inicio del formulario, esta parte es lo qeu se ejecutara en el inicio.
$(document).ready(function() {
    $("#sm_idvehiculoautogenerado").click(function() {
        if ($(this).is(':checked')) {
            $("#txtconsecutivovehiculo").attr('disabled', 'disabled');
            $("#txtconsecutivovehiculo").removeAttr('required');
        } else {
            $("#txtconsecutivovehiculo").attr('required', 'required');
            $("#txtconsecutivovehiculo").removeAttr('disabled');
        }
    });

    iniciarCamposFechas();
    eventoSeleccionarArchivos();
    setEventosComoBoxes();
    iniciarCamposCMB();
    adaptar_controlesbotonformulario(TipoBotones);
    setEventosBotonesFormulario();
    addEventoSubmitFormulario();

    $("#cmbtipovehiculo").trigger('change');
    $("#cmbtipoactivo").trigger('change');



});

//para respectivamente el tiop de activo y tipo de vehiculo.
function setEventosComoBoxes() {


    $("#cmbtipovehiculo").change(function() {
        var valor_cmbtipovehiculo = $(this).children(':selected').text();//$(this).val();
        actualizarVistaClasificacionLlantas(valor_cmbtipovehiculo)
        $("#txtconsecutivovehiculo").trigger('change');
    });

}

//Esta funcion lo qeu hace es mostrar/ocultar los botones de la parte inferior de 
//acuerdo al estado. Puede ser sin proceso, proceso correcto o incorrecto.
function adaptar_controlesbotonformulario(val) {
    console.info('entreeeee' + val + (typeof val));
    switch (parseInt(val)) {
        case 2:
            console.info('entreeeee' + val + (typeof val));
            $(".btn_controles_sinprocesocontainer").fadeOut('fast');
            $("#btn_controles_procesocorrecto").fadeOut('slow');
            $("#btn_controles_procesoerror").fadeIn('slow');
            break;
        case 3:
            $(".btn_controles_sinprocesocontainer").fadeOut('fast');
            $("#btn_controles_procesocorrecto").fadeIn('slow');
            $("#btn_controles_procesoerror").fadeOut('fast');
            break;
        default:
            $(".btn_controles_sinprocesocontainer").fadeIn('slow');
            $("#btn_controles_procesocorrecto").fadeOut('slow');
            $("#btn_controles_procesoerror").fadeOut('slow');
            break;
    }
}

//Los eventos de cada uno de los botones disponibles/posibles del formulario
function setEventosBotonesFormulario() {
    $("#sm_btn_reporte").click(function(e) {
        e.preventDefault();
        var codigoActivo = "";
        codigoActivo = $("#registro").val();
       // alert(codigoActivo);
        window.location.assign(SIMUNI_SERVER + '/activo?proceso=reporte_activo&registro=' + codigoActivo);
    });

    $("#sm_btn_iractivos").click(function(e) {
        e.preventDefault();
        window.location.assign(SIMUNI_SERVER + '/activo?proceso=listado');
    });

    $("#sm_btn_reintentar").click(function(e) {
        e.preventDefault();
        $(".btn_controles_sinprocesocontainer").fadeIn('slow');
        $("#btn_controles_procesocorrecto").fadeOut('slow');
        $("#btn_controles_procesoerror").fadeOut('slow');
    });
    $("#sm_btn_registrarnuevo").click(function(e) {
        window.location.assign('');
    });
}

//carga iniciar de valores para loq eu son fechas, y camops de algunos comboboxes como 
//el de tiop de vehiculo, denominacion. .
function inicializarValores(fechacompra, fechainicio, fechafabricacion, denominacion, tipovehiculo, tipo_botones_requeridos) {
    fechaCompra = isFechaValida(fechacompra) ? fechacompra : obtenerFechaString();
    fechaInicio = isFechaValida(fechainicio) ? fechainicio : obtenerFechaString();
    fechaFabrica = isFechaValida(fechafabricacion) ? fechafabricacion : obtenerFechaString();
    Denominacion = denominacion;
    TipoVehiculo = tipovehiculo;
    TipoBotones = tipo_botones_requeridos;



}
//asignacion de valors de comboxes requeridos
function iniciarCamposCMB() {
    $("#cmbdenominacion").val(Denominacion);
    $("#cmbtipovehiculo").val(TipoVehiculo);
}

//asignacion de valores iniciales a los campos fecha.
function iniciarCamposFechas() {
    setFechaDatePicker('txtfechacompra', fechaCompra);
    setFechaDatePicker('txtfechainicio', fechaInicio);
    setFechaDatePicker('txtfechafabricacion', fechaFabrica);


}
/*para la parte de actualizacion de llantas.*/
function actualizarVistaClasificacionLlantas(val) {
    var error = false;
    if (!val) {
        error = true;
    }
    if (!error) {
        if (val.search(new RegExp("moto", "i")) >= 0) {//encontrado moto
            $("#tipollantas_vehiculo").fadeOut('slow');
            $("#tipollantas_moto").fadeIn('slow');
            set_tipooperacion(3);
        }
        else {
            $("#tipollantas_vehiculo").fadeIn('slow');
            $("#tipollantas_moto").fadeOut('slow');
            set_tipooperacion(4);
        }
    }
}



//tipo de operacion a realizar, es decir si es activo articulo o transporte
function set_tipooperacion(val) {
    sm_registroactivo_tipoproceso = val;
    $("#sm_registroactivo_tipoproceso").val(sm_registroactivo_tipoproceso);
}
//parte de la funcionalidad de la imagen de activo.
function set_tamanioimagethumbnail(size) {
    // alert($("#sm_imgcontainer img").attr('width'))
    $("#sm_imgcontainer img").attr('width', size);
    $("#sm_imgcontainer img").attr('height', size);
    sm_tamanioprevisualizacionimagen = size;
    console.info(size + "  " + $("#sm_imgcontainer img").attr('width'));
}


function set_multiplesinglefileinput(multiple) {
    if (multiple) {
        $("#filearchivos").attr('multiple', multiple);
    }
    else {
        $("#filearchivos").removeAttr('multiple');
    }

}


function adaptar_formularioTransporte() {
    $("#sm_contenedor_propiedades").fadeOut('slow');
    $("#sm_contenedor_activoarticulo").fadeOut('slow');
    $("#sm_contenedor_controles").fadeIn('slow');
    $("#sm_contenedor_transporte").fadeIn('slow');
    $("#sm_contenedor_imagenes").fadeIn('slow');
    $("#sm_contenedor_activo").fadeIn('slow');
    //requrido o no ene el formulario
    $("#txtplacaactivo").attr('required', 'required');
    $("#hddproveedor").removeAttr('required');
    $("#txtplacavehiculo").attr('required', 'required');
    $("#txtnumerochasis").attr('required', 'required');
    $("#cmbdepartamento").removeAttr('required');
    $("#sm_idvehiculoautogenerado").trigger('click');
    $("#sm_idvehiculoautogenerado").trigger('click');



}




function eventoSeleccionarArchivos() {
//Check File API support
    if (window.File && window.FileList && window.FileReader)
    {

        $("#filearchivos").change(function(event) {
            var files = event.target.files; //FileList object
            var output = document.getElementById("sm_imgcontainer");
            output.innerHTML = "";
            for (var i = 0; i < files.length; i++)
            {
                var file = files[i];
                //Only pics
                // if(!file.type.match("image"))
                if (file.type.match("image.*")) {
                    if (this.files[0].size < 2097152) {
                        // continue;
                        var picReader = new FileReader();
                        picReader.addEventListener("load", function(event) {
                            var picFile = event.target;
                            var newcad = "<img class='sm_imgcontainer_item' width='" + sm_tamanioprevisualizacionimagen + "' height='" + sm_tamanioprevisualizacionimagen + "'  src='" + picFile.result + "' title='Vista Previa'/>";
                            output.innerHTML += newcad;
                            console.info(newcad);
                        });
                        //Read the image
                        picReader.readAsDataURL(file);
                    } else {
                        // alert("Image Size is too big. Minimum size is 2MB.");
                        //  $(this).val("");
                    }
                } else {
                    //alert("You can only upload image file.");
                    // $(this).val("");
                }
            }

        });
    }
    else
    {
        console.log("Your browser does not support File API");
    }
}








/*seccion validacion de formulario, antes de enviar y confirmacion*/
function addEventoSubmitFormulario() {
    $("#formulario").submit(function(e) {

        e.preventDefault();
        var isformulariovalido = true;
        //validamos    
        var element = null;
        switch (parseInt(sm_registroactivo_tipoproceso)) {
            case 3:
                element = $(this).children().find('#txtplacaactivo');
                isformulariovalido = (isformulariovalido && campoValido(element));
                element = $(this).children().find('#txtplacavehiculo');
                isformulariovalido = (isformulariovalido && campoValido(element));
                element = $(this).children().find('#txtnumerochasis');
                isformulariovalido = (isformulariovalido && campoValido(element));
                //validar formulario campos que ocupen validar online
                element = $(this).children().find('#txtplacaactivo');
                element.trigger('change');
                element = $(this).children().find('#txtplacavehiculo');
                element.trigger('change');
                break;//vehiculo
        }

        //campos qeu no son requeridos pero que se recomienda llenar
        element = $(this).children().find('#txtprecio');
        (campoValido(element, 'warning'));
        element = $(this).children().find('#txtmarca');
        (campoValido(element, 'warning'));
        element = $(this).children().find('#txtmodelo');
        (campoValido(element, 'warning'));
        element = $(this).children().find('#txtdepreciacion');
        (campoValido(element, 'warning'));
        element = $(this).children().find('#txtrescate');
        (campoValido(element, 'warning'));
        element = $(this).children().find('#txtnumeromotor');
        (campoValido(element, 'warning'));
        element = $(this).children().find('#txtcilindros');
        (campoValido(element, 'warning'));
        element = $(this).children().find('#txtobservacionestecnicas');
        (campoValido(element, 'warning'));

        isformulariovalido = (isformulariovalido && !Error_Existente);
        if (isformulariovalido) {
            if (confirm("Esta a punto de actualizar un registro, ¿Desea continuar?")) {
                $(this).unbind();
                $(this).submit();
            }
        }
    });
}


function campoValido(element, warning) {
    // alert(element.parents('.form-group').html());
    if ($.trim(element.val()) == '' && !warning) {
        setEstadoCampo(element, 3);

        return false;
    }
    else {
        if ($.trim(element.val()) == '' && warning) {
            setEstadoCampo(element, 2);
        } else {
            setEstadoCampo(element, 1);
        }
        return true;
    }
}


//1 bien 2 warning 3 error
function setEstadoCampo(element, value) {
    if (value == 3) {

        element.parents('.form-group').addClass('has-error');
        element.parents('.form-group').removeClass('has-warning');
        element.parents('.form-group').removeClass('has-success');
        element.attr('title', 'Rellena / Corrige los errores de este campo (el valor ' + element.val() + ' no es correcto');

    }
    else {
        if (value == 2) {
            element.parents('.form-group').removeClass('has-error');
            element.parents('.form-group').removeClass('has-success');
            element.parents('.form-group').addClass('has-warning');
            element.attr('title', 'Este no es un campo obligatorio,\n aunque es recomendable rellenarlos todos');

        } else {

            element.parents('.form-group').removeClass('has-error');
            element.parents('.form-group').removeClass('has-warning');
            element.parents('.form-group').addClass('has-success');
            element.attr('title', 'Campo correcto');
        }

    }
}











var sm_tamanioprevisualizacionimagen = 0;
var sm_registroactivo_tipoproceso = 0;
//inicio del formulario, esta parte es lo qeu se ejecutara en el inicio.
$(document).ready(function() {


    iniciarCamposFechas();

    setEventoSeleccionarActivoButton();

    adaptar_controlesbotonformulario(TipoBotones);
    setEventosBotonesFormulario();
    addEventoSubmitFormulario();


});



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
        var codigo = "";
        codigo = $("#sm_hidden_reparacion").val();
        window.location.assign(SIMUNI_SERVER + '/activo?proceso=reporte_activo&registro=' + codigo);
    });

    $("#sm_btn_iractivos").click(function(e) {
        e.preventDefault();
        window.location.assign(SIMUNI_SERVER + '/baja?proceso=listado');
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
function inicializarValores(tipo_botones_requeridos) {

    TipoBotones = tipo_botones_requeridos;



}


//asignacion de valores iniciales a los campos fecha.
function iniciarCamposFechas() {
    if (!$("#txtfechareparacion").val()) {
        setFechaDatePicker('txtfechareparacion');
    }
}


/*seccion de la ventana modal para seleccionar proveedor*/
function setEventoSeleccionarActivoButton() {
    $("#btnseleccionaractivo").click(function(e) {
        e.preventDefault();
        $("#sm_body_ventanamodal").empty();
        $('<iframe id="sm_iframe_listadoasincactivos" width="1000" height="500" src="/simuniv2/activo?proceso=listado_asinc"></iframe>')
                .on('load', function() {
                    setEventosGrillaOnLoad();
                }).appendTo('#sm_body_ventanamodal');
        mostrarventanamodal();
    });
}

function mostrarventanamodal() {
    $("#sm_body_ventanamodal").show('slow');
    $("#sm_body_ventanamodal").dialog({
        width: "79%",
        minHeight: '600px',
        height: 530,
        position: {my: "left-10 top-20", at: "left top", of: sm_body_mainsection},
        maxWidth: "1000px"});
}

//*eventos dentro de grilla*/
function setEventosGrillaOnLoad() {

    $('#sm_iframe_listadoasincactivos').contents().find(".table .sm_tbody_filadatos td.sm_tr_columnadatos:first-child").each(function(index, element) {
        $(this).hover(function() {
            $(this).css('cursor', 'normal');
        });
        $(this).click(function() {
            var placaactivo = $(this).parent().find('td.sm_tr_columnadatos:nth-child(1) input[type=hidden]').val();
            var marcaactivo = $(this).parent().find('td.sm_tr_columnadatos:nth-child(5)').text();
            var modeloactivo = $(this).parent().find('td.sm_tr_columnadatos:nth-child(6)').text();
            $("#lbl_inforactivo").text(placaactivo + " | " + marcaactivo + " | " + modeloactivo);
            $("#hddactivo").val(placaactivo);
            //$("#cantidadestu").val(cantidadestu);
            alert("Seleccionaste  " + $("#lbl_inforactivo").text());
            $("#sm_body_ventanamodal").dialog('close');
            $("#sm_body_ventanamodal").dialog('destroy');
            $("#sm_body_ventanamodal").html('');

        });
    });
}


/*seccion validacion de formulario, antes de enviar y confirmacion*/
function addEventoSubmitFormulario() {
    $("#formulario").submit(function(e) {

        e.preventDefault();
        var isformulariovalido = true;
        //validamos    
        var element = null;

        element = $(this).children().find('#hddactivo');
        isformulariovalido = (isformulariovalido && campoValido(element));
        element = $(this).children().find('#txtfechareparacion');
        isformulariovalido = (isformulariovalido && campoValido(element));
        element = $(this).children().find('#txtnombrereparador');
        isformulariovalido = (isformulariovalido && campoValido(element));       

//alert(isformulariovalido);

        if (isformulariovalido) {
            if (confirm("Esta a punto de registar una Venta ¿Desea continuar?")) {
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

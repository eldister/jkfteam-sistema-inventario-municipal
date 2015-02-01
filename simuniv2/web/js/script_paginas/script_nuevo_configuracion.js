function addEventosSubmitFormulario() {
    $("#formulario").submit(function(e) {

        e.preventDefault();
        var isformulariovalido = true;
        //validamos    
        var element = $(this).children().find('#txtrutamysqldump');
        isformulariovalido = (isformulariovalido && campoValido(element));

        element = $(this).children().find('#txtrutabackup');
        isformulariovalido = (isformulariovalido && campoValido(element));
        element = $(this).children().find('#txtlapsotiempo');
        isformulariovalido = (isformulariovalido && campoValido(element, 'warning'));

        element = $(this).children().find('#txtprefijo');
        isformulariovalido = (isformulariovalido && campoValido(element));
        element = $(this).children().find('#txtnombreserver');
        isformulariovalido = (isformulariovalido && campoValido(element));
        element = $(this).children().find('#txtbd');
        isformulariovalido = (isformulariovalido && campoValido(element));
        if (isformulariovalido) {
            if (confirm("Esta a punto de ingresar una configuracion, ¿Desea continuar?")) {
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
        if (warning) {
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


$(document).ready(function() {

    addEventosSubmitFormulario();
  adaptar_controlesbotonformulario(TipoBotones);
  setEventosBotonesFormulario();

});//validarEmail

function inicializarValores(tipo_botones_requeridos) {

    TipoBotones = tipo_botones_requeridos;



}
function adaptar_controlesbotonformulario(val) {
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

function setEventosBotonesFormulario() {
    $("#sm_btn_irinicio").click(function(e) {
        e.preventDefault();
        window.location.assign(SIMUNI_SERVER);
    });
    $("#sm_btn_reintentar").click(function(e) {
        e.preventDefault();
        $(".btn_controles_sinprocesocontainer").fadeIn('slow');
        $("#btn_controles_procesocorrecto").fadeOut('slow');
        $("#btn_controles_procesoerror").fadeOut('slow');
    });
}
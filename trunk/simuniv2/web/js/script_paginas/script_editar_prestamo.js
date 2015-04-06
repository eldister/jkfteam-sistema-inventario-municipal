/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function() {
    iniciarCamposFechas();
    $("#sm_btmopcionitem_actualhome").click(function(e) {
                e.preventDefault();
                    window.location.assign(SIMUNI_SERVER + "/prestamo?proceso=listado");
                });
    $("#sm_btmopcionitem_editar").click(function() {
        addEventosSubmitFormulario();
                });
    });

function inicializarValores(fechadevolucion) {
    fechaDevolucion = isFechaValida(fechadevolucion) ? fechadevolucion : obtenerFechaString();
}

//asignacion de valores iniciales a los campos fecha.
function iniciarCamposFechas() {
    setFechaDatePicker('txtfechaDevolucion', fechaDevolucion);


}






function addEventosSubmitFormulario() {
    $("#formulario").submit(function(e) {

        e.preventDefault();
        var isformulariovalido = true;
        //validamos    


        var element = $(this).children().find('#txtfechaDevolucion');
        isformulariovalido = (isformulariovalido && campoValido(element));

        element = $(this).children().find('#txtobservaciones');
        isformulariovalido = (isformulariovalido && campoValido(element));
        element.trigger('change');
   



        if (isformulariovalido) {
            if (confirm("Está a punto de actualizar un Prestamo, ¿Desea continuar?")) {
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








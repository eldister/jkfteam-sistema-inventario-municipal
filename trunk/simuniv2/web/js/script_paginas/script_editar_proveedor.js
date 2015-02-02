function addEventosSubmitFormulario() {
    $("#formulario").submit(function(e) {

        e.preventDefault();
        var isformulariovalido = true;
        //validamos    


        var element = $(this).children().find('#txtnombreproveedor');
        isformulariovalido = (isformulariovalido && campoValido(element));

        element = $(this).children().find('#txtappellido1proveedor');
        isformulariovalido = (isformulariovalido && campoValido(element));
        element = $(this).children().find('#txtappellido2proveedor');
        isformulariovalido = (isformulariovalido && campoValido(element, 'warning'));

        element = $(this).children().find('#txttelefonomovil');
        isformulariovalido = (isformulariovalido && campoValido(element));
        element = $(this).children().find('#txtemail');
        isformulariovalido = (isformulariovalido && (campoValido(element) && validarEmail(element.val())));
        validarCampoEmail(element);

        //campos qeu no son requeridos pero que se recomienda llenar
        element = $(this).children().find('#txttelefonofijo');
        (campoValido(element, 'warning'));
        element = $(this).children().find('#txttelefonooficina');
        (campoValido(element, 'warning'));
        element = $(this).children().find('#txtfax');
        (campoValido(element, 'warning'));
        element = $(this).children().find('#txtapartadopostal');
        (campoValido(element, 'warning'));
        element = $(this).children().find('#txtweb');
        (campoValido(element, 'warning'));
        element = $(this).children().find('#txtcedularepresentante');
        (campoValido(element, 'warning'));
        element = $(this).children().find('#txtnombrerepresentante');
        (campoValido(element, 'warning'));
        element = $(this).children().find('#txtappellido1representante');
        (campoValido(element, 'warning'));
        element = $(this).children().find('#txtappellido2representante');
        (campoValido(element, 'warning'));
        element = $(this).children().find('#txtnumcuenta');
        (campoValido(element, 'warning'));
        element = $(this).children().find('#txtnombreempresa');
        (campoValido(element, 'warning'));
        element = $(this).children().find('#txtdireccionempresa');
        (campoValido(element, 'warning'));


        element = $(this).children().find('#txtcedulaproveedor');
        isformulariovalido = (isformulariovalido && campoValido(element));
        element.trigger('change');

        isformulariovalido = (isformulariovalido && !Error_ProveedorExistente);
        if (isformulariovalido) {
            if (confirm("Se va actualizar proveedor, ¿Desea continuar?")) {
                $(this).unbind();
                $(this).submit();
            }
        }
    });
}

function validarCampoEmail(element) {
    if (validarEmail(element.val())) {
        setEstadoCampo(element, 1);
    }
    else {
        setEstadoCampo(element, 3);
    }
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


    $("select#cmbtiporpoveedor option")
            .each(function() {
                this.selected = (this.text == cmbtipoproveedor);
            });
    $("select#cmbestadoproveedor option")
            .each(function() {
                this.selected = (this.text == cmbestadoproveedor);
            });
    $("select#cmbnombrebanco option")
            .each(function() {
                this.selected = (this.text == cmbnombrebanco);
            });

});//validarEmail



Error_ProveedorExistente = false;
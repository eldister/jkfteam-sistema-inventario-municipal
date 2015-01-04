var sm_tamanioprevisualizacionimagen = 300;
var sm_registroactivo_tipoproceso = 0;
var Error_Existente=false;
//inicio del formulario, esta parte es lo qeu se ejecutara en el inicio.
$(document).ready(function() {
    iniciarCamposFechas();
    eventoSeleccionarArchivos();
   
    iniciarCamposCMB();
    adaptar_controlesbotonformulario(TipoBotones);
    setEventosBotonesFormulario();
    addEventoSubmitFormulario();
    $("#chk_eliminarcompleto").click(function(){
        if($(this).is(":checked")){
            alert("Eliminar completamente implica que el registro no podra recuperarse\n ")
        }
        
    });
    
    $("#cmbtipoactivo").trigger('change');

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
function inicializarValores(fechacompra, fechainicio, denominacion, tipo_botones_requeridos) {
    fechaCompra = isFechaValida(fechacompra) ? fechacompra : obtenerFechaString();
    fechaInicio = isFechaValida(fechainicio) ? fechainicio : obtenerFechaString();
    Denominacion = denominacion;
    TipoBotones = tipo_botones_requeridos;
}
//asignacion de valors de comboxes requeridos
function iniciarCamposCMB() {
    $("#cmbdenominacion").val(Denominacion);
}

//asignacion de valores iniciales a los campos fecha.
function iniciarCamposFechas() {
    
    if(document.getElementById('txtfechacompra')){
      setFechaDatePicker('txtfechacompra', fechaCompra);  
    }
    if(document.getElementById('txtfechainicio')){
         setFechaDatePicker('txtfechainicio', fechaInicio);
    }   


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
            case 1:
                element = $(this).children().find('#txtplacaactivo');
                isformulariovalido = (isformulariovalido && campoValido(element));
                element = $(this).children().find('#hddproveedor');
                isformulariovalido = (isformulariovalido && campoValido(element));
                break;//normal
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

        isformulariovalido = (isformulariovalido && !Error_Existente);
        if (isformulariovalido) {
            if (confirm("Esta a punto de eliminar un Artículo, ¿Desea continuar?")) {
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
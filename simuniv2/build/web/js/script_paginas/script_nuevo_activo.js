var sm_tamanioprevisualizacionimagen = 0;
var sm_registroactivo_tipoproceso = 0;
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
    setEventoSeleccionarProveedorButton();
    setEventosCamposTextos();
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
    $("#cmbtipoactivo").change(function() {
        var valor_cmbtipoactivo = $(this).children(':selected').text();//$(this).val();
        //     alert($(this).children(':selected').text());
        actualizarVistaFormulario(valor_cmbtipoactivo)
    });

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
        codigoActivo = $("#sm_hidden_codigoactivo").val();
        window.location.assign(SIMUNI_SERVER + '/activo?proceso=reporte' + codigoActivo);
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


///se encarga de adaptar el formulario de acuerdo al tiop de registro a realizar.
function actualizarVistaFormulario(val) {
    var error = false;
    if (!val) {
        error = true;
    }
    if (!error) {
        if ((val.search(new RegExp("transporte", "i")) >= 0) || (val.search(new RegExp("maquinari", "i")) >= 0)) {//encontrado transporte
            adaptar_formularioTransporte();
            set_tamanioimagethumbnail(200);
            set_multiplesinglefileinput('multiple');
            set_tipooperacion(3);
            $("#cmbtipovehiculo").trigger('change');
        } else {
            if (val.search(new RegExp("propiedad", "i")) >= 0) {//encontrado propiedades
                adaptar_formularioPropiedades();
                set_multiplesinglefileinput('');
                set_tipooperacion(2);
            } else {
                //activos normales
                adaptar_formularioResto();
                set_tamanioimagethumbnail(300);
                set_multiplesinglefileinput('');
                set_tipooperacion(1);

            }
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
//caundo el formulario se cambie, tambien algunos valores cambiaran su obligatoriedad.
function adaptar_formularioPropiedades() {
    $("#sm_contenedor_activo").fadeOut('slow');
    $("#sm_contenedor_activoarticulo").fadeOut('slow');
    $("#sm_contenedor_controles").fadeOut('slow');
    $("#sm_contenedor_transporte").fadeOut('slow');
    $("#sm_contenedor_imagenes").fadeOut('slow');
    $("#sm_contenedor_propiedades").fadeIn('slow');
    //requerido o no del formulario
    $("#txtplacaactivo").removeAttr('required');
    $("#hddproveedor").removeAttr('required');
    $("#txtplacavehiculo").removeAttr('required');
    $("#txtnumerochasis").removeAttr('required');
    $("#cmbdepartamento").removeAttr('required');
    $("#txtconsecutivovehiculo").removeAttr('required');
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


function adaptar_formularioResto() {
    $("#sm_contenedor_propiedades").fadeOut('slow');
    $("#sm_contenedor_transporte").fadeOut('slow');
    $("#sm_contenedor_activoarticulo").fadeIn('slow');
    $("#sm_contenedor_controles").fadeIn('slow');
    $("#sm_contenedor_imagenes").fadeIn('slow');
    $("#sm_contenedor_activo").fadeIn('slow');

    //requerido o no en el formulario
    $("#txtplacaactivo").attr('required', 'required');
    $("#hddproveedor").removeAttr('required');
    $("#txtplacavehiculo").removeAttr('required');
    $("#txtnumerochasis").removeAttr('required');
    $("#txtconsecutivovehiculo").removeAttr('required');
    $("#cmbdepartamento").attr('required', 'required');
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



/*seccion de la ventana modal para seleccionar proveedor*/
function setEventoSeleccionarProveedorButton() {
    $("#btnseleccionarproveedor").click(function(e) {
        e.preventDefault();
        $("#sm_body_ventanamodal").empty();
        $('<iframe id="sm_iframe_listadoasincproveedores" width="1000" height="500" src="/simuniv2/proveedor?proceso=listado_asinc"></iframe>')
                .on('load', function() {
                    setEventosGrillaOnLoad();
                }).appendTo('#sm_body_ventanamodal');
        mostrarventanamodal();
    });
}

function mostrarventanamodal() {
    $("#sm_body_ventanamodal").show('slow');
    $("#sm_body_ventanamodal").dialog({
        width: "60%",
        minHeight: '600px',
        height: 530,
        position: {my: "left-10 top-20", at: "left top", of: sm_body_mainsection},
        maxWidth: "768px"});
}

//*eventos dentro de grilla*/
function setEventosGrillaOnLoad() {

    $('#sm_iframe_listadoasincproveedores').contents().find(".table .sm_tbody_filadatos td.sm_tr_columnadatos:first-child").each(function(index, element) {
        $(this).hover(function() {
            $(this).css('cursor', 'normal');
        });
        $(this).click(function() {
            var cedulaproveedor = $(this).parent().find('td.sm_tr_columnadatos:nth-child(1) input[type=hidden]').val();
            var nombreproveedor_completo = $(this).parent().find('td.sm_tr_columnadatos:nth-child(2)').text();
            nombreproveedor_completo += " " + $(this).parent().find('td.sm_tr_columnadatos:nth-child(3)').text();
            nombreproveedor_completo += " " + $(this).parent().find('td.sm_tr_columnadatos:nth-child(4)').text();
            $("#lbl_inforproveedor").text(nombreproveedor_completo);
            $("#hddproveedor").val(cedulaproveedor);
            //$("#cantidadestu").val(cantidadestu);
            alert("Seleccionaste a " + nombreproveedor_completo + "\n" + "Cédula: " + cedulaproveedor);

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
        switch (parseInt(sm_registroactivo_tipoproceso)) {
            case 1:
                element = $(this).children().find('#txtplacaactivo');
                isformulariovalido = (isformulariovalido && campoValido(element));
                element = $(this).children().find('#hddproveedor');
                isformulariovalido = (isformulariovalido && campoValido(element));
                element = $(this).children().find('#txtplacaactivo');
                element.trigger('change');
                break;//normal
            case 2:
                alert('No hay nada que hacer!')
                break;//propiedad .. nada se hace
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
            if (confirm("Esta a punto de ingresar un nuevo registro de activo, ¿Desea continuar?")) {
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

function setEventosCamposTextos() {
    txtCodigoActivoTextChangedEvent();
    txtPlacaVehiculoTextChangedEvent();
    txtConsecutivoVehiculoTextChangedEvent();
}

function txtCodigoActivoTextChangedEvent() {
    var element = $("#txtplacaactivo");

    element.change(function() {
        $.ajax({
            type: 'POST',
            url: SIMUNI_SERVER + "/activo?proceso=chequeo_activo&registro=" + element.val(),
            cache: false
        }).done(function(html) {
            console.info(html);
            if (html.length > 0) {
                //hay error
                setEstadoCampo(element, 3);
                Error_Existente = true;
                element.attr('title', 'El activo ya se encuentra registrada');
            }
            else {
                //correcto
                setEstadoCampo(element, 1);
                Error_Existente = false;
                element.attr('title', 'El activo ingresado parece que es único');
            }
        }

        ).error(function(html) {
            //no se pudo validar.
            setEstadoCampo(element, 2);
            Error_Existente = false;
        });

    });
}

function txtPlacaVehiculoTextChangedEvent() {
    var element = $("#txtplacavehiculo");

    element.change(function() {
        $.ajax({
            type: 'POST',
            url: SIMUNI_SERVER + "/activo?proceso=chequeo_placa&registro=" + element.val(),
            cache: false
        }).done(function(html) {
            console.info(html);
            if (html.length > 0) {
                //hay error
                setEstadoCampo(element, 3);
                Error_Existente = true;
                element.attr('title', 'El activo ya se encuentra registrada');
            }
            else {
                //correcto
                setEstadoCampo(element, 1);
                Error_Existente = false;
                element.attr('title', 'El activo ingresado parece que es único');
            }
        }

        ).error(function(html) {
            //no se pudo validar.
            setEstadoCampo(element, 2);
            Error_Existente = false;
        });

    });
}

function txtConsecutivoVehiculoTextChangedEvent() {
    var element = $("#txtconsecutivovehiculo");


    element.change(function() {
        if (element && element.val().length > 0) {
            $.ajax({
                type: 'POST',
                url: SIMUNI_SERVER + "/activo?proceso=chequeo_consecutivo&registro=" + $("#cmbtipovehiculo").val() + element.val(),
                cache: false
            }).done(function(html) {
                console.info(html);
                if (html.length > 0) {
                    //hay error
                    setEstadoCampo(element, 3);
                    if ($("#txtconsecutivovehiculo").attr('required')) {
                        Error_Existente = true;
                    }
                    element.attr('title', 'El consecutivo ya se encuentra registrada');
                }
                else {
                    //correcto
                    setEstadoCampo(element, 1);
                    Error_Existente = false;
                    element.attr('title', 'El consecutivo ingresado parece que es único');
                }
            }

            ).error(function(html) {
                //no se pudo validar.
                setEstadoCampo(element, 2);
                Error_Existente = false;
            });
        }
    });

}





$(document).ready(function() {

    sm_grillageneral_inicializar();
    sm_grilla_irapagina_paginacioneventhandler();
    // inicializarpaginamantenimiento();
    //$(".sm_div_elementlinkpag").attr('href',)
    actualizarGrillaPaginacionLinks();

});

function actualizarGrillaPaginacionLinks() {
    $(".sm_div_elementlinkpag")
            .each(function() {
                $(this).attr('href', $(this).attr('href') + "&registro=" + $("#cedulaproveedor").val());
            });
}
function ocultarventanamodal() {
    $("#sm_body_ventanamodal").hide('fast');
}
function AddSubmitFormNuevoEventHandler() {
    $("#btnsubmitform").unbind();
    $("#formulario_registro").submit(function(e) {
        e.preventDefault();

        var formURL = SIMUNI_SERVER + "/proveedor/documentos?proceso=nuevo&registro=" + $("#cedulaproveedor").val();

        var formData = new FormData(this);
        // formData.append("registro",$("#cedulaproveedor").val())
        $.ajax({
            type: 'POST',
            url: formURL,
            data: formData,
            mimeType: "multipart/form-data",
            contentType: false,
            cache: false,
            processData: false,
            success: function(responde) {

                $("#sm_body_ventanamodal").html(responde);

            }

        });
    });
}

function AddSubmitFormActualizacionEventHandler(item) {
    $("#btnsubmitform").unbind();
    $("#formulario_actualizacion").submit(function(e) {
        e.preventDefault();

        var formURL = SIMUNI_SERVER + "/proveedor/documentos?proceso=actualizar&registro=" + $("#cedulaproveedor").val();

        var formData = new FormData(this);
        $.ajax({
            type: 'POST',
            url: formURL,
            data: formData,
            mimeType: "multipart/form-data",
            contentType: false,
            cache: false,
            processData: false,
            success: function(responde) {

                $("#sm_body_ventanamodal").html(responde);

            }

        });
    });
}

function AddSubmitFormEliminacionEventHandler() {
    $("#formulario_eliminacion").submit(function(e) {
        e.preventDefault();
        $.ajax({
            type: "POST",
            url: SIMUNI_SERVER + "/proveedor/documentos?proceso=eliminar",
            data: $(this).serialize(),
            success: function(responde) {
                $("#sm_body_ventanamodal").html(responde);
            }

        });
    });
}
function mostrarventanamodal() {
    $("#sm_body_ventanamodal").show('slow');
    $("#sm_body_ventanamodal").dialog({
        width: "70%",
        minHeight: '700px',
        height: 400,
        position: {my: "left-10 top-20", at: "left top", of: sm_body_mainsection},
        maxWidth: "768px"});


}
function  sm_grilla_proceso_modificar(sm_grilla_item_id) {

    //   alert(sm_grilla_item_id);
    $.ajax({
        url: SIMUNI_SERVER + "/proveedor/documentos?proceso=actualizar&registro=" + sm_grilla_item_id,
        cache: false
    })
            .done(function(html) {
                $("#sm_body_ventanamodal").html(html);
                AddSubmitFormActualizacionEventHandler(sm_grilla_item_id);
                mostrarventanamodal();
            });



}
function  sm_grilla_proceso_eliminar(sm_grilla_item_id) {

    //   alert(sm_grilla_item_id);
    $.ajax({
        url: SIMUNI_SERVER + "/proveedor/documentos?proceso=eliminar&registro=" + sm_grilla_item_id,
        cache: false
    })
            .done(function(html) {
                $("#sm_body_ventanamodal").html(html);
                AddSubmitFormEliminacionEventHandler();
                mostrarventanamodal();
            });



}


function sm_grilla_proceso_agregarnuevo() {
    $.ajax({
        url: SIMUNI_SERVER + "/proveedor/documentos?proceso=nuevo",
        cache: false
    })
            .done(function(html) {
                $("#sm_body_ventanamodal").html(html);
                AddSubmitFormNuevoEventHandler();
                mostrarventanamodal();
            });


}


function sm_grilla_busqueda(sm_grillageneral_query) {
    sm_grilla_actualizargrillageneral("query&query=" + sm_grillageneral_query + "&registro=" + $("#cedulaproveedor").val());
}


function sm_grilla_actualizargrillageneral(proceso) {
    $.ajax({
        type: 'POST',
        url: SIMUNI_SERVER + "/proveedor/documentos?proceso=" + proceso,
        contentType: ' charset=utf-8',
        success: function(data) {
            console.info("correcto" + data);
            $("#sm_div_puratablecontainertarget").html(data);
            sm_grillageneral_refrescareventosgrilla();
            sm_grilla_irapagina_paginacioneventhandler();
            actualizarGrillaPaginacionLinks();
            //actualizar grilla
        },
        beforeSend: function() {
            $("#sm_div_puratablecontainertarget").addClass('sm_div_grillageneralloading');
            // $('#sm_respuesta').css('font-size','3em');
        },
        complete: function() {
            // $('#sm_respuesta').css('font-size','0.51em');
            $("#sm_div_puratablecontainertarget").removeClass('sm_div_grillageneralloading');
        },
        error: function(data) {
            console.info(data);

        }

    });


}

function sm_grilla_irapagina_paginacioneventhandler() {
    $(".sm_div_elementlinkpag").click(function(e) {

        var link = $(this).attr('href');
        var searchvalue = $(this).parents('#sm_grilla_maincontainer').children().find('#sm_div_txtcriteriobusquedagrilla').val();
        if (searchvalue) {
            link += '&query=' + searchvalue;
        }

        $(this).attr('href', link);
    });
}




///*esto es para la parte de eliminar un documento particular de una entrega y ver los documentos*/

function sm_grilla_proceso_verdetalle(sm_grilla_item_id) {
    $.ajax({
        url: SIMUNI_SERVER + "/proveedor/documentos?proceso=detalle&registro=" + sm_grilla_item_id,
        cache: false
    })
            .done(function(html) {
                $("#sm_body_ventanamodal").html(html);

                mostrarventanamodal();
                agregarEventosEliminarDocumentosEntrega();
            });
}

function agregarEventosEliminarDocumentosEntrega() {
    $(".sm_asinclistardocumentos_btnrm").click(function() {
        if (confirm("¿Desea realmente eliminar este documento?")) {
           // alert($(this).parents('tr').children().find('input[type=hidden]').val());
           var codigodocumento=$(this).parents('tr').children().find('input[type=hidden]').val();
           var codigoentrega=$("#sm_asinclistardocumentos_idcodigoentrega").val();
            $.ajax({
                type: "POST",
                url: SIMUNI_SERVER + "/proveedor/documentos?proceso=eliminardoc&registro=" + codigodocumento+"&entrega="+codigoentrega,
                success: function(responde) {
                    $("#sm_body_ventanamodal").html(responde);
                    agregarEventosEliminarDocumentosEntrega();
                }

            });
        }
    });
}
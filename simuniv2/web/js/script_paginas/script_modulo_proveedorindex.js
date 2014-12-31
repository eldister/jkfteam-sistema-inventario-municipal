$(document).ready(function() {

    sm_grillageneral_inicializar();
    sm_grilla_irapagina_paginacioneventhandler();

    //  alert(window.location);
    // inicializarpaginamantenimiento();
});


function  sm_grilla_proceso_modificar(sm_grilla_item_id) {

    //   alert(sm_grilla_item_id);
    var sm_form = $('<form  method="get" action="' + SIMUNI_SERVER + '/proveedor">');
    sm_form.appendTo('body');
    sm_form.append('<input type="hidden" value="' + sm_grilla_item_id + '" name="registro">');
    sm_form.append('<input type="hidden" value="actualizar" name="proceso">');

    sm_form.submit();


}
function sm_grilla_proceso_uploadoc(sm_grilla_item_id){
    var sm_form = $('<form  method="get" action="' + SIMUNI_SERVER + '/proveedor/documentos">');
    sm_form.appendTo('body');
    sm_form.append('<input type="hidden" value="' + sm_grilla_item_id + '" name="registro">');
    sm_form.append('<input type="hidden" value="listado" name="proceso">');

    sm_form.submit();    
}
function  sm_grilla_proceso_eliminar(sm_grilla_item_id) {
    var sm_form = $('<form  method="get" action="' + SIMUNI_SERVER + '/proveedor">');
    sm_form.appendTo('body');
    sm_form.append('<input type="hidden" value="' + sm_grilla_item_id + '" name="registro">');
    sm_form.append('<input type="hidden" value="eliminar" name="proceso">');

    sm_form.submit();
}


function sm_grilla_proceso_agregarnuevo() {
    $.ajax({
        url: SIMUNI_SERVER + "/proveedor?proceso=nuevo",
        cache: false
    })
            .done(function(html) {
                $("#sm_body_ventanamodal").html(html);
                AddSubmitFormNuevoEventHandler();
                mostrarventanamodal();
            });


}


function sm_grilla_busqueda(sm_grillageneral_query) {
    console.info("------ ");
    sm_grilla_actualizargrillageneral("query&query=" + sm_grillageneral_query+(sm_grillageneral_buscarinactivos?sm_grillageneral_mostrarinactivos:""));
}


function sm_grilla_actualizargrillageneral(proceso) {
    $.ajax({
        type: 'POST',
        url: SIMUNI_SERVER + "/proveedor?proceso=" + proceso,
        contentType: ' charset=utf-8',
        success: function(data) {
            console.info("correcto" + data);
            $("#sm_div_puratablecontainertarget").html(data);
            sm_grillageneral_refrescareventosgrilla();
            
            sm_grilla_irapagina_paginacioneventhandler();
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

function iniciarValores(i) {

    $("#sm_checkgrillageneral_mostrarinactivos").prop('checked', i);
    if ($("#sm_checkgrillageneral_mostrarinactivos").is(":checked")) {

        sm_grillageneral_mostrarinactivos = "&mostrar_inactivos=si";
        sm_grillageneral_buscarinactivos=true;

    }
}

function sm_grilla_proceso_mostrarinactivos(i) {
    var cururl = window.location.toString();

    if (i) {
        if (cururl.indexOf(sm_grillageneral_mostrarinactivos) < 0) {
            cururl += sm_grillageneral_mostrarinactivos
        }
    } else {
        console.info("estoy eliminando "+sm_grillageneral_mostrarinactivos)
        cururl = cururl.replace(sm_grillageneral_mostrarinactivos, "");

    }
    window.location.assign(cururl);

}

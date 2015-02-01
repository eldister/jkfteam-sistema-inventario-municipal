$(document).ready(function() {

    sm_grillageneral_inicializar();
    sm_grilla_irapagina_paginacioneventhandler();

    // alert(window.location);
    // inicializarpaginamantenimiento();
});




function  sm_grilla_proceso_eliminar(sm_grilla_item_id) {
    var sm_form = $('<form  method="get" action="' + SIMUNI_SERVER + '/reparacion">');
    sm_form.appendTo('body');
    sm_form.append('<input type="hidden" value="' + sm_grilla_item_id + '" name="registro">');
    sm_form.append('<input type="hidden" value="eliminar" name="proceso">');

    sm_form.submit();
}
function  sm_grilla_proceso_modificar(sm_grilla_item_id) {
    var sm_form = $('<form  method="get" action="' + SIMUNI_SERVER + '/reparacion">');
    sm_form.appendTo('body');
    sm_form.append('<input type="hidden" value="' + sm_grilla_item_id + '" name="registro">');
    sm_form.append('<input type="hidden" value="actualizar" name="proceso">');

    sm_form.submit();
}

function sm_grilla_proceso_agregarnuevo() {
    var sm_form = $('<form  method="get" action="' + SIMUNI_SERVER + '/reparacion">');
    sm_form.appendTo('body');
    sm_form.append('<input type="hidden" value="nuevo" name="proceso">');

    sm_form.submit();

}



function sm_grilla_busqueda(sm_grillageneral_query) {
    sm_grilla_actualizargrillageneral("query&query=" + sm_grillageneral_query);
}


function sm_grilla_actualizargrillageneral(proceso) {
    $.ajax({
        type: 'POST',
        url: SIMUNI_SERVER + "/reparacion?proceso=" + proceso,
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
        // alert('entree')
        var link = $(this).attr('href');
        var searchvalue = $(this).parents('#sm_grilla_maincontainer').children().find('#sm_div_txtcriteriobusquedagrilla').val();
        if (searchvalue) {
            //    alert('entre4e')
            link += '&query=' + searchvalue;
        }
        link += sm_grillageneral_mostrarinventas;
        $(this).attr('href', link);
    });
}

function iniciarValores(i) {

    $("#sm_checkgrillageneral_mostrarinventas").prop('checked', i);
    if ($("#sm_checkgrillageneral_mostrarinventas").is(":checked")) {

        sm_grillageneral_mostrarinventas = "&mostrar_inventas=si";
        sm_grillageneral_buscarinventas = true;

    }
}

function sm_grilla_proceso_mostrarinventas(i) {
    var cururl = window.location.toString();

    if (i) {
        if (cururl.indexOf(sm_grillageneral_mostrarinventas) < 0) {
            cururl += sm_grillageneral_mostrarinventas
        }
    } else {
        console.info("estoy eliminando " + sm_grillageneral_mostrarinventas)
        cururl = cururl.replace(sm_grillageneral_mostrarinventas, "");

    }
    window.location.assign(cururl);

}

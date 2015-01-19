SIMUNI_SERVER="/simuniv2";
$(document).ready(function() {

    sm_grillageneral_inicializar();
    sm_grilla_irapagina_paginacioneventhandler();

    //  alert(window.location);
    // inicializarpaginamantenimiento();
});






function sm_grilla_busqueda(sm_grillageneral_query) {
    console.info("------ ");
    sm_grilla_actualizargrillageneral("query_asinc&query=" + sm_grillageneral_query+(sm_grillageneral_buscarinactivos?sm_grillageneral_mostrarinactivos:""));
}


function sm_grilla_actualizargrillageneral(proceso) {
    $.ajax({
        type: 'POST',
        url: SIMUNI_SERVER + "/activo?proceso=" + proceso,
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

function sm_grilla_irapagina_paginacioneventhandler() {
    $(".sm_div_elementlinkpag").click(function(e) {

        var link = $(this).attr('href');

        link += sm_grillageneral_mostrarinactivos;

        $(this).attr('href', link);
       
    });
}
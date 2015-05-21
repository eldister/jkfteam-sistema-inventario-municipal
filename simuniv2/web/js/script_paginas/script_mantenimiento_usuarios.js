
$(document).ready(function() {

    sm_grillageneral_inicializar();
    sm_grilla_irapagina_paginacioneventhandler();
    // inicializarpaginamantenimiento();
});
function ocultarventanamodal() {
    $("#sm_body_ventanamodal").hide('fast');
}
function AddSubmitFormNuevoEventHandler() {
    $("#formulario_registro").submit(function(e) {
        e.preventDefault();
       if(($("#txtpassword1").val()==$("#txtpassword2").val())){
        $.ajax({
            type: "POST",
            url: SIMUNI_SERVER + "/usuario?proceso=nuevo",
            data: $(this).serialize(),
            success: function(responde) {
                $("#sm_body_ventanamodal").html(responde);
            }

        });
    }else{
        alert("Contraseña no coinciden");
    }
    });
}
function AddSubmitFormActualizacionEventHandler() {
    $("#formulario_actualizacion").submit(function(e) {
        e.preventDefault();
        $.ajax({
            type: "POST",
            url: SIMUNI_SERVER + "/usuario?proceso=actualizar",
            data: $(this).serialize(),
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
            url: SIMUNI_SERVER + "/usuario?proceso=eliminar",
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
        width: "72%",
        minHeight: '600px',
        height: 550,
        position: {my: "left-10 top-20", at: "left top", of: sm_body_mainsection},
        maxWidth: "768px"
       });


}

//funcion metiche para obtener valores de una ventana modal con el atributo a

function sm_grilla_proceso_modificar_nogrilla(id){
    $.ajax({
        url: SIMUNI_SERVER + "/usuario?proceso=actualizar&registro=" + id,
        cache: false
    })
            .done(function(html) {
                $("#sm_body_ventanamodal").html(html);
                AddSubmitFormActualizacionEventHandler();
                mostrarventanamodal();
            });
}




function  sm_grilla_proceso_modificar(sm_grilla_item_id) {

    //   alert(sm_grilla_item_id);
    $.ajax({
        url: SIMUNI_SERVER + "/usuario?proceso=actualizar&registro=" + sm_grilla_item_id,
        cache: false
    })
            .done(function(html) {
                $("#sm_body_ventanamodal").html(html);
                AddSubmitFormActualizacionEventHandler();
                mostrarventanamodal();
            });



}
function  sm_grilla_proceso_eliminar(sm_grilla_item_id) {

    //   alert(sm_grilla_item_id);
    $.ajax({
        url: SIMUNI_SERVER + "/usuario?proceso=eliminar&registro=" + sm_grilla_item_id,
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
        url: SIMUNI_SERVER + "/usuario?proceso=nuevo",
        cache: false
    })
            .done(function(html) {
                $("#sm_body_ventanamodal").html(html);
                AddSubmitFormNuevoEventHandler();
                mostrarventanamodal();
            });


}


function sm_grilla_busqueda(sm_grillageneral_query) {
    sm_grilla_actualizargrillageneral("query&query=" + sm_grillageneral_query);
}


function sm_grilla_actualizargrillageneral(proceso) {
    $.ajax({
        type: 'POST',
        url: SIMUNI_SERVER + "/usuario?proceso=" + proceso,
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
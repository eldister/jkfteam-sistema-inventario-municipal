/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function inicializarpaginamantenimiento() {
    ocultarventanamodal();

}

function ocultarventanamodal() {
    $("#sm_body_ventanamodal").hide('fast');
}
function mostrarventanamodal() {
    $("#sm_body_ventanamodal").show('slow');
    $("#sm_body_ventanamodal").dialog({
        modal: true,
        width: "70%",
        position: {my: "left-10 top", at: "left top", of: sm_body_mainsection},
        maxWidth: "768px"});


}


function  sm_grilla_proceso_modificaractivo(sm_grilla_item_id) {

    $.ajax({
        url: "/SIMUNI/modulos/activos?proceso=modificacionarticulo&codigoactivo=" + sm_grilla_item_id,
        cache: false
    })
            .done(function(html) {
                $("#sm_body_ventanamodal").html(html);
                setManejadorFormularioActualizacion();
            });

    mostrarventanamodal();

}

function setManejadorFormularioActualizacion() {
    var frm = $('#sm_div_formulario');

    frm.submit(function(ev) {
        var formObj = $(this);
        var formURL = formObj.attr("action");
        var formData = new FormData(this);
        $.ajax({
            type: 'POST',
            url: formURL,
            data: formData,
            mimeType: "multipart/form-data",
            contentType: false,
            cache: false,
            processData: false,
            success: function(data) {
                alert(data);
            },
            complete: function(xhr, data) {
              //  console.log(xhr.status);
               // $("#sm_respuesta").html($("#sm_respuesta").html() + "completooo" + data + xhr.responseText);
            },
            error: function(data) {
                //alert(data.responseText);
            //    $("#sm_respuesta").html($("#sm_respuesta").html() + data.responseText);
              alert(data);
            }
        });

        ev.preventDefault(); //Prevent Default action. 
       // ev.unbind();
    });


}

function  sm_grilla_proceso_eliminaractivo(sm_grilla_item_id) {
    $.ajax({
        type: 'POST',
        url: "/SIMUNI/modulos/activos?proceso=bajaarticuloasinc&codigoactivo=" + sm_grilla_item_id,
        contentType: ' charset=utf-8',
        success: function(data) {
            alert(data);
            // $("#sm_body_ventanamodal").html(data.status+textStatus);
            //se actualiza los activos
        },
        error: function(data) {
            alert(data);
            // $("#sm_body_ventanamodal").html(jqXHR.status+textStatus);

        }

    });


}

function sm_grilla_cambiarpaginacion(valorpaginacion) {
    $.ajax({
        type: 'POST',
        url: "/SIMUNI/modulos/activos?proceso=modificacionpaginacionasinc&valorpaginacion=" + valorpaginacion,
        contentType: ' charset=utf-8',
        success: function(data) {
            console.info("correcto" + data);
            //actualizar grilla
            sm_grilla_actualizargrillageneral("ver_activosarticulosasinc");
        },
        error: function(data) {
            console.info(data);

        }

    });

}
function sm_grilla_busqueda(sm_grillageneral_query){
 //alert(sm_grillageneral_query); 
 sm_grilla_actualizargrillageneral("busquedaarticulo&query="+sm_grillageneral_query);
 
}



function sm_grilla_actualizargrillageneral(proceso) {
    $.ajax({
        type: 'POST',
        url: "/SIMUNI/modulos/activos?proceso="+proceso,
        contentType: ' charset=utf-8',
        success: function(data) {
            //console.info("correcto" +data);
            $("#sm_div_puratablecontainertarget").html(data);
            sm_grillageneral_refrescareventosgrilla();
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
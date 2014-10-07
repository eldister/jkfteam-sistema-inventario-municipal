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
    // mostrarventanamodal();
    $("#sm_body_ventanamodal").css('top', 0);
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
                $("#sm_respuesta").html($("#sm_respuesta").html() + "correcto" + data.responseText);
            },
            complete: function(xhr, data) {
                console.log(xhr.status);
                $("#sm_respuesta").html($("#sm_respuesta").html() + "completooo" + data + xhr.responseText);
            },
            error: function(data) {
                //alert(data.responseText);
                $("#sm_respuesta").html($("#sm_respuesta").html() + data.responseText);
            }
        });

        ev.preventDefault(); //Prevent Default action. 
        ev.unbind();
    });


}

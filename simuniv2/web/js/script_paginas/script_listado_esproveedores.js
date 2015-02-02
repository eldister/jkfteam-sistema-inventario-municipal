$(document).ready(function(){
    $("#cmbtipoproveedor").change(function(){
        var formData = {tiposervicio:$(this).val()};
          $.ajax({
            type: "POST",
            url: SIMUNI_SERVER + "/proveedor?proceso=listado2",
            data: formData,
            success: function(responde) {
                $("#divrespuesta").html(responde);
            }

        }); 
    });
    $("#btn_seleccionaremail").click(function(){
        
        $("#emails_textcontainer").text(obtenerEmails());
    });
     $("#cmbtipoproveedor").trigger('change');
     
});

function obtenerEmails(){
    var emailscad="";
    $.each( $(".email_container"), function( index, value ){
    emailscad += $(this).text()+";";
});
       /* $(".email_container")
            .each(function() {
               emailscad+=this.text+";";
            });*/
    return emailscad;
}
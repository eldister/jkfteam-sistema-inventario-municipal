$(document).ready(function(){
  $("#formulario").submit(function(e){
      e.preventDefault();
      //alert("listo")
        $.ajax({
            type: "POST",
            url: SIMUNI_SERVER + "/mensaje?proceso=nuevo",
            data: $(this).serialize(),
            success: function(responde) {
                $("#area-mensajes").html(responde);
            }

        });
      
      
      
  });
});
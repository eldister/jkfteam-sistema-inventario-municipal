var intervaloactualizarmensajes=0;
var intervaloactualizarnotificaciones=0;

window.onload = function() {
    userboxcomponent1 = chesdev_components_userbox_handler;

    userboxcomponent1.setUserText("Configuraciones Generales")
    userboxcomponent1.addMenuItem("/SIMUNI", "Ir a Inicio");
    userboxcomponent1.addMenuItem("/SIMUNI/AccionesUsuarios?proceso=logout", "Cerrar Sesion");
    var configuraciones_usuario = document.getElementById("sm_div_usersettings");
    configuraciones_usuario.addEventListener('click', userboxcomponent1.chesdev_components_events_handler);
    iniciarIntervaloNotificaciones();
    iniciarIntervaloMensajes();
  

}

function iniciarIntervaloNotificaciones(){
    intervaloactualizarnotificaciones=setInterval(ActualizarNotificaciones,8000);
}
function iniciarIntervaloMensajes(){
    intervaloactualizarmensajes=setInterval(ActualizarMensajes,8000);
}

function terminarIntervaloNotificaciones(){
    clearInterval(intervaloactualizarnotificaciones);
}
function terminarIntervaloMensajes(){
    clearInterval(intervaloactualizarmensajes);
}

function ActualizarMensajes(){
      $.ajax({
        url: "/SIMUNI/AccionesNotificaciones?proceso=obtenerultimosmensajes",
        cache: false
    })
            .done(function(html) {
                console.info(html);
                $("#sm_mensajescontainer").prepend(html);
               
              
            });    
    
}

function ActualizarNotificaciones(){
      $.ajax({
        url: "/SIMUNI/AccionesNotificaciones?proceso=obtenerultimasnotificaciones",
        cache: false
    })
            .done(function(html) {
                                console.info(html);

                $("#sm_notificacionescontainer").prepend(html);
                
              
            });    
    
}


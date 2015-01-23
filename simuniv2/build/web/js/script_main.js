var intervaloactualizarmensajes = 0;
var intervaloactualizarnotificaciones = 0;

window.onload = function() {
    userboxcomponent1 = chesdev_components_userbox_handler;

    userboxcomponent1.setUserText("Configuraciones Generales")
    userboxcomponent1.addMenuItem(SIMUNI_SERVER + "/", "Ir a Inicio");
    userboxcomponent1.addMenuItem(SIMUNI_SERVER + "/usuario?proceso=logout", "Cerrar Sesion");
    var configuraciones_usuario = document.getElementById("sm_div_usersettings");
    if (configuraciones_usuario) {
        configuraciones_usuario.addEventListener('click', userboxcomponent1.chesdev_components_events_handler);
    }
    iniciarIntervaloNotificaciones();
    iniciarIntervaloMensajes();


}

function iniciarIntervaloNotificaciones() {
    intervaloactualizarnotificaciones = setInterval(ActualizarNotificaciones, 8000);
}
function iniciarIntervaloMensajes() {
    intervaloactualizarmensajes = setInterval(ActualizarMensajes, 8000);
}

function terminarIntervaloNotificaciones() {
    clearInterval(intervaloactualizarnotificaciones);
}
function terminarIntervaloMensajes() {
    clearInterval(intervaloactualizarmensajes);
}

function ActualizarMensajes() {
    console.info('actualizando todo')
    $.ajax({
        url: SIMUNI_SERVER + "/notificacion?proceso=u_mensajes",
        cache: false
    })
            .done(function(html) {
                console.info(html);
                $("#sm_mensajescontainer").prepend(html);


            });

}

function ActualizarNotificaciones() {
    $.ajax({
        url: SIMUNI_SERVER + "/notificacion?proceso=u_notificaciones",
        cache: false
    })
            .done(function(html) {
                console.info(html);
                $("#sm_notificacionescontainer").prepend(html);


            });
}

function initMensajesNotificaciones() {
    $.ajax({
        url: SIMUNI_SERVER + "/notificacion?proceso=mensajes",
        cache: false,
        beforeSend: function() {
            $("#sm_mensajescontainer").addClass('sm_div_containerloading');

        },
        complete: function() {
            // $('#sm_respuesta').css('font-size','0.51em');
            $("#sm_mensajescontainer").removeClass('sm_div_containerloading');
        },
        success: function(html) {
            console.info("mensaaaaaaaaaaajes" + html.length);
            if (html.length > 10) {
                $("#sm_mensajescontainer").html(html);
            }
        }
    });

    $.ajax({
        url: SIMUNI_SERVER + "/notificacion?proceso=notificaciones",
        cache: false,
        success: function(html) {
            console.info("notificacioooooooooooooooooo " + html)
            if (html) {
                if (html.length > 10) {
                    $("#sm_notificacionescontainer").html(html);
                }
            }
        },
        beforeSend: function() {
            $("#sm_notificacionescontainer").addClass('sm_div_containerloading');

        },
        complete: function() {
            // $('#sm_respuesta').css('font-size','0.51em');
            $("#sm_notificacionescontainer").removeClass('sm_div_containerloading');
        }
    });


}


/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
window.onload = function() {
    userboxcomponent1 = chesdev_components_userbox_handler;

    userboxcomponent1.setUserText("Usuario SIMUNI, configuraciones")
    userboxcomponent1.addMenuItem("www.google.com", "Google");
    userboxcomponent1.addMenuItem("www.google.com", "Google");
    userboxcomponent1.addMenuItem("www.google.com", "Ir a configuraciones");
    userboxcomponent1.addMenuItem("www.google.com", "Cerrar Sesion");
    var configuraciones_usuario = document.getElementById("sm_div_usersettings");
    configuraciones_usuario.addEventListener('click', userboxcomponent1.chesdev_components_events_handler);

}


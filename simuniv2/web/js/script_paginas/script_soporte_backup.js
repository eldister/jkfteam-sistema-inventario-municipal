var sm_registroactivo_tipoproceso = 0;
//inicio del formulario, esta parte es lo qeu se ejecutara en el inicio.
$(document).ready(function() {


    adaptar_controlesbotonformulario(TipoBotones);
    setEventosBotonesFormulario();
    addEventoSubmitFormulario();


});



//Esta funcion lo qeu hace es mostrar/ocultar los botones de la parte inferior de 
//acuerdo al estado. Puede ser sin proceso, proceso correcto o incorrecto.
function adaptar_controlesbotonformulario(val) {
    console.info('entreeeee' + val + (typeof val));
    switch (parseInt(val)) {
        case 2:
            console.info('entreeeee' + val + (typeof val));
            $(".btn_controles_sinprocesocontainer").fadeOut('fast');
            $("#btn_controles_procesocorrecto").fadeOut('slow');
            $("#btn_controles_procesoerror").fadeIn('slow');
            break;
        case 3:
            $(".btn_controles_sinprocesocontainer").fadeOut('fast');
            $("#btn_controles_procesocorrecto").fadeIn('slow');
            $("#btn_controles_procesoerror").fadeOut('fast');
            break;
        default:
            $(".btn_controles_sinprocesocontainer").fadeIn('slow');
            $("#btn_controles_procesocorrecto").fadeOut('slow');
            $("#btn_controles_procesoerror").fadeOut('slow');
            break;
    }
}

//Los eventos de cada uno de los botones disponibles/posibles del formulario
function setEventosBotonesFormulario() {

    $("#sm_btn_reintentar").click(function(e) {
        e.preventDefault();
        $(".btn_controles_sinprocesocontainer").fadeIn('slow');
        $("#btn_controles_procesocorrecto").fadeOut('slow');
        $("#btn_controles_procesoerror").fadeOut('slow');
    });
    $("#sm_btn_registrarnuevo").click(function(e) {
        window.location.assign('');
    });
}

//carga iniciar de valores para loq eu son fechas, y camops de algunos comboboxes como 
//el de tiop de vehiculo, denominacion. .
function inicializarValores(tipo_botones_requeridos) {

    TipoBotones = tipo_botones_requeridos;



}









/*seccion validacion de formulario, antes de enviar y confirmacion*/
function addEventoSubmitFormulario() {
    $("#formulario").submit(function(e) {

        e.preventDefault();
        var isformulariovalido = true;
        if (isformulariovalido) {
            if (confirm("Esta a punto de hacer un backup de la base de datos, ¿Desea continuar?")) {
                $(this).unbind();
                $(this).submit();
            }
        }
    });
}

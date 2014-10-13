function validarMail()  
{  
    var correo = document.getElementById("txtMailC").value;
    var filtro = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
    if (!filtro.test(correo)) {
        document.getElementById("mensaje").innerHTML = "Introduzca un correo v\u00e1lido";
        correo.focus;
        return false;       
    }
    else{
        document.getElementById("mensaje").innerHTML = "";
    }
}
function validarCedula(){
    var cedula = document.getElementById("txtID").value;
    var filtroExtrajera = /^\d{8}[A-Z]$/;
    var filtroNac = /^$/;
    if(!filtroExtrajera.test(cedula) && !filtroNac.test(cedula)) {
        document.getElementById("mensaje").innerHTML = "Introduzca una c\u00e9dula v\u00e1lida";
        return false;
    }
    else {
         document.getElementById("mensaje").innerHTML = "";
    }
}
function validarTelefono(){
    var telefono = document.getElementById("txtTel").value;
    var filtroTelefono = /^\d{4}-\d{4}$/;
    if(!filtroTelefono.test(telefono)) {
        document.getElementById("mensaje2").innerHTML = "Introduzca un tel\u00e9fono v\u00e1lido";
        return false;
    }
    else{
        document.getElementById("mensaje2").innerHTML = "";
    }
}
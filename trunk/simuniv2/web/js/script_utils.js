/**
 *Devuelve la fecha en formato yyyy-mm-dd 
 */
function obtenerFechaString() {
	var fechahoy = new Date();
	var dia = fechahoy.getDate();
	var mes = fechahoy.getMonth()+1;
	var anio = fechahoy.getFullYear();
	var result = anio + '-' + (mes < 10 ? ('0' + mes) : mes) + '-' + (dia < 10 ? ('0' + dia) : dia);
	return result;
}


/**
 *Funcion para poder poner la fecha a un input type date o simplemente un textbox u otro tipo de input
 * que tenga la propiedad value. 
 * @param {String} idselector El identificador del elemento (no jquery)
 */
function setFechaDatePicker(idselector){
	document.getElementById(idselector).value=obtenerFechaString();
}
function setFechaDatePicker(idselector,value){
    
	document.getElementById(idselector).value=value?value:obtenerFechaString();
        
}



function isFechaValida(fecha) {
  var regEx = /^\d{4}-\d{2}-\d{2}$/;
  return fecha.match(regEx) != null;
}

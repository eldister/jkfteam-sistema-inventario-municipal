function validarEmail(email) {
	var reg = /^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/;
	return reg.test(email)?true:false;	
};

function isNull(value){
	return value==null;
}

function isEmpty(value){
	return value=='';
}

function isUndefined(value){
	return typeof value=='undefined';
}

function isNumero(value){
	return !isNaN(value);
}

function validarMaxMin100(value){
	return (val>=0&&val<=100);
}


function validarMaxMin10(value){
	return (val>=0&&val<=10);
}


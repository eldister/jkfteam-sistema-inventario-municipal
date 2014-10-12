
function addEventosACamposDeTexto() {
    $('input[name=codigoproveedor]').change(verificarsiproveedorYaRegistrado);
}

function verificarsiproveedorYaRegistrado() {
    $.ajax({
        url: "/SIMUNI/modulos/proveedores?proceso=verificarsiproveedorexiste&codigoproveedor=" + $(this).val(),
        cache: false
    })
            .done(function(html) {
                if (html.length > 0) {
//hay error
                    $("#codigoproveedorinfo").addClass('lblcampoinvalido');
                    $("#codigoproveedorinfo").removeClass('lblcampovalido');
                    $("#codigoproveedorinfo").removeClass('lblcampowarning');
                    $("#codigoproveedorinfo").attr('title', html);
                    $("#txtID").css('border', 'solid 1px red');
                }
                else {
//correcto
                    $("#codigoproveedorinfo").removeClass('lblcampoinvalido');
                    $("#codigoproveedorinfo").addClass('lblcampovalido');
                    $("#codigoproveedorinfo").removeClass('lblcampowarning');
                    $("#txtID").css('border', 'solid 1px green');
                    $("#codigoproveedorinfo").attr('title', 'El identificador es Valido');

                }
            }

            ).error(function(html) {
//no se pudo validar.
        $("#codigoproveedorinfo").removeClass('lblcampoinvalido');
        $("#codigoproveedorinfo").removeClass('lblcampovalido');
        $("#codigoproveedorinfo").addClass('lblcampowarning');
        $("#codigoproveedorinfo").attr('title', 'No se pudo validar');
        $("#codigoproveedor").css('border', 'solid 1px yellow');
    });

}

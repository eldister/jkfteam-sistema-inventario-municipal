function fnMostrarImagen(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function(e) {
            $('#imgImagenCargada')
                    .attr('src', e.target.result).width(218).height(218);
        };
        reader.readAsDataURL(input.files[0]);
    }
}

function addEventosACamposDeTexto() {
    $('input[name=txtNumeroPlaca]').change(verificarSiPlacaYaRegistrada);
}

function verificarSiPlacaYaRegistrada() {
    $.ajax({
        url: "/SIMUNI/modulos/activos?proceso=verificarsiactivoexiste&codigoactivo=" + $(this).val(),
        cache: false
    })
            .done(function(html) {
                if (html.length > 0) {
//hay error
                    $("#txtnumplacainfo").addClass('lblcampoinvalido');
                    $("#txtnumplacainfo").removeClass('lblcampovalido');
                    $("#txtnumplacainfo").removeClass('lblcampowarning');
                    $("#txtnumplacainfo").attr('title', html);

                    $("#txtnumplaca").css('border', 'solid 1px red');
                }
                else {
//correcto
                    $("#txtnumplacainfo").removeClass('lblcampoinvalido');
                    $("#txtnumplacainfo").addClass('lblcampovalido');
                    $("#txtnumplacainfo").removeClass('lblcampowarning');
                    $("#txtnumplaca").css('border', 'solid 1px green');
                    $("#txtnumplacainfo").attr('title', 'El identificador es Valido');

                }
            }

            ).error(function(html) {
//no se pudo validar.
        $("#txtnumplacainfo").removeClass('lblcampoinvalido');
        $("#txtnumplacainfo").removeClass('lblcampovalido');
        $("#txtnumplacainfo").addClass('lblcampowarning');
        $("#txtnumplacainfo").attr('title', 'No se pudo validar');
        $("#txtnumplaca").css('border', 'solid 1px yellow');
    });

}

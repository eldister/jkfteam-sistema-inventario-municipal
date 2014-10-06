function fnMostrarImagen(input){
    if(input.files && input.files[0]){
        var reader = new FileReader();
        reader.onload = function(e){
           $('#imgImagenCargada')
              .attr('src', e.target.result).width(218).height(218);
        };
        reader.readAsDataURL(input.files[0]);
    }
}


$(document).ready(function () {
    $("#chkfechas").click(function () {
        if ($(this).is(':checked')) {
          //alert('pulsoooooo');
            $("#fefinal").removeAttr('readonly');
            $("#fefinal").attr('required', 'required');
            $("#feinicial").removeAttr('readonly');
            $("#feinicial").attr('required', 'required');
        } else {

            $("#fefinal").attr('readonly', 'readonly');
            $("#fefinal").removeAttr('required');
            $("#feinicial").attr('readonly', 'readonly');
            $("#feinicial").removeAttr('required');
        
        }
        //alert('pulsoooooo');

    });
        setFechaDatePicker('fefinal');
        setFechaDatePicker('feinicial');
});
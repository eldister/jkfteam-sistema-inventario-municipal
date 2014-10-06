/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var sm_grillageneral_txtbusquedahandler;
var sm_grillageneral_txtcambiopaginacion;

function sm_grillageneral_inicializar() {
    agregarTodosEventosGrilla();
  //  sm_grillageneral_refrescareventosgrilla();
}

function sm_grillageneral_refrescareventosgrilla(){
     removerTodosLosEventosGrilla();  
     agregarTodosEventosGrilla();
    
}
function agregarTodosEventosGrilla() {
    agregarEventosProcesosActivos();
    agregarEventosbtnBusqueda();
    agregarEventostxtBusqueda();
    agregarEventosacmbFiltro();
    agregarEventosPaginacion();
    sm_grilla_div_txtpaginacionKeyDownListener();
}
function agregarEventosProcesosActivos() {
    $(".sm_div_grillaverimagen").click({sm_grilla_proceso: "verimagenactivo"}, sm_grilla_manejadoreventos_procesos);
    $(".sm_div_grillamodificar").click({sm_grilla_proceso: "modificaractivo"}, sm_grilla_manejadoreventos_procesos);
    $(".sm_div_grillaimprimir").click({sm_grilla_proceso: "imprimirreporteactivo"}, sm_grilla_manejadoreventos_procesos);
    $(".sm_div_grillaeliminar").click({sm_grilla_proceso: "eliminaractivo"}, sm_grilla_manejadoreventos_procesos);

}
function agregarEventosbtnBusqueda() {
    $("#sm__divgrillageneral_botonbusqueda").click({sm_grilla_proceso: "busquedaactivo"}, sm_grilla_manejadorbusqueda);

}
function agregarEventostxtBusqueda() {
    $("#sm_div_busquedacontainer input[type=text]").keydown({sm_busqueda_procesoaevento: 'quitar'}, sm_grilla_busquedaporeventostxt);//cancelar settimeout
    $("#sm_div_busquedacontainer input[type=text]").keyup({sm_busqueda_procesoaevento: 'poner'}, sm_grilla_busquedaporeventostxt);//poner settimeout

}
function agregarEventosacmbFiltro() {
    $("#sm_div_grillageneral_filtrobusqueda").change(sm_grilla_busquedaporfiltrocategoria);
}
function agregarEventosPaginacion() {
    $("#sm_grilla_div_txtpaginacion").keydown({sm_cambiopag_procesoaevento: 'quitar'}, sm_grilla_paginacioncambio);//cancelar settimeout
    $("#sm_grilla_div_txtpaginacion").keyup({sm_cambiopag_procesoaevento: 'poner'}, sm_grilla_paginacioncambio);//poner settimeout    

}
function sm_grilla_busquedaporeventostxt(event) {
    var tipo_operacion = event.data.sm_busqueda_procesoaevento;
    switch (tipo_operacion) {
        case 'quitar':
            console.info('quitandooo');
            clearTimeout(sm_grillageneral_txtbusquedahandler);
            sm_grillageneral_txtbusquedahandler = undefined;
            break;
        case 'poner':
            console.info('poniendo');
            if (typeof sm_grillageneral_txtbusquedahandler === 'undefined') {
                //  alert("siii")
                sm_grillageneral_txtbusquedahandler = setTimeout(function() {
                    sm_grilla_txtmanejadorbusqueda();
                }, 1500);
            }
            break;

    }
}
function sm_grilla_manejadoreventos_procesos(event) {
    //  alert(event.data.proceso);  
    var sm_grilla_proceso = event.data.sm_grilla_proceso;
    var sm_grilla_item_id = -10;
    var element = $(event.target).parent().parent().parent().parent().find(".sm_tr_columnadatos:first-child input[type=hidden]");
    if (element.length > 0) {
        sm_grilla_item_id = element.val();
        switch (sm_grilla_proceso) {
            case 'verimagenactivo':
                console.log('elegiste verimagen');
                //alert(sm_grilla_item_id);
                if (typeof sm_grilla_proceso_verimagen === 'function') {
                    //llamar la funcion
                    sm_grilla_proceso_verimagen(sm_grilla_item_id);
                }
                break;
            case 'modificaractivo':
                console.log('elegiste modificaractivo');

                if (typeof sm_grilla_proceso_modificaractivo === 'function') {
                    //llamar la funcion
                    sm_grilla_proceso_modificaractivo(sm_grilla_item_id);
                }
                break;
            case 'imprimirreporteactivo':
                console.log('elegiste reporteactivo');

                if (typeof sm_grilla_proceso_imprimirreporteactivo === 'function') {
                    //llamar la funcion
                    sm_grilla_proceso_imprimirreporteactivo(sm_grilla_item_id);
                }
                break;
            case 'eliminaractivo':
                console.log('elegiste eliminaractivo');

                if (typeof sm_grilla_proceso_eliminaractivo === 'function') {
                    //llamar la funcion
                    if (confirm("¿Desea continuar a este proceso? (Proceso de eliminación)")) {
                        sm_grilla_proceso_eliminaractivo(sm_grilla_item_id);
                    }

                }
                break;
            case 'busquedaactivo':
                console.log('elegiste buscar jajaja');

                if (typeof sm_grilla_proceso_busquedaactivo === 'function') {
                    //llamar la funcion

                    sm_grilla_proceso_busquedaactivo();


                }
                break;
        }
    }


}


function sm_grilla_manejadorbusqueda(event) {
    var element = $(this).parent().find('input[type=text]');
    var sm_grillageneral_query = '';
    if (element.length > 0) {
        sm_grillageneral_query = element.val();
        console.info(sm_grillageneral_query);

        if (typeof sm_grilla_busqueda === 'function') {
            sm_grilla_busqueda(sm_grillageneral_query);//se haria la busqueda aqui
        }

    }
}

function sm_grilla_txtmanejadorbusqueda(event) {
    var element = $("#sm_div_busquedacontainer").find('input[type=text]');
    var sm_grillageneral_query = '';
    if (element.length > 0) {
        sm_grillageneral_query = element.val();
        console.info(sm_grillageneral_query);

        if (typeof sm_grilla_busqueda === 'function') {
            sm_grilla_busqueda(sm_grillageneral_query);//se haria la busqueda aqui
        }

    }
}


function sm_grilla_busquedaporfiltrocategoria(event) {
    var selecteditem = $(this).find(':selected');
    var selectedtext = '';
    if (selecteditem.index() > 0) {
        selectedtext = selecteditem.text();
        if (typeof sm_grilla_busquedafiltroporcategoria === 'function') {
            sm_grilla_busquedafiltroporcategoria(selectedtext);//se haria la busqueda aqui
        }

    }

}

function sm_grilla_div_txtpaginacionKeyDownListener() {
    $("#sm_grilla_div_txtpaginacion").keydown(function(e) {

        if (e.which == 8) {
            console.info(e.which);
        } else {
            if (e.which < 48) {
                return false;
            } else if (e.which < 96 && e.which > 57) {
                return false;
            } else if (e.which > 105) {
                return false;
            } /*else if((e.which > 48&&e.which < 57)||(e.which > 96&&e.which < 105)){
             e.data.sm_cambiopag_procesoaevento = 'quitar'
             sm_grilla_paginacioncambio(e);
             }*/

        }

    });
}



function sm_grilla_paginacioncambio(event) {
    var tipo_operacion = event.data.sm_cambiopag_procesoaevento;
    switch (tipo_operacion) {
        case 'quitar':
            console.info('quitandooo pagevent');
            clearTimeout(sm_grillageneral_txtcambiopaginacion);
            sm_grillageneral_txtcambiopaginacion = undefined;
            break;
        case 'poner':
            console.info('poniendo pagevent');
          //  console.info(sm_grillageneral_txtcambiopaginacion);
            if (typeof sm_grilla_manejadorcambiopaginacion === 'function') {
                //  alert("siii")
                sm_grillageneral_txtcambiopaginacion = setTimeout(function() {
                    sm_grilla_manejadorcambiopaginacion();
                }, 1500);
            }
            break;

    }
}

function sm_grilla_manejadorcambiopaginacion(event) {
    var element = $("#sm_grilla_div_txtpaginacion");
    var valorpaginacion = 5;
    //console.info("aqui dentro es "+element.length)
    if (element.length > 0) {
        valorpaginacion = element.val();
        console.info(valorpaginacion);

        if (typeof sm_grilla_cambiarpaginacion === 'function') {
            sm_grilla_cambiarpaginacion(valorpaginacion);//se haria la busqueda aqui
        }

    }
}


function removerTodosLosEventosGrilla() {
    $(".sm_div_grillaverimagen").unbind();
    $(".sm_div_grillamodificar").unbind();
    $(".sm_div_grillaimprimir").unbind();
    $(".sm_div_grillaeliminar").unbind();
    $("#sm__divgrillageneral_botonbusqueda").unbind();
    $("#sm_div_busquedacontainer input[type=text]").unbind();
    $("#sm_div_busquedacontainer input[type=text]").unbind();
    $("#sm_div_grillageneral_filtrobusqueda").unbind();
    sm_grilla_div_txtpaginacionKeyDownListener();
    $("#sm_grilla_div_txtpaginacion").unbind();
    $("#sm_grilla_div_txtpaginacion").unbind();


}
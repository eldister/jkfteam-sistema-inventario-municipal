/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var sm_grillageneral_txtbusquedahandler;
var sm_grillageneral_txtcambiopaginacion;
var sm_grillageneral_pulsoteclaCTRL = false;
var sm_grillageneral_mostrarinactivos = "";
var sm_grillageneral_buscarinactivos=false;

function sm_grillageneral_inicializar() {
    agregarTodosEventosGrilla();
    //  sm_grillageneral_refrescareventosgrilla();






}

function sm_grillageneral_eventoseleccionarfila() {
    $(".sm_tbody_filadatos").click(function() {
        $(".sm_tbody_filadatos").removeClass("sm_tbody_filadatos_seleccionado");
        $(this).addClass("sm_tbody_filadatos_seleccionado");
        // alert("si");
    });
}
function sm_grillageneral_eventodobleclickctrl() {
    $(".sm_tbody_filadatos").dblclick(function() {
        var sm_grilla_item_id = $(this).children(".sm_tr_columnadatos:first-child").find("input[type=hidden]").val();
        if (sm_grillageneral_pulsoteclaCTRL) {
            if (typeof sm_grilla_proceso_imprimirreporte === 'function') {
                //llamar la funcion
                sm_grilla_proceso_imprimirreporte(sm_grilla_item_id);
            }

        } else {
            if (typeof sm_grilla_proceso_modificar === 'function') {
                //llamar la funcion
                sm_grilla_proceso_modificar(sm_grilla_item_id);
            }
        }

    });
}
function sm_grillageneral_eventokeyupdocumento() {
    $(document).keyup(function(e) {
        sm_grillageneral_pulsoteclaCTRL = false;

    });
}
function sm_grillageneral_eventokeydowndocumento() {
    $(document).keydown(function(e) {
        if (e.keyCode == 17) {
            sm_grillageneral_pulsoteclaCTRL = true;
        }

    });
}

function sm_grillageneral_refrescareventosgrilla() {
    removerTodosLosEventosGrilla();
    agregarTodosEventosGrilla();

}
function agregarTodosEventosGrilla() {
    agregarEventosProcesosActivos();
    agregarEventosbtnBusqueda();
    agregarEventostxtBusqueda();
    agregarEventosacmbFiltro();
    agregarEventosPaginacion();
    agregarEventosAgregarButon();
    sm_grilla_div_txtpaginacionKeyDownListener();
    sm_grillageneral_eventodobleclickctrl();
    sm_grillageneral_eventokeydowndocumento();
    sm_grillageneral_eventokeyupdocumento();
    sm_grillageneral_eventoseleccionarfila();
    agregarEventosMostrarOcultos();
}
function agregarEventosProcesosActivos() {
    $(".sm_div_grillaverimagen").click({sm_grilla_proceso: "verimagen"}, sm_grilla_manejadoreventos_procesos);
    $(".sm_div_grillamodificar").click({sm_grilla_proceso: "modificar"}, sm_grilla_manejadoreventos_procesos);
    $(".sm_div_grillaimprimir").click({sm_grilla_proceso: "imprimirreporte"}, sm_grilla_manejadoreventos_procesos);
    $(".sm_div_grillaeliminar").click({sm_grilla_proceso: "eliminar"}, sm_grilla_manejadoreventos_procesos);
    $(".sm_div_grillaverdocumentos").click({sm_grilla_proceso: "uploadoc"}, sm_grilla_manejadoreventos_procesos);
    $(".sm_div_grillaverdetalle").click({sm_grilla_proceso: "verdetalle"}, sm_grilla_manejadoreventos_procesos);
}

function agregarEventosAgregarButon() {
    $(".sm_div_grillaagregar").click(sm_grilla_manejadoreventoagregar);

}
function sm_grilla_manejadoreventoagregar() {
    console.log('elegiste agregar nuevo ');
    //alert(sm_grilla_item_id);
    if (typeof sm_grilla_proceso_agregarnuevo === 'function') {
        //llamar la funcion
        sm_grilla_proceso_agregarnuevo();
    }
}
function agregarEventosbtnBusqueda() {
    $("#sm__divgrillageneral_botonbusqueda").click({sm_grilla_proceso: "busqueda"}, sm_grilla_manejadorbusqueda);

}
function agregarEventosMostrarOcultos() {

    $("#sm_checkgrillageneral_mostrarinactivos").click({sm_grilla_proceso: "inactivos"}, sm_grilla_mostrarinactivoseventos);

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

function sm_grilla_mostrarinactivoseventos(event) {

    var element = $(event.target);
    var param;
    if (element.is(":checked")) {
        console.log('elegiste ver inactivos');
        sm_grillageneral_mostrarinactivos = "&mostrar_inactivos=si";
        sm_grillageneral_buscarinactivos=true;
        param="si";
    }
    else {
        console.log('elegiste ver inactivos q');
        sm_grillageneral_buscarinactivos=false;
    }
    if (typeof sm_grilla_proceso_mostrarinactivos === 'function') {
        //llamar la funcion

        sm_grilla_proceso_mostrarinactivos(param);


    }
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

            case 'verimagen':
                console.log('elegiste verimagen');
                //alert(sm_grilla_item_id);
                if (typeof sm_grilla_proceso_verimagen === 'function') {
                    //llamar la funcion
                    sm_grilla_proceso_verimagen(sm_grilla_item_id);
                }
                break;
            case 'modificar':
                console.log('elegiste modificar');

                if (typeof sm_grilla_proceso_modificar === 'function') {
                    //llamar la funcion
                    sm_grilla_proceso_modificar(sm_grilla_item_id);
                }
                break;
            case 'imprimirreporte':
                console.log('elegiste reporte');

                if (typeof sm_grilla_proceso_imprimirreporte === 'function') {
                    //llamar la funcion
                    sm_grilla_proceso_imprimirreporte(sm_grilla_item_id);
                }
                break;
            case 'eliminar':
                console.log('elegiste eliminar');

                if (typeof sm_grilla_proceso_eliminar === 'function') {
                    //llamar la funcion
                    if (confirm("¿Desea realmente eliminar el registro?")) {
                        sm_grilla_proceso_eliminar(sm_grilla_item_id);
                    }

                }
                break;
            case 'busqueda':
                console.log('elegiste buscar jajaja');

                if (typeof sm_grilla_proceso_busqueda === 'function') {
                    //llamar la funcion

                    sm_grilla_proceso_busqueda();


                }


                break;
            case 'uploadoc':
                console.log('elegiste upload jajaja');

                if (typeof sm_grilla_proceso_uploadoc === 'function') {
                    //llamar la funcion

                    sm_grilla_proceso_uploadoc(sm_grilla_item_id);


                }

                break;
            case 'verdetalle':
                console.log('elegiste ver detalle ');

                if (typeof sm_grilla_proceso_verdetalle === 'function') {
                    //llamar la funcion

                    sm_grilla_proceso_verdetalle(sm_grilla_item_id);


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
    $(".sm_div_grillaverdocumentos").unbind();
    $(".sm_div_grillaverdetalle").unbind();
    $("#sm__divgrillageneral_botonbusqueda").unbind();
    $("#sm_div_busquedacontainer input[type=text]").unbind();
    $("#sm_div_busquedacontainer input[type=text]").unbind();
    $("#sm_div_grillageneral_filtrobusqueda").unbind();
    sm_grilla_div_txtpaginacionKeyDownListener();
    $("#sm_grilla_div_txtpaginacion").unbind();
    $("#sm_grilla_div_txtpaginacion").unbind();


}


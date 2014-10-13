<%@ page language='java' contentType='text/html; charset=ISO-8859-1' pageEncoding='ISO-8859-1'%>
<%@ taglib prefix='decorator' uri='http://claudiushauptmann.com/jsp-decorator/'%>
<decorator:decorate filename='../../../recursos/paginas/master/masterpage.jsp'>
    <decorator:content placeholder='sm_section_titulodepagina'>SIMUNI - Activos</decorator:content>    
    <decorator:content placeholder='sm_section_estilosyscriptssectioncontainer'>
        <link rel='stylesheet' href='../../../recursos/estilos/style_grillageneral.css'>
        <script src='../../../recursos/scripts/script_sm_grillageneral.js'></script>
        <script>
            $(document).ready(function() {
                sm_grillageneral_inicializar();
            });
        </script>
    </decorator:content>
    <decorator:content placeholder='sm_section_mainsectioncontainer'>
        <div id='sm_grilla_maincontainer'> 

            <div class='sm_div_operacionesbusquedacontainer'>
                <div class='sm_div_rowsim'>
                    <div class='sm_div_colsim'>
                        <span class='sm_div_label'>Filtar por</span>
                        <select id='sm_div_grillageneral_filtrobusqueda'>
                            <option>-- Seleccionar --</option>                                          
                            <option>Transporte</option>
                            <option>Tecnológico</option>
                            <option>Muebles</option>
                        </select>
                    </div> 
                    <div class='sm_div_colsim'>

                        <div id='sm_div_busquedacontainer'> 
                            <span class='sm_div_label'>Buscar activo</span>
                            <input type='text' placeholder='Ingrese su busqueda'>
                            <div id='sm__divgrillageneral_botonbusqueda'>&nbsp;</div>
                        </div>
                    </div> 
                    <div class='sm_div_colsim'>
                        <div id='sm_div_agregaritemcontainer'>
                            <a href=''>Agregar</a>
                        </div>
                    </div> 
                </div>
            </div>
            <table class='sm_div_tablemain' >
                <thead><!--seccion solo encabezado-->
                    <tr class='sm_thead_filaencabezado'>
                        <th class='sm_tr_columnaencabezado'>Identificador activo</th>
                        <th class='sm_tr_columnaencabezado'>Nombre</th>
                        <th class='sm_tr_columnaencabezado'>Categoría</th>
                        <th class='sm_tr_columnaencabezado'>Modelo</th>
                        <th class='sm_tr_columnaencabezado'>Descripción</th>
                        <th class='sm_tr_columnaencabezado'>Procesos</th>
                    </tr>
                </thead>
                <tbody>
                    <tr class='sm_tbody_filadatos'>
                        <td class='sm_tr_columnadatos'>
                            544411122354                   
                            <input type='hidden' value='544411122354'>
                        </td>
                        <td class='sm_tr_columnadatos'>Impresora multifuncional Epson        
                            <input type='hidden' value='VAlor 1'>
                        </td>
                        <td class='sm_tr_columnadatos'>Equipo tecnológico                
                            <input type='hidden' value='VAlor 1'>
                        </td>
                        <td class='sm_tr_columnadatos'>e130
                            <input type='hidden' value='VAlor 1'>
                        </td>
                        <td class='sm_tr_columnadatos'>Impresora en buen estado, comprada de segunda
                            <input type='hidden' value='VAlor 1'>
                        </td>
                        <td class='sm_tr_columnadatos'>
                            <div class='sm_td_operacionescontainer'>
                                <div class='sm_div_rowsim'>
                                    <div class='sm_div_colsim sm_div_grillamodificar'  title='Modificar información'>
                                        &nbsp;
                                    </div> 
                                    <div class='sm_div_colsim sm_div_grillaimprimir' title='Imprimir reporte'>
                                        &nbsp;
                                    </div> 
                                    <div class='sm_div_colsim sm_div_grillaeliminar' title='Eliminar activo'>
                                        &nbsp;
                                    </div> 
                                    <div class='sm_div_colsim sm_div_grillaverimagen' title='ver imagen de activo'>
                                        &nbsp;
                                    </div>                                     
                                </div>
                            </div>
                        </td>
                    </tr>
                </tbody>            
            </table>
            <div class='sm_div_paginaciongrillacontainer'>
                <div id='sm_div_paginacionelementscontainer'>
                    <span class='sm_div_elementpagcontainer'>
                        <a href=''>1</a>
                    </span>                 
                    <span class='sm_div_elementpagcontainer'>
                        <a href=''>2</a>
                    </span>                 
                    <span class='sm_div_elementpagcontainer'>
                        <a href=''>3</a>
                    </span>                 
                    <span class='sm_div_elementpagcontainer'>
                        <a href=''>4</a>
                    </span>
                </div>
                <div id='sm_divpaginaciondefiner'>
                    Pag.<input id='sm_grilla_div_txtpaginacion' placeholder='#' type='text'>
                </div>
            </div>
        </div>                    
    </decorator:content>
</decorator:decorate>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="decorator" uri="http://claudiushauptmann.com/jsp-decorator/"%>
<decorator:decorate filename='../../../recursos/paginas/master/masterpage.jsp'>
        <decorator:content placeholder='sm_section_titulodepagina'>Insert title here</decorator:content>    
        <decorator:content placeholder='sm_section_estilosyscriptssectioncontainer'>
            <link rel="stylesheet" href="../../../recursos/estilos/style_grillageneral.css">
        </decorator:content>
        <decorator:content placeholder='sm_section_mainsectioncontainer'>
      <div id='sm_grilla_maincontainer'> 
          
            <div class="sm_div_operacionesbusquedacontainer">
                <div class="sm_div_rowsim">
                    <div class="sm_div_colsim">
                        <span class="sm_div_label">Ordenar por</span>
                        <select>
                            <option>Nombre</option>                                          
                            <option>Un o</option>
                            <option>Apellidos</option>
                            <option>Telefno</option>
                            <option>Cuatro</option>
                        </select>
                    </div> 
                    <div class="sm_div_colsim">

                        <div id="sm_div_busquedacontainer"> 
                            <span class="sm_div_label">Filtrar por</span>
                            <input type="text" placeholder="Ingrese su busqueda">
                            <div id="sm_div_botonbusqueda">&nbsp;</div>
                        </div>
                    </div> 
                    <div class="sm_div_colsim">
                        <div id="sm_div_agregaritemcontainer">
                            <a href="">Agregar</a>
                        </div>
                    </div> 
                </div>
            </div>
            <table class="sm_div_tablemain" >
                <thead><!--seccion solo encabezado-->
                    <tr class="sm_thead_filaencabezado">
                        <th class="sm_tr_columnaencabezado">Columna 1</th>
                        <th class="sm_tr_columnaencabezado">Columna 2</th>
                        <th class="sm_tr_columnaencabezado">Columna 3</th>
                        <th class="sm_tr_columnaencabezado">Columna 4</th>
                        <th class="sm_tr_columnaencabezado">Columna 5</th>
                        <th class="sm_tr_columnaencabezado">Procesos</th>
                    </tr>
                </thead>
                <tbody>
                    <tr class="sm_tbody_filadatos">
                        <td class="sm_tr_columnadatos">
                            VAlor 1                   
                            <input type="hidden" value="VAlor 1">
                        </td>
                        <td class="sm_tr_columnadatos">VAlor 2
                            <input type="hidden" value="VAlor 1">
                        </td>
                        <td class="sm_tr_columnadatos">VAlor 34444 uno oto asmfa sqwieuaid fiqwuf wiufioqweufow foausdfia sd4444444444444                            
                            <input type="hidden" value="VAlor 1">
                        </td>
                        <td class="sm_tr_columnadatos">VAlor 4
                            <input type="hidden" value="VAlor 1">
                        </td>
                        <td class="sm_tr_columnadatos">VAlor 5
                            <input type="hidden" value="VAlor 1">
                        </td>
                        <td class="sm_tr_columnadatos">
                            <div class="sm_td_operacionescontainer">
                                <div class="sm_div_rowsim">
                                    <div class="sm_div_colsim" id="sm_div_grillamodificar" title="una prueba">
                                        &nbsp;
                                    </div> 
                                    <div class="sm_div_colsim" id="sm_div_grillaimprimir">
                                        &nbsp;
                                    </div> 
                                    <div class="sm_div_colsim" id="sm_div_grillaeliminar">
                                        &nbsp;
                                    </div> 
                                    <div class="sm_div_colsim" id="sm_div_grillaverimagen">
                                        &nbsp;
                                    </div>                                     
                                </div>
                            </div>
                        </td>
                    </tr>
                </tbody>            
            </table>
            <div class="sm_div_paginaciongrillacontainer">
                <div id="sm_div_paginacionelementscontainer">
                    <span class="sm_div_elementpagcontainer">
                        <a href="">1</a>
                    </span>                 
                    <span class="sm_div_elementpagcontainer">
                        <a href="">2</a>
                    </span>                 
                    <span class="sm_div_elementpagcontainer">
                        <a href="">3</a>
                    </span>                 
                    <span class="sm_div_elementpagcontainer">
                        <a href="">4</a>
                    </span>
                </div>
                <div id="sm_divpaginaciondefiner">
                    Pag.<input id="sm_grilla_div_txtpaginacion" placeholder="#" type="text">
                </div>
            </div>
        </div>                    
        </decorator:content>
</decorator:decorate>
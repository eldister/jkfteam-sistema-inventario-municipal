<%@page import="simuni.enums.Recursos"%>
<%@page import="simuni.entidades.Prestamo"%>
<%@page import="simuni.entidades.Respuesta"%>
<%@page language='java' contentType='text/html; charset=ISO-8859-1' pageEncoding='ISO-8859-1'%>
<%@taglib prefix='decorator' uri='http://claudiushauptmann.com/jsp-decorator/'%>
<%
    Prestamo prestamo = null;
    boolean proceso = false;
    String respuesta = null;

    try {
        prestamo = (Prestamo) request.getAttribute("registro");
        respuesta = request.getAttribute("respuesta").toString();
        
        if (respuesta != null && prestamo != null) {
            proceso = true;
        }
        if (prestamo == null) {
            prestamo = new Prestamo();
        }        
        
    } catch (Exception ex) {
        ex.printStackTrace();
    }
%>
<decorator:decorate filename='../../recursos/paginas/master/masterpage.jsp'>
    <decorator:content placeholder='sm_section_titulodepagina'>SIMUNI | Prestamo </decorator:content>    
    <decorator:content placeholder='sm_section_estilosyscriptssectioncontainer'>

        <script>
            $(document).ready(function() {
                $("#sm_btmopcionitem_verdoc").click(function() {
                    window.location.assign('');
                });
                $("#sm_btmopcionitem_actualhome").click(function() {
                    window.location.assign(SIMUNI_SERVER + "/prestamo?proceso=listado");
                });
                $("#sm_btmopcionitem_delete").click(function() {
                    var sm_form = $('<form  method="post" action="' + SIMUNI_SERVER + '/prestamo?proceso=eliminar">');
                    sm_form.appendTo('body');
                    sm_form.append('<input type="hidden" value="' + $("#txtprestamo").val() + '" name="registro">');
                    sm_form.append('</form>');
                    sm_form.submit();
                });
            });

            //  #sm_btmopcionitem_delete
        </script>        
    </decorator:content>

    <decorator:content placeholder='sm_div_navegationbarmenuitems' >
        <ol class="breadcrumb" >
            <li><a href="<%out.print(Recursos.Servers.MAINSERVER);%>/">Inicio</a></li>  
            <li><a href="<%out.print(Recursos.Servers.MAINSERVER);%>/prestamo?proceso=listado">Prestamo</a></li>  
            <li class="active">Eliminación</li>
        </ol>
    </decorator:content>  
    <decorator:content placeholder='sm_section_mainsectioncontainer'>
        <div  class="container">


            <fieldset>

                <legend>Proceso de eliminación de prestamo</legend>


                <div class="datos_registro">

                    <p>Los datos actuales de prestamo a eliminar:</p>

                    <table class="sm_datos_registro_table table table-hover ">
                        <tr>
                            <th>Observación</th><td><%out.print(prestamo.getObservaciones()); %></td>
                        </tr>                       
                        <tr>
                            <th>Activo</th><td><%out.print(prestamo.getIdActivo()); %></td>
                        </tr>
                        <tr>
                            <th>Estado</th><td><%out.print(prestamo.getEstado()); %></td>
                        </tr>
                        <tr id="sm_contenedor_controles">
                            <td>&nbsp;</td>
                            <%if (!proceso) {%>  
                            <td  class="btn_controles_sinprocesocontainer">
                                 <div class="sm_container_reportopcionesitem">
                                    <button class="btn btn-danger" id="sm_btmopcionitem_delete">Confirmar Eliminacion</button>
                                </div>
                            </td> 
                            <td>
                                <div class="sm_container_reportopcionesitem">
                                    <button class="btn btn-primary" id="sm_btmopcionitem_actualhome">Ir a Prestamos</button>
                                </div>
                            </td>
                             <%}%>
                            <%if (proceso) {%>     
                            <td>
                                <div class="sm_container_reportopcionesitem">
                                    <button class="btn btn-primary" id="sm_btmopcionitem_actualhome">Ir a Prestamos</button>
                                </div>
                            </td> 
                            <%}%>
                        </tr>                                       
                    </table>
                    <input type="hidden" id="txtprestamo" value="<%out.print(prestamo.getIdPrestamo()); %>">
                </div>
            </fieldset>
        </div>

    </decorator:content>

</decorator:decorate>
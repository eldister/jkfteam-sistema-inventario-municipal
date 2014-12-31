<%@page import="simuni.entidades.Respuesta"%>
<%@page import="simuni.entidades.Proveedor"%>
<%@page import="simuni.enums.Recursos"%>
<%@ page language='java' contentType='text/html; charset=ISO-8859-1' pageEncoding='ISO-8859-1'%>
<%@ taglib prefix='decorator' uri='http://claudiushauptmann.com/jsp-decorator/'%>
<%
    Proveedor proveedor = null;
    Respuesta respuesta = null;
    boolean error = false;
    boolean proceso = false;
    try {
        proveedor = (Proveedor) request.getAttribute("registro");
        respuesta = (Respuesta) request.getAttribute("respuesta");
        if (proveedor != null) {
            proceso = true;
        }
        if (proceso && respuesta != null && respuesta.getNivel() == 2) {
            error = true;
          
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    if (proveedor == null) {
        proveedor = new Proveedor();
    }
%>
<decorator:decorate filename='../../../recursos/paginas/master/masterpage.jsp'>
    <decorator:content placeholder='sm_section_titulodepagina'>SIMUNI | Resultado Eliminación Proveedor </decorator:content>    
    <decorator:content placeholder='sm_section_estilosyscriptssectioncontainer'>
        <%if (!error) {%>
        <script>
            $(document).ready(function() {
                $(".SIMUNI_LATERALMENU").addClass('hidden-print');
                $(".sm_footer_tablecontainer ").addClass('hidden-print');
                $(".breadcrumb").addClass('hidden-print');
                $("#sm_navmenuprincipal").addClass('hidden-print');
                $("#sm_btmopcionitem_print").click(function() {
                    window.print();
                });
                $("#sm_btmopcionitem_actualhome").click(function() {
                    window.location.assign(SIMUNI_SERVER + "/proveedor?proceso=listado");
                });
            });
        </script>
        <%} else {%> 
        <script>
                $("#sm_btmopcionitem_sendmessage").click(function() {
                    window.location.assign(SIMUNI_SERVER);
                });
                $("#sm_btmopcionitem_actualhome").click(function() {
                    window.location.assign(SIMUNI_SERVER + "/proveedor?proceso=listado");
                });
            });
        </script>        
        <%}%>

    </decorator:content>

    <decorator:content placeholder='sm_div_navegationbarmenuitems' >
        <ol class="breadcrumb" >
            <li><a href="<%out.print(Recursos.Servers.MAINSERVER);%>/">Inicio</a></li>  
            <li><a href="<%out.print(Recursos.Servers.MAINSERVER);%>/proveedor?proceso=listado">Proveedores</a></li>  
            <li class="active">Resultado Eliminación</li>
        </ol>
    </decorator:content>  
    <decorator:content placeholder='sm_section_mainsectioncontainer'>
        <div  class="container">
            <%if (!error) {%>
            <p class="sm_container_reporttext sm_container_reporttextheader">Reporte generado el <small>12</small> de <small>12</small> del <small>2014</small>
                por el usuario <small>fcool</small></p>  
                <%}%>
            <fieldset>
                <%if (!error) {%>
                <legend>Proceso terminado con éxito</legend>
                <%} else {%>
                <legend  class="text-danger">Proceso terminado con errores, no terminado</legend>    
                <%}%>
                <div class="datos_registro">
                    <%if (!error) {%>
                    <p>Los datos actuales de proveedor recién eliminado son:</p>
                    <%}%>
                    <table class="sm_datos_registro_table table table-hover ">
                        <tr>
                            <th>Cédula</th><td><%out.print(proveedor.getCedula()); %></td>
                            <th>Nombre</th><td><%out.print(proveedor.getNombre()); %></td> 
                            <th>1er Apellido</th><td><%out.print(proveedor.getPrimerApellido()); %></td>
                            <th>2do Apellido</th><td><%out.print(proveedor.getSegundoApellido()); %></td>
                        </tr>                       
                        <tr>
                            <th>Telefono</th><td><%out.print(proveedor.getTelFijo()); %></td>
                            <th>Telefono oficina</th><td><%out.print(proveedor.getTelOfic()); %></td>
                            <th>Telefono movil</th><td><%out.print(proveedor.getTelMovil()); %></td>
                            <th>fax</th><td><%out.print(proveedor.getTelFax()); %></td>
                        </tr>       
                        <tr>
                            <th>Apartado postal</th><td><%out.print(proveedor.getApartadoPostal()); %></td>
                            <th>Email</th><td><%out.print(proveedor.getEmail()); %></td>
                            <th>Web</th><td><%out.print(proveedor.getPaginaWeb()); %></td>
                        </tr>  
                        <tr>
                            <th>Cédula*</th><td><%out.print(proveedor.getCedulaRepresentanteLegal()); %></td>
                            <th>Nombre*</th><td><%out.print(proveedor.getNombreRepresentanteLegal()); %></td> 
                            <th>1er Apellido*</th><td><%out.print(proveedor.getPrimerApellidoRepresentanteLegal()); %></td>
                            <th>2do Apellido*</th><td><%out.print(proveedor.getSegundoApellidoRepresentanteLegal()); %></td>
                        </tr>                           
                        <tr>
                            <th>Cuenta</th><td colspan="2"><%out.print(proveedor.getNumeroCuenta()); %></td>
                            <th>Banco</th><td colspan="2"><%out.print(proveedor.getNombreBanco()); %></td>                            
                        </tr>  
                        <tr>
                            <th>Nombre de la empresa </th><td><%out.print(proveedor.getNomEmpresa()); %></td>
                            <th>Direccion</th><td colspan="2"><%out.print(proveedor.getDirEmpresa()); %></td>
                        </tr>                        
                        <tr>
                            <th>Tipo Proveedor</th><td><%out.print(proveedor.getTipoProveedor()); %></td>
                            <th>Estado Actual</th><td><%out.print(proveedor.getEstado()); %></td>
                            <th>Registro</th><td><%out.print(proveedor.getFechaRegistro()); %></td>
                        </tr>                        
                    </table>
                    <%if (!error) {%>
                    <p class="sm_container_reporttext">
                        Este reporte fue generado por el sistema de información SIMUNI, sirve de comprobante para el respaldo de procesos realizados.
                    </p>
                    <%} else {%>

                    <p class="sm_container_reporttext">
                        Proceso no termino correctamente.
                    <details>
                        <summary>Detalles del error</summary>
                        - <%out.print(respuesta.getMensaje()); %>
                    </details>
                    </p>                    
                    <%}%>
                    <%out.print(respuesta.getMensaje()); %>
                    <p>Cualquier consulta la puede realizar al correo,<code>javiercoulon@gmail.com</code></p>
                    <p><small><sub>* Datos de representante legal</sub></small></p>
                    <p><small><sub>* El registro no necesariamente se elimina por completo</sub></small></p>

                </div>
            </fieldset> 
            <div class="sm_container_reportopciones hidden-print">
                <fieldset>
                    <legend>Opciones del usuario</legend>
                    <%if (!error) {%>
                    <div class="sm_container_reportopcionesitem">
                        <button class="btn btn-primary" id="sm_btmopcionitem_print">Imprimir Reporte</button>
                    </div>  
                    <div class="sm_container_reportopcionesitem">
                        <button class="btn btn-primary" id="sm_btmopcionitem_actualhome">Ir a Proveedores</button>
                    </div>
                    <%} else {%>
                    <div class="sm_container_reportopcionesitem">
                        <button class="btn btn-danger" id="sm_btmopcionitem_sendmessage">Enviar Mensaje al encargado</button>
                    </div>    
                    <div class="sm_container_reportopcionesitem">
                        <button class="btn btn-danger" id="sm_btmopcionitem_actualhome">Ir a Proveedores</button>
                    </div>
                    <%}%>

                </fieldset>

            </div>
        </div>

    </decorator:content>

</decorator:decorate>
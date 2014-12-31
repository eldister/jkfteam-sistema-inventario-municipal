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
    <decorator:content placeholder='sm_section_titulodepagina'>SIMUNI | Resultado Registro Proveedor </decorator:content>    
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
                $("#sm_btmopcionitem_verdoc").click(function() {
                    window.location.assign(SIMUNI_SERVER+'/proveedor/documentos?proceso=listado&registro=<%out.print(proveedor.getCedula()); %>');
                });
                $("#sm_btmopcionitem_actualhome").click(function() {
                    window.location.assign(SIMUNI_SERVER + "/proveedor?proceso=listado");
                });
            });
        </script>
        <%} else {%> 
        <script>
            $(document).ready(function() {
                $("#sm_btmopcionitem_retry").click(function() {
                    $("#sm_form_retryform").attr('action', SIMUNI_SERVER + "/proveedor?proceso=rnuevo");
                    $("#sm_form_retryform").submit();
                });
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
            <li><a href="<%out.print(Recursos.Servers.MAINSERVER);%>/proveedor?proceso=nuevo">Registro de Proveedor</a></li>  
            <li class="active">Resultado</li>
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
                <legend>Proceso terminado con �xito</legend>
                <%} else {%>
                <legend  class="text-danger">Proceso terminado con errores, no terminado</legend>    
                <%}%>
                <div class="datos_registro">
                    <%if (!error) {%>
                    <p>Los datos del registro de proveedor reci�n ingresado son:</p>
                    <%}%>
                    <table class="sm_datos_registro_table table table-hover ">
                        <tr>
                            <th>C�dula</th><td><%out.print(proveedor.getCedula()); %></td>
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
                            <th>C�dula*</th><td><%out.print(proveedor.getCedulaRepresentanteLegal()); %></td>
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
                        Este reporte fue generado por el sistema de informaci�n SIMUNI, sirve de comprobante para el respaldo de procesos realizados.
                    </p>
                    <%} else {%>
                    <div>
                        <div>
                            <div>
                                <div>
                                    <div>
                                        <div>
                                            <form method="post" id="sm_form_retryform">
                                                <input type="hidden" name="cmbtipoproveedor" value="<% out.print(proveedor.getTipoProveedor()); %>">
                                                <input type="hidden" name="cmbestadoproveedor" value="<% out.print(proveedor.getEstado()); %>">
                                                <input type="hidden" name="txtcedulaproveedor" value="<% out.print(proveedor.getCedula()); %>">
                                                <input type="hidden" name="txtnombreproveedor" value="<% out.print(proveedor.getNombre()); %>">
                                                <input type="hidden" name="txtapellido1proveedor" value="<% out.print(proveedor.getPrimerApellido()); %>">
                                                <input type="hidden" name="txtapellido2proveedor" value="<% out.print(proveedor.getSegundoApellido()); %>">
                                                <input type="hidden" name="txttelefonofijo" value="<% out.print(proveedor.getTelFijo()); %>">
                                                <input type="hidden" name="txttelefonomovil" value="<% out.print(proveedor.getTelMovil()); %>">
                                                <input type="hidden" name="txttelefonooficina" value="<% out.print(proveedor.getTelOfic()); %>">
                                                <input type="hidden" name="txtfax" value="<% out.print(proveedor.getTelFax()); %>">
                                                <input type="hidden" name="txtemail" value="<% out.print(proveedor.getEmail()); %>">
                                                <input type="hidden" name="txtweb" value="<% out.print(proveedor.getPaginaWeb()); %>">
                                                <input type="hidden" name="txtapartadopostal" value="<% out.print(proveedor.getApartadoPostal()); %>">
                                                <input type="hidden" name="txtcedularepresentante" value="<% out.print(proveedor.getCedulaRepresentanteLegal()); %>">
                                                <input type="hidden" name="txtnombrerepresentante" value="<% out.print(proveedor.getNombreRepresentanteLegal()); %>">
                                                <input type="hidden" name="txtappellido1representante" value="<% out.print(proveedor.getPrimerApellidoRepresentanteLegal()); %>">
                                                <input type="hidden" name="txtappellido2representante" value="<% out.print(proveedor.getSegundoApellidoRepresentanteLegal()); %>">
                                                <input type="hidden" name="cmbnombrebanco" value="<% out.print(proveedor.getNombreBanco()); %>">
                                                <input type="hidden" name="txtnumcuenta" value="<% out.print(proveedor.getNumeroCuenta()); %>">
                                                <input type="hidden" name="txtnombreempresa" value="<% out.print(proveedor.getNomEmpresa()); %>">
                                                <input type="hidden" name="txtdireccionempresa" value="<% out.print(proveedor.getDirEmpresa()); %>">               
                                            </form>
                                        </div>
                                    </div>        
                                </div>
                            </div>
                        </div>
                    </div>
                    <p class="sm_container_reporttext">
                        Puedes volver a tratar de hacer el ingreso, a partir las opciones de usuario.
                    <details>
                        <summary>Detalles del error</summary>
                        - <%out.print(respuesta.getMensaje()); %>
                    </details>
                    </p>                    
                    <%}%>
                    <p>Cualquier consulta la puede realizar al correo,<code>javiercoulon@gmail.com</code></p>
                    <p><small><sub>* Datos de representante legal</sub></small></p>
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
                        <button class="btn btn-primary" id="sm_btmopcionitem_verdoc">Ver o agregar Documentos de Proveedor</button>
                    </div>    
                    <div class="sm_container_reportopcionesitem">
                        <button class="btn btn-primary" id="sm_btmopcionitem_actualhome">Ir a Proveedores</button>
                    </div>
                    <%} else {%>
                    <div class="sm_container_reportopcionesitem">
                        <button class="btn btn-danger" id="sm_btmopcionitem_retry">Reintentar Proceso</button>
                    </div>
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
<%
    request.setAttribute("permiso_requerido", 32);
%>
<%@page import="simuni.enums.Recursos"%>
<%@page import="simuni.entidades.Proveedor"%>
<%@page language='java' contentType='text/html; charset=ISO-8859-1' pageEncoding='ISO-8859-1'%>
<%@taglib prefix='decorator' uri='http://claudiushauptmann.com/jsp-decorator/'%>
<%
    Proveedor proveedor = null;

    try {
        proveedor = (Proveedor) request.getAttribute("registro");

    } catch (Exception ex) {
        ex.printStackTrace();
    }
    if (proveedor == null) {
        proveedor = new Proveedor();
    }
%>
<decorator:decorate filename='../../recursos/paginas/master/masterpage.jsp'>
    <decorator:content placeholder='sm_section_titulodepagina'>SIMUNI | Proveedores </decorator:content>    
    <decorator:content placeholder='sm_section_estilosyscriptssectioncontainer'>

        <script>
            $(document).ready(function() {
                $("#sm_btmopcionitem_verdoc").click(function() {
                    window.location.assign('');
                });
                $("#sm_btmopcionitem_actualhome").click(function() {
                    window.location.assign(SIMUNI_SERVER + "/proveedor?proceso=listado");
                });
                $("#sm_btmopcionitem_delete").click(function() {
                    var sm_form = $('<form  method="post" action="' + SIMUNI_SERVER + '/proveedor?proceso=eliminar">');
                    sm_form.appendTo('body');
                    sm_form.append('<input type="hidden" value="' + $("#txtcedulaproveedor").val() + '" name="registro">');
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
            <li><a href="<%out.print(Recursos.Servers.MAINSERVER);%>/proveedor?proceso=listado">Proveedores</a></li>  
            <li class="active">Eliminación</li>
        </ol>
    </decorator:content>  
    <decorator:content placeholder='sm_section_mainsectioncontainer'>
        <div  class="container">


            <fieldset>

                <legend>Proceso de eliminación de proveedor</legend>


                <div class="datos_registro">

                    <p>Los datos actuales de proveedor a eliminar:</p>

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
                            <th>Registro</th><td><%out.print(proveedor.getFechaRegistro());%></td>
                        </tr>                        
                    </table>
                    <p><small><sub>* Datos de representante legal</sub></small></p>
                    <input type="hidden" id="txtcedulaproveedor" value="<%out.print(proveedor.getCedula()); %>">
                </div>
            </fieldset> 
            <div class="sm_container_reportopciones hidden-print">
                <fieldset>
                    <legend>Opciones del usuario</legend>

                    <div class="sm_container_reportopcionesitem">
                        <button class="btn btn-danger" id="sm_btmopcionitem_delete">Confirmar Eliminacion</button>
                    </div>
                    <div class="sm_container_reportopcionesitem">
                        <button class="btn btn-primary" id="sm_btmopcionitem_verdoc">Ver o agregar Documentos de Proveedor</button>
                    </div>    
                    <div class="sm_container_reportopcionesitem">
                        <button class="btn btn-primary" id="sm_btmopcionitem_actualhome">Ir a Proveedores</button>
                    </div>

                </fieldset>

            </div>
        </div>

    </decorator:content>

</decorator:decorate>
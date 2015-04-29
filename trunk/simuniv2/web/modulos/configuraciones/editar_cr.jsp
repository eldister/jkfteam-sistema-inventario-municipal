<%
    request.setAttribute("permiso_requerido", 11);
%>
<%@page import="simuni.entidades.Respuesta"%>
<%@page import="simuni.entidades.Configuracion"%>
<%@page import="simuni.enums.Recursos"%>
<%@ page language='java' contentType='text/html; charset=ISO-8859-1' pageEncoding='ISO-8859-1'%>
<%@ taglib prefix='decorator' uri='http://claudiushauptmann.com/jsp-decorator/'%>
<%
    Configuracion configuracion = null;
    Respuesta respuesta = null;
    boolean error = false;
    boolean proceso = false;
    int tipo_botones_requeridos=1;

    try {
        respuesta = (Respuesta) request.getAttribute("respuesta");
        configuracion = (Configuracion) request.getAttribute("registro");
        if (respuesta != null) {
            proceso = true;
        }
         if (proceso) {
            if (respuesta.getNivel() == 2) {
                error = true;
            }
            
        }       
        if (configuracion == null) {
            configuracion = new Configuracion();
        }
        if(proceso&&error){
            tipo_botones_requeridos=2;
        }
        else if(proceso&&!error){
            tipo_botones_requeridos=3;
        }    
        System.out.println("listo no se que es"+tipo_botones_requeridos);
    } catch (Exception ex) {
        ex.printStackTrace();
    }

%>
<decorator:decorate filename='../../recursos/paginas/master/masterpage.jsp'>
    <decorator:content placeholder='sm_section_titulodepagina'>SIMUNI | Nuevo Configuracion </decorator:content>    
    <decorator:content placeholder='sm_section_estilosyscriptssectioncontainer'>
        <script src='<%=request.getContextPath()%>/js/script_validator.js' charset="utf-8"></script>
        <script src='<%=request.getContextPath()%>/js/script_paginas/script_editar_configuracion.js' charset="utf-8"></script>

        <style>
            #sm_tb_campos td .form-group{
                margin: 15px;
            }
            #sm_tb_campos td label small{
                font-size: 0.8em;
                font-weight: 100;
            }

            #sm_tb_campos{

                margin-left: auto;
                margin-right:auto;
            }
            #formulario {
                padding: 10px;
                margin:15px;
            }
        </style>
    </decorator:content>
    <decorator:content placeholder='sm_div_navegationbarmenuitems'>
        <ol class="breadcrumb">
            <li><a href="<%out.print(Recursos.Servers.MAINSERVER);%>/">Inicio</a></li> 
            <li><a href="<%out.print(Recursos.Servers.MAINSERVER);%>/configuracion?proceso=listado">Configuraciones</a></li> 
            <li class="active">Nuevo</li>
        </ol>

    </decorator:content>
    <decorator:content placeholder='sm_section_mainsectioncontainer'>
        <form id="formulario" class="form" method="POST" action="<%out.print(Recursos.Servers.MAINSERVER);%>/configuracion?proceso=actualizar_cr">
            <fieldset id="configuraciones">
                <legend>Registro de configuraciones <small><sup>* Campos requeridos</sup></small></legend>
                <div id="registerInformation">
                    <table id="sm_tb_campos">
                        <tr>
                            <td colspan="2">
                                <div class="form-group">
                                    <label  class="control-label"for="txtrutamysqldump">Ruta de Mysql Dump</label>
                                    <input type="text" value="<%out.print(configuracion.getPathMysqlDump()); %>" class="form-control" name="txtrutamysqldump" required="required" id="txtrutamysqldump" placeholder="Ej. c:/respaldos">
                                    <input type="hidden" value="<%out.print(configuracion.getCodigoConfiguracion()); %>" name="txtcodigoconf" id="txtcodigoconf">
                                </div>
                            </td>                          
                        </tr>
                        <tr>
                            <td colspan="2">
                                <div class="form-group">
                                    <label  class="control-label"for="txtrutabackup">Ruta de Respaldo Mysql</label>
                                    <input type="text" value="<%out.print(configuracion.getPathBackup()); %>" class="form-control" name="txtrutabackup" required="required" id="txtrutabackup" placeholder="Ej. c:/">
                                </div>
                            </td>                             
                        </tr>
                        <tr>
                            <td>
                                <div class="form-group">
                                    <label  class="control-label"for="txtlapsotiempo">Lapso de tiempo respaldo (días)</label>
                                    <input type="number" value="<%out.print(configuracion.getLapsoTiempoBackup()); %>"  required="required" class="form-control" id="txtlapsotiempo" name="txtlapsotiempo" placeholder="Ej. 30">
                                </div>
                            </td>   
                            <td>
                                <div class="form-group">
                                    <label  class="control-label"for="txtprefijo">Prefijo Backup *</label>
                                    <input type="text" value="<%out.print(configuracion.getPrefijoBackup()); %>"  class="form-control" required="required"  name="txtprefijo"  id="txtprefijo" placeholder="Ej. _simunibackup">
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="form-group">
                                    <label  class="control-label"for="txtnombreserver">Nombre del Server BD</label>
                                    <input type="text" value="<%out.print(configuracion.getServerBaseDatos()); %>" required="required" class="form-control" id="txtnombreserver" name="txtnombreserver"  placeholder="Ej. simuni">
                                </div>
                            </td>
                            <td>
                                <div class="form-group">
                                    <label  class="control-label"for="txtbd">Nombre de la Base de Datos</label>
                                    <input type="text" value="<%out.print(configuracion.getNombreBaseDatos()); %>" class="form-control" id="txtbd" required="required" name="txtbd"  placeholder="Ej. simuni">
                                </div>
                            </td>
                            <td>
                                <div class="form-group">
                                    <label  class="control-label"for="txtpuerto">Puerto del Server</label>
                                    <input type="text" value="<%out.print(configuracion.getPuertoServer()); %>" required="required" class="form-control" id="txtpuerto" name="txtpuerto"  placeholder="Ej. 3306">
                                </div>
                            </td>                            
                        </tr> 
                        <tr>
                            <td>
                                <div class="form-group">
                                    <label  class="control-label"for="txtusuariobd">Usuario</label>
                                    <input type="text" value="<%out.print(configuracion.getUsuarioBd()); %>" required="required" class="form-control" id="txtusuariobd" name="txtusuariobd"  placeholder="Ej. root">
                                </div>
                            </td>   
                            <td>
                                <div class="form-group">
                                    <label  class="control-label"for="txtcontrabd">Contraseña</label>
                                    <input type="password" value="" class="form-control" id="txtcontrabd" required="required" name="txtcontrabd"  placeholder="******">
                                </div>
                            </td>
                        </tr>                         
                        <tr>
                            <%if (proceso) { %>
                            <td colspan="5">
                                <fieldset >
                                    <legend>
                                        Resultado de la Operación
                                    </legend>
                                    <%if (respuesta != null) { %>

                                    <%
                                        if (respuesta.getNivel() == 2) {
                                    %>
                                    <div class="alert alert-danger" role="alert">
                                        <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                                        <span class="sr-only">Error:</span>
                                        <%out.print(respuesta.getMensaje());%>
                                    </div>
                                    <%} else {%>
                                    <div class="alert alert-success" role="alert">
                                        <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                                        <span class="sr-only">Correcto:</span>
                                        <%out.print(respuesta.getMensaje());%>
                                    </div>                        
                                    <%}%>                                        
                                    <%} else {%>
                                    <div>Proceso no devolvió nada!*</div>

                                    <% }%>
                                    <%}%>  
                                </fieldset>
                            </td> 
                        </tr>
                        <tr id="sm_contenedor_controles">
                            <td class="btn_controles_sinprocesocontainer">
                                <div class="form-group">
                                    <input type="submit" value="Actualizar Configuración" class="form-control btn-info">
                                </div>
                            </td>
                            <%if (proceso) {%>     
                            <td id="btn_controles_procesocorrecto">
                                <div class="form-group">
                                    <button id="sm_btn_irinicio" class="form-control btn-success">Ir a Inicio</button>
                                </div>
                            </td> 
                            <td id="btn_controles_procesoerror">
                                <div class="form-group">
                                    <button id="sm_btn_reintentar" class="form-control btn-danger">Reintentar</button>
                                </div>
                            </td>  
                            <%}%>
                        </tr>    
                    </table>
                </div>
            </fieldset>
        </form>
        <script>
            //se inicializan fechas
            inicializarValores(<%out.print("'"+tipo_botones_requeridos+"'");%>);
            

        </script>                         
    </decorator:content>
</decorator:decorate>
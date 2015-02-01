<%@page import="simuni.entidades.Baja"%>
<%@page import="simuni.utils.UtilidadesServlet"%>
<%@page import="simuni.entidades.Respuesta"%>
<%@page import="java.util.Iterator"%>
<%@page import="simuni.enums.Recursos"%>
<%
    //evaluamos si viene de un prceso en curso para ver si fue correcto y si podemos guardar el estado.


    Respuesta respuesta = null;

    boolean error = false;
    boolean proceso = false;
    int tipo_botones_requeridos=1;
    try {

        respuesta = (Respuesta) request.getAttribute("respuesta");

        if (respuesta != null) {
            proceso = true;
        }
        if (proceso) {
            if (respuesta.getNivel() == 2) {
                error = true;
            }
            
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

<%@ page language='java' contentType='text/html; charset=ISO-8859-1' pageEncoding='ISO-8859-1'%>
<%@ taglib prefix='decorator' uri='http://claudiushauptmann.com/jsp-decorator/'%>
<decorator:decorate filename='../../recursos/paginas/master/masterpage.jsp'>
    <decorator:content placeholder='sm_section_titulodepagina'>SIMUNI | Bajas - Nuevo </decorator:content>    
    <decorator:content placeholder='sm_section_estilosyscriptssectioncontainer'>
        <script src="<%=request.getContextPath()%>/js/script_paginas/script_soporte_backup.js" charset="utf-8"></script>

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

            /*imagenes*/
            .sm_imgcontainer_item{
                display: inline-block;
                padding: 5px;
                box-shadow: 0 0 5px rgba(0,0,0,0.5);
            }
            .sm_imgcontainer{
                text-align: center;

            }
        </style>

    </decorator:content>
    <decorator:content placeholder='sm_div_navegationbarmenuitems'>
        <ol class="breadcrumb">
            <li><a href="<%out.print(Recursos.Servers.MAINSERVER);%>/">Inicio</a></li>  
            <li><a href="<%out.print(Recursos.Servers.MAINSERVER);%>/soporte?proceso=index">Soporte</a></li> 
            <li class="active">Hacer Respaldo Base de Datos</li>
        </ol>
    </decorator:content>
    <decorator:content placeholder='sm_section_mainsectioncontainer'>

        <form class="form"  id="formulario" action="<%out.print(Recursos.Servers.MAINSERVER);%>/soporte?proceso=backup" method="POST">
            <fieldset id="activos">
                <legend style="<%out.print((error && proceso) ? "color:red;" : "");%>">Realizar un Respaldo no Planificado de Base de Datos</legend>
                <div id="registerInformation">
                    <table id="sm_tb_campos">
                
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
                                    <input type="submit" value="Hacer Backup" class="form-control btn-info">
                                </div>
                            </td>
                            <%if (proceso) {%>     
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
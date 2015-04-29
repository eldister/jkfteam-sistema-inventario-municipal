<%
    request.setAttribute("permiso_requerido", 26);
%>
<%-- 
    Document   : nuevo.jsp
    Created on : 03-feb-2015, 22:45:06
    Author     : Keylin
--%>

<%@page import="simuni.entidades.Prestamo"%>
<%@page import="simuni.enums.Recursos"%>
<%@page import="simuni.entidades.mantenimientos.Estado"%>
<%@page import="simuni.utils.UtilidadesServlet"%>
<%@page import="simuni.entidades.Respuesta"%>
<%@page import="java.util.Iterator"%>
<%@ page language='java' contentType='text/html; charset=ISO-8859-1' pageEncoding='ISO-8859-1'%>
<%@ taglib prefix='decorator' uri='http://claudiushauptmann.com/jsp-decorator/'%>
<%
    Prestamo prestamo = null;
    Respuesta respuesta = null;
    boolean error = false;
    boolean proceso = false;
    int tipo_botones_requeridos=1;
    
    try {
        prestamo = (Prestamo) request.getAttribute("registro");
        respuesta = (Respuesta) request.getAttribute("respuesta");
        
        if(respuesta != null && prestamo != null){
        proceso=true ; 
        }
        
        if(proceso){
        if (respuesta.getNivel() == 2){
        error = true;
        }
        }
        
        if (prestamo == null) {
            prestamo = new Prestamo();
        }

        if(proceso&&error){
        tipo_botones_requeridos=2;
        }
        else if(proceso&&!error){
        tipo_botones_requeridos=3;
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    }
      String idusuario = request.getSession().getAttribute("USERNAME") == null ? null : request.getSession().getAttribute("USERNAME").toString();

%>
<decorator:decorate filename='../../recursos/paginas/master/masterpage.jsp'>
    <decorator:content placeholder='sm_section_titulodepagina'>SIMUNI | Nuevo Prestamo </decorator:content>    
    <decorator:content placeholder='sm_section_estilosyscriptssectioncontainer'>
        <script src='<%=request.getContextPath()%>/js/script_validator.js' charset="utf-8"></script>
        <script src='<%=request.getContextPath()%>/js/script_paginas/script_nuevo_prestamo.js' charset="utf-8"></script>

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
      /* 

        
    </decorator:content>
    <decorator:content placeholder='sm_div_navegationbarmenuitems'>
        <ol class="breadcrumb">
            <li><a href="<%out.print(Recursos.Servers.MAINSERVER);%>/">Inicio</a></li> 
            <li><a href="<%out.print(Recursos.Servers.MAINSERVER);%>/prestamo?proceso=listado">Prestamo</a></li> 
            <li class="active">Nuevo</li>
        </ol>

    </decorator:content>
    <decorator:content placeholder='sm_section_mainsectioncontainer'>
        <form id="formulario" class="form" method="POST" action="<%out.print(Recursos.Servers.MAINSERVER);%>/prestamo?proceso=nuevo">
            <fieldset id="prestamo">
                <legend>Registro de prestamo <small><sup>* Campos requeridos</sup></small></legend>
                <div id="registerInformation">
                    <table id="sm_tb_campos">
                       <tr>
                           <td>
                                <div class="form-group">
                                    <label  class="control-label"for="txtfechaDevolucion">Fecha Devolución *</label>
                                    <input type="date" value="" class="form-control" name="txtfechaDevolucion" id="txtfechaDevolucion" placeholder="Ej. dd/mm/aaaa">
                                </div>
                            </td>
                            <td>
                                <div class="form-group">
                                    <label  class="control-label"for="txtobservaciones">Observaciones *</label>
                                    <textarea type="text" value="<%out.print(prestamo.getObservaciones()); %>" class="form-control" required="required"  name="txtobservaciones" id="txtobservaciones" placeholder="Observación Prestamo"></textarea>
                                </div>
                            </td>                    
                        </tr>
                        <tr>
                            <td>
                                <div class="form-group">
                                    <label  class="control-label"for="txtdepartamentosolicita">Departamento Solicitante *</label>
                                    <input type="text" value="<% out.print(idusuario); %>" class="form-control" id="txtdepartamento" name="txtdepartamento" placeholder="Ej. 88888888" >
                                </div>
                            </td>   
                            <td>
                                <div class="form-group">
                                    <label  class="control-label"for="txtidActivo">Activo *</label>
                                    <input type="text" value="<%out.print(prestamo.getIdActivo()); %>" class="form-control" required="required"  name="txtidActivo"  id="txtidActivo" placeholder="Ej. 88888888">
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td>
                                <div class="form-group">
                                    <input type="submit" value="Registrar Prestamo" class="form-control btn-info">
                                </div>
                            </td>
                            <td>
                                <div class="form-group">
                                    <input type="reset" class="form-control" value="Limpiar formulario">
                                </div>
                            </td>
                        </tr>    
                    </table>
                </div>
            </fieldset>
        </form>
    </decorator:content>
</decorator:decorate>
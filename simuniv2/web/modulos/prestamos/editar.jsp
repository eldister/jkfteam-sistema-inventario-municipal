<%
    request.setAttribute("permiso_requerido", 27);
%>
<%@page import="simuni.entidades.Prestamo"%>
<%@page import="simuni.enums.Recursos"%>
<%@page import="simuni.utils.UtilidadesServlet"%>

<%@ page language='java' contentType='text/html; charset=ISO-8859-1' pageEncoding='ISO-8859-1'%>
<%@ taglib prefix='decorator' uri='http://claudiushauptmann.com/jsp-decorator/'%>
<%
    Prestamo prestamo = null;
    
    String fecharegistro="No registrado";
    String fechamodificacion="No ha sido modificado aún";
    String fechaDevolucion="";
    try {
        prestamo = (Prestamo) request.getAttribute("registro");
        

        if (prestamo == null) {
            prestamo = new Prestamo();
        }
        fechaDevolucion = (prestamo != null) ? (UtilidadesServlet.getFechaString(((Prestamo) prestamo).getFechaDevolucion())): "";


    } catch (Exception ex) {
        ex.printStackTrace();
    }

%>
<decorator:decorate filename='../../recursos/paginas/master/masterpage.jsp'>
    <decorator:content placeholder='sm_section_titulodepagina'>SIMUNI | Editar Prestamo </decorator:content>    
    <decorator:content placeholder='sm_section_estilosyscriptssectioncontainer'>
        <script src='<%=request.getContextPath()%>/js/script_validator.js' charset="utf-8"></script>
        <script src='<%=request.getContextPath()%>/js/script_paginas/script_editar_prestamo.js' charset="utf-8"></script>

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
        <script>
        inicializarValores(<%out.print("'" + fechaDevolucion + "'");%>);
            </script>
    </decorator:content>
    <decorator:content placeholder='sm_div_navegationbarmenuitems'>
        <ol class="breadcrumb">
            <li><a href="<%out.print(Recursos.Servers.MAINSERVER);%>/">Inicio</a></li> 
            <li><a href="<%out.print(Recursos.Servers.MAINSERVER);%>/prestamo?proceso=listado">Prestamos</a></li> 
            <li class="active">Editar</li>
        </ol>

    </decorator:content>
    <decorator:content placeholder='sm_section_mainsectioncontainer'>
        <form id="formulario" class="form" method="POST" action="<%out.print(Recursos.Servers.MAINSERVER);%>/prestamo?proceso=actualizar">
            <fieldset id="prestamos">
                <legend>Actualización de prestamo <small><sup>* Campos requeridos</sup></small></legend>
                <div id="registerInformation">
                    <input type="hidden" name="txtprestamo" id="txtprestamo" value="<%out.print(prestamo.getIdPrestamo()); %>">
                    <table id="sm_tb_campos">
                        <tr>
                           <td>
                                <div class="form-group">
                                    <label  class="control-label"for="txtfechaDevolucion">Fecha Devolución *</label>
                                    <input type="date" value="" class="form-control" name="txtfechaDevolucion" id="txtfechaDevolucion">
                                </div>
                            </td>
                            <td>
                                <div class="form-group">
                                    <label  class="control-label"for="txtobservaciones">Observaciones *</label>
                                    <textarea class="form-control" required="required"  name="txtobservaciones" id="txtobservaciones" placeholder="Observación Prestamo"><%out.print(prestamo.getObservaciones()); %></textarea>
                                </div>
                            </td>                    
                        </tr>
                        <tr>
                            <td>
                                <div class="form-group">
                                    <label  class="control-label"for="txtdepartamentosolicita">Departamento Solicitante *</label>
                                    <input type="text" value="<%out.print(prestamo.getDepartamentoSolicitante()); %>" class="form-control" id="txtdepartamento" name="txtdepartamento"  >
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
                            <td>
                                <div class="form-group">
                                    <input type="submit" class="btn btn-primary" id="sm_btmopcionitem_editar" value="Actualizar Prestamo">
                                </div>
                            </td>
                            <td>
                                <div class="sm_container_reportopcionesitem">
                                    <button class="btn btn-primary" id="sm_btmopcionitem_actualhome">Ir a Prestamos</button>
                                </div>
                            </td>
                            <td>
                                &nbsp;
                            </td>
                        </tr>    
                    </table>
                </div>
            </fieldset>
        </form>
    </decorator:content>
</decorator:decorate>
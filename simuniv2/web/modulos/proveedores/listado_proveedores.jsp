<%
    request.setAttribute("permiso_requerido", 33);
%>
<%@page import="java.util.Iterator"%>
<%@page import="simuni.entidades.mantenimientos.TipoProveedor"%>
<%@page import="java.util.ArrayList"%>
<%@page import="simuni.entidades.Proveedor"%>
<%@page import="simuni.enums.Recursos"%>
<%@ page language='java' contentType='text/html; charset=ISO-8859-1' pageEncoding='ISO-8859-1'%>
<%@ taglib prefix='decorator' uri='http://claudiushauptmann.com/jsp-decorator/'%>
<%
    ArrayList<TipoProveedor> tipoproveedores = (ArrayList<TipoProveedor>) request.getAttribute("tipoproveedores");
    Iterator<TipoProveedor> itertipoproveedor = null;

    try {
        if (tipoproveedores != null) {
            itertipoproveedor = tipoproveedores.iterator();
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    }
%>

<decorator:decorate filename='../../recursos/paginas/master/masterpage.jsp'>
    <decorator:content placeholder='sm_section_titulodepagina'>SIMUNI | Editar Proveedor </decorator:content>    
    <decorator:content placeholder='sm_section_estilosyscriptssectioncontainer'>
        <script src='<%=request.getContextPath()%>/js/script_paginas/script_listado_esproveedores.js' charset="utf-8"></script>

        <style>
            #sm_tb_campos{

                margin-left: auto;
                margin-right:auto;
            }


        </style>

    </decorator:content>
    <decorator:content placeholder='sm_div_navegationbarmenuitems'>
        <ol class="breadcrumb">
            <li><a href="<%out.print(Recursos.Servers.MAINSERVER);%>/">Inicio</a></li> 
            <li><a href="<%out.print(Recursos.Servers.MAINSERVER);%>/proveedor?proceso=listado">Proveedores</a></li> 
            <li class="active">Editar</li>
        </ol>

    </decorator:content>
    <decorator:content placeholder='sm_section_mainsectioncontainer'>
        <fieldset id="proveedores">
            <legend>Listado Especial Proveedores</legend>
            <div id="registerInformation">
                <table id="sm_tb_campos">
                    <tr>
                        <td>
                            <div class="form-group">
                                <label  class="control-label" for="cmbtipoproveedor">Selecciona el area de Servicio</label>
                                <select class="form-control"  id="cmbtipoproveedor">
                                    <%
                                        if (itertipoproveedor != null && itertipoproveedor.hasNext()) {
                                            do {
                                                TipoProveedor tipoproveedor = itertipoproveedor.next();//del listado
                                    %>
                                    <option  value="<%out.print(tipoproveedor.getCodigoTipoProveedor());%>"><%out.print(tipoproveedor.getNombreTipoProveedor());%></option>
                                    <%

                                            } while (itertipoproveedor.hasNext());
                                        }
                                    %>
                                </select>
                            </div>
                        </td>
                        <td>&nbsp;</td>
                        <td>
                                <div class="form-group">
                                   <label  class="control-label">Pulsa para seleccionar</label>
                                   <button id="btn_seleccionaremail" class=" form-control  btn-primary">Seleccionar Emails</button>
                                </div>
                        </td>
                    </tr> 
                </table>
            </div>
            <h4>Listado de Proveedores</h4>   
            <div id="divrespuesta">Respuesta</div>
            <div>
                <textarea class="form-control" id="emails_textcontainer" readonly="readonly" placeholder="Emails"></textarea>
            </div>
        </fieldset>
    </decorator:content>
</decorator:decorate>
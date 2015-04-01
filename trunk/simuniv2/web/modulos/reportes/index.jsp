<%@page import="java.util.ArrayList"%>
<%@page import="simuni.entidades.mantenimientos.TipoReporte"%>
<%@page import="java.util.Iterator"%>
<%@page import="simuni.enums.Recursos"%>
<%
    //request.setAttribute("permiso_requerido", 1000);
    Iterator<TipoReporte> iter_tiporeporte = null;
    ArrayList<TipoReporte> tiposreport = (ArrayList<TipoReporte>) request.getAttribute("TIPOS_REPORTE");
    if (tiposreport != null) {
        iter_tiporeporte = tiposreport.iterator();
    }
%>

<%@ page language='java' contentType='text/html; charset=ISO-8859-1' pageEncoding='ISO-8859-1'%>
<%@ taglib prefix='decorator' uri='http://claudiushauptmann.com/jsp-decorator/'%>
<decorator:decorate filename='../../recursos/paginas/master/masterpage.jsp'>
    <decorator:content placeholder='sm_section_titulodepagina'>SIMUNI | Mantenimientos - Usuarios </decorator:content>    
    <decorator:content placeholder='sm_section_estilosyscriptssectioncontainer'>
        <script src="<%=request.getContextPath()%>/js/script_paginas/script_reportes_index.js"></script>
    </decorator:content>
    <decorator:content placeholder='sm_div_navegationbarmenuitems'>
        <ol class="breadcrumb">
            <li><a href="<%out.print(Recursos.Servers.MAINSERVER);%>/">Inicio</a></li>   
            <li class="active">Reportes</li>
        </ol>

    </decorator:content>
    <decorator:content placeholder='sm_section_mainsectioncontainer'>
        <form method="post" action="<%out.print(Recursos.Servers.MAINSERVER);%>/reporte?proceso=generacion">
            <table id="sm_tb_campos">
                <tr>
                    <td>
                        <div class="form-group">
                            <label  class="control-label"for="cmbtiporpoveedor">Tipo de Reporte</label>
                            <select class="form-control" name="cmbtiporeporte" id="cmbtiporeporte">
                                <%
                                    if (iter_tiporeporte != null && iter_tiporeporte.hasNext()) {
                                        while (iter_tiporeporte.hasNext()) {
                                            TipoReporte treporte = iter_tiporeporte.next();
                                            out.print("<option value='" + treporte.getIdtiporeporte() + "'>" + treporte.getNombretiporeporte() + "</option>");
                                        }
                                    }
                                %>
                            </select>

                        </div>
                    </td>  
                    <td>
                        <div class="form-group">
                            <label  class="control-label"for="chkfechas">Incluir Fechas</label>
                            <input type="checkbox" name="chkfechas" id="chkfechas" class="form-control"/> 
                        </div>
                    </td> 
                </tr>
                <tr>
                    <td>
                        <div class="form-group">
                            <label  class="control-label"for="feinicial">Fecha Inicial</label>
                            <input  class="form-control" type="date" id="feinicial" name="feinicial"/>
                        </div>
                    </td> 
                    <td>
                        <div class="form-group">
                            <label  class="control-label"for="fefinal">Fecha Final</label>
                            <input type="date" class="form-control" name="fefinal" id="fefinal" required="required"/>
                        </div>
                    </td>  
                    <td>
                        <div class="form-group">
                            <label  class="control-label"for="cmbestadoproveedor">Hacer consulta</label>
                            <input type="submit" value="Enviar" class="form-control btn-primary" required="required"/>
                        </div>
                    </td>                  
                </tr>
            </table>
        </form>
    </decorator:content>
</decorator:decorate>
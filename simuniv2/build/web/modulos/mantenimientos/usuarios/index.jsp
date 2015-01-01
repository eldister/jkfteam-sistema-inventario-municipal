<%@page import="java.sql.ResultSet"%>
<%@page import="simuni.clases.ui.MostradorTipoUsuario"%>
<%@page import="simuni.entidades.mantenimientos.TipoUsuario"%>
<%@page import="simuni.entidades.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="simuni.utils.UtilidadesServlet"%>
<%@ page language='java' contentType='text/html; charset=ISO-8859-1' pageEncoding='ISO-8859-1'%>
<%@ taglib prefix='decorator' uri='http://claudiushauptmann.com/jsp-decorator/'%>
<decorator:decorate filename='../../../recursos/paginas/master/masterpage.jsp'>
    <decorator:content placeholder='sm_section_titulodepagina'>SIMUNI - Activos</decorator:content>    
    <decorator:content placeholder='sm_section_estilosyscriptssectioncontainer'>
        <link rel='stylesheet' href='<%=request.getContextPath()%>/css/style_grillageneral.css'>
          
        <script src='<%=request.getContextPath()%>/js/script_sm_grillageneral.js'></script>
    

        <script>
            $(document).ready(function() {
                sm_grillageneral_inicializar();
                inicializarpaginamantenimiento();
            });
        </script>
    </decorator:content>
    <decorator:content placeholder='sm_section_mainsectioncontainer'>
        <%
        String pagaux=request.getAttribute("paginacion")!=null?request.getAttribute("paginacion").toString():"1";
        int paginacion=0;
       if( UtilidadesServlet.tryParseInt(pagaux)){
           paginacion=Integer.parseInt(pagaux);
       }
        
           ResultSet listado = (ResultSet) request.getAttribute("listado");
            out.write(new MostradorTipoUsuario().Renderizar(listado, paginacion));
        %>
        <div id="sm_body_ventanamodal">&nbsp;</div>
        <div id="sm_respuesta"></div>
        
    </decorator:content>
</decorator:decorate>
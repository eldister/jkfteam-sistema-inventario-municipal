<%@page import="simuni.classes.UI.ProveedoresFisicosMostrador"%>
<%@page import="simuni.classes.EN.ProveedorFisico"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Date"%>
<%@ page language='java' contentType='text/html; charset=ISO-8859-1' pageEncoding='ISO-8859-1'%>
<%@ taglib prefix='decorator' uri='http://claudiushauptmann.com/jsp-decorator/'%>
<decorator:decorate filename='../../../recursos/paginas/master/masterpage.jsp'>
    <decorator:content placeholder='sm_section_titulodepagina'>Insert title here</decorator:content>    
    <decorator:content placeholder='sm_section_estilosyscriptssectioncontainer'>
        <link rel='stylesheet' href='../../../recursos/estilos/style_grillageneral.css'>
        <script src='../../../recursos/scripts/script_sm_grillageneral.js'></script>
        <script>
            $(document).ready(function() {
                sm_grillageneral_inicializar();
            });
        </script>
    </decorator:content>
    <decorator:content placeholder='sm_section_mainsectioncontainer'>
        <%
            ArrayList<ProveedorFisico> to_proveedores = new ArrayList<ProveedorFisico>();
            for (int a = 0; a < 5; a++) {
                ProveedorFisico proveedorfisico = new ProveedorFisico();
                proveedorfisico.setPersona("504230366", "Francisco Coulon", "Coulon", "Ollivier");
                proveedorfisico.setPa_correoElectronico("javiercoulon@hotmail.com");
                proveedorfisico.setPb_estadoprovedor(1);
                proveedorfisico.setPd_fecharegistro(new java.sql.Date(System.currentTimeMillis()));

                to_proveedores.add(proveedorfisico);
            }
            out.write(new ProveedoresFisicosMostrador().RenderizarActivos(to_proveedores, 10));

        %>
    </decorator:content>
</decorator:decorate>
<%@page import="simuni.enums.Recursos"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="decorator" uri="http://claudiushauptmann.com/jsp-decorator/"%>
<decorator:decorate filename='recursos/paginas/master/masterpage.jsp'>
        <decorator:content placeholder='sm_section_titulodepagina'>SIMUNI - Acerca</decorator:content>    
        <decorator:content placeholder='sm_section_estilosyscriptssectioncontainer'>
            <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/recursos/estilos/style_acercade.css"
        </decorator:content>
    <decorator:content placeholder='sm_div_navegationbarmenuitems'>
        <ol class="breadcrumb">
            <li><a href="<%out.print(Recursos.Servers.MAINSERVER);%>/">Inicio</a></li>   
            <li class="active">Acerca del Sistema</li>
        </ol>
    </decorator:content>                  
        <decorator:content placeholder='sm_section_mainsectioncontainer'>
            <div id="sm_body_pAcerca">
                <div id="sm_div_logoSection">
                    <div id="sm_div_logo">
                        <span id="sm_div_nombreSist">
                            <h1>Sistema de Información para el Control del Inventario Municipal</h1>
                        </span>
                    </div>
                </div>
                <div id="sm_informacionAcerca">
                    <hr/>
                    <label>SIMUNI</label><br/>
                    <label>Sistema de información web.</label><br/>
                    <label>Version 1.0 BETA.</label><br/>
                    <label>Copyright &copy; 2015 JFK Developers Team. Derechos Reservados.</label><br/>
                    <label>Proyecto de Ingeniería en Sistemas de la información desde el 2013 - 2015</label><br/>
                    <label>Alma matter: Universidad Nacional.</label><br/>
                    <label>Institución patrocinadora: Municipalidad de Nandayure.</label><br/>
                    <p>Desarrollado bajo GNU GPL de código abierto para la implementación y desarrollo del mismo.</p>
                </div>
           </div>
        </decorator:content>
</decorator:decorate>
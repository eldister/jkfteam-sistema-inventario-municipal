<%@page import="simuni.enums.Recursos"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="decorator" uri="http://claudiushauptmann.com/jsp-decorator/"%>
<decorator:decorate filename='recursos/paginas/master/masterpage.jsp'>
    <decorator:content placeholder='sm_section_titulodepagina'>SIMUNI | Preguntas Frecuentes</decorator:content>    
    <decorator:content placeholder='sm_section_estilosyscriptssectioncontainer'>
        <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/css_paginas/style_faqs.css"
        </decorator:content>
        <decorator:content placeholder='sm_div_navegationbarmenuitems'>
            <ol class="breadcrumb">
            <li><a href="<%out.print(Recursos.Servers.MAINSERVER);%>/">Inicio</a></li>   
            <li class="active">Preguntas Frecuentes</li>
        </ol>
    </decorator:content>                  
    <decorator:content placeholder='sm_section_mainsectioncontainer'>
        <div class="faq_container">
            <fieldset>
                <legend>Encontrará las preguntas más frecuentes</legend>
                <div class="sm_div_faqcontainer">
                    <div class="sm_div_pregunta">
                        <p>Cual es la pregunta?</p>
                    </div>
                    <div class="sm_div_respuesta">
                        Esta es la respuesta
                    </div>
                </div>
                <div class="sm_div_faqcontainer">
                    <div class="sm_div_pregunta">
                        <p>Cual es la pregunta?</p>
                    </div>
                    <div class="sm_div_respuesta">
                        Esta es la respuesta
                    </div>
                </div>
            </fieldset>
        </div>
    </decorator:content>
</decorator:decorate>
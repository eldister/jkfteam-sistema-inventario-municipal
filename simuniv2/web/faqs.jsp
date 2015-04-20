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
                        <p>¿Qué es SIMUNI?</p>
                    </div>
                    <div class="sm_div_respuesta">
                       Es un sistema de software orientado a la
                       web, que utiliza la red dentro de la 
                       municipalidad y que pretende dar soporte
                       a las necesidades relacionadas
                       con la gestión de activos y sus procesos
                       tales como traslados, préstamos entre otros.
                       Con respecto al Sistema de Información para
                       el Control del Inventario Municipal (SIMUNI),
                       el área de acción principal de este sistema 
                       se localiza en el departamento de proveeduría
                       municipal. Esta área funcional se encarga de 
                       cubrir las necesidades de gestión del 
                       inventariado, de lo que hay y no hay, además
                       de los procesos que ocurren durante la vida 
                       útil de un activo.
                    </div>
                </div>
                <div class="sm_div_faqcontainer">
                    <div class="sm_div_pregunta">
                        <p>¿Cuál es el propósito de SIMUNI?</p>
                    </div>
                    <div class="sm_div_respuesta">
                       El sistema de información SIMUNI 
                       (Sistema de Información para el Control 
                       del Inventario Municipal), tiene como
                       propósito principal el poder crear un 
                       sistema de gestión automatizado de los 
                       activos de los bienes de la municipalidad,
                       además de ello poder gestionar de una manera
                       más eficiente los procesos que suceden sobre
                       los activos tales como venta, cambios,
                       traslados, préstamos y reparaciones, que 
                       comparando con la situación actual, la 
                       solución se convertiría en una herramienta 
                       muy importante para la optimización del 
                       encargado de proveeduría.
                    </div>
                </div>
                <div class="sm_div_faqcontainer">
                    <div class="sm_div_pregunta">
                        <p>¿Que significan las siglas de SIMUNI?</p>
                    </div>
                    <div class="sm_div_respuesta">
                       Significan Sistema de Información para el
                       control del inventario municipal.
                    </div>
                </div>   
                <div class="sm_div_faqcontainer">
                    <div class="sm_div_pregunta">
                        <p>¿El sistema tiene accesible su manual de usuario y tipos de ayuda?</p>
                    </div>
                    <div class="sm_div_respuesta">
                      Efectivamente, puede acceder a los 
                      manuales de usuarios de manera digital 
                      por medio del encargado de las TIC?s o
                      directamente en el sistema. 
                    </div>
                </div>
                <div class="sm_div_faqcontainer">
                    <div class="sm_div_pregunta">
                        <p>¿El sistema puede accederse de manera externa a la Municipalidad?</p>
                    </div>
                    <div class="sm_div_respuesta">
                        No, no es posible por ser interno a la organización.
                    </div>
                </div>
                <div class="sm_div_faqcontainer">
                    <div class="sm_div_pregunta">
                        <p>¿Cómo surge el proyecto?</p>
                    </div>
                    <div class="sm_div_respuesta">
                       Surge como parte de las temáticas de 
                       los curso de la carrera de Ingeniería en 
                       Sistemas de Información de la Universidad 
                       Nacional, en el cual se busca brindar una
                       solución de software a una organización 
                       utilizando conocimientos adquiridos durante
                       los años anteriores.
                    </div>
                </div>                   
                <div class="sm_div_faqcontainer">
                    <div class="sm_div_pregunta">
                        <p>¿El sistema tiene una versión móvil?</p>
                    </div>
                    <div class="sm_div_respuesta">
                      Para esta versión no se lanza ninguna aplicación 
                       móvil que interactue con el sistema.
                    </div>
                </div>  
                <div class="sm_div_faqcontainer">
                    <div class="sm_div_pregunta">
                        <p></p>
                    </div>
                    <div class="sm_div_respuesta">
                       
                    </div>
                </div>                  
            </fieldset>
        </div>
    </decorator:content>
</decorator:decorate>
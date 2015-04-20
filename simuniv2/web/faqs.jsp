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
                <legend>Encontrar� las preguntas m�s frecuentes</legend>
                <div class="sm_div_faqcontainer">
                    <div class="sm_div_pregunta">
                        <p>�Qu� es SIMUNI?</p>
                    </div>
                    <div class="sm_div_respuesta">
                       Es un sistema de software orientado a la
                       web, que utiliza la red dentro de la 
                       municipalidad y que pretende dar soporte
                       a las necesidades relacionadas
                       con la gesti�n de activos y sus procesos
                       tales como traslados, pr�stamos entre otros.
                       Con respecto al Sistema de Informaci�n para
                       el Control del Inventario Municipal (SIMUNI),
                       el �rea de acci�n principal de este sistema 
                       se localiza en el departamento de proveedur�a
                       municipal. Esta �rea funcional se encarga de 
                       cubrir las necesidades de gesti�n del 
                       inventariado, de lo que hay y no hay, adem�s
                       de los procesos que ocurren durante la vida 
                       �til de un activo.
                    </div>
                </div>
                <div class="sm_div_faqcontainer">
                    <div class="sm_div_pregunta">
                        <p>�Cu�l es el prop�sito de SIMUNI?</p>
                    </div>
                    <div class="sm_div_respuesta">
                       El sistema de informaci�n SIMUNI 
                       (Sistema de Informaci�n para el Control 
                       del Inventario Municipal), tiene como
                       prop�sito principal el poder crear un 
                       sistema de gesti�n automatizado de los 
                       activos de los bienes de la municipalidad,
                       adem�s de ello poder gestionar de una manera
                       m�s eficiente los procesos que suceden sobre
                       los activos tales como venta, cambios,
                       traslados, pr�stamos y reparaciones, que 
                       comparando con la situaci�n actual, la 
                       soluci�n se convertir�a en una herramienta 
                       muy importante para la optimizaci�n del 
                       encargado de proveedur�a.
                    </div>
                </div>
                <div class="sm_div_faqcontainer">
                    <div class="sm_div_pregunta">
                        <p>�Que significan las siglas de SIMUNI?</p>
                    </div>
                    <div class="sm_div_respuesta">
                       Significan Sistema de Informaci�n para el
                       control del inventario municipal.
                    </div>
                </div>   
                <div class="sm_div_faqcontainer">
                    <div class="sm_div_pregunta">
                        <p>�El sistema tiene accesible su manual de usuario y tipos de ayuda?</p>
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
                        <p>�El sistema puede accederse de manera externa a la Municipalidad?</p>
                    </div>
                    <div class="sm_div_respuesta">
                        No, no es posible por ser interno a la organizaci�n.
                    </div>
                </div>
                <div class="sm_div_faqcontainer">
                    <div class="sm_div_pregunta">
                        <p>�C�mo surge el proyecto?</p>
                    </div>
                    <div class="sm_div_respuesta">
                       Surge como parte de las tem�ticas de 
                       los curso de la carrera de Ingenier�a en 
                       Sistemas de Informaci�n de la Universidad 
                       Nacional, en el cual se busca brindar una
                       soluci�n de software a una organizaci�n 
                       utilizando conocimientos adquiridos durante
                       los a�os anteriores.
                    </div>
                </div>                   
                <div class="sm_div_faqcontainer">
                    <div class="sm_div_pregunta">
                        <p>�El sistema tiene una versi�n m�vil?</p>
                    </div>
                    <div class="sm_div_respuesta">
                      Para esta versi�n no se lanza ninguna aplicaci�n 
                       m�vil que interactue con el sistema.
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
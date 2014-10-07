<%@page import="simuni.classes.UI.ActivosMostrador"%>
<%@page import="simuni.classes.EN.Activos_Articulos"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Date"%>
<%@ page language='java' contentType='text/html; charset=ISO-8859-1' pageEncoding='ISO-8859-1'%>
<%@ taglib prefix='decorator' uri='http://claudiushauptmann.com/jsp-decorator/'%>
<decorator:decorate filename='../../../recursos/paginas/master/masterpage.jsp'>
    <decorator:content placeholder='sm_section_titulodepagina'>Activos Artículos</decorator:content>    
    <decorator:content placeholder='sm_section_estilosyscriptssectioncontainer'>
        <link rel='stylesheet' href='<%=request.getContextPath()%>/recursos/estilos/style_grillageneral.css'>
        <link rel='stylesheet' href='<%=request.getContextPath()%>/recursos/estilos/style_actualizacionarticulos.css'>        
        <script src='<%=request.getContextPath()%>/recursos/scripts/script_sm_grillageneral.js'></script>
        <script src='<%=request.getContextPath()%>/recursos/scripts/js_paginas/script_mantenimientoactivos.js'></script>
        
        <script>
            $(document).ready(function() {
                sm_grillageneral_inicializar();
                inicializarpaginamantenimiento();
            });
        </script>
    </decorator:content>
    <decorator:content placeholder='sm_section_mainsectioncontainer'>
        <%
            ArrayList<Activos_Articulos> to_articulo=(ArrayList<Activos_Articulos>)request.getAttribute("listadoarticulos");
            if(to_articulo==null){
                to_articulo=new ArrayList<Activos_Articulos>();
            }

            for(int a=0;a<5;a++){
              Activos_Articulos articulo=new Activos_Articulos();
            articulo.setPa_identificadorActivo("123456asdf");
            articulo.setPa_tipoActivo(2);
            articulo.setPa_marca("patito 1");
            articulo.setPa_modelo("modelo a");
            articulo.setPd_puestaOperacion(new Date());
            articulo.setPa_Descripcion("Una descripcion rara");  
            to_articulo.add(articulo);
            }
      
            
            out.write(new ActivosMostrador().RenderizarActivos(to_articulo,10));

                
        %>
        <div id="sm_body_ventanamodal">55555</div>
        <div id="sm_respuesta"></div>
    </decorator:content>
</decorator:decorate>
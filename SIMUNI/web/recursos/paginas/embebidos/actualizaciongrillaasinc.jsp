<%@page import="simuni.classes.EN.TipoActivo"%>
<%@page import="simuni.classes.UI.ActivosMostrador"%>
<%@page import="java.util.ArrayList"%>
<%@page import="simuni.classes.EN.Activos_Articulos"%>
<%
    int modorenderizacion = Integer.parseInt(request.getParameter("mod"));
    response.resetBuffer();
    switch (modorenderizacion) {
        case 1:
            ArrayList<Activos_Articulos> to_articulo = (ArrayList<Activos_Articulos>) request.getAttribute("listadoarticulos");
            ArrayList<TipoActivo>to_tipoactivo=(ArrayList<TipoActivo>)request.getAttribute("listadotiposactivo");
            out.write(new ActivosMostrador().RenderizarActualizacionArticulos(to_tipoactivo,to_articulo,10));
            break;//activos articulos
        case 2:
            break;
    }

%>
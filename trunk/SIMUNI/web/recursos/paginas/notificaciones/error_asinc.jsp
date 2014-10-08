<%@page import="java.util.Date"%>
<%
    int tipomensaje=Integer.parseInt(request.getParameter("msg"));
    response.resetBuffer();
    switch(tipomensaje){
        case 1:break;
        case 2:break;
        case 3:
            //eliminar
            out.print("El proceso de eliminación no se completó");
            out.print("Codigo de proceso :"+request.getParameter("id"));
            out.print(new Date().toString());
            break;
        
    }
%>
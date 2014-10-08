<%@page import="java.util.Date"%>
<%
    int tipomensaje=Integer.parseInt(request.getParameter("msg"));
    switch(tipomensaje){
        case 1://ingreso de activo
                      
            
        
            
            break;
        case 2:    //actualizacion de activo
            out.print("El activo "+request.getParameter("id"));
            out.print(" se actualizo correctamente");
            out.print(new Date().toString());break;
        case 3:
            //eliminar
            out.print("El proceso de eliminación se completó");
            out.print("Codigo de proceso :"+request.getParameter("id"));
            out.print(new Date().toString());
            break;
        
    }
%>
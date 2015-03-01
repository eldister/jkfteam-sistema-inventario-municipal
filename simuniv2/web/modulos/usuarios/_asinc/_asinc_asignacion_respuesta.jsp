<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="simuni.entidades.Respuesta"%>
<%
    ArrayList<Respuesta>respuesta=( ArrayList<Respuesta>)request.getAttribute("respuesta");
    Iterator<Respuesta>iter_respuesta=null;
    if(respuesta!=null){
        iter_respuesta=respuesta.iterator();
    }
    while(iter_respuesta!=null&&iter_respuesta.hasNext()){
        Respuesta resp=iter_respuesta.next();
        if(resp.getNivel()==2){
%>
<div class="alert alert-danger resultado_operacion" role="alert">
	<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
	<span class="sr-only">Error:</span>
	<%out.print(resp.getMensaje());%>
</div>
<%            
        }else{
%>  
<div class="alert alert-success resultado_operacion" role="alert">
	<span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
	<span class="sr-only">Correcto:</span>
	<%out.print(resp.getMensaje());%>
</div>
<%            
        }
    }
    
%>
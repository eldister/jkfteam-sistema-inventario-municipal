<%@page import="simuni.entidades.Respuesta"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="simuni.clases.ui.MostradorEntregaDocumento"%>
<%@page import="simuni.entidades.EntregaDocumento"%>
<%@page import="simuni.utils.UtilidadesServlet"%>


<%

    ResultSet listado = (ResultSet) request.getAttribute("listado");
    Respuesta respuesta = null;
    respuesta = (Respuesta) request.getAttribute("respuesta");

    boolean error = false;
    boolean proceso = false;
    int codigoentrega=0;
    try {
        if (respuesta != null) {
            proceso = true;
        }

        if (proceso && respuesta != null && respuesta.getNivel() == 2) {
            error = true;
            out.print("estoy entrando aqui" + respuesta.getMensaje());
        }

        if (respuesta == null) {
            respuesta = new Respuesta();
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    }

%>
<%if (listado != null && listado.next()) { %>
<fieldset>
    <legend>Detalle de Documentos</legend>
    <h4>*Documentos registrados <small>Eliminar solo de la base de datos</small></h4>
    <%if (proceso) {%>
        <h5 class="<%out.print(error ? "text-danger" : "text-success");%>"><%out.print(respuesta.getMensaje());%></h5>

    <%}%>
    <table class="table table-condensed table-striped">
        <tr>
            <th>Código de Documento</th><th>Nombre del Documento / Ir</th><th>Opción</th>
        </tr>
        <%do{%>
        <tr>
            <td><%out.print(listado.getInt(1));%><input type="hidden" value="<%out.print(listado.getInt(1));%>"></td>
            <td><a target="_blank" href="<%out.print(listado.getString(2));%>"><%out.print(listado.getString(3));%></a></td>
            <td><button class="btn bg-primary sm_asinclistardocumentos_btnrm">Remover</button></td>
        </tr>
        <%codigoentrega=listado.getInt(4);}while (listado.next()) ;%>
        <input type="hidden" value="<%out.print(codigoentrega);%>" id="sm_asinclistardocumentos_idcodigoentrega">
    </table>
</fieldset>
<%}else{%>
<h1 class="text-info text-center">No hay nada que mostrar!</h1>

<%}if (proceso&&!error) {%>
<script>
    setTimeout(NotificarUsuario,1000);
    function NotificarUsuario(){
        alert("Proceso Completo correctamente");
    }
</script>
<%}
try{
    listado.close();
}catch(Exception ex){
    ex.printStackTrace();
}
%>
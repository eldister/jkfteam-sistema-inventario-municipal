<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="decorator" uri="http://claudiushauptmann.com/jsp-decorator/"%>
<decorator:decorate filename='recursos/paginas/master/masterpage.jsp'>
    <decorator:content placeholder='sm_section_titulodepagina'>SIMUNI - Inicio</decorator:content>    
    <decorator:content placeholder='sm_section_estilosyscriptssectioncontainer'>
        <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/recursos/estilos/style_inicioIndex.css"
        </decorator:content>
        <decorator:content placeholder='sm_div_navegationbarmenuitems'>
            <span class="sm_div_navmenuitem">
                <a href="/SIMUNI">
                    Inicio
                </a>
            </span>
            <span class="sm_div_navmenuseparator">
                /
            </span>
        </decorator:content>                  
        <decorator:content placeholder='sm_section_mainsectioncontainer'>
            <div id="sm_body">
            <div id="sm_div_contenedor">
                <fieldset id="sm_fs_fs">
                    <legend id="sm_lg_leyeda">INFORMACIÓN DE: </legend>
                    <div id="sm_div_informacionUsuario">
                        <h1>Has iniciado como 
                            <%
                            String idusuario = request.getSession().getAttribute("USERNAME") == null ? null : request.getSession().getAttribute("USERNAME").toString();
                            out.print(idusuario);
                            %>
                        </h1>
                        <br/>
                  <!--      <div id="contenedorTabla">
                            <table id="sm_tb_usuario">
                                <tr>
                                    <td><label>Puesto: </label></td>
                                    <td><label name="lblPuesto">Desarrollador</label></td>
                                </tr>
                                <tr>
                                    <td><label>Departamento: </label></td>
                                    <td><label name="lblDepartamento">Desarrollo de sistemas</label></td>
                                </tr>
                                <tr>
                                    <td><label>Correo electrónico: </label></td>
                                    <td><label name="lblCorreo">ChesvCF94@una.ac.cr</label></td>
                                </tr>
                                <tr>
                                    <td><label>Teléfono: </label></td>
                                    <td><label name="lblTelefono">2331-9887</label></td>
                                </tr>
                                <tr>
                                    <td><label>Nombre de usuario: </label></td>
                                    <td><label name="lblNombUsuario">ChesvSM</label></td>
                                </tr>
                                <tr>
                                    <td><label>Tipo privilegio: </label></td>
                                    <td><label name="lblTipoPrivilegio">Usuario administrador</label></td>
                                </tr>
                            </table>
                        </div>
                  -->
                    </div>   
                </fieldset>
            </div>
        </div>
    </decorator:content>
</decorator:decorate>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="decorator" uri="http://claudiushauptmann.com/jsp-decorator/"%>
<decorator:decorate filename='recursos/paginas/master/masterpage.jsp'>
        <decorator:content placeholder='sm_section_titulodepagina'>SIMUNI - Inicio</decorator:content>    
        <decorator:content placeholder='sm_section_estilosyscriptssectioncontainer'>
            <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/recursos/estilos/style_inicioIndex.css"
        </decorator:content>
        <decorator:content placeholder='sm_section_mainsectioncontainer'>
            <div id="sm_body">
            <div id="sm_div_contenedor">
                <fieldset id="sm_fs_fs">
                    <legend id="sm_lg_leyeda">INFORMACIÓN DE: </legend>
                    <div id="sm_div_informacionUsuario">
                    <h1>Coulon Ollivier Francisco</h1>
                    <br/>
                    <div id="contenedorTabla">
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
                    </div>   
                </fieldset>
            </div>
           </div>
        </decorator:content>
</decorator:decorate>
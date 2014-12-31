<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="decorator" uri="http://claudiushauptmann.com/jsp-decorator/"%>
<decorator:decorate filename='../../../recursos/paginas/master/masterpage.jsp'>

    <decorator:content placeholder='sm_section_titulodepagina'>SIMUNI - Registro de activos</decorator:content>

    <decorator:content placeholder='sm_section_estilosyscriptssectioncontainer'>
        <style>
  
 
            .sm_div_formulario{
                padding: 20px;
            }
            .sm_div_formulario input[type=submit]{
                background-color: lightgray;
                width: 50%;
                margin-left: auto;
                margin-right: auto;
            }
        </style>
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
        <span class="sm_div_navmenuitem">
            <a href="/SIMUNI/modulos/activos?proceso=registroactivoarticulo">
                Registro de Artículos
            </a>
        </span>
        <span class="sm_div_navmenuseparator">
            /
        </span>        
    </decorator:content>
    <decorator:content placeholder='sm_section_mainsectioncontainer'>
        
        <form class="sm_div_formulario" method="POST" action="/simuniv2/tipousuario?proceso=nuevo">
            <fieldset id="sm_fs_articulos">
                <legend>Registro de Tipo Usuario</legend>
                <div class="sm_form_registroinformacion">
                    <table class="sm_tb_campos table">
                        <tr class="sm_table_formulariofila">
                            <th>
                                <label>Nombre de Tipo de Usuario</label>
                            </th>
                            <td>
                                <input type="text" class="form-control" id="txtnombretipousuario" required="required"  name="txtnombretipousuario">
                            </td>  
                        </tr>  
                        <tr class="sm_table_formulariofila">
                            <td colspan="2">
                                <input type="submit" class="form-control" value="Registrar">
                            </td>
                        </tr>                          
                    </table>
                </div>
            </fieldset>
        </form>
    </decorator:content>
</decorator:decorate>
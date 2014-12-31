package org.apache.jsp.modulos.proveedores;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import simuni.entidades.Proveedor;
import simuni.enums.Recursos;

public final class nuevo_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_decorator_content_placeholder;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_decorator_decorate_filename;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_decorator_content_placeholder = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_decorator_decorate_filename = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_decorator_content_placeholder.release();
    _jspx_tagPool_decorator_decorate_filename.release();
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html; charset=ISO-8859-1");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");

    Proveedor proveedor = null;

    try {
        proveedor = (Proveedor) request.getAttribute("registro");

        if (proveedor == null) {
            proveedor = new Proveedor();
        }

    } catch (Exception ex) {
        ex.printStackTrace();
    }


      out.write('\n');
      //  decorator:decorate
      com.claudiushauptmann.jsp.decorator.DecorateTag _jspx_th_decorator_decorate_0 = (com.claudiushauptmann.jsp.decorator.DecorateTag) _jspx_tagPool_decorator_decorate_filename.get(com.claudiushauptmann.jsp.decorator.DecorateTag.class);
      _jspx_th_decorator_decorate_0.setPageContext(_jspx_page_context);
      _jspx_th_decorator_decorate_0.setParent(null);
      _jspx_th_decorator_decorate_0.setFilename("../../recursos/paginas/master/masterpage.jsp");
      int _jspx_eval_decorator_decorate_0 = _jspx_th_decorator_decorate_0.doStartTag();
      if (_jspx_eval_decorator_decorate_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\n");
          out.write("    ");
          if (_jspx_meth_decorator_content_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_decorator_decorate_0, _jspx_page_context))
            return;
          out.write("    \n");
          out.write("    ");
          //  decorator:content
          com.claudiushauptmann.jsp.decorator.ContentTag _jspx_th_decorator_content_1 = (com.claudiushauptmann.jsp.decorator.ContentTag) _jspx_tagPool_decorator_content_placeholder.get(com.claudiushauptmann.jsp.decorator.ContentTag.class);
          _jspx_th_decorator_content_1.setPageContext(_jspx_page_context);
          _jspx_th_decorator_content_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_decorator_decorate_0);
          _jspx_th_decorator_content_1.setPlaceholder("sm_section_estilosyscriptssectioncontainer");
          int _jspx_eval_decorator_content_1 = _jspx_th_decorator_content_1.doStartTag();
          if (_jspx_eval_decorator_content_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            if (_jspx_eval_decorator_content_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
              out = _jspx_page_context.pushBody();
              _jspx_th_decorator_content_1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
              _jspx_th_decorator_content_1.doInitBody();
            }
            do {
              out.write("\n");
              out.write("        <script src='");
              out.print(request.getContextPath());
              out.write("/js/script_validator.js' charset=\"utf-8\"></script>\n");
              out.write("        <script src='");
              out.print(request.getContextPath());
              out.write("/js/script_paginas/script_nuevo_proveedor.js' charset=\"utf-8\"></script>\n");
              out.write("\n");
              out.write("        <style>\n");
              out.write("            #sm_tb_campos td .form-group{\n");
              out.write("                margin: 15px;\n");
              out.write("            }\n");
              out.write("            #sm_tb_campos td label small{\n");
              out.write("                font-size: 0.8em;\n");
              out.write("                font-weight: 100;\n");
              out.write("            }\n");
              out.write("\n");
              out.write("            #sm_tb_campos{\n");
              out.write("\n");
              out.write("                margin-left: auto;\n");
              out.write("                margin-right:auto;\n");
              out.write("            }\n");
              out.write("            #formulario {\n");
              out.write("                padding: 10px;\n");
              out.write("                margin:15px;\n");
              out.write("            }\n");
              out.write("        </style>\n");
              out.write("        <script>\n");
              out.write("            //cmbtipoproveedor\n");
              out.write("            cmbtipoproveedor = \"");
out.print(proveedor.getTipoProveedor()); 
              out.write("\";\n");
              out.write("            //cmbestadoproveedor\n");
              out.write("            cmbestadoproveedor = \"");
out.print(proveedor.getEstado()); 
              out.write("\";\n");
              out.write("            //cmbnombrebanco\n");
              out.write("            cmbnombrebanco = \"");
out.print(proveedor.getNombreBanco()); 
              out.write("\";\n");
              out.write("     \n");
              out.write("            //cmbtipoproveedor=\"Jurídico\";\n");
              out.write("        </script>\n");
              out.write("    ");
              int evalDoAfterBody = _jspx_th_decorator_content_1.doAfterBody();
              if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                break;
            } while (true);
            if (_jspx_eval_decorator_content_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
              out = _jspx_page_context.popBody();
          }
          if (_jspx_th_decorator_content_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_decorator_content_placeholder.reuse(_jspx_th_decorator_content_1);
            return;
          }
          _jspx_tagPool_decorator_content_placeholder.reuse(_jspx_th_decorator_content_1);
          out.write("\n");
          out.write("    ");
          //  decorator:content
          com.claudiushauptmann.jsp.decorator.ContentTag _jspx_th_decorator_content_2 = (com.claudiushauptmann.jsp.decorator.ContentTag) _jspx_tagPool_decorator_content_placeholder.get(com.claudiushauptmann.jsp.decorator.ContentTag.class);
          _jspx_th_decorator_content_2.setPageContext(_jspx_page_context);
          _jspx_th_decorator_content_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_decorator_decorate_0);
          _jspx_th_decorator_content_2.setPlaceholder("sm_div_navegationbarmenuitems");
          int _jspx_eval_decorator_content_2 = _jspx_th_decorator_content_2.doStartTag();
          if (_jspx_eval_decorator_content_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            if (_jspx_eval_decorator_content_2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
              out = _jspx_page_context.pushBody();
              _jspx_th_decorator_content_2.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
              _jspx_th_decorator_content_2.doInitBody();
            }
            do {
              out.write("\n");
              out.write("        <ol class=\"breadcrumb\">\n");
              out.write("            <li><a href=\"");
out.print(Recursos.Servers.MAINSERVER);
              out.write("/\">Inicio</a></li> \n");
              out.write("            <li><a href=\"");
out.print(Recursos.Servers.MAINSERVER);
              out.write("/\">Proveedores</a></li> \n");
              out.write("            <li class=\"active\">Nuevo</li>\n");
              out.write("        </ol>\n");
              out.write("\n");
              out.write("    ");
              int evalDoAfterBody = _jspx_th_decorator_content_2.doAfterBody();
              if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                break;
            } while (true);
            if (_jspx_eval_decorator_content_2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
              out = _jspx_page_context.popBody();
          }
          if (_jspx_th_decorator_content_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_decorator_content_placeholder.reuse(_jspx_th_decorator_content_2);
            return;
          }
          _jspx_tagPool_decorator_content_placeholder.reuse(_jspx_th_decorator_content_2);
          out.write("\n");
          out.write("    ");
          //  decorator:content
          com.claudiushauptmann.jsp.decorator.ContentTag _jspx_th_decorator_content_3 = (com.claudiushauptmann.jsp.decorator.ContentTag) _jspx_tagPool_decorator_content_placeholder.get(com.claudiushauptmann.jsp.decorator.ContentTag.class);
          _jspx_th_decorator_content_3.setPageContext(_jspx_page_context);
          _jspx_th_decorator_content_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_decorator_decorate_0);
          _jspx_th_decorator_content_3.setPlaceholder("sm_section_mainsectioncontainer");
          int _jspx_eval_decorator_content_3 = _jspx_th_decorator_content_3.doStartTag();
          if (_jspx_eval_decorator_content_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            if (_jspx_eval_decorator_content_3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
              out = _jspx_page_context.pushBody();
              _jspx_th_decorator_content_3.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
              _jspx_th_decorator_content_3.doInitBody();
            }
            do {
              out.write("\n");
              out.write("        <form id=\"formulario\" class=\"form\" method=\"POST\" action=\"");
out.print(Recursos.Servers.MAINSERVER);
              out.write("/proveedor?proceso=nuevo\">\n");
              out.write("            <fieldset id=\"proveedores\">\n");
              out.write("                <legend>Registro de proveedores <small><sup>* Campos requeridos</sup></small></legend>\n");
              out.write("                <div id=\"registerInformation\">\n");
              out.write("                    <table id=\"sm_tb_campos\">\n");
              out.write("                        <tr>\n");
              out.write("                            <td>\n");
              out.write("                                <div class=\"form-group\">\n");
              out.write("                                    <label  class=\"control-label\"for=\"cmbtiporpoveedor\">Tipo de Proveedor</label>\n");
              out.write("                                    <select class=\"form-control\" name=\"cmbtipoproveedor\" id=\"cmbtiporpoveedor\">\n");
              out.write("                                        <option>Físico</option>\n");
              out.write("                                        <option>Jurídico</option>\n");
              out.write("                                    </select>\n");
              out.write("                                </div>\n");
              out.write("                            </td>  \n");
              out.write("                            <td>\n");
              out.write("                                <div class=\"form-group\">\n");
              out.write("                                    <label  class=\"control-label\"for=\"cmbestadoproveedor\">Estado del Proveedor</label>\n");
              out.write("                                    <select class=\"form-control\" name=\"cmbestadoproveedor\" id=\"cmbestadoproveedor\">\n");
              out.write("                                        <option>Activo</option>\n");
              out.write("                                        <option>Inactivo</option>\n");
              out.write("                                    </select>\n");
              out.write("                                </div>\n");
              out.write("                            </td>                              \n");
              out.write("                        </tr>\n");
              out.write("                        <tr>\n");
              out.write("                            <td>\n");
              out.write("                                <div class=\"form-group\">\n");
              out.write("                                    <label  class=\"control-label\"for=\"txtcedulaproveedor\">Cédula *</label>\n");
              out.write("                                    <input type=\"text\" value=\"");
out.print(proveedor.getCedula()); 
              out.write("\" class=\"form-control\" name=\"txtcedulaproveedor\" required=\"required\" id=\"txtcedulaproveedor\" placeholder=\"Ej. 505550555\">\n");
              out.write("                                </div>\n");
              out.write("                            </td>  \n");
              out.write("                            <td>\n");
              out.write("                                <div class=\"form-group\">\n");
              out.write("                                    <label  class=\"control-label\"for=\"txtnombreproveedor\">Nombre *</label>\n");
              out.write("                                    <input type=\"text\" value=\"");
out.print(proveedor.getNombre()); 
              out.write("\" class=\"form-control\" required=\"required\" name=\"txtnombreproveedor\" id=\"txtnombreproveedor\" placeholder=\"Ej. Juan\">\n");
              out.write("                                </div>\n");
              out.write("                            </td>\n");
              out.write("                            <td>\n");
              out.write("                                <div class=\"form-group\">\n");
              out.write("                                    <label  class=\"control-label\"for=\"txtapellido1proveedor\">Primer Apellido *</label>\n");
              out.write("                                    <input type=\"text\" value=\"");
out.print(proveedor.getPrimerApellido()); 
              out.write("\" class=\"form-control\" required=\"required\"  name=\"txtapellido1proveedor\" id=\"txtappellido1proveedor\" placeholder=\"Ej. Ramírez\">\n");
              out.write("                                </div>\n");
              out.write("                            </td>\n");
              out.write("                            <td>\n");
              out.write("                                <div class=\"form-group\">\n");
              out.write("                                    <label  class=\"control-label\"for=\"txtapellido2proveedor\">Segundo Apellido</label>\n");
              out.write("                                    <input type=\"text\" value=\"");
out.print(proveedor.getSegundoApellido()); 
              out.write("\" class=\"form-control\" id=\"txtappellido2proveedor\" name=\"txtapellido2proveedor\" placeholder=\"Ej. Ramírez\">\n");
              out.write("                                </div>\n");
              out.write("                            </td>                            \n");
              out.write("                        </tr>\n");
              out.write("                        <tr>\n");
              out.write("                            <td>\n");
              out.write("                                <div class=\"form-group\">\n");
              out.write("                                    <label  class=\"control-label\"for=\"txttelefonofijo\">Teléfono Fijo</label>\n");
              out.write("                                    <input type=\"text\" value=\"");
out.print(proveedor.getTelFijo()); 
              out.write("\" class=\"form-control\" id=\"txttelefonofijo\" name=\"txttelefonofijo\" placeholder=\"Ej. 88888888\">\n");
              out.write("                                </div>\n");
              out.write("                            </td>   \n");
              out.write("                            <td>\n");
              out.write("                                <div class=\"form-group\">\n");
              out.write("                                    <label  class=\"control-label\"for=\"txttelefonomovil\">Teléfono Movil *</label>\n");
              out.write("                                    <input type=\"text\" value=\"");
out.print(proveedor.getTelMovil()); 
              out.write("\" class=\"form-control\" required=\"required\"  name=\"txttelefonomovil\"  id=\"txttelefonomovil\" placeholder=\"Ej. 88888888\">\n");
              out.write("                                </div>\n");
              out.write("                            </td>\n");
              out.write("                            <td>\n");
              out.write("                                <div class=\"form-group\">\n");
              out.write("                                    <label  class=\"control-label\"for=\"txttelefonooficina\">Teléfono Oficina</label>\n");
              out.write("                                    <input type=\"text\" value=\"");
out.print(proveedor.getTelOfic()); 
              out.write("\" class=\"form-control\" id=\"txttelefonooficina\" name=\"txttelefonooficina\"  placeholder=\"Ej. 88888888\">\n");
              out.write("                                </div>\n");
              out.write("                            </td>\n");
              out.write("                            <td>\n");
              out.write("                                <div class=\"form-group\">\n");
              out.write("                                    <label  class=\"control-label\"for=\"txtfax\">Fax</label>\n");
              out.write("                                    <input type=\"text\" value=\"");
out.print(proveedor.getTelFax()); 
              out.write("\" class=\"form-control\" id=\"txtfax\"  name=\"txtfax\"  placeholder=\"Ej. 88888888\">\n");
              out.write("                                </div>\n");
              out.write("                            </td>\n");
              out.write("                        </tr> \n");
              out.write("                        <tr>\n");
              out.write("                            <td>\n");
              out.write("                                <div class=\"form-group\">\n");
              out.write("                                    <label  class=\"control-label\"for=\"txtemail\">Correo Electrónico *</label>\n");
              out.write("                                    <input type=\"email\" value=\"");
out.print(proveedor.getEmail()); 
              out.write("\" class=\"form-control\" required=\"required\" name=\"txtemail\" id=\"txtemail\" placeholder=\"alguien@algo.com\">\n");
              out.write("                                </div>\n");
              out.write("                            </td>  \n");
              out.write("                            <td>\n");
              out.write("                                <div class=\"form-group\">\n");
              out.write("                                    <label  class=\"control-label\"for=\"txtweb\">Página Web</label>\n");
              out.write("                                    <input type=\"text\" value=\"");
out.print(proveedor.getPaginaWeb()); 
              out.write("\" class=\"form-control\" id=\"txtweb\" name=\"txtweb\" placeholder=\"www.alguien.com\">\n");
              out.write("                                </div>\n");
              out.write("                            </td> \n");
              out.write("                            <td>\n");
              out.write("                                <div class=\"form-group\">\n");
              out.write("                                    <label  class=\"control-label\"for=\"txtapartadopostal\">Apartado Postal</label>\n");
              out.write("                                    <input type=\"text\" value=\"");
out.print(proveedor.getApartadoPostal()); 
              out.write("\" class=\"form-control\" id=\"txtapartadopostal\" name=\"txtapartadopostal\" placeholder=\"900145\">\n");
              out.write("                                </div>\n");
              out.write("                            </td>   \n");
              out.write("                        </tr>\n");
              out.write("\n");
              out.write("                        <tr>\n");
              out.write("                            <td>\n");
              out.write("                                <div class=\"form-group\">\n");
              out.write("                                    <label  class=\"control-label\"for=\"txtcedularepresentante\">Cédula <small><sup> * Representante legal</sup></small></label>\n");
              out.write("                                    <input type=\"text\" value=\"");
out.print(proveedor.getCedulaRepresentanteLegal()); 
              out.write("\" class=\"form-control\" id=\"txtcedularepresentante\" name=\"txtcedularepresentante\" placeholder=\"Ej. Juan\">\n");
              out.write("                                </div>\n");
              out.write("                            </td>   \n");
              out.write("                            <td>\n");
              out.write("\n");
              out.write("                                <div class=\"form-group\">\n");
              out.write("                                    <label  class=\"control-label\"for=\"txtnombrerepresentante\">Nombre  <small><sup> * Representante legal</sup></small></label>\n");
              out.write("                                    <input type=\"text\" value=\"");
out.print(proveedor.getNombreRepresentanteLegal()); 
              out.write("\" class=\"form-control\" id=\"txtnombrerepresentante\" name=\"txtnombrerepresentante\" placeholder=\"Ej. Juan\">\n");
              out.write("                                </div>\n");
              out.write("                            </td>\n");
              out.write("                            <td>\n");
              out.write("                                <div class=\"form-group\">\n");
              out.write("                                    <label  class=\"control-label\"for=\"txtappellido1representante\">Primer Apellido <small><sup> * Representante legal</sup></small></label>\n");
              out.write("                                    <input type=\"text\" value=\"");
out.print(proveedor.getPrimerApellidoRepresentanteLegal()); 
              out.write("\" class=\"form-control\" id=\"txtappellido1representante\" name=\"txtappellido1representante\" placeholder=\"Ej. Ramírez\">\n");
              out.write("                                </div>\n");
              out.write("                            </td>\n");
              out.write("                            <td>\n");
              out.write("                                <div class=\"form-group\">\n");
              out.write("                                    <label  class=\"control-label\"for=\"txtappellido2representante\">Segundo Apellido <small><sup> * Representante legal</sup></small></label>\n");
              out.write("                                    <input type=\"text\" value=\"");
out.print(proveedor.getSegundoApellidoRepresentanteLegal()); 
              out.write("\" class=\"form-control\" id=\"txtappellido2representante\" name=\"txtappellido2representante\" placeholder=\"Ej. Ramírez\">\n");
              out.write("                                </div>\n");
              out.write("                            </td>                            \n");
              out.write("                        </tr>  \n");
              out.write("                        <tr>\n");
              out.write("                            <td>\n");
              out.write("                                <div class=\"form-group\">\n");
              out.write("                                    <label  class=\"control-label\"for=\"cmbnombrebanco\">Nombre  Banco</label>\n");
              out.write("                                    <select class=\"form-control\" id=\"cmbnombrebanco\" name=\"cmbnombrebanco\">\n");
              out.write("                                        <option>Banco Nacional</option>\n");
              out.write("                                        <option>Banco Costa Rica</option>\n");
              out.write("                                        <option>Banco HSBC</option>\n");
              out.write("                                    </select>\n");
              out.write("                                </div>\n");
              out.write("                            </td>   \n");
              out.write("                            <td>\n");
              out.write("                                <div class=\"form-group\">\n");
              out.write("                                    <label  class=\"control-label\"for=\"txtnumcuenta\">Número de Cuenta</label>\n");
              out.write("                                    <input type=\"text\" value=\"");
out.print(proveedor.getNumeroCuenta()); 
              out.write("\" class=\"form-control\" name=\"txtnumcuenta\" id=\"txtnumcuenta\" placeholder=\"Ej. 200-01-062-154\">\n");
              out.write("                                </div>\n");
              out.write("                            </td>\n");
              out.write("                        </tr>\n");
              out.write("                        <tr>\n");
              out.write("                            <td>\n");
              out.write("                                <div class=\"form-group\">\n");
              out.write("                                    <label  class=\"control-label\"for=\"txtnombreempresa\">Nombre de la Empresa</label>\n");
              out.write("                                    <input type=\"text\" value=\"");
out.print(proveedor.getNomEmpresa()); 
              out.write("\" class=\"form-control\" name=\"txtnombreempresa\" id=\"txtnombreempresa\" placeholder=\"Ej. Construcciones XYZ\">\n");
              out.write("                                </div>\n");
              out.write("                            </td>\n");
              out.write("                            <td>\n");
              out.write("                                <div class=\"form-group\">\n");
              out.write("                                    <label  class=\"control-label\"for=\"txtdireccionempresa\">Dirección de la empresa</label>\n");
              out.write("                                    <textarea class=\"form-control\"  id=\"txtdireccionempresa\" name=\"txtdireccionempresa\" placeholder=\"Ej. 500 metros sur de la escuela\">");
out.print(proveedor.getDirEmpresa());
              out.write("</textarea>\n");
              out.write("                                </div>\n");
              out.write("                            </td>\n");
              out.write("                        </tr>\n");
              out.write("\n");
              out.write("                        <tr>\n");
              out.write("                            <td>&nbsp;</td>\n");
              out.write("                            <td>\n");
              out.write("                                <div class=\"form-group\">\n");
              out.write("                                    <input type=\"submit\" value=\"Registrar Proveedor\" class=\"form-control btn-info\">\n");
              out.write("                                </div>\n");
              out.write("                            </td>\n");
              out.write("                            <td>\n");
              out.write("                                <div class=\"form-group\">\n");
              out.write("                                    <input type=\"reset\" class=\"form-control\" value=\"Limpiar formulario\">\n");
              out.write("                                </div>\n");
              out.write("                            </td>\n");
              out.write("                        </tr>    \n");
              out.write("                    </table>\n");
              out.write("                </div>\n");
              out.write("            </fieldset>\n");
              out.write("        </form>\n");
              out.write("    ");
              int evalDoAfterBody = _jspx_th_decorator_content_3.doAfterBody();
              if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                break;
            } while (true);
            if (_jspx_eval_decorator_content_3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
              out = _jspx_page_context.popBody();
          }
          if (_jspx_th_decorator_content_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _jspx_tagPool_decorator_content_placeholder.reuse(_jspx_th_decorator_content_3);
            return;
          }
          _jspx_tagPool_decorator_content_placeholder.reuse(_jspx_th_decorator_content_3);
          out.write('\n');
          int evalDoAfterBody = _jspx_th_decorator_decorate_0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_decorator_decorate_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_decorator_decorate_filename.reuse(_jspx_th_decorator_decorate_0);
        return;
      }
      _jspx_tagPool_decorator_decorate_filename.reuse(_jspx_th_decorator_decorate_0);
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_decorator_content_0(javax.servlet.jsp.tagext.JspTag _jspx_th_decorator_decorate_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  decorator:content
    com.claudiushauptmann.jsp.decorator.ContentTag _jspx_th_decorator_content_0 = (com.claudiushauptmann.jsp.decorator.ContentTag) _jspx_tagPool_decorator_content_placeholder.get(com.claudiushauptmann.jsp.decorator.ContentTag.class);
    _jspx_th_decorator_content_0.setPageContext(_jspx_page_context);
    _jspx_th_decorator_content_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_decorator_decorate_0);
    _jspx_th_decorator_content_0.setPlaceholder("sm_section_titulodepagina");
    int _jspx_eval_decorator_content_0 = _jspx_th_decorator_content_0.doStartTag();
    if (_jspx_eval_decorator_content_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_decorator_content_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_decorator_content_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_decorator_content_0.doInitBody();
      }
      do {
        out.write("SIMUNI | Nuevo Proveedor ");
        int evalDoAfterBody = _jspx_th_decorator_content_0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_decorator_content_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
        out = _jspx_page_context.popBody();
    }
    if (_jspx_th_decorator_content_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_decorator_content_placeholder.reuse(_jspx_th_decorator_content_0);
      return true;
    }
    _jspx_tagPool_decorator_content_placeholder.reuse(_jspx_th_decorator_content_0);
    return false;
  }
}

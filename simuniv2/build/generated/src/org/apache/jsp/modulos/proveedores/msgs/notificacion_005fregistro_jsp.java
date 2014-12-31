package org.apache.jsp.modulos.proveedores.msgs;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import simuni.enums.Recursos;

public final class notificacion_005fregistro_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      //  decorator:decorate
      com.claudiushauptmann.jsp.decorator.DecorateTag _jspx_th_decorator_decorate_0 = (com.claudiushauptmann.jsp.decorator.DecorateTag) _jspx_tagPool_decorator_decorate_filename.get(com.claudiushauptmann.jsp.decorator.DecorateTag.class);
      _jspx_th_decorator_decorate_0.setPageContext(_jspx_page_context);
      _jspx_th_decorator_decorate_0.setParent(null);
      _jspx_th_decorator_decorate_0.setFilename("../../../recursos/paginas/master/masterpage.jsp");
      int _jspx_eval_decorator_decorate_0 = _jspx_th_decorator_decorate_0.doStartTag();
      if (_jspx_eval_decorator_decorate_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\n");
          out.write("    ");
          if (_jspx_meth_decorator_content_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_decorator_decorate_0, _jspx_page_context))
            return;
          out.write("    \n");
          out.write("    ");
          if (_jspx_meth_decorator_content_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_decorator_decorate_0, _jspx_page_context))
            return;
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
              out.write("        <ol class=\"breadcrumb\" >\n");
              out.write("            <li><a href=\"");
out.print(Recursos.Servers.MAINSERVER);
              out.write("/\">Inicio</a></li>  \n");
              out.write("            <li><a href=\"");
out.print(Recursos.Servers.MAINSERVER);
              out.write("/\">Registro de Proveedor</a></li>  \n");
              out.write("            <li class=\"active\">Resultado</li>\n");
              out.write("        </ol>\n");
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
          if (_jspx_meth_decorator_content_3((javax.servlet.jsp.tagext.JspTag) _jspx_th_decorator_decorate_0, _jspx_page_context))
            return;
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
        out.write("SIMUNI | Proveedores ");
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

  private boolean _jspx_meth_decorator_content_1(javax.servlet.jsp.tagext.JspTag _jspx_th_decorator_decorate_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
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
        out.write("        <script>\n");
        out.write("            $(document).ready(function() {\n");
        out.write("\n");
        out.write("                $(\".SIMUNI_LATERALMENU\").addClass('hidden-print');\n");
        out.write("                $(\".sm_footer_tablecontainer \").addClass('hidden-print');\n");
        out.write("                $(\".breadcrumb\").addClass('hidden-print');\n");
        out.write("                $(\"#sm_navmenuprincipal\").addClass('hidden-print');\n");
        out.write("                \n");
        out.write("                \n");
        out.write("                $(\"#sm_btmopcionitem_print\").click(function(){\n");
        out.write("                    window.print();\n");
        out.write("                });\n");
        out.write("                $(\"#sm_btmopcionitem_verdoc\").click(function(){\n");
        out.write("                   window.location.assign('');\n");
        out.write("                });\n");
        out.write("                $(\"#sm_btmopcionitem_actualhome\").click(function(){\n");
        out.write("                   window.location.assign(SIMUNI_SERVER+\"/proveedor?proceso=nuevo\");\n");
        out.write("                });                \n");
        out.write("                \n");
        out.write("                \n");
        out.write("            });\n");
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
      return true;
    }
    _jspx_tagPool_decorator_content_placeholder.reuse(_jspx_th_decorator_content_1);
    return false;
  }

  private boolean _jspx_meth_decorator_content_3(javax.servlet.jsp.tagext.JspTag _jspx_th_decorator_decorate_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
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
        out.write("        <div  class=\"container\">\n");
        out.write("            <p class=\"sm_container_reporttext sm_container_reporttextheader\">Reporte generado el <small>12</small> de <small>12</small> del <small>2014</small>\n");
        out.write("                por el usuario <small>fcool</small></p>       \n");
        out.write("            <fieldset>\n");
        out.write("                <legend>Proceso terminado con éxito</legend>\n");
        out.write("                <div class=\"datos_registro\">\n");
        out.write("                    <p>Los datos del registro de proveedor recién ingresado son:</p>\n");
        out.write("                    <table class=\"sm_datos_registro_table table table-hover \">\n");
        out.write("                        <tr>\n");
        out.write("                            <th>Cédula</th><td>504230366</td>\n");
        out.write("                            <th>Nombre</th><td>Francisco Coulon</td> \n");
        out.write("                            <th>1er Apellido</th><td>Contreras</td>\n");
        out.write("                            <th>2do Apellido</th><td>Contreras</td>\n");
        out.write("                        </tr>                       \n");
        out.write("                        <tr>\n");
        out.write("                            <th>Telefono</th><td>888888</td>\n");
        out.write("                            <th>Telefono oficina</th><td>888888</td>\n");
        out.write("                            <th>Telefono movil</th><td>88888888888</td>\n");
        out.write("                            <th>fax</th><td>888888</td>\n");
        out.write("                        </tr>       \n");
        out.write("                        <tr>\n");
        out.write("                            <th>Apartado postal</th><td>95211</td>\n");
        out.write("                            <th>Email</th><td>javiercoulon@algo.com</td>\n");
        out.write("                            <th>Web</th><td>juasdieira.com</td>\n");
        out.write("                        </tr>  \n");
        out.write("                        <tr>\n");
        out.write("                            <th>Cédula*</th><td>504230366</td>\n");
        out.write("                            <th>Nombre*</th><td>Francisco Coulon</td> \n");
        out.write("                            <th>1er Apellido*</th><td>Contreras</td>\n");
        out.write("                            <th>2do Apellido*</th><td>Contreras</td>\n");
        out.write("                        </tr>                           \n");
        out.write("                        <tr>\n");
        out.write("                            <th>Cuenta</th><td colspan=\"2\">100-01-2015-5544</td>\n");
        out.write("                            <th>Banco</th><td colspan=\"2\">Banco Nacional</td>                            \n");
        out.write("                        </tr>  \n");
        out.write("                        <tr>\n");
        out.write("                            <th>Nombre de la empresa </th><td>Juank</td>\n");
        out.write("                            <th>Direccion</th><td colspan=\"2\">300 metos al oeste carmona centro nandayure</td>\n");
        out.write("                        </tr>                        \n");
        out.write("                        <tr>\n");
        out.write("                            <th>Tipo Proveedor</th><td>Fisico</td>\n");
        out.write("                            <th>Estado Actual</th><td>Activo</td>\n");
        out.write("                            <th>Registro</th><td>12-15-2014</td>\n");
        out.write("                        </tr>                        \n");
        out.write("                    </table>\n");
        out.write("                    <p class=\"sm_container_reporttext\">Este reporte fue generado por el sistema de información SIMUNI, sirve de comprobante para el \n");
        out.write("                        respaldo de procesos realizados.</p>\n");
        out.write("                    <p>Cualquier consulta la puede realizar al correo,<code>javiercoulon@gmail.com</code></p>\n");
        out.write("                    <p><small><sub>* Datos de representante legal</sub></small></p>\n");
        out.write("                </div>\n");
        out.write("            </fieldset> \n");
        out.write("            <div class=\"sm_container_reportopciones hidden-print\">\n");
        out.write("                <fieldset>\n");
        out.write("                    <legend>Opciones del usuario</legend>\n");
        out.write("                    <div class=\"sm_container_reportopcionesitem\">\n");
        out.write("                        <button class=\"btn btn-primary\" id=\"sm_btmopcionitem_print\">Imprimir Reporte</button>\n");
        out.write("                    </div>\n");
        out.write("                    <div class=\"sm_container_reportopcionesitem\">\n");
        out.write("                        <button class=\"btn btn-primary\" id=\"sm_btmopcionitem_verdoc\">Ver Documentos de Proveedor</button>\n");
        out.write("                    </div>    \n");
        out.write("                    <div class=\"sm_container_reportopcionesitem\">\n");
        out.write("                        <button class=\"btn btn-primary\" id=\"sm_btmopcionitem_actualhome\">Ir a Proveedores</button>\n");
        out.write("                    </div>\n");
        out.write("                \n");
        out.write("                </fieldset>\n");
        out.write("\n");
        out.write("            </div>\n");
        out.write("        </div>\n");
        out.write("\n");
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
      return true;
    }
    _jspx_tagPool_decorator_content_placeholder.reuse(_jspx_th_decorator_content_3);
    return false;
  }
}

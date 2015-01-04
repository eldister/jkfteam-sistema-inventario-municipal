<%@page import="java.util.Date"%>
<%@page import="simuni.utils.UtilidadesServlet"%>
<%@page import="simuni.entidades.mantenimientos.TipoBateria"%>
<%@page import="simuni.entidades.mantenimientos.TipoLlanta"%>
<%@page import="simuni.entidades.ImagenActivo"%>
<%@page import="java.util.Iterator"%>
<%@page import="simuni.entidades.mantenimientos.Departamento"%>
<%@page import="simuni.entidades.mantenimientos.TipoPago"%>
<%@page import="simuni.entidades.mantenimientos.Estado"%>
<%@page import="simuni.entidades.mantenimientos.TipoActivo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="simuni.entidades.Respuesta"%>
<%@page import="simuni.entidades.ActivoTransporte"%>
<%@page import="simuni.enums.Recursos"%>
<%@ page language='java' contentType='text/html; charset=ISO-8859-1' pageEncoding='ISO-8859-1'%>
<%@ taglib prefix='decorator' uri='http://claudiushauptmann.com/jsp-decorator/'%>
<%
    ActivoTransporte activotransporte = null;

    String departamento_s = "";
    String tipoactivo_s = "";
    String tipopago_s = "";
    String estadoactivo_s = "";
    String tipobateria_s="";
    
    //obtenemos los array list
    ArrayList<Departamento> departamentos = (ArrayList<Departamento>) request.getAttribute("departamentos");
    ArrayList<TipoPago> tipospago = (ArrayList<TipoPago>) request.getAttribute("tipospago");
    ArrayList<Estado> estados = (ArrayList<Estado>) request.getAttribute("estados");
    ArrayList<TipoActivo> tiposactivo = (ArrayList<TipoActivo>) request.getAttribute("tiposactivo");
    ArrayList<TipoBateria> tiposbateria = (ArrayList<TipoBateria>) request.getAttribute("tiposbateria");
    ArrayList<TipoLlanta> tiposllanta = (ArrayList<TipoLlanta>) request.getAttribute("tiposllanta");
    //tratamos de transofrmarlos en iterators
    Iterator<Departamento> iterador_departamento = null;
    Iterator<TipoPago> iterador_tipospago = null;
    Iterator<Estado> iterador_estado = null;
    Iterator<TipoActivo> iterador_tipoactivo = null;
    Iterator<TipoBateria> iterador_tipobateria = null;
    Iterator<TipoLlanta> iterador_tipollanta = null;
    Iterator<TipoLlanta> iterador_llantavehiculo=null;
    Iterator<ImagenActivo> iterador_imagenes = null;
    
    try {
        activotransporte = (ActivoTransporte) request.getAttribute("registro");
        System.out.println("pase esta parteeee");
        if (departamentos != null) {
            iterador_departamento = departamentos.iterator();
        }
        if (tipospago != null) {
            iterador_tipospago = tipospago.iterator();
        }
        if (estados != null) {
            iterador_estado = estados.iterator();
        }
        if (tiposactivo != null) {
            iterador_tipoactivo = tiposactivo.iterator();
        }
        if (tiposbateria != null) {
            iterador_tipobateria = tiposbateria.iterator();
        }

        if (activotransporte != null && activotransporte.getImagenes() != null) {
            iterador_imagenes = activotransporte.getImagenes().iterator();
        }
        if (activotransporte != null && activotransporte.getLlantas()!= null) {
            iterador_llantavehiculo = activotransporte.getLlantas().iterator();
        }

        System.out.println("pase esta parteeee114645546464");
        if (iterador_tipoactivo != null && iterador_tipoactivo.hasNext()) {
            do {
                TipoActivo tipoactivo = iterador_tipoactivo.next();
                if (tipoactivo.getIdtipoactivo() == activotransporte.getCodigoTipoActivo()) {
                    tipoactivo_s = tipoactivo.getNombretipoactivo();
                }

            } while (iterador_tipoactivo.hasNext());

        }
        if (iterador_tipospago != null && iterador_tipospago.hasNext()) {
            do {
                TipoPago tipopago = iterador_tipospago.next();
                if (tipopago.getIdtipopago() == activotransporte.getCodigoTipoPago()) {
                    tipopago_s = tipopago.getNombretipopago();
                }

            } while (iterador_tipospago.hasNext());

        }

        if (iterador_estado != null && iterador_estado.hasNext()) {
            do {
                Estado estado = iterador_estado.next();
                if (estado.getIdestado() == activotransporte.getCodigoTipoActivo()) {
                    estadoactivo_s = estado.getNombreestado();
                }

            } while (iterador_estado.hasNext());

        }

        if (iterador_departamento != null && iterador_departamento.hasNext()) {
            do {
                Departamento departamento = iterador_departamento.next();
                if (departamento.getIddepartamento() == activotransporte.getCodigoTipoActivo()) {
                    departamento_s = departamento.getNombredepartamento();
                }

            } while (iterador_departamento.hasNext());

        }
        if (iterador_tipobateria != null && iterador_tipobateria.hasNext()) {
            do {
                TipoBateria tipobateria = iterador_tipobateria.next();
                System.out.println(tipobateria.getNombretipobateria()+activotransporte.getCodigoBipoBateria());
                if (tipobateria.getIdtipobateria() == activotransporte.getCodigoBipoBateria()) {
                    tipobateria_s = tipobateria.getNombretipobateria();
                }

            } while (iterador_tipobateria.hasNext());

        }        
        System.out.println("pase esta parteeee definal a ver");
    } catch (Exception ex) {
        ex.printStackTrace();
        System.out.println("eerrror " + ex.getMessage());
    }
    if (activotransporte == null) {
        activotransporte = new ActivoTransporte();
    }

    
%>
<decorator:decorate filename='../../recursos/paginas/master/masterpage.jsp'>
    <decorator:content placeholder='sm_section_titulodepagina'>SIMUNI | Reporte Activo </decorator:content>    
    <decorator:content placeholder='sm_section_estilosyscriptssectioncontainer'>
        <style>

        </style>
        <script>
            $(document).ready(function() {
                $(".SIMUNI_LATERALMENU").addClass('hidden-print');
                $(".sm_footer_tablecontainer ").addClass('hidden-print');
                $(".breadcrumb").addClass('hidden-print');
                $("#sm_navmenuprincipal").addClass('hidden-print');
                $("#sm_btmopcionitem_print").click(function() {
                    window.print();
                });
                $("#sm_btmopcionitem_editar").click(function() {
                    window.location.assign(SIMUNI_SERVER + '/activo?proceso=actualizar&registro=<%out.print(activotransporte.getPlacaActivo()); %>');
                });
                $("#sm_btmopcionitem_actualhome").click(function() {
                    window.location.assign(SIMUNI_SERVER + "/activo?proceso=listado");
                });
            });
        </script>

    </decorator:content>

    <decorator:content placeholder='sm_div_navegationbarmenuitems' >
        <ol class="breadcrumb" >
            <li><a href="<%out.print(Recursos.Servers.MAINSERVER);%>/">Inicio</a></li>  
            <li><a href="<%out.print(Recursos.Servers.MAINSERVER);%>/activo?proceso=listado">Activos</a></li>  
            <li class="active">Reporte Rápido</li>
        </ol>
    </decorator:content>  
    <decorator:content placeholder='sm_section_mainsectioncontainer'>
        <div  class="container">
            <p class="sm_container_reporttext sm_container_reporttextheader">Reporte generado el <small><%out.print(UtilidadesServlet.getFechaconTiempoString(new Date()));%></small>
                por el usuario <small>fcool</small></p>  

            <fieldset>
                <legend  class="text-danger">Reporte Rápido de un Registro de Transporte</legend>    
                <div class="datos_registro">
                    <table class="sm_datos_registro_table table table-hover ">
                        <tr>
                            <th>Código/Placa de Activo</th><td><%out.print(activotransporte.getPlacaActivo()); %></td>
                            <th>Fecha de Adquisición</th><td><%out.print(activotransporte.getFechaAdquisicion()); %></td> 
                            <th>Monto</th><td><%out.print(activotransporte.getPrecio()); %></td>
                            <th>Clasificación</th><td><%out.print(tipoactivo_s); %></td>
                        </tr>                       
                        <tr>
                            <th>Departamento</th><td><%out.print(departamento_s); %></td>
                            <th>Tipo de Pago</th><td><%out.print(tipopago_s); %></td>
                            <th>Denominacion</th><td><%out.print(activotransporte.getDenominacion()); %></td>
                            <th>Estado</th><td><%out.print(estadoactivo_s); %></td>
                        </tr>       
                        <tr>
                            <th>Consecutivo Registro</th><td><%out.print(activotransporte.getCodigoActivoTransporte()); %></td>
                            <th>Marca</th><td><%out.print(activotransporte.getMarca()); %></td>
                            <th>Modelo</th><td><%out.print(activotransporte.getModelo()); %></td>
                             <th>Batería</th><td><%out.print(tipobateria_s); %></td>
                        </tr>  
                        <tr>
                            <th>Porcentaje Depreciación</th><td><%out.print(activotransporte.getPorcentajeDepreciacion()); %></td>
                            <th>Porcentaje Rescate</th><td><%out.print(activotransporte.getPorcentajeRescate()); %></td> 
                            <th>Inicio de Operación</th><td><%out.print(activotransporte.getFechaInicio()); %></td>
                            <th>Fabricación</th><td><%out.print(activotransporte.getAnioFabrica()); %>
                                <input type="hidden" id="codigo_transporte" value="<%out.print(activotransporte.getPlacaActivo());%>">
                                <input type="hidden" id="codigo_vehiculo" value="<%out.print(activotransporte.getCodigoActivoTransporte());%>">
                            </td>
                        </tr> 
                        <tr>
                            <th>Número de Motor</th><td><%out.print(activotransporte.getNumeroMotor()); %></td>
                            <th>Número de Chasis</th><td><%out.print(activotransporte.getNumeroChasis()); %></td> 
                            <th>Número de Placa</th><td><%out.print(activotransporte.getPlaca()); %></td>
                            <th>Cilindros</th><td><%out.print(activotransporte.getCilindros()); %></td>                                 
                        </tr>
                        <tr>
                            <%
                            if(iterador_llantavehiculo!=null&&iterador_llantavehiculo.hasNext()){
                                do{    
                                    if (tiposllanta != null) {
                                        TipoLlanta llantavehiculo=iterador_llantavehiculo.next();
                                        iterador_tipollanta = tiposllanta.iterator();
                                        while(iterador_tipollanta.hasNext()){
                                          TipoLlanta tipoaux=iterador_tipollanta.next();
                                       
                                          if(tipoaux.getIdtipollanta()==llantavehiculo.getIdtipollanta()){
                                  
                            %>
                              
                                 <th><%out.print(llantavehiculo.getDescripcion());%></th><td><%out.print(tipoaux.getNombretipollanta());%></td>
                            
                            <%
                                          }
                                        }
                                    }
                                 }while(iterador_llantavehiculo.hasNext());
                            }
                            %>
                        </tr>
                        <tr>
                            <th>Observaciones</th><td colspan="3"><%out.print(activotransporte.getObservaciones()); %></td>
                        </tr>
                        <tr>
                              <th>Observaciones Técnicas</th><td  colspan="3"><%out.print(activotransporte.getObservacionesTecnicas()); %></td>
                        </tr>                        
                    </table>
                    <fieldset>
                        <legend>Imagenes del Activo <strong><%out.print(activotransporte.getPlacaActivo());%></strong></legend>
                        <div class="sm_imgcontainer" id="sm_imgcontainer">
                            <%
                                if (iterador_imagenes != null && iterador_imagenes.hasNext()) {
                                    while (iterador_imagenes.hasNext()) {
                                        ImagenActivo imgaux = iterador_imagenes.next();
                            %> 
                            <div class="sm_imgcontainer_itemcontainer">
                                <img class='sm_imgcontainer_item' width='300' height='300'  src='<%out.print(imgaux.getUrldocumento());%>' title='Vista Previa imagen del activo <%out.print(imgaux.getCodigoActivo());%>'/>
                            </div>
                            <%
                                    System.out.println("pase esta parteeeeadfqwerqw otraves final");
                                }
                            } else {
                            %>
                            <center><strong>No tiene imagenes registradas.</strong></center>
                                <%
                                    }
                                %>
                        </div>
                    </fieldset>

                    <p class="sm_container_reporttext">
                        Este reporte fue generado por el sistema de información SIMUNI, sirve de comprobante para el respaldo de procesos realizados.
                    </p>

                    <p>Cualquier consulta la puede realizar al correo,<code>javiercoulon@gmail.com</code></p>
                    <p><small><sub>* Datos de representante legal</sub></small></p>
                </div>
            </fieldset> 
            <div class="sm_container_reportopciones hidden-print">
                <fieldset>
                    <legend>Opciones del usuario</legend>
                    <div class="sm_container_reportopcionesitem">
                        <button class="btn btn-primary" id="sm_btmopcionitem_print">Imprimir Reporte</button>
                    </div>
                    <div class="sm_container_reportopcionesitem">
                        <button class="btn btn-primary" id="sm_btmopcionitem_pdfreport">Obtener Reporte en PDF</button>
                    </div>
                    <div class="sm_container_reportopcionesitem">
                        <button class="btn btn-primary" id="sm_btmopcionitem_editar">Editar este Registro</button>
                    </div>    
                    <div class="sm_container_reportopcionesitem">
                        <button class="btn btn-primary" id="sm_btmopcionitem_actualhome">Ir a Activos</button>
                    </div>
                </fieldset>
            </div>
        </div>
</decorator:content>
</decorator:decorate>
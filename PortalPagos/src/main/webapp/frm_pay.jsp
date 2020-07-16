<!-- Add Header -->
<%@page session="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="es_CO"/>
<jsp:include page="/WEB-INF/pages/common/header.jsp" />

<section id="clientes">
    <div class="col-md-3 mt-4 text-white bg-info text-center" style="border-top-right-radius: 20px;">
        <h5>
            <i class="fas fa-credit-card"></i>
            &nbsp;<b>PAGOS PENDIENTES</b>
        </h5>
    </div>
    <div class="container">
        <div class="row mt-4">
            <div class="col-md-12">
                <div class="table-responsive-sm">
                    <table class="table table-striped table-hover table-sm text-center" style="background: rgba(212, 231, 236, 0.85); border-radius: 10px;">
                        <thead class="text-info">
                            <tr>
                                <th># Recibo</th>
                                <th>N° Identificación</th>
                                <th style="width: 20%;">Nombre</th>
                                <!--<th>Email</th>-->
                                <th>Fecha Expedición</th>
                                <th style="width: 10%;">Fecha Limite</th>
                                <th>Unidad</th>
                                <th>Valor</th>
                                <th>Estado</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>   
                            <jsp:useBean id="date" class="java.util.Date" />
                            <fmt:formatDate var="dateNow" value="${date}" pattern="yyyy-MM-dd" />
                            
                            <!-- Each element of list pay_pendings INSCRIPTION -->
                            <c:if test="${pay_pendings_insc[0] != null}">
                                <c:forEach var="pay_pending_insc" items="${pay_pendings_insc}">
                                    <tr>
                                        <td>${pay_pending_insc.get("num_formulario").getAsInt()}</td>
                                        <td>${pay_pending_insc.get("num_identificacion").getAsString()}</td>
                                        <td>${pay_pending_insc.get("nombre_largo").getAsString()}</td>
                                        <%--<td>${pay_pending_insc.get("email").getAsString()}</td>--%>                                        
                                        <td>${pay_pending_insc.get("fecha_venta").getAsString()}</td>
                                        <c:set var="date_limit" value="${pay_pending_insc.get('fecha_pago_inscripcion').getAsString()}"/>
                                        <td>${date_limit}</td>
                                        <td>${pay_pending_insc.get("nombre_unidad").getAsString()}</td>
                                        <td>
                                            <fmt:formatNumber value="${pay_pending_insc.get('valor_inscripcion').getAsString()}" type="currency"/>
                                        </td>                                   
                                        <td><b>${pay_pending_insc.get("estado").getAsString()}</b></td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${dateNow le date_limit}">
                                                    <button id="btn_init_pay"
                                                            class="btn-sm btn-warning">
                                                        <i class="fas fa-angle-double-right"></i> 
                                                    </button>
                                                </c:when>
                                                <c:when test="${dateNow gt date_limit}">
                                                    <button type="button" id="btn_fail_pay"
                                                            class="btn-sm btn-danger" disabled>
                                                        <i class="fas fa-times"></i> 
                                                    </button>
                                                </c:when>
                                            </c:choose>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            <!-- End Each element of list pay_pendings INSCRIPTION -->
                            
                            <!-- Each element of list pay_pendings PRE-POSTGRADE -->
                            <c:if test="${pay_pendings_mat[0] != null}">                            
                                <c:forEach var="pay_pending_mat" items="${pay_pendings_mat}">
                                    <tr>
                                        <td>${pay_pending_mat.get("num_recibo").toString()}</td>
                                        <td>${pay_pending_mat.get("num_identificacion").getAsString()}</td>
                                        <td>${pay_pending_mat.get("nombre_largo").getAsString()}</td>
                                        <%--<td>${pay_pending_mat.get("email").getAsString()}</td>--%>
                                        <td>${pay_pending_mat.get('fecha_liquidacion').getAsString()}</td>
                                        <c:set var="date_limit" value="${pay_pending_mat.get('fecha_pago_matricula').getAsString()}"/>
                                        <td>${date_limit}</td>
                                        <td>${pay_pending_mat.get("nombre_unidad").getAsString()}</td>
                                        <td>
                                            <fmt:formatNumber value="${pay_pending_mat.get('valor_matricula').getAsString()}" type="currency"/>
                                        </td>
                                        <td><b>${pay_pending_mat.get("estado").getAsString()}</b></td>
                                        <td> 
                                            <c:choose>
                                                <c:when test="${dateNow le date_limit}">
                                                    <button id="btn_init_pay"
                                                            class="btn-sm btn-warning">
                                                        <i class="fas fa-angle-double-right"></i> 
                                                    </button>
                                                </c:when>
                                                <c:when test="${dateNow gt date_limit}">
                                                    <button type="button" id="btn_fail_pay"
                                                            class="btn-sm btn-danger" disabled>
                                                        <i class="fas fa-times"></i> 
                                                    </button>
                                                </c:when>
                                            </c:choose>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            <!-- End Each element of list pay_pendings PRE-POSTGRADE -->
                        </tbody>
                    </table>
                </div>
            </div>            
        </div>
    </div>
</section>

<!-- Add Footer -->
<jsp:include page="/WEB-INF/pages/common/footer.jsp" />

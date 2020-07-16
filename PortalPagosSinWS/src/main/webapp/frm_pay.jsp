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
            &nbsp;<b>PAGOS POR HACER</b>
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
                                <th>Nombre</th>
                                <th>Email</th>
                                <th>Fecha Inscripción</th>
                                <th>Fecha Limite Pago</th>
                                <th>Unidad</th>
                                <th>Valor</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>                                                        
                            <!-- Each element of list pay_pendings -->
                            <c:forEach var="pay_pending" items="${pay_pendings}">
                                <tr>
                                    <td>${pay_pending.num_formulario}</td>
                                    <td>${pay_pending.num_identificacion}</td>
                                    <td>${pay_pending.nombre_largo}</td>
                                    <td>${pay_pending.email}</td>

                                    <fmt:formatDate var="fecha_venta" value="${pay_pending.fecha_venta}" type="date" pattern="yyyy-MM-dd" />
                                    <td>${fecha_venta}</td>
                                    
                                    <fmt:formatDate var="fecha_hoy" value="${now}" type="date" pattern="yyyy-MM-dd" />
                                    <td><fmt:formatDate value="${pay_pending.fecha_pago_inscripcion}" type="date" pattern="yyyy-MM-dd" /></td>

                                    <td>${pay_pending.nombre_unidad}</td>
                                    <td><fmt:formatNumber value="${pay_pending.valor_inscripcion}" type="currency"/></td>                                   
                                    <td>
                                        <c:choose>
                                            <c:when test="${fecha_hoy le fecha_pago_inscripcion}">
                                                <button id="btn_init_pay"
                                                        class="btn-sm btn-warning">
                                                    <i class="fas fa-angle-double-right"></i> 
                                                </button>
                                            </c:when>
                                            <c:when test="${fecha_hoy gt fecha_pago_inscripcion}">
                                                <button type="button" id="btn_fail_pay"
                                                        class="btn-sm btn-danger" disabled>
                                                    <i class="fas fa-times"></i> 
                                                </button>
                                            </c:when>
                                        </c:choose>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>            
        </div>
    </div>
</section>

<!-- Add Footer -->
<jsp:include page="/WEB-INF/pages/common/footer.jsp" />

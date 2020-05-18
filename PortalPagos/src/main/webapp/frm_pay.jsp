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
                                    <td>${pay_pending.fecha_venta}</td>
                                    <td>${pay_pending.nombre_unidad}</td>
                                    <td><fmt:formatNumber value="${pay_pending.valor_inscripcion}" type="currency"/></td>
                                    <td>
                                        <a href="#"
                                           class="btn-sm btn-danger">
                                            <i class="fas fa-angle-double-right"></i> 
                                        </a>                                        
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

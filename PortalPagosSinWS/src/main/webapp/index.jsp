<!-- Add Header -->
<jsp:include page="/WEB-INF/pages/common/header_init.jsp" />

<!--Modal Init-->
<div class="modal hide fade" id="modal_init">
    <div class="modal-dialog modal-dialog-centered modal-lg">
        <div class="modal-content bg-dark text-white text-center">
            <div class="modal-body">
                <div class="p-4 m-5">
                    <h3><b>Bienvenido al Portal de Pagos de la
                            Fundación Universitaria Sanitas</b>
                    </h3>
                </div>
                <div class="p-4 m-5">
                    <h3><b>Tenga en cuenta que Usted es responsable
                            de los datos aquí suministrados</b>
                    </h3>
                </div>
                <div class="pb-3 pt-5">
                    <button class="btn btn-primary btn-info" data-dismiss="modal">
                        <b><h5>Continuar</h5></b>
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>

<!--Section-->
<section class="pb-5 pt-5">
    <div class="container">
        <div class="row d-flex justify-content-center align-items-center pb-2 pt-4">
            <div class="col-md-4">
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text"><i class="fas fa-user"></i></span>
                    </div>
                    <input type="text" class="form-control border border-info" id="txt_num_identity" 
                           name="txt_num_identity" required autofocus placeholder="Número de Identificación">                            
                </div>
            </div>
            <div class="mb-3 div_btn_check">
                <button id="btn_check" name="btn_check" class="btn btn-primary btn-block border border-info">
                    <i class="fas fa-check"></i>
                </button>
            </div>
        </div>                
        <div class="row text-center d-flex justify-content-center align-items-center">
            <div class="card text-center bg-light p-3 div_data_person"> 
                <form action="${pageContext.request.contextPath}/ServletController?action=accept" method="POST">
                    <b><label id="lbl_name_person"></label>
                        <label id="lbl_id_person"></label></b>
                    <div class="d-flex justify-content-center align-items-center">
                        <div class="mr-3">
                            <button type="submit" name="btn_accept" id="btn_accept" class="btn btn-success btn-block border border-info">
                                <i class="fas fa-arrow-right"></i>
                            </button>
                        </div>
                        <div>
                            <button type="button" id="btn_cancel" name="btn_cancel" class="btn btn-danger btn-block border border-info">
                                <i class="fas fa-times"></i>
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>               
    </div>
</section>

<!-- Add Footer -->
<jsp:include page="/WEB-INF/pages/common/footer.jsp" />

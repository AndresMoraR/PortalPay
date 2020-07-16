$(function () {
    $('#modal_init').modal('show');
    $('.div_data_person').hide();
    $('#btn_check').on('click', function () {
        $.post('ServletController', {
            num_identity: $('#txt_num_identity').val(),
            action: 'check'
        }, function (rs) {
            $('#txt_num_identity').val('');            
            $('.div_btn_check').hide();
            if (rs.success) {
                $('#lbl_name_person').text(rs.person_data.nom_largo);
                $('#lbl_id_person').text(rs.person_data.num_identificacion); 
                $('#btn_accept').show();
            } else {                 
                $('#btn_accept').hide();
                $('#lbl_name_person').text("No se encuentra informaci\u00F3n asociada");
            }
            $('.div_data_person').show();
        });
    });

    $('#btn_cancel').on('click', function () {
        $('#lbl_name_person, #lbl_id_person').text('');
        $('.div_data_person').hide();
        $('.div_btn_check').show();
        $('#txt_num_identity').val('');
        $.post('ServletController');
    });
});
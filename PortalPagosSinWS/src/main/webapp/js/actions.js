$(function () {
    $('#btn_init_pay').on('click', function () {
        $.ajax({
            type: "POST",
            url: "http://papi.colsanitas.com/token",
            headers: {
                'Authorization': 'Basic S2VQRFJ3ejFaNmw3Um1JR3NkWThxTndCRmJnYTp6VGZmTEREYnFuaE9IdkhmSVJNbGZyUUYwYlFh'
            },
            contentType: 'application/x-www-form-urlencoded;charset=UTF-8',
            dataType: 'json',
            data: {
                grant_type: 'password',
                username: 'usermp',
                password: 'MpSanitas2016'
            }
        }).done(function (rsp) {
            var settings = {
                "url": "https://papi.colsanitas.com/osi/api/recaudo/v1.0.0/concepto/recaudo",
                "method": "POST",
                "timeout": 0,
                "headers": {
                    "Authorization": "Bearer " + rsp.access_token,
                    "Content-Type": "application/json"
                },
                "data": JSON.stringify({
                    "requestCabecera": {
                        "canalIngreso": "1",
                        "codPlan": "10",
                        "codProducto": "10",
                        "idConcepto": "6",
                        "numContrato": "10071003",
                        "numFamilia": "3"
                    },
                    "requestRecaudoConcepto": {
                        "cantidad": "1",
                        "ciudad": "11001",
                        "ivaTotal": 31200,
                        "ivaValor": 1486,
                        "numUsuario": "1",
                        "origenPago": "1",
                        "pagador": {
                            "apellidos": "SOLER GARAVITO",
                            "direccionIp": "123.123.123.123",
                            "email": "ofcadena@keralty.com",
                            "nombres": "MARIA CRISTINA",
                            "numDoc": "41552712",
                            "telefono": "2111111",
                            "tipoDoc": "CC"
                        }, "tipoMetodoPago": "2"
                    }
                })
            };
            $.ajax(settings).done(function (response) {
                var val = response.valoresAdicionales[0].valor;
                window.open(val, '_blank');
            });

        });
    });
});


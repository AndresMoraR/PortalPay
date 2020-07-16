<%@page session="true" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap-4.4.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style_init.css">
        <script src="https://kit.fontawesome.com/6a9587acee.js" crossorigin="anonymous"></script>
        <script type="text/javascript">
            var base = "${pageContext.request.contextPath}";
                    /*setTimeout(function(){                         
                        window.location = "${pageContext.request.contextPath}/ServletController?action=close"; 
                    }, 30000);*/
        </script>
        <script src="${pageContext.request.contextPath}/js/jquery-3.5.1.min.js"></script>        
        <script src="${pageContext.request.contextPath}/js/popper.min.js"></script>        
        <script src="${pageContext.request.contextPath}/bootstrap-4.4.1/js/bootstrap.min.js"></script>        
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/actions.js"></script>
        <title>Portal de Pagos</title>
    </head>
    <body>
        <!--Header-->
        <header id="main-header" class="py-4 text-white">
            <div class="container">
                <div class="row">
                    <div class="col-md-6">                                      
                        <img src="${pageContext.request.contextPath}/css/img/unisanitas_texto_blanco.png" alt="" style="max-width: 100%; width: 400px;">                                                
                    </div>
                    <div class="col-md-6 text-right align-self-center">
                        <div>    
                            <i class="fas fa-user"></i> 
                            <h5><b><i><% out.println(session.getAttribute("name").toString()); %></i></b></h5>                           
                        </div>
                        <div>                            
                            <h5><b><i><% out.println(session.getAttribute("type").toString()); %> <% out.println(session.getAttribute("id").toString()); %></i></b></h5>                        
                        </div>
                    </div>
                </div>
            </div>
        </header>
    </body>
</html>

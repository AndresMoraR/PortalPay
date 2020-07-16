package Controller;

import Data.TblPagosDao;
import Model.TblPagos;
import Data.Config;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

/**
 * Functions to use in Portal Pay
 */
@WebServlet("/ServletController")
public class ServletController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "close":
                    this.accionDefault(request, response);
                    break;
                case "accept":
                    HttpSession sesion = request.getSession();
                    if (sesion.getAttribute("id") == null) {
                        this.accionDefault(request, response);
                    } else {
                        this.findPayByUser(request, response);
                    }
                    break;
                default:
                    this.accionDefault(request, response);
            }
        } else {
            this.accionDefault(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "check":
                    this.findOneByUser(request, response);
                    break;
                case "accept":
                    HttpSession sesion = request.getSession();
                    if (sesion.getAttribute("id") == null) {
                        this.accionDefault(request, response);
                    } else {
                        this.findPayByUser(request, response);
                    }
                    break;
                default:
                    this.accionDefault(request, response);
            }
        } else {
            this.accionDefault(request, response);
        }
    }

    private void findPayByUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        Config c = new Config();
        System.out.println(sesion.getAttribute("id"));
        if (sesion.getAttribute("id") != null) {
            Client cli = ClientBuilder.newClient();

            JsonObject[] rs_query_pay_insc = new Gson().fromJson(cli.target("http://localhost:5000/wsPortalPagos/osi/api/portalpagos/aspirante/v1.0.0/inscripcion/{idpersona}/{codperiodo}")
                    .resolveTemplate("idpersona", sesion.getAttribute("id").toString())
                    .resolveTemplate("codperiodo", c.getCodPeriod())
                    .request("application/json")
                    .header("Content-Type", "application/json")
                    .get(String.class), JsonObject[].class);

            JsonObject[] rs_query_pay_mat = new Gson().fromJson(cli.target("http://localhost:5000/wsPortalPagos/osi/api/portalpagos/matriculado/v1.0.0/matricula/{idpersona}/{codperiodo}")
                    .resolveTemplate("idpersona", sesion.getAttribute("id").toString())
                    .resolveTemplate("codperiodo", c.getCodPeriod())
                    .request("application/json")
                    .header("Content-Type", "application/json")
                    .get(String.class), JsonObject[].class);

            for (int i = 0; i < rs_query_pay_insc.length; i++) {
                TblPagos tblPagos_cns = new TblPagosDao().findOneBy(new TblPagos(rs_query_pay_insc[i].get("num_formulario").getAsInt()));                
                if (tblPagos_cns.getNumIdentificacion() == null) {
                    TblPagos tblPagos_ins = new TblPagos(
                            rs_query_pay_insc[i].get("num_identificacion").getAsString(),
                            rs_query_pay_insc[i].get("num_formulario").getAsInt(),
                            rs_query_pay_insc[i].get("valor_inscripcion").getAsInt(),
                            "Pendiente"
                    );
                    new TblPagosDao().insert(tblPagos_ins);
                    rs_query_pay_insc[i].addProperty("estado", "Pendiente");
                }else{
                    rs_query_pay_insc[i].addProperty("estado", tblPagos_cns.getEstadoTransaccion());
                }
                System.out.println("registroModificados = " + rs_query_pay_insc[i]);
            }
            
            for (int i = 0; i < rs_query_pay_mat.length; i++) {
                TblPagos tblPagos_cns = new TblPagosDao().findOneBy(new TblPagos(rs_query_pay_mat[i].get("num_recibo").getAsInt()));                
                if (tblPagos_cns.getNumIdentificacion() == null) {
                    TblPagos tblPagos_mat = new TblPagos(
                            rs_query_pay_mat[i].get("num_identificacion").getAsString(),
                            rs_query_pay_mat[i].get("num_recibo").getAsInt(),
                            rs_query_pay_mat[i].get("valor_matricula").getAsInt(),
                            "Pendiente"
                    );
                    new TblPagosDao().insert(tblPagos_mat);
                    rs_query_pay_mat[i].addProperty("estado", "Pendiente");
                }else{                                    
                    rs_query_pay_mat[i].addProperty("estado", tblPagos_cns.getEstadoTransaccion());
                }
                System.out.println("registroModificados = " + rs_query_pay_mat[i]);
            }

            //Crear el objeto a actualizar (modelo)
            //TblPagos tblPagos_2 = new TblPagos(132456,123456,"Pendiente");
            //Modificar en base de datos el objeto.
            //int registroModificados_2 = new TblPagosDao().update(tblPagos_2);
             
            request.setAttribute("pay_pendings_insc", rs_query_pay_insc);
            request.setAttribute("pay_pendings_mat", rs_query_pay_mat);
            request.getRequestDispatcher("frm_pay.jsp").forward(request, response);
        } else {
            this.accionDefault(request, response);
        }
    }

    private void findOneByUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String num_identificacion = request.getParameter("num_identity");

        Client cli = ClientBuilder.newClient();
        JsonObject person_data = new Gson().fromJson(cli.target("http://localhost:5000/wsPortalPagos/osi/api/portalpagos/persona/v1.0.0/persona/{idpersona}")
                .resolveTemplate("idpersona", num_identificacion)
                .request("application/json")
                .header("Content-Type", "application/json")
                .get(String.class), JsonObject.class);

        System.out.println("target =" + person_data.get("id_tercero"));
        System.out.println("target2 =" + person_data);

        //creamos o recuperamos el objeto http session
        HttpSession sesion = request.getSession();
        sesion.setAttribute("id", num_identificacion);
        sesion.setAttribute("name", person_data.get("nom_largo").getAsString());
        sesion.setAttribute("type", person_data.get("tip_identificacion").getAsString());

        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        JsonObject myObj = new JsonObject();
        response.setContentType("application/json");

        JsonElement person_data_obj = gson.toJsonTree(person_data);
        if (person_data.get("nom_largo") == null) {
            myObj.addProperty("success", false);
        } else {
            myObj.addProperty("success", true);
        }
        myObj.add("person_data", person_data_obj);

        try {
            out.println(myObj.toString());
        } finally {
            out.close();
        }
    }

    private void accionDefault(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        sesion.invalidate();
        System.out.println(sesion);
        response.sendRedirect("index.jsp");
    }
}

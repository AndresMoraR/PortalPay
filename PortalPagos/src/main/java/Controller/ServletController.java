/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Datos.PersonDataDAO;
import Modelo.Tbl_query_pay;

import Datos.QueryPayDAO;
import Modelo.Tbl_person_data;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 *
 * @author Andres Mora
 */
@WebServlet("/ServletController")
public class ServletController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
        //String num_identificacion = request.getParameter("idPerson");
        //List<Tbl_query_pay> rs_query_pay = new QueryPayDAO().get_pay(new Tbl_query_pay(num_identificacion));
        //System.out.println("pay_pendings =" + rs_query_pay);
        //request.setAttribute("pay_pendings", rs_query_pay);
        //request.getRequestDispatcher("frm_pay.jsp").forward(request, response);
                
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "close":
                    this.accionDefault(request, response);
                    break;
                case "accept":
                    HttpSession sesion = request.getSession();
                    if(sesion.getAttribute("id") == null){
                        this.accionDefault(request, response);
                    }else{
                        this.findPayByUser(request, response);
                    }
                    break;
                default:
                    this.accionDefault(request, response);
            }
        }else{
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
                    if(sesion.getAttribute("id") == null){
                        this.accionDefault(request, response);
                    }else{
                        this.findPayByUser(request, response);
                    }
                    break;
                default:
                    this.accionDefault(request, response);
            }
        }else{
            this.accionDefault(request, response);
        }
    }

    private void findPayByUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        System.out.println(sesion.getAttribute("id"));
        if(sesion.getAttribute("id") != null){       
            List<Tbl_query_pay> rs_query_pay = new QueryPayDAO().get_pay(new Tbl_query_pay(sesion.getAttribute("id").toString()));
            System.out.println("pay_pendings =" + rs_query_pay);
            request.setAttribute("pay_pendings", rs_query_pay); 
            request.getRequestDispatcher("frm_pay.jsp").forward(request, response);    
        }else{
            this.accionDefault(request, response);
        }
    }
  
    private void findOneByUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String num_identificacion = request.getParameter("num_identity");
        Tbl_person_data person_data = new PersonDataDAO().findOnePerson(new Tbl_person_data(num_identificacion));
        
        //creamos o recuperamos el objeto http session
        HttpSession sesion = request.getSession();           
        sesion.setAttribute("id", num_identificacion);
        sesion.setAttribute("name", person_data.getNom_largo());
        sesion.setAttribute("type", person_data.getTip_identificacion());
        
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        JsonObject myObj = new JsonObject();
        response.setContentType("application/json");

        JsonElement person_data_obj = gson.toJsonTree(person_data);
        if (person_data.getNom_largo() == null) {
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
        /*sesion.setAttribute("id", "");
        sesion.setAttribute("name", "");
        sesion.setAttribute("type", "");*/
        sesion.invalidate();
        System.out.println(sesion);
        response.sendRedirect("index.jsp");
    }
}

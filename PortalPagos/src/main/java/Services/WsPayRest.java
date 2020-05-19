package Services;

import Data.PersonDataDAO;
import Data.QueryPayDAO;
import Model.Tbl_person_data;
import Model.Tbl_query_pay;
import com.google.gson.Gson;
import java.util.List;
import javax.ejb.Stateless;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("aspirantes/v1.0.0")
@Stateless
public class WsPayRest {
    
    @GET
    @Path("persona/{idpersona}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPerson(@PathParam("idpersona") String idpersona) {      
        Tbl_person_data person_data = new PersonDataDAO().findOnePerson(new Tbl_person_data(idpersona));
        String json = new Gson().toJson(person_data);
        return Response.ok(json, MediaType.APPLICATION_JSON).build();
    }
    
    @GET
    @Path("pago/{idpersona}/{periodo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPays(@PathParam("idpersona") String idpersona, @PathParam("periodo") String periodo) {
        List<Tbl_query_pay> rs_query_pay = new QueryPayDAO().get_pay(new Tbl_query_pay(idpersona), periodo);
            
        String json = new Gson().toJson(rs_query_pay);
        return Response.ok(json, MediaType.APPLICATION_JSON).build();
    }
}

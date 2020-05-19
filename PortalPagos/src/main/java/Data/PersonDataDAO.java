package Data;

import Model.Tbl_person_data;
import java.sql.*;

/**
 *
 * @author Andres Mora
 */
public class PersonDataDAO {
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;
    
    private static final String SQL_SELECT_ONE = "SELECT bt.ID_TERCERO, bt.NUM_IDENTIFICACION, bt.NOM_LARGO, bt.TIP_IDENTIFICACION "
                                           + " FROM usinu.BAS_TERCERO bt "
                                           + " WHERE bt.NUM_IDENTIFICACION = ?";
    
    /** Return data of person
     * @param person_data
     * @return  **/
    public Tbl_person_data findOnePerson(Tbl_person_data person_data) {
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_ONE);
            stmt.setString(1, person_data.getNum_identificacion());
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ID_TERCERO");
                String NUM_IDENTIFICACION = rs.getString("NUM_IDENTIFICACION");
                String name_large = rs.getString("NOM_LARGO");
                String tip_identificacion = rs.getString("TIP_IDENTIFICACION");

                person_data.setId_tercero(id);
                person_data.setNum_identificacion(NUM_IDENTIFICACION);
                person_data.setNom_largo(name_large);
                person_data.setTip_identificacion(tip_identificacion);
            }            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }        
        return person_data;        
    }
}

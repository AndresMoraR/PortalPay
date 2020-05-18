/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Modelo.Tbl_query_pay;
import java.sql.*;
import java.util.*;

/**
 *
 * @author Andres Mora
 */
public class QueryPayDAO {
    
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;
        
    private static final String SQL_SELECT = "select v.num_formulario AS num_recibo, "+
                                            "v.num_identificacion, b.nom_largo, b.dir_email_per, "+
                                            "v.fec_venta AS dia_inscripcion, v.nom_unidad, svi.val_inscripcion "+
                                            "FROM USINU.SRC_VIS_VENTA_FORMULARIO v, usinu.bas_tercero b, "+
                                            "usinu.SRC_VAL_INSCRIPCION svi,"+
                                            "usinu.SRC_FORMULARIO f "+  
                                            "WHERE v.NUM_IDENTIFICACION =b.NUM_IDENTIFICACION " +
                                            "AND svi.COD_PERIODO =v.COD_PERIODO " +
                                            "AND svi.COD_UNIDAD =v.COD_UNIDAD " +
                                            "AND f.NUM_IDENTIFICACION =v.NUM_IDENTIFICACION " +
                                            "AND svi.TIP_INSCRIPCION =f.TIP_INSCRIPCION " +
                                            "AND v.COD_PERIODO ='20202' " +
                                            "AND svi.COD_PERIODO ='20202' " +
                                            "AND f.COD_PERIODO ='20202' " +
                                            "AND v.EST_FORMULARIO ='2' " +
                                            "AND svi.TIP_INSCRIPCION NOT IN ('2','4','7') " +
                                            "AND svi.VAL_INSCRIPCION  <> '0' " +
                                            "AND (v.FEC_VENTA + 4) <= svi.FEC_PAG_INSCRIPCION " +
                                            "AND v.NUM_IDENTIFICACION = ?";
    
    /** Return data of pay pending
     * @param query_pay
     * @return  **/
    public List<Tbl_query_pay> get_pay(Tbl_query_pay query_pay) {
        Tbl_query_pay tbl_qpay;
        List<Tbl_query_pay> list_tbl_qpay = new ArrayList<>();
        
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            stmt.setString(1, query_pay.getNum_identificacion());
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                tbl_qpay = new Tbl_query_pay();
                tbl_qpay.setNum_formulario(rs.getInt("num_recibo"));  
                tbl_qpay.setNum_identificacion(rs.getString("num_identificacion"));  
                tbl_qpay.setNombre_largo(rs.getString("nom_largo"));  
                tbl_qpay.setEmail(rs.getString("dir_email_per")); 
                tbl_qpay.setFecha_venta(rs.getString("dia_inscripcion"));
                tbl_qpay.setNombre_unidad(rs.getString("nom_unidad"));
                tbl_qpay.setValor_inscripcion(rs.getString("val_inscripcion"));
                list_tbl_qpay.add(tbl_qpay);
            }
        
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return list_tbl_qpay;
    }
}

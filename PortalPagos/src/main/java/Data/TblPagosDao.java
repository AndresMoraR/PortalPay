package Data;

import Model.TblPagos;
import java.sql.*;
import java.util.*;

/**
 *
 * @author estfa
 */
public class TblPagosDao {

    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    private static final String SQL_SELECT_BY_ID = "SELECT num_identificacion,id_recibo,estado_transaccion"
            + " FROM tbl_pagos WHERE id_recibo = ?";

    private static final String SQL_INSERT = "INSERT INTO tbl_pagos(num_identificacion,id_recibo,valor_pagado,estado_transaccion) "
            + " VALUES(?, ?, ?, ?)";

    private static final String SQL_UPDATE = "UPDATE tbl_pagos SET "
            + " id_transaccion=?, estado_transaccion=? WHERE id_recibo=?";

    public int insert(TblPagos tblPagos) {
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, tblPagos.getNumIdentificacion());
            stmt.setInt(2, tblPagos.getIdRecibo());
            stmt.setInt(3, tblPagos.getValorPagado());
            stmt.setString(4, tblPagos.getEstadoTransaccion());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int update(TblPagos tblPagos) {
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setInt(1, tblPagos.getIdTransaccion());
            stmt.setString(2, tblPagos.getEstadoTransaccion());
            stmt.setInt(3, tblPagos.getIdRecibo());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public TblPagos findOneBy(TblPagos tblPagos) {
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, tblPagos.getIdRecibo());
            rs = stmt.executeQuery();
            if (rs.next()) {
                rs.absolute(1); //POSICIONARSE EN EL PRIMER REGISTRO                  
                tblPagos.setNumIdentificacion(rs.getString("num_identificacion"));
                tblPagos.setIdRecibo(rs.getInt("id_recibo"));
                tblPagos.setEstadoTransaccion(rs.getString("estado_transaccion"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }        
        return tblPagos;
    }
}

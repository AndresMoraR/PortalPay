package Model;

/**
 *
 * @author estfa
 */
public class TblPagos {
    private int idPago;
    private String numIdentificacion;
    private int idRecibo;
    private int valorPagado;
    private int idTransaccion;
    private String estadoTransaccion;

    public TblPagos() {
    }

    public TblPagos(String numIdentificacion, int idRecibo, int valorPagado, String estadoTransaccion) {
        this.numIdentificacion = numIdentificacion;
        this.idRecibo = idRecibo;
        this.valorPagado = valorPagado;
        this.estadoTransaccion = estadoTransaccion;
    }
    
    public TblPagos(int idRecibo, int idTransaccion, String estadoTransaccion) {
        this.idRecibo = idRecibo;
        this.idTransaccion = idTransaccion;
        this.estadoTransaccion = estadoTransaccion;
    }

    public TblPagos(int idRecibo) {
        this.idRecibo = idRecibo;
    }

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public String getNumIdentificacion() {
        return numIdentificacion;
    }

    public void setNumIdentificacion(String numIdentificacion) {
        this.numIdentificacion = numIdentificacion;
    }

    public int getIdRecibo() {
        return idRecibo;
    }

    public void setIdRecibo(int idRecibo) {
        this.idRecibo = idRecibo;
    }

    public int getValorPagado() {
        return valorPagado;
    }

    public void setValorPagado(int valorPagado) {
        this.valorPagado = valorPagado;
    }

    public int getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(int idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public String getEstadoTransaccion() {
        return estadoTransaccion;
    }

    public void setEstadoTransaccion(String estadoTransaccion) {
        this.estadoTransaccion = estadoTransaccion;
    }

    @Override
    public String toString() {
        return "TblPagos{" + "idPago=" + idPago + ", numIdentificacion=" + numIdentificacion + ", idRecibo=" + idRecibo + ", valorPagado=" + valorPagado + ", idTransaccion=" + idTransaccion + ", estadoTransaccion=" + estadoTransaccion + '}';
    }       
}

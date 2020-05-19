package Model;

/**
 *
 * @author Andres Mora
 */
public class Tbl_query_pay {
    private int num_formulario;
    private String num_identificacion;
    private String nombre_largo;
    private String email;
    private String fecha_venta;
    private String nombre_unidad;
    private String valor_inscripcion;
    private String fecha_pago_inscripcion;
    
    public Tbl_query_pay() {
    }
    
    public Tbl_query_pay(String num_identificacion) {
        this.num_identificacion = num_identificacion;
    }

    public Tbl_query_pay(int num_formulario, String num_identificacion, String nombre_largo, String email, String fecha_venta, String nombre_unidad, String valor_inscripcion, String fecha_pago_inscripcion) {
        this.num_formulario = num_formulario;
        this.num_identificacion = num_identificacion;
        this.nombre_largo = nombre_largo;
        this.email = email;
        this.fecha_venta = fecha_venta;
        this.nombre_unidad = nombre_unidad;
        this.valor_inscripcion = valor_inscripcion;
        this.fecha_pago_inscripcion = fecha_pago_inscripcion;
    }

    public int getNum_formulario() {
        return num_formulario;
    }

    public void setNum_formulario(int num_formulario) {
        this.num_formulario = num_formulario;
    }

    public String getNum_identificacion() {
        return num_identificacion;
    }

    public void setNum_identificacion(String num_identificacion) {
        this.num_identificacion = num_identificacion;
    }

    public String getNombre_largo() {
        return nombre_largo;
    }

    public void setNombre_largo(String nombre_largo) {
        this.nombre_largo = nombre_largo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFecha_venta() {
        return fecha_venta;
    }

    public void setFecha_venta(String fecha_venta) {
        this.fecha_venta = fecha_venta;
    }

    public String getNombre_unidad() {
        return nombre_unidad;
    }

    public void setNombre_unidad(String nombre_unidad) {
        this.nombre_unidad = nombre_unidad;
    }

    public String getValor_inscripcion() {
        return valor_inscripcion;
    }

    public void setValor_inscripcion(String valor_inscripcion) {
        this.valor_inscripcion = valor_inscripcion;
    }

    public String getFecha_pago_inscripcion() {
        return fecha_pago_inscripcion;
    }

    public void setFecha_pago_inscripcion(String fecha_pago_inscripcion) {
        this.fecha_pago_inscripcion = fecha_pago_inscripcion;
    }    
}

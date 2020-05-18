package Modelo;

/**
 *
 * @author Andres Mora
 */
public class Tbl_person_data {
    private int id_tercero;
    private String num_identificacion;
    private String nom_largo;
    private String tip_identificacion;

    public Tbl_person_data() {
    }

    public Tbl_person_data(int id_tercero, String num_identificacion, String nom_largo, String tip_identificacion) {
        this.id_tercero = id_tercero;
        this.num_identificacion = num_identificacion;
        this.nom_largo = nom_largo;
        this.tip_identificacion = tip_identificacion;
    }

    public Tbl_person_data(String num_identificacion) {
        this.num_identificacion = num_identificacion;
    }

    public int getId_tercero() {
        return id_tercero;
    }

    public void setId_tercero(int id_tercero) {
        this.id_tercero = id_tercero;
    }

    public String getNum_identificacion() {
        return num_identificacion;
    }

    public void setNum_identificacion(String num_identificacion) {
        this.num_identificacion = num_identificacion;
    }

    public String getNom_largo() {
        return nom_largo;
    }

    public void setNom_largo(String nom_largo) {
        this.nom_largo = nom_largo;
    }    

    public String getTip_identificacion() {
        return tip_identificacion;
    }

    public void setTip_identificacion(String tip_identificacion) {
        this.tip_identificacion = tip_identificacion;
    }
            
    @Override
    public String toString() {
        return "Tbl_person_data{" + "idTercero=" + id_tercero + ", numIdentificacion=" + num_identificacion + ", nomLargo=" + nom_largo + '}';
    }
}

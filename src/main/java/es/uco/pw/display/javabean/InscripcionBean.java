package es.uco.pw.display.javabean;


import java.io.Serializable;


public class InscripcionBean implements Serializable {
	
    private static final long serialVersionUID = 1L;
    private Integer idCampamento;
    private String estado;
    private Integer tipo;
    private float precio;



    public InscripcionBean() {

    }

    public InscripcionBean(Integer _idCampamento, String _estado, Integer _tipo, float _precio) {
    	this.idCampamento = _idCampamento;
    	this.estado = _estado;
        this.tipo = _tipo;
        this.precio = _precio;
    }
    public Integer getIdCampamento() {
        return idCampamento;
    }

    public void setIdCampamento(Integer _idCampamento) {
        this.idCampamento = _idCampamento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String _estado) {
        this.estado = _estado;
    }

    public Integer getTipo() {
        return tipo;
    }
     public void setTipo(Integer _tipo) {
        this.tipo = _tipo;
    }

    public float getPrecio() {
        return precio;
    }
     public void setPrecio(float _precio) {
        this.precio = _precio;
    }
}

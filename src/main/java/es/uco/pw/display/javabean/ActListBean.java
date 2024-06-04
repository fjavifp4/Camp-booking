package es.uco.pw.display.javabean;

import java.io.Serializable;
import java.util.ArrayList;

import es.uco.pw.business.actividad.DTOActividad;

public class ActListBean implements Serializable {
	
    private static final long serialVersionUID = 1L;
    private ArrayList<DTOActividad> actividades;

    public ActListBean() {
    	actividades= new ArrayList<DTOActividad>();
    }
    public ActListBean(ArrayList<DTOActividad> actividades) {
    	this.actividades = actividades;
    }
    public ArrayList<DTOActividad> getMiLista() {
        return actividades;
    }

    public void setMiLista(ArrayList<DTOActividad> actividades) {
        this.actividades = actividades;
    }
}
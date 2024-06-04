package es.uco.pw.display.javabean;

import java.io.Serializable;
import java.util.ArrayList;
import es.uco.pw.business.campamento.*;

public class CampListBean implements Serializable {
	
    private static final long serialVersionUID = 1L;
    private ArrayList<DTOCampamento> campamentos;

    public CampListBean() {
    	campamentos= new ArrayList<DTOCampamento>();
    }
    public CampListBean(ArrayList<DTOCampamento> campamentos) {
    	this.campamentos = campamentos;
    }
    public ArrayList<DTOCampamento> getMiLista() {
        return campamentos;
    }

    public void setMiLista(ArrayList<DTOCampamento> campamentos) {
        this.campamentos = campamentos;
    }
}
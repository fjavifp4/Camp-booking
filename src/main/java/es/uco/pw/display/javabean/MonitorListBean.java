package es.uco.pw.display.javabean;

import java.io.Serializable;
import java.util.ArrayList;
import es.uco.pw.business.monitor.*;

public class MonitorListBean implements Serializable {
	
    private static final long serialVersionUID = 1L;
    private ArrayList<DTOMonitor> monitores;

    public MonitorListBean() {
    	monitores= new ArrayList<DTOMonitor>();
    }
    public MonitorListBean(ArrayList<DTOMonitor> monitores) {
    	this.monitores = monitores;
    }
    public ArrayList<DTOMonitor> getMiLista() {
        return monitores;
    }

    public void setMiLista(ArrayList<DTOMonitor> monitores) {
        this.monitores = monitores;
    }
}
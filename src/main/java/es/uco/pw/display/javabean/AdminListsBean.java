package es.uco.pw.display.javabean;


import java.io.Serializable;
import java.util.ArrayList;
import es.uco.pw.business.campamento.*;


public class AdminListsBean implements Serializable
{
	private static final long serialVersionUID = 1L;
	private ArrayList<DTOCampamento> campList;
	private ArrayList<Integer> plazasTotales;
	private ArrayList<Integer> plazasParciales;
	
	
	
	public ArrayList<DTOCampamento> getCampList()	
	{
		return this.campList;
	}
	
	public void setCampList(ArrayList<DTOCampamento> camplist)	
	{
		this.campList = camplist;
	}
	
	
	
	public ArrayList<Integer> getPlazasTotales()	
	{
		return this.plazasTotales;
	}
	
	public void setPlazasTotales(ArrayList<Integer> plazastotales)	
	{
		 this.plazasTotales = plazastotales;
	}
	

	public ArrayList<Integer> getPlazasParciales()	
	{
		return this.plazasParciales;
	}
	
	public void setPlazasParciales(ArrayList<Integer> plazasParciales) {
		this.plazasParciales = plazasParciales;
	}
}
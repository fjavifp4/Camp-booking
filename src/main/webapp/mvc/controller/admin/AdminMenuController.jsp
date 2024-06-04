<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="java.io.*"%>
<%@page import="es.uco.pw.data.common.*"%>
<%@page import="es.uco.pw.data.dao.campamento.*"%>
<%@page import="es.uco.pw.business.campamento.*"%>
<%@page import="es.uco.pw.business.inscripcion.*"%>


<jsp:useBean id="Usuario" scope ="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>
<jsp:useBean id="Lists" scope ="request" class="es.uco.pw.display.javabean.AdminListsBean"></jsp:useBean>


<%


    Properties prop = new Properties();

	Properties sqlprop = new Properties();

    // Obtener parámetros de inicialización del archivo web.xml
	String sql = application.getInitParameter("sql");
	String conf = application.getInitParameter("config");
    
	InputStream input= application.getResourceAsStream(conf);
    prop.load(input);
    
	InputStream input2 = application.getResourceAsStream(sql);
	sqlprop.load(input2);

    GestorCampamentos gestorCampamentos = new GestorCampamentos();
    GestorInscripciones gestorInscripciones = new GestorInscripciones();
    ArrayList<DTOCampamento> campamentos = gestorCampamentos.listarCampamentos(prop, sqlprop);
    ArrayList<Integer> plazasCompleto = new ArrayList<Integer>();
    ArrayList<Integer> plazasParcial = new ArrayList<Integer>();

    for (DTOCampamento c : campamentos) {
        plazasCompleto.add(gestorInscripciones.contarPlazasOcupadasTipo("completo", c, prop, sqlprop));
    	plazasParcial.add(gestorInscripciones.contarPlazasOcupadasTipo("parcial", c, prop, sqlprop));
    }
    Lists.setCampList(campamentos);
    Lists.setPlazasTotales(plazasCompleto);
    Lists.setPlazasParciales(plazasParcial);
    
%>

<jsp:forward page="../../view/admin/AdminMenuView.jsp"></jsp:forward>
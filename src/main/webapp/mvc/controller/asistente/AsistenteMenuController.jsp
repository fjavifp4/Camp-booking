<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.io.*" %>
<%@page import="java.util.*" %>
<%@page import="java.time.LocalDate" %>
<%@page import="java.time.format.DateTimeFormatter" %>
<%@page import="es.uco.pw.data.dao.inscripcion.*" %>
<%@page import="es.uco.pw.business.campamento.*" %>
<%@page import="es.uco.pw.business.inscripcion.*" %>
<%@page import="es.uco.pw.data.common.DBconnection" %>


<jsp:useBean id="Usuario" scope ="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>
<jsp:useBean id="Campamentos" scope ="request" class="es.uco.pw.display.javabean.CampListBean"></jsp:useBean>



<%

	int id = Usuario.getId();

	

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy");
	LocalDate Fecha = LocalDate.now();
	String fechafin = Fecha.format(formatter);
	
	Properties prop = new Properties();

	Properties sqlprop = new Properties();

// Obtener parámetros de inicialización del archivo web.xml
	String sql = application.getInitParameter("sql");
	String conf = application.getInitParameter("config");
    
	InputStream input= application.getResourceAsStream(conf);
    prop.load(input);
    
	InputStream input2 = application.getResourceAsStream(sql);
	sqlprop.load(input2);
	
	GestorInscripciones gestorInscripciones = new GestorInscripciones();
	ArrayList<DTOCampamento> campamentos = gestorInscripciones.filtrarInscripciones(id, prop, sqlprop);
	
	Campamentos.setMiLista(campamentos);
		
	 
	 %>
       



<jsp:forward page="../../view/asistente/AsistenteMenuView.jsp">
	<jsp:param name="fecha" value="<%=fechafin%>"/>
</jsp:forward>

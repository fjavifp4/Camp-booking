<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="es.uco.pw.business.usuario.*"%>
<%@ page import="es.uco.pw.data.dao.usuario.*"%>
<%@ page import="es.uco.pw.data.common.DBconnection"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.util.Properties"%>
<%@ page import="java.io.*"%>
<%@ page import="java.time.LocalDate"%>
<%@page import="java.sql.*"%>

<jsp:useBean id="Usuario" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Comprobando</title>
</head> 

<body>
    <%String aclVisitante = application.getInitParameter("aclVisitante"); %>
    <jsp:include page="<%=aclVisitante%>"></jsp:include>
    
    <% 
  
    DTOUsuario usuarioLogueado = new DTOUsuario();

    Properties prop = new Properties();
   
    Properties sqlprop = new Properties();
    DTOUsuario Usuarios = new DTOUsuario();
    
    // Obtener parámetros de inicialización del archivo web.xml
    String sql = application.getInitParameter("sql");
    String conf = application.getInitParameter("config");
        
    InputStream input= application.getResourceAsStream(conf);
        prop.load(input);
        
    InputStream input2 = application.getResourceAsStream(sql);
    sqlprop.load(input2);
        
	String email = request.getParameter("correo");
    String password = request.getParameter("pass");
    GestorUsuarios gestorUsuarios = new GestorUsuarios();
    
   
    if (email==null || password==null){ %>
        <jsp:forward page="../../view/common/LoginView.jsp">
            <jsp:param name="Nothing" value="true"/>
        </jsp:forward>
    
    <%}else if (!gestorUsuarios.comprobarCredenciales(email,password, prop, sqlprop)) {%>
        usuarioLogueado = gestorUsuarios.getUsuarioPorEmail(email,prop, sqlprop);%>
        <jsp:forward page="../../view/common/LoginView.jsp">
            <jsp:param name="ErrorLogin" value="true"/>
        
   
        </jsp:forward>

    <%}else{
        usuarioLogueado = gestorUsuarios.getUsuarioPorEmail(email, prop, sqlprop);%>

        <jsp:setProperty property="id" value="<%=usuarioLogueado.getIdentificador()%>" name="Usuario" />
        <jsp:setProperty property="nombre_apellidos" value="<%=usuarioLogueado.getNombreApellidos()%>" name="Usuario" />
        <jsp:setProperty property="fecha_nacimiento" value="<%=usuarioLogueado.getFechaNacimiento()%>" name="Usuario" />
        <jsp:setProperty property="requiereAtencionEspecial" value="<%=usuarioLogueado.esrequiereAtencionEspecial()%>" name="Usuario" />
        <jsp:setProperty property="email" value="<%=email%>" name="Usuario" />
        <jsp:setProperty property="password" value="<%=password%>" name="Usuario" />
        <jsp:setProperty property="rol" value="<%=usuarioLogueado.getRol()%>" name="Usuario" />

        <jsp:forward page="../../../index.jsp"/>
    <%}%>
    
</body>
</html>
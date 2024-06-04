<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="es.uco.pw.business.usuario.*" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.util.Properties" %>
<%@ page import="java.io.*" %>

<jsp:useBean  id="Usuario" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
    <html>
        <head>
            <meta charset="UTF-8">
            <title>Cargando</title>
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
            
                
        String nombreCompleto = request.getParameter("nombre");
        boolean requiereAtencionEspecial = request.getParameter("atencionEspecial") != null;
        String email = request.getParameter("correo");
        String password = request.getParameter("pass");
        String fechaNacimientoStr = request.getParameter("fechaNacimiento");
        LocalDate fechaNacimiento = LocalDate.parse(fechaNacimientoStr);
        
        if( email == null || nombreCompleto==null || password == null || fechaNacimiento == null){%>
                <jsp:forward page="../../view/common/RegistroView.jsp">
                    <jsp:param name="ErrorRegister" value="true" />
                </jsp:forward>
        <%} 
        GestorUsuarios gestorUsuarios = new GestorUsuarios();
        DTOUsuario newUser = new DTOUsuario(-1,nombreCompleto,fechaNacimiento,requiereAtencionEspecial,email,password,"asistente");
        
        if(gestorUsuarios.altaUsuario(newUser, prop, sqlprop)==false){%>
           <jsp:forward page="../../view/common/RegistroView.jsp">
                    <jsp:param name="UserRegistered" value="true" />
            </jsp:forward>

        <%}else{
            newUser=gestorUsuarios.getUsuarioPorEmail(email, prop, sqlprop);%>
            <jsp:setProperty property="id" value="<%=newUser.getIdentificador()%>" name="Usuario"/>
            <jsp:setProperty property="nombre_apellidos"    value="<%=nombreCompleto%>"    name="Usuario"/>
            <jsp:setProperty property="fecha_nacimiento"    value="<%=fechaNacimiento%>"    name="Usuario"/>
            <jsp:setProperty property="requiereAtencionEspecial"    value="<%=requiereAtencionEspecial%>"    name="Usuario"/>
            <jsp:setProperty property="email"    value="<%=email%>"    name="Usuario"/>
            <jsp:setProperty property="password"    value="<%=password%>"    name="Usuario"/>
            <jsp:setProperty property="rol"    value="<%=newUser.getRol()%>"    name="Usuario"/>

            <jsp:forward page="../../../index.jsp"/>
        <%}%>

    </body>
</html>
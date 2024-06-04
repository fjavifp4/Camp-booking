<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="es.uco.pw.business.usuario.*" %>
<%@ page import="es.uco.pw.data.dao.usuario.*" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.util.Properties" %>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>

<jsp:useBean  id="Usuario" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
    <html>
        <head>
            <meta charset="UTF-8">
            <title>Cargando</title>
        </head>
    <body>
        <%
        
        DTOUsuario usuarioModificado = new DTOUsuario();

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
        
       
              
        if(nombreCompleto==null || password == null || fechaNacimiento == null){%>
                <jsp:forward page="../../view/common/ModificarDatosView.jsp">
                    <jsp:param name="ErrorMod1" value="true" />
                </jsp:forward>
        <%} 
        
        DTOUsuario userMod = new DTOUsuario(Usuario.getId(),nombreCompleto,fechaNacimiento,requiereAtencionEspecial,Usuario.getEmail(),password," ");
        
        
        GestorUsuarios gestorUsuarios = new GestorUsuarios();
        
		
        if(!(gestorUsuarios.modificarUsuario(userMod,prop,sqlprop))){%>
           <jsp:forward page="../../view/common/ModificarDatosView.jsp">
                    <jsp:param name="ErrorMod2" value="true" />
            </jsp:forward>

        <%}else{
            %>
            <jsp:setProperty property="nombre_apellidos"    value="<%=nombreCompleto%>"    name="Usuario"/>
            <jsp:setProperty property="fecha_nacimiento"    value="<%=fechaNacimiento%>"    name="Usuario"/>
            <jsp:setProperty property="requiereAtencionEspecial"    value="<%=requiereAtencionEspecial%>"    name="Usuario"/>
            <jsp:setProperty property="password"    value="<%=password%>"    name="Usuario"/>

            <jsp:forward page="../../../index.jsp"/>
        <%}%>

    </body>
</html>
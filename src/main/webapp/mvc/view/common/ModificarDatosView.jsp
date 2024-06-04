<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.time.LocalDate" %>
<%@page import="java.time.format.DateTimeFormatter" %>
<%@ page import="es.uco.pw.business.usuario.*" %>

<jsp:useBean id="Usuario" scope ="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Modificar Datos del Usuario</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/welcome.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/styles.css">
</head>
<body>
    <div class="container">
        <h2>Modificar Datos del Usuario</h2>
	

        <form id="form" method="post" action="<%=request.getContextPath()%>/mvc/controller/common/ModificarDatosController.jsp">         
            <br><br>
            <label for="nombre">Nombre y apellidos</label>
            <input type="text" name="nombre" value="<%=Usuario.getNombre_apellidos()%>" required>
            <br><br>

            <label for="email">Correo electrónico</label>
            <input type="email" name="correo" id="correo" value="<%=Usuario.getEmail()%>" disabled required>
            <br><br>

            <label for="password">Contraseña</label>
            <input type="password" name="pass" id="pass" value="<%=Usuario.getPassword()%>" required>
            <br><br>
            <input type="checkbox" id="ver" onclick="mostrarContraseña()">
            <label for="mostrarContraseña">Mostrar contraseña</label>
            <br><br>

            <label for="fechaNacimiento">Fecha de nacimiento</label>
			<input type="date" name="fechaNacimiento" value="<%=Usuario.getFecha_nacimiento().format(DateTimeFormatter.ISO_LOCAL_DATE)%>" required>
            <br><br>

            <label for="atencionEspecial">¿Requiere atención especial?</label>
            <input type="checkbox" name="atencionEspecial" id="atencionEspecial" <% if (Usuario.getRequiereAtencionEspecial()) { out.print("checked"); } %>>
            <br><br>

            <div>
                <input type="reset" class="btn" value="Borrar"/>
                <input type="submit" class="btn" value="Actualizar Datos"/>
            </div>
        </form>
        
        <form method="post" action="<%=request.getContextPath()%>">
            <br>
            <input type="submit" class="btn" value="Volver">
        </form>
       
        <%if(request.getParameter("ErrorMod1") != null) {%>
			<p class="cajaRoja"> Error al modificar.</p>
	    <%}%>
	    <%if(request.getParameter("ErrorMod2") != null) {%>
			<p class="cajaRoja"> Error al modificar.</p>
	    <%}%>
    </div>
   
    <script type="text/javascript">
        function mostrarContraseña() {
            var checkBox = document.getElementById('ver');
            var texto = document.getElementById('pass');
            if (checkBox.checked == true) {
                texto.type = 'text';
            } else {
                texto.type = 'password';
            }
        }
    </script>
</body>
</html>
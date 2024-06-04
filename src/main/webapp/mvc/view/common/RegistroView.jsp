<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Crear una cuenta</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/welcome.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/styles.css">
    
</head>

<body>
    <%String aclVisitante = application.getInitParameter("aclVisitante"); %>
    <jsp:include page="<%=aclVisitante%>"></jsp:include>
    
    <div class="container">
        <h1>Crear una cuenta</h1>
        <form id="form" method="post" action="<%=request.getContextPath()%>/mvc/controller/common/RegistroController.jsp">
            <label for="nombre">Nombre y apellidos</label>
            <input type="text" name="nombre" placeholder="John Doe Doe" required>
            <br><br>       
            <label for="email">Correo electrónico</label>
            <input type="email" name="correo" id="correo" placeholder="email@email.com" required>
            <br><br>
            <label for="password">Contraseña</label>
            <input type="password" name="pass" id="pass" placeholder="Password" required>
            <br><br>
            <input type="checkbox" id="ver" onclick="mostrarContraseña()">
            <label for="mostrarContraseña">Mostrar contraseña</label>
            <br><br>
            <label for="fechaNacimiento">Fecha de nacimiento</label>
            <input type="date" name="fechaNacimiento" required>
            <br><br>
            <label for="atencionEspecial">¿Requiere atención especial?</label>
            <input type="checkbox" name="atencionEspecial" id="atencionEspecial">
            <br><br>
            <div>
                <input type="reset" class="btn" value="Borrar"/>
                <input type="submit" class="btn" value="Enviar"/>
            </div>
        </form>
        <form method="post" action="<%=request.getContextPath()%>">
            <br>
            <div>
                <input type="submit" class="btn" value="Volver">
            </div>
        </form>
        <%if(request.getParameter("ErrorRegister") != null) {%>
			<p class="cajaRoja"> Par&aacute;metros incorrectos. </p>
	    <%} else if ( request.getParameter("UserRegistered") != null) {%>
	    		<p class="cajaRoja"> Usuario ya registrado. </p>
	    <% }%>
	    
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

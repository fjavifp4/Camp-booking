<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Iniciar sesión</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/welcome.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/styles.css">
</head>

<body>
    <%String aclVisitante = application.getInitParameter("aclVisitante"); %>
    <jsp:include page="<%=aclVisitante%>"></jsp:include>

    <div class="container">
        <h1>Iniciar sesión</h1>
        <form id="form" method="post" action="<%=request.getContextPath()%>/mvc/controller/common/LoginController.jsp">
            <label for="email">Correo electrónico</label>
            <input type="email" name="correo" id="correo" placeholder="ejemplo@email.com" required>
            <br><br>
            <label for="password">Contraseña</label>
            <input type="password" name="pass" id="pass" placeholder="password" required>
            <br><br>
            <input type="checkbox" id="ver" onclick="mostrarContraseña()">
            <label for="mostrarContraseña">Mostrar contraseña</label>
            <br><br>
            <div>
                <input type="reset" class="btn" value="Borrar"/>
                <input type="submit" class="btn" value="Acceder"/>
            </div>
        </form>
        <form method="post" action="<%=request.getContextPath()%>">
            <br>
            <input type="submit" class="btn" value="Volver">
        </form>
        <%if(request.getParameter("ErrorLogin") != null) {%>
			<p class="cajaRoja"> Email o contraseña incorrectas. </p>
            
	    <%} else if( request.getParameter("Nothing") != null)  {	%>
            <p class="cajaBlanca"> Debes introducir el correo y la contraseña. </p>
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



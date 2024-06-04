<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Crear Monitor</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/welcome.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/styles.css">
    
</head>
<body>
    <%String aclAdmin = application.getInitParameter("aclAdmin"); %>
	<jsp:include page="<%=aclAdmin%>"></jsp:include>

    <div class="container">
        <h1>Crear Monitor</h1>
        <form id="form" method="post" action="<%=request.getContextPath()%>/CrearMonitor">
            
            <label for="nombre_apellidos">Nombre y Apellidos:</label>
            <input type="text" id="nombre_apellidos" name="nombre_apellidos" required>
            <br><br>
            
            <label for="educadorEspecial">Educador Especial:</label>
            <input type="checkbox" id="educadorEspecial" name="educadorEspecial">
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

        <%if(request.getAttribute("ErrorMonitor") != null) {%>
			<p class="cajaRoja"> Error al crear monitor.</p>
	    <%}%>
    </div>
</body>
</html>
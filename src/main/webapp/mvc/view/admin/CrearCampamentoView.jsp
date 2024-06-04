<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Crear Campamento</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/welcome.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/styles.css">
    
</head>
<body>
    <%String aclAdmin = application.getInitParameter("aclAdmin"); %>
	<jsp:include page="<%=aclAdmin%>"></jsp:include>

    <div class="container">
        <h1>Crear Campamento</h1>
        <form id="form" method="post" action="<%=request.getContextPath()%>/CrearCampamento">
           
            <label for="nombre">Nombre del Campamento:</label>
            <input type="text" id="nombre" name="nombre" required>
            <br><br>
           
            <label for="fechaInicio">Fecha de Inicio:</label>
            <input type="date" id="fechaInicio" name="fechaInicio" required>
            <br><br>
           
            <label for="fechaFin">Fecha de Finalización:</label>
            <input type="date" id="fechaFin" name="fechaFin" required>
            <br><br>
           
            <label for="nivelEducativo">Nivel Educativo:</label>
            <select id="nivelEducativo" name="nivelEducativo">
                <option value="" disabled selected hidden>Selecciona un Nivel Educativo</option>
                <option value="infantil">Infantil</option>
                <option value="juvenil">Juvenil</option>
                <option value="adolescente">Adolescente</option>
            </select>
            <br><br>
           
            <label for="maxUsuarios">Número Máximo de Asistentes:</label>
            <input type="number" id="maxUsuarios" name="maxUsuarios" min="1" required>
            <br><br>
            
            <div >
                <input type="reset" class="btn" value="Borrar"/>
                <input type="submit" class="btn" value="Enviar"/>
            </div>
        </form>

        <form method="post" action="<%=request.getContextPath()%>">
            <br>
            <div >
                <input type="submit" class="btn" value="Volver">
            </div>
        </form>

        <%if(request.getAttribute("ErrorCamp") != null) {%>
			<p class="cajaRoja"> Error al crear campamento.</p>
	    <%}%>
    </div>
</body>
</html>

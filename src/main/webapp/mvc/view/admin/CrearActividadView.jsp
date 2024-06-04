<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Crear Actividad</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/welcome.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/styles.css">
    
</head>
<body>
    <%String aclAdmin = application.getInitParameter("aclAdmin"); %>
	<jsp:include page="<%=aclAdmin%>"></jsp:include>

    <div class="container">
        <h1>Crear Actividad</h1>
        <form id="form" method="post" action="<%=request.getContextPath()%>/CrearActividad">
            
            <label for="nombreActividad">Nombre de la Actividad:</label>
            <input type="text" id="nombreActividad" name="nombreActividad" required>
            <br><br> 
            
            <label for="nivelEducativo">Nivel Educativo:</label>
            <select id="nivelEducativo" name="nivelEducativo" required>
                <option value="" disabled selected hidden>Selecciona un Nivel Educativo</option>
                <option value="infantil">Infantil</option>
                <option value="juvenil">Juvenil</option>
                <option value="adolescente">Adolescente</option>
            </select>
            <br><br> 
    
            <label for="horario">Horario:</label>
            <select id="horario" name="horario" required>
                <option value="" disabled selected hidden>Selecciona un horario</option>
                <option value="manana">Mañana</option>
                <option value="tarde">Tarde</option>
            </select>
            <br><br> 
    
            <label for="maxParticipantes">Número Máximo de Participantes:</label>
            <input type="number" id="maxParticipantes" name="maxParticipantes" min="1" required>
            <br><br> 
    
            <label for="monitoresNecesarios">Número de Monitores Necesarios:</label>
            <input type="number" id="monitoresNecesarios" name="monitoresNecesarios" min="1" required>
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

        <%if(request.getAttribute("ErrorAct") != null) {%>
			<p class="cajaRoja"> Error al crear actividad.</p>
	    <%}%>
    </div>
</body>
</html>

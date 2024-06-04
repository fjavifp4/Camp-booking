<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:useBean id="Usuario" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inicio</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/welcome.css">
</head>
<body>
    
    <div class="container">
        <%if (Usuario.getEmail() == null || Usuario.getRol() == null){%>
            
            <h1>Reservas de campamentos</h1>
            <a href="<%=request.getContextPath()%>/mvc/view/common/LoginView.jsp" class="btn">Inicia sesión</a>
            <a href="<%=request.getContextPath()%>/mvc/view/common/RegistroView.jsp" class="btn">Crea una cuenta</a>
        
        <%} else if (Usuario.getRol().equals("admin")){%>
                <jsp:forward page="/mvc/controller/admin/AdminMenuController.jsp"/>
        

        <% } else if (Usuario.getRol().equals("asistente")){ %>
                <jsp:forward page="/mvc/controller/asistente/AsistenteMenuController.jsp"/>


        <% }%>
    </div>
</body>
</html>
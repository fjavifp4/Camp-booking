<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="es.uco.pw.business.campamento.*"%>

<jsp:useBean id="Campamentos" scope ="request" class="es.uco.pw.display.javabean.CampListBean"></jsp:useBean>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Consultar Campamentos por fechas</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/funcionalidades.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/styles.css">
    
</head>
<body>
    <%String aclUsuario = application.getInitParameter("aclUsuario"); %>
	<jsp:include page="<%=aclUsuario%>"></jsp:include>
    
    <div class="container">
        <h1>Consultar Campamentos por fecha</h1>
        <form id="form" method="post" action="<%=request.getContextPath()%>/ConsultarCampamentos">
            
            <label for="fechaInicio">Seleccione una fecha:</label>
            <input type="date" id="fechaInicio" name="fechaInicio" required>
            <br><br>
            
            <label for="fechaFin">Seleccione una fecha:</label>
            <input type="date" id="fechaFin" name="fechaFin" required>
            <br><br>   

            <div class="buttons">
                <input type="reset" class="button" value="Borrar" onclick="limpieza()"/>
                <input type="submit" class="button" value="Enviar"/>
            </div>
        </form>

        <form method="post" action="<%=request.getContextPath()%>">
            <br>
            <div class="buttons">
                <input type="submit" class="button" value="Volver">
            </div>
        </form>
        
    </div>

    <div class="container">
        <section class="camp-list">
            <%if(request.getAttribute("ErrEmpty")!=null) {%>
                <p class="cajaRoja"> No hay campamentos en la fecha seleccionada.</p>
    
            <br>
        
            <%}else{%>
                <h3>Campamentos disponibles:</h3>
                <%
                for (DTOCampamento c : Campamentos.getMiLista()) {%>
                    <ul>
                        <li>
                            <p><strong>Nombre: </strong><%=c.getNombre()%></p>
                            <p><strong>Nivel Educativo: </strong> <%=c.getNivelEducativo()%></p>
                        </li>
                    </ul>    
                    
                <%}%>
            <%}%>
        </section>
    </div>

    <script>
        window.addEventListener('beforeunload', function (event) {
            limpieza(); 
        });
        function limpieza() {
            //oculta el div de campamentos si es que hay
            var list = document.getElementById("camp-list");
            if (list) {
                // Si existe, oculta el div
                list.parentNode.removeChild(list);
            }
        }
    </script>
</body>
</html>

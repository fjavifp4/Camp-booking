<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="es.uco.pw.business.campamento.*"%>

<jsp:useBean id="CampamentosNvl" scope ="request" class="es.uco.pw.display.javabean.CampListBean"></jsp:useBean>
<jsp:useBean id="CampamentosMin" scope ="request" class="es.uco.pw.display.javabean.CampListBean"></jsp:useBean>

<!DOCTYPE html> 
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, 
    initial-scale=1.0">
    <title>Filtrar Campamentos </title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/funcionalidades.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/styles.css">
</head>

<body>
    <%String aclUsuario = application.getInitParameter("aclUsuario"); %>
	<jsp:include page="<%=aclUsuario%>"></jsp:include>
    
    <div class="container">
        <h1>Filtrar Campamentos</h1>

        <form id="form1" method="post" action="<%=request.getContextPath()%>/FiltrarCampamentos" onsubmit="return validarFormulario()">

            <label for="nivelEducativo">Filtrar por nivel educativo:</label>
            <select name="nivelEducativo" id="nivelEducativo">
                <option value="" disabled selected hidden>Selecciona un nivel</option>
                <option value="infantil">Infantil</option>
                <option value="juvenil">Juvenil</option>
                <option value="adolescente">Adolescente</option>
            </select>
            <br><br>
    
            <label for="minPlazas">Filtrar por número mínimo de plazas:</label>
            <input type="number" id="minPlazas" name="minPlazas" min="1">
            <br><br>

            <div class="buttons">
                <input type="reset" class="button" value="Borrar"/>
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
        <%if(CampamentosNvl.getMiLista().size()>0){%>
            <section class="camp-list">
                <h3>Campamentos disponibles:</h3>
                <%
                for (DTOCampamento c : CampamentosNvl.getMiLista()) {%>
                    <ul>
                        <li>
                            <p><strong>Nombre: </strong><%=c.getNombre()%></p>
                            <p><strong>Nivel Educativo: </strong> <%=c.getNivelEducativo()%></p>
                        </li>
                    </ul>    
                <%}%>
            </section>
        <%} 
        if(CampamentosMin.getMiLista().size()>0){%>
            <section class="camp-list">
                <h3>Campamentos disponibles:</h3>
                <%
                for (DTOCampamento c : CampamentosMin.getMiLista()) {%>
                    <ul>
                        <li>
                            <p><strong>Nombre: </strong><%=c.getNombre()%></p>
                            <p><strong>Nivel Educativo: </strong> <%=c.getNivelEducativo()%></p>
                        </li>
                    </ul>    
                <%}%>
            </section>
        <%}
        if(CampamentosMin.getMiLista().size()==0 && CampamentosNvl.getMiLista().size()==0){%>
            <p>No hay campamentos que cumplan los requisitos.</p>
        <%}%>
    </div>

    <script>
        function validarFormulario() {
            var nivelEducativo = document.getElementById("nivelEducativo").value;
            var minPlazas = document.getElementById("minPlazas").value;

            if ((nivelEducativo === "" && minPlazas === "") || (nivelEducativo !== "" && minPlazas !== "")) {
                alert("Por favor, selecciona solo una opción de filtro.");
                return false;
            }

            return true;
        }
    </script>  


</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="es.uco.pw.business.campamento.*"%>
<%@page import="es.uco.pw.business.actividad.*"%>

<jsp:useBean id="Campamentos" scope ="session" class="es.uco.pw.display.javabean.CampListBean"></jsp:useBean>
<jsp:useBean id="Actividades" scope ="session" class="es.uco.pw.display.javabean.ActListBean"></jsp:useBean>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Asociar actividad a campamento</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/welcome.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/styles.css">
    
</head>
<body onload="restablecerSelects()">
    <%String aclAdmin = application.getInitParameter("aclAdmin"); %>
	<jsp:include page="<%=aclAdmin%>"></jsp:include>
    
    <div class="container">
        <h1>Asociar actividad a campamento</h1>
        <form id="form" method="post" action="<%=request.getContextPath()%>/ActividadCampamento" >

            <label for="nivelEducativo">Filtra por Nivel Educativo:</label>
            <select id="nivelEducativo" name="nivelEducativo" onchange="filtrarPorNivelEducativo()" required>
                <option value="" disabled selected hidden>Selecciona un nivel</option>
                <option value="infantil">Infantil</option>
                <option value="juvenil">Juvenil</option>
                <option value="adolescente">Adolescente</option>
            </select>
            <br><br>

            <select id="actividad" name="actividad" required>
                <option value="" disabled selected hidden>Selecciona una actividad</option>
                <% for(DTOActividad actividad : Actividades.getMiLista()) { %>
                    <option value="<%= actividad.getNombreActividad() %>" data-nivel="<%= actividad.getNivelEducativo() %>">
                        <%= actividad.getNombreActividad() %>
                    </option>
                <% } %>
            </select>
            <br><br>
            
            <select id="campamento" name="campamento" required>
                <option value="" disabled selected hidden>Selecciona un campamento</option>
                <% for(DTOCampamento campamento : Campamentos.getMiLista()) { %>
                    <option value="<%= campamento.getIdentificador() %>" data-nivel="<%= campamento.getNivelEducativo() %>">
                        <%= campamento.getNombre() %>
                    </option>
                <% } %>
            </select>
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

        <%if(request.getAttribute("ErrorActCamp") != null) {%>
			<p class="cajaRoja"> Error al asociar actividad a campamento.</p>
            
	    <%  session.removeAttribute("Actividades");
            session.removeAttribute("Campamentos");
        }%>
    </div>

    <script>
        function restablecerSelects() {
            restablecerSelect('nivelEducativo');
            restablecerSelect('actividad');
            restablecerSelect('campamento');
        }

        function filtrarPorNivelEducativo() {
            var nivelSeleccionado = document.getElementById('nivelEducativo').value;

            restablecerSelect('actividad');
            restablecerSelect('campamento');

            if(nivelSeleccionado) {
                var actividades = document.getElementById('actividad').options;
                var campamentos = document.getElementById('campamento').options;

                filtrarOpciones(actividades, nivelSeleccionado);
                filtrarOpciones(campamentos, nivelSeleccionado);
            }
        }

        function restablecerSelect(selectId) {
            var select = document.getElementById(selectId);
            select.selectedIndex = 0; 
        }

        function filtrarOpciones(opciones, nivel) {
            for (var i = 0; i < opciones.length; i++) {
                opciones[i].style.display = opciones[i].getAttribute('data-nivel') === nivel ? 'block' : 'none';
            }
        }
    </script>
</body>
</html>  
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.time.*" %>
<%@page import="java.time.format.DateTimeFormatter" %>
<%@page import="java.io.*" %>
<%@page import="java.util.*" %>
<%@page import="es.uco.pw.data.dao.inscripcion.*" %>
<%@page import="es.uco.pw.business.campamento.*" %>
<%@page import="es.uco.pw.data.common.DBconnection" %>

<jsp:useBean id="Usuario" scope ="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>
<jsp:useBean id="Campamentos" scope ="request" class="es.uco.pw.display.javabean.CampListBean"></jsp:useBean>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Menu</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/menunew.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/styles.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pop-up.css">
</head>
<body>
    <div class="header">
        <h2>Bienvenido, <jsp:getProperty name="Usuario" property="nombre_apellidos"/></h2>
        <h4>Hoy es <%=request.getParameter("fecha")%></h4>
        <div class="botones">
            <form action="<%=request.getContextPath()%>/mvc/view/common/ModificarDatosView.jsp" method="get">
                <input type="submit" class="button" value="Modificar Datos"/>
            </form>
            <form action="<%=request.getContextPath()%>/mvc/controller/common/LogOutController.jsp" method="post">
                <input type="submit" class="button" value="Desconectar"/>
            </form>
            <form action="<%=request.getContextPath()%>/mvc/view/asistente/ConsultarCampamentosView.jsp" method="get">
                <input type="submit" class="button" value="Consultar Campamentos por fecha"/>
            </form>
            <form action="<%=request.getContextPath()%>/mvc/view/asistente/FiltrarCampamentosView.jsp" method="get">
                <input type="submit" class="button" value="Filtrar Campamentos"/>
            </form>
            <form action="<%=request.getContextPath()%>/Inscripcion" method="get">
                <input type="submit" class="button" value="Realizar Inscripcion"/>
            </form>
            <form action="<%=request.getContextPath()%>/CancelarInscripcion" method="get">
                <input type="submit" class="button" value="Cancelar Inscripcion"/>
            </form>

        </div>
    </div>

    <div class="container admin-specific">
        <section class="camp-list">
            <h3>Mis inscripciones</h3>
            <%
            ArrayList<DTOCampamento> campamentos = Campamentos.getMiLista();
            if (campamentos != null && !campamentos.isEmpty()) {
                for (DTOCampamento c : campamentos) {
            %>
                    <ul>
                        <li>
                        	<p><strong>Campamento con ID:</strong> <%=c.getIdentificador()%></p>
                        	<p><strong>Fecha de inicio:</strong> <%=c.getFechaInicio()%></p>
                        </li>
                    </ul>
            <%
                }
            } else {
            %>
                <p>No est&aacute;s inscrito en ning&uacute;n campamento.</p>
            <%
            }
            %>
        </section>
    </div>

    <div id="popupMensaje" class="popup-mensaje" style="display: none;">
        <div class="popup-contenido">
            <span class="close-btn" onclick="cerrarPopup()">&times;</span>
            <p id="textoMensaje"></p>
        </div>
    </div>
    
    <script>
        function cerrarPopup() {
            document.getElementById('popupMensaje').style.display = 'none';
        }
    </script>

    <%
    String mensajeExito = (String) session.getAttribute("mensajeExito");
    if (mensajeExito != null && !mensajeExito.isEmpty()) {
    %>
        <script>
            window.onload = function() {
                document.getElementById('textoMensaje').innerText = '<%= mensajeExito %>';
                document.getElementById('popupMensaje').style.display = 'block';
            };
        </script>
    <%
    }
    %>

    <%if((request.getAttribute("ACL") != null)) {%>
        <script>
            window.onload = function() {
                document.getElementById('textoMensaje').innerText = '<%= request.getAttribute("ACL") %>';
                document.getElementById('popupMensaje').style.display = 'block';
            };
        </script>
    <%}%>

    <%if((request.getParameter("ACL") != null)) {%>
        <script>
            window.onload = function() {
                document.getElementById('textoMensaje').innerText = '<%= request.getParameter("ACL") %>';
                document.getElementById('popupMensaje').style.display = 'block';
            };
        </script>
    <%}%>
</body>
</html>
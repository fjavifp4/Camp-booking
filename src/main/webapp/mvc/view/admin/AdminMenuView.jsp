<%@page import="java.io.*" %>
<%@page import="java.util.*" %>
<%@page import="java.time.LocalDate" %>
<%@page import="java.time.format.DateTimeFormatter" %>
<%@page import="es.uco.pw.data.dao.inscripcion.*" %>
<%@page import="es.uco.pw.business.campamento.*" %>
<%@page import="es.uco.pw.business.inscripcion.*" %>
<%@page import="es.uco.pw.data.common.DBconnection" %>

<jsp:useBean id="Lists" scope ="request" class="es.uco.pw.display.javabean.AdminListsBean"></jsp:useBean>


<!DOCTYPE html>
<html>
<head>
    <title>Menu</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/menunew.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/styles.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pop-up.css">

</head>
<body>    
    <div class="header">
        
        <h2>Menu de administrador</h2>
        <div class="botones">
            <form action="<%=request.getContextPath()%>/mvc/view/common/ModificarDatosView.jsp" method="get">
                <input type="submit" class="button" value="Modificar Datos"/>
            </form>
            <form action="<%=request.getContextPath()%>/mvc/controller/common/LogOutController.jsp" method="post">
                <input type="submit" class="button" value="Desconectar"/>
            </form>
            <form action="<%=request.getContextPath()%>/mvc/view/admin/CrearActividadView.jsp" method="get">
                <input type="submit" class="button" value="Crear Actividad"/>
            </form>
            <form action="<%=request.getContextPath()%>/mvc/view/admin/CrearCampamentoView.jsp" method="get">
                <input type="submit" class="button" value="Crear Campamento"/>
            </form>
            <form action="<%=request.getContextPath()%>/mvc/view/admin/CrearMonitorView.jsp" method="get">
                <input type="submit" class="button" value="Crear Monitor"/>
            </form>
            <form action="<%=request.getContextPath()%>/ActividadCampamento" method="get">
                <input type="submit" class="button" value="Asociar Actividad a Campamento"/>
            </form>
            <form action="<%=request.getContextPath()%>/MonitorActividad" method="get">
                <input type="submit" class="button" value="Asociar Monitor a Actividad "/>
            </form>
            <form action="<%=request.getContextPath()%>/MonitorCampamento" method="get">
                <input type="submit" class="button" value="Asociar Monitor a Campamento "/>
            </form>
        </div>
    </div>

    <div class="container admin-specific">
        <section class="camp-list">
            <h3>Campamentos</h3>
            <%
            ArrayList<DTOCampamento> campamentos = Lists.getCampList();
            ArrayList<Integer> plazasCompletas = Lists.getPlazasTotales();
            ArrayList<Integer> plazasParciales = Lists.getPlazasParciales();
            int i = 0;    
            %>
            <%
            for (DTOCampamento c : campamentos) {%>
                <ul>
                    <li>
                        <p class="nombre"><%=c.getNombre()%></p>
                        <p><strong>Asistentes inscritos de manera completa:</strong> <%=plazasCompletas.get(i)%></p>
                        <p><strong>Asistentes inscritos de manera parcial:</strong> <%=plazasParciales.get(i)%> </p>
                    </li>
                </ul>    
                
            <%i++;}%>
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
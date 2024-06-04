<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="es.uco.pw.business.monitor.*"%>
<%@page import="es.uco.pw.business.actividad.*"%>

<jsp:useBean id="Monitores" scope ="session" class="es.uco.pw.display.javabean.MonitorListBean"></jsp:useBean>
<jsp:useBean id="Actividades" scope ="session" class="es.uco.pw.display.javabean.ActListBean"></jsp:useBean>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Asociar monitor a actividad</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/welcome.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/styles.css">
    
</head>
<body>
    <%String aclAdmin = application.getInitParameter("aclAdmin"); %>
	<jsp:include page="<%=aclAdmin%>"></jsp:include>
    
    <div class="container">
        <h1>Asociar monitor a actividad</h1>
        <form id="form" method="post" action="<%=request.getContextPath()%>/MonitorActividad" >

            <select id="monitor" name="monitor" required>
                <option value="" disabled selected hidden>Selecciona un monitor</option>
                <% for(DTOMonitor monitor : Monitores.getMiLista()) { %>
                    <option value="<%=monitor.getIdentificador()%>"><%= monitor.getNombreApellidos() %></option>
                <% } %>
            </select>
            <br><br>

            <select id="actividad" name="actividad" required>
                <option value="" disabled selected hidden>Selecciona una actividad</option>
                <% for(DTOActividad actividad : Actividades.getMiLista()) { %>
                    <option value="<%= actividad.getNombreActividad() %>"><%= actividad.getNombreActividad() %></option>
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
			<p class="cajaRoja"> Error al asociar monitor a actividad.</p>
	    <%  session.removeAttribute("Monitores");
            session.removeAttribute("Actividades"); 
        }%>
    
    </div>

</body>
</html>  
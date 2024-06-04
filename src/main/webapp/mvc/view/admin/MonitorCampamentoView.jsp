<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="es.uco.pw.business.monitor.*"%>
<%@page import="es.uco.pw.business.actividad.*"%>
<%@page import="es.uco.pw.business.campamento.*"%>
<%@page import="java.util.ArrayList"%>

<jsp:useBean id="Monitores" scope ="request" class="es.uco.pw.display.javabean.MonitorListBean"></jsp:useBean>
<jsp:useBean id="MonitoresEspeciales" scope ="request" class="es.uco.pw.display.javabean.MonitorListBean"></jsp:useBean>
<jsp:useBean id="Campamentos" scope ="request" class="es.uco.pw.display.javabean.CampListBean"></jsp:useBean>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Asociar monitor a campamento</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/welcome.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/styles.css">
</head>
<body>
    <%String aclAdmin = application.getInitParameter("aclAdmin"); %>
	<jsp:include page="<%=aclAdmin%>"></jsp:include>
    
    <div class="container">
        <h1>Asociar monitor a campamento</h1>

        
        <form id="form1" method="post" action="<%=request.getContextPath()%>/MonitorCampamento" onsubmit="eliminarFormulario()" >

            <select id="campamento" name="campamento" required>
                <option value="" disabled selected hidden>Selecciona un campamento</option>
                <% for(DTOCampamento campamento : Campamentos.getMiLista()) { %>
                        <option value="<%=campamento.getIdentificador()%>"><%= campamento.getNombre() %></option>
                <% } %>
            </select>
            <br><br>

            <div >
                <input type="submit" class="btn" value="Enviar"/>
            </div>
        </form>
        <br><br>
        <form id="form2" method="post" action="<%=request.getContextPath()%>/MonitorCampamento" >

            <%if(Monitores.getMiLista().size()>0 && MonitoresEspeciales.getMiLista().size()==0){%>
                <select id="monitor" name="monitor" required>
                    <option value="" disabled selected hidden>Selecciona un monitor responsable</option>
                    <% for(DTOMonitor monitor : Monitores.getMiLista()) { %>
                        <option value="<%= monitor.getIdentificador() %>"><%= monitor.getNombreApellidos() %></option>
                    <% } %>
                </select>
                <br><br>
                <div>
                    <input type="submit" class="btn" value="Enviar"/>
                </div>
            <%} else if(MonitoresEspeciales.getMiLista().size()>0 && Monitores.getMiLista().size()==0){%>
                <select id="monitorespecial" name="monitorespecial" required>
                    <option value="" disabled selected hidden>Selecciona un monitor de necesidades especiales responsable</option>
                    <% for(DTOMonitor monitor : MonitoresEspeciales.getMiLista()) { %>
                        <option value="<%= monitor.getIdentificador() %>"><%= monitor.getNombreApellidos() %></option>
                    <% } %>
                </select>
                <br><br>
                <div>
                    <input type="submit" class="btn" value="Enviar"/>
                </div>
            <%} else if(MonitoresEspeciales.getMiLista().size()>0 && Monitores.getMiLista().size()>0){%>
                <select id="monitor" name="monitor" required>
                    <option value="" disabled selected hidden>Selecciona un monitor responsable</option>
                    <% for(DTOMonitor monitor : Monitores.getMiLista()) { %>
                        <option value="<%= monitor.getIdentificador() %>"><%= monitor.getNombreApellidos() %></option>
                    <% } %>
                </select>
                <br><br>

                <select id="monitorespecial" name="monitorespecial" required>
                    <option value="" disabled selected hidden>Selecciona un monitor de necesidades especiales responsable</option>
                    <% for(DTOMonitor monitor : MonitoresEspeciales.getMiLista()) { %>
                        <option value="<%= monitor.getIdentificador() %>"><%= monitor.getNombreApellidos() %></option>
                    <% } %>
                </select>
                <br><br>
                <div>
                    <input type="submit" class="btn" value="Enviar"/>
                </div>
            <%}%>
            
        </form>

        <form method="post" action="<%=request.getContextPath()%>">
            <br>
            <div>
                <input type="submit" class="btn" value="Volver">
            </div>
        </form>
        <%if(request.getAttribute("mensajeError") != null) {%>
            <p class="cajaRoja">No se puede asociar a un monitor especial a un campamento sin haber asistentes con necesidades especiales en este.</p>
        <%}%>

        <%if(request.getAttribute("ErrorMonCamp") != null) {%>
			<p class="cajaRoja"> Error al asociar actividad a campamento.</p>
	    <%}%>
        <%if(request.getAttribute("ErrorListaMon") != null) {%>
			<p class="cajaRoja"> No se han podido asociar monitores a este campamento.</p>
	    <%}%>
    </div>
    <script>
        function eliminarFormulario(){
            document.getElementById("form1").style.display = "none";
        }
    </script>
</body>
</html>  
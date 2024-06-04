<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="es.uco.pw.business.campamento.*"%>

<jsp:useBean id="CampamentosCancelables" scope ="request" class="es.uco.pw.display.javabean.CampListBean"></jsp:useBean>

<!DOCTYPE html> 
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cancelar inscripcion a un Campamento </title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/welcome.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/styles.css">
</head>

<body>
    <%String aclUsuario = application.getInitParameter("aclUsuario"); %>
	<jsp:include page="<%=aclUsuario%>"></jsp:include>
    
    <div class="container">
        <h1>Cancelar inscripcion a un Campamento </h1>
        <br>
        <form id="form1" method="post" action="<%=request.getContextPath()%>/CancelarInscripcion">
            <p><strong>Tus campamentos cancelables:</strong></p>

            <select id="campamento" name="campamento" required>
                <option value="" disabled selected hidden>Selecciona un campamento</option>
                <%for(DTOCampamento campamento : CampamentosCancelables.getMiLista()) { %>
                    <option value="<%= campamento.getIdentificador() %>"><%=campamento.getNombre()%></option>
                <% } %>
            </select>
            <br><br>

            <div>
                <input type="reset" class="btn" value="Borrar"/>
                <input type="submit" class="btn" value="Enviar"/>
            </div>

        </form>
        
        <form method="post" action="<%=request.getContextPath()%>">
            <br>
            <div>
                <input type="submit" class="btn" value="Volver">
            </div>
        </form>
        <%if(request.getParameter("ErrEmpty") != null) {%>
            <p class="cajaBlanca">No hay campamentos inscripcion para cancelar.</p>
        <%}
         if(request.getParameter("ErrCancelar") != null) {%>
			<p class="cajaRoja"> Error al cancelar inscripcion.</p>
	    <%}%>
    </div>

</body>
</html>
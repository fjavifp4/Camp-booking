<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="es.uco.pw.business.campamento.*"%>

<jsp:useBean id="Campamentos" scope ="request" class="es.uco.pw.display.javabean.CampListBean"></jsp:useBean>
<jsp:useBean id="Inscripcion" scope ="session" class="es.uco.pw.display.javabean.InscripcionBean"></jsp:useBean>


<!DOCTYPE html> 
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, 
    initial-scale=1.0">
    <title>Inscribirse a un Campamento </title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/welcome.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/styles.css">
</head>

<body>
    <%String aclUsuario = application.getInitParameter("aclUsuario"); %>
	<jsp:include page="<%=aclUsuario%>"></jsp:include>
    
    <div class="container">

        <h1>Realizar inscripcion a un campamento</h1>
        <br>
        <form id="form1" method="post" action="<%=request.getContextPath()%>/Inscripcion">
            <p><strong>Campamentos disponibles:</strong></p>

            <select id="campamento" name="campamento" required>
                <option value="" disabled selected hidden>Selecciona un campamento</option>
                <%for(DTOCampamento campamento : Campamentos.getMiLista()) { %>
                    <option value="<%= campamento.getIdentificador() %>"><%=campamento.getNombre()%></option>
                <% } %>
            </select>
            <br><br>
            <p><strong>Modalidad de inscripci&oacute;n:</strong></p>
            <select id="tipo" name="tipo" required>
                <option value="" disabled selected hidden>Selecciona la modalidad</option>
                <option value="completa" >Completa</option>
                <option value="parcial" >Parcial</option>
            </select>
            <br><br>

            <div >
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

        <%if(Inscripcion.getEstado()!= null && Inscripcion.getTipo() != null){%>
            <p class="statement"><strong>Estado de la inscripcion: </strong><%=Inscripcion.getEstado()%></p>
            <p class="statement"><strong>Precio de la inscripcion: </strong><%=Inscripcion.getPrecio()%></p>
            <br>

            <h3>Deseas confirmar con la inscripcion?</h3>
            <div>
                
                <form id="form" action="<%=request.getContextPath()%>/Inscripcion" method="post">
                    <input type="submit" id="confirm" name="confirm" class="btn" value="Si"/>
                    <input type="submit" id="confirm" name="confirm" class="btn" value="No"/>
                </form>
            </div>
        <%}%>
    </div>

</body>
</html>
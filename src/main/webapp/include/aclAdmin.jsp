<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean  id="Usuario" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Control de Acceso</title>
</head>
<body>
	<%
	if(Usuario.getRol() == null){%>
		<jsp:forward page="<%=request.getContextPath()%>">
		    <jsp:param name="ACL" value="No tiene permitido entrar alli" />
		</jsp:forward>
		<%
	}else if(! Usuario.getRol().equals("admin")){%>
		<jsp:forward page="../mvc/controller/admin/AsistenteMenuController.jsp">
		    <jsp:param name="ACL" value="No tiene permitido entrar alli" />
		</jsp:forward>
		<%
	}
	%>
</body>
</html>
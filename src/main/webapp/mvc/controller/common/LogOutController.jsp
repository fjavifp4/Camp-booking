<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="javax.servlet.http.HttpSession"%>

<%
    HttpSession session1 = request.getSession();

    if (session1 != null) {
        session1.invalidate();
    }

    response.sendRedirect("../../../index.jsp");
%>

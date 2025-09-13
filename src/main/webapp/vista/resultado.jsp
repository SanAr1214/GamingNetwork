<%-- 
    Document   : resultado
    Created on : 6/09/2025, 12:39:15 a. m.
    Author     : santi
--%>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Resultado</title>
</head>
<body>
    <h2><%= request.getAttribute("mensaje") %></h2>
    <a href="/registroUsuario.jsp">Volver al registro</a>
</body>
</html>

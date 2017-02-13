<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="dominio.Usuario" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	Usuario novoUsuario = (Usuario)session.getAttribute("novoUsuario");
	
	out.println(novoUsuario.getEmail()+ " " + novoUsuario.getNome()+ " " + novoUsuario.getTelefone());
%>

</body>
</html>
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

<form action="teste.jsp" method="post">
<select name="opcao">
<%
	
for(int contador = 0; contador < 10;contador++){
String teste1 = "modelo" + contador;
String teste2 = "placa" + contador;%>

<option><%=teste1 %><%="/"%><%=teste2 %></option>

<%	
}				
%>		
</select>


<input type="submit" value="Enviar">

</form>








</body>
</html>
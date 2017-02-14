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
	String resposta = request.getParameter("opcao");
	String[] teste = resposta.split("/");
	out.println(" opcao escolhida foi  " + resposta + "  o slipt foi = ");
	out.println("  modelo:  ");
	out.println(teste[0]);
	out.println("  placa:  ");
	out.println(teste[1]);
	
	if(teste[0].equals("modelo0")){
		out.println("testo0 ta certo");
	}
	if(teste[1].equals("placa0")){
		out.println("teste1 ta certo");
	}
%>

</body>
</html>
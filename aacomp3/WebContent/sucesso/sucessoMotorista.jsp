<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="dominio.Usuario"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Caronae - Página Inicial</title>
<style>
body 
{
  background: url(carona6.png) no-repeat white;
  background-size: cover;
}

table
{
	opacity: 0.75; 
	filter: alpha(opacity=75);
	border-radius: 8px;
}

td
{
	padding: 20px 0px 20px 20px; /*margem sentido horario acima direita abaixo esquerda*/ 
	background:linear-gradient(white, black);
	border-radius: 8px;
    border:black;
}

</style>
</head>
<body>
	<%
		
		Usuario novoUsuario = (Usuario)request.getAttribute("novoUsuario");
		String nome = novoUsuario.getNome();
		request.setAttribute("novoUsuario", novoUsuario);
	
	%>
	<h1 align = center>	Seu cadastro foi realizado com sucesso <%=nome %>!	</h1>

	<table  align=center width =600 border=1 bgcolor="white" bordercolor= "black" borderstyle="solid"> 
	
	
	<tr>
	<td width=50 align=center>							
		<br>
		<label> Você cadastrou veiculo de modelo<% %> e placa <% %> .</label>
		<label>Deseja cadastrar outro veiculo?</label>
		<form action="./criar/criarVeiculo.jsp" method="post">
			
			<input type="submit" value="Sim">
		</form>	
		<br>
		<form action="pagInicial.jsp" method="post">
			
			<input type="submit" value="Nao">
		</form>
		</td>
	</tr>
	
	</table>

</body>
</html>
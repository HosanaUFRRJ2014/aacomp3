<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="dominio.*" %>
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
	<%
		Usuario recuperado = (Usuario) session.getAttribute("novoUsuario");
		Carona minhaCarona = (Carona) session.getAttribute("novaCarona");		
	%>


</head>
<body>

	<h1 align = center> Adicione mais uma pessoa a sua carona	</h1>

	<table  align=center width =600 border=1 bgcolor="white" bordercolor= "black" borderstyle="solid"> 
	<form method="post" action="./criarLogradouro.jsp">
	<tr>
		<td width=50 >
			
								
				<br>
				<label> Email do Caroneiro *:</label>
				<input type= "text" name = "emailUsuario"><br>
				
				<label>Esses são o CEP e Número das paradas que já existem na sua carona:</label>	
				
					<select name="paradaEscolhida">
							<%					
								for(Parada p : minhaCarona.paradasDaCarona()){						
							%> 
				 					<option><%=p.getLog().getCEP()%><%="/"%><%=p.getLog().getNumero() %></option>
				  			<%}   				
				  			%>
					</select>
				<label>Deseja cadastrar o caroneiro nesta mesma parada?</label>
				
				<input type="hidden" name="opcao" value="mesmaParada">
				<input type= "submit" value="Sim"> <br><br>

	
		</td>
	</tr>
	</form>		
	</table>
	
	<form action="./criar/criarLogradouro.jsp" method="post">
			<label>Você pode criar uma nova parada para seu caroneiro</label><br>
			<input type="submit" value="Nova Parada">
			</form>
	

</body>
</html>
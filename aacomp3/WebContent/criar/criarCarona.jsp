<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dominio.Usuario" %>
<%@ page import= "java.util.*" %>
<%@ page import= "java.text.SimpleDateFormat" %>
<%@ page import="dominio.Veiculo" %>
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
		String emailUsuario = novoUsuario.getEmail();
		request.setAttribute("novoUsuario",novoUsuario);
	%>

	<h1 align = center>	<br> <br> </h1>

	<table  align=center width =600 border=1 bgcolor="white" bordercolor= "black" borderstyle="solid"> 
	<form method="post" action="../CtrCriarCarona">
	<tr>
		<td width=50 >
								
				<br>
				
				<label> Data da Carona *:</label>
				<input type= "text" value ="00/00" name = "diaCarona"></p>
				
				<label> Hora de Saída *:</label>
				<input type= "text" value= "00:00:00" name = "horaSaida"></p>
				
				<label> Cep e Número local de origem *:</label>
				<input type= "text" value = "CEP origem" name = "cepOrigem"></p>
				<input type= "text" value = "Número origem" name="numeroOrigem"></p>
				
				<label> Cep e Número local de destino *:</label>
				<input type= "text" value = "CEP destino" name = "cepDestino"></p>
				<input type= "text" value = "Número" name="numeroDestino"></p>
				
				<label>Veiculo a ser usado na carona *:</label>				
				<select name="veiculoEscolhido">
				
				<%
					Veiculo aux = new Veiculo();
					ArrayList<String> meusVeiculos = aux.veiculosDeUmDono(emailUsuario);
					
					
					for(int contador = 0; contador < meusVeiculos.size();contador = contador+2){%>
					
					<option><%=meusVeiculos.get(contador) %><%="   " %><%=meusVeiculos.get(contador+1)%></option>
					
				<%	
					}				
				%>				
				</select>				
				<label>Lembre-se que a quantidade de vagas irá variar com o veiculo escolhido.</label>
				
				
				<input type= "reset" value="limpar"> 
				<input type= "submit" value="Enviar"> 	
				
		</td>
	</tr>
	</form>
	</table>

</body>
</html>
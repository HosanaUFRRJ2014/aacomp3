<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="dominio.*" import="java.util.ArrayList"
    
    %>
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

<%-- 	<% --%>
		
<!-- // 		
// 		
	 -->
<%-- 	%> --%>

	<h1 align = center>
	Mudar é sempre bom! <br> <br>
	<b>		Selecione um veículo para alterar		</b>
	</h1>
	
	<%
	   
// 	Usuario novoUsuario = (Usuario)request.getAttribute("novoUsuario");
// 	request.setAttribute("novoUsuario", novoUsuario);
    Motorista motorista = (Motorista) request.getAttribute("motorista");
    request.setAttribute("motorista", motorista);
    ArrayList<Veiculo> veiculos = new ArrayList<Veiculo>(); 
	veiculos = motorista.getVeiculos(); //em algum momento, vai precisar pegar isso do banco.
	
	%>
	


	<table  align=center width =600 border=1 bgcolor="white" bordercolor= "black" borderstyle="solid"> 
	<form method="post" action="./alterarVeiculo.jsp">
	<tr>
		<td width=50 align=center>
			
<!-- 			    Não exite necessidade desse campo caso a sessão salve o email do usuário logado -->
<!-- 				<label> Informe seu email cadastrado na nossa base *:(Esse campo não pode ser alterado, apenas informado)</label> -->
<!-- 				<input type= "text" name = "emailUsuario"></p>				 -->

            
				
				<select>
				<%for(Veiculo v : veiculos){%> 
 						<option value="veiculo"><%=v.getModelo() + " " + v.getCor() %></option> <!-- Exibe nomes dos grupos vindos do banco -->
  				<%} %>
				</select>

                <br>
				<input type= "reset" value="limpar"> 
				<input type= "submit" value="Enviar"> 
				
		
	
	
		</td>
	</tr>
	</form>
	</table>

</body>
</html>
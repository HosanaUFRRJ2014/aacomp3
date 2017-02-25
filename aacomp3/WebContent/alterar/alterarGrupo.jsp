<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="dominio.Usuario" import="java.util.LinkedList" import="dominio.Grupo"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Caronae - Página Inicial</title>
<style>
body 
{
  background: url(../carona6.png) no-repeat white;
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
	   
	Usuario novoUsuario = (Usuario)session.getAttribute("novoUsuario");	
	
	
	%>
	
	

	
	<h1 align = center>
	Algo errado? <br> <br>
	<b>		Altere as informações do grupo escolhido		</b>
	</h1>
	


	<table  align=center width =600 border=1 bgcolor="white" bordercolor= "black" borderstyle="solid"> 
	<form method="post" action="../Grupo">
	<tr>
		<td width=50 >		
				
				
                <br>

                <br>Caso não deseje alterar um dos campos, apenas deixe-o em branco.
				<br><br>
				<label> Informe um novo nome para o grupo:</label>
				<input type= "text" name = "novoNome"></p>
				
				<label> Informe um nova descricao para o grupo:</label>
				<input type= "text" name = "novaDescricao"></p>
				
				<label> Informe um novo limite mínimo de avaliações ruins:</label>
				<input type= "number" name = "novoLimMin"></p>
				
				<input type= "text" name = "opcao" value="alterarGrupo" hidden></p>

				
	
				
				<input type= "reset" value="limpar"> 
				<input type= "submit" value="Enviar"> 
				
		
	
	
		</td>
	</tr>
	</form>
	</table>

</body>
</html>
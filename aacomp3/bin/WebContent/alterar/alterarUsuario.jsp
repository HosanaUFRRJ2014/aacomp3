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
		String nome = novoUsuario.getNome();
	%>

	<h1 align = center>
	Algo errado <%=nome %> ? <br> <br>
	<b>		Altere seu cadastro		</b>
	</h1>
	
	

	<table  align=center width =600 border=1 bgcolor="white" bordercolor= "black" borderstyle="solid"> 
	<form method="post" action="../Usuario">
	<tr>
		<td width=50 >		

                <br>Campos deixados em branco não serão alterados.
				<br><br>
				<label> Informe um novo nome de usuário:</label>
				<input type= "text" name = "novoNomeUsuario"></p>
				
				<label> Informe um novo telefone para contato:</label>
				<input type= "text" name = "novoTelefoneUsuario"></P>
				
                <input type= "text" name = "opcao" value="alterarUsuario" hidden></p>

				<input type= "reset" value="limpar"> 
				<input type= "submit" value="Enviar"> 
				
		
	
	
		</td>
	</tr>
	</form>
	</table>

</body>
</html>
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

.btn-group .button {
    background-color: black;
    border: 1px solid green;
    color: white;
    padding: 15px 32px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    cursor: pointer;
    width: 150px;
    display: block;
}

.btn-group .button:not(:last-child) {
    border-bottom: none; /* Prevent double borders */
}

.btn-group .button:hover {
    background-color: #3e8e41;
}

</style>
</head>
<body>
	


	<%			
		Usuario novoUsuario = (Usuario) session.getAttribute("novoUsuario");
		String nome = novoUsuario.getNome();
	%>
	
	<h1 align = center>	Bem vindo <%=nome %>!</h1>	

	<table  align=center width =600 border=1 bgcolor="white" bordercolor= "black" borderstyle="solid"> 	
	
	<tr>
	<td width=50 align=center>							
				<br>
		<label>Escolha uma das opções abaixo:</label>
		<br>
		<br>
		<br>
			
			<div class="btn-group" >
  				<a href="./criar/criarVeiculo.jsp" class="button">Cadastrar um Veículo</a>
  				<a href="./alterar/alterarUsuario.jsp" class="button">Alterar Informações de Usuário</a>
  				
				<a href="./criar/criarGrupo.jsp" class="button">Criar um novo Grupo de Caronas</a>
  				<a href="./alterar/alterarGrupo.jsp" class="button">Alterar Informações de um Grupo</a>
  				
  				<a href="selecionarVeiculo.jsp" class="button">Alterar cor de um carro</a>
  				<a href="./criar/criarCarona.jsp" class="button">Criar uma nova Carona	</a>

  				<a href="./listar/listarVeiculos.jsp" class="button">Listar Meus Veículos (Apenas para Motoristas)</a>
<!--   				<a href="./alterar/alterarVeiculo.jsp" class="button">Alterar Cor de um Veículo</a> -->
  				<a href="listar/listarGrupos.jsp" class="button">Listar Grupos que Faço Parte</a>
  				<a href="setarendereco" class="button">Button</a>

  				<a href="setarendereco" class="button">Button</a>
			</div>
			
			
		
		</td>
	</tr>
	
	</table>

</body>
</html>
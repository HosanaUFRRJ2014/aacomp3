<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

	<h1 align = center><b>		CRIAR GRUPO		</b></h1>

	<table  align=center width =600 border=1 bgcolor="white" bordercolor= "black" borderstyle="solid"> 
<<<<<<< HEAD
	<form>
	<tr>
		<td width=50 >
			
								
				<br>
				<label> Nome *:</label>
				<input type= "text" name = "nomeGrupo"></p>
				<label> Descrição *:</label>
				<input type= "text" name = "descricaoGrupo"></p>
				<label> Regras *:</label>
				<input type= "text" name = "regrasGrupo"></p>
				<label> Limite de avaliações ruins que um usuário pode receber: (Opcional)</label>
				<input type= "text" name = "limiteAvalRuinsGrupo"></p>
=======
	<form method="post" action="../CtrCriarGrupo">
	<tr>
		<td width=50 >
			
								
				<br>
				<label> Digite seu email já cadastrado no sistema *:</label>
				<input type= "text" name = "emailDonoGrupo"></p>
				<label> Nome *:</label>
				<input type= "text" name = "nomeGrupo"></p>
				<label> Descrição *:</label>
				<input type= "text" name = "descricaoGrupo"></p>
				<label> Regras *:</label>
				<input type= "text" name = "regrasGrupo"></p>
				<label> Limite de avaliações ruins que um usuário pode receber: (Opcional)</label>
				<input type= "number" name = "limiteAvalRuinsGrupo"></p>
>>>>>>> refs/heads/hosana
				
<!-- 			    Adicionar email do cara que está criando o grupo. Pré-requisito: email/usuario já cadasttrado no sistema. -->
				
				
				
<!-- 				<p><label> botoes </label></p> -->
<!-- 				<input type= "button" value="botao1">  -->
				
				<input type= "reset" value="limpar"> 
				<input type= "submit" value="Enviar"> 
		
	
	
		</td>
	</tr>
	</form>
	</table>

</body>
</html>
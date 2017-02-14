<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Caronae - Página Inicial</title>
<style>
body {
	background: url(carona6.png) no-repeat white;
	background-size: cover;
}

table {
	opacity: 0.75;
	filter: alpha(opacity = 75);
	border-radius: 8px;
}

td {
	padding: 20px 0px 20px 20px;
	/*margem sentido horario acima direita abaixo esquerda*/
	background: linear-gradient(white, black);
	border-radius: 8px;
	border: black;
}
</style>
</head>
<body>
	<h1 align=center>O email informado não está cadastrado ou está incorreto.</h1>
	<table align=center width=600 border=1 bgcolor="white"
		bordercolor="black" borderstyle="solid">
		<tr>
			<td width=50 >
			
				<form>
					<label>Volte e tente novamente.</label>
					<input type="button" value="Retornar" onClick="history.go(-1)">
				</form>
				
				<label>Não está cadastrado?</label>
				<form action="./criar/criarUsuario.jsp" method="post">
					<input type="submit" value="Cadastre-se">
				</form>
			
			</td>
		</tr>
			
	</table>

</body>
</html>
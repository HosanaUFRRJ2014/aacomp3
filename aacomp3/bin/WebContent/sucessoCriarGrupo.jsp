<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="dominio.Usuario" import="dominio.Grupo"%>

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
	<%
		Usuario novoUsuario = (Usuario) session.getAttribute("novoUsuario");
		String nome = novoUsuario.getNome();

		Grupo novoGrupo = (Grupo) session.getAttribute("novoGrupo");
		String nomeGrupo = novoGrupo.getNome();
		String descricaoGrupo = novoGrupo.getDescricao();
		String regrasGrupo = novoGrupo.getRegras();
		int limiteGrupo = novoGrupo.getLimMinAvaliacoesRuins();
	%>

	<h1 align=center>
		Cadastro do seu Grupo
		<%=nomeGrupo%>
		foi realizado com sucesso
		<%=nome%>!
	</h1>

	<table align=center width=600 border=1 bgcolor="white"
		bordercolor="black" borderstyle="solid">


		<tr>
			<td width=50><br> <label>A descricao que aparecerá
					para os usuários será</label> <br> <textarea name="regrasGrupo"
					rows=10 cols=20 disabled><%=descricaoGrupo%></textarea> <br>
			<br> <label>As regras do grupo são</label><br> <textarea
					name="regrasGrupo" rows=10 cols=20 disabled><%=regrasGrupo%></textarea>
				<br>
			<br>

				<div style="color:#FFFFFF; background-color:green" align=center>
					<label>Se qualquer usuario do grupo receber <%=limiteGrupo%>
						avaliações ruins ficará inativo no grupo <%=nomeGrupo%></label><br>

					<label>Deseja convidar pessoas para seu grupo?</label>
					<form action="convidarAmigo.jsp" method="post">

						<input type="submit" value="Sim">
					</form>
				    <br>
				    
					<form action="pagInicial.jsp" method="post">
						<input type="submit" value="Nao">
				</div>
				</form></td>
		</tr>

	</table>

</body>
</html>
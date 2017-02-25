<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="dominio.*" %>
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
   		Usuario novoUsuario = (Usuario)session.getAttribute("novoUsuario");
   
   		String escolhido = request.getParameter("grupoEscolhido");
   		String [] info = escolhido.split("/");	
   		
		Grupo grupoEscolhido = new Grupo();
		
		grupoEscolhido.recuperaID(info[0],info[1]);		
		grupoEscolhido.recuperaGrupo(grupoEscolhido.getId());
		session.setAttribute("grupoEscolhido", grupoEscolhido);
   
   %>
<h1 align = center><b>		Exibindo Grupo <%=grupoEscolhido.getNome() %>	</b></h1>

	<table  align=center width =600 border=1 bgcolor="white" bordercolor= "black" borderstyle="solid"> 
	<tr>
		<td width=50 >
			
								
				<br>
				<label> Nome :</label>
				<%= grupoEscolhido.getNome() %>  <br>
				
				<label> Descrição :</label> 
				<%= grupoEscolhido.getDescricao() %> <br>
				
				<label> Regras :</label> 
				<%=grupoEscolhido.getRegras() %> <br><br>
				
				<label> Limite de avaliações ruins que um usuário pode receber(Opcional)  :</label>
				<%=grupoEscolhido.getLimMinAvaliacoesRuins() %><br>
				


            <div class="btn-group"  align="center">
  				<a href="../alterar/alterarGrupo.jsp" class="button">Alterar informações desse grupo</a>  				
			</div>
			
			<br><br>
				
			<div class="btn-group" align="center">
  				<a href="../listar/listarUsuarios.jsp" class="button">Mostrar usuários desse grupo</a>
  				
			</div>
			
			<br> <br>
			
			<div class="btn-group"  align="center">
  				<a href="../listar/listarCaronas.jsp" class="button">Listar caronas criadas</a>  				
			</div>
			
			<br> <br>
			
			<div class="btn-group"  align="center">
  				<a href="../criar/criarCarona.jsp" class="button">Criar uma carona (Apenas para motoristas)</a>  				
			</div>
		
	
	
		</td>
	</tr>
	</table>

	
</body>
</html>
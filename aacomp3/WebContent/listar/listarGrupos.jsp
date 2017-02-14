<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="dominio.*" import="java.util.LinkedList"%>

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
// 		Usuario novoUsuario = (Usuario) session.getAttribute("novoUsuario");
// 		String nome = novoUsuario.getNome();

        LinkedList<Grupo> grupos = new LinkedList<Grupo>();
        Usuario usuario = new Usuario(); //na verdade, ele vem da session
        

	%>
	
	<h1 align = center>	Grupos que você faz parte!!</h1>	

	<table  align=center width =600 border=1 bgcolor="white" bordercolor= "black" borderstyle="solid"> 	
	
	<tr>
	<td width=50>							
				<br>
		<label>Escolha uma dos grupos para ver mais detalhes:</label>
		<br>
		<br>
		<br>
			
		<form action="../exibir/exibirGrupo.jsp" method="post">
		<p>Exibindo os grupos que você está ativo:</p>
		<%for(int i = 0; i < usuario.getGruposQueUsuarioEstaAtivo().size(); i++) {%>
		
  			<input type="radio" name="grupo" value="grupo<%=i%>"><%=usuario.getGruposQueUsuarioEstaAtivo().get(i).getNome() %><br>
  		
  			
  	   <% }%>
  	   
  	   <p>Agora, exibindo os grupos que você está inativo:</p>
		<%for(int i = 0; i < usuario.getGruposQueUsuarioEstaInativo().size(); i++) {%>

  			<input type="radio" name="grupo" value="grupo<%=i%>"><%=usuario.getGruposQueUsuarioEstaInativo().get(i).getNome() %><br>
  	
  			
  	   <% }%>
  	   
  	        <input type= "reset" value="limpar"> 
	        <input type= "submit" value="Enviar"> 
		</form> 	
			
			
		
		</td>
	</tr>
	
	</table>

</body>
</html>
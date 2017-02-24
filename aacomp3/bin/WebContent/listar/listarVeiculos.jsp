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

        LinkedList<Veiculo> Veiculos = new LinkedList<Veiculo>();
        Motorista motorista = new Motorista(); //na verdade, ele vem da session
        

	%>
	
	<h1 align = center>	Veiculos que você faz parte!!</h1>	

	<table  align=center width =600 border=1 bgcolor="white" bordercolor= "black" borderstyle="solid"> 	
	
	<tr>
	<td width=50>							
				<br>
		<label>Escolha uma dos Veiculos para ver mais detalhes:</label>
		<br>
		<br>
		<br>
			
		<form action="../exibir/exibirVeiculo.jsp" method="post">
		<p>Listando os seus Veiculos por nome e cor:</p>
		<%for(int i = 0; i < motorista.getVeiculos().size(); i++) {%>
		
  			<input type="radio" name="Veiculo" value="veiculo<%=i%>"><%=motorista.getVeiculos().get(i).getModelo() %>   <%=motorista.getVeiculos().get(i).getCor() %><br>
  		
  			
  	   <% }%>
  	   
  	   
  	        <input type= "reset" value="limpar"> 
	        <input type= "submit" value="Enviar"> 
		</form> 	
			
			
		
		</td>
	</tr>
	
	</table>

</body>
</html>
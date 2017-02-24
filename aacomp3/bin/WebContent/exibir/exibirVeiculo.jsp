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
   
        Veiculo veiculo = new Veiculo("Modelo de página", "Placa placa", "A cor do problema será preta se não trocar isso por pegar as infomações do banco", 3);
   
   %>

	<h1 align = center><b>		EXIBINDO INFORMAÇÕES DESTE VEÍCULO		</b></h1>

	<table  align=center width =600 border=1 bgcolor="white" bordercolor= "black" borderstyle="solid"> 
	<tr>
		<td width=50 >
			
								
				<br>
				<label> Modelo :</label>
				<%= veiculo.getModelo() %>  <br>
				<label> Placa :</label> 
				<%= veiculo.getPlaca() %> <br>
				<label> Cor :</label> 
				<%=veiculo.getCor() %> <br><br>
				<label> Número de vagas: (Número de assentos - 1) </label>
				<%=veiculo.getNumeroVagas() %></p>
				
<!-- 			    Adicionar email do cara que está criando o Veiculo. Pré-requisito: email/usuario já cadasttrado no sistema. -->
				
				
				
<!-- 				<p><label> botoes </label></p> -->
<!-- 				<input type= "button" value="botao1">  -->

            <div class="btn-group"  align="center">
  				<a href="../alterar/alterarVeiculo.jsp" class="button">Alterar informações desse Veiculo</a>
  				
			</div>
			
		
	
	
		</td>
	</tr>
	</table>

</body>
</html>
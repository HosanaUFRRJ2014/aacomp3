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
table
{
	opacity: 0.75; 
	filter: alpha(opacity=75);
	border-radius: 8px;
}

td{padding: 20px 0px 20px 20px; background:linear-gradient(white, black);
border-radius: 8px;} border:black}; <!-- margem sentido horario acima direita abaixo esquerda -->

</style>
</head>
<body>

	<h1 align = center><b>		TITULO		</b></h1>

	<table  align=center width =600 border=1 bgcolor="white" bordercolor= "black" borderstyle="solid"> 
	<form>
	<tr>
		<td width=50 >
			
								
				<br>
				<label> label1</label>
				<input type= "text" name = "txt1"></p>
				<label> label2</label>
				<input type= "text" name = "txt2"></p>
				<label> label3</label>
				<input type= "text" name = "txt3"></p>
				
				<br><br>
				<p><label> botoes </label></p>
				<input type= "radio" name = "rd" value= "radio1"> radio 1
				<input type= "radio" name = "rd" value= "radio2"> radio 2
				<input type= "radio" name = "rd" value= "radio3"> radio 3
				
				
				<br><br>
				<p><label> checkbox </label></p>
				<input type= "checkbox" name = "cbx1"> check 1
				<input type= "checkbox" name = "cbx2"> check 2
				
				<br><br><br>
				
				<select name="valores">
					<option value = "valor1"> valor 1
					<option value = "valor2"> valor 2
					<option value = "valor3"> valor 3
					<option value = "valor4"> valor 4
				</select>
				
				<br><br><br>
				
				<p><label> botoes </label></p>
				<input type= "button" value="botao1"> 
				
				<input type= "submit" value="Enviar"> 
				<input type= "reset" value="limpar"> 
		
	
	
		</td>
	</tr>
	</form>
	</table>

</body>
</html>
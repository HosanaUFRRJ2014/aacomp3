<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dominio.Usuario" %>
<%@ page import= "java.util.*" %>
<%@ page import= "java.text.SimpleDateFormat" %>
<%@ page import="dominio.Veiculo" %>
<%@ page import="dto.VeiculoDTO" %>
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

<!-- Adicionando Javascript -->
    <script type="text/javascript" >
    
    function limpa_formulário_cep() {
            //Limpa valores do formulário de cep.
            document.getElementById('rua').value=("");
            document.getElementById('bairro').value=("");
            document.getElementById('cidade').value=("");
            document.getElementById('uf').value=("");
            document.getElementById('ibge').value=("");
    }

    function meu_callback(conteudo) {
        if (!("erro" in conteudo)) {
            //Atualiza os campos com os valores.
            document.getElementById('rua').value=(conteudo.logradouro);
            document.getElementById('bairro').value=(conteudo.bairro);
            document.getElementById('cidade').value=(conteudo.localidade);
            document.getElementById('uf').value=(conteudo.uf);
            document.getElementById('ibge').value=(conteudo.ibge);
        } //end if.
        else {
            //CEP não Encontrado.
            limpa_formulário_cep();
            alert("CEP não encontrado.");
        }
    }
        
    function pesquisacep(valor) {

        //Nova variável "cep" somente com dígitos.
        var cep = valor.replace(/\D/g, '');

        //Verifica se campo cep possui valor informado.
        if (cep != "") {

            //Expressão regular para validar o CEP.
            var validacep = /^[0-9]{8}$/;

            //Valida o formato do CEP.
            if(validacep.test(cep)) {

                //Preenche os campos com "..." enquanto consulta webservice.
                document.getElementById('rua').value="...";
                document.getElementById('bairro').value="...";
                document.getElementById('cidade').value="...";
                document.getElementById('uf').value="...";
                document.getElementById('ibge').value="...";

                //Cria um elemento javascript.
                var script = document.createElement('script');

                //Sincroniza com o callback.
                script.src = '//viacep.com.br/ws/'+ cep + '/json/?callback=meu_callback';

                //Insere script no documento e carrega o conteúdo.
                document.body.appendChild(script);

            } //end if.
            else {
                //cep é inválido.
                limpa_formulário_cep();
                alert("Formato de CEP inválido.");
            }
        } //end if.
        else {
            //cep sem valor, limpa formulário.
            limpa_formulário_cep();
        }
    };

    </script>
	
	
	
	
	
	
	<!-- script campo 2 -->
	 <!-- Adicionando Javascript -->
    <script type="text/javascript" >
    
    function limpa_formulário_cep2() {
            //Limpa valores do formulário de cep.
            document.getElementById('rua2').value=("");
            document.getElementById('bairro2').value=("");
            document.getElementById('cidade2').value=("");
            document.getElementById('uf2').value=("");
            document.getElementById('ibge2').value=("");
    }

    function meu_callback2(conteudo) {
        if (!("erro" in conteudo)) {
            //Atualiza os campos com os valores.
            document.getElementById('rua2').value=(conteudo.logradouro);
            document.getElementById('bairro2').value=(conteudo.bairro);
            document.getElementById('cidade2').value=(conteudo.localidade);
            document.getElementById('uf2').value=(conteudo.uf);
            document.getElementById('ibge2').value=(conteudo.ibge);
        } //end if.
        else {
            //CEP não Encontrado.
            limpa_formulário_cep2();
            alert("CEP não encontrado.");
        }
    }
        
    function pesquisacep2(valor) {

        //Nova variável "cep" somente com dígitos.
        var cep = valor.replace(/\D/g, '');

        //Verifica se campo cep possui valor informado.
        if (cep != "") {

            //Expressão regular para validar o CEP.
            var validacep = /^[0-9]{8}$/;

            //Valida o formato do CEP.
            if(validacep.test(cep)) {

                //Preenche os campos com "..." enquanto consulta webservice.
                document.getElementById('rua2').value="...";
                document.getElementById('bairro2').value="...";
                document.getElementById('cidade2').value="...";
                document.getElementById('uf2').value="...";
                document.getElementById('ibge2').value="...";

                //Cria um elemento javascript.
                var script = document.createElement('script');

                //Sincroniza com o callback.
                script.src = '//viacep.com.br/ws/'+ cep + '/json/?callback=meu_callback2';

                //Insere script no documento e carrega o conteúdo.
                document.body.appendChild(script);

            } //end if.
            else {
                //cep é inválido.
                limpa_formulário_cep2();
                alert("Formato de CEP inválido.");
            }
        } //end if.
        else {
            //cep sem valor, limpa formulário.
            limpa_formulário_cep();
        }
    };

    </script>

</head>
<body>	

	<h1 align = center>	<br> <br> </h1>

	<table  align=center width =600 border=1 bgcolor="white" bordercolor= "black" borderstyle="solid"> 
	
	<form method="post" action="../Carona">
	<tr>
		<td width=50 >
								
				<br>
				
				<label> Data da Carona *:</label><br>
				<label>Favor seguir o exemplo contido no campo</label><br>
				<input type= "text" value ="00/00" name = "diaCarona"><br>
				
				<label> Hora de Saída *:</label><br />
				<label>Favor seguir o exemplo contido no campo</label><br />
				<input type= "text" value= "00:00:00" name = "horaSaida"><br>
				
				<label>Digite CEP e o número do local de origem da carona e clique em um dos campos para autocompletar</label><br />
		      	
		      	<label>Número:
		      	<input name="numeroOrigem" type="text"></label><br />
		        <label>Cep:
		        <input name="cepOrigem" type="text" id="cep" value="" size="10" maxlength="9"
		               onblur="pesquisacep(this.value);" /></label><br>
		        <label>Rua:
		        <input name="ruaOrigem" type="text" id="rua" size="60" /></label><br>
		        <label>Bairro:
		        <input name="bairroOrigem" type="text" id="bairro" size="40" /></label><br>
		        <label>Cidade:
		        <input name="cidadeOrigem" type="text" id="cidade" size="40" /></label><br>
		        <label>Estado:
		        <input name="ufOrigem" type="text" id="uf" size="2" /></label><br>
		        <label>IBGE:
		        <input name="ibgeOrigem" type="text" id="ibge" size="8" /></label><br>				
				
				<label>Digite CEP e o número do local de destino da carona e clique em um dos campos para autocompletar</label><br />
		      	
		      	<label>Número:
		      	<input type="text" name="numeroDestino"></label><br>
		      	
		        <label>Cep:
		        <input name="cepDestino" type="text" id="cep2" value="" size="10" maxlength="9"
		               onblur="pesquisacep2(this.value);" /></label><br>
		        <label>Rua:
		        <input name="ruaDestino" type="text" id="rua2" size="60" /></label><br>
		        <label>Bairro:
		        <input name="bairroDestino" type="text" id="bairro2" size="40" /></label><br>
		        <label>Cidade:
		        <input name="cidadeDestino" type="text" id="cidade2" size="40" /></label><br>
		        <label>Estado:
		        <input name="ufDestino" type="text" id="uf2" size="2" /></label><br>
		        <label>IBGE:
		        <input name="ibgeDestino" type="text" id="ibge2" size="8" /></label><br>
												
				<label>Veiculo a ser usado na carona *:</label><br>				
				<select name="veiculoEscolhido">
				
				<%
					Usuario novoUsuario = (Usuario)session.getAttribute("novoUsuario");
					String emailUsuario = novoUsuario.getEmail();
					
					Veiculo aux = new Veiculo();
					ArrayList<Veiculo> meusVeiculos = aux.veiculosDeUmDono(emailUsuario);
					
					
					for(Veiculo meuVeiculo : meusVeiculos){%>
					
					<option><%=meuVeiculo.getModelo() %><%="/"%><%=meuVeiculo.getPlaca() %></option>
					
				<%	
					}				
				%>				
				</select>				
				<label>Lembre-se que a quantidade de vagas irá variar com o veiculo escolhido.</label><br />
				
				<input type= "text" name = "opcao" value="criarCarona" hidden><br>
				
				<input type= "reset" value="limpar"><br>
				<input type= "submit" value="Enviar"> 	<br><br>
				
		</td>
	</tr>
	</form>
	</table>

</body>
</html>
package dominio;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import excecoes.NaoEhMotoristaException;

@WebServlet("/Motorista")
public class Motorista extends Usuario {


	private ArrayList<Veiculo> veiculos;


	//apenas para facilitar na jsp
	public Motorista()
	{

	}

	public Motorista(String nome, String email, String telefone, Veiculo veiculo) {
		super(nome, email, telefone);
		veiculos = new ArrayList<Veiculo>();
		veiculos.add(veiculo);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String opcao = request.getParameter("opcao");

		if(opcao.equals("verificarMotorista"))
		{
			this.verificarMotorista(request, response);	
		}

	}

	/**
	 * Para usar dentro do doPost
	 * @param request 
	 * @param response 
	 * @throws IOException 
	 * @throws ServletException 
	 * **/

	public void verificarMotorista(ServletRequest request, ServletResponse response) throws ServletException, IOException
	{


		Usuario novoUsuario = (Usuario) request.getAttribute("novoUsuario");
		request.setAttribute("novoUsuario", novoUsuario);

		try {
			if(novoUsuario.ehMotorista(novoUsuario.getEmail())){

				RequestDispatcher rdSucesso = request.getRequestDispatcher("./criar/criarCarona.jsp");
				rdSucesso.forward(request, response);				
			}
			else{
				throw new NaoEhMotoristaException();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NaoEhMotoristaException e) {
			RequestDispatcher rdErro = request.getRequestDispatcher("./excecoes/naoMotorista.jsp");
			rdErro.forward(request, response);
			e.printStackTrace();
		}

	}

	public boolean addVeiculo(Veiculo veiculo)
	{
		return veiculos.add(veiculo);
	}

	public void criarCarona(Veiculo veiculo, Date dia, Time horarioSaida, Logradouro logrOrigem, Logradouro logrDestino)
	{
		Carona carona = new Carona(veiculo, dia, horarioSaida, logrOrigem, logrDestino);
		veiculo.addCarona(carona);

		//comunicao como banco de dados.  
		carona.armazenar();

	}

	public boolean alterarVeiculoCarona(Carona carona, Veiculo veiculo)
	{
		boolean alterado = carona.alterarVeiculo(veiculo);

		if(alterado == true)
		{
			//comunicao como banco de dados. 
			carona.alterar();
		}

		return alterado;

	}

	public void cancelarCarona(Carona carona)
	{
		carona.setCancelada(true);	
	}

	public ArrayList<Veiculo> getVeiculos() {
		return veiculos;
	}





}

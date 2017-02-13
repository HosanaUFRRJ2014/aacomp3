package controladores;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.Veiculo;
import excecoes.CampoInvalidoException;

/**
 * Servlet implementation class CtrAltVeiculo
 */
@WebServlet("/CtrAltVeiculo")
public class CtrAltVeiculo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CtrAltVeiculo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String novaCor = request.getParameter("novaCorVeiculo");
		
		//salvar na session em colocar dentro daqui.
		Veiculo novoVeiculo = new Veiculo(modelo, placa, cor, numVagas);

		try
		{
			if(novaCor.equals(""))
				throw new CampoInvalidoException();
			
			else
			{
				novoVeiculo.alterar(novaCor);
			}
		}
		catch(CampoInvalidoException e){	
			RequestDispatcher rdErro = request.getRequestDispatcher("./excecoes/campoInvalido.jsp");
			rdErro.forward(request, response);
		}
	}

}

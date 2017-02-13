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
 * Servlet implementation class CtrCriarVeiculo
 */
@WebServlet("/CtrCriarVeiculo")
public class CtrCriarVeiculo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CtrCriarVeiculo() {
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
		String modelo = request.getParameter("modeloVeiculo");
		String placa = request.getParameter("placaVeiculo");
		String cor = request.getParameter("corVeiculo");
		String numVagas = request.getParameter("numVagas");

		Veiculo novoVeiculo = new Veiculo(modelo,placa,cor,Integer.parseInt(numVagas));

		try{

			if(modelo.equals("") || placa.equals("") || cor.equals("") || numVagas.equals("")){
				throw new CampoInvalidoException();
			}			
			else{				
				try {
					novoVeiculo.armazenar(emailDono);
					
					// Esse resquest dispatcher vai para a tela de Sucesso para usuario criar ou n�o um veiculo
					request.setAttribute("novoUsuario", novoUsuario);
					RequestDispatcher rdSucesso = request.getRequestDispatcher("./sucessoCadastro.jsp");
					rdSucesso.forward(request,response);
					
				} catch (ClassNotFoundException e) {
					// eclipse me OBRIGOU a criar esse Try/Catch
					e.printStackTrace();
				}
			}

			}catch(CampoInvalidoException e){	
				RequestDispatcher rdErro = request.getRequestDispatcher("./excecoes/campoInvalido.jsp");
				rdErro.forward(request, response);
			}
		
			
			
// Esse resquest dispatcher vai para a tela de Sucesso para usuario criar ou n�o um veiculo
//			request.setAttribute("novoUsuario", novoUsuario);
//			RequestDispatcher rdSucesso = request.getRequestDispatcher("/sucessoCadastro");
//			rdSucesso.forward(request,response);
			

		}

	}

}

package controladores;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.Usuario;
import execoes.NaoEhMotoristaException;

/**
 * Servlet implementation class CtrVerificaMotorista
 */
@WebServlet("/CtrVerificaMotorista")
public class CtrVerificaMotorista extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CtrVerificaMotorista() {
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
		//controlador feito para impedir usuarios que não são motoristas de criar caronas
		
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
			RequestDispatcher rdErro = request.getRequestDispatcher("./execoes/naoMotorista.jsp");
			rdErro.forward(request, response);
			e.printStackTrace();
		}
		
		
	}

}

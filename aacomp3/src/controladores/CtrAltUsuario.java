package controladores;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.Usuario;
import excecoes.CampoInvalidoException;

/**
 * Servlet implementation class AtrUsuario
 */
@WebServlet(name = "CtrAltUsuario", urlPatterns = { "/CtrAltUsuario" })
public class CtrAltUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CtrAltUsuario() {
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
		String novoNome = request.getParameter("nomeUsuario");
		String novoTelefone = request.getParameter("telefoneUsuario");
	    Usuario usuarioExistente = (Usuario)request.getAttribute("novoUsuario");

		//Usuario novoUsuario = new Usuario(novoNome, usuarioExistente.getEmail(), novoTelefone);

		try{

			if(novoNome.equals("") && novoTelefone.equals("")){
				throw new CampoInvalidoException();
			}			
			else{				
				usuarioExistente.alterar(novoNome, novoTelefone);
				
				// Esse resquest dispatcher vai para a tela de Sucesso para usuario criar ou n�o um veiculo
				request.setAttribute("novoUsuario", usuarioExistente);
				RequestDispatcher rdSucesso = request.getRequestDispatcher("./sucessoAlterar.jsp");
				rdSucesso.forward(request,response);
			}

			}catch(CampoInvalidoException e){	
				RequestDispatcher rdErro = request.getRequestDispatcher("./excecoes/campoInvalido.jsp");
				rdErro.forward(request, response);
			}
		
			
//			
//// Esse resquest dispatcher vai para a tela de Sucesso para usuario criar ou n�o um veiculo
//			request.setAttribute("novoUsuario", usu);
//			RequestDispatcher rdSucesso = request.getRequestDispatcher("/sucessoCadastro");
//			rdSucesso.forward(request,response);
	}

}
package controladores;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		
		HttpSession session = request.getSession();
		
		String novoNome = request.getParameter("novoNomeUsuario");
		String novoTelefone = request.getParameter("novoTelefoneUsuario");
	    Usuario usuarioExistente = (Usuario)session.getAttribute("novoUsuario");		

		try{

			if(novoNome.equals("") && novoTelefone.equals("")){
				throw new CampoInvalidoException();
			}	
			else if(novoNome.equals("")){
				usuarioExistente.alterar(usuarioExistente.getNome(), novoTelefone);
				usuarioExistente.setTelefone(novoTelefone);
			}
			else if(novoTelefone.equals("")){
				usuarioExistente.alterar(novoNome, usuarioExistente.getTelefone());
				usuarioExistente.setNome(novoNome);
			}
			else{				
				usuarioExistente.alterar(novoNome, novoTelefone);	
				usuarioExistente.setNome(novoNome);
			}
			
			// Esse resquest dispatcher vai para a tela de Sucesso para usuario criar ou n�o um veiculo
			session.removeAttribute("novoUsuario");
			session.setAttribute("novoUsuario", usuarioExistente);
			
			RequestDispatcher rdSucesso = request.getRequestDispatcher("./sucessoAlterar.jsp");
			rdSucesso.forward(request,response);

			}catch(CampoInvalidoException e){	
				RequestDispatcher rdErro = request.getRequestDispatcher("./excecoes/campoInvalido.jsp");
				rdErro.forward(request, response);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		

	}

}

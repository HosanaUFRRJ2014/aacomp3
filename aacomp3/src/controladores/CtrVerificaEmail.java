package controladores;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.Usuario;
import execoes.CampoInvalidoException;
import execoes.EmailInvalidoException;

/**
 * Servlet implementation class CtrVerificaEmail
 */
@WebServlet("/CtrVerificaEmail")
public class CtrVerificaEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CtrVerificaEmail() {
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
		// TODO Auto-generated method stub
		String email = request.getParameter("emailUsuario");
		
		Usuario aux = new Usuario(null,null,null);
		PrintWriter out = response.getWriter();
		try {
			if(aux.verificaEmail(email)){
				
				Usuario novoUsuario = aux.montaUsuario(email);
				// enviando para JSP, sempre enviar como novoUsuario
				request.setAttribute("novoUsuario", novoUsuario);
				out.println(novoUsuario.getEmail());
				out.println(novoUsuario.getNome());
				out.println(novoUsuario.getTelefone());
			//	RequestDispatcher rdSucesso = request.getRequestDispatcher("./pagInicial.jsp");
			//	rdSucesso.forward(request, response);
				
			}
			else{
				throw new EmailInvalidoException();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(EmailInvalidoException e){
			RequestDispatcher rdErro = request.getRequestDispatcher("./emailInvalido.jsp");
			rdErro.forward(request, response);
		}
	}

}
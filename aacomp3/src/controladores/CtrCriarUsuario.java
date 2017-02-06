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
 * Servlet implementation class CtrCriarUsu
 */
@WebServlet("/CtrCriarUsuario")
public class CtrCriarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CtrCriarUsuario() {
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
				
		String nome = request.getParameter("nomeUsuario");
		String telefone = request.getParameter("telefoneUsuario");
		String email = request.getParameter("emailUsuario");
		
		try{
			
			if(nome.equals("") || telefone.equals("") || email.equals("")){
				throw new CampoInvalidoException();
				}
			}catch(CampoInvalidoException e){	
				RequestDispatcher rdErro = request.getRequestDispatcher("campoInvalido.jsp");
				rdErro.forward(request, response);
			}
		
			Usuario novoUsuario = new Usuario(nome, email, telefone);		
			
			try {
				novoUsuario.armazenar();
			} catch (ClassNotFoundException e) {
				// eclipse me OBRIGOU a criar esse Try/Catch
				e.printStackTrace();
			}
			
// Esse resquest dispatcher vai para a tela de Sucesso para usuario criar ou não um veiculo
//			RequestDispatcher rdSucesso = request.getRequestDispatcher("/PermAdicionarCozinha");
//			rdSucesso.forward(request,response);
			
			
		}
		
		
		
		
	}



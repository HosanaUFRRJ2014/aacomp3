package controladores;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dominio.Grupo;
import dominio.Usuario;
import excecoes.CampoInvalidoException;
import excecoes.UsuarioNaoEncontradoException;

/**
 * Servlet implementation class CtrAltGrupo
 */
@WebServlet("/CtrAltGrupo")
public class CtrAltGrupo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CtrAltGrupo() {
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
		
		String emailDonoGrupo = request.getParameter("emailDonoGrupo");
		String nome = request.getParameter("nomeGrupo");
		String descricao = request.getParameter("descricaoGrupo");
		String regras = request.getParameter("regrasGrupo");

		String limiteAvalRuinsGrupo = request.getParameter("limiteAvalRuinsGrupo");

		//verificando se o Usuario criador do grupo existe
		/****************************************************************************************************/
		//cria esse pseudo usuario s� para realizar busca no banco dado o email. 
		Usuario usuarioAbuscar = new Usuario();

		//Objeto que receber� o usuario cujo o email � o informado em criarGrupo.jsp, caso exista.
		Usuario donoGrupo = null;
		try 
		{
			donoGrupo = usuarioAbuscar.buscar(emailDonoGrupo);


			if(donoGrupo == null)
				throw new UsuarioNaoEncontradoException();
		}
		catch(UsuarioNaoEncontradoException e)
		{
			RequestDispatcher rdErro = request.getRequestDispatcher("./excecoes/usuarioInexistente.jsp");
			rdErro.forward(request, response);
		}

	    catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		/****************************************************************************************************/

		//verificando se todos os campos foram preenchidos 
		try{

			if(emailDonoGrupo.equals("") || nome.equals("") || descricao.equals("") || regras.equals("")){
				throw new CampoInvalidoException();
			}
		}catch(CampoInvalidoException e){	
			RequestDispatcher rdErro = request.getRequestDispatcher("./excecoes/campoInvalido.jsp");
			rdErro.forward(request, response);
		}

		Grupo novoGrupo;

		//criar grupo com limite de avalia��es ruins padr�o
		if(limiteAvalRuinsGrupo.equals(""))
		{
			novoGrupo = new Grupo(donoGrupo, nome, descricao, regras);
		}

		//criar limite de avalia��es ruins personalizado
		else 
		{
			novoGrupo = new Grupo(donoGrupo, nome, descricao, regras, Integer.parseInt(limiteAvalRuinsGrupo));
		}



		/*****************falta adicionar o usuario no grupo na camada de dados tamb�m!!!************/
		try {
			novoGrupo.armazenar();
		} catch (ClassNotFoundException e) {
			// eclipse me OBRIGOU a criar esse Try/Catch
			e.printStackTrace();
		}
	}

}

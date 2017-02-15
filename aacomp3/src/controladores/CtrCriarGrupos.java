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
import excecoes.JaExisteException;

/**
 * Servlet implementation class CtrCriarGrupos
 */
@WebServlet("/CtrCriarGrupos")
public class CtrCriarGrupos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CtrCriarGrupos() {
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
		
		Usuario recuperado = (Usuario)session.getAttribute("novoUsuario");
		
		String nomeGrupo = request.getParameter("nomeGrupo");
		String regrasGrupo = request.getParameter("regrasGrupo");
		String descricaoGrupo = request.getParameter("descricaoGrupo");
		
		
		if(nomeGrupo.equals("") || regrasGrupo.equals("") || descricaoGrupo.equals("")){
			
			RequestDispatcher rdErro = request.getRequestDispatcher("./excecoes/campoInvalido.jsp");
			rdErro.forward(request, response);
			
		}
		else{
			int limiteGrupo = 3;
			if(!request.getParameter("limiteAvalRuinsGrupo").equals("")){	
				
				limiteGrupo = Integer.parseInt(request.getParameter("limiteAvalRuinsGrupo"));				
			}		
				
				Grupo novoGrupo = new Grupo(recuperado,nomeGrupo,descricaoGrupo,regrasGrupo,limiteGrupo);
				
				try {
					
					novoGrupo.armazenar();
					novoGrupo.recuperaID();
					novoGrupo.adicionarUsuario(recuperado);
					session.setAttribute("novoGrupo", novoGrupo);
					
					RequestDispatcher rdSucesso = request.getRequestDispatcher("./sucessoCriarGrupo.jsp");
					rdSucesso.forward(request, response);
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}catch(JaExisteException e){
					
					RequestDispatcher rdErro = request.getRequestDispatcher("./excecoes/jaExiste.jsp");
					rdErro.forward(request, response);
					
				}
				
			
		}
		
		
		
		
		
		
		
	}

}

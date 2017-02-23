package controladores;

import java.io.IOException;
import java.io.PrintWriter;

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
		
		String escolhido = request.getParameter("grupoEscolhido");
		PrintWriter out = response.getWriter();
		out.println(escolhido);
		
		String [] info = escolhido.split("/");
		
		String nomeGrupo = info[0];
		String descricaoGrupo = info[1];
		
		String novoNome = request.getParameter("novoNome");
		String novaDescricao = request.getParameter("novaDescricao");
		String novoLimite = request.getParameter("novoLimMin");
		
		Usuario recuperado = (Usuario) session.getAttribute("novoUsuario");
		
		Grupo aux = new Grupo();
		aux.setDescricao(descricaoGrupo);
		aux.setNome(nomeGrupo);
		
		try 
		{
			aux.recuperaID();
			aux.recuperaGrupo(aux.getId(), recuperado);
			
			//tratando casos de campos em branco
			// todos campos em branco = erro
			if(novoNome.equals("") && novaDescricao.equals("") && novoLimite.equals("")){
				throw new CampoInvalidoException();
			}	
			//novoNome em branco
			else if(novoNome.equals("")){
				//novo limite e novo nome em branco
				if(novoLimite.equals("")){
					aux.alterar(aux.getNome(), novaDescricao, aux.getLimMinAvaliacoesRuins());
				}
				//novo nome e nova descricao em branco
				else if(novaDescricao.equals("")){
					aux.alterar(aux.getNome(),aux.getDescricao(), Integer.parseInt(novoLimite));
				}				
			}
			// nova descricao em branco
			else if(novaDescricao.equals("")){
				
				if(novoNome.equals("")){
					aux.alterar(aux.getNome(),aux.getDescricao(), Integer.parseInt(novoLimite));
				}
				
				
			}
			else if(novoLimite.equals("")){
				aux.alterar(novoNome,novaDescricao, aux.getLimMinAvaliacoesRuins());
				
			}
			else{				
				aux.alterar(novoNome, novaDescricao,Integer.parseInt(novoLimite));	
				
			}
			
			RequestDispatcher rdSucesso = request.getRequestDispatcher("./sucessoAlterar.jsp");
			rdSucesso.forward(request,response);
			
		}
		catch (ClassNotFoundException e) {
			// eclipse me OBRIGOU a criar esse Try/Catch
			e.printStackTrace();
		}
		catch(CampoInvalidoException e){
			RequestDispatcher rdErro = request.getRequestDispatcher("./excecoes/campoInvalido.jsp");
			rdErro.forward(request, response);
		}
	
	}


}

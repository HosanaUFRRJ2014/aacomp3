package controladores;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		
		String nomeGrupo = request.getParameter("nomeGrupo");
		String regrasGrupo = request.getParameter("regrasGrupo");
		String descricaoGrupo = request.getParameter("descricaoGrupo");
		
		if(!request.getParameter("limiteAvalRuinsGrupo").equals("")){
			
			int limiteGrupo = Integer.parseInt(request.getParameter("limiteAvalRuinsGrupo"));
			
		}
		
		try{
			
			Grupo
			
		}
		
		
		
	}

}

package controladores;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dominio.Usuario;
import dominio.Carona;
import dominio.Logradouro;

/**
 * Servlet implementation class CtrCriarCarona
 */
@WebServlet("/CtrCriarCarona")
public class CtrCriarCarona extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CtrCriarCarona() {
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
		String emailUsuario = recuperado.getEmail();
		
		String diaCarona = request.getParameter("diaCarona");
		String horaSaida = request.getParameter("diaCarona");
		String veiculoEscolhido = request.getParameter("veiculoEscolhido");
		
		String cepOrigem = request.getParameter("cepOrigem");
		int numeroOrigem = Integer.parseInt(request.getParameter("numeroOrigem"));
		
		Logradouro origem = new Logradouro(cepOrigem,numeroOrigem);
		
		String cepDestino = request.getParameter("cepDestino");
		int numeroDestino = Integer.parseInt(request.getParameter("numeroDestino"));
		
		Logradouro destino = new Logradouro(cepDestino,numeroDestino);
		
		
		
		//para transformar os strings recebidos em Data para o Banco
		SimpleDateFormat formata = new SimpleDateFormat("dd-mm");
		
		try {
			Date dia = (Date)formata.parse(diaCarona);
			Carona novaCarona = new Carona(null, dia, null, origem, destino);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}

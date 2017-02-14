package controladores;

import java.io.IOException;
import java.sql.Date;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dominio.Usuario;
import dominio.Veiculo;
import projetoDAO.CaronaDAO;
import dominio.Carona;
import dominio.Logradouro;
import dominio.Motorista;

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
		
		
		String diaCarona = request.getParameter("diaCarona");
		String horaSaida = request.getParameter("horaSaida");
		String veiculoEscolhido = request.getParameter("veiculoEscolhido");
		
		//pega informações do CEP do logradouro Origem
		String cepOrigem = request.getParameter("cepOrigem");
		String estadoOrigem = request.getParameter("ufOrigem");
		String cidadeOrigem = request.getParameter("cidadeOrigem");
		String distritoOrigem = request.getParameter("bairroOrigem");
		String enderecoOrigem = request.getParameter("ruaOrigem");		
		String numero1 = request.getParameter("numeroOrigem");
		int numeroOrigem = Integer.parseInt(numero1);
		
		Logradouro origem = new Logradouro(cepOrigem,estadoOrigem,cidadeOrigem,distritoOrigem,enderecoOrigem, numeroOrigem);
		
		//pega informações do CEP do logradouro destino
		String cepDestino = request.getParameter("cepDestino");
		String estadoDestino = request.getParameter("ufDestino");
		String cidadeDestino = request.getParameter("cidadeDestino");
		String distritoDestino = request.getParameter("bairroDestino");
		String enderecoDestino = request.getParameter("ruaDestino");	
		String numero2 = request.getParameter("numeroDestino");
		int numeroDestino = Integer.parseInt(numero2);
		
		Logradouro destino = new Logradouro(cepDestino,estadoDestino,cidadeDestino,distritoDestino,enderecoDestino, numeroDestino);
		
		
		
		//para transformar os strings recebidos em Data para o Banco
		DateFormat formata = new SimpleDateFormat("dd/mm");
		
		try {
			java.util.Date dia = formata.parse(diaCarona);
			Date diaSQL = new Date(dia.getTime());
			Time hora = Time.valueOf(horaSaida);
			
			//verifica se logradouro origem já existe, se sim seta o ID do logradouro para podermos colocar ele na ParadaOrigem
			if(origem.verifica()){
				origem.recuperaID();
			}
			//logradourou não existe ainda, deve ser armazenado
			else{
				origem.armazena();
				origem.recuperaID();
			}
			
			//verifica se logradouro destino já existe
			if(destino.verifica()){
				destino.recuperaID();
			}
			//logradourou não existe ainda, deve ser armazenado
			else{
				origem.armazena();
				origem.recuperaID();
			}
			
			String[] infoVeiculo = veiculoEscolhido.split("/");
			Veiculo aux = new Veiculo();
			Motorista motorista = recuperado.virarMotorista(aux);
			aux.recuperaID(infoVeiculo[0], infoVeiculo[1]);
			
			Veiculo montado = aux.recuperaCarro(aux.getID());
			montado.setMotorista(motorista);
					
			
			//finalmente adicionar carona
			
			Carona novaCarona = new Carona(montado,diaSQL, hora, origem, destino);
			novaCarona.armazena();
			
		} catch (ParseException e) {
			RequestDispatcher rdErro = request.getRequestDispatcher("./excecoes/campoInvalido.jsp");
			rdErro.forward(request, response);
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}

package dominio;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import excecoes.CampoInvalidoException;
import projetoDAO.UsuarioDAO;
import projetoDAO.VeiculoDAO;

@WebServlet("/Veiculo")
public class Veiculo extends HttpServlet
{
	private int id;
	private String modelo;
	private String placa;
	private String cor;
	private Motorista motorista;
	private int numeroVagas;

	private ArrayList<Carona> caronas;

	public Veiculo(){
		//para auxiliar na crian�ar de veiculos nas jsp
	}

	public Veiculo(String modelo,String placa,String cor,int numeroVagas)
	{

		this.modelo = modelo;
		this.placa = placa;
		this.cor = cor;		

		caronas = new ArrayList<Carona>();

	}


	public Veiculo(int ID,String modelo,String placa,String cor,int numeroVagas)
	{
		this.id = ID;
		this.modelo = modelo;
		this.placa = placa;
		this.cor = cor;		

		caronas = new ArrayList<Carona>();

	}

	public Veiculo(String modelo,String placa,String cor,int numeroVagas, Motorista motorista)
	{
		this.modelo = modelo;
		this.placa = placa;
		this.cor = cor;
		this.motorista = motorista;

		caronas = new ArrayList<Carona>();


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String opcao = request.getParameter("opcao");

		if(opcao.equals("criarVeiculo"))
		{
			this.criarVeiculo(request, response);	
		}

		else if(opcao.equals("criarUsuario"))
		{
			this.alterarVeiculo(request, response);
		}

		



	}

	/**
	 * Método para ser chamado dentro do doPost.
	 * @throws IOException 
	 * @throws ServletException 
	 * 
	 * **/

	public void criarVeiculo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		HttpSession session = request.getSession();

		String modelo = request.getParameter("modeloVeiculo");
		String placa = request.getParameter("placaVeiculo");
		String cor = request.getParameter("corVeiculo");
		String numVagas = request.getParameter("numVagasVeiculo");		



		Usuario recuperado = (Usuario)session.getAttribute("novoUsuario");
		String email = recuperado.getEmail();

		try{

			if(modelo.equals("") || placa.equals("") || cor.equals("") || numVagas.equals("")){
				throw new CampoInvalidoException();
			}			
			else{				
				try {

				//	Veiculo novoVeiculo = new Veiculo(modelo,placa,cor,Integer.parseInt(numVagas));
					
					this.setModelo(modelo);
					this.setPlaca(placa);
					this.setCor(cor);
					this.setNumeroVagas(Integer.parseInt(numVagas));

					UsuarioDAO auxUsu = new UsuarioDAO();					
					auxUsu.mudaMotorista(email);

					VeiculoDAO auxVei = new VeiculoDAO();
					auxVei.adicionaVeiculo(email, placa, cor, modelo,Integer.parseInt(numVagas));

					// Esse resquest dispatcher vai para a tela de Sucesso para usuario criar um novo veiculo, se quiser

					request.setAttribute("novoVeiculo", this);
					request.setAttribute("novoUsuario", recuperado);

					RequestDispatcher rdSucesso = request.getRequestDispatcher("./sucessoMotorista.jsp");
					rdSucesso.forward(request,response);

				} catch (ClassNotFoundException e) {
					// eclipse me OBRIGOU a criar esse Try/Catch
					e.printStackTrace();
				}
			}

		}catch(CampoInvalidoException e){	
			RequestDispatcher rdErro = request.getRequestDispatcher("./excecoes/campoInvalido.jsp");
			rdErro.forward(request, response);
		}



	}

	/**
	 * Método para ser chamado dentro do doPost.
	 * @param request 
	 * @param response 
	 * @throws IOException 
	 * @throws ServletException 
	 * **/

	public void alterarVeiculo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String novaCor = request.getParameter("novaCorVeiculo");

		//salvar na session em colocar dentro daqui.
		Veiculo novoVeiculo = new Veiculo();

		try
		{
			if(novaCor.equals(""))
				throw new CampoInvalidoException();

			else
			{
				try {
					novoVeiculo.alterar(novaCor);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		catch(CampoInvalidoException e){	
			RequestDispatcher rdErro = request.getRequestDispatcher("./excecoes/campoInvalido.jsp");
			rdErro.forward(request, response);
		}
	}


	public ArrayList<String> veiculosDeUmDono(String email) throws ClassNotFoundException{

		VeiculoDAO aux = new VeiculoDAO();

		return aux.veiculosDeUmDono(email);
	}



	public void armazenar() throws ClassNotFoundException 
	{
		VeiculoDAO veiculodao = new VeiculoDAO();

		veiculodao.adicionaVeiculo(this.motorista.getEmail(), this.placa, this.cor, this.modelo,this.numeroVagas);


	}

	public void alterar(String novaCor) throws ClassNotFoundException {

		VeiculoDAO veiculodao = new VeiculoDAO();

		//precisa desse método no DAO
		veiculodao.mudaCor(this.id, novaCor);

	}	

	public void recuperaID(String modelo, String placa) throws ClassNotFoundException {

		VeiculoDAO aux = new VeiculoDAO();

		this.setID(aux.recuperaID(modelo,placa));
	}

	public Veiculo recuperaCarro(int ID) throws ClassNotFoundException{

		VeiculoDAO aux = new VeiculoDAO();
		ArrayList<String> info = aux.buscaInformacoes(ID);
		Veiculo novoMontado = new Veiculo(Integer.parseInt(info.get(0)),info.get(4),info.get(2),info.get(3),Integer.parseInt(info.get(5)));


		return novoMontado;

	}

	public ArrayList<String> recuperaInfo() throws ClassNotFoundException{

		VeiculoDAO aux = new VeiculoDAO();

		return aux.buscaInformacoes(this.id);

	}

	//daqui para baixo apenas getters and setters

	//	public void setMotorista(Motorista novoMotorista) {
	//		this.motorista = novoMotorista;
	//		
	//	}
	
	

	public String getCor() 
	{
		return cor;
	}

	private void setModelo(String modelo) {
		this.modelo = modelo;
	}

	private void setPlaca(String placa) {
		this.placa = placa;
	}

	private void setNumeroVagas(int numeroVagas) {
		this.numeroVagas = numeroVagas;
	}

	public void setCor(String cor) 
	{
		this.cor = cor;
	}

	public String getModelo() 
	{
		return modelo;
	}

	public String getPlaca() 
	{
		return placa;
	}

	public int getNumeroVagas()
	{
		return numeroVagas;
	}

	public Motorista getMotorista()
	{
		return motorista;
	}

	public boolean addCarona(Carona carona)
	{
		return this.getCaronas().add(carona);

	}

	public ArrayList<Carona> getCaronas() 
	{
		return caronas;
	}

	public void setCaronas(ArrayList<Carona> caronas) 
	{
		this.caronas = caronas;

	}

	public void setMotorista(Motorista novoMotorista) {
		this.motorista = novoMotorista;

	}

	public void setID(int ID) {

		this.id = ID;

	}

	public int getID() {

		return this.id;	

	}




}

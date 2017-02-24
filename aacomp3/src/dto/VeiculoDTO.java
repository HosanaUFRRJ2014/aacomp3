package dto;

import dominio.Motorista;

public class VeiculoDTO {
	
	private int id;
	private String modelo;
	private String placa;
	private String cor;
	private String emailMotorista;
	private int numeroVagas;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	public String getMotorista() {
		return emailMotorista;
	}
	public void setMotorista(String emailMotorista) {
		this.emailMotorista = emailMotorista;
	}
	public int getNumeroVagas() {
		return numeroVagas;
	}
	public void setNumeroVagas(int numeroVagas) {
		this.numeroVagas = numeroVagas;
	}
}

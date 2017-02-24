package dto;

import dominio.Grupo;


public class GrupoDTO {
	
	private int id;
	private String nome;
	private String descricao;
	private String regras; 	
	private int limitMin;	
	private boolean ativo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getRegras() {
		return regras;
	}

	public void setRegras(String regras) {
		this.regras = regras;
	}

	public int getLimitMin() {
		return limitMin;
	}

	public void setLimitMin(int limitMin) {
		this.limitMin = limitMin;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}


}

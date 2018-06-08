package com.acoes.bolsa.modelo;

public class Empresa {

	private Float valorAcao;
	private String nome;
	
	private static Empresa instanciaIntel;
	private static Empresa instanciaSoftExpert;
	
	public Float getValorAcao() {
		return valorAcao;
	}
	
	public void setValorAcao(Float valorAcao) {
		this.valorAcao = valorAcao;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public static Empresa getIntelInstance() {
		if(instanciaIntel == null) {
			instanciaIntel = new Empresa();
			instanciaIntel.setNome("Intel");
			instanciaIntel.setValorAcao(10f);
		}
		
		return instanciaIntel;
	}
	
	public static Empresa getSoftExpertInstance() {
		if(instanciaSoftExpert == null) {
			instanciaSoftExpert = new Empresa();
			instanciaSoftExpert.setNome("SoftExpert");
			instanciaSoftExpert.setValorAcao(10.5f);
		}
		return instanciaSoftExpert;
	}
	
	public static Empresa getInstancePorNome(String nomeEmpresa) {
		Empresa instancia = null;
		
		switch(nomeEmpresa) {
			case "Intel":
				instancia = getIntelInstance();
				break;
			case "SoftExpert":
				instancia = getSoftExpertInstance();
				break;
		}
		
		return instancia;
	}

}

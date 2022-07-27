package br.com.sigoa.model;

public class Servico {
	
	private Integer id_servico; 
	private String nome_servico; 
	private Double valor_servico;
	private Double valor_servico_os;
	private Integer quantidade_servico_os;
	
	public Servico(String nome_servico, Double valor_servico) {
		super();
		this.nome_servico = nome_servico;
		this.valor_servico = valor_servico;
	}
	
	public Servico() {
	}
	public Double getValor_servico_os() {
		return valor_servico_os;
	}
	public void setValor_servico_os(Double valor_servico_os) {
		this.valor_servico_os = valor_servico_os;
	}
	public Integer getQuantidade_servico_os() {
		return quantidade_servico_os;
	}
	public void setQuantidade_servico_os(Integer quantidade_servico_os) {
		this.quantidade_servico_os = quantidade_servico_os;
	}
	public void setId_servico(Integer id_servico) {
		this.id_servico = id_servico;
	}
	public Integer getId_servico() {
		return id_servico;
	}
	public String getNome_servico() {
		return nome_servico;
	}
	public void setNome_servico(String nome_servico) {
		this.nome_servico = nome_servico;
	}
	public Double getValor_servico() {
		return valor_servico;
	}
	public void setValor_servico(Double valor_servico) {
		this.valor_servico = valor_servico;
	}
}
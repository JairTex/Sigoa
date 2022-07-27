package br.com.sigoa.model;

public class Peca {
	private Integer id_peca;
	private String nome_peca;
	private String fabricante_peca;
	private Integer quantidade_peca;
	private Integer quantidade_peca_os;
	private Double valor_peca;
	private Double valor_peca_os;
	
	public Peca(String nome_peca, String fabricante_peca, Integer quantidade_peca, Double valor_peca) {
		super();
		this.nome_peca = nome_peca;
		this.fabricante_peca = fabricante_peca;
		this.quantidade_peca = 0;
		this.valor_peca = valor_peca;
	}
	public Peca() {		
	}
	public Double getValor_peca_os() {
		return valor_peca_os;
	}
	public void setValor_peca_os(Double valor_peca_os) {
		this.valor_peca_os = valor_peca_os;
	}
	public Integer getQuantidade_peca_os() {
		return quantidade_peca_os;
	}
	public void setQuantidade_peca_os(Integer quantidade_peca_os) {
		this.quantidade_peca_os = quantidade_peca_os;
	}
	public void setId_peca (Integer id_peca) {
		this.id_peca = id_peca;
	}
	public Integer getId_peca() {
		return id_peca;
	}
	public String getNome_peca() {
		return nome_peca;
	}
	public void setNome_peca(String nome_peca) {
		this.nome_peca = nome_peca;
	}
	public String getFabricante_peca() {
		return fabricante_peca;
	}
	public void setFabricante_peca(String fabricante_peca) {
		this.fabricante_peca = fabricante_peca;
	}
	public Integer getQuantidade_peca() {
		return quantidade_peca;
	}
	public void setQuantidade_peca(Integer quantidade_peca) {
		this.quantidade_peca = quantidade_peca;
	}
	public Double getValor_peca() {
		return valor_peca;
	}
	public void setValor_peca(Double valor_peca) {
		this.valor_peca = valor_peca;
	}
}
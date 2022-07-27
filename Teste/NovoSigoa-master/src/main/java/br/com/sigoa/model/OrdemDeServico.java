package br.com.sigoa.model;


import java.util.ArrayList;

public class OrdemDeServico {
	
	private Integer id_ordem_servico;
	private String cliente_ordem_servico;
	private ArrayList<Servico> servicos_ordem_servico = new ArrayList<Servico>();
	private ArrayList<Peca> pecas_ordem_servico = new ArrayList<Peca>(); 
	private String inicio_ordem_servico;
	private String fim_ordem_servico;
	private Integer tb_cliente_id_cliente;
	
	public OrdemDeServico(Integer id_ordem_servico, String cliente_ordem_servico,
			ArrayList<Servico> servicos_ordem_servico, ArrayList<Peca> pecas_ordem_servico, String inicio_ordem_servico,
			String fim_ordem_servico, Integer tb_cliente_id_cliente) {
		super();
		this.id_ordem_servico = id_ordem_servico;
		this.cliente_ordem_servico = cliente_ordem_servico;
		this.servicos_ordem_servico = servicos_ordem_servico;
		this.pecas_ordem_servico = pecas_ordem_servico;
		this.inicio_ordem_servico = java.time.LocalDate.now().toString();
		this.fim_ordem_servico = null;
		this.tb_cliente_id_cliente = tb_cliente_id_cliente;
	}

	public Integer getId_ordem_servico() {
		return id_ordem_servico;
	}

	public void setId_ordem_servico(Integer id_ordem_servico) {
		this.id_ordem_servico = id_ordem_servico;
	}

	public String getCliente_ordem_servico() {
		return cliente_ordem_servico;
	}

	public void setCliente_ordem_servico(String cliente_ordem_servico) {
		this.cliente_ordem_servico = cliente_ordem_servico;
	}

	public ArrayList<Servico> getServicos_ordem_servico() {
		return servicos_ordem_servico;
	}

	public void setServicos_ordem_servico(ArrayList<Servico> servicos_ordem_servico) {
		this.servicos_ordem_servico = servicos_ordem_servico;
	}

	public ArrayList<Peca> getPecas_ordem_servico() {
		return pecas_ordem_servico;
	}

	public void setPecas_ordem_servico(ArrayList<Peca> pecas_ordem_servico) {
		this.pecas_ordem_servico = pecas_ordem_servico;
	}

	public String getInicio_ordem_servico() {
		return inicio_ordem_servico;
	}

	public void setInicio_ordem_servico(String inicio_ordem_servico) {
		this.inicio_ordem_servico = inicio_ordem_servico;
	}

	public String getFim_ordem_servico() {
		return fim_ordem_servico;
	}

	public void setFim_ordem_servico(String fim_ordem_servico) {
		this.fim_ordem_servico = fim_ordem_servico;
	}

	public Integer getTb_cliente_id_cliente() {
		return tb_cliente_id_cliente;
	}

	public void setTb_cliente_id_cliente(Integer tb_cliente_id_cliente) {
		this.tb_cliente_id_cliente = tb_cliente_id_cliente;
	}
	
}

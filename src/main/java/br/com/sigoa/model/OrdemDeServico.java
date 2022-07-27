package br.com.sigoa.model;

import java.util.ArrayList;
import java.util.List;

public class OrdemDeServico {
	
	private Integer id_ordem_servico;
	private ArrayList<Servico> servicos_ordem_servico = new ArrayList<Servico>();
	private ArrayList<Peca> pecas_ordem_servico = new ArrayList<Peca>(); 
	private String inicio_ordem_servico;
	private String fim_ordem_servico;
	private Integer tb_cliente_id_cliente;
	private Integer tb_funcionario_id_funcionario;
	private String nome_funcionario;
	private String nome_cliente;
	private String status_os;
	public List<Peca> pecas_os;
	public List<Servico> servico_os;
	
	public OrdemDeServico(Integer tb_cliente_id_cliente,
			Integer tb_funcionario_id_funcionario, String inicio_ordem_servico, String nome_funcionario,
			String nome_cliente) 
	{
		super();
		this.inicio_ordem_servico = java.time.LocalDate.now().toString();
		this.tb_cliente_id_cliente = tb_cliente_id_cliente;
		this.tb_funcionario_id_funcionario = tb_funcionario_id_funcionario;
		this.nome_funcionario = nome_funcionario;
		this.nome_cliente = nome_cliente;
	}
	
	public OrdemDeServico(){
	}
	
	public String getStatus_os() {
		return status_os;
	}

	public void setStatus_os(String status_os) {
		this.status_os = status_os;
	}

	public double calcularValorTotalServico(){
	        double total = 0;
	        for (Servico servico : servico_os) {
	            total += servico.getValor_servico_os();
	        }
	        return total;
	}
	
	public double calcularValorTotalPecas(){
        double total = 0;
        for (Peca peca : pecas_os) {
            total += peca.getValor_peca_os();
        }
        return total;
	}
   
	public String getNome_funcionairo() {
		return nome_funcionario;
	}

	public void setNome_funcionairo(String nome_funcionairo) {
		this.nome_funcionario = nome_funcionairo;
	}

	public String getNome_cliente() {
		return nome_cliente;
	}

	public void setNome_cliente(String nome_cliente) {
		this.nome_cliente = nome_cliente;
	}

	public Integer getTb_funcionario_id_funcionario() {
		return tb_funcionario_id_funcionario;
	}

	public void setTb_funcionario_id_funcionario(Integer tb_funcionario_id_funcionario) {
		this.tb_funcionario_id_funcionario = tb_funcionario_id_funcionario;
	}
	
	public Integer getId_ordem_servico() {
		return id_ordem_servico;
	}

	public void setId_ordem_servico(Integer id_ordem_servico) {
		this.id_ordem_servico = id_ordem_servico;
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
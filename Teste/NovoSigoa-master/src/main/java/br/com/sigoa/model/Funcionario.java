package br.com.sigoa.model;

public class Funcionario {
	private Integer id_funcionario;
	private String nome_funcionario;
	private String cpf_funcionario;
	private String matricula_funcionario;
	private String endereco_funcionario;
	private String nascimento_funcionario;
	private String telefone_funcionario;
	
	public Funcionario(String nome_funcionario, String cpf_funcionario,
			String matricula_funcionario, String endereco_funcionario, String nascimento_funcionario,
			String telefone_funcionario) {
		
		super();
		this.nome_funcionario = nome_funcionario;
		this.cpf_funcionario = cpf_funcionario;
		this.matricula_funcionario = matricula_funcionario;
		this.endereco_funcionario = endereco_funcionario;
		this.nascimento_funcionario = nascimento_funcionario;
		this.telefone_funcionario = telefone_funcionario;
	
	}
	
	public Funcionario() {
	
	}
	
	public void setId_funcionario (Integer id_funcionario) {
		this.id_funcionario = id_funcionario;
	}

	public Integer getId_funcionario() {
		return id_funcionario;
	}

	public String getNome_funcionario() {
		return nome_funcionario;
	}

	public void setNome_funcionario(String nome_funcionario) {
		this.nome_funcionario = nome_funcionario;
	}

	public String getCpf_funcionario() {
		return cpf_funcionario;
	}

	public void setCpf_funcionario(String cpf_funcionario) {
		this.cpf_funcionario = cpf_funcionario;
	}

	public String getMatricula_funcionario() {
		return matricula_funcionario;
	}

	public void setMatricula_funcionario(String matricula_funcionario) {
		this.matricula_funcionario = matricula_funcionario;
	}

	public String getEndereco_funcionario() {
		return endereco_funcionario;
	}

	public void setEndereco_funcionario(String endereco_funcionario) {
		this.endereco_funcionario = endereco_funcionario;
	}

	public String getNascimento_funcionario() {
		return nascimento_funcionario;
	}

	public void setNascimento_funcionario(String nascimento_funcionario) {
		this.nascimento_funcionario = nascimento_funcionario;
	}

	public String getTelefone_funcionario() {
		return telefone_funcionario;
	}

	public void setTelefone_funcionario(String telefone_funcionario) {
		this.telefone_funcionario = telefone_funcionario;
	}

}
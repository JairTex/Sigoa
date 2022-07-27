package br.com.sigoa.model;

public class Usuario {
	private Integer id_usuario;
	private String nome_usuario;
	private String senha_usuario;
	
	public Usuario(String nome_usuario, String senha_usuario) {
		super();
		this.nome_usuario = nome_usuario;
		this.senha_usuario = senha_usuario;
	}
	
	public Integer getId_usuario() {
		return id_usuario;
	}
	public String getNome_usuario() {
		return nome_usuario;
	}
	public void setNome_usuario(String nome_usuario) {
		this.nome_usuario = nome_usuario;
	}
	public String getSenha_usuario() {
		return senha_usuario;
	}
	public void setSenha_usuario(String senha_usuario) {
		this.senha_usuario = senha_usuario;
	}
}
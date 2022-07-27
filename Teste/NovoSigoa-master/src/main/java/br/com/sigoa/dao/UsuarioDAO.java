package br.com.sigoa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import br.com.sigoa.connection.Conexao;
import br.com.sigoa.controller.ControllerTelaPrincipal;
import br.com.sigoa.model.Usuario;
import br.com.sigoa.view.TelaLogin;
import br.com.sigoa.view.TelaPrincipal;

public class UsuarioDAO {
	private static TelaLogin telaLogin;

	public UsuarioDAO(TelaLogin telaLogin) {
		this.telaLogin = telaLogin;
		// TODO Auto-generated constructor stub
	}

	static public void CREATE(Usuario usuario) {
		
		String sql = "INSERT INTO tb_usuario (nome_usuario, "
				+ "senha_usuario) VALUES (?, ?);";
	
		try {
			Conexao conexao = new Conexao();
			Connection con = conexao.getConnection();
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setString(1, usuario.getNome_usuario());
			stmt.setString(2, usuario.getSenha_usuario());
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!", "Sucess", 2);
			}
			else {
				JOptionPane.showMessageDialog(null, "Houve um erro inesperado, contate o suporte!", "Error", 1);
			}
			
			try {
				if(con != null){
					con.close();
				}
				if(stmt != null) {
					stmt.close();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	@SuppressWarnings("deprecation")
	static public void SELECT () {
		
		String sql = "select * from tb_usuario where nome_usuario=? and senha_usuario =?";
		

		try {
			Conexao conexao = new Conexao();
			Connection con = conexao.getConnection();
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setString(1, br.com.sigoa.view.TelaLogin.getTxt_usuario().getText());
			stmt.setString(2, br.com.sigoa.view.TelaLogin.getPswSenha().getText());
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				JOptionPane.showMessageDialog(null, "Usuário encontrado com sucesso!", "Sucess", 2);
				ControllerTelaPrincipal.abrirTelaPrincipal();
				telaLogin.dispose();
			}
			else {
				JOptionPane.showMessageDialog(null, "Houve um erro inesperado, contate o suporte!", "Error", 1);
			}
			
			try {
				if(con != null){
					con.close();
				}
				if(stmt != null) {
					stmt.close();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}		
	}
}

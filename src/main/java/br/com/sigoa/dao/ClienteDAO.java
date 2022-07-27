package br.com.sigoa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import br.com.sigoa.connection.Conexao;
import br.com.sigoa.model.Cliente;

public class ClienteDAO {
	
	static public void CREATE(Cliente cliente) {
		
		String sql = "INSERT INTO tb_cliente (nome_cliente, email_cliente, "
				+ "cpf_cliente, telefone_cliente) VALUES (?, ?, ?, ?)";
		try {
			Conexao conexao = new Conexao();
			Connection con = conexao.getConnection();
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setString(1, cliente.getNome_cliente());
			stmt.setString(2, cliente.getEmail_cliente());
			stmt.setString(3, cliente.getCpf_cliente());
			stmt.setString(4, cliente.getTelefone_cliente());
			
			stmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!", "Sucess", 2);
			
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
	
	static public void UPDATE(Cliente cliente) {
		String sql = "UPDATE tb_cliente SET nome_cliente = ?, email_cliente = ?, "
				+ "cpf_cliente = ?, telefone_cliente = ? WHERE id_cliente = ?";
		try {
			Conexao conexao = new Conexao();
			Connection con = conexao.getConnection();
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setString(1, cliente.getNome_cliente());
			stmt.setString(2, cliente.getEmail_cliente());
			stmt.setString(3, cliente.getCpf_cliente());
			stmt.setString(4, cliente.getTelefone_cliente());
			stmt.setString(5, cliente.getId_cliente().toString());
			
			stmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "Cadastro modificado com sucesso!", "Sucess", 2);
			
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
	
	public List<Cliente> listar(){
		
		List <Cliente> Clientes = new ArrayList<>();
		
		Conexao conexao = new Conexao();
		Connection con = conexao.getConnection();
		con = conexao.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {		 
			stmt = con.prepareStatement("SELECT * FROM tb_cliente");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				Cliente cliente = new Cliente(null, null, null, null);
				
				cliente.setId_cliente(rs.getInt("id_cliente"));
				cliente.setNome_cliente(rs.getString("nome_cliente"));
				cliente.setEmail_cliente(rs.getString("email_cliente"));
				cliente.setCpf_cliente(rs.getString("cpf_cliente"));
				cliente.setTelefone_cliente(rs.getString("telefone_cliente"));
				Clientes.add(cliente);
			}
			
		} catch (SQLException ex) {
			Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null,ex);
		} finally {
			Conexao.closeConnection(con, stmt, rs);
		}
		return Clientes;
	}

	public static ResultSet carregaTabela() throws SQLException{
		Conexao conexao = new Conexao();
		PreparedStatement pstmt = conexao.getConnection().prepareStatement(
				"SELECT * FROM tb_cliente");
				ResultSet rs = pstmt.executeQuery();
				return rs;
	}//Fim do método carrega tabela
	
	//Pesquisa com argumento
	public static ResultSet carregaTabela(String tipo, String Arg) throws SQLException{
		
		String argumento = tipo +" "+"like '"+Arg+"%'";
		
		Conexao conexao = new Conexao();
		
		PreparedStatement pstmt = conexao.getConnection().prepareStatement(
				"SELECT * FROM tb_cliente where "+ argumento+"");
				ResultSet rs = pstmt.executeQuery();
				return rs;
	}
	
	static public void DELETE(Cliente cliente) {
		
		String sql = "DELETE FROM tb_cliente WHERE id_cliente = ?";
		
		try {
			Conexao conexao = new Conexao();
			Connection con = conexao.getConnection();
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setString(1, cliente.getId_cliente().toString());
			
			stmt.execute();
			
			JOptionPane.showMessageDialog(null, "Cliente deletado!", "Sucess", 2);
			
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
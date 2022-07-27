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
import br.com.sigoa.model.Servico;

public class ServicoDAO {
	static public void CREATE(Servico servico) {
		
		String sql = "INSERT INTO tb_servico (nome_servico, "
				+ "valor_servico) VALUES (?, ?);";
		
		try {
			Conexao conexao = new Conexao();
			Connection con = conexao.getConnection();
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setString(1, servico.getNome_servico());
			stmt.setString(2, servico.getValor_servico().toString());
			
			stmt.executeUpdate();
			
			
			JOptionPane.showMessageDialog(null, "ServiÃ§o cadastrado com sucesso!", "Sucess", 2);
			
			
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


	static public void UPDATE(Servico servico) {
	
		String sql = "UPDATE tb_servico SET nome_servico = ?, valor_servico = ? WHERE id_servico = ?";
	
		try {
			Conexao conexao = new Conexao();
			Connection con = conexao.getConnection();
		
			PreparedStatement stmt = con.prepareStatement(sql);
		
			stmt.setString(1, servico.getNome_servico());
			stmt.setString(2, servico.getValor_servico().toString());
			stmt.setString(3, servico.getId_servico().toString());
		
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
	
	public List<Servico> listar(){

		
		List <Servico> Servicos = new ArrayList<>();
		
		Conexao conexao = new Conexao();
		Connection con = conexao.getConnection();
		con = conexao.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			 
			stmt = con.prepareStatement("SELECT * FROM tb_servico");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				Servico servico = new Servico(null, null);
				
				servico.setId_servico(rs.getInt("id_servico"));
				servico.setNome_servico(rs.getString("nome_servico"));
				servico.setValor_servico(rs.getDouble("valor_servico"));
				Servicos.add(servico);
			}
			
		} catch (SQLException ex) {
			Logger.getLogger(ServicoDAO.class.getName()).log(Level.SEVERE, null,ex);
		} finally {
			Conexao.closeConnection(con, stmt, rs);
		}
		return Servicos;
		
	}
	
	public static ResultSet carregaTabela() throws SQLException{
		Conexao conexao = new Conexao();
		PreparedStatement pstmt = conexao.getConnection().prepareStatement(
				"SELECT * FROM tb_servico");
				ResultSet rs = pstmt.executeQuery();
				return rs;
	}//Fim do método carrega tabela
	
	//Pesquisa com argumento
	public static ResultSet carregaTabela(String tipo, String Arg) throws SQLException{
		
		String argumento = tipo +" "+"like '"+Arg+"%'";
		
		Conexao conexao = new Conexao();
		
		PreparedStatement pstmt = conexao.getConnection().prepareStatement(
				"SELECT * FROM tb_servico where "+ argumento+"");
				ResultSet rs = pstmt.executeQuery();
				return rs;
	}
}
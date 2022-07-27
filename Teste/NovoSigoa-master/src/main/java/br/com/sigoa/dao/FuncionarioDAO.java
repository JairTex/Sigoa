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
import br.com.sigoa.model.Funcionario;

public class FuncionarioDAO {
	
	static public void CREATE(Funcionario funcionario) {
		
		String sql = "INSERT INTO tb_funcionario (nome_funcionario, cpf_funcionario, "
				+ "matricula_funcionario, endereco_funcionario, nascimento_funcionario, "
				+ "telefone_funcionario) "
				+ "VALUES (?, ?, ?, ?, ?, ?);";
	
		try {
			Conexao conexao = new Conexao();
			Connection con = conexao.getConnection();
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setString(1, funcionario.getNome_funcionario());
			stmt.setString(2, funcionario.getCpf_funcionario());
			stmt.setString(3, funcionario.getMatricula_funcionario());
			stmt.setString(4, funcionario.getEndereco_funcionario());
			stmt.setString(5, funcionario.getNascimento_funcionario());
			stmt.setString(6, funcionario.getTelefone_funcionario());
			
			stmt.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "FuncionÃ¡rio cadastrado com sucesso!", "Sucess", 2);
			
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

	static public void UPDATE(Funcionario funcionario) {
		
		String sql = "UPDATE tb_funcionario SET nome_funcionario = ?, cpf_funcionario = ?, "
				+ "matricula_funcionario = ?, endereco_funcionario = ?, nascimento_funcionario = ?, telefone_funcionario = ? WHERE id_funcionario = ?";
		
		try {
			Conexao conexao = new Conexao();
			Connection con = conexao.getConnection();
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setString(1, funcionario.getNome_funcionario());
			stmt.setString(2, funcionario.getCpf_funcionario());
			stmt.setString(3, funcionario.getMatricula_funcionario());
			stmt.setString(4, funcionario.getEndereco_funcionario());
			stmt.setString(5, funcionario.getNascimento_funcionario());
			stmt.setString(6, funcionario.getTelefone_funcionario());
			stmt.setString(7, funcionario.getId_funcionario().toString());
			
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
	
	
	public List<Funcionario> listar(){
		
		List <Funcionario> Funcionarios = new ArrayList<>();
	
		Conexao conexao = new Conexao();
		Connection con = conexao.getConnection();
		con = conexao.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
	
		try {
		 
			stmt = con.prepareStatement("SELECT * FROM tb_funcionario");
			rs = stmt.executeQuery();
		
			while(rs.next()) {
			
				Funcionario funcionario = new Funcionario(null, null, null, null, null, null);
			
				funcionario.setId_funcionario(rs.getInt("id_funcionario"));
				funcionario.setNome_funcionario(rs.getString("nome_funcionario"));
				funcionario.setCpf_funcionario(rs.getString("cpf_funcionario"));
				funcionario.setMatricula_funcionario(rs.getString("matricula_funcionario"));
				funcionario.setEndereco_funcionario(rs.getString("endereco_funcionario"));
				funcionario.setNascimento_funcionario(rs.getString("nascimento_funcionario"));
				funcionario.setTelefone_funcionario(rs.getString("telefone_funcionario"));
				Funcionarios.add(funcionario);
			}
		
		} catch (SQLException ex) {
			Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null,ex);
		} finally {
			Conexao.closeConnection(con, stmt, rs);
		}
		return Funcionarios;
	}
	
	public static ResultSet carregaTabela() throws SQLException{
		Conexao conexao = new Conexao();
		PreparedStatement pstmt = conexao.getConnection().prepareStatement(
				"SELECT * FROM tb_funcionario");
				ResultSet rs = pstmt.executeQuery();
				return rs;
	}//Fim do método carrega tabela
	
	//Pesquisa com argumento
	public static ResultSet carregaTabela(String tipo, String Arg) throws SQLException{
		
		String argumento = tipo +" "+"like '"+Arg+"%'";
		
		Conexao conexao = new Conexao();
		
		PreparedStatement pstmt = conexao.getConnection().prepareStatement(
				"SELECT * FROM tb_funcionario where "+ argumento+"");
				ResultSet rs = pstmt.executeQuery();
				return rs;
	}
}
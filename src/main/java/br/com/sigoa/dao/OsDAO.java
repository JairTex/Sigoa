package br.com.sigoa.dao;

import java.lang.reflect.Executable;
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
import br.com.sigoa.model.OrdemDeServico;
import br.com.sigoa.model.Peca;
import br.com.sigoa.model.Servico;

public class OsDAO {
	static public void CREATE(OrdemDeServico os) {
		
		String sql = "insert into tb_ordem_servico (tb_cliente_id_cliente, tb_funcionario_id_funcionario, inicio_ordem_servico,"
				+ "nome_funcionario, nome_cliente) "
				+ "values (?, ?, ?, ?, ?);";
	
		try {
			Conexao conexao = new Conexao();
			Connection con = conexao.getConnection();
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setString(1, os.getTb_cliente_id_cliente().toString());
			stmt.setString(2, os.getTb_funcionario_id_funcionario().toString());
			stmt.setString(3, os.getInicio_ordem_servico());
			stmt.setString(4, os.getNome_funcionairo());
			stmt.setString(5, os.getNome_cliente());
			
			stmt.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Ordem de Serviço Gerada", "Sucess", 2);
			
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
	
	static public void UPDATE_CANCELAR(OrdemDeServico os) {
		
		String sql = "update tb_ordem_servico set status_os = \'Cancelada\' "
				+ "where id_ordem_servico = ?;";
	
		try {
			Conexao conexao = new Conexao();
			Connection con = conexao.getConnection();
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setString(1, os.getId_ordem_servico().toString());
			
			stmt.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Ordem de Serviço Cancelada!", "Sucess", 2);
			
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
	
	static public void UPDATE_FINALIZAR(OrdemDeServico os) {
		
		String sql = "update tb_ordem_servico set status_os = \'Finalizada\' "
				+ "where id_ordem_servico = ?;";
	
		try {
			Conexao conexao = new Conexao();
			Connection con = conexao.getConnection();
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setString(1, os.getId_ordem_servico().toString());
			
			stmt.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Ordem de Serviço Finalizada!", "Sucess", 2);
			
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
	

	static public void SELECT(OrdemDeServico os) {
	
	String sql = "select id_ordem_servico FROM tb_ordem_servico "
			+ "where tb_cliente_id_cliente = ? and tb_funcionario_id_funcionario = ? and inicio_ordem_servico = ?;";

	try {
		Conexao conexao = new Conexao();
		Connection con = conexao.getConnection();
		
		PreparedStatement stmt = con.prepareStatement(sql);
		
		stmt.setString(1, os.getTb_cliente_id_cliente().toString());
		stmt.setString(2, os.getTb_funcionario_id_funcionario().toString());
		stmt.setString(3, os.getInicio_ordem_servico());
		
		stmt.executeQuery();
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()) {
			JOptionPane.showMessageDialog(null, "OS gerada n°: " + rs.getString("id_ordem_servico"), "Sucess", 2);
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

	public List<OrdemDeServico> listar(){
		
		List <OrdemDeServico> OS = new ArrayList<>();
		
		Conexao conexao = new Conexao();
		Connection con = conexao.getConnection();
		con = conexao.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			 
			stmt = con.prepareStatement("SELECT * FROM tb_ordem_servico");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				OrdemDeServico os = new OrdemDeServico();
				
				os.setId_ordem_servico(rs.getInt("id_ordem_servico"));
				os.setNome_cliente(rs.getString("nome_cliente"));
				os.setNome_funcionairo(rs.getString("nome_funcionario"));
				os.setStatus_os(rs.getString("status_os"));
				OS.add(os);
			}
			
		} catch (SQLException ex) {
			Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null,ex);
		} finally {
			Conexao.closeConnection(con, stmt, rs);
		}
		return OS;
		
	}
	
	public static ResultSet carregaTabela() throws SQLException{
		Conexao conexao = new Conexao();
		PreparedStatement pstmt = conexao.getConnection().prepareStatement(
				"SELECT  id_ordem_servico, nome_cliente, nome_funcionario, status_os FROM tb_ordem_servico");
				ResultSet rs = pstmt.executeQuery();
				return rs;
	}//Fim do método carrega tabela
	
	public static ResultSet carregaTabela(String tipo, String Arg) throws SQLException{
		
		String argumento = tipo +" "+"like '"+Arg+"%'";
		
		Conexao conexao = new Conexao();
		
		PreparedStatement pstmt = conexao.getConnection().prepareStatement(
				"SELECT id_ordem_servico, nome_cliente, nome_funcionario, status_os FROM tb_ordem_servico where "+ argumento+"");
				ResultSet rs = pstmt.executeQuery();
				return rs;
	}
	
	static public void CREATE_PECA_OS_AUX() {
		
		String sql = "SET FOREIGN_KEY_CHECKS = 0;";
	
		try {
			Conexao conexao = new Conexao();
			Connection con = conexao.getConnection();
			
			PreparedStatement prepstm = null;
			
	        prepstm = con.prepareStatement(sql);
	        prepstm.execute();
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}		
	
	}	
	
	static public void CREATE_PECA_OS_AUX_II() {
		
		String sql = "SET FOREIGN_KEY_CHECKS = 1;";
	
		try {
			Conexao conexao = new Conexao();
			Connection con = conexao.getConnection();
			
			PreparedStatement prepstm = null;
			
	        prepstm = con.prepareStatement(sql);
	        prepstm.execute();
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}		
	
	}	
	
	static public void CREATE_PECA_OS(OrdemDeServico os, Peca peca) {
		
		String sql = "INSERT INTO tb_peca_has_tb_ordem_servico (tb_peca_id_peca, tb_ordem_servico_id_ordem_servico,"
				+ "quantidade, nome_peca, preco_peca, preco_total_peca) VALUES (?, ?, ?, ?, ?, ?);";
	
		try {
			Conexao conexao = new Conexao();
			Connection con = conexao.getConnection();
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setString(1, peca.getId_peca().toString());
			stmt.setString(2, os.getId_ordem_servico().toString());
			stmt.setString(3, peca.getQuantidade_peca_os().toString());
			stmt.setString(4, peca.getNome_peca());
			stmt.setString(5, peca.getValor_peca().toString());
			stmt.setString(6, peca.getValor_peca_os().toString());
			
			stmt.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Peça adicionada à OS!", "Sucess", 2);
			
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
	
	static public void CREATE_SERVICO_OS(OrdemDeServico os, Servico servico) {
		
		String sql = "INSERT INTO tb_servico_has_tb_ordem_servico (tb_servico_id_servico, tb_ordem_servico_id_ordem_servico,"
				+ "quantidade, nome_servico, preco_servico, preco_total_servico) VALUES (?, ?, ?, ?, ?, ?);";
	
		try {
			Conexao conexao = new Conexao();
			Connection con = conexao.getConnection();
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setString(1, servico.getId_servico().toString());
			stmt.setString(2, os.getId_ordem_servico().toString());
			stmt.setString(3, servico.getQuantidade_servico_os().toString());
			stmt.setString(4, servico.getNome_servico());
			stmt.setString(5, servico.getValor_servico().toString());
			stmt.setString(6, servico.getValor_servico_os().toString());
			
			stmt.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Serviço adicionado à OS!", "Sucess", 2);
			
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
	
	public List<Peca> listarPeca(){
		
		List <Peca> Pecas = new ArrayList<>();
		
		Conexao conexao = new Conexao();
		Connection con = conexao.getConnection();
		con = conexao.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			 
			stmt = con.prepareStatement("SELECT nome_peca, preco_peca, quantidade_peca FROM tb_peca_has_tb_ordem_servico");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				Peca pecaos = new Peca();
				
				pecaos.setNome_peca(rs.getString("nome_peca"));
				pecaos.setValor_peca_os(rs.getDouble("preco_peca"));
				pecaos.setQuantidade_peca_os(rs.getInt("quantidade"));
				//cliente.setTelefone_cliente(rs.getString("telefone_cliente"));
				Pecas.add(pecaos);
			}
			
		} catch (SQLException ex) {
			Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null,ex);
		} finally {
			Conexao.closeConnection(con, stmt, rs);
		}
		return Pecas;
		
	}
	
}

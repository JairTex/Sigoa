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
import br.com.sigoa.model.Peca;

public class PecaDAO {
	static public void CREATE(Peca peca) {
			
			String sql = "INSERT INTO tb_peca (nome_peca, fabricante_peca,"
					+ "quantidade_peca, valor_peca) VALUES (?, ?, ?, ?);";
			
			try {
				Conexao conexao = new Conexao();
				Connection con = conexao.getConnection();
				
				PreparedStatement stmt = con.prepareStatement(sql);
				
				stmt.setString(1, peca.getNome_peca());
				stmt.setString(2, peca.getFabricante_peca());
				stmt.setString(3, peca.getQuantidade_peca().toString());
				stmt.setString(4, peca.getValor_peca().toString());
				
				stmt.executeUpdate();
				
				JOptionPane.showMessageDialog(null, "Peç a cadastrada com sucesso!", "Sucess", 2);
				
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


	static public void UPDATE(Peca peca) {
	
		String sql = "UPDATE tb_peca SET nome_peca = ?, fabricante_peca = ?, "
				+ "valor_peca = ? , quantidade_peca = ? WHERE id_peca = ?";
	
		try {
			Conexao conexao = new Conexao();
			Connection con = conexao.getConnection();
		
			PreparedStatement stmt = con.prepareStatement(sql);
		
			stmt.setString(1, peca.getNome_peca());
			stmt.setString(2, peca.getFabricante_peca());
			stmt.setString(3, peca.getValor_peca().toString());
			stmt.setString(4, peca.getQuantidade_peca().toString());
			stmt.setString(5, peca.getId_peca().toString());
		
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
	
	public List<Peca> listar(){
		
		List <Peca> Pecas = new ArrayList<>();
		
		Conexao conexao = new Conexao();
		Connection con = conexao.getConnection();
		con = conexao.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			 
			stmt = con.prepareStatement("SELECT * FROM tb_peca");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				Peca peca = new Peca(null, null, null, null);
				
				peca.setId_peca(rs.getInt("id_peca"));
				peca.setNome_peca(rs.getString("nome_peca"));
				peca.setFabricante_peca(rs.getString("fabricante_peca"));
				peca.setQuantidade_peca(rs.getInt("quantidade_peca"));
				peca.setValor_peca(rs.getDouble("valor_peca"));
				Pecas.add(peca);
			}
			
		} catch (SQLException ex) {
			Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null,ex);
		} finally {
			Conexao.closeConnection(con, stmt, rs);
		}
		return Pecas;
		
	}
	
	public static ResultSet carregaTabela() throws SQLException{
		Conexao conexao = new Conexao();
		PreparedStatement pstmt = conexao.getConnection().prepareStatement(
				"SELECT * FROM tb_peca");
				ResultSet rs = pstmt.executeQuery();
				return rs;
	}//Fim do método carrega tabela
	
	//Pesquisa com argumento
	public static ResultSet carregaTabela(String tipo, String Arg) throws SQLException{
		
		String argumento = tipo +" "+"like '"+Arg+"%'";
		
		Conexao conexao = new Conexao();
		
		PreparedStatement pstmt = conexao.getConnection().prepareStatement(
				"SELECT * FROM tb_peca where "+ argumento+"");
				ResultSet rs = pstmt.executeQuery();
				return rs;
	}

}
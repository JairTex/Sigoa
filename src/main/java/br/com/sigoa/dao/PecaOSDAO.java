package br.com.sigoa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.mysql.cj.x.protobuf.MysqlxSql.StmtExecute;

import br.com.sigoa.connection.Conexao;
import br.com.sigoa.model.OrdemDeServico;
import br.com.sigoa.model.Peca;
import br.com.sigoa.model.Servico;

public class PecaOSDAO {

	public static List<Peca> SELECT_LIST (OrdemDeServico os) {
		String sql = "select tb_peca_id_peca,nome_peca, quantidade, preco_total_peca  FROM tb_peca_has_tb_ordem_servico "
				+ "where tb_ordem_servico_id_ordem_servico = ?;";
		try {
			Conexao conexao = new Conexao();
			Connection con = conexao.getConnection();
			
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, os.getId_ordem_servico().toString());
			ResultSet rs = stmt.executeQuery();
			
			
			List<Peca> pecas = new ArrayList<>();
			
			while(rs.next()) {
				Peca peca = new Peca();
				
				peca.setId_peca(rs.getInt("tb_peca_id_peca"));
				peca.setNome_peca(rs.getString("nome_peca"));
				peca.setQuantidade_peca_os(rs.getInt("quantidade"));
				peca.setValor_peca_os(rs.getDouble("preco_total_peca"));
				pecas.add(peca);
			}
			
			try {
				if(con != null){
					con.close();
				}
				if(stmt != null) {
					stmt.close();
				}
				if(rs != null) {
					rs.close();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return pecas;
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;	
	}
}
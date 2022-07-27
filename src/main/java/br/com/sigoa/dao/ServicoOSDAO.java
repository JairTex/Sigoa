package br.com.sigoa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.sigoa.connection.Conexao;
import br.com.sigoa.model.OrdemDeServico;
import br.com.sigoa.model.Servico;

public class ServicoOSDAO {
	public List<Servico> SELECT_LIST (OrdemDeServico os) {
		String sql = "select tb_servico_id_servico, nome_servico,quantidade,preco_total_servico FROM tb_servico_has_tb_ordem_servico "
				+ "where tb_ordem_servico_id_ordem_servico = ?;";
		try {
			Conexao conexao = new Conexao();
			Connection con = conexao.getConnection();
			
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, os.getId_ordem_servico().toString());
			ResultSet rs = stmt.executeQuery();
			
			List<Servico> servicos = new ArrayList<>();
			
			while(rs.next()) {
				Servico servico = new Servico();
				
				servico.setId_servico(rs.getInt("tb_servico_id_servico"));
				servico.setNome_servico(rs.getString("nome_servico"));
				servico.setQuantidade_servico_os(rs.getInt("quantidade"));
				servico.setValor_servico_os(rs.getDouble("preco_total_servico"));
				servicos.add(servico);
			}
			
			stmt.executeQuery();
			
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
			
			return servicos;
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;	
		
	}
}

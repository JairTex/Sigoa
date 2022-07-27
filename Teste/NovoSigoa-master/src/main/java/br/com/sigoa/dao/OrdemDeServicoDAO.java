package br.com.sigoa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import br.com.sigoa.connection.Conexao;
import br.com.sigoa.model.OrdemDeServico;

public class OrdemDeServicoDAO {
	public void Create(OrdemDeServico ordemDeServico) {
		
		String sql = "INSERT INTO tb_ordem_servico (cliente_ordem_servico, "
				+ "servicos_ordem_servico, pecas_ordem_servico, inicio_ordem_servico, "
				+ "fim_ordem_servico, tb_cliente_id_cliente)"
				+ "VALUES (?, ?, ?, ?, ?, ?);";
	
		try {
			Conexao conexao = new Conexao();
			Connection con = conexao.getConnection();
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setString(1, ordemDeServico.getCliente_ordem_servico());
			stmt.setString(2, ordemDeServico.getServicos_ordem_servico().toString());
			stmt.setString(3, ordemDeServico.getPecas_ordem_servico().toString());
			stmt.setString(4, ordemDeServico.getInicio_ordem_servico());
			stmt.setString(5, ordemDeServico.getFim_ordem_servico());
			stmt.setString(6, ordemDeServico.getTb_cliente_id_cliente().toString());
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				JOptionPane.showMessageDialog(null, "Ordem de Serviço cadastrada com sucesso!", "Sucess", 2);
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

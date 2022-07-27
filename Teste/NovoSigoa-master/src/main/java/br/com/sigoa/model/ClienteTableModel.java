package br.com.sigoa.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ClienteTableModel extends AbstractTableModel{

	private List <Cliente> dados = new ArrayList<>();
	private String[] colunas = {"Nome","Email","CPF","Telefone"};
	
	@Override
	public String getColumnName(int column) {
		return colunas[column];
	}
	
	@Override
	public int getRowCount() {
		return dados.size();
	}

	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		switch(coluna) {
			case 0:
				return dados.get(linha).getNome_cliente();
			case 1:
				return dados.get(linha).getEmail_cliente();
			case 2:
				return dados.get(linha).getCpf_cliente();
			case 3:
				return dados.get(linha).getTelefone_cliente();		
		}
		return null;
	}
}

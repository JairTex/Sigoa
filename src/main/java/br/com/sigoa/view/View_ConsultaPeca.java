 package br.com.sigoa.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;

import br.com.sigoa.dao.PecaDAO;
import br.com.sigoa.model.Peca;

public class View_ConsultaPeca extends JInternalFrame {
	private JTextField txt_Arg;
	private static JTable Jt_Peca;

	//INICIALIZAÇÃO
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View_ConsultaPeca frame = new View_ConsultaPeca();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//CRIAÇÃO DO FRAME
	public View_ConsultaPeca() {
		getContentPane().setBackground(SystemColor.inactiveCaption);
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent e) {
				
				TelaPrincipal.menuEstoque=true;
				
				DefaultTableModel mpl = (DefaultTableModel) Jt_Peca.getModel();
				int l=mpl.getRowCount();
				
				if(l>0) {
					while(l>0) {
						((DefaultTableModel) Jt_Peca.getModel()).removeRow(l-1);
						l--;
					}
				}
				try {
				ResultSet rs = PecaDAO.carregaTabela();
				DefaultTableModel mp =(DefaultTableModel) Jt_Peca.getModel();
				
				while(rs.next()) {
					String Coluna0 = rs.getString("id_peca").toString().trim();
					String Coluna1 = rs.getString("nome_peca").toString().trim();
					String Coluna2 = rs.getString("fabricante_peca").toString().trim();
					String Coluna3 = rs.getString("valor_peca").toString().trim();
					String Coluna4 = rs.getString("quantidade_peca").toString().trim();
					
					mp.addRow(new String[] {Coluna0,Coluna1,Coluna2,Coluna3,Coluna4});
				}
			}catch(SQLException erro) {
				//JOptionPane.showMessageDialog(this, "Ocorreu um erro:"+ erro, "Preencher Item", 2);
			}
			tamanhocolunas();
			Jt_Peca.setAutoCreateRowSorter(true);
			}
		});
		setIconifiable(true);
		setMaximizable(true);
		setTitle("Menu Clientes");
		setBounds(300, 20, 860, 600);
		
		JLabel lbl_peca = new JLabel("PEÇAS");
		lbl_peca.setIcon(new ImageIcon(View_ConsultaPeca.class.getResource("/Images/Pe\u00E7as.png")));
		lbl_peca.setBounds(301, 11, 245, 52);
		lbl_peca.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_peca.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lblNewLabel = new JLabel("Consulta por:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(34, 63, 76, 23);
		
		JLabel lblDigiteOArgumento = new JLabel("Digite o argumento:");
		lblDigiteOArgumento.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDigiteOArgumento.setBounds(128, 63, 139, 23);
		
		JComboBox cmb_TipoP = new JComboBox();
		cmb_TipoP.setBounds(34, 85, 76, 30);
		cmb_TipoP.setModel(new DefaultComboBoxModel(new String[] {"ID", "Nome", "Fabricante"}));
		
		txt_Arg = new JTextField();
		txt_Arg.setBounds(128, 85, 301, 30);
		txt_Arg.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String tipo=" ";
				
				String escolha = cmb_TipoP.getSelectedItem().toString().trim();
				
				if (escolha.equals("ID")) {
					tipo = " " + "id_peca";
				}
				if (escolha.equals("Nome")) {
					tipo = " " + "nome_peca";
				}
				if (escolha.equals("Fabricante")) {
					tipo = " " + "fabricante_peca";
				}
				String Arg = txt_Arg.getText();
				
				DefaultTableModel mpl = (DefaultTableModel) Jt_Peca.getModel();
				int l = mpl.getRowCount();
				
				if(l>0) {
					while(l>0) {
						((DefaultTableModel) Jt_Peca.getModel()).removeRow(l-1);	
						l--;
					}
				}
				try{
					ResultSet rs = PecaDAO.carregaTabela(tipo, Arg);
					
					DefaultTableModel mp =(DefaultTableModel) Jt_Peca.getModel();
					
					while(rs.next()) {
						String Coluna0 = rs.getString("id_peca").toString().trim();
						String Coluna1 = rs.getString("nome_peca").toString().trim();
						String Coluna2 = rs.getString("fabricante_peca").toString().trim();
						String Coluna3 = rs.getString("valor_peca").toString().trim();
						String Coluna4 = rs.getString("quantidade_peca").toString().trim();
						
						mp.addRow(new String[] {Coluna0,Coluna1,Coluna2,Coluna3,Coluna4});
					}
				}catch(SQLException erro) {
					
				}
				tamanhocolunas();
				Jt_Peca.setAutoCreateRowSorter(true);
			}
		});
		txt_Arg.setColumns(10);
		
		JButton btn_atualizar = new JButton("");
		btn_atualizar.setIcon(new ImageIcon(View_ConsultaPeca.class.getResource("/Images/update.png")));
		btn_atualizar.setToolTipText("Atualizar tabela.");
		btn_atualizar.setBounds(761, 85, 50, 30);
		btn_atualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					listarJTable();
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 131, 777, 343);
		
		JButton btn_editar = new JButton("Editar");
		btn_editar.setIcon(new ImageIcon(View_ConsultaPeca.class.getResource("/Images/editar.png")));
		btn_editar.setBounds(175, 489, 123, 30);
		btn_editar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Jt_Peca.getSelectedRow() != -1) {
					Peca peca = new Peca();
					String id_middle = Jt_Peca.getValueAt(Jt_Peca.getSelectedRow(), 0).toString();
					Integer id_final = Integer.parseInt(id_middle);
					
					peca.setId_peca(id_final);
					peca.setNome_peca(Jt_Peca.getValueAt(Jt_Peca.getSelectedRow(), 1).toString());
					peca.setFabricante_peca(Jt_Peca.getValueAt(Jt_Peca.getSelectedRow(), 2).toString());
					
					String valor_middle = Jt_Peca.getValueAt(Jt_Peca.getSelectedRow(), 3).toString();
					Double valor_final = Double.parseDouble(valor_middle);
					peca.setValor_peca(valor_final);
					
					String quantidade_middle = Jt_Peca.getValueAt(Jt_Peca.getSelectedRow(),4).toString();
					Integer quantidade_final = Integer.parseInt(quantidade_middle);
					peca.setQuantidade_peca(quantidade_final);
					
					TelaCadastroPeca tcp = new TelaCadastroPeca(peca.getId_peca(), peca.getNome_peca(), peca.getFabricante_peca(), peca.getValor_peca(), peca.getQuantidade_peca());
					
					tcp.setVisible(true);
				}
			}
		});
		
		JButton btn_Nova = new JButton("Nova");
		btn_Nova.setIcon(new ImageIcon(View_ConsultaPeca.class.getResource("/Images/adicionar.png")));
		btn_Nova.setBounds(44, 489, 123, 30);
		btn_Nova.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				br.com.sigoa.controller.ControllerPeca.abrirTelaCadastroPeca();
			}
		});
		
		JButton btn_sair = new JButton("Sair");
		btn_sair.setIcon(new ImageIcon(View_ConsultaPeca.class.getResource("/Images/voltar.png")));
		btn_sair.setBounds(711, 491, 123, 30);
		btn_sair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaPrincipal.menuEstoque=false;
				View_ConsultaPeca.this.dispose();
			}
		});
		getContentPane().setLayout(null);
		
		Jt_Peca = new JTable();
		Jt_Peca.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"ID", "NOME", "FABRICANTE", "VALOR (R$)", "QUANTIDADE"
				}
			) {
				boolean[] columnEditables = new boolean[] {
					false, false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
		Jt_Peca.setBackground(SystemColor.menu);
		scrollPane.setViewportView(Jt_Peca);
		getContentPane().add(scrollPane);
		getContentPane().add(btn_editar);
		getContentPane().add(btn_Nova);
		getContentPane().add(btn_sair);
		getContentPane().add(cmb_TipoP);
		getContentPane().add(txt_Arg);
		getContentPane().add(btn_atualizar);
		getContentPane().add(lblNewLabel);
		getContentPane().add(lblDigiteOArgumento);
		getContentPane().add(lbl_peca);
		
		JButton btn_add_no_estoque = new JButton("Atualizar Estoque");
		btn_add_no_estoque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Peca peca = new Peca();
				String id_middle = Jt_Peca.getValueAt(Jt_Peca.getSelectedRow(), 0).toString();
				Integer id_final = Integer.parseInt(id_middle);
				
				peca.setId_peca(id_final);
				peca.setNome_peca(Jt_Peca.getValueAt(Jt_Peca.getSelectedRow(), 1).toString());
				peca.setFabricante_peca(Jt_Peca.getValueAt(Jt_Peca.getSelectedRow(), 2).toString());
				
				String valor_middle = Jt_Peca.getValueAt(Jt_Peca.getSelectedRow(), 3).toString();
				Double valor_final = Double.parseDouble(valor_middle);
				peca.setValor_peca(valor_final);
				
				String quantidade_middle = Jt_Peca.getValueAt(Jt_Peca.getSelectedRow(),4).toString();
				Integer quantidade_final = Integer.parseInt(quantidade_middle);
				peca.setQuantidade_peca(quantidade_final);

				Object[] options = { "Saída", "Entrada" }; 
				Integer option = JOptionPane.showOptionDialog(null, "Selecione a operação: \n Peça: " + peca.getNome_peca() + "."
				, "", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				
				if (option == 0) {
					String quantidade = JOptionPane.showInputDialog(null, "Quantas peças saíram do estoque?\nPeça: " + peca.getNome_peca() + ".");
					Integer quantidade_int = Integer.parseInt(quantidade);
					peca.setQuantidade_peca(peca.getQuantidade_peca() - quantidade_int);
					PecaDAO.UPDATE(peca);
				}
				if (option == 1) {
					String quantidade = JOptionPane.showInputDialog(null, "Quantas peças entraram no estoque?\nPeça: " + peca.getNome_peca() + ".");
					Integer quantidade_int = Integer.parseInt(quantidade);
					peca.setQuantidade_peca(peca.getQuantidade_peca() + quantidade_int);
					PecaDAO.UPDATE(peca);
				}
			}
		});
		btn_add_no_estoque.setIcon(new ImageIcon(View_ConsultaPeca.class.getResource("/Images/atualizar.png")));
		btn_add_no_estoque.setBounds(308, 489, 158, 30);
		getContentPane().add(btn_add_no_estoque);
	}
	
	// --------------------------MÉTODOS LOCAIS----------------------------//
	public void tamanhocolunas() {
		Jt_Peca.getColumnModel().getColumn(0).setMinWidth(100);
		Jt_Peca.getColumnModel().getColumn(1).setMinWidth(250);
	}
	
	public static void listarJTable(){
		DefaultTableModel modelo = (DefaultTableModel) Jt_Peca.getModel();
		modelo.setNumRows(0);
		PecaDAO Cdao = new PecaDAO();
		
		for (Peca c: Cdao.listar()){
			
			modelo.addRow(new Object[] {
				c.getId_peca(),
				c.getNome_peca(),
				c.getFabricante_peca(),
				c.getValor_peca(),
				c.getQuantidade_peca()
			});
		}
	} //Fim do Método Listar JTable
}
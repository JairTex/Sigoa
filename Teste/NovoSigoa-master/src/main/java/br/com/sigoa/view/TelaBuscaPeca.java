package br.com.sigoa.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import br.com.sigoa.dao.ClienteDAO;
import br.com.sigoa.dao.PecaDAO;
import br.com.sigoa.model.Cliente;
import br.com.sigoa.model.Peca;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TelaBuscaPeca extends JFrame {

	private JPanel contentPane;
	private JTextField txt_Arg;
	private static JTable Jt_Peca;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaBuscaPeca frame = new TelaBuscaPeca();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
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

	//---------------------------CRIAÇÃO DO FRAME--------------------------//
	public TelaBuscaPeca() {
		
		//Carregando Jtable ao iniciar a Frame
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
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
		});	// Fim carregar Jtable
		
		setTitle("SIGOA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1920, 1080);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lbl_pecas = new JLabel("PE\u00C7AS");
		lbl_pecas.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_pecas.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JButton btn_Atualizar = new JButton("ATUALIZAR");
		btn_Atualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listarJTable();
			}
		});
		
		JComboBox cmb_TipoP = new JComboBox();
		cmb_TipoP.setModel(new DefaultComboBoxModel(new String[] {"ID", "Nome", "Fabricante"}));
		
		txt_Arg = new JTextField();
		txt_Arg.addKeyListener(new KeyAdapter() {
			
			// PESQUISANDO POR COMPONENTE
			
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
		}); // Fim da pesquisa por Componente
		txt_Arg.setColumns(10);
		
		JButton btn_editar = new JButton("Editar");
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
					
					String quantidade_middle = Jt_Peca.getValueAt(Jt_Peca.getSelectedRow(), 4).toString();
					Integer quantidade_final = Integer.parseInt(quantidade_middle);
					peca.setQuantidade_peca(quantidade_final);
					
					TelaCadastroPeca tcp = new TelaCadastroPeca(peca.getId_peca(), peca.getNome_peca(), peca.getFabricante_peca(), peca.getValor_peca(), peca.getQuantidade_peca());
					
					tcp.setVisible(true);
				}
			}
		});
		
		JButton btn_adicionar = new JButton("Adicionar");
		btn_adicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				br.com.sigoa.controller.ControllerTelaCadastroPeca.abrirTelaCadastroPeca();
			}
		});
		
		JButton btn_remover = new JButton("Remover");
		
		JButton btn_voltar = new JButton("Sair");
		btn_voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				br.com.sigoa.controller.ControllerTelaCadastroPeca.voltar();
				TelaBuscaPeca.this.dispose();
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblNewLabel = new JLabel("Consulta por:");
		
		JLabel lblDigiteOArgumento = new JLabel("Digite o argumento:");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(90)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(239)
							.addComponent(lbl_pecas, GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
							.addGap(387))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
											.addGap(11))
										.addComponent(cmb_TipoP, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(txt_Arg, GroupLayout.PREFERRED_SIZE, 465, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(btn_Atualizar, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblDigiteOArgumento, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
											.addGap(695))))
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 928, GroupLayout.PREFERRED_SIZE))
							.addGap(131)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btn_editar, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
								.addComponent(btn_adicionar, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
								.addComponent(btn_remover, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
								.addComponent(btn_voltar, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE))
							.addGap(88))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(64)
							.addComponent(lbl_pecas, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
							.addGap(13)
							.addComponent(btn_Atualizar))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDigiteOArgumento, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
							.addGap(6)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(cmb_TipoP, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(txt_Arg, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btn_editar)
							.addGap(12)
							.addComponent(btn_adicionar)
							.addGap(11)
							.addComponent(btn_remover)
							.addPreferredGap(ComponentPlacement.RELATED, 378, Short.MAX_VALUE)
							.addComponent(btn_voltar)
							.addGap(90))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 329, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		
		Jt_Peca = new JTable();
		Jt_Peca.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "NOME", "FABRICANTE", "VALOR", "QUANTIDADE"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(Jt_Peca);
		contentPane.setLayout(gl_contentPane);
	}
}
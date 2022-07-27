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
import br.com.sigoa.dao.ServicoDAO;
import br.com.sigoa.model.Peca;
import br.com.sigoa.model.Servico;

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
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TelaBuscaServico extends JFrame {

	private JPanel contentPane;
	private JTextField txt_Arg;
	private static JTable Jt_Servico;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaBuscaServico frame = new TelaBuscaServico();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	// --------------------------MÉTODOS LOCAIS----------------------------//		
	public static void listarJTable(){
		DefaultTableModel modelo = (DefaultTableModel) Jt_Servico.getModel();
		ServicoDAO Sdao = new ServicoDAO();
		modelo.setNumRows(0);
		
		for (Servico s: Sdao.listar()){
			
			modelo.addRow(new Object[] {
				s.getId_servico(),
				s.getNome_servico(),
				s.getValor_servico()
			});
		}
	}

	public void tamanhocolunas() {
		Jt_Servico.getColumnModel().getColumn(0).setMinWidth(100);
		Jt_Servico.getColumnModel().getColumn(1).setMinWidth(250);
	}
	
	//---------------------------CRIAÇÃO DO FRAME--------------------------//
	public TelaBuscaServico() {
		addWindowListener(new WindowAdapter() {
			
			//Carregando Jtable ao iniciar a Frame
			@Override
			public void windowOpened(WindowEvent e) {
				DefaultTableModel mpl = (DefaultTableModel) Jt_Servico.getModel();
				int l=mpl.getRowCount();
				
				if(l>0) {
					while(l>0) {
						((DefaultTableModel) Jt_Servico.getModel()).removeRow(l-1);
						l--;
					}
				}
				try {
					
				ResultSet rs = ServicoDAO.carregaTabela();
				
				DefaultTableModel mp =(DefaultTableModel) Jt_Servico.getModel();
				
				while(rs.next()) {
					String Coluna0 = rs.getString("id_servico").toString().trim();
					String Coluna1 = rs.getString("nome_servico").toString().trim();
					String Coluna2 = rs.getString("valor_servico").toString().trim();
					mp.addRow(new String[] {Coluna0,Coluna1,Coluna2});
				}
			}catch(SQLException erro) {
				//JOptionPane.showMessageDialog(this, "Ocorreu um erro:"+ erro, "Preencher Item", 2);
			}
			tamanhocolunas();
			Jt_Servico.setAutoCreateRowSorter(true);
		}
		});
		
		setTitle("SIGOA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1920, 1080);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lbl_Servios = new JLabel("SERVICOS");
		lbl_Servios.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Servios.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JButton btn_Atualizar = new JButton("ATUALIZAR");
		btn_Atualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listarJTable();
			}
		});
		
		JComboBox cmb_TipoS = new JComboBox();
		cmb_TipoS.setModel(new DefaultComboBoxModel(new String[] {"ID", "Nome"}));
		
		txt_Arg = new JTextField();
		txt_Arg.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String tipo=" ";
				
				String escolha = cmb_TipoS.getSelectedItem().toString().trim();
				
				if (escolha.equals("ID")) {
					tipo = " " + "id_servico";
				}
				if (escolha.equals("Nome")) {
					tipo = " " + "nome_servico";
				}
				String Arg = txt_Arg.getText();
				
				DefaultTableModel mpl = (DefaultTableModel) Jt_Servico.getModel();
				int l = mpl.getRowCount();
				
				if(l>0) {
					while(l>0) {
						((DefaultTableModel) Jt_Servico.getModel()).removeRow(l-1);	
						l--;
					}
				}
				try{
					ResultSet rs = ServicoDAO.carregaTabela(tipo, Arg);
					
					DefaultTableModel mp =(DefaultTableModel) Jt_Servico.getModel();
					
					while(rs.next()) {
						String Coluna0 = rs.getString("id_servico").toString().trim();
						String Coluna1 = rs.getString("nome_servico").toString().trim();
						String Coluna2 = rs.getString("valor_servico").toString().trim();
						
						mp.addRow(new String[] {Coluna0,Coluna1,Coluna2});
					}
				}catch(SQLException erro) {
					
				}
				tamanhocolunas();
				Jt_Servico.setAutoCreateRowSorter(true);
			}

		});
		txt_Arg.setColumns(10);
		
		JButton btn_Editar = new JButton("Editar");
		btn_Editar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Jt_Servico.getSelectedRow() != -1) {
					Servico servico = new Servico();
					
					String id_middle = Jt_Servico.getValueAt(Jt_Servico.getSelectedRow(), 0).toString();
					Integer id_final = Integer.parseInt(id_middle);
					servico.setId_servico(id_final);
					
					servico.setNome_servico(Jt_Servico.getValueAt(Jt_Servico.getSelectedRow(), 1).toString());
					
					String valor_middle = Jt_Servico.getValueAt(Jt_Servico.getSelectedRow(), 2).toString();
					Double valor_final = Double.parseDouble(valor_middle);
					servico.setValor_servico(valor_final);
					
					try {
						TelaCadastroServico tcs = new TelaCadastroServico(servico.getId_servico(), servico.getNome_servico(), servico.getValor_servico());
						tcs.setVisible(true);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
		});
		
		JButton btn_Adicionar = new JButton("Adicionar");
		btn_Adicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					br.com.sigoa.controller.ControllerTelaCadastroServico.abrirTelaCadastroServico();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		JButton btn_Remover = new JButton("Remover");
		
		JButton btn_Voltar = new JButton("Sair");
		btn_Voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				br.com.sigoa.controller.ControllerTelaCadastroServico.voltar();
				TelaBuscaServico.this.dispose();
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblNewLabel = new JLabel("Consulta por:");
		
		JLabel lblDigiteOArgumento = new JLabel("Digite o argumento:");
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(119, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(cmb_TipoS, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
							.addGap(43)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(txt_Arg, GroupLayout.PREFERRED_SIZE, 466, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 217, Short.MAX_VALUE)
									.addComponent(btn_Atualizar, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblDigiteOArgumento, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 669, Short.MAX_VALUE))))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 927, GroupLayout.PREFERRED_SIZE))
					.addGap(129)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btn_Voltar, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btn_Editar, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
						.addComponent(btn_Adicionar, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
						.addComponent(btn_Remover, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE))
					.addGap(62))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(458)
					.addComponent(lbl_Servios, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(675, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lbl_Servios, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDigiteOArgumento, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txt_Arg, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(cmb_TipoS, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn_Atualizar))
					.addGap(26)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btn_Editar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btn_Adicionar)
							.addGap(11)
							.addComponent(btn_Remover)
							.addPreferredGap(ComponentPlacement.RELATED, 372, Short.MAX_VALUE)
							.addComponent(btn_Voltar)
							.addGap(136))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 358, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		
		Jt_Servico = new JTable();
		Jt_Servico.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "NOME", "VALOR"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(Jt_Servico);
		contentPane.setLayout(gl_contentPane);
	}
}
package br.com.sigoa.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;

import br.com.sigoa.dao.OsDAO;
import br.com.sigoa.dao.PecaDAO;
import br.com.sigoa.dao.ServicoDAO;
import br.com.sigoa.model.OrdemDeServico;
import br.com.sigoa.model.Peca;
import br.com.sigoa.model.Servico;

public class Tela_AddServicoOS extends JInternalFrame {

	private JPanel contentPane;
	private JTextField txt_Arg;
	private JTable Jt_Servico;
	
	//INICIALIZAÇÃO
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrdemDeServico os = new OrdemDeServico();
					Tela_AddPecaOS frame = new Tela_AddPecaOS(os);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//-------------------------MÉTODOS LOCAIS-----------------------//
	public void tamanhocolunas() {
		Jt_Servico.getColumnModel().getColumn(0).setMinWidth(100);
		Jt_Servico.getColumnModel().getColumn(1).setMinWidth(250);
	}
	//-----------------------------FIM------------------------------//
	
	//CRIAÇÃO DO FRAME
	public Tela_AddServicoOS(OrdemDeServico os) {
		setClosable(true);
		setTitle("SIGOA");
		getContentPane().setBackground(SystemColor.inactiveCaption);
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent e) {
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
		setBounds(100, 100, 730, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Consulta por:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(37, 66, 76, 23);
		
		JLabel lblDigiteOArgumento = new JLabel("Digite o argumento:");
		lblDigiteOArgumento.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDigiteOArgumento.setBounds(131, 66, 146, 23);
		
		JComboBox cmb_Tipo = new JComboBox();
		cmb_Tipo.setBounds(37, 91, 76, 30);
		cmb_Tipo.setModel(new DefaultComboBoxModel(new String[] {"ID", "Nome"}));
		
		txt_Arg = new JTextField();
		txt_Arg.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txt_Arg.setBounds(131, 91, 301, 30);
		txt_Arg.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String tipo=" ";
				
				String escolha = cmb_Tipo.getSelectedItem().toString().trim();
				
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
		
		JButton btn_Selecionar = new JButton("SELECIONAR");
		btn_Selecionar.setIcon(new ImageIcon(Tela_AddPecaOS.class.getResource("/Images/Sign-Select-icon.png")));
		btn_Selecionar.setBounds(537, 91, 135, 30);
		btn_Selecionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Servico servico = new Servico();
				String id_middle = Jt_Servico.getValueAt(Jt_Servico.getSelectedRow(), 0).toString();
				Integer id_final = Integer.parseInt(id_middle);
				servico.setId_servico(id_final);
				
				servico.setNome_servico(Jt_Servico.getValueAt(Jt_Servico.getSelectedRow(), 1).toString());
				
				String valor_middle = Jt_Servico.getValueAt(Jt_Servico.getSelectedRow(), 2).toString();
				Double valor_final = Double.parseDouble(valor_middle);
				servico.setValor_servico(valor_final);
				
				String quantidade = JOptionPane.showInputDialog(null, "Digite a quantidade desse serviço que deseja adicionar: ");
				
				servico.setQuantidade_servico_os(Integer.parseInt(quantidade));
				
				servico.setValor_servico_os(servico.getValor_servico() * servico.getQuantidade_servico_os());
				
				OsDAO.CREATE_SERVICO_OS(os, servico);
				
				//Tela_VisualizarOS tvos = new Tela_VisualizarOS(os);
				//tvos.setExtendedState(Tela_VisualizarOS.MAXIMIZED_BOTH);
				//tvos.setVisible(true);
				dispose();
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 132, 635, 307);
		
		JButton btn_sair = new JButton("Voltar para a OS");
		btn_sair.setIcon(new ImageIcon(Tela_AddPecaOS.class.getResource("/Images/voltar.png")));
		btn_sair.setBounds(518, 450, 154, 30);
		btn_sair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		Jt_Servico = new JTable();
		Jt_Servico.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"ID", "NOME", "VALOR (R$)"
				}
			) {
				boolean[] columnEditables = new boolean[] {
					false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
		Jt_Servico.setBackground(SystemColor.menu);
		scrollPane.setViewportView(Jt_Servico);
		contentPane.setLayout(null);
		contentPane.add(lblNewLabel);
		contentPane.add(lblDigiteOArgumento);
		contentPane.add(cmb_Tipo);
		contentPane.add(txt_Arg);
		contentPane.add(btn_Selecionar);
		contentPane.add(scrollPane);
		contentPane.add(btn_sair);
		
		JLabel lbl_servicos = new JLabel("SERVI\u00C7OS");
		lbl_servicos.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_servicos.setFont(new Font("Tahoma", Font.BOLD, 18));
		lbl_servicos.setBounds(231, 28, 245, 22);
		contentPane.add(lbl_servicos);
	}
}
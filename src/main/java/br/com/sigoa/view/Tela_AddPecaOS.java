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
import br.com.sigoa.model.OrdemDeServico;
import br.com.sigoa.model.Peca;

public class Tela_AddPecaOS extends JInternalFrame {

	private JPanel contentPane;
	private JTextField txt_Arg;
	private JTable Jt_Peca;
	
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
		Jt_Peca.getColumnModel().getColumn(0).setMinWidth(100);
		Jt_Peca.getColumnModel().getColumn(1).setMinWidth(250);
	}
	//-----------------------------FIM------------------------------//
	
	//CRIAÇÃO DO FRAME
	public Tela_AddPecaOS(OrdemDeServico os) {
		setClosable(true);
		setTitle("SIGOA");
		getContentPane().setBackground(SystemColor.inactiveCaption);
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent e) {
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
		cmb_Tipo.setModel(new DefaultComboBoxModel(new String[] {"ID", "Nome", "Fabricante"}));
		
		txt_Arg = new JTextField();
		txt_Arg.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txt_Arg.setBounds(131, 91, 301, 30);
		txt_Arg.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String tipo=" ";
				
				String escolha = cmb_Tipo.getSelectedItem().toString().trim();
				
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
		
		JButton btn_Selecionar = new JButton("SELECIONAR");
		btn_Selecionar.setIcon(new ImageIcon(Tela_AddPecaOS.class.getResource("/Images/Sign-Select-icon.png")));
		btn_Selecionar.setBounds(537, 91, 135, 30);
		btn_Selecionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Peca peca = new Peca();
				String id_middle = Jt_Peca.getValueAt(Jt_Peca.getSelectedRow(), 0).toString();
				Integer id_final = Integer.parseInt(id_middle);
				peca.setId_peca(id_final);
				
				peca.setNome_peca(Jt_Peca.getValueAt(Jt_Peca.getSelectedRow(), 1).toString());
				
				String valor_middle = Jt_Peca.getValueAt(Jt_Peca.getSelectedRow(), 3).toString();
				Double valor_final = Double.parseDouble(valor_middle);
				peca.setValor_peca(valor_final);
				
				String quantidade = JOptionPane.showInputDialog(null, "Digite a quantidade de peçcas que dejse adicionar: ");
				
				peca.setQuantidade_peca_os(Integer.parseInt(quantidade));
				
				peca.setValor_peca_os(peca.getValor_peca() * peca.getQuantidade_peca_os());
				
				OsDAO.CREATE_PECA_OS_AUX();
				OsDAO.CREATE_PECA_OS(os, peca);
				OsDAO.CREATE_PECA_OS_AUX_II();
				
				dispose();
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 132, 635, 307);
		
		JLabel lbl_Peca = new JLabel("PE\u00C7AS");
		lbl_Peca.setBounds(217, 33, 245, 22);
		lbl_Peca.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Peca.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JButton btn_sair = new JButton("Voltar para a OS");
		btn_sair.setIcon(new ImageIcon(Tela_AddPecaOS.class.getResource("/Images/voltar.png")));
		btn_sair.setBounds(518, 450, 154, 30);
		btn_sair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
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
		contentPane.setLayout(null);
		contentPane.add(lbl_Peca);
		contentPane.add(lblNewLabel);
		contentPane.add(lblDigiteOArgumento);
		contentPane.add(cmb_Tipo);
		contentPane.add(txt_Arg);
		contentPane.add(btn_Selecionar);
		contentPane.add(scrollPane);
		contentPane.add(btn_sair);
	}
}
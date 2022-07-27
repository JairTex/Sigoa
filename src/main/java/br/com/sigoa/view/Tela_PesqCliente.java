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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.com.sigoa.dao.ClienteDAO;
import br.com.sigoa.model.Cliente;

public class Tela_PesqCliente extends JFrame {

	private JPanel contentPane;
	private JTextField txt_Arg;
	private JTable JtClientes;
	public static boolean OS_CadCliente;

	// INICIALIZAÇÃO
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela_PesqCliente frame = new Tela_PesqCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// CRIAÇÃO DO FRAME
	public Tela_PesqCliente() {
		setTitle("SIGOA");
		getContentPane().setBackground(SystemColor.inactiveCaption);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				DefaultTableModel mpl = (DefaultTableModel) JtClientes.getModel();
				int l = mpl.getRowCount();
				if(l>0) {
					while(l>0) {
						((DefaultTableModel) JtClientes.getModel()).removeRow(l-1);
						l--;
					}
				}
				try {
				ResultSet rs = ClienteDAO.carregaTabela();
				DefaultTableModel mp =(DefaultTableModel) JtClientes.getModel();
				while(rs.next()) {
					String Coluna0 = rs.getString("id_cliente").toString().trim();
					String Coluna1 = rs.getString("nome_cliente").toString().trim();
					String Coluna2 = rs.getString("email_cliente").toString().trim();
					String Coluna3 = rs.getString("cpf_cliente").toString().trim();
					String Coluna4 = rs.getString("telefone_cliente").toString().trim();
					
					mp.addRow(new String[] {Coluna0,Coluna1,Coluna2,Coluna3,Coluna4});
				}
			}catch(SQLException erro) {
				//JOptionPane.showMessageDialog(this, "Ocorreu um erro:"+ erro, "Preencher Item", 2);
			}
			tamanhocolunas();
			JtClientes.setAutoCreateRowSorter(true);
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 730, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Consulta por:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(37, 69, 76, 23);
		
		JLabel lblDigiteOArgumento = new JLabel("Digite o argumento:");
		lblDigiteOArgumento.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDigiteOArgumento.setBounds(131, 69, 139, 23);
		
		JComboBox cmb_Tipo = new JComboBox();
		cmb_Tipo.setBounds(37, 91, 76, 30);
		cmb_Tipo.setModel(new DefaultComboBoxModel(new String[] {"ID", "Nome", "CPF"}));
		
		txt_Arg = new JTextField();
		txt_Arg.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txt_Arg.setBounds(131, 91, 301, 30);
		txt_Arg.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				DefaultTableModel mpl = (DefaultTableModel) JtClientes.getModel();
				int l = mpl.getRowCount();
				if(l>0) {
					while(l>0) {
						((DefaultTableModel) JtClientes.getModel()).removeRow(l-1);	
						l--;
					}
				}
				String tipo=" ";
				String escolha = cmb_Tipo.getSelectedItem().toString().trim();
				if (escolha.equals("ID")) {
					tipo = " " + "id_cliente";
				}
				if (escolha.equals("Nome")) {
					tipo = " " + "nome_cliente";
				}
				if (escolha.equals("CPF")) {
					tipo = " " + "cpf_cliente";
				}
				String Arg = txt_Arg.getText();
				try{
					ResultSet rs = ClienteDAO.carregaTabela(tipo, Arg);
					DefaultTableModel mp =(DefaultTableModel) JtClientes.getModel();
					while(rs.next()) {
						String Coluna0 = rs.getString("id_cliente").toString().trim();
						String Coluna1 = rs.getString("nome_cliente").toString().trim();
						String Coluna2 = rs.getString("email_cliente").toString().trim();
						String Coluna3 = rs.getString("cpf_cliente").toString().trim();
						String Coluna4 = rs.getString("telefone_cliente").toString().trim();
						
						mp.addRow(new String[] {Coluna0,Coluna1,Coluna2,Coluna3,Coluna4});
					}
				}catch(SQLException erro) {
				}
				tamanhocolunas();
				JtClientes.setAutoCreateRowSorter(true);
			}
		});
		txt_Arg.setColumns(10);
		
		JButton btn_Selecionar = new JButton("SELECIONAR");
		btn_Selecionar.setIcon(new ImageIcon(Tela_PesqCliente.class.getResource("/Images/Sign-Select-icon.png")));
		btn_Selecionar.setBounds(541, 91, 131, 30);
		btn_Selecionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cliente cliente = new Cliente();
				String id_middle = JtClientes.getValueAt(JtClientes.getSelectedRow(), 0).toString();
				Integer id_final = Integer.parseInt(id_middle);
				cliente.setId_cliente(id_final);
				
				cliente.setNome_cliente(JtClientes.getValueAt(JtClientes.getSelectedRow(), 1).toString());
				cliente.setEmail_cliente(JtClientes.getValueAt(JtClientes.getSelectedRow(), 2).toString());
				cliente.setCpf_cliente(JtClientes.getValueAt(JtClientes.getSelectedRow(), 3).toString());
				cliente.setTelefone_cliente(JtClientes.getValueAt(JtClientes.getSelectedRow(), 4).toString());
				
				TelaCadastroOSs tcos = new TelaCadastroOSs(cliente);
				tcos.setVisible(true);
				dispose();
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 132, 635, 307);
		
		JLabel lbl_clientes = new JLabel("CLIENTES");
		lbl_clientes.setBounds(225, 36, 245, 22);
		lbl_clientes.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_clientes.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JButton btn_sair = new JButton("SAIR");
		btn_sair.setIcon(new ImageIcon(Tela_PesqCliente.class.getResource("/Images/voltar.png")));
		btn_sair.setBounds(564, 450, 108, 30);
		btn_sair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		JtClientes = new JTable();
		JtClientes.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"ID", "NOME", "EMAIL", "CPF", "TELEFONE"
				}
			) {
				boolean[] columnEditables = new boolean[] {
					false, false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
		JtClientes.setBackground(SystemColor.menu);
		scrollPane.setViewportView(JtClientes);
		contentPane.setLayout(null);
		contentPane.add(lbl_clientes);
		contentPane.add(lblNewLabel);
		contentPane.add(lblDigiteOArgumento);
		contentPane.add(cmb_Tipo);
		contentPane.add(txt_Arg);
		contentPane.add(btn_Selecionar);
		contentPane.add(scrollPane);
		contentPane.add(btn_sair);
	}
	
	//-------------------------- MÉTODOS LOCAIS ---------------------------//
	public void tamanhocolunas() {
		JtClientes.getColumnModel().getColumn(0).setMinWidth(100);
		JtClientes.getColumnModel().getColumn(1).setMinWidth(250);
	}
}

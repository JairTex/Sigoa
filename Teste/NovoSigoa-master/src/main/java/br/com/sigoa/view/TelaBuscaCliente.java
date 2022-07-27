package br.com.sigoa.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import br.com.sigoa.dao.ClienteDAO;
import br.com.sigoa.model.Cliente;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.ScrollPane;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TelaBuscaCliente extends JFrame {

	private JPanel contentPane;
	private JTextField txt_Arg;
	private static JTable Jt_Clientes;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				
				try {
					TelaBuscaCliente frame = new TelaBuscaCliente();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	// --------------------------MÉTODOS LOCAIS----------------------------//
	public void tamanhocolunas() {
		Jt_Clientes.getColumnModel().getColumn(0).setMinWidth(100);
		Jt_Clientes.getColumnModel().getColumn(1).setMinWidth(250);
	}
	
	public static void listarJTable(){
		DefaultTableModel modelo = (DefaultTableModel) Jt_Clientes.getModel();
		modelo.setNumRows(0);
		ClienteDAO Cdao = new ClienteDAO();
		
		for (Cliente c: Cdao.listar()){
			
			modelo.addRow(new Object[] {
				c.getId_cliente(),
				c.getNome_cliente(),
				c.getEmail_cliente(),
				c.getCpf_cliente(),
				c.getTelefone_cliente()
			});
		}
	}
	
	//---------------------------CRIAÇÃO DO FRAME--------------------------//
	public TelaBuscaCliente() {
		addWindowListener(new WindowAdapter() {
			
			//Carregando Jtable ao iniciar a Frame
			@Override
			public void windowOpened(WindowEvent e) {
				DefaultTableModel mpl = (DefaultTableModel) Jt_Clientes.getModel();
				int l = mpl.getRowCount();
				
				if(l>0) {
					while(l>0) {
						((DefaultTableModel) Jt_Clientes.getModel()).removeRow(l-1);
						l--;
					}
				}
				try {
					
				ResultSet rs = ClienteDAO.carregaTabela();
				
				DefaultTableModel mp =(DefaultTableModel) Jt_Clientes.getModel();
				
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
			Jt_Clientes.setAutoCreateRowSorter(true);
		}
		});
		
		
		setTitle("SIGOA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1920, 1080);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Menu");
		menuBar.add(mnNewMenu);
		
		JMenuItem Mn_Cliente = new JMenuItem("Clientes");
		mnNewMenu.add(Mn_Cliente);
		
		JMenuItem Mn_Funcionario = new JMenuItem("Funcion\u00E1rio");
		mnNewMenu.add(Mn_Funcionario);
		
		JMenuItem Mn_Servico = new JMenuItem("Servi\u00E7o");
		mnNewMenu.add(Mn_Servico);
		
		JMenuItem Mn_Estoque = new JMenuItem("Estoque");
		mnNewMenu.add(Mn_Estoque);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Sair");
		mnNewMenu.add(mntmNewMenuItem_6);
		
		JMenu mnNewMenu_3 = new JMenu("Sobre");
		menuBar.add(mnNewMenu_3);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Especifica\u00E7\u00F5es");
		mnNewMenu_3.add(mntmNewMenuItem_7);
		
		JMenu mnNewMenu_4 = new JMenu("Suporte");
		mnNewMenu_3.add(mnNewMenu_4);
		
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Ir para a p\u00E1gina");
		mnNewMenu_4.add(mntmNewMenuItem_8);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lbl_clientes = new JLabel("CLIENTES");
		lbl_clientes.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_clientes.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JButton btn_Todos = new JButton("TODOS");
		btn_Todos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listarJTable();
			}
		});
		
		
		JButton btn_editar = new JButton("Editar");
		btn_editar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//if (Jt_Clientes.getSelectedRow() != -1) {
					try {
						Cliente cliente = new Cliente();
						String id_middle = Jt_Clientes.getValueAt(Jt_Clientes.getSelectedRow(), 0).toString();
						Integer id_final = Integer.parseInt(id_middle);
						cliente.setId_cliente(id_final);
						
						cliente.setNome_cliente(Jt_Clientes.getValueAt(Jt_Clientes.getSelectedRow(), 1).toString());
						cliente.setEmail_cliente(Jt_Clientes.getValueAt(Jt_Clientes.getSelectedRow(), 2).toString());
						cliente.setCpf_cliente(Jt_Clientes.getValueAt(Jt_Clientes.getSelectedRow(), 3).toString());
						cliente.setTelefone_cliente(Jt_Clientes.getValueAt(Jt_Clientes.getSelectedRow(), 4).toString());
						TelaCadastroCliente tcc = new TelaCadastroCliente(cliente.getId_cliente() ,cliente.getNome_cliente(), cliente.getEmail_cliente(),
								cliente.getCpf_cliente(), cliente.getTelefone_cliente());
						
						tcc.setVisible(true);
						
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
						
				}
			//}
		});
		
		JButton btn_adicionar = new JButton("Adicionar");
		btn_adicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					br.com.sigoa.controller.ControllerTelaCadastroCliente.abrirTelaCadastroCliente();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JButton btn_remover = new JButton("Remover");
		
		JLabel lblNewLabel_2 = new JLabel("");
		
		JButton btn_voltar = new JButton("Sair");
		btn_voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				br.com.sigoa.controller.ControllerTelaCadastroCliente.voltar();
				TelaBuscaCliente.this.dispose();
			}
		});
		
		JButton btn_Pesq = new JButton("Pesquisar");
		
		JComboBox cmb_Tipo = new JComboBox();
		cmb_Tipo.setModel(new DefaultComboBoxModel(new String[] {"ID", "Nome", "CPF"}));
		
		txt_Arg = new JTextField();
		
		txt_Arg.addKeyListener(new KeyAdapter() {	
			public void keyReleased(KeyEvent e) {
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
				
				DefaultTableModel mpl = (DefaultTableModel) Jt_Clientes.getModel();
				int l = mpl.getRowCount();
				
				if(l>0) {
					while(l>0) {
						((DefaultTableModel) Jt_Clientes.getModel()).removeRow(l-1);	
						l--;
					}
				}
				try{
					ResultSet rs = ClienteDAO.carregaTabela(tipo, Arg);
					
					DefaultTableModel mp =(DefaultTableModel) Jt_Clientes.getModel();
					
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
				Jt_Clientes.setAutoCreateRowSorter(true);
			}

		});
		txt_Arg.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Consulta por:");
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblDigiteOArgumento = new JLabel("Digite o argumento:");
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(460)
					.addComponent(lbl_clientes, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(800, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(93)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(cmb_Tipo, 0, 76, Short.MAX_VALUE)
									.addGap(18))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNewLabel)
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txt_Arg, GroupLayout.PREFERRED_SIZE, 301, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btn_Pesq)
									.addGap(299)
									.addComponent(btn_Todos, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblDigiteOArgumento, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)))
						.addComponent(scrollPane))
					.addPreferredGap(ComponentPlacement.RELATED, 186, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addComponent(btn_voltar, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btn_editar, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addComponent(btn_remover, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
									.addComponent(btn_adicionar, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)))
							.addGap(62))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
							.addGap(155))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(24)
					.addComponent(lbl_clientes, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDigiteOArgumento, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(9)
							.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(txt_Arg, Alignment.LEADING)
								.addGroup(Alignment.LEADING, gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(cmb_Tipo, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
									.addComponent(btn_Todos, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btn_Pesq, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)))))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btn_editar)
							.addGap(11)
							.addComponent(btn_adicionar)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btn_remover)
							.addGap(75)
							.addComponent(btn_voltar))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 329, GroupLayout.PREFERRED_SIZE)))
		);
		
		Jt_Clientes = new JTable();
		Jt_Clientes.setModel(new DefaultTableModel(
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
		Jt_Clientes.setBackground(SystemColor.menu);
		scrollPane.setViewportView(Jt_Clientes);
		contentPane.setLayout(gl_contentPane);
	}
}
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
import java.text.ParseException;

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

import br.com.sigoa.dao.ClienteDAO;
import br.com.sigoa.model.Cliente;

public class View_ConsultaCliente extends JInternalFrame {
	private JTextField txt_Arg;
	private static JTable Jt_Clientes;

	//INICIALIZAÇÃO
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View_ConsultaCliente frame = new View_ConsultaCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//CRIAÇÃO DO FRAME
	public View_ConsultaCliente() {
		getContentPane().setBackground(SystemColor.inactiveCaption);
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent e) {
				
				TelaPrincipal.menuCliente=true;
				
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
		setTitle("Menu Clientes");
		setBounds(300, 20, 860, 600);
		
		JLabel lbl_clientes = new JLabel("CLIENTES");
		lbl_clientes.setIcon(new ImageIcon(View_ConsultaCliente.class.getResource("/Images/User-Clients-icon.png")));
		lbl_clientes.setBounds(296, 11, 245, 51);
		lbl_clientes.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_clientes.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lblNewLabel = new JLabel("Consulta por:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(34, 63, 76, 23);
		
		JLabel lblDigiteOArgumento = new JLabel("Digite o argumento:");
		lblDigiteOArgumento.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDigiteOArgumento.setBounds(128, 63, 139, 23);
		
		JComboBox cmb_Tipo = new JComboBox();
		cmb_Tipo.setBounds(34, 87, 76, 30);
		cmb_Tipo.setModel(new DefaultComboBoxModel(new String[] {"ID", "Nome", "CPF"}));
		
		txt_Arg = new JTextField();
		txt_Arg.setBounds(128, 87, 301, 30);
		txt_Arg.addKeyListener(new KeyAdapter() {
			@Override
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
		
		JButton btn_Atualizar = new JButton("");
		btn_Atualizar.setBounds(759, 86, 57, 30);
		btn_Atualizar.setToolTipText("Atualizar tabela.");
		btn_Atualizar.setIcon(new ImageIcon(View_ConsultaCliente.class.getResource("/Images/update.png")));
		btn_Atualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					listarJTable();
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 137, 782, 342);
		
		JButton btn_editar = new JButton("Editar");
		btn_editar.setIcon(new ImageIcon(View_ConsultaCliente.class.getResource("/Images/editar.png")));
		btn_editar.setBounds(167, 505, 123, 30);
		btn_editar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
					e1.printStackTrace();
				}
			}
		});
		
		JButton btn_adicionar = new JButton("Novo");
		btn_adicionar.setIcon(new ImageIcon(View_ConsultaCliente.class.getResource("/Images/adicionar.png")));
		btn_adicionar.setBounds(34, 505, 123, 30);
		btn_adicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					br.com.sigoa.controller.ControllerCliente.abrirTelaCadastroCliente();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		JButton btn_remover = new JButton("Remover");
		btn_remover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cliente cliente = new Cliente();
				String id_middle = Jt_Clientes.getValueAt(Jt_Clientes.getSelectedRow(), 0).toString();
				Integer id_final = Integer.parseInt(id_middle);
				cliente.setId_cliente(id_final);
				
				cliente.setNome_cliente(Jt_Clientes.getValueAt(Jt_Clientes.getSelectedRow(), 1).toString());
				
				
				Object[] options = { "Cancelar", "Confirmar" }; 
				Integer option = JOptionPane.showOptionDialog(null, "Deseja remover " + cliente.getNome_cliente() + " dos cliente?"
				, "", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				
				if (option == 0) {
					
				}
				if (option == 1) {
					ClienteDAO.DELETE(cliente);
					
				}
			}
		});
		
		btn_remover.setIcon(new ImageIcon(View_ConsultaCliente.class.getResource("/Images/Remover.png")));
		btn_remover.setBounds(300, 505, 123, 30);
		
		JButton btn_sair = new JButton("SAIR");
		btn_sair.setToolTipText("Retornar a tela principal");
		btn_sair.setIcon(new ImageIcon(View_ConsultaCliente.class.getResource("/Images/voltar.png")));
		btn_sair.setBounds(693, 505, 123, 30);
		btn_sair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaPrincipal.menuCliente=false;
				View_ConsultaCliente.this.dispose();
			}
		});
		getContentPane().setLayout(null);
		
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
		getContentPane().add(scrollPane);
		getContentPane().add(btn_editar);
		getContentPane().add(btn_adicionar);
		getContentPane().add(btn_remover);
		getContentPane().add(btn_sair);
		getContentPane().add(cmb_Tipo);
		getContentPane().add(txt_Arg);
		getContentPane().add(lblNewLabel);
		getContentPane().add(lblDigiteOArgumento);
		getContentPane().add(btn_Atualizar);
		getContentPane().add(lbl_clientes);

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
}
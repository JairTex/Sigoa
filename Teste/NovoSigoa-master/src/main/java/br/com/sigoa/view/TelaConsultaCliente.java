package br.com.sigoa.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.com.sigoa.dao.ClienteDAO;

import java.awt.Point;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.SystemColor;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaConsultaCliente extends JFrame {

	private JPanel TelaConsultaCliente;
	private JTextField txt_Arg;
	private JTable Jt_ConsC;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaConsultaCliente frame = new TelaConsultaCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void tamanhocolunas() {
		Jt_ConsC.getColumnModel().getColumn(0).setMinWidth(100);
		Jt_ConsC.getColumnModel().getColumn(1).setMinWidth(250);
	}

	/**
	 * Create the frame.
	 */
	public TelaConsultaCliente() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				DefaultTableModel mpl = (DefaultTableModel) Jt_ConsC.getModel();
				int l = mpl.getRowCount();
				
				if(l>0) {
					while(l>0) {
						((DefaultTableModel) Jt_ConsC.getModel()).removeRow(l-1);
						l--;
					}
				}
				try {
					
				ResultSet rs = ClienteDAO.carregaTabela();
				
				DefaultTableModel mp =(DefaultTableModel) Jt_ConsC.getModel();
				
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
			Jt_ConsC.setAutoCreateRowSorter(true);
		}
		});
		setLocation(new Point(30, 10));
		setTitle("Consulta de Clientes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		TelaConsultaCliente = new JPanel();
		TelaConsultaCliente.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(TelaConsultaCliente);
		
		JLabel lbl_clientes = new JLabel("CLIENTES");
		lbl_clientes.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_clientes.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lblNewLabel = new JLabel("Consulta por:");
		
		JComboBox cmb_Tipo = new JComboBox();
		cmb_Tipo.setModel(new DefaultComboBoxModel(new String[] {"ID", "Nome", "CPF"}));
		
		JLabel lblDigiteOArgumento = new JLabel("Digite o argumento:");
		
		txt_Arg = new JTextField();
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
				
				DefaultTableModel mpl = (DefaultTableModel) Jt_ConsC.getModel();
				int l = mpl.getRowCount();
				
				if(l>0) {
					while(l>0) {
						((DefaultTableModel) Jt_ConsC.getModel()).removeRow(l-1);	
						l--;
					}
				}
				try{
					ResultSet rs = ClienteDAO.carregaTabela(tipo, Arg);
					
					DefaultTableModel mp =(DefaultTableModel) Jt_ConsC.getModel();
					
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
				Jt_ConsC.setAutoCreateRowSorter(true);
			}
		});
		txt_Arg.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnCapturar = new JButton("CAPTURAR");
		
		JButton btnSair = new JButton("SAIR");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaConsultaCliente.this.dispose();
			}
		});
		GroupLayout gl_TelaConsultaCliente = new GroupLayout(TelaConsultaCliente);
		gl_TelaConsultaCliente.setHorizontalGroup(
			gl_TelaConsultaCliente.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_TelaConsultaCliente.createSequentialGroup()
					.addGroup(gl_TelaConsultaCliente.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_TelaConsultaCliente.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnSair, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.LEADING, gl_TelaConsultaCliente.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_TelaConsultaCliente.createSequentialGroup()
								.addGap(286)
								.addComponent(lbl_clientes, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_TelaConsultaCliente.createSequentialGroup()
								.addGap(21)
								.addGroup(gl_TelaConsultaCliente.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_TelaConsultaCliente.createSequentialGroup()
										.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
										.addGap(29)
										.addComponent(lblDigiteOArgumento, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE))
									.addGroup(Alignment.TRAILING, gl_TelaConsultaCliente.createSequentialGroup()
										.addComponent(cmb_Tipo, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(txt_Arg, GroupLayout.PREFERRED_SIZE, 411, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnCapturar))
									.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 632, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap(21, Short.MAX_VALUE))
		);
		gl_TelaConsultaCliente.setVerticalGroup(
			gl_TelaConsultaCliente.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_TelaConsultaCliente.createSequentialGroup()
					.addContainerGap()
					.addComponent(lbl_clientes, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
					.addGroup(gl_TelaConsultaCliente.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDigiteOArgumento, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addGroup(gl_TelaConsultaCliente.createParallelGroup(Alignment.LEADING)
						.addComponent(cmb_Tipo, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_TelaConsultaCliente.createParallelGroup(Alignment.BASELINE)
							.addComponent(txt_Arg, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnCapturar)))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 259, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnSair)
					.addGap(15))
		);
		
		Jt_ConsC = new JTable();
		Jt_ConsC.setModel(new DefaultTableModel(
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
		Jt_ConsC.setBackground(SystemColor.menu);
		scrollPane.setViewportView(Jt_ConsC);
		TelaConsultaCliente.setLayout(gl_TelaConsultaCliente);
	}
}

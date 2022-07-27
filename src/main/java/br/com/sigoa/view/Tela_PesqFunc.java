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

import br.com.sigoa.dao.FuncionarioDAO;
import br.com.sigoa.model.Cliente;
import br.com.sigoa.model.Funcionario;

public class Tela_PesqFunc extends JFrame {

	private JPanel contentPane;
	private JTextField txt_Arg;
	private JTable Jt_Funcionario;

	//INICIALIZAÇÃO
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cliente cliente = new Cliente();
					Tela_PesqFunc frame = new Tela_PesqFunc(cliente);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//CRIAÇÃO DO FRAME
	public Tela_PesqFunc(Cliente cliente) {
		setTitle("SIGOA");
		getContentPane().setBackground(SystemColor.inactiveCaption);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				DefaultTableModel mpl = (DefaultTableModel) Jt_Funcionario.getModel();
				int l=mpl.getRowCount();
				if(l>0) {
					while(l>0) {
						((DefaultTableModel) Jt_Funcionario.getModel()).removeRow(l-1);
						l--;
					}
				}
				try {
				ResultSet rs = FuncionarioDAO.carregaTabela();
				DefaultTableModel mp =(DefaultTableModel) Jt_Funcionario.getModel();
				while(rs.next()) {
					String Coluna0 = rs.getString("id_funcionario").toString().trim();
					String Coluna1 = rs.getString("nome_funcionario").toString().trim();
					String Coluna2 = rs.getString("cpf_funcionario").toString().trim();
					String Coluna3 = rs.getString("matricula_funcionario").toString().trim();
					//String Coluna4 = rs.getString("endereco_funcionario").toString().trim();
					String Coluna5 = rs.getString("nascimento_funcionario").toString().trim();
					String Coluna6 = rs.getString("telefone_funcionario").toString().trim();
					
					mp.addRow(new String[] {Coluna0,Coluna1,Coluna2,Coluna3,Coluna5,Coluna6});
				}
			}catch(SQLException erro) {
				//JOptionPane.showMessageDialog(this, "Ocorreu um erro:"+ erro, "Preencher Item", 2);
			}
			tamanhocolunas();
			Jt_Funcionario.setAutoCreateRowSorter(true);
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
				String tipo=" ";
				String escolha = cmb_Tipo.getSelectedItem().toString().trim();
				if (escolha.equals("ID")) {
					tipo = " " + "id_funcionario";
				}
				if (escolha.equals("Nome")) {
					tipo = " " + "nome_funcionario";
				}
				if (escolha.equals("CPF")) {
					tipo = " " + "cpf_funcionario";
				}
				String Arg = txt_Arg.getText();
				DefaultTableModel mpl = (DefaultTableModel) Jt_Funcionario.getModel();
				int l = mpl.getRowCount();
				if(l>0) {
					while(l>0) {
						((DefaultTableModel) Jt_Funcionario.getModel()).removeRow(l-1);	
						l--;
					}
				}
				try{
					ResultSet rs = FuncionarioDAO.carregaTabela(tipo, Arg);
					DefaultTableModel mp =(DefaultTableModel) Jt_Funcionario.getModel();
		
					while(rs.next()) {
						String Coluna0 = rs.getString("id_funcionario").toString().trim();
						String Coluna1 = rs.getString("nome_funcionario").toString().trim();
						String Coluna2 = rs.getString("cpf_funcionario").toString().trim();
						String Coluna3 = rs.getString("matricula_funcionario").toString().trim();
						String Coluna4 = rs.getString("nascimento_funcionario").toString().trim();
						String Coluna5 = rs.getString("telefone_funcionario").toString().trim();
						
						mp.addRow(new String[] {Coluna0,Coluna1,Coluna2,Coluna3,Coluna4,Coluna5});
					}
				}catch(SQLException erro) {	
				}
				tamanhocolunas();
				Jt_Funcionario.setAutoCreateRowSorter(true);
			}
		});		
		txt_Arg.setColumns(10);
		
		JButton btn_Capturar = new JButton("SELECIONAR");
		btn_Capturar.setIcon(new ImageIcon(Tela_PesqFunc.class.getResource("/Images/Sign-Select-icon.png")));
		btn_Capturar.setBounds(553, 91, 123, 30);
		btn_Capturar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Funcionario funcionario = new Funcionario();
				String id_middle = Jt_Funcionario.getValueAt(Jt_Funcionario.getSelectedRow(), 0).toString();
				Integer id_final = Integer.parseInt(id_middle);
				funcionario.setId_funcionario(id_final);
				
				funcionario.setNome_funcionario(Jt_Funcionario.getValueAt(Jt_Funcionario.getSelectedRow(), 1).toString());
				funcionario.setCpf_funcionario(Jt_Funcionario.getValueAt(Jt_Funcionario.getSelectedRow(), 2).toString());
				funcionario.setMatricula_funcionario(Jt_Funcionario.getValueAt(Jt_Funcionario.getSelectedRow(), 3).toString());
				funcionario.setNascimento_funcionario(Jt_Funcionario.getValueAt(Jt_Funcionario.getSelectedRow(), 4).toString());
				funcionario.setTelefone_funcionario(Jt_Funcionario.getValueAt(Jt_Funcionario.getSelectedRow(), 5).toString());
				
				TelaCadastroOSsFuncionario tcosf = new TelaCadastroOSsFuncionario(cliente, funcionario);
				tcosf.setVisible(true);
				setVisible(false);	
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 132, 639, 307);
		
		JLabel lbl_Func = new JLabel("FUNCION\u00C1RIOS");
		lbl_Func.setBounds(225, 36, 245, 22);
		lbl_Func.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Func.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JButton btn_sair = new JButton("SAIR");
		btn_sair.setIcon(new ImageIcon(Tela_PesqFunc.class.getResource("/Images/voltar.png")));
		btn_sair.setBounds(568, 450, 108, 30);
		btn_sair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		Jt_Funcionario = new JTable();
		Jt_Funcionario.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"ID", "NOME", "CPF", "MATR\u00CDCULA", "NASCIMENTO", "TELEFONE"
				}
			) {
				boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
		Jt_Funcionario.setBackground(SystemColor.menu);
		scrollPane.setViewportView(Jt_Funcionario);
		contentPane.setLayout(null);
		contentPane.add(lbl_Func);
		contentPane.add(lblNewLabel);
		contentPane.add(lblDigiteOArgumento);
		contentPane.add(cmb_Tipo);
		contentPane.add(txt_Arg);
		contentPane.add(btn_Capturar);
		contentPane.add(scrollPane);
		contentPane.add(btn_sair);
	}

	//------------------------- MÉTODOS LOCAIS ------------------------------//
	public void tamanhocolunas() {
		Jt_Funcionario.getColumnModel().getColumn(0).setMinWidth(100);
		Jt_Funcionario.getColumnModel().getColumn(1).setMinWidth(250);
	}
}
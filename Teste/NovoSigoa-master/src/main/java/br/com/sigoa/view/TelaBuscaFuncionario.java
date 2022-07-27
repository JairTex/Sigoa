package br.com.sigoa.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import br.com.sigoa.dao.ClienteDAO;
import br.com.sigoa.dao.FuncionarioDAO;
import br.com.sigoa.model.Cliente;
import br.com.sigoa.model.Funcionario;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
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

public class TelaBuscaFuncionario extends JFrame {

	private JPanel contentPane;
	private JTextField txt_Arg;
	private static JTable Jt_Funcionario;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaBuscaFuncionario frame = new TelaBuscaFuncionario();
					frame.setVisible(true);
				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// --------------------------MÉTODOS LOCAIS----------------------------//	
	public void tamanhocolunas() {
		Jt_Funcionario.getColumnModel().getColumn(0).setMinWidth(100);
		Jt_Funcionario.getColumnModel().getColumn(1).setMinWidth(250);
	}
	
	public static void listarJTable(){
		DefaultTableModel modelo = (DefaultTableModel) Jt_Funcionario.getModel();
		FuncionarioDAO Fdao = new FuncionarioDAO();
		modelo.setNumRows(0);
		
		for (Funcionario f: Fdao.listar()){
			
			modelo.addRow(new Object[] {
				f.getId_funcionario(),
				f.getNome_funcionario(),
				f.getCpf_funcionario(),
				f.getMatricula_funcionario(),
				f.getEndereco_funcionario(),
				f.getNascimento_funcionario(),
				f.getTelefone_funcionario()
			});
		}
	}
	
	//---------------------------CRIAÇÃO DO FRAME--------------------------//	
	public TelaBuscaFuncionario() {
		addWindowListener(new WindowAdapter() {
			
			//Carregando Jtable ao iniciar a Frame
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
					String Coluna4 = rs.getString("endereco_funcionario").toString().trim();
					String Coluna5 = rs.getString("nascimento_funcionario").toString().trim();
					String Coluna6 = rs.getString("telefone_funcionario").toString().trim();
					
					mp.addRow(new String[] {Coluna0,Coluna1,Coluna2,Coluna3,Coluna4,Coluna5,Coluna6});
				}
			}catch(SQLException erro) {
				//JOptionPane.showMessageDialog(this, "Ocorreu um erro:"+ erro, "Preencher Item", 2);
			}
			tamanhocolunas();
			Jt_Funcionario.setAutoCreateRowSorter(true);
		}
		});
		setTitle("SIGOA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1920, 1080);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lbl_funcionarios = new JLabel("FUNCIONÁRIOS");
		lbl_funcionarios.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_funcionarios.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JButton btn_Atualizar = new JButton("ATUALIZAR");
		btn_Atualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listarJTable();
			}
		});
		
		JComboBox cmb_TipoF = new JComboBox();
		cmb_TipoF.setModel(new DefaultComboBoxModel(new String[] {"ID", "Nome", "CPF"}));
		
		txt_Arg = new JTextField();
		txt_Arg.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				String tipo=" ";
				
				String escolha = cmb_TipoF.getSelectedItem().toString().trim();
				
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
						String Coluna4 = rs.getString("endereco_funcionario").toString().trim();
						String Coluna5 = rs.getString("nascimento_funcionario").toString().trim();
						String Coluna6 = rs.getString("telefone_funcionario").toString().trim();
						
						mp.addRow(new String[] {Coluna0,Coluna1,Coluna2,Coluna3,Coluna4,Coluna5,Coluna6});
					}
				}catch(SQLException erro) {
					
				}
				tamanhocolunas();
				Jt_Funcionario.setAutoCreateRowSorter(true);
			}

		});
		txt_Arg.setColumns(10);
		
		JButton btn_editar = new JButton("Editar");
		btn_editar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Jt_Funcionario.getSelectedRow() != -1) {
					Funcionario funcionario = new Funcionario();
					String id_middle = Jt_Funcionario.getValueAt(Jt_Funcionario.getSelectedRow(), 0).toString();
					Integer id_final = Integer.parseInt(id_middle);
					funcionario.setId_funcionario(id_final);
					
					funcionario.setNome_funcionario(Jt_Funcionario.getValueAt(Jt_Funcionario.getSelectedRow(), 1).toString());
					funcionario.setCpf_funcionario(Jt_Funcionario.getValueAt(Jt_Funcionario.getSelectedRow(), 2).toString());
					funcionario.setMatricula_funcionario(Jt_Funcionario.getValueAt(Jt_Funcionario.getSelectedRow(), 3).toString());
					funcionario.setEndereco_funcionario(Jt_Funcionario.getValueAt(Jt_Funcionario.getSelectedRow(), 4).toString());
					funcionario.setNascimento_funcionario(Jt_Funcionario.getValueAt(Jt_Funcionario.getSelectedRow(), 5).toString());
					funcionario.setTelefone_funcionario(Jt_Funcionario.getValueAt(Jt_Funcionario.getSelectedRow(), 6).toString());

					TelaCadastroFuncionario tcf;
					try {
						tcf = new TelaCadastroFuncionario(funcionario.getId_funcionario(),funcionario.getNome_funcionario(), funcionario.getCpf_funcionario(),
								funcionario.getMatricula_funcionario(), funcionario.getNascimento_funcionario(), funcionario.getEndereco_funcionario(),funcionario.getTelefone_funcionario());
						
						tcf.setVisible(true);
					
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
			}
		}
		});
		
		JButton btn_adicionar = new JButton("Adicionar");
		btn_adicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					br.com.sigoa.controller.ControllerTelaCadastroFuncionario.abrirTelaCadastroFuncionario();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JButton btn_remover = new JButton("Remover");
		
		JButton btn_voltar = new JButton("Sair");
		btn_voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				br.com.sigoa.controller.ControllerTelaCadastroFuncionario.voltar();
				TelaBuscaFuncionario.this.dispose();
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblNewLabel = new JLabel("Consulta por:");
		
		
		JLabel lblDigiteOArgumento = new JLabel("Digite o argumento:");
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(96)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(467)
							.addComponent(lbl_funcionarios, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(647, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
											.addGap(29)
											.addComponent(lblDigiteOArgumento, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(cmb_TipoF, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
											.addGap(18)
											.addComponent(txt_Arg, GroupLayout.PREFERRED_SIZE, 441, GroupLayout.PREFERRED_SIZE)))
									.addPreferredGap(ComponentPlacement.RELATED, 370, Short.MAX_VALUE)
									.addComponent(btn_Atualizar, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE))
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 1019, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btn_voltar, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btn_editar, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
								.addComponent(btn_adicionar, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
								.addComponent(btn_remover, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE))
							.addGap(59))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lbl_funcionarios, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btn_Atualizar))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDigiteOArgumento, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
							.addGap(6)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(cmb_TipoF, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(txt_Arg, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btn_editar)
							.addGap(12)
							.addComponent(btn_adicionar)
							.addGap(11)
							.addComponent(btn_remover)
							.addPreferredGap(ComponentPlacement.RELATED, 395, Short.MAX_VALUE)
							.addComponent(btn_voltar)
							.addGap(118))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 296, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		
		Jt_Funcionario = new JTable();
		Jt_Funcionario.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "NOME", "CPF", "MATR\u00CDCULA", "ENDERE\u00C7O", "NASCIMENTO", "TELEFONE"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(Jt_Funcionario);
		contentPane.setLayout(gl_contentPane);
	}
}

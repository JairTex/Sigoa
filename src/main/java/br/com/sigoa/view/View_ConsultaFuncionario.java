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

import br.com.sigoa.dao.FuncionarioDAO;
import br.com.sigoa.model.Funcionario;

public class View_ConsultaFuncionario extends JInternalFrame {
	private JTextField txt_Arg;
	private static JTable Jt_Funcionario;

	//INICIALIZAÇÃO
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View_ConsultaFuncionario frame = new View_ConsultaFuncionario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//CRIAÇÃO DO FRAME
	public View_ConsultaFuncionario() {
		getContentPane().setBackground(SystemColor.inactiveCaption);
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent e) {
				
				TelaPrincipal.menuFuncionario=true;
				
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
		setIconifiable(true);
		setMaximizable(true);
		setTitle("Menu Clientes");
		setBounds(300, 20, 860, 600);
		
		JLabel lbl_funcionario = new JLabel("FUNCIONÁRIOS");
		lbl_funcionario.setIcon(new ImageIcon(View_ConsultaFuncionario.class.getResource("/Images/Funcionarios.png")));
		lbl_funcionario.setBounds(323, 11, 245, 55);
		lbl_funcionario.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_funcionario.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lblNewLabel = new JLabel("Consulta por:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(37, 55, 76, 23);
		
		JLabel lblDigiteOArgumento = new JLabel("Digite o argumento:");
		lblDigiteOArgumento.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDigiteOArgumento.setBounds(145, 55, 139, 23);
		
		JComboBox cmb_Tipo = new JComboBox();
		cmb_Tipo.setBounds(37, 77, 76, 30);
		cmb_Tipo.setModel(new DefaultComboBoxModel(new String[] {"ID", "Nome", "CPF"}));
		
		txt_Arg = new JTextField();
		txt_Arg.setBounds(145, 77, 301, 30);
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 125, 768, 358);
		
		JButton btn_editar = new JButton("Editar");
		btn_editar.setIcon(new ImageIcon(View_ConsultaFuncionario.class.getResource("/Images/editar.png")));
		btn_editar.setBounds(170, 505, 123, 30);
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
						e1.printStackTrace();
					}
				}
			}
		});
		
		JButton btn_adicionar = new JButton("Novo");
		btn_adicionar.setIcon(new ImageIcon(View_ConsultaFuncionario.class.getResource("/Images/adicionar.png")));
		btn_adicionar.setBounds(37, 505, 123, 30);
		btn_adicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					br.com.sigoa.controller.ControllerFuncionario.abrirTelaCadastroFuncionario();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		JButton btn_remover = new JButton("Remover");
		btn_remover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Funcionario funcionario = new Funcionario();
				String id_middle = Jt_Funcionario.getValueAt(Jt_Funcionario.getSelectedRow(), 0).toString();
				Integer id_final = Integer.parseInt(id_middle);
				funcionario.setId_funcionario(id_final);
				funcionario.setNome_funcionario(Jt_Funcionario.getValueAt(Jt_Funcionario.getSelectedRow(), 1).toString());
				
				Object[] options = { "Cancelar", "Confirmar" }; 
				Integer option = JOptionPane.showOptionDialog(null, "Deseja remover " + funcionario.getNome_funcionario() + " dos funcionarios?"
				, "", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				
				if (option == 0) {
				}
				if (option == 1) {
					FuncionarioDAO.DELETE(funcionario);	
				}
			}
		});
		btn_remover.setIcon(new ImageIcon(View_ConsultaFuncionario.class.getResource("/Images/Remover.png")));
		btn_remover.setBounds(303, 505, 123, 30);
		
		JButton btn_sair = new JButton("SAIR");
		btn_sair.setToolTipText("Retornar a tela principal");
		btn_sair.setIcon(new ImageIcon(View_ConsultaFuncionario.class.getResource("/Images/voltar.png")));
		btn_sair.setBounds(682, 505, 123, 30);
		btn_sair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaPrincipal.menuFuncionario=false;
				View_ConsultaFuncionario.this.dispose();
			}
		});
		
		JButton btn_Atualizar = new JButton("");
		btn_Atualizar.setToolTipText("Atualizar tabela.");
		btn_Atualizar.setIcon(new ImageIcon(View_ConsultaFuncionario.class.getResource("/Images/update.png")));
		btn_Atualizar.setBounds(753, 77, 52, 30);
		btn_Atualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listarJTable();	
			}
		});
		
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
		Jt_Funcionario.setBackground(SystemColor.menu);
		scrollPane.setViewportView(Jt_Funcionario);
		getContentPane().setLayout(null);
		getContentPane().add(lbl_funcionario);
		getContentPane().add(lblNewLabel);
		getContentPane().add(cmb_Tipo);
		getContentPane().add(txt_Arg);
		getContentPane().add(btn_Atualizar);
		getContentPane().add(lblDigiteOArgumento);
		getContentPane().add(btn_editar);
		getContentPane().add(btn_adicionar);
		getContentPane().add(btn_remover);
		getContentPane().add(btn_sair);
		getContentPane().add(scrollPane);

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
}

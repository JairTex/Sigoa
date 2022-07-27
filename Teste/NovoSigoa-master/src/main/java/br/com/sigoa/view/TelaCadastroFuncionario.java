package br.com.sigoa.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import br.com.sigoa.controller.ControllerTelaCadastroFuncionario;
import br.com.sigoa.dao.ClienteDAO;
import br.com.sigoa.dao.FuncionarioDAO;
import br.com.sigoa.model.Cliente;
import br.com.sigoa.model.Funcionario;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;

public class TelaCadastroFuncionario extends JFrame {

	private JPanel contentPane;
	private JTextField txt_nome;
	private JTextField txt_endereco;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroFuncionario frame = new TelaCadastroFuncionario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	public TelaCadastroFuncionario() throws ParseException {
		setTitle("SIGOA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mb_menu = new JMenu("Menu");
		menuBar.add(mb_menu);
		
		JMenu mn_cadastro = new JMenu("Cadastro");
		mb_menu.add(mn_cadastro);
		
		JMenuItem mn_cadCadastrarCliente = new JMenuItem("Cadastrar cliente");
		mn_cadastro.add(mn_cadCadastrarCliente);
		
		JMenuItem mn_cadCadastrarServico = new JMenuItem("Cadastrar servi\u00E7o");
		mn_cadastro.add(mn_cadCadastrarServico);
		
		JMenuItem mn_cadCadastrarFunc = new JMenuItem("Cadastrar funcion\u00E1rio");
		mn_cadastro.add(mn_cadCadastrarFunc);
		
		JMenuItem mn_cadCadastarPeca = new JMenuItem("Cadastrar pe\u00E7as");
		mn_cadastro.add(mn_cadCadastarPeca);
		
		JMenu mn_estoque = new JMenu("Estoque");
		mb_menu.add(mn_estoque);
		
		JMenuItem mn_estConsultar = new JMenuItem("Consultar");
		mn_estoque.add(mn_estConsultar);
		
		JMenuItem mn_estRegEntrada = new JMenuItem("Registrar entrada");
		mn_estoque.add(mn_estRegEntrada);
		
		JMenuItem mn_sair = new JMenuItem("Sair");
		mb_menu.add(mn_sair);
		
		JMenu mb_sobre = new JMenu("Sobre");
		menuBar.add(mb_sobre);
		
		JMenuItem mn_especif = new JMenuItem("Especifica\u00E7\u00F5es");
		mb_sobre.add(mn_especif);
		
		JMenu mn_suporte = new JMenu("Suporte");
		mb_sobre.add(mn_suporte);
		
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Ir para a p\u00E1gina");
		mn_suporte.add(mntmNewMenuItem_8);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lbl_CadastrarFuncionrio = new JLabel("Cadastrar Funcion\u00E1rio");
		lbl_CadastrarFuncionrio.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lbl_matricula = new JLabel("Matr\u00EDcula");
		lbl_matricula.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JFormattedTextField frt_Matricula = new JFormattedTextField(new MaskFormatter("######"));
		frt_Matricula.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frt_Matricula.setColumns(10);
		
		JLabel lbl_cpf = new JLabel("CPF");
		lbl_cpf.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JFormattedTextField frt_CPF = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
		frt_CPF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frt_CPF.setColumns(14);
		
		JLabel lbl_nome = new JLabel("Nome");
		lbl_nome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		txt_nome = new JTextField();
		txt_nome.setColumns(10);
		
		JLabel lbl_nascimento = new JLabel("Nascimento");
		lbl_nascimento.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lbl_telefone = new JLabel("Telefone");
		lbl_telefone.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JFormattedTextField frt_Nascimento = new JFormattedTextField(new MaskFormatter("##/##/####"));
		frt_Nascimento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frt_Nascimento.setColumns(10);
		
		JLabel lbl_endereco = new JLabel("Endere\u00E7o");
		lbl_endereco.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		txt_endereco = new JTextField();
		txt_endereco.setColumns(10);
		
		JFormattedTextField frt_Telefone = new JFormattedTextField(new MaskFormatter("(##) #####-####"));
		frt_Telefone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frt_Telefone.setColumns(15);
		
		JButton btn_salvar = new JButton("Salvar");
		btn_salvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Funcionario funcionario = new Funcionario(txt_nome.getText(), frt_CPF.getText(), frt_Matricula.getText(),
															txt_endereco.getText(), frt_Nascimento.getText(), frt_Telefone.getText());
				FuncionarioDAO.CREATE(funcionario);
				try {
					ControllerTelaCadastroFuncionario.abrirTelaCadastroFuncionario();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				setVisible(false);
			}
		});
		
		JButton btn_voltar = new JButton("Sair");
		btn_voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastroFuncionario.this.dispose();
			}
		});
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(105)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lbl_matricula, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbl_nascimento, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbl_nome, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbl_endereco, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(frt_Nascimento, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
										.addComponent(frt_Matricula, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lbl_cpf, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(frt_CPF, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
											.addGap(2))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lbl_telefone)
											.addGap(18)
											.addComponent(frt_Telefone, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED))))
								.addComponent(txt_endereco, GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(19)
							.addComponent(txt_nome, GroupLayout.PREFERRED_SIZE, 463, GroupLayout.PREFERRED_SIZE)))
					.addGap(102))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(303)
					.addComponent(lbl_CadastrarFuncionrio, GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
					.addGap(290))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(377, Short.MAX_VALUE)
					.addComponent(btn_salvar, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
					.addGap(127)
					.addComponent(btn_voltar, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
					.addGap(68))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(63)
							.addComponent(lbl_CadastrarFuncionrio, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txt_nome, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(lbl_nome, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
							.addGap(8)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(lbl_cpf, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
									.addComponent(frt_CPF, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(lbl_matricula, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
									.addComponent(frt_Matricula, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lbl_telefone, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
								.addComponent(frt_Telefone, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(frt_Nascimento, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(lbl_nascimento, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
							.addGap(51))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap(218, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txt_endereco, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
								.addComponent(lbl_endereco, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
							.addGap(18)))
					.addGap(18)
					.addComponent(btn_salvar)
					.addGap(73)
					.addComponent(btn_voltar)
					.addGap(35))
		);
		contentPane.setLayout(gl_contentPane);
	}

	public TelaCadastroFuncionario(Integer id_funcionario, String nome_funcionario, String cpf_funcionario,
			String matricula_funcionario, String nascimento_funcionario, String endereco_funcionario, String telefone_funcionario) throws ParseException {		
			
			setTitle("SIGOA");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 800, 500);
			
			JMenuBar menuBar = new JMenuBar();
			setJMenuBar(menuBar);
			
			JMenu mb_menu = new JMenu("Menu");
			menuBar.add(mb_menu);
			
			JMenu mn_cadastro = new JMenu("Cadastro");
			mb_menu.add(mn_cadastro);
			
			JMenuItem mn_cadCadastrarCliente = new JMenuItem("Cadastrar cliente");
			mn_cadastro.add(mn_cadCadastrarCliente);
			
			JMenuItem mn_cadCadastrarServico = new JMenuItem("Cadastrar servi\u00E7o");
			mn_cadastro.add(mn_cadCadastrarServico);
			
			JMenuItem mn_cadCadastrarFunc = new JMenuItem("Cadastrar funcion\u00E1rio");
			mn_cadastro.add(mn_cadCadastrarFunc);
			
			JMenuItem mn_cadCadastarPeca = new JMenuItem("Cadastrar pe\u00E7as");
			mn_cadastro.add(mn_cadCadastarPeca);
			
			JMenu mn_estoque = new JMenu("Estoque");
			mb_menu.add(mn_estoque);
			
			JMenuItem mn_estConsultar = new JMenuItem("Consultar");
			mn_estoque.add(mn_estConsultar);
			
			JMenuItem mn_estRegEntrada = new JMenuItem("Registrar entrada");
			mn_estoque.add(mn_estRegEntrada);
			
			JMenuItem mn_sair = new JMenuItem("Sair");
			mb_menu.add(mn_sair);
			
			JMenu mb_sobre = new JMenu("Sobre");
			menuBar.add(mb_sobre);
			
			JMenuItem mn_especif = new JMenuItem("Especifica\u00E7\u00F5es");
			mb_sobre.add(mn_especif);
			
			JMenu mn_suporte = new JMenu("Suporte");
			mb_sobre.add(mn_suporte);
			
			JMenuItem mntmNewMenuItem_8 = new JMenuItem("Ir para a p\u00E1gina");
			mn_suporte.add(mntmNewMenuItem_8);
			contentPane = new JPanel();
			contentPane.setBackground(SystemColor.activeCaption);
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			
			JLabel lbl_CadastrarFuncionrio = new JLabel("Cadastrar Funcion\u00E1rio");
			lbl_CadastrarFuncionrio.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			JLabel lbl_matricula = new JLabel("Matr\u00EDcula");
			lbl_matricula.setFont(new Font("Tahoma", Font.PLAIN, 15));
			
			JFormattedTextField frt_Matricula = new JFormattedTextField(new MaskFormatter("######"));
			frt_Matricula.setFont(new Font("Tahoma", Font.PLAIN, 14));
			frt_Matricula.setColumns(10);
			frt_Matricula.setText(matricula_funcionario);
			
			JLabel lbl_cpf = new JLabel("CPF");
			lbl_cpf.setFont(new Font("Tahoma", Font.PLAIN, 15));
			
			JFormattedTextField frt_CPF = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
			frt_CPF.setFont(new Font("Tahoma", Font.PLAIN, 14));
			frt_CPF.setColumns(14);
			frt_CPF.setText(cpf_funcionario);
			
			JLabel lbl_nome = new JLabel("Nome");
			lbl_nome.setFont(new Font("Tahoma", Font.PLAIN, 15));
			
			txt_nome = new JTextField();
			txt_nome.setColumns(10);
			txt_nome.setText(nome_funcionario);
			
			JLabel lbl_nascimento = new JLabel("Nascimento");
			lbl_nascimento.setFont(new Font("Tahoma", Font.PLAIN, 15));
			
			JLabel lbl_telefone = new JLabel("Telefone");
			lbl_telefone.setFont(new Font("Tahoma", Font.PLAIN, 15));
			
			JFormattedTextField frt_Nascimento = new JFormattedTextField(new MaskFormatter("##/##/####"));
			frt_Nascimento.setFont(new Font("Tahoma", Font.PLAIN, 14));
			frt_Nascimento.setColumns(10);
			frt_Nascimento.setText(nascimento_funcionario);
			
			JLabel lbl_endereco = new JLabel("Endere\u00E7o");
			lbl_endereco.setFont(new Font("Tahoma", Font.PLAIN, 15));
			
			txt_endereco = new JTextField();
			txt_endereco.setColumns(10);
			txt_endereco.setText(endereco_funcionario);
			
			JFormattedTextField frt_Telefone = new JFormattedTextField(new MaskFormatter("(##) #####-####"));
			frt_Telefone.setFont(new Font("Tahoma", Font.PLAIN, 14));
			frt_Telefone.setColumns(15);
			frt_Telefone.setText(telefone_funcionario);
			
			JButton btn_salvar = new JButton("Salvar");
			btn_salvar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Funcionario funcionario = new Funcionario();
					funcionario.setNome_funcionario(txt_nome.getText());
					funcionario.setCpf_funcionario(frt_CPF.getText());
					funcionario.setEndereco_funcionario(txt_endereco.getText());
					funcionario.setNascimento_funcionario(frt_Nascimento.getText());
					funcionario.setMatricula_funcionario(frt_Matricula.getText());
					funcionario.setTelefone_funcionario(frt_Telefone.getText());
					funcionario.setId_funcionario(id_funcionario);
					
					FuncionarioDAO.UPDATE(funcionario);
					
					setVisible(false);
					
				}
			});
			
			JButton btn_voltar = new JButton("Sair");
			btn_voltar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					TelaCadastroFuncionario.this.dispose();
				}
			});
			
			
			GroupLayout gl_contentPane = new GroupLayout(contentPane);
			gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(105)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
							.addComponent(lbl_matricula, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
							.addComponent(lbl_nascimento, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
							.addComponent(lbl_nome, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
							.addComponent(lbl_endereco, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(18)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
											.addComponent(frt_Nascimento, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
											.addComponent(frt_Matricula, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
											.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(lbl_cpf, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(frt_CPF, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
												.addGap(2))
											.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(lbl_telefone)
												.addGap(18)
												.addComponent(frt_Telefone, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED))))
									.addComponent(txt_endereco, GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(19)
								.addComponent(txt_nome, GroupLayout.PREFERRED_SIZE, 463, GroupLayout.PREFERRED_SIZE)))
						.addGap(102))
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(303)
						.addComponent(lbl_CadastrarFuncionrio, GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
						.addGap(290))
					.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap(377, Short.MAX_VALUE)
						.addComponent(btn_salvar, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
						.addGap(127)
						.addComponent(btn_voltar, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
						.addGap(68))
			);
			gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(63)
								.addComponent(lbl_CadastrarFuncionrio, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(txt_nome, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
									.addComponent(lbl_nome, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
								.addGap(8)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(lbl_cpf, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
										.addComponent(frt_CPF, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(lbl_matricula, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
										.addComponent(frt_Matricula, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(lbl_telefone, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
									.addComponent(frt_Telefone, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
									.addComponent(frt_Nascimento, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
									.addComponent(lbl_nascimento, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
								.addGap(51))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addContainerGap(218, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(txt_endereco, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
									.addComponent(lbl_endereco, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
								.addGap(18)))
						.addGap(18)
						.addComponent(btn_salvar)
						.addGap(73)
						.addComponent(btn_voltar)
						.addGap(35))
			);
			contentPane.setLayout(gl_contentPane);
		}
}
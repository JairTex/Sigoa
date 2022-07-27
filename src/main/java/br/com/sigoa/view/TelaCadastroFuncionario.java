package br.com.sigoa.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import br.com.sigoa.controller.ControllerFuncionario;
import br.com.sigoa.dao.FuncionarioDAO;
import br.com.sigoa.model.Funcionario;

public class TelaCadastroFuncionario extends JFrame {

	private JPanel contentPane;
	private JTextField txt_nome;
	private JTextField txt_endereco;

	// INICILIZAÇÃO
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
	 * CRIAÇÃO DO FRAME
	 * @throws ParseException 
	 */
	public TelaCadastroFuncionario() throws ParseException {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				TelaPrincipal.cadFunc=true;
			}
		});
		setTitle("SIGOA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 470);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lbl_CadastrarFuncionrio = new JLabel("Cadastrar Funcion\u00E1rio");
		lbl_CadastrarFuncionrio.setBounds(308, 68, 214, 34);
		lbl_CadastrarFuncionrio.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JLabel lbl_matricula = new JLabel("Matr\u00EDcula");
		lbl_matricula.setBounds(133, 151, 70, 24);
		lbl_matricula.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JFormattedTextField frt_Matricula = new JFormattedTextField(new MaskFormatter("######"));
		frt_Matricula.setBounds(213, 152, 180, 23);
		frt_Matricula.setFont(new Font("Tahoma", Font.PLAIN, 12));
		frt_Matricula.setColumns(10);
		
		JLabel lbl_cpf = new JLabel("CPF");
		lbl_cpf.setBounds(451, 154, 40, 19);
		lbl_cpf.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JFormattedTextField frt_CPF = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
		frt_CPF.setBounds(495, 152, 180, 23);
		frt_CPF.setFont(new Font("Tahoma", Font.PLAIN, 12));
		frt_CPF.setColumns(14);
		
		JLabel lbl_nome = new JLabel("Nome");
		lbl_nome.setBounds(151, 120, 52, 19);
		lbl_nome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		txt_nome = new JTextField();
		txt_nome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txt_nome.setBounds(212, 119, 463, 23);
		txt_nome.setColumns(10);
		
		JLabel lbl_nascimento = new JLabel("Nascimento");
		lbl_nascimento.setBounds(118, 188, 85, 19);
		lbl_nascimento.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lbl_telefone = new JLabel("Telefone");
		lbl_telefone.setBounds(422, 188, 57, 19);
		lbl_telefone.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JFormattedTextField frt_Nascimento = new JFormattedTextField(new MaskFormatter("##/##/####"));
		frt_Nascimento.setBounds(213, 186, 180, 23);
		frt_Nascimento.setFont(new Font("Tahoma", Font.PLAIN, 12));
		frt_Nascimento.setColumns(10);
		
		JLabel lbl_endereco = new JLabel("Endereço");
		lbl_endereco.setBounds(132, 219, 69, 19);
		lbl_endereco.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		txt_endereco = new JTextField();
		txt_endereco.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txt_endereco.setBounds(211, 218, 464, 23);
		txt_endereco.setColumns(10);
		
		JFormattedTextField frt_Telefone = new JFormattedTextField(new MaskFormatter("(##) #####-####"));
		frt_Telefone.setBounds(497, 186, 178, 23);
		frt_Telefone.setFont(new Font("Tahoma", Font.PLAIN, 12));
		frt_Telefone.setColumns(15);
		
		JButton btn_salvar = new JButton("   Salvar");
		btn_salvar.setHorizontalAlignment(SwingConstants.LEFT);
		btn_salvar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_salvar.setIcon(new ImageIcon(TelaCadastroFuncionario.class.getResource("/Images/Salvar.png")));
		btn_salvar.setBounds(369, 269, 122, 30);
		btn_salvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Funcionario funcionario = new Funcionario(txt_nome.getText(), frt_CPF.getText(), frt_Matricula.getText(),
															txt_endereco.getText(), frt_Nascimento.getText(), frt_Telefone.getText());
				FuncionarioDAO.CREATE(funcionario);
				setVisible(false);
			}
		});
		
		JButton btn_voltar = new JButton("   Sair");
		btn_voltar.setHorizontalAlignment(SwingConstants.LEFT);
		btn_voltar.setIcon(new ImageIcon(TelaCadastroFuncionario.class.getResource("/Images/voltar.png")));
		btn_voltar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_voltar.setBounds(603, 342, 105, 30);
		btn_voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaPrincipal.cadFunc=false;
				TelaCadastroFuncionario.this.dispose();
			}
		});
		contentPane.setLayout(null);
		contentPane.add(lbl_matricula);
		contentPane.add(lbl_nascimento);
		contentPane.add(lbl_nome);
		contentPane.add(lbl_endereco);
		contentPane.add(frt_Nascimento);
		contentPane.add(frt_Matricula);
		contentPane.add(lbl_cpf);
		contentPane.add(frt_CPF);
		contentPane.add(lbl_telefone);
		contentPane.add(frt_Telefone);
		contentPane.add(txt_endereco);
		contentPane.add(txt_nome);
		contentPane.add(lbl_CadastrarFuncionrio);
		contentPane.add(btn_salvar);
		contentPane.add(btn_voltar);
	}

	public TelaCadastroFuncionario(Integer id_funcionario, String nome_funcionario, String cpf_funcionario,
			String matricula_funcionario, String nascimento_funcionario, String endereco_funcionario, String telefone_funcionario) throws ParseException {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				TelaPrincipal.cadFunc=true;
			}
		});
		setTitle("SIGOA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 470);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lbl_CadastrarFuncionrio = new JLabel("Editar Cadastro");
		lbl_CadastrarFuncionrio.setBounds(308, 68, 214, 34);
		lbl_CadastrarFuncionrio.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JLabel lbl_matricula = new JLabel("Matr\u00EDcula");
		lbl_matricula.setBounds(133, 151, 70, 24);
		lbl_matricula.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JFormattedTextField frt_Matricula = new JFormattedTextField(new MaskFormatter("######"));
		frt_Matricula.setBounds(213, 152, 180, 23);
		frt_Matricula.setFont(new Font("Tahoma", Font.PLAIN, 12));
		frt_Matricula.setColumns(10);
		frt_Matricula.setText(matricula_funcionario);
		
		JLabel lbl_cpf = new JLabel("CPF");
		lbl_cpf.setBounds(451, 154, 40, 19);
		lbl_cpf.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JFormattedTextField frt_CPF = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
		frt_CPF.setBounds(495, 152, 180, 23);
		frt_CPF.setFont(new Font("Tahoma", Font.PLAIN, 12));
		frt_CPF.setColumns(14);
		frt_CPF.setText(cpf_funcionario);
		
		JLabel lbl_nome = new JLabel("Nome");
		lbl_nome.setBounds(151, 120, 52, 19);
		lbl_nome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		txt_nome = new JTextField();
		txt_nome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txt_nome.setBounds(212, 119, 463, 23);
		txt_nome.setColumns(10);
		txt_nome.setText(nome_funcionario);
		
		JLabel lbl_nascimento = new JLabel("Nascimento");
		lbl_nascimento.setBounds(118, 188, 85, 19);
		lbl_nascimento.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lbl_telefone = new JLabel("Telefone");
		lbl_telefone.setBounds(422, 188, 57, 19);
		lbl_telefone.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JFormattedTextField frt_Nascimento = new JFormattedTextField(new MaskFormatter("##/##/####"));
		frt_Nascimento.setBounds(213, 186, 180, 23);
		frt_Nascimento.setFont(new Font("Tahoma", Font.PLAIN, 12));
		frt_Nascimento.setColumns(10);
		frt_Nascimento.setText(nascimento_funcionario);
		
		JLabel lbl_endereco = new JLabel("Endereço");
		lbl_endereco.setBounds(132, 219, 69, 19);
		lbl_endereco.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		txt_endereco = new JTextField();
		txt_endereco.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txt_endereco.setBounds(211, 218, 464, 23);
		txt_endereco.setColumns(10);
		txt_endereco.setText(endereco_funcionario);
		
		JFormattedTextField frt_Telefone = new JFormattedTextField(new MaskFormatter("(##) #####-####"));
		frt_Telefone.setBounds(497, 186, 178, 23);
		frt_Telefone.setFont(new Font("Tahoma", Font.PLAIN, 12));
		frt_Telefone.setColumns(15);
		frt_Telefone.setText(telefone_funcionario);
		
		JButton btn_salvar = new JButton("   Salvar");
		btn_salvar.setHorizontalAlignment(SwingConstants.LEFT);
		btn_salvar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_salvar.setIcon(new ImageIcon(TelaCadastroFuncionario.class.getResource("/Images/Salvar.png")));
		btn_salvar.setBounds(369, 269, 122, 30);
		btn_salvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Funcionario funcionario = new Funcionario(txt_nome.getText(), frt_CPF.getText(), frt_Matricula.getText(),
															txt_endereco.getText(), frt_Nascimento.getText(), frt_Telefone.getText());
				funcionario.setId_funcionario(id_funcionario);
				FuncionarioDAO.UPDATE(funcionario);
				setVisible(false);
			}
		});
		
		JButton btn_voltar = new JButton("   Sair");
		btn_voltar.setHorizontalAlignment(SwingConstants.LEFT);
		btn_voltar.setIcon(new ImageIcon(TelaCadastroFuncionario.class.getResource("/Images/voltar.png")));
		btn_voltar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_voltar.setBounds(603, 342, 105, 30);
		btn_voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaPrincipal.cadFunc=false;
				TelaCadastroFuncionario.this.dispose();
			}
		});
		contentPane.setLayout(null);
		contentPane.add(lbl_matricula);
		contentPane.add(lbl_nascimento);
		contentPane.add(lbl_nome);
		contentPane.add(lbl_endereco);
		contentPane.add(frt_Nascimento);
		contentPane.add(frt_Matricula);
		contentPane.add(lbl_cpf);
		contentPane.add(frt_CPF);
		contentPane.add(lbl_telefone);
		contentPane.add(frt_Telefone);
		contentPane.add(txt_endereco);
		contentPane.add(txt_nome);
		contentPane.add(lbl_CadastrarFuncionrio);
		contentPane.add(btn_salvar);
		contentPane.add(btn_voltar);
	}
}
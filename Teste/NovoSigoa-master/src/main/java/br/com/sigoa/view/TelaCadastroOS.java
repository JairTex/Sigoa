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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaCadastroOS extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroOS frame = new TelaCadastroOS();
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
	public TelaCadastroOS() throws ParseException {
		setTitle("SIGOA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 450);
		
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
		
		JButton btn_voltar = new JButton("Sair");
		btn_voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastroOS.this.dispose();
			}
		});
		
		JLabel lbl_CadastrarOS = new JLabel("Cadastrar OS");
		lbl_CadastrarOS.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lbl_Cliente = new JLabel("Cliente");
		lbl_Cliente.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JButton btn_PesqC = new JButton("");
		btn_PesqC.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TelaConsultaCliente objConsulta = new TelaConsultaCliente();
				objConsulta.setVisible(true);
			}
		});
		
		
		JButton btn_PesqF = new JButton("");
		btn_PesqF.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TelaConsultaFuncionario objConsulta = new TelaConsultaFuncionario();
				objConsulta.setVisible(true);
			}
		});
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JLabel lbl_Funcionario = new JLabel("Funcion\u00E1rio");
		lbl_Funcionario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lbl_Data = new JLabel("Data");
		lbl_Data.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JButton btn_Salvar = new JButton("Salvar");
		
		JFormattedTextField Jfr_Data = new JFormattedTextField(new MaskFormatter("##/##/####"));
		Jfr_Data.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Jfr_Data.setColumns(15);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(128, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lbl_Cliente, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
								.addComponent(lbl_Funcionario, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
								.addComponent(lbl_Data, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(textField_1, 289, 289, 289)
										.addComponent(textField, GroupLayout.PREFERRED_SIZE, 289, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(18)
											.addComponent(btn_voltar, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(btn_PesqF, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
												.addComponent(btn_PesqC, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)))))
								.addComponent(Jfr_Data, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(179)
							.addComponent(lbl_CadastrarOS, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)))
					.addGap(59))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(322)
					.addComponent(btn_Salvar, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(274, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(46, Short.MAX_VALUE)
					.addComponent(lbl_CadastrarOS, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addGap(27)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btn_PesqC, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lbl_Cliente, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(textField, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(12)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
								.addComponent(lbl_Funcionario, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(11)
							.addComponent(btn_PesqF, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(Jfr_Data, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbl_Data, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btn_Salvar, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addGap(45)
					.addComponent(btn_voltar)
					.addGap(45))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
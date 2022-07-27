package br.com.sigoa.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
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

import br.com.sigoa.model.Cliente;
import br.com.sigoa.dao.ClienteDAO;
import br.com.sigoa.controller.ControllerTelaCadastroCliente;
import javax.swing.SwingConstants;
import javax.swing.JFormattedTextField;

public class TelaCadastroCliente extends JFrame {

	private JPanel contentPane;
	private JTextField txt_Nome;
	private JTextField txt_Email;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroCliente frame = new TelaCadastroCliente();
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
	public TelaCadastroCliente() throws ParseException {
		setTitle("SIGOA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Menu");
		menuBar.add(mnNewMenu);
		
		JMenu mnNewMenu_1 = new JMenu("Cadastro");
		mnNewMenu.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Cadastrar cliente");
		mnNewMenu_1.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Cadastrar servi\u00E7o");
		mnNewMenu_1.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Cadastrar funcion\u00E1rio");
		mnNewMenu_1.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Cadastrar pe\u00E7as");
		mnNewMenu_1.add(mntmNewMenuItem_3);
		
		JMenu mnNewMenu_2 = new JMenu("Estoque");
		mnNewMenu.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Consultar");
		mnNewMenu_2.add(mntmNewMenuItem_4);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Registrar entrada");
		mnNewMenu_2.add(mntmNewMenuItem_5);
		
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
		
		JLabel lbl_CadastrarCliente = new JLabel("Cadastrar Cliente");
		lbl_CadastrarCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_CadastrarCliente.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lbl_Nome = new JLabel("Nome");
		lbl_Nome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lbl_Email = new JLabel("Email");
		lbl_Email.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lbl_CpfCnpj = new JLabel("CPF");
		lbl_CpfCnpj.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		txt_Nome = new JTextField();
		txt_Nome.setColumns(10);
		
		txt_Email = new JTextField();
		txt_Email.setColumns(10);
		
		JFormattedTextField frtCPF = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
		frtCPF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frtCPF.setColumns(15);
		
		JFormattedTextField frt_Telefone = new JFormattedTextField(new MaskFormatter("(##) #####-####"));
		frt_Telefone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frt_Telefone.setColumns(15);
		
		JButton btn_Voltar = new JButton("Sair");
		btn_Voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastroCliente.this.dispose();
			}
		});
		
		JButton btn_Salvar = new JButton("Salvar");
		btn_Salvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cliente cliente = new Cliente(txt_Nome.getText(), txt_Email.getText(), frtCPF.getText(), frt_Telefone.getText());
				ClienteDAO.CREATE(cliente);
				try {
					ControllerTelaCadastroCliente.abrirTelaCadastroCliente();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				setVisible(false);
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(131, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lbl_Nome, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(txt_Nome, GroupLayout.PREFERRED_SIZE, 388, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblTelefone, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
										.addComponent(lbl_Email, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
										.addComponent(lbl_CpfCnpj, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE))
									.addGap(18)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(txt_Email, GroupLayout.PREFERRED_SIZE, 388, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
											.addComponent(frtCPF, Alignment.LEADING)
											.addComponent(frt_Telefone, Alignment.LEADING)))))
							.addGap(170))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lbl_CadastrarCliente, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
							.addGap(309))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btn_Voltar, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
							.addGap(64))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btn_Salvar, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
							.addGap(312))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(69)
					.addComponent(lbl_CadastrarCliente, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addGap(30)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txt_Nome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbl_Nome, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(13)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lbl_Email, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
								.addComponent(txt_Email, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(45)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lbl_CpfCnpj)
								.addComponent(frtCPF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTelefone)
								.addComponent(frt_Telefone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addGap(60)
					.addComponent(btn_Salvar)
					.addGap(7)
					.addComponent(btn_Voltar)
					.addContainerGap(61, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	
	public TelaCadastroCliente(Integer id, String nome_cliente, String email_cliente, String cpf_cliente, String telefonecliente) throws ParseException {
		setTitle("SIGOA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Menu");
		menuBar.add(mnNewMenu);
		
		JMenu mnNewMenu_1 = new JMenu("Cadastro");
		mnNewMenu.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Cadastrar cliente");
		mnNewMenu_1.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Cadastrar servi\u00E7o");
		mnNewMenu_1.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Cadastrar funcion\u00E1rio");
		mnNewMenu_1.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Cadastrar pe\u00E7as");
		mnNewMenu_1.add(mntmNewMenuItem_3);
		
		JMenu mnNewMenu_2 = new JMenu("Estoque");
		mnNewMenu.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Consultar");
		mnNewMenu_2.add(mntmNewMenuItem_4);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Registrar entrada");
		mnNewMenu_2.add(mntmNewMenuItem_5);
		
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
		
		JLabel lbl_CadastrarCliente = new JLabel("Cadastrar Cliente");
		lbl_CadastrarCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_CadastrarCliente.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lbl_Nome = new JLabel("Nome");
		lbl_Nome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lbl_Email = new JLabel("Email");
		lbl_Email.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lbl_CpfCnpj = new JLabel("CPF/CNPJ");
		lbl_CpfCnpj.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		txt_Nome = new JTextField();
		txt_Nome.setColumns(10);
		txt_Nome.setText(nome_cliente);
		
		txt_Email = new JTextField();
		txt_Email.setColumns(10);
		txt_Email.setText(email_cliente);
		
		JFormattedTextField frtCPF = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
		frtCPF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frtCPF.setText(cpf_cliente);
		frtCPF.setColumns(15);
		
		JFormattedTextField frt_Telefone = new JFormattedTextField(new MaskFormatter("(##) #####-####"));
		frt_Telefone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frt_Telefone.setColumns(15);
		frt_Telefone.setText(telefonecliente);
		

		
		JButton btn_Voltar = new JButton("Sair");
		btn_Voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastroCliente.this.dispose();
			}
		});
		
		JButton btn_Salvar = new JButton("Salvar");
		btn_Salvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cliente cliente = new Cliente();
				cliente.setNome_cliente(txt_Nome.getText());
				cliente.setEmail_cliente(txt_Email.getText());
				cliente.setCpf_cliente(frtCPF.getText());
				cliente.setTelefone_cliente(frt_Telefone.getText());
				cliente.setId_cliente(id);
				
				ClienteDAO.UPDATE(cliente);
				
				setVisible(false);
			}
		});
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(131, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lbl_Nome, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(txt_Nome, GroupLayout.PREFERRED_SIZE, 388, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblTelefone, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
										.addComponent(lbl_Email, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
										.addComponent(lbl_CpfCnpj, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE))
									.addGap(18)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(txt_Email, GroupLayout.PREFERRED_SIZE, 388, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
											.addComponent(frt_Telefone, Alignment.LEADING)
											.addComponent(frtCPF, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)))))
							.addGap(170))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(lbl_CadastrarCliente, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
							.addGap(309))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(btn_Voltar, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
							.addGap(64))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(btn_Salvar, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
							.addGap(312))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(69)
					.addComponent(lbl_CadastrarCliente, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addGap(30)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txt_Nome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbl_Nome, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(13)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lbl_Email, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
								.addComponent(txt_Email, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(45)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(frtCPF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lbl_CpfCnpj))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTelefone)
								.addComponent(frt_Telefone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addGap(60)
					.addComponent(btn_Salvar)
					.addGap(7)
					.addComponent(btn_Voltar)
					.addContainerGap(61, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}

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

import br.com.sigoa.controller.ControllerCliente;
import br.com.sigoa.dao.ClienteDAO;
import br.com.sigoa.model.Cliente;

public class TelaCadastroCliente extends JFrame {

	private JPanel contentPane;
	private JTextField txt_Nome;
	private JTextField txt_Email;

	// INICIALIZAÇÃO
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

	// CRIAÇÃO DO FRAME
	public TelaCadastroCliente() throws ParseException {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				TelaPrincipal.cadCliente=true;
			}
		});
		setTitle("SIGOA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 480);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lbl_CadastrarCliente = new JLabel("Cadastrar Cliente");
		lbl_CadastrarCliente.setBounds(305, 74, 186, 34);
		lbl_CadastrarCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_CadastrarCliente.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JLabel lbl_Nome = new JLabel("Nome");
		lbl_Nome.setBounds(151, 140, 67, 21);
		lbl_Nome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lbl_Email = new JLabel("Email");
		lbl_Email.setBounds(151, 175, 67, 19);
		lbl_Email.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lbl_CpfCnpj = new JLabel("CPF");
		lbl_CpfCnpj.setBounds(151, 209, 67, 19);
		lbl_CpfCnpj.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(151, 243, 67, 19);
		lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txt_Nome = new JTextField();
		txt_Nome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txt_Nome.setBounds(221, 140, 388, 23);
		txt_Nome.setColumns(10);
		
		txt_Email = new JTextField();
		txt_Email.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txt_Email.setBounds(221, 174, 388, 23);
		txt_Email.setColumns(10);
		
		JFormattedTextField frtCPF = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
		frtCPF.setBounds(221, 205, 186, 23);
		frtCPF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frtCPF.setColumns(15);
		
		JFormattedTextField frt_Telefone = new JFormattedTextField(new MaskFormatter("(##) #####-####"));
		frt_Telefone.setBounds(221, 239, 186, 23);
		frt_Telefone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frt_Telefone.setColumns(15);
		
		JButton btn_Voltar = new JButton("  Sair");
		btn_Voltar.setHorizontalAlignment(SwingConstants.LEFT);
		btn_Voltar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_Voltar.setIcon(new ImageIcon(TelaCadastroCliente.class.getResource("/Images/voltar.png")));
		btn_Voltar.setBounds(609, 356, 106, 30);
		btn_Voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaPrincipal.cadCliente=false;
				TelaCadastroCliente.this.dispose();
			}
		});
		
		JButton btn_Salvar = new JButton("    Salvar");
		btn_Salvar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_Salvar.setHorizontalAlignment(SwingConstants.LEFT);
		btn_Salvar.setBounds(344, 297, 122, 30);
		btn_Salvar.setIcon(new ImageIcon(TelaCadastroCliente.class.getResource("/Images/Salvar.png")));
		btn_Salvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cliente cliente = new Cliente(txt_Nome.getText(), txt_Email.getText(), frtCPF.getText(), frt_Telefone.getText());
				ClienteDAO.CREATE(cliente);
				try {
					ControllerCliente.abrirTelaCadastroCliente();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				setVisible(false);
			}
		});
		contentPane.setLayout(null);
		contentPane.add(lbl_Nome);
		contentPane.add(txt_Nome);
		contentPane.add(lblTelefone);
		contentPane.add(lbl_Email);
		contentPane.add(lbl_CpfCnpj);
		contentPane.add(txt_Email);
		contentPane.add(frtCPF);
		contentPane.add(frt_Telefone);
		contentPane.add(lbl_CadastrarCliente);
		contentPane.add(btn_Voltar);
		contentPane.add(btn_Salvar);
	}
	
	public TelaCadastroCliente(Integer id, String nome_cliente, String email_cliente, String cpf_cliente, String telefonecliente) throws ParseException {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				TelaPrincipal.cadCliente=true;
			}
		});
		setTitle("SIGOA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 480);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lbl_CadastrarCliente = new JLabel("Editar Cadastro");
		lbl_CadastrarCliente.setBounds(305, 74, 186, 34);
		lbl_CadastrarCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_CadastrarCliente.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JLabel lbl_Nome = new JLabel("Nome");
		lbl_Nome.setBounds(151, 140, 67, 21);
		lbl_Nome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lbl_Email = new JLabel("Email");
		lbl_Email.setBounds(151, 175, 67, 19);
		lbl_Email.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lbl_CpfCnpj = new JLabel("CPF");
		lbl_CpfCnpj.setBounds(151, 209, 67, 19);
		lbl_CpfCnpj.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(151, 243, 67, 19);
		lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txt_Nome = new JTextField();
		txt_Nome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txt_Nome.setBounds(221, 140, 388, 23);
		txt_Nome.setColumns(10);
		txt_Nome.setText(nome_cliente);
		
		txt_Email = new JTextField();
		txt_Email.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txt_Email.setBounds(221, 174, 388, 23);
		txt_Email.setColumns(10);
		txt_Email.setText(email_cliente);
		
		JFormattedTextField frtCPF = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
		frtCPF.setBounds(221, 205, 186, 23);
		frtCPF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frtCPF.setColumns(15);
		frtCPF.setText(cpf_cliente);
		
		JFormattedTextField frt_Telefone = new JFormattedTextField(new MaskFormatter("(##) #####-####"));
		frt_Telefone.setBounds(221, 239, 186, 23);
		frt_Telefone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frt_Telefone.setColumns(15);
		frt_Telefone.setText(telefonecliente);
		
		JButton btn_Voltar = new JButton("  Sair");
		btn_Voltar.setHorizontalAlignment(SwingConstants.LEFT);
		btn_Voltar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_Voltar.setIcon(new ImageIcon(TelaCadastroCliente.class.getResource("/Images/voltar.png")));
		btn_Voltar.setBounds(609, 356, 106, 30);
		btn_Voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaPrincipal.cadCliente=false;
				TelaCadastroCliente.this.dispose();
			}
		});
		
		JButton btn_Salvar = new JButton("    Salvar");
		btn_Salvar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_Salvar.setHorizontalAlignment(SwingConstants.LEFT);
		btn_Salvar.setBounds(344, 297, 122, 30);
		btn_Salvar.setIcon(new ImageIcon(TelaCadastroCliente.class.getResource("/Images/Salvar.png")));
		btn_Salvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cliente cliente = new Cliente(txt_Nome.getText(), txt_Email.getText(), frtCPF.getText(), frt_Telefone.getText());
				cliente.setId_cliente(id);
				ClienteDAO.UPDATE(cliente);
				setVisible(false);
			}
		});
		contentPane.setLayout(null);
		contentPane.add(lbl_Nome);
		contentPane.add(txt_Nome);
		contentPane.add(lblTelefone);
		contentPane.add(lbl_Email);
		contentPane.add(lbl_CpfCnpj);
		contentPane.add(txt_Email);
		contentPane.add(frtCPF);
		contentPane.add(frt_Telefone);
		contentPane.add(lbl_CadastrarCliente);
		contentPane.add(btn_Voltar);
		contentPane.add(btn_Salvar);
	}
}
package br.com.sigoa.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import br.com.sigoa.controller.ControllerTelaCadastroServico;
import br.com.sigoa.dao.ServicoDAO;
import br.com.sigoa.model.Servico;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;

public class TelaCadastroServico extends JFrame {

	private JPanel contentPane;
	private JTextField txt_Nome;
	private JTextField txt_Valor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroServico frame = new TelaCadastroServico();
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
	public TelaCadastroServico() throws ParseException {
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
		
		JLabel lbl_CadastrarServio = new JLabel("Cadastrar Servi\u00E7o");
		lbl_CadastrarServio.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lbl_Nome = new JLabel("Nome");
		lbl_Nome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		txt_Nome = new JTextField();
		txt_Nome.setColumns(10);
		
		txt_Valor = new JTextField();
		txt_Valor.setColumns(10);
		
		JLabel lbl_Valor = new JLabel("Valor");
		lbl_Valor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JButton btn_Salvar = new JButton("Salvar");
		btn_Salvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Servico servico = new Servico(txt_Nome.getText(), Double.parseDouble(txt_Valor.getText()));
				ServicoDAO.CREATE(servico);
				try {
					ControllerTelaCadastroServico.abrirTelaCadastroServico();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				setVisible(false);
			}
		});
		
		JButton btn_Voltar = new JButton("Sair");
		btn_Voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastroServico.this.dispose();
			}
		});
		
	
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(314, Short.MAX_VALUE)
					.addComponent(lbl_CadastrarServio, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
					.addGap(312))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(149)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btn_Salvar, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
							.addGap(166))
						.addComponent(btn_Voltar, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lbl_Valor, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(txt_Valor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lbl_Nome, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(txt_Nome, GroupLayout.PREFERRED_SIZE, 388, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(166, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(77)
					.addComponent(lbl_CadastrarServio, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addGap(27)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txt_Nome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbl_Nome))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbl_Valor, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(txt_Valor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addComponent(btn_Salvar)
					.addGap(47)
					.addComponent(btn_Voltar)
					.addContainerGap(115, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	public TelaCadastroServico(Integer id_servico, String nome_servico, Double valor_servico) throws ParseException {
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
		
		JLabel lbl_CadastrarServio = new JLabel("Cadastrar Servi\u00E7o");
		lbl_CadastrarServio.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lbl_Nome = new JLabel("Nome");
		lbl_Nome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		txt_Nome = new JTextField();
		txt_Nome.setColumns(10);
		txt_Nome.setText(nome_servico);
		
		JLabel lbl_Valor = new JLabel("Valor");
		lbl_Valor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		txt_Valor = new JTextField();
		txt_Valor.setText(valor_servico.toString());
		txt_Valor.setColumns(10);
		
		JButton btn_Salvar = new JButton("Salvar");
		btn_Salvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Servico servico = new Servico();
				servico.setId_servico(id_servico);
				servico.setNome_servico(txt_Nome.getText());
				servico.setValor_servico(Double.parseDouble(txt_Valor.getText()));
				
				ServicoDAO.UPDATE(servico);
				
				setVisible(false);
			}
		});
		
		JButton btn_Voltar = new JButton("Sair");
		btn_Voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastroServico.this.dispose();
			}
		});
		
	
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(314, Short.MAX_VALUE)
					.addComponent(lbl_CadastrarServio, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
					.addGap(312))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(149)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btn_Salvar, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
							.addGap(166))
						.addComponent(btn_Voltar, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lbl_Valor, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(txt_Valor, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lbl_Nome, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(txt_Nome, GroupLayout.PREFERRED_SIZE, 388, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(166, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(77)
					.addComponent(lbl_CadastrarServio, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addGap(27)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txt_Nome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbl_Nome))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbl_Valor, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(txt_Valor, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addComponent(btn_Salvar)
					.addGap(47)
					.addComponent(btn_Voltar)
					.addContainerGap(115, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
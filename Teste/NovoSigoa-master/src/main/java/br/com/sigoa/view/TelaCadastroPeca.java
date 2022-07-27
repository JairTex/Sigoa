package br.com.sigoa.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.sigoa.controller.ControllerTelaCadastroPeca;
import br.com.sigoa.dao.ClienteDAO;
import br.com.sigoa.dao.PecaDAO;
import br.com.sigoa.model.Cliente;
import br.com.sigoa.model.Peca;

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
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaCadastroPeca extends JFrame {

	private JPanel contentPane;
	private JTextField txt_nome;
	private JTextField txt_fabricante;
	private JTextField txt_valor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroPeca frame = new TelaCadastroPeca();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaCadastroPeca() {
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
		
		JLabel lbl_CadastrarPecas = new JLabel("Cadastrar Pe\u00E7as");
		lbl_CadastrarPecas.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lbl_nome = new JLabel("Nome");
		lbl_nome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		txt_nome = new JTextField();
		txt_nome.setColumns(10);
		
		JLabel lbl_fabricante = new JLabel("Fabricante");
		lbl_fabricante.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		txt_fabricante = new JTextField();
		txt_fabricante.setColumns(10);
		
		JLabel lbl_valor = new JLabel("Valor");
		lbl_valor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		txt_valor = new JTextField();
		txt_valor.setColumns(10);
		
		JButton btn_salvar = new JButton("Salvar");
		btn_salvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Peca peca = new Peca(txt_nome.getText(), txt_fabricante.getText(), 0, Double.parseDouble(txt_valor.getText()));
				PecaDAO.CREATE(peca);
				ControllerTelaCadastroPeca.abrirTelaCadastroPeca();
				setVisible(false);
			}
		});
		
		JButton btn_voltar = new JButton("Sair");
		btn_voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastroPeca.this.dispose();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(124, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lbl_valor, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
								.addGap(28)
								.addComponent(txt_valor, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btn_salvar, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
									.addGap(133)
									.addComponent(btn_voltar, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lbl_fabricante, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
										.addComponent(lbl_nome, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE))
									.addGap(28)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(txt_nome, GroupLayout.PREFERRED_SIZE, 436, GroupLayout.PREFERRED_SIZE)
										.addComponent(txt_fabricante, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 436, GroupLayout.PREFERRED_SIZE)))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lbl_CadastrarPecas, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
							.addGap(190)))
					.addGap(119))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(91)
					.addComponent(lbl_CadastrarPecas, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lbl_nome, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(1)
							.addComponent(txt_nome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbl_fabricante, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(txt_fabricante, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lbl_valor, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
								.addComponent(txt_valor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(btn_salvar))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(67)
							.addComponent(btn_voltar)))
					.addContainerGap(148, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	public TelaCadastroPeca(Integer id_peca, String nome_peca, String fabricante_peca, 
			Double valor_peca, Integer quantidade_peca) {
		
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
		
		JLabel lbl_CadastrarPecas = new JLabel("Cadastrar Pe\u00E7as");
		lbl_CadastrarPecas.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lbl_nome = new JLabel("Nome");
		lbl_nome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		txt_nome = new JTextField();
		txt_nome.setColumns(10);
		txt_nome.setText(nome_peca);
		
		JLabel lbl_fabricante = new JLabel("Fabricante");
		lbl_fabricante.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		txt_fabricante = new JTextField();
		txt_fabricante.setColumns(10);
		txt_fabricante.setText(fabricante_peca);
		
		JLabel lbl_valor = new JLabel("Valor");
		lbl_valor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		txt_valor = new JTextField();
		txt_valor.setColumns(10);
		txt_valor.setText(valor_peca.toString());
		
		JButton btn_salvar = new JButton("Salvar");
		btn_salvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Peca peca = new Peca();
				peca.setNome_peca(txt_nome.getText());
				peca.setFabricante_peca(txt_fabricante.getText());
				peca.setValor_peca(Double.parseDouble(txt_valor.getText()));
				peca.setQuantidade_peca(quantidade_peca);
				peca.setId_peca(id_peca);
				
				PecaDAO.UPDATE(peca);;
				
				setVisible(false);
			}
		});
		
		JButton btn_voltar = new JButton("Sair");
		btn_voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastroPeca.this.dispose();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(124, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lbl_valor, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
								.addGap(28)
								.addComponent(txt_valor, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btn_salvar, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
									.addGap(133)
									.addComponent(btn_voltar, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lbl_fabricante, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
										.addComponent(lbl_nome, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE))
									.addGap(28)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(txt_nome, GroupLayout.PREFERRED_SIZE, 436, GroupLayout.PREFERRED_SIZE)
										.addComponent(txt_fabricante, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 436, GroupLayout.PREFERRED_SIZE)))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lbl_CadastrarPecas, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
							.addGap(190)))
					.addGap(119))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(91)
					.addComponent(lbl_CadastrarPecas, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lbl_nome, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(1)
							.addComponent(txt_nome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbl_fabricante, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(txt_fabricante, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lbl_valor, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
								.addComponent(txt_valor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(btn_salvar))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(67)
							.addComponent(btn_voltar)))
					.addContainerGap(148, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}

}
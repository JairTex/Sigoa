package br.com.sigoa.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import java.awt.Color;
import br.com.sigoa.controller.ControllerTelaCadastroCliente;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import java.awt.Dimension;

public class TelaPrincipal extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
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
	public TelaPrincipal() {
		setTitle("SIGOA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1920, 1080);
		
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
		
		JButton btn_Cliente = new JButton("Clientes");
		btn_Cliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				br.com.sigoa.controller.ControllerTelaPrincipal.abrirMenuClientes();
				TelaPrincipal.this.dispose();
			}
		});
		
		JButton btn_Funcionario = new JButton("Funcionários");
		btn_Funcionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				br.com.sigoa.controller.ControllerTelaPrincipal.abrirMenuFuncionario();
				TelaPrincipal.this.dispose();
			}
		});
		
		JButton btn_OS = new JButton("OS");
		btn_OS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					br.com.sigoa.controller.ControllerTelaOS.abrirTelaOs();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				TelaPrincipal.this.dispose();
			}
		});
		
		JButton btn_Servico = new JButton("Serviços");
		btn_Servico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				br.com.sigoa.controller.ControllerTelaPrincipal.abrirMenuServico();
				TelaPrincipal.this.dispose();
			}
		});
		
		JButton btn_Estoque = new JButton("Estoque");
		btn_Estoque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				br.com.sigoa.controller.ControllerTelaPrincipal.abrirMenuEstoque();
				TelaPrincipal.this.dispose();
			}
		});
		
		JButton btn_Sair = new JButton("SAIR");
		btn_Sair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaPrincipal.this.dispose();
			}
		});
		//Image img = new ImageIcon(this.getclass().getResource("/MenuOficina.png")).getImage();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(95, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addComponent(btn_Servico, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
									.addComponent(btn_Estoque, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(btn_Funcionario, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
										.addComponent(btn_Cliente, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
									.addGap(51)
									.addComponent(btn_OS, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)))
							.addGap(906))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btn_Sair, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
							.addGap(108))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(83)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btn_Cliente, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn_OS, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btn_Funcionario, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btn_Servico, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(btn_Estoque, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 282, Short.MAX_VALUE)
					.addComponent(btn_Sair)
					.addGap(81))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
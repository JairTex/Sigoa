package br.com.sigoa.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;

import br.com.sigoa.controller.ControllerTelaPrincipal;
import javax.swing.JLabel;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

public class TelaPrincipal extends JFrame {
	
	//-----------------------------------VARIÁVEIS LOCAIS-----------------------------------//
	public static boolean menuCliente;
	public static boolean menuFuncionario;
	public static boolean menuServico;
	public static boolean menuEstoque;
	public static boolean menuOS;
	public static boolean cadCliente;
	public static boolean cadOS;
	public static boolean cadFunc;
	public static boolean cadServico;
	public static boolean cadPeca;
	
	
	
	//-------------------------------- TESTE TELA DE FUNDO -----------------------------------//
	
	
	//--------------------------------------- FIM---------------------------------------------//
	
	ControllerTelaPrincipal tela;
	
	// INICIALIZAÇÃO
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

	//CRIAÇÃO DO FRAME
	public TelaPrincipal() {
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				setExtendedState(MAXIMIZED_BOTH);
				
				//Condição para os menus abrirem somente uma vez
				TelaPrincipal.menuCliente=false;
				TelaPrincipal.menuFuncionario=false;
				TelaPrincipal.menuServico=false;
				TelaPrincipal.menuEstoque=false;
				TelaPrincipal.menuOS=false;
				TelaPrincipal.cadCliente=false;
				TelaPrincipal.cadFunc=false;
				TelaPrincipal.cadServico=false;
				TelaPrincipal.cadOS=false;
				TelaPrincipal.cadPeca=false;
			}
		});
		setTitle("SIGOA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1920, 1080);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Cadastros");
		mnNewMenu.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/Images/Cadastrar.png")));
		menuBar.add(mnNewMenu);
		
		JMenu mnNewMenu_3_1 = new JMenu("Consultar");
		mnNewMenu_3_1.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/Images/Visualizar.png")));
		menuBar.add(mnNewMenu_3_1);
		
		JMenu mnNewMenu_3 = new JMenu("Sobre");
		mnNewMenu_3.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/Images/Sobre.png")));
		menuBar.add(mnNewMenu_3);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Especificações");
		mnNewMenu_3.add(mntmNewMenuItem_7);
		
		JMenu mnNewMenu_4 = new JMenu("Suporte");
		mnNewMenu_3.add(mnNewMenu_4);
		
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Ir para a página");
		mnNewMenu_4.add(mntmNewMenuItem_8);
		
		JDesktopPane Jdp_Principal = new JDesktopPane();
		Jdp_Principal.setBackground(SystemColor.activeCaption);
		getContentPane().add(Jdp_Principal, BorderLayout.CENTER);
		
		JMenuItem mntm_CadPeca = new JMenuItem("Cadastrar pe\u00E7as");
		mntm_CadPeca.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK | InputEvent.ALT_DOWN_MASK));
		mntm_CadPeca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cadPeca==false) {
					br.com.sigoa.controller.ControllerPeca.abrirTelaCadastroPeca();
				}
			}
		});
		mnNewMenu.add(mntm_CadPeca);
		
		JMenuItem mntm_CadServ = new JMenuItem("Cadastrar servi\u00E7o");
		mntm_CadServ.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK | InputEvent.ALT_DOWN_MASK));
		mntm_CadServ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cadServico==false) {
					try {
						br.com.sigoa.controller.ControllerServico.abrirTelaCadastroServico();
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		mnNewMenu.add(mntm_CadServ);
		
		JMenuItem mntm_CadFunc = new JMenuItem("Cadastrar funcion\u00E1rio");
		mntm_CadFunc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_DOWN_MASK | InputEvent.ALT_DOWN_MASK));
		mntm_CadFunc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cadFunc==false) {
					try {
						br.com.sigoa.controller.ControllerFuncionario.abrirTelaCadastroFuncionario();
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		mnNewMenu.add(mntm_CadFunc);
		
		JMenuItem mntm_CadCliente = new JMenuItem("Cadastrar cliente");
		mntm_CadCliente.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK | InputEvent.ALT_DOWN_MASK));
		mntm_CadCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cadCliente==false) {
					try {
						br.com.sigoa.controller.ControllerCliente.abrirTelaCadastroCliente();
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				} // Fim if
			}
		});
		mnNewMenu.add(mntm_CadCliente);

		JMenuItem mntm_CadOS = new JMenuItem("Cadastrar OS");
		mntm_CadOS.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK | InputEvent.ALT_DOWN_MASK));
		mntm_CadOS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(cadOS==false) {
					try {
						br.com.sigoa.controller.ControllerTelaPrincipal.abrirCadastroOS();
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				} // Fim if
			}
		});
		mnNewMenu.add(mntm_CadOS);
		
		JMenuItem mntm_ConsEst = new JMenuItem("Consulta de Estoque");
		mntm_ConsEst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(menuEstoque==false) {
					View_ConsultaPeca cf = new View_ConsultaPeca();
					Jdp_Principal.add(cf);
					cf.setVisible(true);
					} // Fim if
			}
		});
		mnNewMenu_3_1.add(mntm_ConsEst);
		
		JMenuItem mntm_ConsOS = new JMenuItem("Consulta de  OS");
		mntm_ConsOS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(menuOS==false) {
					View_ConsultaOS cc = new View_ConsultaOS();
					Jdp_Principal.add(cc);
					cc.setVisible(true);
					} // Fim if
			}
		});
		mnNewMenu_3_1.add(mntm_ConsOS);
		
		JMenuItem mntm_ConsServ = new JMenuItem("Consulta de Serviços");
		mntm_ConsServ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(menuServico==false) {
					View_ConsultaServico cf = new View_ConsultaServico();
					Jdp_Principal.add(cf);
					cf.setVisible(true);
					} // Fim if
			}
		});
		mnNewMenu_3_1.add(mntm_ConsServ);
		
		JMenuItem mntm_ConsClien = new JMenuItem("Consulta de Clientes");
		mntm_ConsClien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(menuCliente==false) {
				View_ConsultaCliente cc = new View_ConsultaCliente();
				Jdp_Principal.add(cc);
				cc.setVisible(true);
				} // Fim if
			}
		});
		mnNewMenu_3_1.add(mntm_ConsClien);
		
		JMenuItem mntm_ConsFunc = new JMenuItem("Consulta de Funcionários");
		mntm_ConsFunc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(menuFuncionario==false) {
					View_ConsultaFuncionario cf = new View_ConsultaFuncionario();
					Jdp_Principal.add(cf);
					cf.setVisible(true);
					} // Fim if
			}
		});
		mnNewMenu_3_1.add(mntm_ConsFunc);
		
		JButton btn_Cliente = new JButton("   Clientes");
		btn_Cliente.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/Images/User-Clients-icon.png")));
		btn_Cliente.setHorizontalAlignment(SwingConstants.LEFT);
		btn_Cliente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_Cliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(menuCliente==false) {
					View_ConsultaCliente cc = new View_ConsultaCliente();
					Jdp_Principal.add(cc);
					cc.setVisible(true);
					} // Fim if
			}
		});
		btn_Cliente.setBounds(67, 57, 172, 58);
		Jdp_Principal.add(btn_Cliente);
		
		JButton btn_Funcionario = new JButton("Funcionarios");
		btn_Funcionario.setHorizontalAlignment(SwingConstants.LEFT);
		btn_Funcionario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_Funcionario.setToolTipText("");
		btn_Funcionario.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/Images/Funcionarios.png")));
		btn_Funcionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(menuFuncionario==false) {
					View_ConsultaFuncionario cf = new View_ConsultaFuncionario();
					Jdp_Principal.add(cf);
					cf.setVisible(true);
					} // Fim if
			}
		});
		btn_Funcionario.setBounds(67, 126, 172, 58);
		Jdp_Principal.add(btn_Funcionario);
		
		JButton btn_Servico = new JButton("   Serviços");
		btn_Servico.setHorizontalAlignment(SwingConstants.LEFT);
		btn_Servico.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_Servico.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/Images/Servi\u00E7os.png")));
		btn_Servico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(menuServico==false) {
					View_ConsultaServico cf = new View_ConsultaServico();
					Jdp_Principal.add(cf);
					cf.setVisible(true);
					} // Fim if
			}
		});
		btn_Servico.setBounds(67, 204, 172, 57);
		Jdp_Principal.add(btn_Servico);
		
		JButton btn_Estoque = new JButton("  Est. Pe\u00E7as");
		btn_Estoque.setHorizontalAlignment(SwingConstants.LEFT);
		btn_Estoque.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_Estoque.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/Images/estoque.png")));
		btn_Estoque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(menuEstoque==false) {
					View_ConsultaPeca cf = new View_ConsultaPeca();
					Jdp_Principal.add(cf);
					cf.setVisible(true);
					} // Fim if
			}
		});
		btn_Estoque.setBounds(67, 281, 172, 57);
		Jdp_Principal.add(btn_Estoque);
		
		JButton btn_OS = new JButton("   OS");
		btn_OS.setHorizontalAlignment(SwingConstants.LEFT);
		btn_OS.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_OS.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/Images/OS.png")));
		btn_OS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cadOS==false) {
					View_ConsultaOS tcc = new View_ConsultaOS();
					if(tcc.isVisible() != true) {
						Jdp_Principal.add(tcc);
						tcc.setVisible(true);
					}
				} // Fim if
			}
		});
		btn_OS.setBounds(67, 360, 172, 58);
		Jdp_Principal.add(btn_OS);
		
		JButton btn_Sair = new JButton("SAIR");
		btn_Sair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btn_Sair.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/Images/next-icon.png")));
		btn_Sair.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_Sair.setBounds(1211, 612, 111, 33);
		Jdp_Principal.add(btn_Sair);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/Images/Planodefundo.png")));
		lblNewLabel.setBounds(0, 0, 1370, 719);
		Jdp_Principal.add(lblNewLabel);
	}
}
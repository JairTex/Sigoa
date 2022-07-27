package br.com.sigoa.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import br.com.sigoa.dao.PecaOSDAO;
import br.com.sigoa.dao.ServicoOSDAO;
import br.com.sigoa.model.OrdemDeServico;
import br.com.sigoa.model.Peca;
import br.com.sigoa.model.Servico;
import br.com.sigoa.report.Relatorio;

public class Tela_VisualizarOS extends JFrame {
	private JTextField txt_Cliente;
	private JTextField txt_Funcionario;
	private JTextField txt_Id;
	private static JTable List_Servicos;
	private static JTable List_Pecas;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrdemDeServico os = new OrdemDeServico();
					Tela_VisualizarOS frame = new Tela_VisualizarOS(os);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}	
	
	
		/**
	 * Create the frame.
	 * 
	 */
	public Tela_VisualizarOS(OrdemDeServico os) {
		setExtendedState(MAXIMIZED_BOTH);
		
		JDesktopPane Jdp_Principal = new JDesktopPane();
		getContentPane().add(Jdp_Principal, BorderLayout.CENTER);
		setTitle("SIGOA");
		setResizable(false);
		
		JLabel lbl_os = new JLabel("ORDEM DE SERVI\u00C7O");
		lbl_os.setIcon(new ImageIcon(Tela_VisualizarOS.class.getResource("/Images/OS.png")));
		lbl_os.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_os.setForeground(Color.WHITE);
		lbl_os.setFont(new Font("Tahoma", Font.BOLD, 18));
		lbl_os.setBounds(550, 21, 245, 42);
		Jdp_Principal.add(lbl_os);
		
		JLabel lbl_os_2 = new JLabel("Confirme os dados da OS");
		lbl_os_2.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_os_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_os_2.setBounds(1301, 82, 431, 22);
		Jdp_Principal.add(lbl_os_2);
		
		JLabel lbl_os_2_1_1_1 = new JLabel("Identifica\u00E7\u00E3o");
		lbl_os_2_1_1_1.setIcon(new ImageIcon(Tela_VisualizarOS.class.getResource("/Images/editar.png")));
		lbl_os_2_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_os_2_1_1_1.setForeground(Color.WHITE);
		lbl_os_2_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_os_2_1_1_1.setBounds(606, 78, 116, 30);
		Jdp_Principal.add(lbl_os_2_1_1_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(325, 110, 694, 145);
		Jdp_Principal.add(panel_1);
		
		JLabel lbl_os_1_1_1_1 = new JLabel("FUNCION\u00C1RIO");
		lbl_os_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_os_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_os_1_1_1_1.setBounds(122, 78, 88, 22);
		panel_1.add(lbl_os_1_1_1_1);
		
		JLabel lbl_os_1_1_2 = new JLabel("CLIENTE");
		lbl_os_1_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_os_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_os_1_1_2.setBounds(148, 46, 62, 21);
		panel_1.add(lbl_os_1_1_2);
		
		txt_Cliente = new JTextField();
		txt_Cliente.setEditable(false);
		txt_Cliente.setColumns(10);
		txt_Cliente.setBounds(228, 43, 367, 23);
		txt_Cliente.setText(os.getNome_cliente());
		panel_1.add(txt_Cliente);
		
		txt_Funcionario = new JTextField();
		txt_Funcionario.setEditable(false);
		txt_Funcionario.setColumns(10);
		txt_Funcionario.setBounds(228, 77, 367, 23);
		txt_Funcionario.setText(os.getNome_funcionairo());
		panel_1.add(txt_Funcionario);
		
		JLabel lbl_os_1_2 = new JLabel("ID");
		lbl_os_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_os_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_os_1_2.setBounds(180, 13, 30, 13);
		panel_1.add(lbl_os_1_2);
		
		txt_Id = new JTextField();
		txt_Id.setEditable(false);
		txt_Id.setColumns(10);
		txt_Id.setBounds(230, 11, 86, 20);
		txt_Id.setText(os.getId_ordem_servico().toString());
		panel_1.add(txt_Id);
		
		JLabel lbl_os_1_2_1 = new JLabel("VALOR (R$)");
		lbl_os_1_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_os_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_os_1_2_1.setBounds(122, 113, 88, 13);
		panel_1.add(lbl_os_1_2_1);
		
		textField = new JTextField();
		textField.setText((String) null);
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(228, 111, 86, 20);
		panel_1.add(textField);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(325, 339, 694, 137);
		Jdp_Principal.add(scrollPane_2);
		
		List_Servicos = new JTable();
		List_Servicos.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"ID", "NOME", "VALOR (R$)", "QUANTIDADE"
				}
			) {
				boolean[] columnEditables = new boolean[] {
					false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
		scrollPane_2.setViewportView(List_Servicos);
		
		JLabel lbl_os_2_1_1 = new JLabel("Servi\u00E7os");
		lbl_os_2_1_1.setIcon(new ImageIcon(Tela_VisualizarOS.class.getResource("/Images/Service-icon.png")));
		lbl_os_2_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_os_2_1_1.setForeground(Color.WHITE);
		lbl_os_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_os_2_1_1.setBounds(513, 307, 311, 30);
		Jdp_Principal.add(lbl_os_2_1_1);
		
		JLabel lbl_os_2_1 = new JLabel("Pe\u00E7as");
		lbl_os_2_1.setIcon(new ImageIcon(Tela_VisualizarOS.class.getResource("/Images/Pe\u00E7as.png")));
		lbl_os_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_os_2_1.setForeground(Color.WHITE);
		lbl_os_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_os_2_1.setBounds(502, 487, 336, 30);
		Jdp_Principal.add(lbl_os_2_1);
		
		JScrollPane scrollPane_2_1 = new JScrollPane();
		scrollPane_2_1.setBounds(325, 518, 694, 147);
		Jdp_Principal.add(scrollPane_2_1);
		
		List_Pecas = new JTable();
		List_Pecas.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"id", "NOME", "VALOR (R$)", "QUANTIDADE"
				}
			) {
				boolean[] columnEditables = new boolean[] {
					false, false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
		scrollPane_2_1.setViewportView(List_Pecas);
		
		JButton btn_AddServico = new JButton("ADICIONAR SERVI\u00C7OS");
		btn_AddServico.setIcon(new ImageIcon(Tela_VisualizarOS.class.getResource("/Images/adicionar.png")));
		btn_AddServico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tela_AddServicoOS cc = new Tela_AddServicoOS(os);
				Jdp_Principal.add(cc);
				cc.setVisible(true);
			}
		});
		btn_AddServico.setBounds(425, 266, 194, 30);
		Jdp_Principal.add(btn_AddServico);
		
		JButton btn_sair = new JButton("SAIR");
		btn_sair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btn_sair.setBounds(1199, 685, 108, 30);
		Jdp_Principal.add(btn_sair);
		
		JButton btn_imprimir = new JButton("IMPRIMIR");
		btn_imprimir.setIcon(new ImageIcon(Tela_VisualizarOS.class.getResource("/Images/Imprimir.png")));
		btn_imprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PecaOSDAO posdao = new PecaOSDAO();
				List<Peca> list = new ArrayList<Peca>(); 
				for(Peca peca: posdao.SELECT_LIST(os)) {
					list.add(peca);
				}
				os.pecas_os = list;
				ServicoOSDAO sosdao = new ServicoOSDAO();
				List<Servico> list2 = new ArrayList<Servico>(); 
				for(Servico servico: sosdao.SELECT_LIST(os)) {
					list2.add(servico);
				}
				os.servico_os = list2;
				
				Relatorio relatorio = new Relatorio();
				relatorio.gerarOS(os);
				Relatorio.abrirArquivoPDF();
			}
		});
			
		btn_imprimir.setBackground(new Color(50, 205, 50));
		btn_imprimir.setBounds(896, 685, 123, 30);
		Jdp_Principal.add(btn_imprimir);
		
		JButton btn_AddPeca = new JButton("ADICIONAR PE\u00C7AS");
		btn_AddPeca.setIcon(new ImageIcon(Tela_VisualizarOS.class.getResource("/Images/adicionar.png")));
		btn_AddPeca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tela_AddPecaOS cc = new Tela_AddPecaOS(os);
				Jdp_Principal.add(cc);
				cc.setVisible(true);
			}
		});
		btn_AddPeca.setBounds(723, 266, 182, 30);
		Jdp_Principal.add(btn_AddPeca);
		
		JButton btn_Atualizar = new JButton("Atualizar tabelas");
		btn_Atualizar.setIcon(new ImageIcon(Tela_VisualizarOS.class.getResource("/Images/update.png")));
		btn_Atualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listarJTablePeca(os);
				listarJTableServico(os);
			}
		});
		btn_Atualizar.setBounds(593, 685, 155, 30);
		Jdp_Principal.add(btn_Atualizar);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Tela_VisualizarOS.class.getResource("/Images/Planodefundo.png")));
		lblNewLabel.setBounds(0, 0, 1370, 738);
		Jdp_Principal.add(lblNewLabel);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1389, 772);
		
		listarJTablePeca(os);
		listarJTableServico(os);
		}
		
			//------------------------------ MÉTODOS LOCAIS ----------------------------//
			public static void listarJTablePeca(OrdemDeServico os){
				DefaultTableModel modelo = (DefaultTableModel) List_Pecas.getModel();
				modelo.setNumRows(0);
				PecaOSDAO pdao = new PecaOSDAO();
				for (Peca c : pdao.SELECT_LIST(os)){
					modelo.addRow(new Object[] {
						c.getId_peca(),
						c.getNome_peca(),
						c.getValor_peca(),
						c.getQuantidade_peca_os(),
					});
				}
			}
			
			public static void listarJTableServico(OrdemDeServico os){
				DefaultTableModel modelo = (DefaultTableModel) List_Servicos.getModel();
				modelo.setNumRows(0);
				ServicoOSDAO sdao = new ServicoOSDAO();
				for (Servico s : sdao.SELECT_LIST(os)){		
					modelo.addRow(new Object[] {
						s.getId_servico(),
						s.getNome_servico(),
						s.getValor_servico(),
						s.getQuantidade_servico_os(),
					});
				}
			}
		}
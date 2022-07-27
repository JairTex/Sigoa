package br.com.sigoa.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;

import br.com.sigoa.dao.OsDAO;
import br.com.sigoa.dao.PecaDAO;
import br.com.sigoa.model.OrdemDeServico;

public class View_ConsultaOS extends JInternalFrame {
	private JTextField txt_Arg;
	private static JTable Jt_OS;

	//INICIALIZAÇÃO
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View_ConsultaOS frame = new View_ConsultaOS();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
		
	//CRIAÇÃO DO FRAME
	public View_ConsultaOS() {
		getContentPane().setBackground(SystemColor.inactiveCaption);
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent e) {
				listarJTable();
			}
		});
		setIconifiable(true);
		setMaximizable(true);
		setTitle("Menu OS");
		setBounds(300, 20, 860, 600);
		
		JLabel lbl_os = new JLabel("ORDEM DE SERVIÇOS");
		lbl_os.setIcon(new ImageIcon(View_ConsultaOS.class.getResource("/Images/OS.png")));
		lbl_os.setBounds(291, 11, 245, 55);
		lbl_os.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_os.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lblNewLabel = new JLabel("Consulta por:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(38, 55, 76, 23);
		
		JLabel lblDigiteOArgumento = new JLabel("Digite o argumento:");
		lblDigiteOArgumento.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDigiteOArgumento.setBounds(132, 55, 139, 23);
		
		JComboBox cmb_Tipo = new JComboBox();
		cmb_Tipo.setBounds(38, 77, 76, 30);
		cmb_Tipo.setModel(new DefaultComboBoxModel(new String[] {"ID", "Cliente","Funcionário", "Status"}));
		
		txt_Arg = new JTextField();
		txt_Arg.setBounds(132, 77, 301, 30);
		txt_Arg.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				DefaultTableModel mpl = (DefaultTableModel) Jt_OS.getModel();
				int l = mpl.getRowCount();
				
				if(l>0) {
					while(l>0) {
						((DefaultTableModel) Jt_OS.getModel()).removeRow(l-1);	
						l--;
					}
				}
				
				String tipo=" ";
				String escolha = cmb_Tipo.getSelectedItem().toString().trim();
				
				if (escolha.equals("ID")) {
					tipo = " " + "id_ordem_servico";
				}
				if (escolha.equals("Cliente")) {
					tipo = " " + "nome_cliente";
				}
				if (escolha.equals("Funcionário")) {
					tipo = " " + "nome_funcionario";
				}
				if (escolha.equals("STATUS")) {
					tipo = " " + "status_os";
				}
				String Arg = txt_Arg.getText();
				
				try{
					ResultSet rs = OsDAO.carregaTabela(tipo, Arg);
					DefaultTableModel mp =(DefaultTableModel) Jt_OS.getModel();
					
					while(rs.next()) {
						String Coluna0 = rs.getString("id_ordem_servico").toString().trim();
						String Coluna1 = rs.getString("nome_cliente").toString().trim();
						String Coluna2 = rs.getString("nome_funcionario").toString().trim();
						String Coluna3 = rs.getString("status_os").toString().trim();
						
						mp.addRow(new String[] {Coluna0,Coluna1,Coluna2, Coluna3});
					}
				}catch(SQLException erro) {
				}
				tamanhocolunas();
				Jt_OS.setAutoCreateRowSorter(true);
			}
		});
		txt_Arg.setColumns(10);
		
		JButton btn_Atualizar = new JButton("");
		btn_Atualizar.setIcon(new ImageIcon(View_ConsultaOS.class.getResource("/Images/update.png")));
		btn_Atualizar.setToolTipText("Atualizar tabela.");
		btn_Atualizar.setBounds(756, 77, 44, 30);
		btn_Atualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					listarJTable();
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(38, 125, 762, 347);
		
		JButton btn_Visualizar = new JButton("Visualizar");
		btn_Visualizar.setIcon(new ImageIcon(View_ConsultaOS.class.getResource("/Images/editar.png")));
		btn_Visualizar.setBounds(179, 495, 123, 30);
		btn_Visualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrdemDeServico os = new OrdemDeServico();
				String id_middle = Jt_OS.getValueAt(Jt_OS.getSelectedRow(), 0).toString();
				Integer id_final = Integer.parseInt(id_middle);
				os.setId_ordem_servico(id_final);
				
				os.setNome_cliente(Jt_OS.getValueAt(Jt_OS.getSelectedRow(), 1).toString());
				os.setNome_funcionairo(Jt_OS.getValueAt(Jt_OS.getSelectedRow(), 2).toString());
				os.setStatus_os(Jt_OS.getValueAt(Jt_OS.getSelectedRow(), 3).toString());
				
				Tela_VisualizarOS tvos = new Tela_VisualizarOS(os);
				tvos.setVisible(true);
			}
		});
		
		JButton btn_adicionar = new JButton("Nova");
		btn_adicionar.setIcon(new ImageIcon(View_ConsultaOS.class.getResource("/Images/adicionar.png")));
		btn_adicionar.setBounds(38, 495, 131, 30);
		btn_adicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					br.com.sigoa.controller.ControllerOS.abrirTelaCadastroOS();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		JButton btn_remover = new JButton("Cancelar");
		btn_remover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrdemDeServico os = new OrdemDeServico();
				String id_middle = Jt_OS.getValueAt(Jt_OS.getSelectedRow(), 0).toString();
				Integer id_final = Integer.parseInt(id_middle);
				os.setId_ordem_servico(id_final);
				
				
				Object[] options = { "Sim", "Não" }; 
				Integer option = JOptionPane.showOptionDialog(null, "Deseja cancelar O.S. Nº " + os.getId_ordem_servico() + " ?"
				, "", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				
				if (option == 0) {
					OsDAO.UPDATE_CANCELAR(os);
				}
				if (option == 1) {
					JOptionPane.showMessageDialog(null, "O status da O.S. Nº " + os.getId_ordem_servico() + "não foi modificado!");
				}
			}
		});
		btn_remover.setToolTipText("Cancelar Ordem de Serviço");
		btn_remover.setIcon(new ImageIcon(View_ConsultaOS.class.getResource("/Images/Remover.png")));
		btn_remover.setBounds(312, 495, 123, 30);
		
		JButton btn_sair = new JButton("SAIR");
		btn_sair.setToolTipText("Retornar a tela principal");
		btn_sair.setIcon(new ImageIcon(View_ConsultaOS.class.getResource("/Images/voltar.png")));
		btn_sair.setBounds(677, 495, 123, 30);
		btn_sair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaPrincipal.menuOS=false;
				View_ConsultaOS.this.dispose();
			}
		});
		
		Jt_OS = new JTable();
		Jt_OS.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"ID", "CLIENTE", "RESPONSÁVEL", "STATUS"
				}
			) {
				boolean[] columnEditables = new boolean[] {
					false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
		Jt_OS.setBackground(SystemColor.menu);
		scrollPane.setViewportView(Jt_OS);
		getContentPane().setLayout(null);
		getContentPane().add(lblNewLabel);
		getContentPane().add(lblDigiteOArgumento);
		getContentPane().add(cmb_Tipo);
		getContentPane().add(txt_Arg);
		getContentPane().add(btn_Atualizar);
		getContentPane().add(btn_Visualizar);
		getContentPane().add(btn_adicionar);
		getContentPane().add(btn_remover);
		getContentPane().add(btn_sair);
		getContentPane().add(scrollPane);
		getContentPane().add(lbl_os);
		
		JButton btn_remover_1 = new JButton("Finalizar");
		btn_remover_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrdemDeServico os = new OrdemDeServico();
				String id_middle = Jt_OS.getValueAt(Jt_OS.getSelectedRow(), 0).toString();
				Integer id_final = Integer.parseInt(id_middle);
				os.setId_ordem_servico(id_final);
				
				
				Object[] options = { "Sim", "Não" }; 
				Integer option = JOptionPane.showOptionDialog(null, "O serviço da O.S. Nº " + os.getId_ordem_servico() + " foi concluído?"
				, "", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				
				if (option == 0) {
					OsDAO.UPDATE_FINALIZAR(os);
				}
				if (option == 1) {
					JOptionPane.showMessageDialog(null, "O status da O.S. Nº " + os.getId_ordem_servico() + "não foi modificado!");
				}
			}
		});
		btn_remover_1.setIcon(new ImageIcon(View_ConsultaOS.class.getResource("/Images/Sign-Select-icon.png")));
		btn_remover_1.setBounds(453, 495, 123, 30);
		getContentPane().add(btn_remover_1);

	}
	
	// --------------------------MÉTODOS LOCAIS----------------------------//
	public void tamanhocolunas() {
		Jt_OS.getColumnModel().getColumn(0).setMinWidth(100);
		Jt_OS.getColumnModel().getColumn(1).setMinWidth(250);
	}

	public static void listarJTable(){
		DefaultTableModel modelo = (DefaultTableModel) Jt_OS.getModel();
		modelo.setNumRows(0);
		OsDAO Odao = new OsDAO();
		
		for (OrdemDeServico c: Odao.listar()){
			
			modelo.addRow(new Object[] {
				c.getId_ordem_servico(),
				c.getNome_cliente(),
				c.getNome_funcionairo(),
				c.getStatus_os()
			});
		}
	}
}

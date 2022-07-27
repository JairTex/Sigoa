package br.com.sigoa.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import java.awt.Point;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import br.com.sigoa.dao.ClienteDAO;
import br.com.sigoa.dao.ServicoDAO;
import br.com.sigoa.model.Cliente;
import br.com.sigoa.model.Funcionario;
import br.com.sigoa.model.Servico;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.SystemColor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Component;
import javax.swing.ImageIcon;

public class View_ConsultaServico extends JInternalFrame {
	private JTextField txt_Arg;
	private static JTable Jt_Servico;

	//INICIALIZAÇÃO
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View_ConsultaServico frame = new View_ConsultaServico();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//CRIAÇÃO DO FRAME
	public View_ConsultaServico() {
		getContentPane().setBackground(SystemColor.inactiveCaption);
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent e) {
				
				TelaPrincipal.menuServico=true;
				
				DefaultTableModel mpl = (DefaultTableModel) Jt_Servico.getModel();
				int l=mpl.getRowCount();
				
				if(l>0) {
					while(l>0) {
						((DefaultTableModel) Jt_Servico.getModel()).removeRow(l-1);
						l--;
					}
				}
				try {
				ResultSet rs = ServicoDAO.carregaTabela();
				DefaultTableModel mp =(DefaultTableModel) Jt_Servico.getModel();
				
				while(rs.next()) {
					String Coluna0 = rs.getString("id_servico").toString().trim();
					String Coluna1 = rs.getString("nome_servico").toString().trim();
					String Coluna2 = rs.getString("valor_servico").toString().trim();
					mp.addRow(new String[] {Coluna0,Coluna1,Coluna2});
				}
			}catch(SQLException erro) {
				//JOptionPane.showMessageDialog(this, "Ocorreu um erro:"+ erro, "Preencher Item", 2);
			}
			tamanhocolunas();
			Jt_Servico.setAutoCreateRowSorter(true);
			}
		});
		setIconifiable(true);
		setMaximizable(true);
		setTitle("Menu Servi\u00E7os");
		setBounds(300, 20, 860, 600);
		
		JLabel lbl_servicos = new JLabel("SERVIÇOS");
		lbl_servicos.setIcon(new ImageIcon(View_ConsultaServico.class.getResource("/Images/Servi\u00E7os.png")));
		lbl_servicos.setBounds(305, 11, 245, 62);
		lbl_servicos.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_servicos.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lblNewLabel = new JLabel("Consulta por:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(33, 62, 76, 23);
		
		JLabel lblDigiteOArgumento = new JLabel("Digite o argumento:");
		lblDigiteOArgumento.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDigiteOArgumento.setBounds(127, 62, 261, 23);
		
		JComboBox cmb_TipoS = new JComboBox();
		cmb_TipoS.setBounds(33, 84, 76, 30);
		cmb_TipoS.setModel(new DefaultComboBoxModel(new String[] {"ID", "Nome"}));
		
		txt_Arg = new JTextField();
		txt_Arg.setBounds(127, 84, 423, 30);
		txt_Arg.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String tipo=" ";
				
				String escolha = cmb_TipoS.getSelectedItem().toString().trim();
				
				if (escolha.equals("ID")) {
					tipo = " " + "id_servico";
				}
				if (escolha.equals("Nome")) {
					tipo = " " + "nome_servico";
				}
				String Arg = txt_Arg.getText();
				
				DefaultTableModel mpl = (DefaultTableModel) Jt_Servico.getModel();
				int l = mpl.getRowCount();
				
				if(l>0) {
					while(l>0) {
						((DefaultTableModel) Jt_Servico.getModel()).removeRow(l-1);	
						l--;
					}
				}
				try{
					ResultSet rs = ServicoDAO.carregaTabela(tipo, Arg);
					DefaultTableModel mp =(DefaultTableModel) Jt_Servico.getModel();
					
					while(rs.next()) {
						String Coluna0 = rs.getString("id_servico").toString().trim();
						String Coluna1 = rs.getString("nome_servico").toString().trim();
						String Coluna2 = rs.getString("valor_servico").toString().trim();
						
						mp.addRow(new String[] {Coluna0,Coluna1,Coluna2});
					}
				}catch(SQLException erro) {
					
				}
				tamanhocolunas();
				Jt_Servico.setAutoCreateRowSorter(true);
			}
		});
		txt_Arg.setColumns(10);
		
		JButton btn_Atualizar = new JButton("");
		btn_Atualizar.setIcon(new ImageIcon(View_ConsultaServico.class.getResource("/Images/update.png")));
		btn_Atualizar.setToolTipText("Atualizar tabela.");
		btn_Atualizar.setBounds(765, 84, 47, 30);
		btn_Atualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					listarJTable();
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(33, 132, 784, 342);
		
		JButton btn_editar = new JButton("Editar");
		btn_editar.setIcon(new ImageIcon(View_ConsultaServico.class.getResource("/Images/editar.png")));
		btn_editar.setBounds(166, 503, 123, 30);
		btn_editar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Servico servico = new Servico();
					String id_middle = Jt_Servico.getValueAt(Jt_Servico.getSelectedRow(), 0).toString();
					Integer id_final = Integer.parseInt(id_middle);
					servico.setId_servico(id_final);
					
					servico.setNome_servico(Jt_Servico.getValueAt(Jt_Servico.getSelectedRow(), 1).toString());
					
					String valor_middle = Jt_Servico.getValueAt(Jt_Servico.getSelectedRow(), 2).toString();
					Double valor_final = Double.parseDouble(valor_middle);
					servico.setValor_servico(valor_final);
					
					TelaCadastroServico tcc = new TelaCadastroServico(servico.getId_servico() ,servico.getNome_servico(), servico.getValor_servico());
					tcc.setVisible(true);
					
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		JButton btn_adicionar = new JButton("Novo");
		btn_adicionar.setIcon(new ImageIcon(View_ConsultaServico.class.getResource("/Images/adicionar.png")));
		btn_adicionar.setBounds(33, 503, 123, 30);
		btn_adicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					br.com.sigoa.controller.ControllerServico.abrirTelaCadastroServico();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JButton btn_sair = new JButton("Sair");
		btn_sair.setToolTipText("Retornar a tela principal");
		btn_sair.setIcon(new ImageIcon(View_ConsultaServico.class.getResource("/Images/voltar.png")));
		btn_sair.setBounds(694, 503, 123, 30);
		btn_sair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaPrincipal.menuServico=false;
				View_ConsultaServico.this.dispose();
			}
		});
		
		Jt_Servico = new JTable();
		Jt_Servico.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"ID", "NOME", "VALOR (R$)"
				}
			) {
				boolean[] columnEditables = new boolean[] {
					false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
		Jt_Servico.setBackground(SystemColor.menu);
		scrollPane.setViewportView(Jt_Servico);
		getContentPane().setLayout(null);
		getContentPane().add(btn_editar);
		getContentPane().add(btn_adicionar);
		getContentPane().add(btn_sair);
		getContentPane().add(scrollPane);
		getContentPane().add(cmb_TipoS);
		getContentPane().add(txt_Arg);
		getContentPane().add(lblNewLabel);
		getContentPane().add(lblDigiteOArgumento);
		getContentPane().add(lbl_servicos);
		getContentPane().add(btn_Atualizar);

	}

	// --------------------------MÉTODOS LOCAIS----------------------------//		
	public static void listarJTable(){
		DefaultTableModel modelo = (DefaultTableModel) Jt_Servico.getModel();
		ServicoDAO Sdao = new ServicoDAO();
		modelo.setNumRows(0);
		
		for (Servico s: Sdao.listar()){
			
			modelo.addRow(new Object[] {
				s.getId_servico(),
				s.getNome_servico(),
				s.getValor_servico()
			});
		}
	}
	public void tamanhocolunas() {
	Jt_Servico.getColumnModel().getColumn(0).setMinWidth(100);
		Jt_Servico.getColumnModel().getColumn(1).setMinWidth(250);
	}
}
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import br.com.sigoa.model.Cliente;

public class TelaCadastroOSs extends JFrame {

	private JPanel Jdp_OS;
	private JTextField textField;
	public static boolean PesqCliente;

	// INICIALIZAÇÃO
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroOSs frame = new TelaCadastroOSs();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// CRIAÇÃO DO FRAME
	public TelaCadastroOSs() {
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				TelaPrincipal.cadOS=true;
				TelaCadastroOSs.PesqCliente=false;
			}
		});
		setTitle("SIGOA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 722, 338);
		Jdp_OS = new JPanel();
		Jdp_OS.setBackground(SystemColor.inactiveCaption);
		Jdp_OS.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(Jdp_OS);
		
		JLabel lbl_CadastrarOS = new JLabel("Cadastrar OS");
		lbl_CadastrarOS.setBounds(311, 65, 123, 34);
		lbl_CadastrarOS.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lbl_Cliente = new JLabel("Cliente");
		lbl_Cliente.setBounds(160, 126, 55, 28);
		lbl_Cliente.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField.setBounds(219, 126, 289, 28);
		textField.setColumns(10);
		
		JButton btn_PesqC = new JButton("");
		btn_PesqC.setBounds(518, 126, 33, 30);
		btn_PesqC.setBackground(SystemColor.inactiveCaption);
		btn_PesqC.setIcon(new ImageIcon(TelaCadastroOSs.class.getResource("/Images/search-icon.png")));
		btn_PesqC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					br.com.sigoa.view.TelaCadastroOSs.this.dispose();
					br.com.sigoa.controller.ControllerOS.abrirPesqCliente();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}		
			}
		});
		
		JButton btn_Proximo = new JButton("Pr\u00F3ximo");
		btn_Proximo.setBounds(445, 178, 106, 30);
		btn_Proximo.setIcon(new ImageIcon(TelaCadastroOSs.class.getResource("/Images/Proximo.png")));
		btn_Proximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Impossível abrir uma OS sem cliente!");
			}
		});
		
		JButton btn_voltar = new JButton("Voltar");
		btn_voltar.setIcon(new ImageIcon(TelaCadastroOSs.class.getResource("/Images/voltar.png")));
		btn_voltar.setBounds(332, 177, 103, 30);
		btn_voltar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaPrincipal.cadOS=false;
				TelaCadastroOSs.this.dispose();
			}
		});
		Jdp_OS.setLayout(null);
		Jdp_OS.add(lbl_CadastrarOS);
		Jdp_OS.add(lbl_Cliente);
		Jdp_OS.add(textField);
		Jdp_OS.add(btn_PesqC);
		Jdp_OS.add(btn_voltar);
		Jdp_OS.add(btn_Proximo);
	}
	
	public TelaCadastroOSs(Cliente cliente) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				TelaPrincipal.cadOS=true;
				TelaCadastroOSs.PesqCliente=false;
			}
		});
		setTitle("Menu Ordem de Servi\u00E7o");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 722, 407);
		Jdp_OS = new JPanel();
		Jdp_OS.setBackground(SystemColor.inactiveCaption);
		Jdp_OS.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(Jdp_OS);
		
		JLabel lbl_CadastrarOS = new JLabel("Cadastrar OS");
		lbl_CadastrarOS.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lbl_Cliente = new JLabel("Cliente");
		lbl_Cliente.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setText(cliente.getNome_cliente());
		
		JButton btn_PesqC = new JButton("");
		btn_PesqC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					br.com.sigoa.controller.ControllerOS.abrirPesqCliente();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}			
			}
		});
		
		JButton btn_Proximo = new JButton("Pr\u00F3ximo");
		btn_Proximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastroOSsFuncionario tcosf = new TelaCadastroOSsFuncionario(cliente);
				setVisible(false);
				tcosf.setVisible(true);
			}
		});
		
		JButton btn_voltar = new JButton("Voltar");
		btn_voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaPrincipal.cadOS=false;
				TelaCadastroOSs.this.dispose();
			}
		});
		GroupLayout gl_Jdp_OS = new GroupLayout(Jdp_OS);
		gl_Jdp_OS.setHorizontalGroup(
			gl_Jdp_OS.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_Jdp_OS.createSequentialGroup()
					.addContainerGap(155, Short.MAX_VALUE)
					.addGroup(gl_Jdp_OS.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_Jdp_OS.createSequentialGroup()
							.addGap(151)
							.addComponent(lbl_CadastrarOS, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_Jdp_OS.createSequentialGroup()
							.addComponent(lbl_Cliente, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 289, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(btn_PesqC, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_Jdp_OS.createSequentialGroup()
							.addGap(171)
							.addComponent(btn_voltar, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btn_Proximo, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)))
					.addGap(150))
		);
		gl_Jdp_OS.setVerticalGroup(
			gl_Jdp_OS.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Jdp_OS.createSequentialGroup()
					.addGap(60)
					.addComponent(lbl_CadastrarOS, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addGap(27)
					.addGroup(gl_Jdp_OS.createParallelGroup(Alignment.LEADING)
						.addComponent(lbl_Cliente, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn_PesqC, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addGap(36)
					.addGroup(gl_Jdp_OS.createParallelGroup(Alignment.BASELINE)
						.addComponent(btn_voltar, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn_Proximo, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(146, Short.MAX_VALUE))
		);
		Jdp_OS.setLayout(gl_Jdp_OS);
	}	
}
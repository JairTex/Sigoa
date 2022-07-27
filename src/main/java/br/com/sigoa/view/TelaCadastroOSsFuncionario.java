package br.com.sigoa.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import br.com.sigoa.dao.OsDAO;
import br.com.sigoa.model.Cliente;
import br.com.sigoa.model.Funcionario;
import br.com.sigoa.model.OrdemDeServico;

public class TelaCadastroOSsFuncionario extends JFrame {

	private JPanel Jdp_OS;
	private JTextField textField_1;
	public static boolean PesqCliente;

	// INICIALIZAÇÃO
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cliente cliente = new Cliente();
					TelaCadastroOSsFuncionario frame = new TelaCadastroOSsFuncionario(cliente);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// CRIAÇÃO DO FRAME
	public TelaCadastroOSsFuncionario(Cliente cliente) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				TelaPrincipal.cadOS=true;
				TelaCadastroOSsFuncionario.PesqCliente=false;
			}
		});
		setTitle("Menu Ordem de Serviço");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 612, 404);
		Jdp_OS = new JPanel();
		Jdp_OS.setBackground(SystemColor.inactiveCaption);
		Jdp_OS.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(Jdp_OS);
		
		JLabel lbl_CadastrarOS = new JLabel("Cadastrar OS");
		lbl_CadastrarOS.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JButton btn_PesqF = new JButton("");
		btn_PesqF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Tela_PesqFunc tcs = new Tela_PesqFunc(cliente);
				tcs.setVisible(true);
			}
		});
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JLabel lbl_Funcionario = new JLabel("Funcion\u00E1rio");
		lbl_Funcionario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JButton btn_Proximo = new JButton("Pr\u00F3ximo");
		btn_Proximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Impossível abrir uma OS sem cliente!");
			}
		});
		
		JButton btn_voltar = new JButton("Voltar");
		btn_voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaPrincipal.cadOS=false;
				TelaCadastroOSsFuncionario.this.dispose();
			}
		});
		GroupLayout gl_Jdp_OS = new GroupLayout(Jdp_OS);
		gl_Jdp_OS.setHorizontalGroup(
			gl_Jdp_OS.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Jdp_OS.createSequentialGroup()
					.addGap(70)
					.addGroup(gl_Jdp_OS.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_Jdp_OS.createSequentialGroup()
							.addGap(179)
							.addComponent(lbl_CadastrarOS, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_Jdp_OS.createSequentialGroup()
							.addGroup(gl_Jdp_OS.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_Jdp_OS.createSequentialGroup()
									.addComponent(btn_voltar, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(btn_Proximo, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_Jdp_OS.createSequentialGroup()
									.addComponent(lbl_Funcionario, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
									.addGap(4)
									.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 289, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btn_PesqF, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(97, Short.MAX_VALUE))
		);
		gl_Jdp_OS.setVerticalGroup(
			gl_Jdp_OS.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Jdp_OS.createSequentialGroup()
					.addGap(86)
					.addComponent(lbl_CadastrarOS, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addGap(28)
					.addGroup(gl_Jdp_OS.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_Jdp_OS.createParallelGroup(Alignment.BASELINE)
							.addComponent(lbl_Funcionario, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
						.addComponent(btn_PesqF, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addGap(35)
					.addGroup(gl_Jdp_OS.createParallelGroup(Alignment.BASELINE)
						.addComponent(btn_Proximo, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn_voltar, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(112, Short.MAX_VALUE))
		);
		Jdp_OS.setLayout(gl_Jdp_OS);
	}
	
	public TelaCadastroOSsFuncionario(Cliente cliente, Funcionario funcionario) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				TelaPrincipal.cadOS=true;
				TelaCadastroOSsFuncionario.PesqCliente=false;
			}
		});
		setTitle("Menu Ordem de Servi\u00E7o");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 612, 404);
		Jdp_OS = new JPanel();
		Jdp_OS.setBackground(SystemColor.inactiveCaption);
		Jdp_OS.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(Jdp_OS);
		
		JLabel lbl_CadastrarOS = new JLabel("Cadastrar OS");
		lbl_CadastrarOS.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JButton btn_PesqF = new JButton("");
		btn_PesqF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tela_PesqFunc tcs = new Tela_PesqFunc(cliente);
				tcs.setVisible(true);
			}
		});
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setText(funcionario.getNome_funcionario());
		
		JLabel lbl_Funcionario = new JLabel("Funcion\u00E1rio");
		lbl_Funcionario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JButton btn_Proximo = new JButton("Pr\u00F3ximo");
		btn_Proximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] options = { "Cancelar", "Confirmar" }; 
				Integer option = JOptionPane.showOptionDialog(null, "Deseja gerar OS para cliente: " + cliente.getNome_cliente() + "\nResponsável: " + funcionario.getNome_funcionario()
				, "", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				
				if (option == 0) {
					TelaCadastroOSsFuncionario tcosff = new TelaCadastroOSsFuncionario(cliente, funcionario);
					tcosff.setVisible(true);
					setVisible(false);
				}
				if (option == 1) {
					OrdemDeServico os = new OrdemDeServico(cliente.getId_cliente(), funcionario.getId_funcionario(), java.time.LocalDate.now().toString(), funcionario.getNome_funcionario(), cliente.getNome_cliente());
					setVisible(false);
					OsDAO.CREATE(os);
					OsDAO.SELECT(os);
				}
			}
		});
		
		JButton btn_voltar = new JButton("Voltar");
		btn_voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaPrincipal.cadOS=false;
				TelaCadastroOSsFuncionario.this.dispose();
			}
		});
		GroupLayout gl_Jdp_OS = new GroupLayout(Jdp_OS);
		gl_Jdp_OS.setHorizontalGroup(
			gl_Jdp_OS.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Jdp_OS.createSequentialGroup()
					.addGap(70)
					.addGroup(gl_Jdp_OS.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_Jdp_OS.createSequentialGroup()
							.addGap(179)
							.addComponent(lbl_CadastrarOS, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_Jdp_OS.createSequentialGroup()
							.addGroup(gl_Jdp_OS.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_Jdp_OS.createSequentialGroup()
									.addComponent(btn_voltar, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(btn_Proximo, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_Jdp_OS.createSequentialGroup()
									.addComponent(lbl_Funcionario, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
									.addGap(4)
									.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 289, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btn_PesqF, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(97, Short.MAX_VALUE))
		);
		gl_Jdp_OS.setVerticalGroup(
			gl_Jdp_OS.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Jdp_OS.createSequentialGroup()
					.addGap(86)
					.addComponent(lbl_CadastrarOS, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addGap(28)
					.addGroup(gl_Jdp_OS.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_Jdp_OS.createParallelGroup(Alignment.BASELINE)
							.addComponent(lbl_Funcionario, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
						.addComponent(btn_PesqF, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addGap(35)
					.addGroup(gl_Jdp_OS.createParallelGroup(Alignment.BASELINE)
						.addComponent(btn_Proximo, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn_voltar, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(112, Short.MAX_VALUE))
		);
		Jdp_OS.setLayout(gl_Jdp_OS);
	}
}
package br.com.sigoa.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;

import br.com.sigoa.dao.UsuarioDAO;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;

public class TelaLogin extends JFrame {
	
	static private TelaLogin instance = null;
	
	static TelaLogin getInstance () {
		if (instance == null)
			instance = new TelaLogin();
		return instance;
	}
	
	UsuarioDAO tl = new UsuarioDAO(this);

	private JPanel contentPane;
	private static JTextField txt_usuario;
	private static JPasswordField pswSenha;

	//INICIALIZAÇÃO
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLogin frame = TelaLogin.getInstance();
					frame.setExtendedState(TelaLogin.MAXIMIZED_BOTH);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//CRIAÇÃO DO FRAME
	public TelaLogin() {
		setTitle("SIGOA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1920, 1080);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 204, 255));
		contentPane.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, new Color(0, 0, 204), new Color(51, 153, 153), new Color(0, 102, 102), null), null));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBounds(2, 2, 501, 745);
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(51, 51, 153), new Color(51, 102, 153), new Color(51, 153, 153), new Color(51, 153, 102)));
		panel.setBackground(SystemColor.activeCaption);
		
		JPanel panel_1 = new JPanel();
		panel_1.setForeground(Color.BLUE);
		panel_1.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_1.setBounds(755, 180, 391, 343);
		
		JButton btn_entrar = new JButton("Entrar");
		btn_entrar.setIcon(new ImageIcon(TelaLogin.class.getResource("/Images/system-log-out-icon.png")));
		btn_entrar.setBounds(138, 266, 138, 30);
		btn_entrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UsuarioDAO.SELECT();
			}
		});
		
		pswSenha = new JPasswordField();
		pswSenha.setBounds(102, 220, 245, 28);
		
		txt_usuario = new JTextField();
		txt_usuario.setBounds(102, 182, 245, 29);
		txt_usuario.setColumns(10);
		
		JLabel lbl_usuario = new JLabel("Usu\u00E1rio");
		lbl_usuario.setBounds(35, 182, 57, 30);
		lbl_usuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lbl_senha = new JLabel("Senha");
		lbl_senha.setBounds(35, 220, 57, 28);
		lbl_senha.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("SIGOA");
		lblNewLabel.setFont(new Font("Lucida Handwriting", Font.BOLD, 55));
		
		JLabel lblBemVindoDe = new JLabel("Bem Vindo");
		lblBemVindoDe.setLabelFor(this);
		lblBemVindoDe.setAutoscrolls(true);
		lblBemVindoDe.setAlignmentY(Component.TOP_ALIGNMENT);
		lblBemVindoDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblBemVindoDe.setForeground(new Color(255, 255, 255));
		lblBemVindoDe.setFont(new Font("Lucida Handwriting", Font.BOLD, 45));
		
		JLabel lblDeVolta = new JLabel("de Volta!");
		lblDeVolta.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeVolta.setForeground(Color.WHITE);
		lblDeVolta.setFont(new Font("Lucida Handwriting", Font.BOLD, 45));
		lblDeVolta.setAutoscrolls(true);
		lblDeVolta.setAlignmentY(0.0f);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(40)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDeVolta, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(95)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblBemVindoDe, GroupLayout.PREFERRED_SIZE, 407, Short.MAX_VALUE))
					.addGap(40))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(183)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
					.addGap(77)
					.addComponent(lblBemVindoDe)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblDeVolta)
					.addContainerGap(290, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPane.add(panel);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		panel_1.add(lbl_usuario);
		panel_1.add(txt_usuario);
		panel_1.add(lbl_senha);
		panel_1.add(pswSenha);
		panel_1.add(btn_entrar);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(138, 35, 128, 128);
		panel_1.add(lblNewLabel_1);
		lblNewLabel_1.setIcon(new ImageIcon(TelaLogin.class.getResource("/Images/Admin-icon.png")));
	}

	public TelaLogin(TelaLogin telaLogin) {
	}

	public static JTextField getTxt_usuario() {
		return txt_usuario;
	}

	public void setTxt_usuario(JTextField txt_usuario) {
		this.txt_usuario = txt_usuario;
	}

	public static JPasswordField getPswSenha() {
		return pswSenha;
	}

	public void setPswSenha(JPasswordField pswSenha) {
		this.pswSenha = pswSenha;
	}
}
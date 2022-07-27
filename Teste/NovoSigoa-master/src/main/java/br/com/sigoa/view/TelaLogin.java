package br.com.sigoa.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JPasswordField;

import br.com.sigoa.controller.*;
import br.com.sigoa.dao.UsuarioDAO;
import br.com.sigoa.model.Usuario;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import java.awt.BorderLayout;
import javax.swing.JTextPane;
import java.awt.TextArea;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.Window.Type;

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

	/**
	 * Create the frame.
	 */
	
	public TelaLogin() {
		setTitle("SIGOA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1920, 1080);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 204, 255));
		contentPane.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, new Color(0, 0, 204), new Color(51, 153, 153), new Color(0, 102, 102), null), null));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(51, 51, 153), new Color(51, 102, 153), new Color(51, 153, 153), new Color(51, 153, 102)));
		panel.setBackground(SystemColor.activeCaption);
		
		JPanel panel_1 = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 501, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 267, Short.MAX_VALUE)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 376, GroupLayout.PREFERRED_SIZE)
					.addGap(222))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(217)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 311, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(217, Short.MAX_VALUE))
		);
		
		
		JButton btn_entrar = new JButton("Entrar");
		btn_entrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UsuarioDAO.SELECT();
			}
		});
		
		pswSenha = new JPasswordField();
		
		txt_usuario = new JTextField();
		txt_usuario.setColumns(10);
		
		JLabel lbl_usuario = new JLabel("Usu\u00E1rio");
		lbl_usuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lbl_senha = new JLabel("Senha");
		lbl_senha.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap(34, Short.MAX_VALUE)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(lbl_usuario, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
									.addGap(10)
									.addComponent(txt_usuario, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(lbl_senha, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
									.addGap(10)
									.addComponent(pswSenha, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE)))
							.addGap(30))
						.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
							.addComponent(btn_entrar, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
							.addGap(101))))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(124)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lbl_usuario, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(txt_usuario, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addGap(8)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lbl_senha, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(pswSenha, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btn_entrar, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(74, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
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
		contentPane.setLayout(gl_contentPane);
	}

	public TelaLogin(TelaLogin telaLogin) {
		// TODO Auto-generated constructor stub
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
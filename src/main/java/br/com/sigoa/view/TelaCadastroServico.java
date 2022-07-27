package br.com.sigoa.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.text.ParseException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import br.com.sigoa.controller.ControllerServico;
import br.com.sigoa.dao.ServicoDAO;
import br.com.sigoa.model.Servico;

public class TelaCadastroServico extends JFrame {

	private JPanel contentPane;
	private JTextField txt_Nome;
	private JTextField txt_Valor;

	//INICIALIZAÇÃO
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroServico frame = new TelaCadastroServico();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//CRIAÇÃO DO FRAME
	public TelaCadastroServico() throws ParseException {
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				TelaPrincipal.cadServico=true;
			}
		});
		setTitle("SIGOA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 366);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lbl_CadastrarServio = new JLabel("Cadastrar Servi\u00E7o");
		lbl_CadastrarServio.setBounds(320, 43, 148, 34);
		lbl_CadastrarServio.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lbl_Nome = new JLabel("Nome");
		lbl_Nome.setVerticalAlignment(SwingConstants.BOTTOM);
		lbl_Nome.setBounds(168, 105, 79, 23);
		lbl_Nome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		txt_Nome = new JTextField();
		txt_Nome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txt_Nome.setBounds(244, 105, 370, 23);
		txt_Nome.setColumns(10);
		
		txt_Valor = new JTextField();
		txt_Valor.setHorizontalAlignment(JTextField.RIGHT);
		int qtdeDigitos = 10;
		int parteDecimal = 2;
		txt_Valor.addKeyListener(new ValorMasc(txt_Valor,qtdeDigitos,parteDecimal));
		
		txt_Valor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txt_Valor.setBounds(244, 137, 68, 23);
		txt_Valor.setColumns(10);
		
		JLabel lbl_Valor = new JLabel("Valor (R$)");
		lbl_Valor.setVerticalAlignment(SwingConstants.BOTTOM);
		lbl_Valor.setBounds(168, 137, 79, 23);
		lbl_Valor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JButton btn_Salvar = new JButton("  Salvar");
		btn_Salvar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_Salvar.setIcon(new ImageIcon(TelaCadastroServico.class.getResource("/Images/Salvar.png")));
		btn_Salvar.setBounds(359, 185, 109, 30);
		btn_Salvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Valor = txt_Valor.getText().replace(".","");
				String Valor1 = Valor.replace(",",".");
				Servico servico = new Servico(txt_Nome.getText(), Double.parseDouble(Valor1));
				ServicoDAO.CREATE(servico);
				setVisible(false);
			}
		});
		
		JButton btn_Voltar = new JButton("Sair");
		btn_Voltar.setIcon(new ImageIcon(TelaCadastroServico.class.getResource("/Images/voltar.png")));
		btn_Voltar.setBounds(548, 236, 96, 30);
		btn_Voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaPrincipal.cadServico=false;
				TelaCadastroServico.this.dispose();
			}
		});
		contentPane.setLayout(null);
		contentPane.add(lbl_CadastrarServio);
		contentPane.add(btn_Salvar);
		contentPane.add(btn_Voltar);
		contentPane.add(lbl_Valor);
		contentPane.add(txt_Valor);
		contentPane.add(lbl_Nome);
		contentPane.add(txt_Nome);
	}
	
	
	public TelaCadastroServico(Integer id_servico, String nome_servico, Double valor_servico) throws ParseException {
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				TelaPrincipal.cadServico=true;
			}
		});
		setTitle("SIGOA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 366);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lbl_CadastrarServio = new JLabel("Editar Cadastro");
		lbl_CadastrarServio.setBounds(320, 43, 148, 34);
		lbl_CadastrarServio.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lbl_Nome = new JLabel("Nome");
		lbl_Nome.setVerticalAlignment(SwingConstants.BOTTOM);
		lbl_Nome.setBounds(168, 105, 79, 23);
		lbl_Nome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		txt_Nome = new JTextField();
		txt_Nome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txt_Nome.setBounds(244, 105, 370, 23);
		txt_Nome.setColumns(10);
		txt_Nome.setText(nome_servico);
		
		txt_Valor = new JTextField();
		txt_Valor.setHorizontalAlignment(JTextField.RIGHT);
		int qtdeDigitos = 10;
		int parteDecimal = 2;
		txt_Valor.addKeyListener(new ValorMasc(txt_Valor,qtdeDigitos,parteDecimal));
		
		txt_Valor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txt_Valor.setBounds(244, 137, 68, 23);
		txt_Valor.setColumns(10);
		txt_Valor.setText(valor_servico.toString());
		
		JLabel lbl_Valor = new JLabel("Valor (R$)");
		lbl_Valor.setVerticalAlignment(SwingConstants.BOTTOM);
		lbl_Valor.setBounds(168, 137, 79, 23);
		lbl_Valor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JButton btn_Salvar = new JButton("  Salvar");
		btn_Salvar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_Salvar.setIcon(new ImageIcon(TelaCadastroServico.class.getResource("/Images/Salvar.png")));
		btn_Salvar.setBounds(359, 185, 109, 30);
		btn_Salvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Valor = txt_Valor.getText().replace(".","");
				String Valor1 = Valor.replace(",",".");
				Servico servico = new Servico(txt_Nome.getText(), Double.parseDouble(Valor1));
				servico.setId_servico(id_servico);
				
				ServicoDAO.UPDATE(servico);
				setVisible(false);
			}
		});
		
		JButton btn_Voltar = new JButton("Sair");
		btn_Voltar.setIcon(new ImageIcon(TelaCadastroServico.class.getResource("/Images/voltar.png")));
		btn_Voltar.setBounds(548, 236, 96, 30);
		btn_Voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaPrincipal.cadServico=false;
				TelaCadastroServico.this.dispose();
			}
		});
		contentPane.setLayout(null);
		contentPane.add(lbl_CadastrarServio);
		contentPane.add(btn_Salvar);
		contentPane.add(btn_Voltar);
		contentPane.add(lbl_Valor);
		contentPane.add(txt_Valor);
		contentPane.add(lbl_Nome);
		contentPane.add(txt_Nome);
	}
	
	
	
	//------------------------MÉTODOS LOCAIS----------------------------//
	public class ValorMasc implements KeyListener {
		
		JTextField caixaTexto;
		DecimalFormat formato;

		int inteiro, decimal;
		
		public ValorMasc(JTextField jt, int tam, int dec){
			
			caixaTexto = jt;
			inteiro = tam-dec;
			decimal = dec;
			
			String mascara = "";
			
			if((tam-dec)>0){
				for(int a=0; a<dec; a++){mascara += "0";}
				if(mascara.length()>0){mascara = "."+mascara;}
				for(int a=0; a<tam-dec; a++){
					mascara = "#"+mascara;
					if(((a+1)%3)==0 && (a+1)<(tam-dec)){mascara = ","+mascara;}
				}
			}
			formato = new DecimalFormat(mascara);
		} 
		public void keyTyped(KeyEvent e) {   
	        char c = e.getKeyChar();   
	        String textNum = caixaTexto.getText().replace(".","").replace(",","");
	          if(!Character.isDigit(c) || textNum.length()>=(inteiro+decimal) ){   
	            	 e.consume();     
	          }      
	     }   
	     public void keyPressed(KeyEvent e) {  
	     }   
	     public void keyReleased(KeyEvent e) {
	    	 
	       	 String textoAntes = caixaTexto.getText();
	        	 
	        	if(textoAntes.replace(".","").replace(",","").length()<=decimal){
	        		if(inteiro>=decimal){
	        			caixaTexto.setText(textoAntes.replace(".","").replace(",",""));
	        		}else{
	        		}
	            }
	            else if(textoAntes.replace(".","").replace(",","").length()>decimal){
	            	Double num = 0.0;
	               	String textNum = textoAntes.replace(".","").replace(",","");
	               	if(decimal>0){
	               		textNum = textNum.substring(0,textNum.length()-decimal)+"."+textNum.substring(textNum.length()-decimal,textNum.length());
	               	}else{
	               		textNum = textNum+".0";
	               	}
	               	try{
	            		num = Double.parseDouble(textNum);
	            	}catch (Exception exp) { }
	            	        	
	            	String fim = formato.format(num);
	            	if(fim.substring(0,1).equals(",")){fim = "0"+fim;}
	            	caixaTexto.setText(fim);	            	
	            }
	     }
	}
}
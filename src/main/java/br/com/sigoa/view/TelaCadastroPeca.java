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

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import br.com.sigoa.controller.ControllerPeca;
import br.com.sigoa.dao.OsDAO;
import br.com.sigoa.dao.PecaDAO;
import br.com.sigoa.model.OrdemDeServico;
import br.com.sigoa.model.Peca;

public class TelaCadastroPeca extends JFrame {

	private JPanel contentPane;
	private JTextField txt_nome;
	private JTextField txt_fabricante;
	private JTextField txt_valor;

	//INICIALIZAÇÃO
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroPeca frame = new TelaCadastroPeca();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//CRIAÇÃO DO FRAME
	public TelaCadastroPeca() {
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				TelaPrincipal.cadPeca=true;
			}
		});
		setTitle("SIGOA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 418);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lbl_CadastrarPecas = new JLabel("Cadastrar Pe\u00E7as");
		lbl_CadastrarPecas.setBounds(336, 53, 135, 34);
		lbl_CadastrarPecas.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lbl_nome = new JLabel("Nome");
		lbl_nome.setBounds(140, 106, 87, 23);
		lbl_nome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		txt_nome = new JTextField();
		txt_nome.setBounds(225, 106, 436, 23);
		txt_nome.setColumns(10);
		
		JLabel lbl_fabricante = new JLabel("Fabricante");
		lbl_fabricante.setBounds(140, 137, 87, 23);
		lbl_fabricante.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		txt_fabricante = new JTextField();
		txt_fabricante.setBounds(225, 137, 436, 23);
		txt_fabricante.setColumns(10);
		
		JLabel lbl_valor = new JLabel("Valor (R$)");
		lbl_valor.setVerticalAlignment(SwingConstants.BOTTOM);
		lbl_valor.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_valor.setBounds(140, 169, 87, 23);
		lbl_valor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		txt_valor = new JTextField();
		txt_valor.setHorizontalAlignment(JTextField.RIGHT);
		int qtdeDigitos = 10;
		int parteDecimal = 2;
		txt_valor.addKeyListener(new ValorMasc(txt_valor,qtdeDigitos,parteDecimal));
		
		txt_valor.setBounds(225, 171, 196, 23);
		txt_valor.setColumns(14);
		
		JButton btn_salvar = new JButton("Salvar");
		btn_salvar.setIcon(new ImageIcon(TelaCadastroPeca.class.getResource("/Images/Salvar.png")));
		btn_salvar.setBounds(369, 220, 102, 30);
		btn_salvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Valor = txt_valor.getText().replace(".","");
				String Valor1 = Valor.replace(",",".");
				Peca peca = new Peca(txt_nome.getText(), txt_fabricante.getText(), 0, Double.parseDouble(Valor1));
				String quantidade = JOptionPane.showInputDialog(null, "Digite a quantidade de " + peca.getNome_peca() + ":");
				peca.setQuantidade_peca(Integer.parseInt(quantidade));
				PecaDAO.CREATE(peca);
				setVisible(false);
			}
		});
		
		JButton btn_voltar = new JButton("Sair");
		btn_voltar.setIcon(new ImageIcon(TelaCadastroPeca.class.getResource("/Images/voltar.png")));
		btn_voltar.setBounds(565, 269, 96, 30);
		btn_voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaPrincipal.cadPeca=false;
				TelaCadastroPeca.this.dispose();
			}
		});
		contentPane.setLayout(null);
		contentPane.add(lbl_valor);
		contentPane.add(txt_valor);
		contentPane.add(btn_salvar);
		contentPane.add(btn_voltar);
		contentPane.add(lbl_fabricante);
		contentPane.add(lbl_nome);
		contentPane.add(txt_nome);
		contentPane.add(txt_fabricante);
		contentPane.add(lbl_CadastrarPecas);
	}
	
	public TelaCadastroPeca(Integer id_peca, String nome_peca, String fabricante_peca, 
			Double valor_peca, Integer quantidade_peca) {
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				TelaPrincipal.cadPeca=true;
			}
		});
		setTitle("SIGOA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 418);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lbl_CadastrarPecas = new JLabel("Cadastrar Pe\u00E7as");
		lbl_CadastrarPecas.setBounds(336, 53, 135, 34);
		lbl_CadastrarPecas.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lbl_nome = new JLabel("Nome");
		lbl_nome.setBounds(140, 106, 87, 23);
		lbl_nome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		txt_nome = new JTextField();
		txt_nome.setBounds(225, 106, 436, 23);
		txt_nome.setColumns(10);
		txt_nome.setText(nome_peca);
		
		JLabel lbl_fabricante = new JLabel("Fabricante");
		lbl_fabricante.setBounds(140, 137, 87, 23);
		lbl_fabricante.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		txt_fabricante = new JTextField();
		txt_fabricante.setBounds(225, 137, 436, 23);
		txt_fabricante.setColumns(10);
		txt_fabricante.setText(fabricante_peca);
		
		JLabel lbl_valor = new JLabel("Valor (R$)");
		lbl_valor.setVerticalAlignment(SwingConstants.BOTTOM);
		lbl_valor.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_valor.setBounds(140, 169, 87, 23);
		lbl_valor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		txt_valor = new JTextField();
		txt_valor.setHorizontalAlignment(JTextField.RIGHT);
		int qtdeDigitos = 10;
		int parteDecimal = 2;
		txt_valor.addKeyListener(new ValorMasc(txt_valor,qtdeDigitos,parteDecimal));
		
		txt_valor.setBounds(225, 171, 196, 23);
		txt_valor.setColumns(14);
		String valor_peca_middle = valor_peca.toString();
		txt_valor.setToolTipText("Valor unitário das peças.");
		txt_valor.setText(valor_peca_middle);
		
		JButton btn_salvar = new JButton("Salvar");
		btn_salvar.setIcon(new ImageIcon(TelaCadastroPeca.class.getResource("/Images/Salvar.png")));
		btn_salvar.setBounds(369, 220, 102, 30);
		btn_salvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Valor = txt_valor.getText().replace(".","");
				String Valor1 = Valor.replace(",",".");
				Peca peca = new Peca(txt_nome.getText(), txt_fabricante.getText(), 0, Double.parseDouble(Valor1));
				
				peca.setId_peca(id_peca);
				
				Object[] options = { "Sim", "Não" }; 
				Integer option = JOptionPane.showOptionDialog(null, "A quantidade de " + peca.getNome_peca() + "\nainda é " + quantidade_peca + " ?"
				, "", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				
				if (option == 0) {
					peca.setQuantidade_peca(quantidade_peca);
					PecaDAO.UPDATE(peca);
				}
				if (option == 1) {
					String quantidade = JOptionPane.showInputDialog(null, "Digite a quantidade de " + peca.getNome_peca() + ":");
					peca.setQuantidade_peca(Integer.parseInt(quantidade));
					PecaDAO.UPDATE(peca);
				}
				setVisible(false);
			}
		});
		
		JButton btn_voltar = new JButton("Sair");
		btn_voltar.setIcon(new ImageIcon(TelaCadastroPeca.class.getResource("/Images/voltar.png")));
		btn_voltar.setBounds(565, 269, 96, 30);
		btn_voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaPrincipal.cadPeca=false;
				TelaCadastroPeca.this.dispose();
			}
		});
		contentPane.setLayout(null);
		contentPane.add(lbl_valor);
		contentPane.add(txt_valor);
		contentPane.add(btn_salvar);
		contentPane.add(btn_voltar);
		contentPane.add(lbl_fabricante);
		contentPane.add(lbl_nome);
		contentPane.add(txt_nome);
		contentPane.add(txt_fabricante);
		contentPane.add(lbl_CadastrarPecas);
	}

	//-----------------------MÉTODOS LOCAIS----------------------//
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
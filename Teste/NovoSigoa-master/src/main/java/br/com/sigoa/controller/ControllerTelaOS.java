package br.com.sigoa.controller;

import java.text.ParseException;

import br.com.sigoa.view.TelaBuscaServico;
import br.com.sigoa.view.TelaCadastroOS;
import br.com.sigoa.view.TelaOS;
import br.com.sigoa.view.TelaPrincipal;

public class ControllerTelaOS {
	static public void abrirTelaOs() throws ParseException {
		TelaOS tcs = new TelaOS();
		if(tcs.isVisible() != true) {
			tcs.setVisible(true);
		}
	}
	
	static public void abrirTelaCadastroOS() throws ParseException {
		TelaCadastroOS tcs = new TelaCadastroOS();
		if(tcs.isVisible() != true) {
			tcs.setVisible(true);
		}
	}
	
	static public void voltar (){
		TelaPrincipal vtp = new TelaPrincipal();
			vtp.setExtendedState(TelaPrincipal.MAXIMIZED_BOTH);
			vtp.setVisible(true);
		}
	

}

package br.com.sigoa.controller;

import java.text.ParseException;

import br.com.sigoa.view.TelaBuscaCliente;
import br.com.sigoa.view.TelaCadastroCliente;
import br.com.sigoa.view.TelaPrincipal;

public class ControllerTelaCadastroCliente {
	static public void abrirTelaCadastroCliente() throws ParseException {
		TelaCadastroCliente tcc = new TelaCadastroCliente();
		if(tcc.isVisible() != true) {
			tcc.setVisible(true);
		}
	}
	static public void voltar (){
		TelaPrincipal vtp = new TelaPrincipal();
			vtp.setExtendedState(TelaBuscaCliente.MAXIMIZED_BOTH);
			vtp.setVisible(true);
		}
}
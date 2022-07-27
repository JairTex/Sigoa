package br.com.sigoa.controller;

import java.text.ParseException;

import br.com.sigoa.view.TelaBuscaCliente;
import br.com.sigoa.view.TelaBuscaServico;
import br.com.sigoa.view.TelaCadastroServico;
import br.com.sigoa.view.TelaPrincipal;

public class ControllerTelaCadastroServico {
	static public void abrirTelaCadastroServico() throws ParseException {
		TelaCadastroServico tcs = new TelaCadastroServico();
		if(tcs.isVisible() != true) {
			tcs.setVisible(true);
		}
	}
	static public void voltar (){
		TelaPrincipal vtp = new TelaPrincipal();
			vtp.setExtendedState(TelaBuscaServico.MAXIMIZED_BOTH);
			vtp.setVisible(true);
		}
}

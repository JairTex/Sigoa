package br.com.sigoa.controller;

import java.text.ParseException;

import br.com.sigoa.view.TelaCadastroServico;
import br.com.sigoa.view.TelaPrincipal;

public class ControllerServico {
	static public void abrirTelaCadastroServico() throws ParseException {
		TelaCadastroServico tcs = new TelaCadastroServico();
		if(tcs.isVisible() != true) {
			tcs.setVisible(true);
		}
	}
}

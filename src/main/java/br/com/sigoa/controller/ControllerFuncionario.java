package br.com.sigoa.controller;


import java.text.ParseException;

import br.com.sigoa.view.TelaCadastroFuncionario;
import br.com.sigoa.view.TelaPrincipal;

public class ControllerFuncionario {
	static public void abrirTelaCadastroFuncionario() throws ParseException {
		TelaCadastroFuncionario tcf = new TelaCadastroFuncionario();
		if(tcf.isVisible() != true) {
			tcf.setVisible(true);
		}
	}
}

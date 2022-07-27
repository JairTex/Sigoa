package br.com.sigoa.controller;

import br.com.sigoa.view.TelaBuscaServico;

import java.text.ParseException;

import br.com.sigoa.view.TelaBuscaFuncionario;
import br.com.sigoa.view.TelaCadastroFuncionario;
import br.com.sigoa.view.TelaPrincipal;

public class ControllerTelaCadastroFuncionario {
	static public void abrirTelaCadastroFuncionario() throws ParseException {
		TelaCadastroFuncionario tcf = new TelaCadastroFuncionario();
		if(tcf.isVisible() != true) {
			tcf.setVisible(true);
		}
	}
	static public void voltar (){
		TelaPrincipal vtp = new TelaPrincipal();
			vtp.setExtendedState(TelaBuscaFuncionario.MAXIMIZED_BOTH);
			vtp.setVisible(true);
		}
}

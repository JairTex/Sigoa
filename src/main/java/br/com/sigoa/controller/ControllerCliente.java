package br.com.sigoa.controller;

import java.text.ParseException;

import br.com.sigoa.view.TelaCadastroCliente;
import br.com.sigoa.view.TelaPrincipal;
import br.com.sigoa.view.View_ConsultaCliente;

public class ControllerCliente {
	static public void abrirTelaCadastroCliente() throws ParseException {
		TelaCadastroCliente tcc = new TelaCadastroCliente();
		if(tcc.isVisible() != true) {
			tcc.setVisible(true);
		}
	}
	static public void voltar (){
		View_ConsultaCliente vtp = new View_ConsultaCliente();
			vtp.setVisible(true);
		}
}
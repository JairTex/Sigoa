package br.com.sigoa.controller;

import br.com.sigoa.view.TelaCadastroPeca;
import br.com.sigoa.view.TelaPrincipal;

public class ControllerPeca {
	static public void abrirTelaCadastroPeca() {
		TelaCadastroPeca tcp = new TelaCadastroPeca();
		if(tcp.isVisible() != true) {
			tcp.setVisible(true);
		}
	}
}
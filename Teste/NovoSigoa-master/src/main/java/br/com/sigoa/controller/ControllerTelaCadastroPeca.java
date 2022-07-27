package br.com.sigoa.controller;

import br.com.sigoa.view.TelaBuscaServico;
import br.com.sigoa.view.TelaBuscaPeca;
import br.com.sigoa.view.TelaCadastroPeca;
import br.com.sigoa.view.TelaPrincipal;

public class ControllerTelaCadastroPeca {
	static public void abrirTelaCadastroPeca() {
		TelaCadastroPeca tcp = new TelaCadastroPeca();
		if(tcp.isVisible() != true) {
			tcp.setVisible(true);
		}
	}
	static public void voltar (){
		TelaPrincipal vtp = new TelaPrincipal();
			vtp.setExtendedState(TelaBuscaPeca.MAXIMIZED_BOTH);
			vtp.setVisible(true);
		}
}


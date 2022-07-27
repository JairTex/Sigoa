package br.com.sigoa.controller;

import java.text.ParseException;

import br.com.sigoa.model.OrdemDeServico;
import br.com.sigoa.view.TelaCadastroOSs;
import br.com.sigoa.view.Tela_AddPecaOS;
import br.com.sigoa.view.Tela_AddServicoOS;
import br.com.sigoa.view.Tela_PesqCliente;
import br.com.sigoa.view.Tela_PesqFunc;
import br.com.sigoa.view.Tela_VisualizarOS;
import br.com.sigoa.view.View_ConsultaOS;

public class ControllerOS {
	static public void abrirTelaCadastroOS() throws ParseException {
		TelaCadastroOSs tco = new TelaCadastroOSs();
		if(tco.isVisible() != true) {
			tco.setVisible(true);
		}
	}
	static public void abrirTelaOS() {
		View_ConsultaOS tcc = new View_ConsultaOS();
		if(tcc.isVisible() != true) {
			tcc.setVisible(true);
		}	
	}
	
	static public void abrirPesqCliente() throws ParseException {
		Tela_PesqCliente tcs = new Tela_PesqCliente();
		tcs.setVisible(true);
		}
	
	static public void abrirVisualizarOS() throws ParseException {
		OrdemDeServico os = new OrdemDeServico();
		Tela_VisualizarOS tcs = new Tela_VisualizarOS(os);
		tcs.setExtendedState(Tela_VisualizarOS.MAXIMIZED_BOTH);
		tcs.setVisible(true);
		}
	
	/*static public void abrirTelaAddSeervico() throws ParseException {
		Tela_AddServicoOS tcs = new Tela_AddServicoOS();
		//tcs.setExtendedState(Tela_VisualizarOS.MAXIMIZED_BOTH);
		tcs.setVisible(true);
		}
	static public void abrirTelaAddPeca() throws ParseException {
		Tela_AddPecaOS tcs = new Tela_AddPecaOS();
		//tcs.setExtendedState(Tela_VisualizarOS.MAXIMIZED_BOTH);
		tcs.setVisible(true);
		}*/
}
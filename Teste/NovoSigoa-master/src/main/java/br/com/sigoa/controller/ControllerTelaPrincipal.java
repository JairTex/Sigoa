package br.com.sigoa.controller;

import br.com.sigoa.view.TelaPrincipal;
import br.com.sigoa.view.TelaBuscaFuncionario;
import br.com.sigoa.view.TelaBuscaCliente;
import br.com.sigoa.view.TelaBuscaPeca;
import br.com.sigoa.view.TelaBuscaServico;

public class ControllerTelaPrincipal{
	
	static public void abrirTelaPrincipal() {
		TelaPrincipal telaPrincipal = new TelaPrincipal();
		telaPrincipal.setExtendedState(TelaPrincipal.MAXIMIZED_BOTH);
		telaPrincipal.setVisible(true);
	}
	static public void abrirMenuFuncionario() {
		TelaBuscaFuncionario tb = new TelaBuscaFuncionario();
		tb.setExtendedState(TelaBuscaFuncionario.MAXIMIZED_BOTH);
		tb.setVisible(true);
	}
	static public void abrirMenuClientes() {
		TelaBuscaCliente tb = new TelaBuscaCliente();
		tb.setExtendedState(TelaBuscaCliente.MAXIMIZED_BOTH);
		tb.setVisible(true);
	}
	static public void abrirMenuEstoque() {
		TelaBuscaPeca tb = new TelaBuscaPeca();
		tb.setExtendedState(TelaBuscaPeca.MAXIMIZED_BOTH);
		tb.setVisible(true);
	}
	static public void abrirMenuServico() {
		TelaBuscaServico tb = new TelaBuscaServico();
		tb.setExtendedState(TelaBuscaServico.MAXIMIZED_BOTH);
		tb.setVisible(true);
	}
}
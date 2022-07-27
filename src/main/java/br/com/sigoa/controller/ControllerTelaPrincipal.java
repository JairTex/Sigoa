package br.com.sigoa.controller;

import java.text.ParseException;

import br.com.sigoa.view.TelaCadastroOSs;
import br.com.sigoa.view.TelaPrincipal;
import br.com.sigoa.view.View_ConsultaCliente;
import br.com.sigoa.view.View_ConsultaFuncionario;
import br.com.sigoa.view.View_ConsultaOS;
import br.com.sigoa.view.View_ConsultaPeca;
import br.com.sigoa.view.View_ConsultaServico;

public class ControllerTelaPrincipal extends TelaPrincipal{
	
	
	static public void abrirTelaPrincipal() {
		TelaPrincipal telaPrincipal = new TelaPrincipal();
		telaPrincipal.setExtendedState(TelaPrincipal.MAXIMIZED_BOTH);
		telaPrincipal.setVisible(true);
	}
	static public void abrirMenuFuncionario() {
		View_ConsultaFuncionario tb = new View_ConsultaFuncionario();
		tb.setVisible(true);
	}
	static public void abrirMenuClientes() {
		View_ConsultaCliente tb = new View_ConsultaCliente();
		tb.setVisible(true);
	}
	static public void abrirMenuEstoque() {
		View_ConsultaPeca tb = new View_ConsultaPeca();
		tb.setVisible(true);
	}
	static public void abrirMenuServico() {
		View_ConsultaServico tb = new View_ConsultaServico();
		tb.setVisible(true);
	}
	
	static public void abrirMenuOs() throws ParseException {
		View_ConsultaOS tcs = new View_ConsultaOS();
		tcs.setVisible(true);
		}
	
	static public void abrirCadastroOS() throws ParseException {
		TelaCadastroOSs tcs = new TelaCadastroOSs();
		//tcs.setExtendedState(TelaPrincipal.MAXIMIZED_BOTH);
		tcs.setVisible(true);
		}
	
	
	//-------------------------------- TESTE TELA DE FUNDO -----------------------------------//
	
}
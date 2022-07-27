package br.com.sigoa.controller;

import br.com.sigoa.model.Usuario;
import br.com.sigoa.view.TelaLogin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import br.com.sigoa.dao.*;

public class ControllerTelaLogin {

	static public void TelaLogin() {
		TelaLogin telaLogin = new TelaLogin();
		if (telaLogin.isVisible() != true) {
			telaLogin.setVisible(true);
		}
	}
	

}

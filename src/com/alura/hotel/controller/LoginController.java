package com.alura.hotel.controller;

import javax.swing.JPasswordField;

import com.alura.hotel.dao.LoginDAO;
import com.alura.hotel.factory.ConnectionFactory;

public class LoginController {
	private LoginDAO loginDAO;
	
	public LoginController(){
		var factory = new ConnectionFactory();
		this.loginDAO = new LoginDAO(factory.recuperaConexion());
	}
	
	public Boolean logearse(String usuario, JPasswordField txtContrasena) {	
		return loginDAO.logearse(usuario, txtContrasena);
	}
}

package com.alura.hotel.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;


public class ConnectionFactory {

private DataSource datasource;
	
	public ConnectionFactory() {
		var pooleDataSource = new ComboPooledDataSource();
		pooleDataSource.setJdbcUrl("jdbc:mysql://localhost/nombreBaseDeDatos");
		pooleDataSource.setUser("usuario");
		pooleDataSource.setPassword("contraseña");
		pooleDataSource.setMaxPoolSize(10);
		
		this.datasource = pooleDataSource;
	}
	
	public Connection recuperaConexion(){
		 try {
			return this.datasource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}		 
	}
}

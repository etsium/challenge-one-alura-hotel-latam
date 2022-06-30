package com.alura.hotel.controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.alura.hotel.dao.HuespedDAO;
import com.alura.hotel.factory.ConnectionFactory;
import com.alura.hotel.modelo.Huesped;

public class HuespedController {
	private HuespedDAO huespedDAO;
	
	public HuespedController() {
		var factory = new ConnectionFactory();
		this.huespedDAO = new HuespedDAO(factory.recuperaConexion());
	}

	public void guardar(int id, Huesped huesped) {
		huespedDAO.guardarHuesped(id, huesped);
	}
	
	public List<Huesped> ListarHuespedes(){
		
		return huespedDAO.ListarHuespedes();
	}

	public int eliminar(Integer id) {

		return huespedDAO.eliminar(id);

	}

	public void actualizar(Huesped huesped) {

		huespedDAO.actualiar(huesped);

	}
}

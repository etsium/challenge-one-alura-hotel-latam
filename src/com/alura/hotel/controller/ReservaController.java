package com.alura.hotel.controller;

import java.util.List;

import com.alur.hotel.modelo.Reserva;
import com.alura.hotel.dao.ReservaDAO;
import com.alura.hotel.factory.ConnectionFactory;

public class ReservaController {
	private ReservaDAO reservaDAO;
	
	public ReservaController() {
		var factory = new ConnectionFactory();
		this.reservaDAO = new ReservaDAO(factory.recuperaConexion());
	}
	
	public void guardar(Reserva reserva){
		reservaDAO.guardarReserva(reserva);
	}

	public List<Reserva> ListarReservas() {
		return reservaDAO.ListarReservas();
	}
	
	public void actualizar(Reserva reserva) {
		reservaDAO.actualiar(reserva);
	}

	public int eliminar(Integer id) {

		return reservaDAO.eliminar(id);

	}
}

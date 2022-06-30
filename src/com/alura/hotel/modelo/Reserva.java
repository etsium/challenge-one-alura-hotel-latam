package com.alura.hotel.modelo;

import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import views.Busqueda;

public class Reserva {
	private int id;
	private Date fechaEntrada;
	private Date fechaSalida;
	private float valorReserva;
	private String metodoPago;
	private final float precioDia = 150;

	public Reserva(Date fechaEntrada, Date fechaSalida, String metodoPago) {
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.metodoPago = metodoPago;

		valorReserva = calcularValor();
	}
	
	public Reserva(int id, Date fechaEntrada, Date fechaSalida, float valor, String metodoPago) {
		this.id = id;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.valorReserva = valor;
		this.metodoPago = metodoPago;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public float getValorReserva() {
		return this.valorReserva;
	}
	
	public String getMetodoPago() {
		return this.metodoPago;
	}
	
	
	
	private float calcularValor() {
		float valor = 0;

		switch (metodoPago) {
		case "Tarjeta de Crédito":
				valor = (obtenerCantidadDias() * precioDia) + (precioDia * 0.4f);
			break;

		case "Tarjeta de Débito":
				valor = (obtenerCantidadDias() * precioDia) + (precioDia * 0.2f);
			break;
			
		case "Dinero en efectivo":
				valor = obtenerCantidadDias() * precioDia;
			break;
			
		default:
			break;
		}

		return valor;
	}
	
	private int obtenerCantidadDias() {
		long timeDiff = 0;
		long dateE = fechaEntrada.getTime();
		long dateS = fechaSalida.getTime();

		if(dateE > dateS) {
			timeDiff = dateE - dateS;
		} else {
			timeDiff = dateS - dateE;
		}
		
		int daysDiff = (int) (timeDiff / (1000 * 60 * 60* 24));
		
		return (int) daysDiff;
	}
	
	public String getFechaEntradaFormatoDB() {
	    DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
	    
		return targetFormat.format(fechaEntrada);
	}
	
	public Date getFechaEntrada() {
		return fechaEntrada;
	}
	
	public String getFechaSalidaFormatoDB() {
	    DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
	    
		return targetFormat.format(fechaSalida);
	}
	
	public Date getFechaSalida() {
		return fechaSalida;
	}

	@Override
	public String toString() {
	    DateFormat targetFormat = new SimpleDateFormat("dd-MM-yyyy");
	    
	    
		return "Numero de Reserva: " + id + " Fecha de entrada: " + targetFormat.format(fechaEntrada) + " Fecha de salida: " + targetFormat.format(fechaSalida) + " Valor de reserva: " + valorReserva + " Metodo de Pago: " + metodoPago;
	}

}

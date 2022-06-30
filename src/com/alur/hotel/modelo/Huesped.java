package com.alur.hotel.modelo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Huesped {
	private int id;
	private String nombre;
	private String apellido;
	private Date fechaNacimiento;
	private String nacionalidad;
	private String telefono;
	private int idReserva;
	
	
	public Huesped(String nombre, String apellido, Date fechaNacimiento, String nacionalidad, String telefono) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
	}
	
	public Huesped(int id, String nombre, String apellido, Date fechaNacimiento, String nacionalidad, String telefono,
			int idReserva) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
		this.idReserva = idReserva;
	}

	public String getFechaNacimientoFormatoDB() {
	    DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
	    
		return targetFormat.format(fechaNacimiento);

	}
	
	public int getId() {
		return id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getApellido() {
		return apellido;
	}
	
	public String getNacionalidad() {
		return nacionalidad;
	}
	
	public String getTelefono() {
		return telefono;
	}
	
	public int getIdReserva() {
		return idReserva;
	}
	
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	@Override
	public String toString() {
		return "Huesped [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", fechaNacimiento="
				+ fechaNacimiento + ", nacionalidad=" + nacionalidad + ", telefono=" + telefono + ", idReserva="
				+ idReserva + "]";
	}
	
	
}

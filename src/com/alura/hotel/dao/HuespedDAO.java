package com.alura.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.alur.hotel.modelo.Huesped;
import com.alur.hotel.modelo.Reserva;

public class HuespedDAO {
	private Connection con;

	public HuespedDAO(Connection con) {
		this.con = con;
	}

	public void guardarHuesped(int idReserva, Huesped huesped) {
		try{
			final PreparedStatement statement = con.prepareStatement(
					"INSERT INTO HUESPEDES " + "(`Nombre`, `Apellido`, `Fecha de Nacimiento`, `Nacionalidad`, `Telefono`, `Id Reserva`)" + " VALUES(?, ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			try (statement) {
				ejecutaRegistro(idReserva, huesped, statement);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private void ejecutaRegistro(int idReserva, Huesped huesped, PreparedStatement statement) throws SQLException {
		statement.setString(1, huesped.getNombre());
		statement.setString(2, huesped.getApellido());
		statement.setString(3, huesped.getFechaNacimientoFormatoDB());
		statement.setString(4, huesped.getNacionalidad());
		statement.setString(5, huesped.getTelefono());
		statement.setInt(6, idReserva);

		statement.execute();

		final ResultSet resultSet = statement.getGeneratedKeys();
		

	}

	public List<Huesped> ListarHuespedes() {
		List<Huesped> resultado = new ArrayList<>();

		try {

			var querySelect = "SELECT * FROM huespedes";

			final PreparedStatement statement = con.prepareStatement(querySelect);

			try (statement) {

				statement.execute();

				final ResultSet resultSet = statement.getResultSet();

				while (resultSet.next()) {

					Huesped fila = new Huesped(resultSet.getInt("id"), resultSet.getString("nombre"),
							resultSet.getString("apellido"), resultSet.getDate("fecha de nacimiento"),
							resultSet.getString("nacionalidad"), resultSet.getString("telefono"),
							resultSet.getInt("id reserva"));

					resultado.add(fila);

				}

				return resultado;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public int eliminar(Integer id) {
		try {

			final PreparedStatement statement = con.prepareStatement("DELETE FROM huespedes WHERE id = ?");

			try (statement) {

				statement.setInt(1, id);

				statement.execute();

				return statement.getUpdateCount();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void actualiar(Huesped huesped) {
		try{

			final PreparedStatement statement = con.prepareStatement("UPDATE huespedes SET "

					+ "`nombre` = ?"

					+ ", `apellido` = ?"

					+ ", `Fecha de Nacimiento` = ?"

					+ ", `nacionalidad` = ?"

					+ ", `telefono` = ?"

					+ "WHERE id = ?");

			try (statement) {

				statement.setString(1, huesped.getNombre());
				statement.setString(2, huesped.getApellido());
				statement.setString(3, huesped.getFechaNacimientoFormatoDB());
				statement.setString(4, huesped.getNacionalidad());
				statement.setString(5, huesped.getTelefono());
				statement.setInt(6, huesped.getId());

				statement.execute();

			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
}

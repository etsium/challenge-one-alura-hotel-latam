package com.alura.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.alura.hotel.modelo.Huesped;
import com.alura.hotel.modelo.Reserva;

public class ReservaDAO {
	private Connection con;

	public ReservaDAO(Connection con) {
		this.con = con;
	}
	
	public void guardarReserva(Reserva reserva) {
		try{
			final PreparedStatement statement = con.prepareStatement(
					"INSERT INTO RESERVAS " + "(fechaentrada, fechasalida, valor, formapago)" + " VALUES(?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			try (statement) {
				ejecutaRegistro(reserva, statement);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private void ejecutaRegistro(Reserva reserva, PreparedStatement statement) throws SQLException {
		statement.setString(1, reserva.getFechaEntradaFormatoDB());
		statement.setString(2, reserva.getFechaSalidaFormatoDB());
		statement.setFloat(3, reserva.getValorReserva());
		statement.setString(4, reserva.getMetodoPago());

		statement.execute();

		final ResultSet resultSet = statement.getGeneratedKeys();

		try (resultSet) {
			while (resultSet.next()) {
				reserva.setId(resultSet.getInt(1));
			}
		}

	}

	public List<Reserva> ListarReservas() {
		List<Reserva> resultado = new ArrayList<>();

		try {

			var querySelect = "SELECT * FROM reservas";

			final PreparedStatement statement = con.prepareStatement(querySelect);

			try (statement) {

				statement.execute();

				final ResultSet resultSet = statement.getResultSet();

				while (resultSet.next()) {

					Reserva fila = new Reserva(resultSet.getInt("id"), resultSet.getDate("fechaentrada"),
							resultSet.getDate("fechasalida"), resultSet.getFloat("valor"),
							resultSet.getString("formapago"));

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

			final PreparedStatement statement = con.prepareStatement("DELETE FROM reservas WHERE id = ?");

			try (statement) {

				statement.setInt(1, id);

				statement.execute();

				return statement.getUpdateCount();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void actualiar(Reserva reserva) {

		try {

			final PreparedStatement statement = con.prepareStatement("UPDATE reservas SET "

					+ "fechaentrada = ?"

					+ ", fechasalida = ?"

					+ ", valor = ?"

					+ ", formapago = ?"

					+ "WHERE id = ?");

			try (statement) {

				statement.setString(1, reserva.getFechaEntradaFormatoDB());
				statement.setString(2, reserva.getFechaSalidaFormatoDB());
				statement.setFloat(3, reserva.getValorReserva());
				statement.setString(4, reserva.getMetodoPago());
				statement.setInt(5, reserva.getId());

				statement.execute();

			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}


}

package cl.wom.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import cl.wom.beans.Cliente;

public class ClienteDaoImpl implements IClienteDao {

	@Override
	public Cliente getDatosFacturacion(String sql, Connection co) {
		Statement stm = null;
		ResultSet rs = null;

		Cliente cliente = null;

		try {

			stm = co.createStatement();
			rs = stm.executeQuery(sql);

			cliente = new Cliente();
			if (rs.next()) {
				cliente.setMsisdn(rs.getString("MSISDN"));
				cliente.setCustomerId(rs.getString("CUSTOMER_ID"));
				cliente.setCodId(rs.getString("CO_ID"));
				cliente.setRateplan(rs.getString("RATE_PLAN"));
				cliente.setTipoContrato(rs.getString("TIPO_CONTRATO"));
				cliente.setEstado(rs.getString("ESTADO"));
				cliente.setFechaActivacion(rs.getDate("FECHA_ACTIVACION"));
				cliente.setFechaDesactivacion(rs.getDate("FECHA_DESACTIVACION"));

			}
			stm.close();
			rs.close();
			co.close();
		} catch (SQLException e) {
			System.out.println("Error: Clase ClienteDaoImple, método obtener");
			e.printStackTrace();
		}

		return cliente;
	}

	@Override
	public String paymentTransactionId(String sql, Connection co) {
		Statement stm = null;
		ResultSet rs = null;

		Cliente cliente = null;

		try {

			stm = co.createStatement();
			rs = stm.executeQuery(sql);

			cliente = new Cliente();
			if (rs.next()) {

				cliente.setSecuencia(rs.getString("SECUENCIA"));

			}
			stm.close();
			rs.close();
			co.close();
		} catch (SQLException e) {
			System.out.println("Error: Clase ClienteDaoImple, método obtener");
			e.printStackTrace();
		}

		return cliente.getSecuencia();
	}
}

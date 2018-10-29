package cl.wom.database;

import java.sql.Connection;

import cl.wom.beans.Cliente;


public interface IClienteDao {
	public Cliente getDatosFacturacion(String sql,Connection co);
	public String paymentTransactionId (String sql, Connection co);
	public boolean insertAuthorizeCarrierbilling(String sql,Cliente cliente ,Connection co);
}

package cl.wom.database;

import java.sql.Connection;
import java.util.List;

import cl.wom.beans.Cliente;


public interface IClienteDao {
	public Cliente getInfoSuscriptorCarrierBilling(String sql,Connection co);
	public int getSuscripcionesCarrierExist(String sql,Connection co);
	public String getCustomerContractMoreOld(String sql,Connection co);
	public String getCustomerPagador(String sql, Connection co);
	public List<Cliente> getofertacarrier (String sql, Connection co);
	public void insertaregelegcarrierbilling (String sql, Connection co);
	public int validacionPreInsert (String sql, Connection co);
	public String paymentTransactionId (String sql, Connection co);
}

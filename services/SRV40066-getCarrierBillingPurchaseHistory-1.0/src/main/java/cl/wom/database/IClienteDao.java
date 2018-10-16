package cl.wom.database;

import java.util.List;

import org.springframework.stereotype.Component;

import cl.wom.beans.Cliente;


public interface IClienteDao {
	public Cliente getCliente(String sql);
}

package br.com.helpcar.dao;

import br.com.helpcar.entities.Cliente;
import br.com.helpcar.entities.Veiculo;

import java.sql.SQLException;
import java.util.List;

public interface ClienteDao {

    //TODO: Criar assinatura para Create
    void create(Cliente cliente) throws SQLException;

    //TODO: Criar assinatura para Read
    List<Cliente> findAll() throws SQLException;

    //TODO: Criar assinatura para Update
    void update(Cliente cliente) throws SQLException;

    //TODO: Criar assinatura para Delete
    void delete(Long id) throws SQLException;
}

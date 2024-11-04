package br.com.helpcar.dao;

import br.com.helpcar.entities.Cliente;
import br.com.helpcar.entities.Telefone;

import java.sql.SQLException;
import java.util.List;

public interface TelefoneDao {
    //TODO: Criar assinatura para Create
    void create(Telefone telefone) throws SQLException;

    //TODO: Criar assinatura para Read
    List<Telefone > read() throws SQLException;

    //TODO: Criar assinatura para Update
    void update(Telefone telefone) throws SQLException;

    //TODO: Criar assinatura para Delete
    void delete(Long id) throws SQLException;
}

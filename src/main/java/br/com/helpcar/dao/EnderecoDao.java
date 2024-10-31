package br.com.helpcar.dao;

import br.com.helpcar.entities.Cliente;
import br.com.helpcar.entities.Endereco;

import java.sql.SQLException;
import java.util.List;

public interface EnderecoDao {
    //TODO: Criar assinatura para Create
    void create(Endereco endereco) throws SQLException;

    //TODO: Criar assinatura para Read
    List<Endereco> read() throws SQLException;

    //TODO: Criar assinatura para Update
    void update(Endereco endereco) throws SQLException;

    //TODO: Criar assinatura para Delete
    void delete(Long id) throws SQLException;
}

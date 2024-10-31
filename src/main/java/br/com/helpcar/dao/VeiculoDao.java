package br.com.helpcar.dao;

import br.com.helpcar.entities.Veiculo;

import java.sql.SQLException;
import java.util.List;

public interface VeiculoDao {

    //TODO: Criar assinatura para Create
    void create(Veiculo veiculo) throws SQLException;

    //TODO: Criar assinatura para Read
    List<Veiculo> read() throws SQLException;

    //TODO: Criar assinatura para Update
    void update(Veiculo veiculo) throws SQLException;

    //TODO: Criar assinatura para Delete
    void delete(Long id) throws SQLException;
}

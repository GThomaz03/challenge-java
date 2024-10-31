package br.com.helpcar.dao;


import br.com.helpcar.entities.Oficina;

import java.sql.SQLException;
import java.util.List;

public interface OficinaDao {
    //TODO: Criar assinatura para Create
    void create(Oficina oficina) throws SQLException;

    //TODO: Criar assinatura para Read
    List<Oficina> read() throws SQLException;

    //TODO: Criar assinatura para Update
    void update(Oficina oficina) throws SQLException;

    //TODO: Criar assinatura para Delete
    void delete(Long id) throws SQLException;
}

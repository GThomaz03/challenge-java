package br.com.helpcar.dao;

import br.com.helpcar.entities.Servicos;
import java.sql.SQLException;
import java.util.List;

public interface ServicoDao<T extends Servicos> {
    void create(T servico) throws SQLException;
    List<T> read() throws SQLException;
    void update(T servico) throws SQLException;
    void delete(Long id) throws SQLException;
}

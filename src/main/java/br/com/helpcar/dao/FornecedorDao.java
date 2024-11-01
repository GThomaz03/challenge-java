package br.com.helpcar.dao;

import br.com.helpcar.entities.Fornecedor;

import java.sql.SQLException;
import java.util.List;

public interface FornecedorDao {

    void create(Fornecedor fornecedor) throws SQLException;

    List<Fornecedor> read() throws SQLException;

    void delete(Long id) throws SQLException;
}

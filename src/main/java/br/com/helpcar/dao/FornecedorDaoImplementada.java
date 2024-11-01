package br.com.helpcar.dao;

import br.com.helpcar.DatabaseConfig;
import br.com.helpcar.entities.Endereco;
import br.com.helpcar.entities.Fornecedor;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FornecedorDaoImplementada implements FornecedorDao{
    private final DatabaseConfig dbc;

    public FornecedorDaoImplementada(DatabaseConfig dbc) {
        this.dbc = dbc;
    }


    @Override
    public void create(Fornecedor fornecedor) throws SQLException {
        String sql = "INSERT INTO T_HC_FORNECEDOR (NM_SITES_CONFIAVEIS) VALUES (?)";
        try (Connection connection = dbc.getConnection()) {
            for (String site : fornecedor.getSitesConfiaveis()) {
                try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                    pstmt.setString(1, site);
                    pstmt.executeUpdate();
                }
            }
        }
    }



    @Override
    public List<Fornecedor> read() throws SQLException {
        List<Fornecedor> result = new ArrayList<>();
        String sql = "SELECT * FROM T_HC_FORNECEDOR";

        try (Connection connection = dbc.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Long idFornecedor = rs.getLong("ID_FORNECEDOR");
                String sitesConfiaveisStr = rs.getString("NM_SITES_CONFIAVEIS");

                // Convertendo a string de sites confiáveis para uma lista
                List<String> sitesConfiaveis = Arrays.asList(sitesConfiaveisStr.split(","));

                result.add(new Fornecedor(sitesConfiaveis));
            }
        }
        return result;
    }


    @Override
    public void delete(Long id) throws SQLException {

        String sql = "DELETE FROM T_HC_ENDERECO WHERE ID_FORNECEDOR = ?";

        try (Connection connection = dbc.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        }
    }
}

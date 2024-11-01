package br.com.helpcar.dao;

import br.com.helpcar.DatabaseConfig;
import br.com.helpcar.entities.Oficina;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OficinaDaoImplementada implements OficinaDao {
    private final DatabaseConfig dbc;

    public OficinaDaoImplementada(DatabaseConfig dbc) {
        this.dbc = dbc;
    }

    @Override
    public void create(Oficina oficina) throws SQLException {
        String sql = "INSERT INTO T_HC_OFICINA ( NM_OFICINA, CT_OFICINA, NR_CNPJ) VALUES ( ?, ?, ?)";
        try (Connection connection = dbc.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, oficina.getNome());
            pstmt.setString(2, oficina.getTelefone());
            pstmt.setString(3, oficina.getCnpj());
            pstmt.executeUpdate();
        }
    }

    @Override
    public List<Oficina> read() throws SQLException {
        List<Oficina> result = new ArrayList<>();
        String sql = "SELECT * FROM T_HC_OFICINA";

        try (Connection connection = dbc.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Long id = rs.getLong("ID_OFICINA");
                String nome = rs.getString("NM_OFICINA");
                String telefone = rs.getString("CT_OFICINA");
                String cnpj = rs.getString("NR_CNPJ");

                Oficina oficina = new Oficina(id, nome, cnpj, null, null); // Assumindo Endereço e Avaliação nulos por enquanto
                oficina.setTelefone(telefone);
                result.add(oficina);
            }
        }
        return result;
    }

    @Override
    public void update(Oficina oficina) throws SQLException {
        String sql = "UPDATE T_HC_OFICINA SET NM_OFICINA = ?, CT_OFICINA = ?, NR_CNPJ = ? WHERE ID_OFICINA = ?";

        try (Connection connection = dbc.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, oficina.getNome());
            pstmt.setString(2, oficina.getTelefone());
            pstmt.setString(3, oficina.getCnpj());
            pstmt.setLong(4, oficina.getId());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void delete(Long id) throws SQLException {
        String sql = "DELETE FROM T_HC_OFICINA WHERE ID_OFICINA = ?";

        try (Connection connection = dbc.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        }
    }
}

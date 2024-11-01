package br.com.helpcar.dao;

import br.com.helpcar.DatabaseConfig;
import br.com.helpcar.entities.Endereco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnderecoDaoImplementada implements EnderecoDao {
    private final DatabaseConfig dbc;

    public EnderecoDaoImplementada(DatabaseConfig dbc) {
        this.dbc = dbc;
    }

    @Override
    public void create(Endereco endereco) throws SQLException {
        String sql = "INSERT INTO T_HC_ENDERECO ( NM_LOGRADOURO, NR_ENDERECO, DC_COMPLEMENTO, DC_BAIRRO, DC_CIDADE, DC_ESTADO, NR_CEP) VALUES ( ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = dbc.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, endereco.getLogradouro());
            pstmt.setString(2, endereco.getNumero());
            pstmt.setString(3, endereco.getComplemento());
            pstmt.setString(4, endereco.getBairro());
            pstmt.setString(5, endereco.getCidade());
            pstmt.setString(6, endereco.getEstado());
            pstmt.setString(7, endereco.getCep());
            pstmt.executeUpdate();
        }
    }

    @Override
    public List<Endereco> read() throws SQLException {
        List<Endereco> result = new ArrayList<>();
        String sql = "SELECT * FROM T_HC_ENDERECO";

        try (Connection connection = dbc.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Long idEndereco = rs.getLong("ID_ENDERECO");
                String logradouro = rs.getString("NM_LOGRADOURO");
                String numero = rs.getString("NR_ENDERECO");
                String complemento = rs.getString("DC_COMPLEMENTO");
                String bairro = rs.getString("DC_BAIRRO");
                String cidade = rs.getString("DC_CIDADE");
                String estado = rs.getString("DC_ESTADO");
                String cep = rs.getString("NR_CEP");

                result.add(new Endereco(idEndereco, logradouro, numero, complemento, bairro, cidade, estado, cep));
            }
        }
        return result;
    }

    @Override
    public void update(Endereco endereco) throws SQLException {
        String sql = "UPDATE T_HC_ENDERECO SET NM_LOGRADOURO = ?, NR_ENDERECO = ?, DC_COMPLEMENTO = ?, DC_BAIRRO = ?, DC_CIDADE = ?, DC_ESTADO = ?, NR_CEP = ? WHERE ID_ENDERECO = ?";

        try (Connection connection = dbc.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, endereco.getLogradouro());
            pstmt.setString(2, endereco.getNumero());
            pstmt.setString(3, endereco.getComplemento());
            pstmt.setString(4, endereco.getBairro());
            pstmt.setString(5, endereco.getCidade());
            pstmt.setString(6, endereco.getEstado());
            pstmt.setString(7, endereco.getCep());
            pstmt.setLong(8, endereco.getId());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void delete(Long id) throws SQLException {
        String sql = "DELETE FROM T_HC_ENDERECO WHERE ID_ENDERECO = ?";

        try (Connection connection = dbc.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        }
    }
}

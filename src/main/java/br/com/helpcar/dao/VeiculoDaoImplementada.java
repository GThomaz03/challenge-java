package br.com.helpcar.dao;

import br.com.helpcar.entities.Veiculo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeiculoDaoImplementada implements VeiculoDao {
    private final Connection connection;

    public VeiculoDaoImplementada(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Veiculo veiculo) throws SQLException {
        // 1. Buscar o último ID do cliente
        String idQuery = "SELECT MAX(id_cliente) AS ultimo_id FROM t_hc_cliente";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(idQuery);
        Long idCliente = null;

        if (rs.next()) {
            idCliente = rs.getLong("ultimo_id");
        }

        // Verificar se o ID do cliente foi encontrado
        if (idCliente == null) {
            throw new IllegalArgumentException("O ID do cliente não pode ser nulo.");
        }

        // 2. Inserir o veículo com o ID do cliente encontrado
        String sql = "INSERT INTO T_HC_VEICULO (ID_CLIENTE, NR_PLACA, NM_MODELO, NM_MARCA, NR_ANO_FABRICACAO) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setLong(1, idCliente);
        pstmt.setString(2, veiculo.getPlaca());
        pstmt.setString(3, veiculo.getModelo());
        pstmt.setString(4, veiculo.getMarca());
        pstmt.setInt(5, veiculo.getAno());

        pstmt.executeUpdate();
    }

    @Override
    public List<Veiculo> read() throws SQLException {
        List<Veiculo> result = new ArrayList<>();

        String sql = "SELECT * FROM T_HC_VEICULO";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            Long id = rs.getLong("ID_VEICULO");
            Long id_cliente = rs.getLong("ID_CLIENTE");
            String placa = rs.getString("NR_PLACA");
            String modelo = rs.getString("NM_MODELO");
            String marca = rs.getString("NM_MARCA");
            int ano = rs.getInt("NR_ANO_FABRICACAO");

            result.add(new Veiculo(id, id_cliente, placa, modelo, marca, ano));
        }
        return result;
    }

    @Override
    public void update(Veiculo veiculo) throws SQLException {
        String sql = "UPDATE T_HC_VEICULO SET NM_MODELO = ?, NM_MARCA = ?, NR_ANO_FABRICACAO = ? WHERE ID_VEICULO = ?";

        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, veiculo.getModelo());
        pstmt.setString(2, veiculo.getMarca());
        pstmt.setInt(3, veiculo.getAno());
        pstmt.setLong(4, veiculo.getId_veiculo());

        pstmt.executeUpdate();
    }

    @Override
    public void delete(Long id) throws SQLException {
        String sql = "DELETE FROM T_HC_VEICULO WHERE ID_VEICULO = ?";

        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setLong(1, id);

        pstmt.executeUpdate();
    }
}

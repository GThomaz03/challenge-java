package br.com.helpcar.dao;

import br.com.helpcar.config.DatabaseConfig;
import br.com.helpcar.entities.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDaoImplementada implements ClienteDao {
    private final Connection connection;

    public ClienteDaoImplementada( Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Cliente> findAll() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM T_HC_CLIENTE";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getLong("ID_CLIENTE"),
                        rs.getString("NR_CPF"),
                        rs.getString("NM_CLIENTE"),
                        rs.getString("DS_EMAIL"),
                        rs.getString("DS_SENHA")
                );
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    @Override
    public void create(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO T_HC_CLIENTE (NM_CLIENTE, NR_CPF, DS_SENHA, DS_EMAIL) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, cliente.getNome());
            pstmt.setString(2, cliente.getCpf());
            pstmt.setString(3, cliente.getSenha());
            pstmt.setString(4, cliente.getEmail());
            pstmt.executeUpdate();
        }
    }


    @Override
    public void update(Cliente cliente) throws SQLException {
        String sql = "UPDATE T_HC_CLIENTE SET NM_CLIENTE = ?, DS_SENHA = ? WHERE ID_CLIENTE = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, cliente.getNome());
            pstmt.setString(2, cliente.getSenha());
            pstmt.setLong(3, cliente.getIdCliente());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void delete(Long id) throws SQLException {
        String sql = "DELETE FROM T_HC_CLIENTE WHERE ID_CLIENTE = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        }
    }
}

package br.com.helpcar.dao;

import br.com.helpcar.config.DatabaseConfig;
import br.com.helpcar.entities.ServicoReparo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicoReparoDaoImplementada implements ServicoDao<ServicoReparo> {
    private final DatabaseConfig dbc;

    public ServicoReparoDaoImplementada(DatabaseConfig dbc) {
        this.dbc = dbc;
    }

    @Override
    public void create(ServicoReparo servico) throws SQLException {
        String sqlServico = "INSERT INTO T_HC_SERVICO (DS_SERVICO, VL_SERVICO, TP_ESTIMADO, ID_SOLUCAO) VALUES (?, ?, ?, ?)";
        String sqlReparo = "INSERT INTO T_HC_SERVICO_REPARO (ID_SERVICO, NM_COMPONENTE_REPARADO, DS_PROBLEMA) VALUES ( (SELECT MAX(id_servico) AS ultimo_id FROM t_hc_servico), ?, ?)";

        try (Connection connection = dbc.getConnection()) {
            try (PreparedStatement pstmt = connection.prepareStatement(sqlServico)) {
                pstmt.setString(1, servico.getDescricao());
                pstmt.setDouble(2, servico.getPreco());
                pstmt.setInt(3, servico.getTempoEstimado());
                pstmt.setLong(4, servico.getIdSolucao());
                pstmt.executeUpdate();

            }

            try (PreparedStatement pstmtReparo = connection.prepareStatement(sqlReparo)) {
                pstmtReparo.setString(1, servico.getComponenteReparado());
                pstmtReparo.setString(2, servico.getDescricao());
                pstmtReparo.executeUpdate();
            }
        }
    }

    @Override
    public List<ServicoReparo> read() throws SQLException {
        List<ServicoReparo> result = new ArrayList<>();
        String sql = "SELECT s.*, r.NM_COMPONENTE_REPARADO, r.DS_PROBLEMA FROM T_HC_SERVICO s JOIN T_HC_SERVICO_REPARO r ON s.ID_SERVICO = r.ID_SERVICO";

        try (Connection connection = dbc.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                ServicoReparo servico = new ServicoReparo(
                        rs.getString("DS_SERVICO"),
                        rs.getDouble("VL_SERVICO"),
                        rs.getString("NM_COMPONENTE_REPARADO"),
                        rs.getInt("TP_ESTIMADO"),
                        rs.getLong("ID_SOLUCAO"),
                        rs.getString("DS_PROBLEMA")
                );
                servico.setId(rs.getLong("ID_SERVICO"));
                servico.setDescricao(rs.getString("DS_SERVICO"));
                servico.setPreco(rs.getDouble("VL_SERVICO"));
                servico.setTempoEstimado(rs.getInt("TP_ESTIMADO"));
                result.add(servico);
            }
        }
        return result;
    }

    @Override
    public void update(ServicoReparo servico) throws SQLException {
        String sqlServico = "UPDATE T_HC_SERVICO SET DS_SERVICO = ?, VL_SERVICO = ?, TP_ESTIMADO = ? WHERE ID_SERVICO = ?";
        String sqlReparo = "UPDATE T_HC_SERVICO_REPARO SET NM_COMPONENTE_REPARADO = ?, DS_PROBLEMA = ? WHERE ID_SERVICO = ?";

        try (Connection connection = dbc.getConnection()) {
            try (PreparedStatement pstmt = connection.prepareStatement(sqlServico)) {
                pstmt.setString(1, servico.getDescricao());
                pstmt.setDouble(2, servico.getPreco());
                pstmt.setInt(3, servico.getTempoEstimado());
                pstmt.setLong(4, servico.getId());
                pstmt.executeUpdate();
            }

            try (PreparedStatement pstmtReparo = connection.prepareStatement(sqlReparo)) {
                pstmtReparo.setString(1, servico.getComponenteReparado());
                pstmtReparo.setString(2, servico.getDescricao());
                pstmtReparo.setLong(3, servico.getId());
                pstmtReparo.executeUpdate();
            }
        }
    }

    @Override
    public void delete(Long id) throws SQLException {
        String sqlReparo = "DELETE FROM T_HC_SERVICO_REPARO WHERE ID_SERVICO = ?";
        String sqlServico = "DELETE FROM T_HC_SERVICO WHERE ID_SERVICO = ?";

        try (Connection connection = dbc.getConnection()) {
            try (PreparedStatement pstmtReparo = connection.prepareStatement(sqlReparo)) {
                pstmtReparo.setLong(1, id);
                pstmtReparo.executeUpdate();
            }

            try (PreparedStatement pstmtServico = connection.prepareStatement(sqlServico)) {
                pstmtServico.setLong(1, id);
                pstmtServico.executeUpdate();
            }
        }
    }
}

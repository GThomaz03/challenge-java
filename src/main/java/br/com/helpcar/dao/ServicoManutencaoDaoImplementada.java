package br.com.helpcar.dao;

import br.com.helpcar.config.DatabaseConfig;
import br.com.helpcar.entities.ServicoManutencao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicoManutencaoDaoImplementada implements ServicoDao<ServicoManutencao> {
    private final DatabaseConfig dbc;

    public ServicoManutencaoDaoImplementada(DatabaseConfig dbc) {
        this.dbc = dbc;
    }

    @Override
    public void create(ServicoManutencao servico) throws SQLException {
        String sqlServico = "INSERT INTO T_HC_SERVICO (DS_SERVICO, VL_SERVICO, TP_ESTIMADO, ID_SOLUCAO) VALUES (?, ?, ?, ?)";
        String sqlManutencao = "INSERT INTO T_HC_SERVICO_MANUTENCAO ( TP_MANUTENCAO, DS_PERIODICIDADE) VALUES ( ?, ?)";

        try (Connection connection = dbc.getConnection()) {
            try (PreparedStatement pstmt = connection.prepareStatement(sqlServico, Statement.RETURN_GENERATED_KEYS)) {
                pstmt.setString(1, servico.getDescricao());
                pstmt.setDouble(2, servico.getPreco());
                pstmt.setInt(3, servico.getTempoEstimado());
                pstmt.setInt(4, /* Solução ID */ 1);
                pstmt.executeUpdate();

                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        servico.setId(rs.getLong(1));
                    }
                }
            }

            try (PreparedStatement pstmtManutencao = connection.prepareStatement(sqlManutencao)) {
                pstmtManutencao.setString(1, servico.getTipoManutencao());
                pstmtManutencao.setString(2, servico.getPeriodicidade());
                pstmtManutencao.executeUpdate();
            }
        }
    }

    @Override
    public List<ServicoManutencao> read() throws SQLException {
        List<ServicoManutencao> result = new ArrayList<>();
        String sql = "SELECT s.*, m.TP_MANUTENCAO, m.DS_PERIODICIDADE FROM T_HC_SERVICO s JOIN T_HC_SERVICO_MANUTENCAO m ON s.ID_SERVICO = m.ID_SERVICO";

        try (Connection connection = dbc.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                ServicoManutencao servico = new ServicoManutencao(
                        rs.getString("DS_SERVICO"),
                        rs.getDouble("VL_SERVICO"),
                        rs.getString("TP_MANUTENCAO"),
                        rs.getString("DS_PERIODICIDADE"),
                        rs.getInt("TP_ESTIMADO"),
                        rs.getLong("ID_SOLUCAO")
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
    public void update(ServicoManutencao servico) throws SQLException {
        String sqlServico = "UPDATE T_HC_SERVICO SET DS_SERVICO = ?, VL_SERVICO = ?, TP_ESTIMADO = ? WHERE ID_SERVICO = ?";
        String sqlManutencao = "UPDATE T_HC_SERVICO_MANUTENCAO SET TP_MANUTENCAO = ?, DS_PERIODICIDADE = ? WHERE ID_SERVICO = ?";

        try (Connection connection = dbc.getConnection()) {
            try (PreparedStatement pstmt = connection.prepareStatement(sqlServico)) {
                pstmt.setString(1, servico.getDescricao());
                pstmt.setDouble(2, servico.getPreco());
                pstmt.setInt(3, servico.getTempoEstimado());
                pstmt.setLong(4, servico.getId());
                pstmt.executeUpdate();
            }

            try (PreparedStatement pstmtManutencao = connection.prepareStatement(sqlManutencao)) {
                pstmtManutencao.setString(1, servico.getTipoManutencao());
                pstmtManutencao.setString(2, servico.getPeriodicidade());
                pstmtManutencao.setLong(3, servico.getId());
                pstmtManutencao.executeUpdate();
            }
        }
    }

    @Override
    public void delete(Long id) throws SQLException {
        String sqlManutencao = "DELETE FROM T_HC_SERVICO_MANUTENCAO WHERE ID_SERVICO = ?";
        String sqlServico = "DELETE FROM T_HC_SERVICO WHERE ID_SERVICO = ?";

        try (Connection connection = dbc.getConnection()) {
            try (PreparedStatement pstmtManutencao = connection.prepareStatement(sqlManutencao)) {
                pstmtManutencao.setLong(1, id);
                pstmtManutencao.executeUpdate();
            }

            try (PreparedStatement pstmtServico = connection.prepareStatement(sqlServico)) {
                pstmtServico.setLong(1, id);
                pstmtServico.executeUpdate();
            }
        }
    }
}

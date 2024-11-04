package br.com.helpcar.dao;

import br.com.helpcar.config.DatabaseConfig;
import br.com.helpcar.entities.ServicoRevisao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicoRevisaoDaoImplementada implements ServicoDao<ServicoRevisao> {
    private final DatabaseConfig dbc;

    public ServicoRevisaoDaoImplementada(DatabaseConfig dbc) {
        this.dbc = dbc;
    }

    @Override
    public void create(ServicoRevisao servico) throws SQLException {
        String sqlServico = "INSERT INTO T_HC_SERVICO (DS_SERVICO, VL_SERVICO, TP_ESTIMADO, ID_SOLUCAO) VALUES (?, ?, ?, ?)";
        String sqlRevisao = "INSERT INTO T_HC_SERVICO_REVISAO ( ID_SERVICO, NM_ITEM_REVISADO, DS_CHECKLIST) VALUES ((SELECT MAX(id_servico) AS ultimo_id FROM t_hc_servico), ?, ?)";

        try (Connection connection = dbc.getConnection()) {
            // Inicia uma transação para garantir a integridade dos dados
            connection.setAutoCommit(false);

            try (PreparedStatement pstmt = connection.prepareStatement(sqlServico)) {
                pstmt.setString(1, servico.getDescricao());
                pstmt.setDouble(2, servico.getPreco());
                pstmt.setInt(3, servico.getTempoEstimado());
                pstmt.setLong(4, servico.getIdSolucao());
                pstmt.executeUpdate();

            }

            // Inserir na tabela de revisão, utilizando o ID gerado do serviço
            try (PreparedStatement pstmtRevisao = connection.prepareStatement(sqlRevisao)) {
                pstmtRevisao.setString(1, servico.getItensRevisados());
                pstmtRevisao.setString(2, servico.getCheckList());
                pstmtRevisao.executeUpdate();
            }

            // Confirma a transação
            connection.commit();
        }
    }

    @Override
    public List<ServicoRevisao> read() throws SQLException {
        List<ServicoRevisao> result = new ArrayList<>();
        String sql = "SELECT s.*, r.NM_ITEM_REVISADO, r.DS_CHECKLIST FROM T_HC_SERVICO s JOIN T_HC_SERVICO_REVISAO r ON s.ID_SERVICO = r.ID_SERVICO";

        try (Connection connection = dbc.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                ServicoRevisao servico = new ServicoRevisao(
                        rs.getString("DS_SERVICO"),
                        rs.getDouble("VL_SERVICO"),
                        rs.getString("NM_ITEM_REVISADO"),
                        rs.getString("DS_CHECKLIST"),
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
    public void update(ServicoRevisao servico) throws SQLException {
        String sqlServico = "UPDATE T_HC_SERVICO SET DS_SERVICO = ?, VL_SERVICO = ?, TP_ESTIMADO = ? WHERE ID_SERVICO = ?";
        String sqlRevisao = "UPDATE T_HC_SERVICO_REVISAO SET NM_ITEM_REVISADO = ?, DS_CHECKLIST = ? WHERE ID_SERVICO = ?";

        try (Connection connection = dbc.getConnection()) {
            // Atualiza os dados na tabela T_HC_SERVICO
            try (PreparedStatement pstmt = connection.prepareStatement(sqlServico)) {
                pstmt.setString(1, servico.getDescricao());
                pstmt.setDouble(2, servico.getPreco());
                pstmt.setInt(3, servico.getTempoEstimado());
                pstmt.setLong(4, servico.getId());
                pstmt.executeUpdate();
            }

            // Atualiza os dados na tabela T_HC_SERVICO_REVISAO
            try (PreparedStatement pstmtRevisao = connection.prepareStatement(sqlRevisao)) {
                pstmtRevisao.setString(1, servico.getItensRevisados());
                pstmtRevisao.setString(2, servico.getCheckList());
                pstmtRevisao.setLong(3, servico.getId());
                pstmtRevisao.executeUpdate();
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

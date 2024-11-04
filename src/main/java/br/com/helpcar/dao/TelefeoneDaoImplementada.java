package br.com.helpcar.dao;

import br.com.helpcar.entities.Telefone;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TelefeoneDaoImplementada implements TelefoneDao{
    private final Connection connection;

    public TelefeoneDaoImplementada( Connection connection) {
        this.connection = connection;
    }


    @Override
    public void create(Telefone telefone) throws SQLException {
        String sql = "INSERT INTO t_hc_telefone (nr_ddi, nr_ddd, nr_telefone, tp_telefone, st_telefone) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, telefone.getDdi());
            pstmt.setString(2, telefone.getDdd());
            pstmt.setString(3, telefone.getNumero());
            pstmt.setString(4, telefone.getTipo());
            pstmt.setString(5, telefone.getStatus());
            pstmt.executeUpdate();

            String sqlTelefoneCliente = "INSERT INTO T_HC_TELEFONE_CLIENTE (ID_CLIENTE, ID_TELEFONE) VALUES ((SELECT MAX(id_cliente) AS ultimo_id FROM t_hc_cliente), (SELECT MAX(ID_TELEFONE) AS ultimo_idT FROM T_HC_TELEFONE))";
            try (PreparedStatement pstmtTelefoneCliente = connection.prepareStatement(sqlTelefoneCliente)) {
                pstmtTelefoneCliente.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }


    @Override
    public List<Telefone> read() throws SQLException {
        List<Telefone> telefones = new ArrayList<>();
        String sql = "SELECT * FROM t_hc_telefone";
        try (PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery(sql)){
            while (rs.next()) {
                Telefone telefone = new Telefone(
                        rs.getLong("id_telefone"),
                        rs.getString("nr_ddi"),
                        rs.getString("nr_ddd"),
                        rs.getString("nr_telefone"),
                        rs.getString("tp_telefone"),
                        rs.getString("st_telefone")
                );
                telefones.add(telefone);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return telefones;
    }

    @Override
    public void update(Telefone telefone) throws SQLException {
        String sql = "UPDATE T_HC_TELEFONE SET st_telefone = ? WHERE id_telefone = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, telefone.getStatus());
            pstmt.setLong(2,telefone.getId());
            pstmt.executeUpdate();
        }

    }

    @Override
    public void delete(Long id) throws SQLException {
        String sql = "DELETE T_HC_TELEFONE WHERE id_telefone = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        }
    }
}

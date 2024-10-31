package br.com.helpcar.dao;

import br.com.helpcar.DatabaseConfig;
import br.com.helpcar.entities.Veiculo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeiculoDaoImplementada implements VeiculoDao{
    private final DatabaseConfig dbc;

    public VeiculoDaoImplementada(DatabaseConfig dbc) {
        this.dbc = dbc;
    }

    @Override
    public void create(Veiculo veiculo) throws SQLException {
        String sql = "INSERT INTO T_HC_VEICULO( ID_VEICULO, ID_CLIENTE, NR_PLACA, NM_MODELO, NM_MARCA, NR_ANO_FABRICACAO ) VALUES (?, ?, ?, ?, ?, ?)";
        Connection connection = dbc.getConnection();
        PreparedStatement pstmt = connection.prepareStatement(sql);

        pstmt.setLong(1, veiculo.getId_veiculo());
        pstmt.setLong(2, veiculo.getId_cliente());
        pstmt.setString(3, veiculo.getPlaca());
        pstmt.setString(4,veiculo.getModelo());
        pstmt.setString(5,veiculo.getMarca());
        pstmt.setInt(6, veiculo.getAno());
        pstmt.executeUpdate();
    }

    @Override
    public List<Veiculo> read() throws SQLException {
        List<Veiculo> result = new ArrayList<>();

        //1- Executar o querry select *
        String sql = "SELECT * FROM T_HC_VEICULO";
        Connection connection = dbc.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        //2- mapear linhas para objetos
        while(rs.next()){
            Long id = rs.getLong("ID_VEICULO");
            Long id_cliente = rs.getLong("ID_CLIENTE");
            String placa = rs.getNString("NR_PLACA");
            String modelo = rs.getNString("NM_MODELO");
            String marca = rs.getNString("NM_MARCA");
            int ano = rs.getInt("NR_ANO_FABRICACAO");
            result.add(new Veiculo(id, id_cliente, placa, modelo,marca, ano));
        }
        return result;
    }

    @Override
    public void update(Veiculo veiculo) throws SQLException {
        String sql = "update T_HC_VEICULO set NM_MODELO=?, DE_MARCA=?, NR_ANO_FABRICACAO=? where iD=?";

        Connection connection = dbc.getConnection();
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(2, veiculo.getModelo());
        pstmt.setString(1, veiculo.getMarca());
        pstmt.setInt(3, veiculo.getAno());
        pstmt.setLong(3, veiculo.getId_veiculo());

        pstmt.executeUpdate();
    }

    @Override
    public void delete(Long id) throws SQLException {
        String sql = "delete T_HC_VEICULO where id = ?";

        Connection connection = dbc.getConnection();
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setLong(1, id);

        pstmt.executeUpdate();
    }


}

package br.com.helpcar.dao;

import br.com.helpcar.DatabaseConfig;
import br.com.helpcar.entities.Cliente;
import br.com.helpcar.entities.Veiculo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDaoImplementada implements ClienteDao{
    private final DatabaseConfig dbc;

    public ClienteDaoImplementada(DatabaseConfig dbc) {
        this.dbc = dbc;
    }

    @Override
    public void create(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO T_HC_CLIENTE ( ID_CLIENTE, NM_CLIENTE, NR_CPF, DS_SENHA, DS_EMAIL ) VALUES (?, ?, ?, ?, ?)";
        Connection connection = dbc.getConnection();
        PreparedStatement pstmt = connection.prepareStatement(sql);

        pstmt.setLong(1, cliente.getIdCliente());
        pstmt.setString(2, cliente.getNome());
        pstmt.setString(3, cliente.getCpf());
        pstmt.setString(4, cliente.getSenha());
        pstmt.setString(5, cliente.getEmail());
        pstmt.executeUpdate();
    }

    @Override
    public List<Cliente> read() throws SQLException {
        List<Cliente> result = new ArrayList<>();

        //1- Executar o querry select *
        String sql = "SELECT * FROM T_HC_CLIENTE";
        Connection connection = dbc.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        //2- mapear linhas para objetos
        while(rs.next()){
            Long id = rs.getLong("ID_CLIENTE");
            String nome = rs.getNString("NM_CLIENTE");
            String cpf = rs.getNString("NR_CPF");
            String senha = rs.getNString("DS_SENHA");
            String  email = rs.getString("DS_EMAIL");
            result.add(new Cliente(id, nome, cpf, senha, email));
        }
        return result;
    }

    @Override
    public void update(Cliente cliente) throws SQLException {
        String sql = "update T_HC_CLIENTE set NM_CLIENTE=?, DE_SENHA=? where iD=?";
        Connection connection = dbc.getConnection();
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, cliente.getNome());
        pstmt.setString(2, cliente.getSenha());
        pstmt.setLong(3, cliente.getIdCliente());

        pstmt.executeUpdate();
    }

    @Override
    public void delete(Long id) throws SQLException {
        String sql = "delete T_HC_CLIENTE where id = ?";

        Connection connection = dbc.getConnection();
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setLong(1, id);

        pstmt.executeUpdate();
    }


}

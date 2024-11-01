package br.com.helpcar.dao;

import br.com.helpcar.DatabaseConfig;
import br.com.helpcar.entities.*;

import java.sql.Connection;
import java.sql.SQLException;

public class MainTeste {
    public static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    public static final String USER = "rm558637";
    public static final String PASS = "010405";


    public static void main(String[] args) {
//
////        //
////
        DatabaseConfig db = new DatabaseConfig(URL, USER, PASS);
        try {
            Connection Connection = db.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

//        Endereco endereco1 = new Endereco( "05541030", "Rua dos pinheiros", "1155", "Pinheiros", "São Paulo" ,"SP");
//
//        EnderecoDao enderecoDao = new EnderecoDaoImplementada(db);
//        try {
//            enderecoDao.create(endereco1);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//        Oficina oficina1 = new Oficina(1L, "Oficina Central", "12345678000123", endereco1, "11987654321", 4.5);
//
//        // Instanciação do DAO de Oficina
//        OficinaDaoImplementada oficinaDao = new OficinaDaoImplementada(db);
//
//        // Tentativa de inserir a oficina no banco de dados
//        try {
//            oficinaDao.create(oficina1);
//            System.out.println("Oficina criada com sucesso!");
//        } catch (SQLException e) {
//            throw new RuntimeException("Erro ao criar oficina: " + e.getMessage(), e);
//        }

        ServicoRevisaoDaoImplementada revisaoDao = new ServicoRevisaoDaoImplementada(db);
        ServicoRevisao servicoRevisao = new ServicoRevisao("Revisão do carburador do veiculo", 120.0,"Carburador", "Interior, Exterior, Qualidade", 120);

        // Teste de ServicoRevisao
        try {
            revisaoDao.create(servicoRevisao);
            System.out.println("Serviço de Revisão inserido com ID: " + servicoRevisao.getId());
        } catch (SQLException e) {
            System.err.println("Erro ao inserir Serviço de Revisão: " + e.getMessage());
        }

//        // Teste de ServicoReparo
//        try {
//            ServicoReparoDaoImplementada reparoDao = new ServicoReparoDaoImplementada(db);
//            ServicoReparo servicoReparo = new ServicoReparo("Freio", "Frenagem falhando", 200.0, 60);
//            reparoDao.create(servicoReparo);
//            System.out.println("Serviço de Reparo inserido com ID: " + servicoReparo.getId());
//        } catch (SQLException e) {
//            System.err.println("Erro ao inserir Serviço de Reparo: " + e.getMessage());
//        }
//
//        // Teste de ServicoManutencao
//        try {
//            ServicoManutencaoDaoImplementada manutencaoDao = new ServicoManutencaoDaoImplementada(db);
//            ServicoManutencao servicoManutencao = new ServicoManutencao("Manutenção Geral", "Anual", 150.0, 30);
//            manutencaoDao.create(servicoManutencao);
//            System.out.println("Serviço de Manutenção inserido com ID: " + servicoManutencao.getId());
//        } catch (SQLException e) {
//            System.err.println("Erro ao inserir Serviço de Manutenção: " + e.getMessage());
//        }



    }
}

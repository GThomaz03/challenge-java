package br.com.helpcar;

import br.com.helpcar.dao.*;
import br.com.helpcar.entities.*;
import br.com.helpcar.service.ValidadorUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    public static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    public static final String USER = "rm558637";
    public static final String PASS = "010405";


    public static void main(String[] args) throws SQLException {

        // Criando instâncias de Cliente, Veiculo e Endereço
        Cliente cliente1 = new Cliente(1L, "58631478994", "Mauricio", "abacaxi123@gmail.com", "Abacaxi123");
//      Opções de cientes para registrar
//        Cliente cliente2 = new Cliente(2L, "58631478995", "Melinda", "abacaxi125@gmail.com", "Abacaxi123");
//        Cliente cliente3 = new Cliente(3L, "58631478973", "Laura", "abacaxi456@gmail.com", "Amora23");
//        Cliente cliente4 = new Cliente(4L, "58631478927", "Joaquim", "abacaxi789@gmail.com", "Amendoim2424");

        cliente1.adicionarVeiculo( 1L, "ABC1D25", "Civic", "Honda", 2020);
//      Opções de veiculos para registar (um cliente pode ter mais de um veiculo)
//        cliente1.adicionarVeiculo( 2L, "ABC1W25", "Camaro", "Chevrolette", 2023);
//        cliente2.adicionarVeiculo( 3L, "CBA1D23", "Mustang", "Ford", 2024);
//        cliente3.adicionarVeiculo( 4L, "ABC6F35", "Ferrari Roma", "Ferrari", 2022);
//        cliente3.adicionarVeiculo( 5L, "JBC1D29", "P1", "Mclaren", 2024);
//        cliente4.adicionarVeiculo( 6L, "LBC5D20", "Porsche panamera", "Porsche", 2023);

//      criação de um endereço que será atribuido à um cliente
        Endereco endereco1 = new Endereco(1L, "05541030", "Rua dos pinheiros", "1155", "Pinheiros", "São Paulo" ,"SP");
//      Opçõpes de endereços para criar
//        Endereco endereco2 = new Endereco(2L, 01222000, "Avenida Paulista", "1578", "Bela Vista", "São Paulo", "SP");
//        Endereco endereco3 = new Endereco(3L, 04534011, "Rua Joaquim Floriano", "466", "Itaim Bibi", "São Paulo", "SP");
//        Endereco endereco4 = new Endereco(4L, 04004000, "Rua Vergueiro", "1000", "Paraíso", "São Paulo", "SP");
//        Endereco endereco5 = new Endereco(5L, 05010040, "Rua Turiassu", "1500", "Perdizes", "São Paulo", "SP");

//      Atribuindo um endereço à um cliente
        cliente1.setEndereco(endereco1);

//        adicionando um telefone à um cliente para a validação de números de telefone posteriormente
        cliente1.setTelefone("11963320175");

        //Registrando clientes no banco de dados
        DatabaseConfig db = new DatabaseConfig(URL, USER, PASS);
        Connection connection = db.getConnection();

        ClienteDao clienteDao =  new ClienteDaoImplementada(db);
        //Escolher o cliente a ser inserido no banco de dados
        clienteDao.create(cliente1);

        EnderecoDao enderecoDao = new EnderecoDaoImplementada(db);
        enderecoDao.create(endereco1);

        //visualizando todos os clientes cadatrados
        List<Cliente> read = clienteDao.read();
        for(Cliente C : read){
            System.out.println(C.toString());
        }

        //Registrando um veiculo de um cliente no banco de dados
        VeiculoDao veiculoDao =  new VeiculoDaoImplementada(db);
        veiculoDao.create(cliente1.getVeiculo());

        //visualizando todos os veiculos cadatrados
        List<Veiculo> carros = veiculoDao.read();
        for(Veiculo veiculo : carros){
            System.out.println(veiculo.toString());
        }


        // Métodos de validação de dados
        boolean cpfValido = ValidadorUtil.isCPFValid(cliente1.getCpf());
        boolean telefoneValido = ValidadorUtil.isTelefoneValid(cliente1.getTelefone());
        boolean emailValido = ValidadorUtil.isEmailValid(cliente1.getEmail());
        boolean placaValida = ValidadorUtil.isPlacaValid(cliente1.getVeiculo().getPlaca());

        System.out.println("CPF válido: " + cpfValido);
        System.out.println("Telefone válido: " + telefoneValido);
        System.out.println("Email válido: " + emailValido);
        System.out.println("Placa válida: " + placaValida);


        // Criando instância de ChatBot
        ChatBot chatBot = new ChatBot();
        chatBot.setCliente(cliente1);
        chatBot.setVeiculo(cliente1.getVeiculo());
        chatBot.setDiagnostico("Diagnóstico inicial: sem problemas");

        System.out.println("\nChatBot:");
        System.out.println("Nome: " + chatBot.getNome());
        System.out.println("Diagnóstico: " + chatBot.getDiagnostico());

        // Criando instâncias de serviços
        Servicos servicoRevisao = new ServicoRevisao("Óleo, Freios", "Tudo Ok");
        Servicos servicoReparo = new ServicoReparo("Motor", "Falha na ignição");
        Servicos servicoManutencao = new ServicoManutencao("Troca de óleo", "6 meses");

        // Exibindo informações dos serviços
        System.out.println("\nServiço de Revisão:");
        System.out.println("Tipo: " + servicoRevisao.getTipo());
        System.out.println("Itens Revisados: " + ((ServicoRevisao) servicoRevisao).getItensRevisados());
        System.out.println("CheckList: " + ((ServicoRevisao) servicoRevisao).getCheckList());

        System.out.println("\nServiço de Reparo:");
        System.out.println("Tipo: " + servicoReparo.getTipo());
        System.out.println("Componente Reparado: " + ((ServicoReparo) servicoReparo).getComponenteReparado());
        System.out.println("Descrição do Problema: " + ((ServicoReparo) servicoReparo).getDescricaoproblema());

        System.out.println("\nServiço de Manutenção:");
        System.out.println("Tipo: " + servicoManutencao.getTipo());
        System.out.println("Tipo de Manutenção: " + ((ServicoManutencao) servicoManutencao).getTipoManutencao());
        System.out.println("Periodicidade: " + ((ServicoManutencao) servicoManutencao).getPeriodicidade());

        // Criando instância de Fornecedor
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setSitesConfiaveis(Arrays.asList("www.pecascar.com", "www.autoreparo.com"));

        System.out.println("\nFornecedor:");
        System.out.println("Sites Confiáveis: " + fornecedor.getSitesConfiaveis());

        // Criando instância de Oficina
        Oficina oficina = new Oficina(35L, "Auto Mecânica", "12.345.678/0001-99", new Endereco(2L, "05541030", "Rua dos pinheiros", "123", "Pinheiros", "São Paulo", "SP" ), "(11) 3344-5566", 4.5);
        oficina.setDiagnostico(chatBot);
        oficina.setServicos(servicoReparo);
        oficina.setPeca(fornecedor);

        System.out.println("\nOficina:");
        System.out.println("Nome: " + oficina.getNome());
        System.out.println("CNPJ: " + oficina.getCnpj());
        System.out.println("Endereço: " + oficina.getEndereco().getEnderecoCompleto());
        System.out.println("Telefone: " + oficina.getTelefone());
        System.out.println("Avaliação: " + oficina.getAvaliacao());
        System.out.println("Diagnóstico: " + oficina.getDiagnostico().getDiagnostico());
        System.out.println("Serviço: " + oficina.getServicos().getTipo());
        System.out.println("Fornecedor: " + oficina.getPeca().getSitesConfiaveis());
    }
}

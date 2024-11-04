package br.com.helpcar.controller;

import br.com.helpcar.config.DatabaseConfig;
import br.com.helpcar.dao.*;
import br.com.helpcar.dtos.CadastroDto;
import br.com.helpcar.entities.Cliente;
import br.com.helpcar.entities.Endereco;
import br.com.helpcar.entities.Telefone;
import br.com.helpcar.entities.Veiculo;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;



@Path("/cadastro")
public class ClienteController {
    DatabaseConfig db = new DatabaseConfig();
    Connection connection = db.getConnection();
    ClienteDao clienteDao = new ClienteDaoImplementada(connection);
    VeiculoDao veiculoDao = new VeiculoDaoImplementada(connection);
    EnderecoDao enderecoDao = new EnderecoDaoImplementada(connection);
    TelefoneDao telefeoneDao = new TelefeoneDaoImplementada(connection);


    // ver todos os cliente cadastrados
    @GET
    @Path("/listar-todos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodos() {
        try {
            List<Cliente> clientes = clienteDao.findAll();
            return Response.ok(clientes).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    // Recebe todos os dados juntos e cria todas as classes necessária separadamente
    @POST
    @Path("/dados")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrarCliente(CadastroDto cadastroDto) throws SQLException {
        Cliente cliente = new Cliente();

        // Dados básicos do cliente
        cliente.setNome(cadastroDto.getNome());
        cliente.setCpf(cadastroDto.getCpf());
        cliente.setTelefone(cadastroDto.getTelefone());
        cliente.setEmail(cadastroDto.getEmail());
        cliente.setSenha(cadastroDto.getSenha());

        // Dados do veículo
        Veiculo veiculo = new Veiculo();
        veiculo.setMarca(cadastroDto.getMarca());
        veiculo.setModelo(cadastroDto.getModelo());
        veiculo.setAno(cadastroDto.getAno());
        veiculo.setPlaca(cadastroDto.getPlaca());
        cliente.setVeiculo(veiculo);

        // Dados do endereço
        Endereco endereco = new Endereco();
        endereco.setLogradouro(cadastroDto.getLogradouro());
        endereco.setNumero(cadastroDto.getNumero());
        endereco.setComplemento(cadastroDto.getComplemento());
        endereco.setBairro(cadastroDto.getBairro());
        endereco.setCidade(cadastroDto.getCidade());
        endereco.setEstado(cadastroDto.getEstado());
        endereco.setCep(cadastroDto.getCep());
        cliente.setEndereco(endereco);

        // Dados do telefone
        Telefone telefone = new Telefone(cadastroDto.getTelefone());

        // Lógica para salvar o cliente no banco
        clienteDao.create(cliente);
        veiculoDao.create(veiculo);
        enderecoDao.create(endereco);
        telefeoneDao.create(telefone);


        return Response.status(Response.Status.CREATED)
                .entity(cliente)
                .build();
    }
}


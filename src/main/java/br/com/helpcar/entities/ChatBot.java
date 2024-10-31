package br.com.helpcar.entities;

public class ChatBot {
    private Cliente cliente;
    private Veiculo veiculo;
    private Long idChatBot;
    private String nome;
    private String versao;
    private String diagnostico;

    public ChatBot() {
        this.nome = "HelpCar";
        this.idChatBot = 1L;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }


    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getNome() {
        return nome;
    }
}

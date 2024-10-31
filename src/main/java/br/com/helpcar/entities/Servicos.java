package br.com.helpcar.entities;

public abstract class Servicos {
    private ChatBot diagnostico;
    private Integer id;
    private String nome,descricao;
    private Double preco;
    private Integer tempoEstimado;

    public abstract String getTipo();

    public ChatBot getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(ChatBot diagnostico) {
        this.diagnostico = diagnostico;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getTempoEstimado() {
        return tempoEstimado;
    }

    public void setTempoEstimado(Integer tempoEstimado) {
        this.tempoEstimado = tempoEstimado;
    }
}

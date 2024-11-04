package br.com.helpcar.entities;

public abstract class Servicos {
    private ChatBot diagnostico;
    private Long id;
    private String nome,descricao;
    private Double preco;
    private Integer tempoEstimado;
    private Long idSolucao;

    public Servicos(String descricao, Double preco, Integer tempoEstimado, Long idSolucao) {
        this.descricao = descricao;
        this.preco = preco;
        this.tempoEstimado = tempoEstimado;
        this.idSolucao = idSolucao;
    }

    public Servicos() {

    }

    public abstract String getTipo();

    public ChatBot getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(ChatBot diagnostico) {
        this.diagnostico = diagnostico;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPreco() {
        return preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public Long getIdSolucao() {
        return idSolucao;
    }
}

package br.com.helpcar.entities;

public class Oficina {
    private Long id;
    private ChatBot diagnostico;
    private Servicos servicos;
    private Fornecedor peca;
    private String nome;
    private String cnpj;
    private Endereco endereco;
    private String telefone;
    private Double avaliacao;

    public Oficina(Long id, String nome, String cnpj, Endereco endereco, String telefone, Double avaliacao) {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.telefone = telefone;
        this.avaliacao = avaliacao;
    }

    public Oficina(Long id, String nome, String cnpj, Endereco endereco, Double avaliacao) {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.avaliacao = avaliacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ChatBot getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(ChatBot diagnostico) {
        this.diagnostico = diagnostico;
    }

    public Servicos getServicos() {
        return servicos;
    }

    public void setServicos(Servicos servicos) {
        this.servicos = servicos;
    }

    public Fornecedor getPeca() {
        return peca;
    }

    public void setPeca(Fornecedor peca) {
        this.peca = peca;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }
}

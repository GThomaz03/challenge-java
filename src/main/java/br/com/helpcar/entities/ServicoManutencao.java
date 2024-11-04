package br.com.helpcar.entities;

public class ServicoManutencao extends Servicos{
    private String tipoManutencao, periodicidade, descricao;
    private Double preco;
    private int tempo;


    public ServicoManutencao(String descricao, Double preco, String tipoManutencao, String periodicidade, int tempo, Long idSolucao) {
        super(descricao, preco, tempo, idSolucao);
        this.tipoManutencao = tipoManutencao;
        this.periodicidade = periodicidade;
        this.descricao = descricao;
    }

    @Override
    public String getTipo() {
        return "Manutenção";
    }

    public String getTipoManutencao() {
        return tipoManutencao;
    }

    public void setTipoManutencao(String tipoManutencao) {
        this.tipoManutencao = tipoManutencao;
    }

    public String getPeriodicidade() {
        return periodicidade;
    }

    public void setPeriodicidade(String periodicidade) {
        this.periodicidade = periodicidade;
    }

    @Override
    public String getDescricao() {
        return descricao;
    }

    @Override
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


    @Override
    public Double getPreco() {
        return preco;
    }

    @Override
    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    @Override
    public String toString() {
        return "ServicoReparo{" +
                "Tipo de manutenção ='" + tipoManutencao + '\'' +
                ", Periodicidade='" + periodicidade + '\'' +
                ", descricao='" + descricao + '\'' +
                ", preco=" + preco +
                ", tempo=" + tempo +
                ", tipo='" + getTipo() + '\'' +
                ", idSolucao=" + getIdSolucao() +
                '}' + '\n';
    }
}

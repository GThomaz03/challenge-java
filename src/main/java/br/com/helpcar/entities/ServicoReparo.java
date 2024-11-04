package br.com.helpcar.entities;

public class ServicoReparo extends Servicos{
    private String componenteReparado, descricao, descricaoSolucao;
    private Double preco;
    private int tempo;

    public ServicoReparo(String descricaoSolucao, Double preco, String componenteReparado, int tempo, Long idSolucao, String descricao ) {
        super(descricaoSolucao, preco, tempo, idSolucao);
        this.componenteReparado = componenteReparado;
        this.descricaoSolucao = descricaoSolucao;
        this.descricao = descricao;
        this.preco = preco;
        this.tempo = tempo;
    }


    public String getComponenteReparado() {
        return componenteReparado;
    }

    public void setComponenteReparado(String componenteReparado) {
        this.componenteReparado = componenteReparado;
    }

    @Override
    public String getDescricao() {
        return descricao;
    }

    @Override
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricaoSolucao() {
        return descricaoSolucao;
    }

    public void setDescricaoSolucao(String descricaoSolucao) {
        this.descricaoSolucao = descricaoSolucao;
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
    public String getTipo() {
        return "Reparo";
    }

    @Override
    public String toString() {
        return "ServicoReparo{" +
                "componenteReparado='" + componenteReparado + '\'' +
                ", descricao='" + descricao + '\'' +
                ", preco=" + preco +
                ", tempo=" + tempo +
                ", tipo='" + getTipo() + '\'' +
                ", idSolucao=" + getIdSolucao() +
                '}' + '\n';
    }
}

package br.com.helpcar.entities;

public class ServicoRevisao extends Servicos{
    private String itensRevisados, checkList, descricao;
    private Double preco;
    private int tempo;


    public ServicoRevisao(String descricao, Double preco, String itensRevisados, String checkList, int tempo, Long idSolucao) {
        super(descricao, preco, tempo, idSolucao);
        this.itensRevisados = itensRevisados;
        this.checkList = checkList;
        this.descricao = descricao;
        this.preco = preco;
        this.tempo = tempo;
    }

    @Override
    public String getTipo() {
        return "Revisão";
    }


    public String getItensRevisados() {
        return itensRevisados;
    }

    public void setItensRevisados(String itensRevisados) {
        this.itensRevisados = itensRevisados;
    }

    public String getCheckList() {
        return checkList;
    }

    public void setCheckList(String checkList) {
        this.checkList = checkList;
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
        return "ServicoRevisao{" +
                "itensRevisados='" + itensRevisados + '\'' +
                ", checkList='" + checkList + '\'' +
                ", descricao='" + descricao + '\'' +
                ", preco=" + preco +
                ", tempo=" + tempo +
                ", tipo='" + getTipo() + '\'' +
                ", idSolucao=" + getIdSolucao() +
                '}' + '\n';
    }

}

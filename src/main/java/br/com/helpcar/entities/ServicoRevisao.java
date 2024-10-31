package br.com.helpcar.entities;

public class ServicoRevisao extends Servicos{
    private String itensRevisados, checkList;

    public ServicoRevisao(String itensRevisados, String checkList) {
        this.itensRevisados = itensRevisados;
        this.checkList = checkList;
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
}

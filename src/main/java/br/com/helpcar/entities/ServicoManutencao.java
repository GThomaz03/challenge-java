package br.com.helpcar.entities;

public class ServicoManutencao extends Servicos{
    private String tipoManutencao, periodicidade;

    public ServicoManutencao(String tipoManutencao, String periodicidade) {
        this.tipoManutencao = tipoManutencao;
        this.periodicidade = periodicidade;
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
}

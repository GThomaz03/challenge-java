package br.com.helpcar.entities;

public class ServicoReparo extends Servicos{
    private String componenteReparado, descricaoproblema;

    public ServicoReparo(String componenteReparado, String descricaoproblema) {
        this.componenteReparado = componenteReparado;
        this.descricaoproblema = descricaoproblema;
    }
    @Override
    public String getTipo() {
        return "Reparo";
    }

    public String getComponenteReparado() {
        return componenteReparado;
    }

    public void setComponenteReparado(String componenteReparado) {
        this.componenteReparado = componenteReparado;
    }

    public String getDescricaoproblema() {
        return descricaoproblema;
    }

    public void setDescricaoproblema(String descricaoproblema) {
        this.descricaoproblema = descricaoproblema;
    }
}

package br.com.helpcar.entities;

import java.util.List;

public class Fornecedor {
    private ChatBot diagnostico;
    private List<String> sitesConfiaveis;

    public Fornecedor(List<String> sitesConfiaveis) {
        this.sitesConfiaveis = sitesConfiaveis;
    }

    public ChatBot getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(ChatBot diagnostico) {
        this.diagnostico = diagnostico;
    }

    public List<String> getSitesConfiaveis() {
        return sitesConfiaveis;
    }

    public void setSitesConfiaveis(List<String> sitesConfiaveis) {
        this.sitesConfiaveis = sitesConfiaveis;
    }

}


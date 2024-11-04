package br.com.helpcar.entities;

public class Telefone {
    private Long id;
    private String ddi;
    private String ddd;
    private String numero;
    private String tipo;
    private String Status;

    public Telefone(Long id, String ddi, String ddd, String numero, String tipo, String status) {
        this.id = id;
        this.ddi = ddi;
        this.ddd = ddd;
        this.numero = numero;
        this.tipo = tipo;
        Status = status;
    }


    public Telefone(String telefone){
        this.ddi = telefone.substring(0,3);
        this.ddd = telefone.substring(3, 5);
        this.numero = telefone.substring(5);
        this.tipo = "Celular";
        this.Status = "Ativo";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDdi() {
        return ddi;
    }

    public void setDdi(String ddi) {
        this.ddi = ddi;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    @Override
    public String toString() {
        return ddi + ddd + numero;
    }
}

package br.com.helpcar.entities;

public class Veiculo {
    private Long idVeiculo, idCliente;
    private String placa, modelo, marca;
    private int ano;

    public Veiculo(Long id, Long id_cliente, String placa, String modelo, String marca, int ano) {
        this.idVeiculo = id;
        this.idCliente = id_cliente;
        this.placa = placa;
        this.modelo = modelo;
        this.marca = marca;
        this.ano = ano;
    }

    public Long getId_veiculo() {
        return idVeiculo;
    }

    public void setId_veiculo(Long id_veiculo) {
        this.idVeiculo = id_veiculo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public Long getId_cliente() {
        return idCliente;
    }

    public void setIdcliente(Long idcliente) {
        this.idCliente = idCliente;
    }

    @Override
    public String toString() {
        return "Veiculo{" +
                "idVeiculo=" + idVeiculo +
                ", idCliente=" + idCliente + '\'' +
                ", placa='" + placa + '\'' +
                ", modelo='" + modelo + '\'' +
                ", marca='" + marca + '\'' +
                ", ano=" + ano +
                '}';
    }
}

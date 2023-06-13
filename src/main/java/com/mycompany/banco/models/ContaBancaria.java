package com.mycompany.banco.models;

public class ContaBancaria {
    //Variáveis
    private int numConta;
    private String nmTitular;
    private double vlSaldo;
    private double vlDepositado;                                                                                                                                                                                                                                                                                                                                                                                                                                        
    private double vlSacado;
    
    //Construtores
    public ContaBancaria(){
    }
    public ContaBancaria(int numConta, String nmTitular,  double vlSaldo,
            double vlDepositado, double vlSacado){
        this.numConta = numConta;
        this.nmTitular = nmTitular;
        this.vlSaldo = vlSaldo;
        this.vlDepositado = vlDepositado;
        this.vlSacado = vlSacado;
    }
    
    //Getter e Setter
    public void setNumConta(int numConta){
        this.numConta = numConta;
    }
    public int getNumConta(){
        return numConta;
    }
    public void setNmTitular(String nmTitular){
        this.nmTitular = nmTitular;
    }
    public String getNmTitular(){
        return nmTitular;
    }
    public void setVlSaldo(double vlSaldo){
        this.vlSaldo = vlSaldo;
    }
    public double getVlSaldo(){
        return vlSaldo;
    }
    public void setVlDepositado(double vlDepositado){
        this.vlDepositado = vlDepositado;
    }
    public double getVlDepositado(){
        return vlDepositado;
    }
    public void setVlSacado(double vlSacado){
        this.vlSacado = vlSacado;
    }
    public double getVlSacado(){
        return vlSacado;
    }
    
    public void depositar(double vlDep){
        vlSaldo += vlDep;
        vlDepositado += vlDep;
    }
    public void sacar(double vlSac){
        vlSaldo -= vlSac;
        vlSacado += vlSac;
    }

    @Override
    public String toString() {
        return "Dados da(s) Conta(s) Bancaria(s): \n"
                + "Número da Conta: " + numConta + "\n"
                + "Nome Titular: " + nmTitular + "\n"
                + "Saldo Disponível: " + vlSaldo + "\n"
                + "Valor Total Depositado: " + vlDepositado + "\n"
                + "Valor Total Sacado: " + vlSacado + "\n";
    }
    
    
}

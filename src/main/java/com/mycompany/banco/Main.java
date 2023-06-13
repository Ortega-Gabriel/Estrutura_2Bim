package com.mycompany.banco;

import com.mycompany.banco.models.ContaBancaria;
import javax.swing.JOptionPane;

public class Main {
    //Variáveis Globais
    private static int posConta;
    private static int posContaNegativa;
    private static ContaBancaria[] contasBancarias;
    private static ContaBancaria[] contasNegativas;

    public static void main(String[] args) { 
        //Variáveis Dentro da Main
        contasBancarias = new ContaBancaria[1000];
        contasNegativas = new ContaBancaria[1000];
        posConta = 0;
        posContaNegativa = 0;
        double saldoTotal = 0;
        int selOper = 1;

        //Menu Interativo
        do {
            selOper = Integer.parseInt(JOptionPane.showInputDialog(null, 
                    "Informe qual Operação Deseja Realizar: \n"
                    + "1 - Cadastrar uma Conta Nova \n"
                    + "2 - Exibir Contas Cadastradas \n"
                    + "3 - Realizar Depósito \n"
                    + "4 - Realizar Saque \n"
                    + "5 - Calcular Saldo Total do Banco \n"
                    + "6 - Verificar Contas Negativas \n"
                    + "0 - Cancelar Operação"));

            switch (selOper) {
                case 0:
                    JOptionPane.showMessageDialog(null, "Operação Cancelada!");
                    break;
                case 1:
                    cadastrarConta();
                    break;
                case 2:
                    String msg = imprimeContasCadastradas();
                    JOptionPane.showMessageDialog(null, msg);
                    break;
                case 3:
                    depositarValor();
                    break;
                case 4:
                    sacarValor();
                    break;
                case 5:
                    calcSaldoContas(saldoTotal);
                    imprimeSaldoConta(saldoTotal);
                    break;
                case 6:
                    calcSaldoContas(saldoTotal);
                    imprimeContasNegativas();
                    break;
            }
        } while (selOper != 0);
    }
    
    //Métodos de Cadastrar, Depositar e Sacar de Dados
    public static void cadastrarConta() {
        //Solicitando Dados para o Usuário Criar a Conta
        int numConta = Integer.parseInt(JOptionPane.showInputDialog(null,
                "Informe o número da Conta: "));
        String nmTitular = JOptionPane.showInputDialog(null, 
                "Informe o nome do Titular da Conta: ");
        double vlSaldo = Double.parseDouble(JOptionPane.showInputDialog(null,
                "Informe o valor Inicial da Conta:"));
        
        //Criando a Conta, Adicionando os Valores e Adicionando ao Vetor
        ContaBancaria contaNova = new ContaBancaria();
        contaNova.setNumConta(numConta);
        contaNova.setNmTitular(nmTitular);
        contaNova.setVlSaldo(vlSaldo);
        contasBancarias[posConta] = contaNova;
        posConta++;
        
        //Confirmação de Conta Cadastrada
        JOptionPane.showMessageDialog(null, "Conta Cadastrada!");
    }
    
    public static void depositarValor() {
        //Solicitando a Forma que o Usuário deseja Consultar
        int numConta = 0;
        int respDep = Integer.parseInt(JOptionPane.showInputDialog(null,
                "Deseja Consultar a Conta pelo Número da Conta ou Nome do "
                        + "Titular? \n"
                + "1 - Número da Conta \n"
                + "2 - Titular da Conta"));
        
        //Solicitando o dado para Pesquisa da Conta e Valor para Deposito
        switch (respDep) {
            case 1:
                numConta = Integer.parseInt(JOptionPane.showInputDialog(null,
                        "Informe o Número da Conta: "));
                numConta = pesquisaLinear(numConta);
                
                if (numConta == -1) {
                    JOptionPane.showMessageDialog(null, 
                            "Conta Não Encontrada!");
                } else {
                    double vlDep = Double.parseDouble(
                            JOptionPane.showInputDialog(null,
                            "Informe o Valor do Depósito: "));
                    contasBancarias[numConta].depositar(vlDep);
                }
                break;
                
            case 2:
                String nmTitular = JOptionPane.showInputDialog(null,
                        "Informe o Nome do Titular da Conta: ");
                numConta = pesquisaLinearString(nmTitular);
                
                if (numConta == -1) {
                    JOptionPane.showMessageDialog(null, "Conta Não Encontrada");
                } else {
                    double vlDep = Double.parseDouble(
                            JOptionPane.showInputDialog(null,
                            "Informe o Valor do Depósito: "));
                    contasBancarias[numConta].depositar(vlDep);
                }
                break;
        }
    }
    
    public static void sacarValor() {
        //Solicitando a Forma que o Usuário deseja Consultar
        int respDep = Integer.parseInt(JOptionPane.showInputDialog(null,
                "Deseja Consultar a Conta pelo Número da Conta ou "
                        + "Nome do Titular? \n"
                + "1 - Número da Conta \n"
                + "2 - Titular da Conta"));
        
        //Solicitando o dado para Pesquisa da Conta e Valor para Sacar
        switch (respDep) {
            case 1:
                int numConta = Integer.parseInt(
                        JOptionPane.showInputDialog(null,
                        "Informe o Número da Conta: "));
                
                for (int i = 0; i < posConta; i++) {
                    if (contasBancarias[i].getNumConta() == numConta) {
                        double vlSac = Double.parseDouble(
                                JOptionPane.showInputDialog(null, 
                                "Informe o Valor do Saque: "));
                        //Validando Se possuí Saldo
                        if (vlSac > contasBancarias[i].getVlSaldo()) {
                            JOptionPane.showMessageDialog(null, 
                                    "Saldo Insuficiente");
                        } else {
                            contasBancarias[i].sacar(vlSac);
                            JOptionPane.showMessageDialog(null, 
                                    "Saque Realizado");
                        }
                        break;
                    } else {
                        JOptionPane.showMessageDialog(null, 
                                "Conta não Encontrada");
                    }
                }
                break;
                
            case 2:
                String nmTitular = JOptionPane.showInputDialog(null,
                        "Informe o Nome do Titular da Conta: ");
                
                for (int i = 0; i < posConta; i++) {
                    if (contasBancarias[i].getNmTitular().equals(nmTitular)) {
                        double vlSac = Double.parseDouble(
                                JOptionPane.showInputDialog(null, 
                                "Informe o Valor do Saque: "));
                        contasBancarias[i].sacar(vlSac);
                        JOptionPane.showMessageDialog(null, "Valor Sacado!");
                        break; 
                    }
                }
                break;
        }
    }
    
    //Métodos para Calcular Saldos
    public static void calcSaldoContas(double saldoTotal) {
        //Método que Puxa o Método Recursivo de Cálculo de Saldos
        saldoTotal = calcSaldoContasRecursivo(0, posConta - 1);
    }
    
    public static double calcSaldoContasRecursivo(int comeco, int fim) {
        
        if (comeco == fim) {
            //Verificando se é Negativa e Adicionando ao Vetor caso seja
            if (contasBancarias[comeco].getVlSaldo() < 0) {
                contasNegativas[posContaNegativa] = contasBancarias[comeco];
                posContaNegativa++;
            }
            return contasBancarias[comeco].getVlSaldo();
        } else {
            if (contasBancarias[comeco].getVlSaldo() < 0) {
                contasNegativas[posContaNegativa] = contasBancarias[comeco];
                posContaNegativa++;
            }
            
            int meio = (comeco + fim) / 2;
            double saldoPri = calcSaldoContasRecursivo(comeco, meio);
            double saldoSec = calcSaldoContasRecursivo(meio + 1, fim);
            
            return saldoPri + saldoSec;
        }
    }
    
    //Métodos de Impressão
    public static void imprimeContasNegativas() {
        String msg = "Contas Negativas \n";
        
        //Adicionando as Contas Negativas na Mensagem para Impressão
        for (int i = 0; i < posContaNegativa; i++) {
            msg += "Número da Conta: " + 
                    contasNegativas[i].getNumConta() + "\n" +
                   "Titular da Conta: " + 
                    contasNegativas[i].getNmTitular() + "\n" +
                   "Saldo da Conta: R$" + 
                    contasNegativas[i].getVlSaldo() + "\n";
        }
        JOptionPane.showMessageDialog(null, msg);
    }
    
    public static String imprimeContasCadastradas() {
        ordBolha();
        String msg = "Contas Cadastradas \n";
        
        //Adicionando as Contas Cadastradas na Mensagem para Impressão
        for (int i = 0; i < posConta; i++) {
            if (contasBancarias[i] != null) {
                msg += "Número da Conta: " + 
                        contasBancarias[i].getNumConta() + "\n" +
                       "Titular da Conta: " + 
                        contasBancarias[i].getNmTitular() + "\n" +
                       "Saldo da Conta: R$" + 
                        contasBancarias[i].getVlSaldo() + "\n";
            }
        }
        
        return msg;
    }
    
    public static void imprimeSaldoConta(double saldoTotal) {
        JOptionPane.showMessageDialog(null, 
                "Saldo de Todas as Contas Somadas: "+ saldoTotal);
    }  
    
    //Métodos de Pesquisa
    public static int pesquisaLinear(int numPesq) {
        ordBolha();
        
        for (int i = 0; i < posConta; i++) {
            if (numPesq == contasBancarias[i].getNumConta()) {
                return i;  
            }
        }
        return -1;
    }
    
    public static int pesquisaLinearString(String nmTitularPesq) {
        ordBolha();
        
        for (int i = 0; i < posConta; i++) {
            if (contasBancarias[i].getNmTitular().equals(nmTitularPesq)) {
                return i;
            }
        }
        return -1;
    }
    
    public static int pesquisaBinaria(int numPesq) {
        ordBolha();
        
        int esq, meio, dir;
        esq = 0;
        dir = posConta - 1;
        
        while (esq <= dir) {
            meio = (esq + dir) / 2;
            
            if (numPesq == contasBancarias[meio].getNumConta()) {
                return meio;
            }
            
            if (numPesq >= contasBancarias[meio].getNumConta()) {
                esq = meio + 1;
            } else {
                dir = meio - 1;
            }
        }
        return -1;
    }
    
    //Métodos de Ordenação
    public static void ordBolha() {
        boolean status = true;
        
        while (status) {
            status = false;
            for (int i = 0; i < posConta - 1; i++) {
                if (contasBancarias[i].getNumConta() > 
                        contasBancarias[i+1].getNumConta()) {
                    ContaBancaria aux = contasBancarias[i];
                    contasBancarias[i] = contasBancarias[i+1];
                    contasBancarias[i+1] = aux;
                    status = true;
                }
            }
        }
    }
}

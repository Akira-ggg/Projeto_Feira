package saida;


import modelos.FazerPedidos;
import modelos.Pedido;
import java.util.Scanner;

import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception {
       Scanner sc = new Scanner(System.in);

       try{
           FazerPedidos pedidos = new FazerPedidos();
           Pedido f = null;
           String continuar = "sim";
           while (continuar.equalsIgnoreCase("sim")){
               System.out.println("Bem vindo ao Sistema de cadastro de pedidos ");
               System.out.println("Digite 1 para adicionar pedido");
               System.out.println("Digite 2 para listar pedidos");
               System.out.println("Digite 3 para apagar pedido");
               System.out.println("Qual a sua escolha: ");
               int escolha = sc.nextInt();
               sc.nextLine();
               if(escolha == 1){
                   System.out.println("Digite o nome do pedido: ");
                   String nome = sc.nextLine();
                   System.out.println("Digite a quantidade do pedido: ");
                   int quantidade = sc.nextInt();
                   System.out.println("Digite o valor do produto: ");
                   double valor = sc.nextDouble();
                   f = new Pedido(nome,quantidade,valor);
                   pedidos.adicionarProduto(f);
               } else if (escolha == 2) {
                   ArrayList<Pedido> lista = pedidos.listarProdutos();
                   for(Pedido p : lista){
                       System.out.println(p);
                   }
                   double somaTotal = pedidos.somaTotal(f);
                   System.out.println("Soma total do pedido: " +  somaTotal);


               } else if (escolha == 3) {
                   System.out.println("Digite o número do ID: ");
                   int id = sc.nextInt();
                   pedidos.removerProduto(id);


               }
               System.out.println("Quer continuar: sim/nao: ");
               continuar = sc.next();



           }

       }catch(Exception e){
           System.out.println(e.getMessage());
       }


    }
}
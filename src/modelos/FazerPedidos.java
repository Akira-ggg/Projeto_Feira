package modelos;
import interfaces.IPedidos;

import java.io.*;
import java.util.ArrayList;


public class FazerPedidos implements IPedidos {
    private String nomeDoArquivo = null;
    public FazerPedidos(){
        nomeDoArquivo = "./src/bancoDeDados/tiposDePedidos.txt";
    }
    public int geradorDeID()throws Exception{
        ArrayList<Pedido> pedido = this.listarProdutos();
        int auxID = 0;
        for(Pedido p : pedido){
            if(p.getId()> auxID){
                auxID = p.getId();

            }
        }
        return auxID+=1;

    }





    @Override
    public double somaTotal(Pedido tipoDePedido) throws Exception {

        double soma = 0;
        try{
            FileReader fr = new FileReader(nomeDoArquivo);
            BufferedReader bw = new BufferedReader(fr);
            String linha= "";
            while((linha = bw.readLine()) != null){
                String[] linhas = linha.split(";");
                int qunatidade = Integer.parseInt(linhas[2]);
                double preco =  Double.parseDouble(linhas[3]);
                double calcular = qunatidade * preco;
                soma += calcular;
            }



        }catch(Exception e){
            throw new Exception("Erro ao somar o total de produtos");
        }
        return soma;


    }

    @Override
    public void adicionarProduto(Pedido tipoDePedido) throws Exception {
        ArrayList<Pedido> listaProdutos = this.listarProdutos();
        int novoID = geradorDeID();
        tipoDePedido.setId(novoID);

        try{

            for(Pedido pedido : listaProdutos){
                if(pedido.getId() == tipoDePedido.getId()){
                    throw new Exception("Intem já existente cadastre outro");
                }
            }
            FileWriter fw = new FileWriter(nomeDoArquivo, true);
            BufferedWriter bw = new BufferedWriter(fw);
            String str = tipoDePedido.getId() + ";";
            str += tipoDePedido.getNomePedido() + ";" +
                    tipoDePedido.getQunatidde() + ";" +
                    tipoDePedido.getPreco() + ";" + "\n";
            bw.write(str);
            bw.close();
            fw.close();

        }catch (Exception e){
            throw new Exception("Erro ao adicionar produto");
        }


    }

    @Override
    public void removerProduto(int id)throws Exception {
        ArrayList<Pedido> listaProdutos = this.listarProdutos();
        try{
            FileWriter fw = new FileWriter(nomeDoArquivo);
            BufferedWriter bw = new BufferedWriter(fw);
            for(Pedido pedido : listaProdutos){
                if(pedido.getId() != id){
                    String linha = pedido.getId() + ";" +
                            pedido.getNomePedido() + ";" +
                            pedido.getQunatidde() + ";" +
                            pedido.getPreco() + ";" +
                            "\n";
                    bw.write(linha);


                }
            }
            bw.close();

        } catch (Exception e) {
            throw new Exception("Erro ao remover produto");
        }

    }

    @Override
    public ArrayList<Pedido> listarProdutos() throws Exception {
        ArrayList<Pedido> listaProdutos = new ArrayList<>();
        try {

            FileReader fr = new FileReader(nomeDoArquivo);
            BufferedReader br = new BufferedReader(fr);


            String linha = "";
            while ((linha = br.readLine()) != null) {
                String[] vetorSTR = linha.split(";");
                Pedido objTipoDePedido = null;
                int id = Integer.parseInt(vetorSTR[0]);
                String nome = vetorSTR[1];
                int quantidade = Integer.parseInt(vetorSTR[2]);
                double valor = Double.parseDouble(vetorSTR[3]);
                double somaDeCadaProduto = quantidade * valor;
                objTipoDePedido = new Pedido(id, nome, quantidade, valor, somaDeCadaProduto);
                listaProdutos.add(objTipoDePedido);


            }
            br.close();
            fr.close();


        } catch (Exception e) {
            e.printStackTrace();
        }


        return listaProdutos;
    }
}

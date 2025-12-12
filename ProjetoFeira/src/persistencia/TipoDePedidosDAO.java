package persistencia;
import modelos.Pedidos;
import modelos.interfaces.IPedidosCRUD;

import java.io.*;
import java.util.ArrayList;



public class TipoDePedidosDAO implements IPedidosCRUD {

    private String nomeDoArquivo = null;
    public TipoDePedidosDAO()throws Exception {
        try {
            nomeDoArquivo = "./src/bancoDeDadaos/tiposDePedidos.txt" ;
            File arquivo = new File(nomeDoArquivo);




        } catch (Exception e) {

            System.out.println("Erro " + e.getMessage());
        }  
    }







    public int geradorID()throws Exception{
        int maiorNumero= 0;


            for(Pedidos pedido : listaDePedidos()){
                if(pedido.getId()>maiorNumero){
                    maiorNumero = pedido.getId();
                }
            }


        return maiorNumero +=1;


    }

    @Override
    public void salvar(Pedidos pedidos) throws Exception {
        int idTemporario = geradorID();
        pedidos.setId(idTemporario);
        FileWriter fr = new FileWriter(nomeDoArquivo, true);
        BufferedWriter br = new BufferedWriter(fr);
        String str =pedidos.getId() + ";" +
                pedidos.getNome() + ";" +
                pedidos.getQuantidade() + ";" +
                pedidos.getPreco() + "\n";

        br.write(str);
        br.close();

    }

    @Override
    public void excluir(Pedidos pedidos) throws Exception {

    }

   

    @Override
    public ArrayList<Pedidos> listaDePedidos() throws Exception {
        FileReader fr = new FileReader(nomeDoArquivo);
        BufferedReader br = new BufferedReader(fr);
        ArrayList<Pedidos> lista = new ArrayList<>();
        String linha = "";
        while((linha =br.readLine()) !=null){
            String vetorStr[] = linha.split(";");
            int id = Integer.parseInt(vetorStr[0]);
            String nome = vetorStr[1];
            int quantidade = Integer.parseInt(vetorStr[2]);
            double preco = Double.parseDouble(vetorStr[3]);
            Pedidos objPedido = new Pedidos(id ,nome, quantidade, preco);
            lista.add(objPedido);

        }
        br.close();
        return lista;

    }

    @Override
    public void atualizar(Pedidos pedidos) throws Exception {
        FileWriter fr = new FileWriter(nomeDoArquivo);
        BufferedWriter br = new BufferedWriter(fr);
        ArrayList<Pedidos> lista = null;
        lista = this.listaDePedidos();
        for(Pedidos obj : lista){
            if(obj.getId() == pedidos.getId()){
                String str = pedidos.getId() + ";" +
                        pedidos.getQuantidade() + ";" +
                        pedidos.getPreco() + ";" + "\n";
                br.write(str);

            } else{
                String str = obj.getId() + ";" +
                        obj.getQuantidade() + ";" +
                        obj.getPreco() + ";" + "\n";
                br.write(str);
            }
        }
        br.close();
        fr.close();


    }
}

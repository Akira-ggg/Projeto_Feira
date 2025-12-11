package persistencia;
import modelos.Pedidos;
import modelos.interfaces.IPedidosCRUD;

import java.io.*;
import java.util.ArrayList;



public class TipoDePedidosDAO implements IPedidosCRUD {

    private String nomeDoArquivo = null;

    public TipoDePedidosDAO()  {
        try {
             nomeDoArquivo = "src\\bancoDeDadaos\\tiposDePedidos.txt" ;
             File arquivo = new File(nomeDoArquivo);

             if(!arquivo.exists()){
                 arquivo.getParentFile().mkdirs();
                 arquivo.createNewFile();
             }

        }catch (Exception e){
            System.out.println("Erro ao criar: " + e.getMessage());
        }



    }
    @Override
    public void salvar(Pedidos pedidos) throws Exception {
        FileWriter fr = new FileWriter(nomeDoArquivo, true);
        BufferedWriter br = new BufferedWriter(fr);
        String str = pedidos.getNome() + ";" +
                pedidos.getQuantidade() + ";" +
                pedidos.getPreco() + "\n";

        br.write(str);
        br.close();

    }

    @Override
    public void excluir(Pedidos pedidos) throws Exception {

    }

    @Override
    public void editar(Pedidos pedidos) throws Exception {

    }

    @Override
    public ArrayList<Pedidos> listaDePedidos() throws Exception {
        FileReader fr = new FileReader(nomeDoArquivo);
        BufferedReader br = new BufferedReader(fr);
        ArrayList<Pedidos> lista = new ArrayList<>();
        String linha = "";
        while((linha =br.readLine()) !=null){
            String vetorStr[] = linha.split(";");
            String nome = vetorStr[0];
            int quantidade = Integer.parseInt(vetorStr[1]);
            double preco = Double.parseDouble(vetorStr[2]);
            Pedidos objPedido = new Pedidos(nome, quantidade, preco);
            lista.add(objPedido);

        }
        br.close();
        return lista;

    }

    @Override
    public void atualizar(Pedidos pedidos) throws Exception {

    }
}

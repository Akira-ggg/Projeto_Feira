package interfaces;
import java.util.ArrayList;
import modelos.Pedido;

public interface IPedidos {
     double somaTotal(Pedido tipoDePedido) throws Exception;
     void adicionarProduto(Pedido tipoDePedido) throws Exception;
     void removerProduto(int id)throws Exception;
     ArrayList <Pedido> listarProdutos()throws Exception;

}

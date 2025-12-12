package modelos.interfaces;
import java.util.ArrayList;
import modelos.Pedidos;

public interface IPedidosCRUD {
    void salvar(Pedidos pediso) throws Exception;
    void excluir(Pedidos pedidos) throws Exception;

    ArrayList<Pedidos> listaDePedidos() throws Exception;
    void atualizar(Pedidos pedidos) throws Exception;

}

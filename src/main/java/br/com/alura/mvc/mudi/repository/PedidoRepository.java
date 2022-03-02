package br.com.alura.mvc.mudi.repository;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByStatus(StatusPedido status);

    //versão sem extends JPA...
    //@PersistenceContext
    //private EntityManager em;
    //public List<Pedido> recuperarTodosPedidos() {
    //    Query qry = em.createQuery("SELECT p FROM Pedido p", Pedido.class);
    //    return qry.getResultList();
    //}


}

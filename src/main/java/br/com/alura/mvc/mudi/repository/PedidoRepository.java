package br.com.alura.mvc.mudi.repository;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Cacheable("PedidosPorStatus")
    List<Pedido> findByStatus(StatusPedido status, Pageable pageable);

    @Query("SELECT p FROM Pedido p WHERE p.user.username = :username")
    List<Pedido> findAllByUser(@Param("username") String username);

    @Query("SELECT p FROM Pedido p WHERE p.user.username = :username and p.status = :status")
    List<Pedido> findByStatusAndUsername(@Param("status") StatusPedido status, @Param("username") String username);


    //versão sem extends JPA...
    //@PersistenceContext
    //private EntityManager em;
    //public List<Pedido> recuperarTodosPedidos() {
    //    Query qry = em.createQuery("SELECT p FROM Pedido p", Pedido.class);
    //    return qry.getResultList();
    //}



}

package sales.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sales.domain.entity.Client;

import java.util.List;

public interface ClientesRepository extends JpaRepository<Client, Integer> {

//    @Query(value = " select * from client c where c.nome like '%:nome%' ", nativeQuery = true)
//    List<Client> encontrarPorNome(@Param("nome") String nome);
//
//    @Query(" delete from Client c where c.nome =:nome ")
//    @Modifying
//    void deleteByNome(String nome);
//
//    boolean existsByNome(String nome);
//
//    @Query(" select c from Client c left join fetch c.pedidos where c.id = :id  ")
//    Client findClienteFetchPedidos(@Param("id") Integer id);
}
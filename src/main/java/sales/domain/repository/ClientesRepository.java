package sales.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sales.domain.entity.Client;

import java.util.List;

public interface ClientesRepository extends JpaRepository<Client, Integer> {

//    @Query(value = " select * from client c where c.name like '%:name%' ", nativeQuery = true)
//    List<Client> encontrarPorname(@Param("name") String name);
//
//    @Query(" delete from Client c where c.name =:name ")
//    @Modifying
//    void deleteByname(String name);
//
//    boolean existsByname(String name);
//
//    @Query(" select c from Client c left join fetch c.pedidos where c.id = :id  ")
//    Client findClienteFetchPedidos(@Param("id") Integer id);
}
package housemarket.rodolforoca.com.DAO;

import housemarket.rodolforoca.com.Model.Anuncio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnuncioRepository extends PagingAndSortingRepository<Anuncio, Long> {

	@Query(value =  "SELECT * FROM ANUNCIO a " + "JOIN IMOVEL i ON a.imovel_id = i.imovel_id " +
            "JOIN ENDERECO e ON i.ENDERECO_ID = e.ENDERECO_ID " +
            "WHERE e.RUA ILIKE %?1% OR " +
            "e.BAIRRO ILIKE %?1% OR " +
            "e.CIDADE ILIKE %?1%", nativeQuery = true)
	Page<Anuncio> findAnunciosBySearch(String search, Pageable pageable);

	@Query(value =  "SELECT * FROM ANUNCIO a " + "JOIN IMOVEL i ON a.imovel_id = i.imovel_id " +
			"JOIN ENDERECO e ON i.ENDERECO_ID = e.ENDERECO_ID " +
			"WHERE e.RUA ILIKE %?1% OR " +
			"e.BAIRRO ILIKE %?1% OR " +
			"e.CIDADE ILIKE %?1%", nativeQuery = true)
	List<Anuncio> findAnunciosByTerm(String search);


	@Query(value =  "SELECT * FROM ANUNCIO a " + "JOIN USUARIO u ON a.user_id = u.user_id " + "WHERE u.user_id = ?1 ", nativeQuery = true)
	Page<Anuncio> findByAnunciante(int id, Pageable pageable);

	@Query(value =  "SELECT * FROM ANUNCIO a " + "JOIN USUARIO u ON a.user_id = u.user_id " + "WHERE u.user_id = ?1 AND a.tipo = 0 ", nativeQuery = true)
	List<Anuncio> findAnunciosVenda(int userId);

	@Query(value =  "SELECT * FROM ANUNCIO a " + "JOIN USUARIO u ON a.user_id = u.user_id " + "WHERE u.user_id = ?1 AND a.tipo = 1 ", nativeQuery = true)
	List<Anuncio> findAnunciosAluguel(int userId);

	Anuncio findById(int id);
}

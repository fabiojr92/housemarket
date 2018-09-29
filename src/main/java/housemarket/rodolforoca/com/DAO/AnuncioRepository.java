package housemarket.rodolforoca.com.DAO;

import housemarket.rodolforoca.com.Model.Anuncio;
import housemarket.rodolforoca.com.Model.Imovel;
import housemarket.rodolforoca.com.Model.Endereco;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository("anuncioRepository")
public interface AnuncioRepository extends JpaRepository<Anuncio, Integer> {
    
	@Query(value =  "SELECT * FROM ANUNCIO a" +
            "JOIN ENDERECO e ON e.ENDERECO_ID = a.ENDERECO_ID " +
            "WHERE e.RUA ILIKE %?1% OR " +
            "e.BAIRRO ILIKE %?1% OR " +
            "e.CIDADE ILIKE %?1%", nativeQuery = true)
	List<Anuncio> findAnunciosBySearch(String search);
}

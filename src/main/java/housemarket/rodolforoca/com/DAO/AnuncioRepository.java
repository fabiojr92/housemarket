package housemarket.rodolforoca.com.DAO;

import housemarket.rodolforoca.com.Model.Anuncio;
import housemarket.rodolforoca.com.Model.Imovel;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface AnuncioRepository extends PagingAndSortingRepository<Anuncio, Long> {

    Anuncio findById(int id);
}

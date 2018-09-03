package housemarket.rodolforoca.com.DAO;

import housemarket.rodolforoca.com.Model.Anuncio;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface anuncioRepository extends CrudRepository<Anuncio, Long> {

    List<Anuncio> findById(int id);
}

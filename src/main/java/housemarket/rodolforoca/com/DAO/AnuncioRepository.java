package housemarket.rodolforoca.com.DAO;

import housemarket.rodolforoca.com.Model.Anuncio;
import housemarket.rodolforoca.com.Model.Imovel;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AnuncioRepository extends CrudRepository<Anuncio, Long> {

    Anuncio findById(int id);
}

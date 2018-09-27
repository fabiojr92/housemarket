package housemarket.rodolforoca.com.DAO;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import housemarket.rodolforoca.com.Model.Anuncio;
import housemarket.rodolforoca.com.Model.Endereco;

public interface EnderecoRepository extends CrudRepository<Endereco, Long> {

    List<Endereco> findById(int id);
}

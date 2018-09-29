package housemarket.rodolforoca.com.DAO;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import housemarket.rodolforoca.com.Model.Anuncio;
import housemarket.rodolforoca.com.Model.Endereco;

@Repository
public interface EnderecoRepository extends CrudRepository<Endereco, Long> {

    List<Endereco> findById(int id);
}

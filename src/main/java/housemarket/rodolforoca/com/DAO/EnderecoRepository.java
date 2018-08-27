package housemarket.rodolforoca.com.DAO;

import housemarket.rodolforoca.com.Model.Endereco;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EnderecoRepository extends CrudRepository<Endereco, Long> {

    List<Endereco> findById(int id);
}

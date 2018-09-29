package housemarket.rodolforoca.com.DAO;

import housemarket.rodolforoca.com.Model.Endereco;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnderecoRepository extends CrudRepository<Endereco, Long> {

    List<Endereco> findById(int id);
}

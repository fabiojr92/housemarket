package housemarket.rodolforoca.com.DAO;

import housemarket.rodolforoca.com.Model.Imovel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ImovelRepository extends CrudRepository<Imovel, Long> {

    List<Imovel> findById(int id);
}

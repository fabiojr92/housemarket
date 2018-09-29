package housemarket.rodolforoca.com.DAO;

import housemarket.rodolforoca.com.Model.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    Usuario findByEmail(String email);
}
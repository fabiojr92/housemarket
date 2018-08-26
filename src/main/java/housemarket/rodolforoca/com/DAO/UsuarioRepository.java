package housemarket.rodolforoca.com.DAO;

import housemarket.rodolforoca.com.Model.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    List<Usuario> findByEmail(String email);
}
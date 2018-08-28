package housemarket.rodolforoca.com.DAO;

import housemarket.rodolforoca.com.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role findByRole(String role);

}

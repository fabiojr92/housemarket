package housemarket.rodolforoca.com.DAO;


import housemarket.rodolforoca.com.Application;
import housemarket.rodolforoca.com.Model.Role;
import housemarket.rodolforoca.com.Model.Usuario;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@DataJpaTest
public class UsuarioRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    Usuario usuario;

    @Before
    public void setUp() throws Exception {
        Role role = new Role();
        role.setRole("ADMIN");

        entityManager.persist(role);

        usuario = new Usuario("Roca");
        usuario.setEmail("teste@teste.com");
        usuario.setActive(1);
        usuario.setSenha("1234");
        usuario.setRoles(new HashSet<Role>(Arrays.asList(role)));

        entityManager.persist(usuario);
        entityManager.flush();
    }



    @Test
    public void findByEmail() {
        Usuario usuarioRetornado = usuarioRepository.findByEmail(usuario.getEmail());
        assertEquals(usuarioRetornado.getEmail(), usuario.getEmail());
    }
}
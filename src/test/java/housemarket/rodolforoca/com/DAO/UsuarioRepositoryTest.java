package housemarket.rodolforoca.com.DAO;

import housemarket.rodolforoca.com.Model.Role;
import housemarket.rodolforoca.com.Model.Usuario;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.Assert.*;

public class UsuarioRepositoryTest {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    Usuario usuario = null;
    @Before
    public void setUp() throws Exception {
        Role role = new Role();
        role.setRole("ADMIN");

        roleRepository.save(role);

        usuario = new Usuario("Roca");
        usuario.setEmail("teste@teste.com");
        usuario.setActive(1);
        usuario.setSenha(bCryptPasswordEncoder.encode("1234"));
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void findByEmail() {
    }

    @Test
    public void saveUsuario() {
        usuarioRepository.save(usuario);
        assertNotNull(usuario.getId());
    }
}
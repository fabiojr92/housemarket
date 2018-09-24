package housemarket.rodolforoca.com.DAO;

import housemarket.rodolforoca.com.ApplicationTests;
import housemarket.rodolforoca.com.Model.Role;
import housemarket.rodolforoca.com.Model.Usuario;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.*;

public class UsuarioRepositoryTest extends ApplicationTests {

    @Autowired
    DataSource dataSource;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    Usuario usuario = null;

    Role role = null;

    @Before
    public void setUp() throws Exception {
        role = new Role();
        role.setRole("ADMIN");


        usuario = new Usuario("Roca");
        usuario.setEmail("teste@teste.com");
        usuario.setActive(1);
        usuario.setSenha("1234");
        usuario.setRoles(new HashSet<Role>(Arrays.asList(role)));

    }

    @After
    public void tearDown() throws Exception {
    }

//    @Test
//    public void findByEmail() {
//    }

    @Test
    public void saveUsuario() {
        usuario.setSenha(bCryptPasswordEncoder.encode("1234"));

        roleRepository.save(role);

        usuarioRepository.save(usuario);
        assertNotNull(usuario.getId());
    }
}
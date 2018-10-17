package housemarket.rodolforoca.com;

import housemarket.rodolforoca.com.DAO.AnuncioRepository;
import housemarket.rodolforoca.com.DAO.EnderecoRepository;
import housemarket.rodolforoca.com.Model.Anuncio;
import housemarket.rodolforoca.com.Model.Usuario;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class UsuarioTests extends ApplicationTests {

    @Autowired
    EnderecoRepository enderecoRepository;

    @Test
    public void testInit() {
        Usuario usuario = new Usuario();
        assertNotNull(usuario);

//        Iterable<Anuncio> anuncios = anuncioRepository.findAll();
//        assertNotNull(anuncios);
    }
}

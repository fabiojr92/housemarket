package housemarket.rodolforoca.com.Controllers;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class IndexControllerTest {

    @Autowired
    IndexController indexController;

//    @Test
//    public void listaAnuncios() {
//        assertNotNull(indexController);
//        assertNotNull(indexController.listaAnuncios());
//    }

    @Test
    public void detalhesAnuncio() {
        assertNotNull(indexController);
        assertNotNull(indexController.detalhesAnuncio(1));
    }
}
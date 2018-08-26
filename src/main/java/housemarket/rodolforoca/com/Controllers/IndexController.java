package housemarket.rodolforoca.com.Controllers;

import housemarket.rodolforoca.com.DAO.UsuarioRepository;
import housemarket.rodolforoca.com.Model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.sql.DataSource;

@Controller
public class IndexController {

    @Autowired
    DataSource dataSource;

    @Autowired
    UsuarioRepository usuarioRepository;

    @RequestMapping("/index")
    public String hello(Model model, @RequestParam(value="name", required=false, defaultValue="Wooorld!!!") String name) {
        model.addAttribute("name", name);
        System.out.println(dataSource);
        usuarioRepository.save(new Usuario("Roca"));
        usuarioRepository.save(new Usuario("Roca2"));
        usuarioRepository.save(new Usuario("Roca3"));

        for (Usuario u: usuarioRepository.findAll()) {
            System.out.println(u.getId());
        }
        return "index";
    }
}

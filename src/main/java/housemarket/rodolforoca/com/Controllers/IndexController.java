package housemarket.rodolforoca.com.Controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import housemarket.rodolforoca.com.DAO.AnuncioRepository;
import housemarket.rodolforoca.com.DAO.ImovelRepository;
import housemarket.rodolforoca.com.Model.Anuncio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import housemarket.rodolforoca.com.DAO.EnderecoRepository;
import housemarket.rodolforoca.com.DAO.RoleRepository;
import housemarket.rodolforoca.com.Model.Role;

@Controller
public class IndexController {

    @Autowired
    DataSource dataSource;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ImovelRepository imovelRepository;

    @Autowired
    private AnuncioRepository anuncioRepository;

    @RequestMapping(value={"/", "/index"}, method = RequestMethod.GET)
    public String hello(Model model, @RequestParam(value="name", required=false, defaultValue="Wooorld!!!") String name) {
        model.addAttribute("name", name);
        Role role = new Role();
        role.setRole("ADMIN");

        roleRepository.save(role);
//        System.out.println(dataSource);
//        Usuario usuario = new Usuario("Roca");
//
//        Endereco endereco = new Endereco();
//
//        endereco.rua = "Minha Rua";
//        endereco.numero = 123;
//        endereco.bairro = "Centro";
//        endereco.complemento = "Centro";
//        endereco.cidade = "Campo Grande";
//        endereco.uf = "MS";
//
//        enderecoRepository.save(endereco);
//
//        usuario.setEndereco(endereco);
//        usuarioRepository.save(usuario);
//        usuarioRepository.save(new Usuario("Roca2"));
//        usuarioRepository.save(new Usuario("Roca3"));
//
//        for (Usuario u: usuarioRepository.findAll()) {
//            System.out.println(u.getId());
//        }
        return "index";
    }

//    @RequestMapping(value="/logout", method = RequestMethod.GET)
//    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null){
//            new SecurityContextLogoutHandler().logout(request, response, auth);
//        }
//        return "redirect:/login?logout";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
//    }
    @RequestMapping("/index")
    public ModelAndView listaAnuncios() {
        ModelAndView mv = new ModelAndView("index");
        Iterable<Anuncio> anuncio = anuncioRepository.findAll();
        mv.addObject("anuncio", anuncio);

        return mv;
    }

//    @RequestMapping("/delete")
//    public String deletar(int id) {
//        Anuncio anuncio = (Anuncio) anuncioRepository.findById(id);
//        anuncioRepository.delete(anuncio);
//        imovelRepository.delete(anuncio.getImovel());
//        enderecoRepository.delete(anuncio.getImovel().getEndereco());
//
//        return "redirect:/index";
//    }
}
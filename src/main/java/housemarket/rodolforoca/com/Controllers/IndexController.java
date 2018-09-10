package housemarket.rodolforoca.com.Controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import housemarket.rodolforoca.com.DAO.*;
import housemarket.rodolforoca.com.Model.*;
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

import java.util.Arrays;
import java.util.HashSet;

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
    private UsuarioRepository usuarioRepository;
    @Autowired
    private AnuncioRepository anuncioRepository;

//    @RequestMapping(value={"/", "/index"}, method = RequestMethod.GET)
//    public String hello(Model model, @RequestParam(value="name", required=false, defaultValue="Wooorld!!!") String name) {
//        model.addAttribute("name", name);
//
////        usuarioRepository.save(new Usuario("Roca3"));
////
////        for (Usuario u: usuarioRepository.findAll()) {
////            System.out.println(u.getId());
////        }
//        return "index";
//    }

    @RequestMapping(value={"/", "/index"}, method = RequestMethod.GET)
    public ModelAndView listaAnuncios() {
        addSampleData();

        ModelAndView mv = new ModelAndView("index");
        Iterable<Anuncio> anuncio = anuncioRepository.findAll();

        mv.addObject("anuncio", anuncio);

        return mv;
    }

    private void addSampleData() {
        Role role = new Role();
        role.setRole("ADMIN");

        roleRepository.save(role);

        Usuario usuario = new Usuario("Roca");

        Endereco endereco = new Endereco();

        endereco.setRua("Minha Rua");
        endereco.setNumero(123);
        endereco.setBairro("Centro");
        endereco.setCidade("Campo Grande");
        endereco.setUf("MS");

        enderecoRepository.save(endereco);

        usuario.setEndereco(endereco);
        usuario.setRoles(new HashSet<Role>(Arrays.asList(role)));
        usuarioRepository.save(usuario);

        Imovel imovel = new Imovel();
        imovel.setEndereco(endereco);
        imovel.setAreaTotalM2(300);
        imovel.setAreaConstruidaM2(200);
        imovel.setQtdQuartos(2);
        imovel.setQtdVagasGaragem(2);
        imovel.setTemChurrasqueira(false);
        imovel.setTemPiscina(false);
        imovel.setTipo(1);

        imovelRepository.save(imovel);

        Anuncio anuncio = new Anuncio();
        anuncio.setImovel(imovel);
        anuncio.setAnunciante(usuario);
        anuncio.setTipo(1);
        anuncio.setTitulo("Imóvel novo - ótimo preçø");

        anuncioRepository.save(anuncio);

    }

    //    @RequestMapping(value="/logout", method = RequestMethod.GET)
//    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null){
//            new SecurityContextLogoutHandler().logout(request, response, auth);
//        }
//        return "redirect:/login?logout";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
//    }



    @RequestMapping("/delete-anuncio")
    public String deletar(int id) {
        Anuncio anuncio = (Anuncio) anuncioRepository.findById(id);
        anuncioRepository.delete(anuncio);
        imovelRepository.delete(anuncio.getImovel());
        enderecoRepository.delete(anuncio.getImovel().getEndereco());

        return "redirect:/index";
    }
}
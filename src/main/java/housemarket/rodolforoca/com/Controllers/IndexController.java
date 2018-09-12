package housemarket.rodolforoca.com.Controllers;

import java.util.Arrays;
import java.util.HashSet;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import housemarket.rodolforoca.com.DAO.AnuncioRepository;
import housemarket.rodolforoca.com.DAO.EnderecoRepository;
import housemarket.rodolforoca.com.DAO.ImovelRepository;
import housemarket.rodolforoca.com.DAO.RoleRepository;
import housemarket.rodolforoca.com.DAO.UsuarioRepository;
import housemarket.rodolforoca.com.Model.Anuncio;
import housemarket.rodolforoca.com.Model.Endereco;
import housemarket.rodolforoca.com.Model.Imovel;
import housemarket.rodolforoca.com.Model.Role;
import housemarket.rodolforoca.com.Model.Usuario;

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

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

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
        usuario.setEmail("rod@roc.com");
        usuario.setActive(1);
        usuario.setSenha(bCryptPasswordEncoder.encode("1234"));

        Endereco endereco = new Endereco();

        endereco.setRua("Rua Edward Quirino Lacerda");
        endereco.setNumero(216);
        endereco.setBairro("Residencial Ana Maria do Couto");
        endereco.setComplemento("nenhum");
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
        anuncio.setTitulo("Casa Térrea - ótimo preço");
        anuncio.setPreco(180.000);
        anuncio.setObservacoes("Excelente casa com amplo espaço");

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
    public String deletarAnuncio(int id) {
        Anuncio anuncio = (Anuncio) anuncioRepository.findById(id);
        anuncioRepository.delete(anuncio);
        imovelRepository.delete(anuncio.getImovel());
        enderecoRepository.delete(anuncio.getImovel().getEndereco());

        return "redirect:/index";
    }
    
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ModelAndView detalhesAnuncio(@PathVariable("id") long id) {
    	Anuncio anuncio = anuncioRepository.findById(id);
    	ModelAndView mv = new ModelAndView("visualizarAnuncio");
    	mv.addObject("anuncio", anuncio);
    	return mv;
    }
    
}
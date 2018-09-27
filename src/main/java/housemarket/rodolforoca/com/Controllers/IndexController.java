package housemarket.rodolforoca.com.Controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
import housemarket.rodolforoca.com.Service.AnuncioService;

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
    private AnuncioService anuncioService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping(value={"/", "/index"}, method = RequestMethod.GET)
    public String listaAnuncios(@PageableDefault(size = 10) Pageable pageable,
                               Model model) {
        if (pageable.getPageNumber() == 0) {
            addSampleData();
        }

        Page<Anuncio> page = anuncioRepository.findAll(pageable);
        model.addAttribute("page", page);
        return "index";
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

        for (int i = 1; i <= 10; i++) {
            addImovelSample(i, usuario, endereco);
        }
    }

    private void addImovelSample(int i, Usuario usuario, Endereco endereco) {
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
        anuncio.setTitulo("Casa Térrea - ótimo preço " + i);
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



    //@RequestMapping("/delete-anuncio")
   // public String deletarAnuncio(int id) {
        //Anuncio anuncio = anuncioRepository.findById(id);
       // anuncioRepository.delete(anuncio);
       // imovelRepository.delete(anuncio.getImovel());
       // enderecoRepository.delete(anuncio.getImovel().getEndereco());
//
      //  return "redirect:/index";
   // }
    
    @RequestMapping(value="/detalhesAnuncio/{id}", method=RequestMethod.GET)
    public ModelAndView detalhesAnuncio(@PathVariable("id") int id) {
        Optional<Anuncio> anuncio = anuncioRepository.findById(id);
    	ModelAndView mv = new ModelAndView("visualizarAnuncio");
    	mv.addObject("anuncio", anuncio);
    	return mv;
    }
    
    @RequestMapping(value = {"/pesquisar"}, method = RequestMethod.GET)
    public ModelAndView pesquisar(@RequestParam("search") String search) {
        ModelAndView mv = new ModelAndView();
        List<Anuncio> anuncio = anuncioService.buscarAnunciosPorPesquisa(search);
        mv.addObject("anuncio", anuncio);
        mv.setViewName("index");

        return mv;
    }
    
    
}
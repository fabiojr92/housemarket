package housemarket.rodolforoca.com.Controllers;

import java.util.*;

import javax.sql.DataSource;

import housemarket.rodolforoca.com.DAO.UsuarioRepository;
import housemarket.rodolforoca.com.Model.Usuario;
import housemarket.rodolforoca.com.Service.AnuncioSuggestions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import housemarket.rodolforoca.com.DAO.AnuncioRepository;
import housemarket.rodolforoca.com.DAO.RoleRepository;
import housemarket.rodolforoca.com.Model.Anuncio;

@Controller
public class PerfilController {

    @Autowired
    DataSource dataSource;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    private AnuncioRepository anuncioRepository;

    @RequestMapping(value={"/perfil-anunciante"}, method = RequestMethod.GET)
    public ModelAndView listaMeusAnuncios(@PageableDefault(size = 10) Pageable pageable,
                                      Model model) {

        String email = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();

        Usuario usuario = usuarioRepository.findByEmail(email);

        Page<Anuncio> page = anuncioRepository.findByAnunciante(usuario.getId(), pageable);
        model.addAttribute("page", page);
        model.addAttribute("user", usuario);

        ModelAndView mv = new ModelAndView("perfilAnunciante");
        mv.addObject(page);
        mv.addObject(usuario);

        return mv;
    }

    @RequestMapping(value={"/perfil-cliente"}, method = RequestMethod.GET)
    public ModelAndView listaAnunciosCurtidos(@PageableDefault(size = 10) Pageable pageable,
                                          Model model) {

        String email = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();

        Usuario usuario = usuarioRepository.findByEmail(email);


        Iterable<Anuncio> anuncios = usuario.getAnuncios();

        model.addAttribute("anuncios", anuncios);
        model.addAttribute("user", usuario);

        ModelAndView mv = new ModelAndView("perfilAnunciante");
        mv.addObject(anuncios);
        mv.addObject(usuario);

        return mv;
    }

//    @RequestMapping(value="/detalhesAnuncio/{id}", method=RequestMethod.GET)
//    public ModelAndView detalhesAnuncio(@PathVariable("id") int id) {
//        Anuncio anuncio = anuncioRepository.findById(id);
//        ModelAndView mv = new ModelAndView("visualizarAnuncio");
//        mv.addObject("anuncio", anuncio);
//        return mv;
//    }

//    @RequestMapping(value = "/anunciante-suggestion", method = RequestMethod.GET, produces = "application/json")
//    @ResponseBody
//    public AnuncioSuggestions autocompleteSuggestions(@RequestParam("searchTerm") String searchTerm) {
//        System.out.println("searchTerm: " + searchTerm);
//
//        ArrayList<Anuncio> suggestions = new ArrayList<>();
//
//        List<Anuncio> anuncios = anuncioRepository.findAnunciosByTerm(searchTerm);
//
//        for (Anuncio anuncio : anuncios) {
//            String lowerRua = anuncio.getImovel().getEndereco().getRua().toLowerCase();
//            String lowerBairro = anuncio.getImovel().getEndereco().getBairro().toLowerCase();
//            String lowerCidade = anuncio.getImovel().getEndereco().getCidade().toLowerCase();
//            if (lowerRua.contains(searchTerm.toLowerCase()) || lowerBairro.contains(searchTerm.toLowerCase()) || lowerCidade.contains(searchTerm.toLowerCase())) {
//
//                anuncio.setValue(anuncio.getTitulo());
//                anuncio.setData(anuncio.getId() + "");
//                suggestions.add(anuncio);
//            }
//        }
//
//        // max 20 elementos
//        int n = suggestions.size() > 20 ? 20 : suggestions.size();
//        List<Anuncio> sulb = new ArrayList<>(suggestions.subList(0, n));
//
//        AnuncioSuggestions sw = new AnuncioSuggestions();
//        sw.setSuggestions(sulb);
//        return sw;
//    }
//
//
//    @RequestMapping(value = {"/pesquisar"}, method = RequestMethod.GET)
//    public ModelAndView pesquisar(@RequestParam("search") String search, @PageableDefault(size = 10) Pageable pageable,
//                                  Model model) {
//        ModelAndView mv = new ModelAndView("index");
//
//        Page<Anuncio> page = anuncioRepository.findAnunciosBySearch(search, pageable);
//        model.addAttribute("page", page);
//
//        mv.addObject(page);
//
//        return mv;
//    }

}

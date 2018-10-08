package housemarket.rodolforoca.com.Controllers;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import housemarket.rodolforoca.com.Service.AnuncioSuggestions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import housemarket.rodolforoca.com.DAO.AnuncioRepository;
import housemarket.rodolforoca.com.DAO.RoleRepository;
import housemarket.rodolforoca.com.Model.Anuncio;


@Controller
public class IndexController {

    @Autowired
    DataSource dataSource;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private AnuncioRepository anuncioRepository;

//    @Autowired
//    private AnuncioService anuncioService;

    @RequestMapping(value={"/", "/index"}, method = RequestMethod.GET)
    public ModelAndView listaAnuncios(@PageableDefault(size = 10) Pageable pageable,
                               Model model) {

        Page<Anuncio> page = anuncioRepository.findAll(pageable);
        model.addAttribute("page", page);

        ModelAndView mv = new ModelAndView("index");
        mv.addObject(page);

        return mv;
    }



    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/index";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
    }



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
        Anuncio anuncio = anuncioRepository.findById(id);
    	ModelAndView mv = new ModelAndView("visualizarAnuncio");
    	mv.addObject("anuncio", anuncio);
    	return mv;
    }

    @RequestMapping(value = "/suggestion", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public AnuncioSuggestions autocompleteSuggestions(@RequestParam("searchTerm") String searchTerm) {
        System.out.println("searchTerm: " + searchTerm);

        ArrayList<Anuncio> suggestions = new ArrayList<>();

        List<Anuncio> anuncios = anuncioRepository.findAnunciosByTerm(searchTerm);

        for (Anuncio anuncio : anuncios) {
            String lowerRua = anuncio.getImovel().getEndereco().getRua().toLowerCase();
            String lowerBairro = anuncio.getImovel().getEndereco().getBairro().toLowerCase();
            String lowerCidade = anuncio.getImovel().getEndereco().getCidade().toLowerCase();

            if (lowerRua.contains(searchTerm.toLowerCase())) {
                anuncio.setValue(anuncio.getImovel().getEndereco().getRua());

            } else if (lowerBairro.contains(searchTerm.toLowerCase())) {
                anuncio.setValue(anuncio.getImovel().getEndereco().getBairro());
            } else if (lowerCidade.contains(searchTerm.toLowerCase())) {
                anuncio.setValue(anuncio.getImovel().getEndereco().getCidade());
            }
            anuncio.setData(anuncio.getId() + "");
            suggestions.add(anuncio);
        }

        // max 5 elementos
        int n = suggestions.size() > 5 ? 5 : suggestions.size();
        List<Anuncio> sulb = new ArrayList<>(suggestions.subList(0, n));

        AnuncioSuggestions sw = new AnuncioSuggestions();
        sw.setSuggestions(sulb);
        return sw;
    }


    @RequestMapping(value = {"/pesquisar"}, method = RequestMethod.GET)
    public ModelAndView pesquisar(@RequestParam("search") String search, @PageableDefault(size = 10) Pageable pageable,
                                  Model model) {
        ModelAndView mv = new ModelAndView("index");

        Page<Anuncio> page = anuncioRepository.findAnunciosBySearch(search, pageable);
        model.addAttribute("page", page);

        mv.addObject(page);

        return mv;
    }


}
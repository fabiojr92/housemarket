package housemarket.rodolforoca.com.Controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
        Anuncio anuncio = anuncioRepository.findById(id);
    	ModelAndView mv = new ModelAndView("visualizarAnuncio");
    	mv.addObject("anuncio", anuncio);
    	return mv;
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
package housemarket.rodolforoca.com.Controllers;


import housemarket.rodolforoca.com.DAO.AnuncioRepository;
import housemarket.rodolforoca.com.DAO.RoleRepository;
import housemarket.rodolforoca.com.Model.Anuncio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.sql.DataSource;
import java.util.List;

@Controller
public class RelatorioController {

    @Autowired
    DataSource dataSource;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private AnuncioRepository anuncioRepository;

//    @Autowired
//    private AnuncioService anuncioService;

    @RequestMapping(value = {"/relatorio-venda"}, method = RequestMethod.GET)
    public ModelAndView listaAnunciosVenda() {

        Iterable<Anuncio> anuncios = anuncioRepository.findAnunciosVenda();

        ModelAndView mv = new ModelAndView("relatorioAnuncios");

        mv.addObject("anuncios", anuncios);
        double total = calcularPrecoTotal(anuncios);

        mv.addObject("total", total);

        return mv;
    }

    @RequestMapping(value = {"/relatorio-aluguel"}, method = RequestMethod.GET)
    public ModelAndView listaAnunciosAluguel(@PageableDefault(size = 10) Pageable pageable,
                                           Model model) {

        Iterable<Anuncio> anuncios = anuncioRepository.findAnunciosAluguel();

        ModelAndView mv = new ModelAndView("relatorioAnuncios");
        mv.addObject("anuncios", anuncios);

        double total = calcularPrecoTotal(anuncios);

        mv.addObject("total", total);

        return mv;
    }

    public double calcularPrecoTotal(Iterable<Anuncio> anuncios) {
        double total = 0;
        for (Anuncio anuncio: anuncios) {
            total += anuncio.getPreco();
        }
        return total;
    }

}
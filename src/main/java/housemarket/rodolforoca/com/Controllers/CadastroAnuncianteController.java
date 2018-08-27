package housemarket.rodolforoca.com.Controllers;


import housemarket.rodolforoca.com.DAO.EnderecoRepository;
import housemarket.rodolforoca.com.DAO.UsuarioRepository;
import housemarket.rodolforoca.com.Model.Endereco;
import housemarket.rodolforoca.com.Model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class CadastroAnuncianteController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    EnderecoRepository enderecoRepository;

    @RequestMapping("/cadastro-anunciante")
    public ModelAndView cadastro(Usuario usuario) {
        ModelAndView mv = new ModelAndView("/cadastroAnunciante");
        usuario.setEndereco(new Endereco());
        mv.addObject("usuario", usuario);
        return mv;
    }

    @RequestMapping("/salvar-anunciante")
    public ModelAndView salvar(@Valid Usuario usuario, BindingResult result) {
        ModelAndView mv = new ModelAndView("/cadastroAnunciante");
        mv.addObject("usuario", usuario);

        enderecoRepository.save(usuario.getEndereco());
        usuarioRepository.save(usuario);

        return mv;
    }
}

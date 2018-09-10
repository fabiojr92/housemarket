package housemarket.rodolforoca.com.Controllers;

import housemarket.rodolforoca.com.DAO.ImovelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import housemarket.rodolforoca.com.DAO.AnuncioRepository;
import housemarket.rodolforoca.com.DAO.EnderecoRepository;
import housemarket.rodolforoca.com.Model.Anuncio;
import housemarket.rodolforoca.com.Model.Endereco;
import housemarket.rodolforoca.com.Model.Imovel;

@Controller
public class CadastroAnuncioController {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ImovelRepository imovelRepository;

    @Autowired
    private AnuncioRepository anuncioRepository;
    
    
    @RequestMapping("/cadastro-anuncio")
    public ModelAndView cadastro(Anuncio anuncio) {
        ModelAndView mv = new ModelAndView("/cadastroAnuncio");
        Endereco endereco = new Endereco();
        Imovel imovel = new Imovel();
        imovel.setEndereco(endereco);
        anuncio.setImovel(imovel);
        mv.addObject("anuncio", anuncio);
        return mv;
    }

 //   @RequestMapping("/salvar-anuncio")
 //   public ModelAndView salvar(@Valid Usuario usuario, BindingResult result) {
 //       ModelAndView mv = new ModelAndView("/cadastroAnuncio");
//        mv.addObject("usuario", usuario);
//        usuario.setSenha(bCryptPasswordEncoder.encode(usuario.getSenha()));
//        usuario.setActive(1);
//        Role userRole = roleRepository.findByRole("ADMIN");
//        if(userRole == null) {
//            userRole = new Role("ADMIN");
//            roleRepository.save(userRole);
//        }
//        usuario.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
//
//        enderecoRepository.save(usuario.getEndereco());
//        usuarioRepository.save(usuario);

 //       return mv;
 //   }
    
    @RequestMapping("/salvar-anuncio")
    public String salvar(Anuncio anuncio) {
      	enderecoRepository.save(anuncio.getImovel().getEndereco());
      	imovelRepository.save(anuncio.getImovel());
    	anuncioRepository.save(anuncio);
    	
    	return "redirect:/index";
   }

}
    


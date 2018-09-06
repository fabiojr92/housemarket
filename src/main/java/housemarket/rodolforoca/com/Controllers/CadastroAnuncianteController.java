package housemarket.rodolforoca.com.Controllers;


import java.util.Arrays;
import java.util.HashSet;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import housemarket.rodolforoca.com.DAO.EnderecoRepository;
import housemarket.rodolforoca.com.DAO.RoleRepository;
import housemarket.rodolforoca.com.DAO.UsuarioRepository;
import housemarket.rodolforoca.com.Model.Endereco;
import housemarket.rodolforoca.com.Model.Role;
import housemarket.rodolforoca.com.Model.Usuario;

@Controller
public class CadastroAnuncianteController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    EnderecoRepository enderecoRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping("/cadastro-anunciante")
    public ModelAndView cadastro(Usuario usuario) {
        ModelAndView mv = new ModelAndView("/cadastroAnunciante");
        if (usuario.getEndereco() == null) {
            usuario.setEndereco(new Endereco());
        }
        mv.addObject("usuario", usuario);
        return mv;
    }

    @RequestMapping("/salvar-anunciante")
    public ModelAndView salvar(@Valid Usuario usuario, BindingResult result) {
        ModelAndView mv = new ModelAndView("/cadastroAnunciante");
        mv.addObject("usuario", usuario);
        usuario.setSenha(bCryptPasswordEncoder.encode(usuario.getSenha()));
        usuario.setActive(1);
        Role userRole = roleRepository.findByRole("ADMIN");
        if(userRole == null) {
            userRole = new Role("ADMIN");
            roleRepository.save(userRole);
        }
        usuario.setRoles(new HashSet<Role>(Arrays.asList(userRole)));

        enderecoRepository.save(usuario.getEndereco());
        usuarioRepository.save(usuario);

        return mv;
    }

//    @RequestMapping("/delete")
//    public Usuario deleteUserBy(String email) {
//    	UsuarioRepository usuario = (UsuarioRepository) usuario.findByEmail(email);
//    	usuario.delete((Usuario) usuario);
//    	
//    	return "redirect:/index";
//    }
    
//    public Usuario findUserByEmail(String email) {
//    
//    	return usuarioRepository.findByEmail(email);
//    }
//
//    public void saveUser(Usuario user) {
//        user.setSenha(bCryptPasswordEncoder.encode(user.getSenha()));
//        user.setActive(1);
//        Role userRole = roleRepository.findByRole("ADMIN");
//        if(userRole == null) {
//            userRole = new Role("ADMIN");
//            roleRepository.save(userRole);
//        }
//        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
//        usuarioRepository.save(user);
//    }
}

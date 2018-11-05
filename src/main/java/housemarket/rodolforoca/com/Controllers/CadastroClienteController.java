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
import housemarket.rodolforoca.com.DAO.RoleRepository;
import housemarket.rodolforoca.com.DAO.UsuarioRepository;
import housemarket.rodolforoca.com.Model.Endereco;
import housemarket.rodolforoca.com.Model.Role;
import housemarket.rodolforoca.com.Model.Usuario;

@Controller
public class CadastroClienteController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping("/cadastro-cliente")
    public ModelAndView cadastroCliente(Usuario usuario) {
        ModelAndView mv = new ModelAndView("/cadastroCliente");
        mv.addObject("usuario", usuario);
        return mv;
    }

    @RequestMapping("/salvar-cliente")
    public ModelAndView salvarCliente(@Valid Usuario usuario, BindingResult result) {
        ModelAndView mv = new ModelAndView("/cadastroCliente");
        mv.addObject("cliente", usuario);
        usuario.setSenha(bCryptPasswordEncoder.encode(usuario.getSenha()));
        usuario.setActive(1);
        usuario.setTipo(1);
        Role userRole = roleRepository.findByRole("CLIENTE");
        if(userRole == null) {
            userRole = new Role("CLIENTE");
            roleRepository.save(userRole);
        }
        usuario.setRoles(new HashSet<Role>(Arrays.asList(userRole)));

        usuarioRepository.save(usuario);

        return mv;
    }
}

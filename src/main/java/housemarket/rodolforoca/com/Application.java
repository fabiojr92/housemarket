package housemarket.rodolforoca.com;

import housemarket.rodolforoca.com.DAO.*;
import housemarket.rodolforoca.com.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.h2.server.web.WebServlet;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashSet;


@SpringBootApplication
public class Application extends SpringBootServletInitializer {

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

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		addSampleData();

		return application.sources(Application.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public ServletRegistrationBean h2servletRegistration() {
		ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
		registration.addUrlMappings("/console/*");
		return registration;
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
}

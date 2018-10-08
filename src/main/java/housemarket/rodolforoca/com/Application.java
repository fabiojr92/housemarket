package housemarket.rodolforoca.com;

import housemarket.rodolforoca.com.DAO.*;
import housemarket.rodolforoca.com.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
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
public class Application extends SpringBootServletInitializer implements ApplicationRunner {

	@Autowired
	DataSource dataSource;

	@Autowired
	private RoleRepository roleRepository;

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

	@Override
	public void run(ApplicationArguments args) throws Exception {
		addSampleData();
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

		endereco.setRua("Av Afonso Pena");
		endereco.setNumero(2160);
		endereco.setBairro("Centro");
		endereco.setComplemento("nenhum");
		endereco.setCidade("Campo Grande");
		endereco.setUf("MS");

		enderecoRepository.save(endereco);

		usuario.setEndereco(endereco);
		usuario.setRoles(new HashSet<Role>(Arrays.asList(role)));
		usuarioRepository.save(usuario);

		for (int i = 1; i <= 30; i++) {
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

		Endereco novoEndereco = new Endereco();

		novoEndereco.setRua("Av Mato Grosso");
		novoEndereco.setNumero(3160);
		novoEndereco.setBairro("Parque dos Poderes");
		novoEndereco.setComplemento("nenhum");
		novoEndereco.setCidade("Dourados");
		novoEndereco.setUf("MS");

		enderecoRepository.save(novoEndereco);

		Imovel novoImovel = new Imovel();
		novoImovel.setEndereco(novoEndereco);
		novoImovel.setAreaTotalM2(300);
		novoImovel.setAreaConstruidaM2(200);
		novoImovel.setQtdQuartos(2);
		novoImovel.setQtdVagasGaragem(2);
		novoImovel.setTemChurrasqueira(false);
		novoImovel.setTemPiscina(false);
		novoImovel.setTipo(1);

		imovelRepository.save(novoImovel);

		Anuncio anuncio = new Anuncio();
		anuncio.setImovel(imovel);
		anuncio.setAnunciante(usuario);
		anuncio.setTipo(1);
		anuncio.setTitulo("Casa Térrea - ótimo preço " + i);
		anuncio.setPreco(180.000);
		anuncio.setObservacoes("Excelente casa com amplo espaço");


		Anuncio novoAnuncio = new Anuncio();
		novoAnuncio.setImovel(novoImovel);
		novoAnuncio.setAnunciante(usuario);
		novoAnuncio.setTipo(0);
		novoAnuncio.setTitulo("Apartamento caro " + i);
		novoAnuncio.setPreco(180.000);
		novoAnuncio.setObservacoes("Excelente ap com amplo espaço");

		if (i == 0 || i % 2 == 0) {
			anuncioRepository.save(anuncio);
		} else {
			anuncioRepository.save(novoAnuncio);
		}

	}
}

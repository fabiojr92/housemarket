package housemarket.rodolforoca.com.Controllers;

import housemarket.rodolforoca.com.DAO.AnuncioRepository;
import housemarket.rodolforoca.com.DAO.RoleRepository;
import housemarket.rodolforoca.com.DAO.UsuarioRepository;
import housemarket.rodolforoca.com.Model.Anuncio;
import housemarket.rodolforoca.com.Model.Relatorio;
import housemarket.rodolforoca.com.Model.Usuario;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@Controller
public class RelatorioController {

    @Autowired
    DataSource dataSource;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private AnuncioRepository anuncioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    @RequestMapping(value = {"/relatorio-venda"}, method = RequestMethod.GET)
    public ModelAndView listaAnunciosVenda() {
        Relatorio relatorio = getRelatorioPorTipo("Venda");

        return getRelatorioMV(relatorio);
    }

    @RequestMapping(value = {"/relatorio-aluguel"}, method = RequestMethod.GET)
    public ModelAndView listaAnunciosAluguel() {
        Relatorio relatorio = getRelatorioPorTipo("Aluguel");

        return getRelatorioMV(relatorio);
    }

    @RequestMapping(value="/export-json/{tipo}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<Object> convertJson(@PathVariable("tipo") String tipo) {
        Relatorio relatorio = getRelatorioPorTipo(tipo);

        ObjectMapper mapper = new ObjectMapper();

        try {
            byte[] buf = mapper.writeValueAsBytes(relatorio);

            return ResponseEntity
                    .ok()
                    .contentLength(buf.length)
                    .contentType(
                            MediaType.parseMediaType("application/octet-stream"))
                    .header("Content-Disposition", "attachment; filename=\"relatorio.json\"")
                    .body(new InputStreamResource(new ByteArrayInputStream(buf)));
        } catch (JsonGenerationException e) {
            e.printStackTrace();
            return ResponseEntity.noContent().build();
        } catch (JsonMappingException e) {
            e.printStackTrace();
            return ResponseEntity.noContent().build();
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.noContent().build();
        }
    }

    @RequestMapping(value="/export-csv/{tipo}")
    @ResponseBody
    public void convertCSV(@PathVariable("tipo") String tipo, HttpServletResponse response) throws IOException {
        Relatorio relatorio = getRelatorioPorTipo(tipo);

        String csvFileName = "relatorio.csv";

        response.setContentType("text/csv");

        // creates mock data
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                csvFileName);
        response.setHeader(headerKey, headerValue);

        // uses the Super CSV API to generate CSV data from the model data
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),
                CsvPreference.STANDARD_PREFERENCE);

        String[] header = {"titulo", "preco" };

        csvWriter.writeHeader(header);

        csvWriter.write(relatorio, "anunciante");
        csvWriter.write(relatorio, "tipo");

        for (Anuncio anuncio : relatorio.getAnuncios()) {
            csvWriter.write(anuncio, header);
        }
        csvWriter.write(relatorio, "total");

        csvWriter.close();
    }

    @RequestMapping(value="/export-txt/{tipo}")
    @ResponseBody
    public ResponseEntity<Object> convertTXT(@PathVariable("tipo") String tipo) {
        Relatorio relatorio = getRelatorioPorTipo(tipo);

        StringBuilder builder = new StringBuilder();
        builder.append("Relatorio de " + relatorio.getTipo() + "\n");
        builder.append("Anunciante " + relatorio.getAnunciante() + "\n");
        builder.append("\n");
        builder.append("Anúncios");
        builder.append("\n");

        for (Anuncio anuncio: relatorio.getAnuncios()) {
            builder.append(anuncio.getTitulo() + "    " + "R$ " + anuncio.getPreco() + "\n");
        }

        builder.append("\n");
        builder.append("Total " + "    " + "R$ " + relatorio.getTotal());

        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType("text/plain"))
                .header("Content-Disposition", "attachment; filename=\"relatorio.txt\"")
                .body(builder.toString());
    }

    // Helpers
    private ModelAndView getRelatorioMV(Relatorio relatorio) {
        ModelAndView mv = new ModelAndView("relatorioAnuncios");
        mv.addObject("anuncios", relatorio.getAnuncios());

        mv.addObject("total", relatorio.getTotal());
        mv.addObject("tipo", relatorio.getTipo());
        mv.addObject("usuario", relatorio.getAnunciante());

        return mv;
    }

    private Relatorio getRelatorioPorTipo(@PathVariable("tipo") String tipo) {
        Usuario usuario = getUsuarioLogado();

        Iterable<Anuncio> anuncios;

        if (tipo.toLowerCase().equals("aluguel")) {
            anuncios = getAnunciosAluguel(usuario.getId());
        } else {
            anuncios = getAnunciosVenda(usuario.getId());
        }

        return gerarRelatorio(tipo, anuncios, usuario);
    }

    private Relatorio gerarRelatorio(String tipo, Iterable<Anuncio> anuncios, Usuario usuario) {
        Relatorio relatorio = new Relatorio();
        relatorio.setTipo(tipo);
        relatorio.setAnunciante(usuario.getNome());
//        relatorio.setAnuncios(anuncios);
        relatorio.setAnuncios(anuncios);
        relatorio.setTotal(anuncios);

        return relatorio;
    }

    private Usuario getUsuarioLogado() {
        String email = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();

        Usuario usuario = usuarioRepository.findByEmail(email);

        return usuario;
    }

    private Iterable<Anuncio> getAnunciosVenda(int usuarioID) {
        Iterable<Anuncio> anuncios = anuncioRepository.findAnunciosVenda(usuarioID);

        return anuncios;
    }

    private Iterable<Anuncio> getAnunciosAluguel(int usuarioID) {
        Iterable<Anuncio> anuncios = anuncioRepository.findAnunciosAluguel(usuarioID);

        return anuncios;
    }
}
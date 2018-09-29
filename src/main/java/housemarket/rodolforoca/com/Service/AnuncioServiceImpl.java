package housemarket.rodolforoca.com.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import antlr.collections.List;
import housemarket.rodolforoca.com.DAO.AnuncioRepository;
import housemarket.rodolforoca.com.Model.Anuncio;

@Service("anuncioService")
public class AnuncioServiceImpl {
//    implements
//} AnuncioService {

//    @Qualifier("anuncioRepository")
//    @Autowired
//    private AnuncioRepository anuncioRepository;
//
//    @Override
//    public Optional<Anuncio> buscarAnuncioPorId(int id) {
//        return anuncioRepository.findById(id);
//    }
//
//    @Override
//    public java.util.List<Anuncio> buscarAnunciosPorPesquisa(String search) {
//        return anuncioRepository.findAnunciosBySearch(search);
//    }
//
//    @Override
//    public void adicionar(Anuncio anuncio) {
//        anuncioRepository.saveAndFlush(anuncio);
//    }
//
//    @Override
//    public void atualizar(Anuncio anuncio) {
//        //TODO atualizar anuncio
//    }
//
//    @Override
//    public void deletar(Anuncio anuncio) {
//        anuncioRepository.delete(anuncio);
//    }
}

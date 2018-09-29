package housemarket.rodolforoca.com.Service;

import java.util.List;
import java.util.Optional;

import housemarket.rodolforoca.com.Model.Anuncio;

public interface AnuncioService {
	Optional<Anuncio> buscarAnuncioPorId(int id);
    List<Anuncio> buscarAnunciosPorPesquisa(String search);

    void adicionar(Anuncio anuncio);
    void atualizar(Anuncio anuncio);
    void deletar(Anuncio anuncio);
}
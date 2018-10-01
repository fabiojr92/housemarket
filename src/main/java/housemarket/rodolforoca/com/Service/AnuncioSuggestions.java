package housemarket.rodolforoca.com.Service;

import housemarket.rodolforoca.com.Model.Anuncio;

import java.util.List;

public class AnuncioSuggestions {

    List<Anuncio> suggestions;

    public List<Anuncio> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(List<Anuncio> suggestions) {
        this.suggestions = suggestions;
    }
}

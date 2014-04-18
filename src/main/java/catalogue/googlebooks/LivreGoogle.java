package catalogue.googlebooks;

import catalogue.DetailsLivre;

import java.util.Optional;
import java.util.function.Supplier;

public class LivreGoogle {

    public DetailsLivre enDetailsLivre() {
        final DetailsLivre résultat = new DetailsLivre();
        résultat.titre = volumeInfo.title;
        résultat.isbn = isbn();
        résultat.image = Optional.ofNullable(volumeInfo.imageLinks).orElse(new ImagesGoogle()).thumbnail;
        return résultat;
    }

    private String isbn() {
        if(volumeInfo.industryIdentifiers == null) {
            return "";
        }
        return identifiantDeType("ISBN_13")
                .orElseGet(premier()).identifier;
    }

    private Supplier<Identifieur> premier() {
        return () -> volumeInfo.industryIdentifiers.stream().findFirst().orElse(new Identifieur());
    }

    private Optional<Identifieur> identifiantDeType(String type) {
        return volumeInfo.industryIdentifiers.stream().filter(identifier -> identifier.type.equals(type)).findFirst();
    }

    public VolumeGoogle volumeInfo;
}

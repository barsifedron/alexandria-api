package alexandria.recherche.livre.details.synchronisation;

import alexandria.modele.LocalisateurEntrepots;
import alexandria.modele.emprunt.Emprunt;
import alexandria.modele.emprunt.EmpruntAjouteEvenement;
import fr.arpinum.graine.modele.evenement.CapteurEvenement;
import org.jongo.Jongo;

import javax.inject.Inject;

public class SurEmpruntAjoute implements CapteurEvenement<EmpruntAjouteEvenement> {

    @Inject
    public SurEmpruntAjoute(Jongo jongo) {
        miseAJourDisponibilite = new MiseAJourDisponibilite(jongo);
    }

    @Override
    public void executeEvenement(EmpruntAjouteEvenement evenement) {
        final Emprunt emprunt = LocalisateurEntrepots.emprunts().get(evenement.emprunt);
        miseAJourDisponibilite.metAJourDisponibilite(emprunt, false);

    }

    private final MiseAJourDisponibilite miseAJourDisponibilite;
}

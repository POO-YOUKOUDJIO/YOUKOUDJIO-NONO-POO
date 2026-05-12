package ipplanmanager.service;

import ipplanmanager.model.*;     // Pour voir BesoinReseau, ResultatVLSM, etc.
import ipplanmanager.exception.*; // Pour voir ReseauInsuffisantException, etc.
import java.util.ArrayList;       // Pour les listes
import java.util.ArrayList;

public class ValidateurPlanAdressage {
    public void verifierChevauchements(ArrayList<ResultatVLSM> resultats) throws ChevauchementReseauException {
        for (int i = 0; i < resultats.size(); i++) {
            for (int j = i + 1; j < resultats.size(); j++) {
                ResultatVLSM r1 = resultats.get(i);
                ResultatVLSM r2 = resultats.get(j);
                if (CalculateurReseau.reseauxSeChevauchent(r1.getAdresseReseau(), r1.getCidr(), r2.getAdresseReseau(), r2.getCidr())) {
                    throw new ChevauchementReseauException("Conflit entre " + r1.getNomBesoin() + " et " + r2.getNomBesoin());
                }
            }
        }
    }

    public void verifierAdresses(ArrayList<ResultatVLSM> resultats) throws AdresseIPInvalideException {
        for (ResultatVLSM res : resultats) {
            CalculateurReseau.verifierAdresseIP(res.getAdresseReseau());
        }
    }

    public void afficherValidationReussie() {
        System.out.println("Validation réussie : aucun conflit détecté.");
    }
}
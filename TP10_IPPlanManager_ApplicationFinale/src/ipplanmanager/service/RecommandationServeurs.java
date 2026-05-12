package ipplanmanager.service;

import ipplanmanager.model.VLAN;
import ipplanmanager.model.Recommandation;
import java.util.ArrayList;

/**
 * Règle pour la protection spécifique des VLANs Serveurs.
 */
public class RecommandationServeurs implements RegleRecommandation {

    @Override
    public ArrayList<Recommandation> evaluer(ArrayList<VLAN> vlans) {
        ArrayList<Recommandation> recs = new ArrayList<>();

        if (vlans == null) return recs;

        for (VLAN v : vlans) {
            if (v.getNom() != null && v.getNom().toUpperCase().contains("SERVEUR")) {
                // Ordre respecté : Titre, Priorité, Message
                recs.add(new Recommandation(
                    "Protection du VLAN Serveurs", 
                    "ÉLEVÉE", 
                    "Le VLAN " + v.getNom() + " doit être protégé par des ACL et surveillé en priorité."
                ));
            }
        }
        return recs;
    }
}
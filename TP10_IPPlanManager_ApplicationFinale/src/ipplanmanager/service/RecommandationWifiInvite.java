package ipplanmanager.service;

import ipplanmanager.model.VLAN;
import ipplanmanager.model.Recommandation;
import java.util.ArrayList;

/**
 * Règle pour l'isolation des réseaux WiFi invités.
 */
public class RecommandationWifiInvite implements RegleRecommandation {

    @Override
    public ArrayList<Recommandation> evaluer(ArrayList<VLAN> vlans) {
        ArrayList<Recommandation> recs = new ArrayList<>();

        if (vlans == null) return recs;

        for (VLAN v : vlans) {
            // Vérification si le nom contient WIFI
            if (v.getNom() != null && v.getNom().toUpperCase().contains("WIFI")) {
                
                // On ajoute la recommandation (Ordre: Titre, Priorité, Message)
                recs.add(new Recommandation(
                    "Isolation du WiFi", 
                    "ÉLEVÉE", 
                    "Le VLAN " + v.getNom() + " doit être isolé des VLANs internes sensibles."
                ));
            }
        }
        return recs;
    }
}
package ipplanmanager.service;

import ipplanmanager.model.VLAN;
import ipplanmanager.model.Recommandation;
import java.util.ArrayList;

public class RecommandationGrandVLAN implements RegleRecommandation {

    @Override
    public ArrayList<Recommandation> evaluer(ArrayList<VLAN> vlans) {
        ArrayList<Recommandation> recs = new ArrayList<>();

        if (vlans == null) return recs;

        for (VLAN v : vlans) {
            // On vérifie si le réseau associé existe et si sa capacité est > 200
            if (v.getReseauAssocie() != null && v.getReseauAssocie().getCapaciteHotes() > 200) {
                
                // On respecte l'ordre (titre, priorite, message)
                recs.add(new Recommandation(
                    "VLAN de grande taille", 
                    "MOYENNE", 
                    "Le VLAN " + v.getNom() + " possède une grande capacité. Attention aux tempêtes de broadcast."
                ));
            }
        }
        return recs;
    }
}
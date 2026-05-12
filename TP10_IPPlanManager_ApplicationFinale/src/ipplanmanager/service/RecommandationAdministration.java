package ipplanmanager.service;

import java.util.ArrayList;
import ipplanmanager.model.VLAN;
import ipplanmanager.model.Recommandation;

public class RecommandationAdministration implements RegleRecommandation {

    @Override
    public ArrayList<Recommandation> evaluer(ArrayList<VLAN> vlans) {
        ArrayList<Recommandation> recs = new ArrayList<>();
        if (vlans == null) return recs;

        for (VLAN v : vlans) {
            if (v.getNom() != null && v.getNom().toUpperCase().contains("ADMIN")) {
                recs.add(new Recommandation("Sécurité Administration", "HAUTE", "Accès restreint au VLAN " + v.getNom()));
            }
        }
        return recs;
    }
}
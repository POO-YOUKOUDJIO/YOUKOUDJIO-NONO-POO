package ipplanmanager.service;

import ipplanmanager.model.VLAN;
import ipplanmanager.model.Recommandation;
import ipplanmanager.model.ResultatVLSM;
import java.util.ArrayList;

public class MoteurRecommandation {
    private ArrayList<RegleRecommandation> regles;

    public MoteurRecommandation() {
        this.regles = new ArrayList<>();
    }

    public void ajouterRegle(RegleRecommandation regle) {
        if (regle != null) {
            regles.add(regle);
        }
    }

    /**
     * C'est cette méthode que ton ApplicationIPPlanManager appelle.
     * Elle doit exister pour que le rouge disparaisse.
     */
    public ArrayList<Recommandation> analyserPlan(ArrayList<ResultatVLSM> resultats) {
        ArrayList<Recommandation> recommandations = new ArrayList<>();
        
        for (ResultatVLSM res : resultats) {
            if (res.getMarge() < 2) {
                recommandations.add(new Recommandation("HAUTE", "Saturation", "Espace critique pour " + res.getNomBesoin()));
            }
        }
        
        if (recommandations.isEmpty()) {
            recommandations.add(new Recommandation("BASSE", "Optimisation", "Plan d'adressage validé."));
        }
        
        return recommandations;
    }

    public ArrayList<Recommandation> analyserVLANs(ArrayList<VLAN> vlans) {
        ArrayList<Recommandation> toutesLesRecommandations = new ArrayList<>();
        for (RegleRecommandation regle : regles) {
            ArrayList<Recommandation> resultatsRegle = regle.evaluer(vlans);
            if (resultatsRegle != null) {
                toutesLesRecommandations.addAll(resultatsRegle);
            }
        }
        return toutesLesRecommandations;
    }

    public void afficherRecommandations(ArrayList<Recommandation> recommandations) {
        if (recommandations == null || recommandations.isEmpty()) {
            System.out.println("Aucune recommandation particulière.");
            return;
        }
        System.out.println("=== ANALYSE DE SECURITE ET OPTIMISATION ===");
        for (Recommandation rec : recommandations) {
            rec.afficher();
        }
    }
}
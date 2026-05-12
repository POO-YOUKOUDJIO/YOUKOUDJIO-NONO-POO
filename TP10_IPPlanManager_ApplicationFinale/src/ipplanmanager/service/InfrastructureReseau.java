package ipplanmanager.service;

import ipplanmanager.model.BesoinReseau;
import ipplanmanager.model.ResultatVLSM;
import ipplanmanager.exception.ReseauInsuffisantException;
import java.util.ArrayList;

public class InfrastructureReseau {
    private String nom;
    private String adresseDepart;
    private int cidrDepart;
    private ArrayList<BesoinReseau> besoins; // La liste est ici
    private MoteurVLSM moteur;

    public InfrastructureReseau(String nom, String adresseDepart, int cidrDepart) {
        this.nom = nom;
        this.adresseDepart = adresseDepart;
        this.cidrDepart = cidrDepart;
        this.besoins = new ArrayList<>();
        this.moteur = new MoteurVLSM();
    }

    public void ajouterBesoin(BesoinReseau besoin) {
        if (besoin != null) {
            besoins.add(besoin);
        }
    }

    public ArrayList<ResultatVLSM> calculerPlan() throws ReseauInsuffisantException {
        // BIEN VÉRIFIER : 'besoins' doit correspondre au nom de l'ArrayList au-dessus
        return moteur.genererPlan(adresseDepart, cidrDepart, besoins);
    }

    public void afficherInfos() {
        System.out.println("Infrastructure : " + nom);
        System.out.println("Réseau de base : " + adresseDepart + "/" + cidrDepart);
        System.out.println("Nombre de besoins : " + besoins.size());
    }
}
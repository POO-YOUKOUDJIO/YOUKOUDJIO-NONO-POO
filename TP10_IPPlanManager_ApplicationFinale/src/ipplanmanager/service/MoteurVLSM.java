package ipplanmanager.service;

import ipplanmanager.model.BesoinReseau;
import ipplanmanager.model.ResultatVLSM;
import ipplanmanager.exception.ReseauInsuffisantException; // Ajout important
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MoteurVLSM {

    // AJOUT DU PARAMÈTRE int cidrDepart ET DE L'EXCEPTION
    public ArrayList<ResultatVLSM> genererPlan(String adresseDepart, int cidrDepart, ArrayList<BesoinReseau> besoins) throws ReseauInsuffisantException {
        ArrayList<ResultatVLSM> resultats = new ArrayList<>();

        // Tri des besoins par nombre d'hôtes décroissant
        Collections.sort(besoins, new Comparator<BesoinReseau>() {
            @Override
            public int compare(BesoinReseau b1, BesoinReseau b2) {
                return b2.getNombreHotes() - b1.getNombreHotes();
            }
        });

        int adresseCourante = CalculateurReseau.convertirIpEnEntier(adresseDepart);

        for (BesoinReseau besoin : besoins) {
            int cidr = CalculateurReseau.calculerCidrPourHotes(besoin.getNombreHotes());
            int capacite = CalculateurReseau.calculerNombreHotes(cidr);
            
            String adresseReseau = CalculateurReseau.convertirEntierEnIp(adresseCourante);

            // Création du résultat avec les 5 paramètres (nom, adresse, cidr, capacité, demandés)
            ResultatVLSM resultat = new ResultatVLSM(
                besoin.getNom(),
                adresseReseau,
                cidr,
                capacite,
                besoin.getNombreHotes()
            );
            
            resultats.add(resultat);
            
            // Calcul de l'adresse suivante
            adresseCourante = adresseCourante + CalculateurReseau.calculerTailleBloc(cidr);
        }

        return resultats;
    }
}
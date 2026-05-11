/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipplanmanager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author laura
 */
public class MoteurVLSM {
    public ArrayList<ResultatVLSM> genererPlan(String adresseDepart,
            ArrayList<BesoinReseau> besoins) {
        ArrayList<ResultatVLSM> resultats = new ArrayList<>();

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
            String masque = CalculateurReseau.obtenirMasqueDecimal(cidr);
            String adresseReseau = CalculateurReseau.convertirEntierEnIp(adresseCourante);
            String premiereAdresse = CalculateurReseau.calculerPremiereAdresseUtilisable(adresseReseau);
            String derniereAdresse = CalculateurReseau.calculerDerniereAdresseUtilisable(adresseReseau, cidr);

            ResultatVLSM resultat = new ResultatVLSM(besoin.getNom(),
                    adresseReseau, cidr, masque, capacite,
                    premiereAdresse, derniereAdresse);
            resultats.add(resultat);

            int tailleBloc = CalculateurReseau.calculerTailleBloc(cidr);
            adresseCourante = adresseCourante + tailleBloc;
        }

        return resultats;
    }

    public void verifierCapacite(String adresseDepart, int cidrDepart,
            ArrayList<BesoinReseau> besoins) throws ReseauInsuffisantException {
        int capaciteTotale = CalculateurReseau.calculerNombreHotes(cidrDepart);
        int totalBesoins = 0;
        for (BesoinReseau besoin : besoins) {
            totalBesoins += besoin.getNombreHotes();
        }
        if (totalBesoins > capaciteTotale) {
            throw new ReseauInsuffisantException(
                    "Reseau insuffisant : " + totalBesoins
                    + " hotes demandes mais seulement "
                    + capaciteTotale + " disponibles dans "
                    + adresseDepart + "/" + cidrDepart);
        }
    }
}



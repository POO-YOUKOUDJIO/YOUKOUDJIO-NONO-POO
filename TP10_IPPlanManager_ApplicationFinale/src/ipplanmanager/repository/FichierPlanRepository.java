package ipplanmanager.repository;

import ipplanmanager.model.ResultatVLSM;
import ipplanmanager.model.VLAN;
import ipplanmanager.model.Recommandation;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FichierPlanRepository {

    public void sauvegarderPlanCSV(ArrayList<ResultatVLSM> resultats, String cheminFichier) throws IOException {
        try (FileWriter writer = new FileWriter(cheminFichier)) {
            // L'en-tête mis à jour avec 7 colonnes
            writer.write("Nom;AdresseReseau;CIDR;Masque;HotesDemandes;Capacite;Marge\n");
            
            for (ResultatVLSM resultat : resultats) {
                writer.write(resultat.getNomBesoin() + ";"
                        + resultat.getAdresseReseau() + ";"
                        + resultat.getCidr() + ";"
                        + resultat.getMasqueDecimal() + ";"    // AJOUT : Masque (ex: 255.255.255.0)
                        + resultat.getHotesDemandes() + ";"   // AJOUT : Ce que l'utilisateur a demandé
                        + resultat.getCapaciteHotes() + ";"   // La capacité réelle du sous-réseau
                        + resultat.getMarge() + "\n");        // AJOUT : La différence (Libres)
            }
        }
    }

    public void sauvegarderVLANsCSV(ArrayList<VLAN> vlans, String cheminFichier) throws IOException {
        try (FileWriter writer = new FileWriter(cheminFichier)) {
            writer.write("ID;Nom;AdresseReseau;CIDR;Capacite\n");
            for (VLAN vlan : vlans) {
                if (vlan.getReseauAssocie() != null) {
                    writer.write(vlan.getId() + ";"
                            + vlan.getNom() + ";"
                            + vlan.getReseauAssocie().getAdresseReseau() + ";"
                            + vlan.getReseauAssocie().getCidr() + ";"
                            + vlan.getReseauAssocie().getCapaciteHotes() + "\n");
                }
            }
        }
    }

    public void sauvegarderRecommandations(ArrayList<Recommandation> recommandations, String cheminFichier) throws IOException {
        try (FileWriter writer = new FileWriter(cheminFichier)) {
            writer.write("===== RECOMMANDATIONS IPPLAN-MANAGER =====\n\n");
            if (recommandations == null || recommandations.isEmpty()) {
                writer.write("Aucune recommandation particulière.\n");
            } else {
                for (Recommandation rec : recommandations) {
                    writer.write("[" + rec.getPriorite() + "] "
                            + rec.getTitre() + " : "
                            + rec.getMessage() + "\n");
                }
            }
        }
    }
}
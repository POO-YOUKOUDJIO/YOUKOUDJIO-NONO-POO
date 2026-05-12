package ipplanmanager.service;

import ipplanmanager.model.BesoinReseau;
import ipplanmanager.model.Recommandation;
import ipplanmanager.model.ResultatVLSM;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class RapportService {

    public void genererRapportComplet(String nomProjet, ArrayList<BesoinReseau> besoins, 
                                     ArrayList<ResultatVLSM> resultats, 
                                     ArrayList<Recommandation> recommandations, 
                                     String cheminFichier) throws IOException {
        
        try (FileWriter writer = new FileWriter(cheminFichier)) {
            writer.write("====================================================\n");
            writer.write("      RAPPORT TECHNIQUE : " + nomProjet.toUpperCase() + "\n");
            writer.write("====================================================\n\n");

            writer.write("1. SYNTHÈSE DES BESOINS\n");
            for (BesoinReseau b : besoins) {
                writer.write(" - Service : " + b.getNom() + " (" + b.getNombreHotes() + " hôtes)\n");
            }

            writer.write("\n2. PLAN D'ADRESSAGE VLSM\n");
            writer.write(String.format("%-15s | %-18s | %-15s | %-6s\n", "Service", "Réseau", "Masque", "Marge"));
            writer.write("----------------------------------------------------------------------\n");
            for (ResultatVLSM r : resultats) {
                writer.write(String.format("%-15s | %-18s | %-15s | %-6d\n", 
                    r.getNomBesoin(), 
                    r.getAdresseReseau() + "/" + r.getCidr(),
                    r.getMasqueDecimal(),
                    r.getMarge()));
            }

            writer.write("\n3. RECOMMANDATIONS ET ALERTES\n");
            if (recommandations.isEmpty()) {
                writer.write(" - Aucune anomalie détectée.\n");
            } else {
                for (Recommandation rec : recommandations) {
                    writer.write(" [" + rec.getPriorite() + "] " + rec.getTitre() + " : " + rec.getMessage() + "\n");
                }
            }

            writer.write("\n\nFichier généré automatiquement par IPPlanManager.\n");
        }
    }
}
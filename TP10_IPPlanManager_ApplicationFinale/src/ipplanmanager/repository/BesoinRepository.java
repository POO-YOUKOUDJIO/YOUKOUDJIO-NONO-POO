package ipplanmanager.repository;

import ipplanmanager.model.BesoinReseau;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class BesoinRepository {

    /**
     * Charge les besoins depuis un fichier CSV (Nom;Hotes)
     * Renommée en chargerDepuisCSV pour correspondre à l'appel dans ApplicationIPPlanManager
     */
    public ArrayList<BesoinReseau> chargerDepuisCSV(String cheminFichier) throws IOException {
        ArrayList<BesoinReseau> besoins = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            boolean premiereLigne = true;
            
            while ((ligne = br.readLine()) != null) {
                // On saute l'en-tête (Nom;Hotes) s'il existe
                if (premiereLigne && ligne.contains(";")) {
                    // Si la première ligne contient des lettres, c'est l'en-tête
                    if (ligne.matches(".*[a-zA-Z].*")) {
                        premiereLigne = false;
                        continue;
                    }
                }
                
                if (ligne.trim().isEmpty()) continue;

                // Séparation par le point-virgule
                String[] colonnes = ligne.split(";");
                if (colonnes.length >= 2) {
                    try {
                        String nom = colonnes[0].trim();
                        int hotes = Integer.parseInt(colonnes[1].trim());
                        besoins.add(new BesoinReseau(nom, hotes));
                    } catch (NumberFormatException e) {
                        System.out.println("Saut d'une ligne invalide : " + ligne);
                    }
                }
            }
        }
        return besoins;
    }

    /**
     * Sauvegarde les besoins dans un fichier CSV
     */
    public void sauvegarderBesoins(ArrayList<BesoinReseau> besoins, String cheminFichier) throws IOException {
        try (FileWriter writer = new FileWriter(cheminFichier)) {
            // 1. Écriture de l'en-tête
            writer.write("Nom;Hotes\n");
            
            // 2. Écriture des données
            for (BesoinReseau besoin : besoins) {
                String ligne = besoin.getNom() + ";" + besoin.getNombreHotes() + "\n";
                writer.write(ligne);
            }
            
            System.out.println("Fichier de besoins sauvegardé avec succès : " + cheminFichier);
        }
    }
}
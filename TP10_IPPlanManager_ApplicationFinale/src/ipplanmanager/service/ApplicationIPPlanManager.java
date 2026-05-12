package ipplanmanager.service;

import ipplanmanager.console.ConsoleService;
import ipplanmanager.exception.AdresseIPInvalideException;
import ipplanmanager.exception.ChevauchementReseauException;
import ipplanmanager.exception.ConflitVLANException;
import ipplanmanager.exception.ReseauInsuffisantException;
import ipplanmanager.model.BesoinReseau;
import ipplanmanager.model.Recommandation;
import ipplanmanager.model.ResultatVLSM;
import ipplanmanager.model.VLAN;
import ipplanmanager.repository.FichierPlanRepository;
import ipplanmanager.repository.BesoinRepository; // Import pour le TP9
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ApplicationIPPlanManager {

    private ConsoleService console;
    private MoteurVLSM moteurVLSM;
    private GestionnaireVLAN gestionnaireVLAN;
    private ValidateurPlanAdressage validateur;
    private MoteurRecommandation moteurRecommandation;
    private FichierPlanRepository fichierRepository;
    private RapportService rapportService;

    public ApplicationIPPlanManager() {
        this.console = new ConsoleService();
        this.moteurVLSM = new MoteurVLSM();
        this.gestionnaireVLAN = new GestionnaireVLAN();
        this.validateur = new ValidateurPlanAdressage();
        this.moteurRecommandation = new MoteurRecommandation();
        this.fichierRepository = new FichierPlanRepository();
        this.rapportService = new RapportService();
    }

    public void demarrer() {
        boolean continuer = true;
        while (continuer) {
            int choix = console.afficherMenuPrincipal();

            switch (choix) {
                case 1:
                    executerGenerationComplete();
                    break;
                case 2:
                    executerGenerationDepuisCSV();
                    break;
                case 3:
                    System.out.println("Merci d'avoir utilisé IPPlan-Manager. Au revoir !");
                    continuer = false;
                    break;
                default:
                    System.out.println("Choix invalide, veuillez recommencer.");
            }
        }
    }

    private void executerGenerationComplete() {
        try {
            String nomProjet = console.saisirTexte("Nom du projet : ");
            String ipDepart = console.saisirTexte("Adresse IP de départ : ");
            int cidrDepart = console.saisirEntier("CIDR de départ (ex: 24) : ");

            ArrayList<BesoinReseau> besoins = console.saisirBesoins();
            traiterEtSauvegarder(nomProjet, ipDepart, cidrDepart, besoins);

        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    private void executerGenerationDepuisCSV() {
        try {
            String cheminCSV = console.saisirTexte("Chemin du fichier besoins CSV (ex: exports/besoins.csv) : ");
            
            BesoinRepository besoinRepo = new BesoinRepository();
            ArrayList<BesoinReseau> besoins = besoinRepo.chargerDepuisCSV(cheminCSV);

            if (besoins.isEmpty()) {
                System.out.println("Le fichier CSV est vide ou introuvable.");
                return;
            }

            String nomProjet = console.saisirTexte("Nom du projet pour l'export : ");
            String ipDepart = console.saisirTexte("Adresse IP de départ : ");
            int cidrDepart = console.saisirEntier("CIDR de départ : ");

            traiterEtSauvegarder(nomProjet, ipDepart, cidrDepart, besoins);

        } catch (Exception e) {
            System.out.println("Erreur lors du chargement CSV : " + e.getMessage());
        }
    }

    private void traiterEtSauvegarder(String nomProjet, String ipDepart, int cidrDepart, ArrayList<BesoinReseau> besoins) 
            throws ReseauInsuffisantException, AdresseIPInvalideException, ChevauchementReseauException, ConflitVLANException, IOException {
        
        // 1. Calcul VLSM
        ArrayList<ResultatVLSM> resultats = moteurVLSM.genererPlan(ipDepart, cidrDepart, besoins);

        // 2. Validation
        validateur.verifierAdresses(resultats);
        validateur.verifierChevauchements(resultats);

        // 3. Génération VLANs et Recommandations
        genererVLANs(resultats);
        ArrayList<Recommandation> recommandations = moteurRecommandation.analyserPlan(resultats);

        // 4. Affichage et Sauvegarde
        afficherResultats(resultats, recommandations);
        sauvegarderResultats(nomProjet, besoins, resultats, recommandations);
    }

    private void genererVLANs(ArrayList<ResultatVLSM> resultats) throws ConflitVLANException {
        gestionnaireVLAN.nettoyerVLANs(); // Optionnel : vide la liste avant une nouvelle génération
        int numeroVLAN = 10;
        for (ResultatVLSM resultat : resultats) {
            try {
                VLAN vlan = new VLAN(numeroVLAN, "VLAN_" + resultat.getNomBesoin(), resultat, "Généré automatiquement");
                gestionnaireVLAN.ajouterVLAN(vlan);
                numeroVLAN += 10;
            } catch (ChevauchementReseauException e) {
                System.out.println("Avertissement : " + e.getMessage());
            }
        }
    }

    private void afficherResultats(ArrayList<ResultatVLSM> resultats, ArrayList<Recommandation> recommandations) {
        System.out.println("\n--- RÉSULTATS DU PLAN VLSM ---");
        for (ResultatVLSM r : resultats) {
            r.afficher();
        }
        System.out.println("\n--- RECOMMANDATIONS TECHNIQUES ---");
        for (Recommandation rec : recommandations) {
            System.out.println("[" + rec.getPriorite() + "] " + rec.getTitre() + " : " + rec.getMessage());
        }
    }

    private void sauvegarderResultats(String nomProjet, ArrayList<BesoinReseau> besoins, ArrayList<ResultatVLSM> resultats, ArrayList<Recommandation> recommandations) throws IOException {
        File dossier = new File("exports");
        if (!dossier.exists()) {
            dossier.mkdir();
        }

        String nomNettoye = nomProjet.replace(" ", "_");

        String cheminPlan = "exports/" + nomNettoye + "_plan.csv";
        String cheminVlans = "exports/" + nomNettoye + "_vlans.csv";
        String cheminRecs = "exports/" + nomNettoye + "_recommandations.txt";
        String cheminRapport = "exports/" + nomNettoye + "_rapport.txt";

        fichierRepository.sauvegarderPlanCSV(resultats, cheminPlan);
        fichierRepository.sauvegarderVLANsCSV(gestionnaireVLAN.getListeVLANs(), cheminVlans);
        fichierRepository.sauvegarderRecommandations(recommandations, cheminRecs);
        rapportService.genererRapportComplet(nomProjet, besoins, resultats, recommandations, cheminRapport);
        
        System.out.println("\n[OK] Génération terminée. Consultez le dossier /exports.");
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipplanmanager;
import java.util.ArrayList;

/**
 *
 * @author laura
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("===== IPPlan-Manager : TP8 - Recommandations =====");

        MoteurVLSM moteurVLSM = new MoteurVLSM();
        MoteurRecommandation moteurRecommandation = new MoteurRecommandation();
        moteurRecommandation.ajouterRegle(new RecommandationWifiInvite());
        moteurRecommandation.ajouterRegle(new RecommandationServeurs());
        moteurRecommandation.ajouterRegle(new RecommandationGrandVLAN());
        moteurRecommandation.ajouterRegle(new RecommandationAdministration());
        moteurRecommandation.ajouterRegle(new RecommandationMargeAdresse());

        // ===== SCENARIO 1 =====
        System.out.println("\n===== SCENARIO 1 : Universite =====");
        ArrayList<BesoinReseau> besoins1 = new ArrayList<>();
        besoins1.add(new BesoinReseau("ETUDIANTS", 500));
        besoins1.add(new BesoinReseau("WIFI_INVITES", 200));
        besoins1.add(new BesoinReseau("ENSEIGNANTS", 120));
        besoins1.add(new BesoinReseau("LABORATOIRES", 60));
        besoins1.add(new BesoinReseau("SERVEURS", 30));

        ArrayList<ResultatVLSM> resultats1 = moteurVLSM.genererPlan("10.10.0.0", besoins1);
        GestionnaireVLAN gestionnaire1 = new GestionnaireVLAN();
        int numeroVLAN = 10;
        try {
            for (ResultatVLSM r : resultats1) {
                gestionnaire1.ajouterVLAN(new VLAN(numeroVLAN, r.getNomBesoin(),
                        r, "VLAN " + r.getNomBesoin()));
                numeroVLAN += 10;
            }
        } catch (ConflitVLANException e) {
            System.out.println("Erreur VLAN : " + e.getMessage());
        }

        System.out.println("\nPlan VLAN genere :");
        gestionnaire1.afficherTousLesVLANs();
        System.out.println("\nRecommandations proposees :");
        moteurRecommandation.afficherRecommandations(
                moteurRecommandation.analyserVLANs(gestionnaire1.getVlans()));

        // ===== SCENARIO 2 =====
        System.out.println("\n===== SCENARIO 2 : Entreprise =====");
        ArrayList<BesoinReseau> besoins2 = new ArrayList<>();
        besoins2.add(new BesoinReseau("ADMINISTRATION", 50));
        besoins2.add(new BesoinReseau("WIFI_INVITES", 120));
        besoins2.add(new BesoinReseau("SERVEURS", 20));
        besoins2.add(new BesoinReseau("CAMERAS", 80));
        besoins2.add(new BesoinReseau("VOIP", 60));

        ArrayList<ResultatVLSM> resultats2 = moteurVLSM.genererPlan("192.168.1.0", besoins2);
        GestionnaireVLAN gestionnaire2 = new GestionnaireVLAN();
        numeroVLAN = 10;
        try {
            for (ResultatVLSM r : resultats2) {
                gestionnaire2.ajouterVLAN(new VLAN(numeroVLAN, r.getNomBesoin(),
                        r, "VLAN " + r.getNomBesoin()));
                numeroVLAN += 10;
            }
        } catch (ConflitVLANException e) {
            System.out.println("Erreur VLAN : " + e.getMessage());
        }

        System.out.println("\nPlan VLAN genere :");
        gestionnaire2.afficherTousLesVLANs();
        System.out.println("\nRecommandations proposees :");
        moteurRecommandation.afficherRecommandations(
                moteurRecommandation.analyserVLANs(gestionnaire2.getVlans()));
    }
}

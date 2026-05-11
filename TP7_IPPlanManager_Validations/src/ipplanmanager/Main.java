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
        System.out.println("===== IPPlan-Manager : TP7 - Validations avancees =====");

        // ===== CAS NORMAL =====
        ArrayList<BesoinReseau> besoins = new ArrayList<>();
        besoins.add(new BesoinReseau("ADMINISTRATION", 50));
        besoins.add(new BesoinReseau("TECHNIQUE", 120));
        besoins.add(new BesoinReseau("WIFI", 80));
        besoins.add(new BesoinReseau("SERVEURS", 20));

        MoteurVLSM moteur = new MoteurVLSM();
        ArrayList<ResultatVLSM> resultats = moteur.genererPlan("192.168.1.0", besoins);

        System.out.println("\nPlan genere :");
        for (ResultatVLSM resultat : resultats) {
            resultat.afficher();
        }

        ValidateurPlanAdressage validateur = new ValidateurPlanAdressage();
        try {
            validateur.verifierAdresses(resultats);
            validateur.verifierChevauchements(resultats);
            validateur.afficherValidationReussie();
        } catch (AdresseIPInvalideException | ChevauchementReseauException e) {
            System.out.println("Erreur de validation : " + e.getMessage());
        }

        // ===== TEST ADRESSE INVALIDE =====
        System.out.println("\nTest adresse invalide :");
        try {
            CalculateurReseau.verifierAdresseIP("192.168.300.0");
        } catch (AdresseIPInvalideException e) {
            System.out.println("Erreur detectee : " + e.getMessage());
        }

        // ===== TEST CHEVAUCHEMENT =====
        System.out.println("\nTest chevauchement :");
        ArrayList<ResultatVLSM> reseauxTest = new ArrayList<>();
        reseauxTest.add(new ResultatVLSM("RESEAU1", "192.168.1.0", 25,
                "255.255.255.128", 126, "192.168.1.1", "192.168.1.126"));
        reseauxTest.add(new ResultatVLSM("RESEAU2", "192.168.1.64", 26,
                "255.255.255.192", 62, "192.168.1.65", "192.168.1.126"));
        try {
            validateur.verifierChevauchements(reseauxTest);
        } catch (ChevauchementReseauException e) {
            System.out.println("Erreur detectee : " + e.getMessage());
        }

        // ===== TEST CONFLIT VLAN =====
        System.out.println("\nTest conflit VLAN :");
        GestionnaireVLAN gestionnaire = new GestionnaireVLAN();
        try {
            VLAN vlan10 = new VLAN(10, "ADMINISTRATION", resultats.get(0), "VLAN Administration");
            VLAN vlan20 = new VLAN(20, "TECHNIQUE", resultats.get(1), "VLAN Technique");
            VLAN vlan10Erreur = new VLAN(10, "WIFI", resultats.get(2), "VLAN WiFi ID deja utilise");
            gestionnaire.ajouterVLAN(vlan10);
            gestionnaire.ajouterVLAN(vlan20);
            gestionnaire.ajouterVLAN(vlan10Erreur);
        } catch (ConflitVLANException e) {
            System.out.println("Erreur VLAN : " + e.getMessage());
        }

        // ===== TEST RESEAU INSUFFISANT =====
        System.out.println("\nTest reseau insuffisant :");
        ArrayList<BesoinReseau> besoinsTrop = new ArrayList<>();
        besoinsTrop.add(new BesoinReseau("SERVICE1", 100));
        besoinsTrop.add(new BesoinReseau("SERVICE2", 100));
        besoinsTrop.add(new BesoinReseau("SERVICE3", 100));
        try {
            moteur.verifierCapacite("192.168.1.0", 24, besoinsTrop);
        } catch (ReseauInsuffisantException e) {
            System.out.println("Erreur detectee : " + e.getMessage());
        }
    }
}
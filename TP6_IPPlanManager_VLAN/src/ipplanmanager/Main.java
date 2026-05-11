
package ipplanmanager;
import java.util.ArrayList;

/**
 *
 * @author Nono
 */
public class Main {
    public static void main(String[] args) {

        MoteurVLSM moteur = new MoteurVLSM();

        // ===== SCENARIO 1 : Entreprise =====
        System.out.println("===== SCENARIO 1 : Entreprise =====");
        ArrayList<BesoinReseau> besoins1 = new ArrayList<>();
        besoins1.add(new BesoinReseau("TECHNIQUE", 120));
        besoins1.add(new BesoinReseau("WIFI", 80));
        besoins1.add(new BesoinReseau("ADMINISTRATION", 50));
        besoins1.add(new BesoinReseau("SERVEURS", 20));

        ArrayList<ResultatVLSM> resultats1 = moteur.genererPlan("192.168.1.0", besoins1);
        GestionnaireVLAN gestionnaire1 = new GestionnaireVLAN();
        int numeroVLAN = 10;
        for (ResultatVLSM resultat : resultats1) {
            gestionnaire1.ajouterVLAN(new VLAN(numeroVLAN, resultat.getNomBesoin(),
                    resultat, "VLAN du service " + resultat.getNomBesoin()));
            numeroVLAN += 10;
        }

        gestionnaire1.afficherTousLesVLANs();
        System.out.println("Nombre total de VLANs : " + gestionnaire1.obtenirNombreVLANs());
        gestionnaire1.afficherVLANsCritiques();

        // ===== SCENARIO 2 : Universite =====
        System.out.println("\n===== SCENARIO 2 : Universite =====");
        ArrayList<BesoinReseau> besoins2 = new ArrayList<>();
        besoins2.add(new BesoinReseau("ETUDIANTS", 500));
        besoins2.add(new BesoinReseau("ENSEIGNANTS", 120));
        besoins2.add(new BesoinReseau("LABORATOIRES", 60));
        besoins2.add(new BesoinReseau("WIFI_PUBLIC", 200));
        besoins2.add(new BesoinReseau("SERVEURS", 30));

        ArrayList<ResultatVLSM> resultats2 = moteur.genererPlan("172.16.0.0", besoins2);
        GestionnaireVLAN gestionnaire2 = new GestionnaireVLAN();
        numeroVLAN = 10;
        for (ResultatVLSM resultat : resultats2) {
            gestionnaire2.ajouterVLAN(new VLAN(numeroVLAN, resultat.getNomBesoin(),
                    resultat, "VLAN du service " + resultat.getNomBesoin()));
            numeroVLAN += 10;
        }

        gestionnaire2.afficherTousLesVLANs();
        System.out.println("Nombre total de VLANs : " + gestionnaire2.obtenirNombreVLANs());
        gestionnaire2.afficherVLANsCritiques();
    }
}



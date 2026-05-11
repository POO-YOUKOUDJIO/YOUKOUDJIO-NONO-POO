
package ipplanmanager;
import java.util.ArrayList;

/**
 *
 * @author Nono
 */
public class Main {
    public static void main(String[] args) {
        MoteurVLSM moteur = new MoteurVLSM();

        // ===== SCENARIO 1 : Entreprise principale =====
        System.out.println("===== SCENARIO 1 : Entreprise principale =====");
        ArrayList<BesoinReseau> besoins1 = new ArrayList<>();
        besoins1.add(new BesoinReseau("TECHNIQUE", 120));
        besoins1.add(new BesoinReseau("WIFI", 80));
        besoins1.add(new BesoinReseau("ADMINISTRATION", 50));
        besoins1.add(new BesoinReseau("SERVEURS", 20));
        besoins1.add(new BesoinReseau("DIRECTION", 10));

        System.out.println("\nBesoins exprimes :");
        for (BesoinReseau b : besoins1) b.afficher();

        System.out.println("\nPlan d'adressage propose :");
        for (ResultatVLSM r : moteur.genererPlan("192.168.1.0", besoins1)) r.afficher();

        // ===== SCENARIO 2 : Petite entreprise =====
        System.out.println("\n===== SCENARIO 2 : Petite entreprise =====");
        ArrayList<BesoinReseau> besoins2 = new ArrayList<>();
        besoins2.add(new BesoinReseau("ADMIN", 25));
        besoins2.add(new BesoinReseau("COMPTABILITE", 12));
        besoins2.add(new BesoinReseau("WIFI_INVITES", 40));
        besoins2.add(new BesoinReseau("SERVEURS", 8));

        System.out.println("\nBesoins exprimes :");
        for (BesoinReseau b : besoins2) b.afficher();

        System.out.println("\nPlan d'adressage propose :");
        for (ResultatVLSM r : moteur.genererPlan("192.168.10.0", besoins2)) r.afficher();

        // ===== SCENARIO 3 : Campus =====
        System.out.println("\n===== SCENARIO 3 : Campus =====");
        ArrayList<BesoinReseau> besoins3 = new ArrayList<>();
        besoins3.add(new BesoinReseau("ETUDIANTS", 500));
        besoins3.add(new BesoinReseau("PERSONNEL", 120));
        besoins3.add(new BesoinReseau("LABORATOIRE", 60));
        besoins3.add(new BesoinReseau("ADMINISTRATION", 40));
        besoins3.add(new BesoinReseau("WIFI_PUBLIC", 200));

        System.out.println("\nBesoins exprimes :");
        for (BesoinReseau b : besoins3) b.afficher();

        System.out.println("\nPlan d'adressage propose :");
        for (ResultatVLSM r : moteur.genererPlan("172.16.0.0", besoins3)) r.afficher();
    }
}


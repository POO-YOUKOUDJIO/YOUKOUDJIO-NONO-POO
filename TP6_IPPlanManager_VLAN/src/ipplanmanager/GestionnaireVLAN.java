
package ipplanmanager;
import java.util.ArrayList;

/**
 *
 * @author NONO
 */
public class GestionnaireVLAN {
    private ArrayList<VLAN> vlans;

    public GestionnaireVLAN() {
        vlans = new ArrayList<>();
    }

    public void ajouterVLAN(VLAN vlan) {
        vlans.add(vlan);
    }

    public void afficherTousLesVLANs() {
        for (VLAN vlan : vlans) {
            vlan.afficher();
            System.out.println();
        }
    }

    public VLAN rechercherVLAN(int id) {
        for (VLAN vlan : vlans) {
            if (vlan.getId() == id) {
                return vlan;
            }
        }
        return null;
    }

    public int obtenirNombreVLANs() {
        return vlans.size();
    }

    public void afficherVLANsCritiques() {
        System.out.println("===== VLANs CRITIQUES (capacite > 100 hotes) =====");
        for (VLAN vlan : vlans) {
            if (vlan.getReseauAssocie().getCapacite() > 100) {
                System.out.println("VLAN critique detecte :");
                System.out.println("VLAN " + vlan.getId()
                        + " - " + vlan.getNom()
                        + " - " + vlan.getReseauAssocie().getCapacite()
                        + " hotes");
            }
        }
    }
}
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
public class GestionnaireVLAN {
    private ArrayList<VLAN> vlans;

    public GestionnaireVLAN() {
        vlans = new ArrayList<>();
    }

    public void ajouterVLAN(VLAN vlan) throws ConflitVLANException {
        if (vlan == null) {
            return;
        }
        for (VLAN v : vlans) {
            if (v.getId() == vlan.getId()) {
                throw new ConflitVLANException("Conflit VLAN : l'identifiant "
                        + vlan.getId() + " est deja utilise.");
            }
        }
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
    public ArrayList<VLAN> getVlans() {
        return vlans;
    }
}

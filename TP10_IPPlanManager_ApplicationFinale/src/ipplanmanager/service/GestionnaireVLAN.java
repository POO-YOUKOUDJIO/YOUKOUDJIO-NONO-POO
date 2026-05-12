package ipplanmanager.service;

import ipplanmanager.model.VLAN;
import ipplanmanager.exception.ConflitVLANException;
import ipplanmanager.exception.ChevauchementReseauException;
import java.util.ArrayList;

public class GestionnaireVLAN {
    private ArrayList<VLAN> vlans = new ArrayList<>();

    // Cette méthode permet à ApplicationIPPlanManager de récupérer la liste
    public ArrayList<VLAN> getListeVLANs() {
        return vlans;
    }

    // Gardons celle-ci pour la compatibilité si besoin
    public ArrayList<VLAN> getVlans() {
        return vlans;
    }

    // AJOUT : Nettoie la liste avant un nouveau calcul (Case 1 ou Case 2)
    public void nettoyerVLANs() {
        this.vlans.clear();
    }

    public void ajouterVLAN(VLAN vlan) throws ConflitVLANException, ChevauchementReseauException {
        if (vlan == null) {
            return;
        }

        for (VLAN v : vlans) {
            if (v.getId() == vlan.getId()) {
                throw new ConflitVLANException("Conflit VLAN : l'identifiant " + vlan.getId() + " est déjà utilisé.");
            }
        }

        vlans.add(vlan);
    }

    public void afficherTousLesVLANs() {
        for (VLAN vlan : vlans) {
            vlan.afficher();
            System.out.println("-----------------------------------");
        }
    }

    public void afficherVLANsCritiques() {
        System.out.println("VLANs critiques détectés (> 100 hôtes) :");
        for (VLAN vlan : vlans) {
            if (vlan.getReseauAssocie() != null && vlan.getReseauAssocie().getCapaciteHotes() > 100) {
                System.out.println("VLAN " + vlan.getId() + " - " + vlan.getNom() + " - " + vlan.getReseauAssocie().getCapaciteHotes() + " hôtes");
            }
        }
    }

    public VLAN trouverPlusGrandeCapacite() {
        if (vlans.isEmpty()) return null;
        VLAN plusGrand = vlans.get(0);
        for (VLAN vlan : vlans) {
            if (vlan.getReseauAssocie() != null && plusGrand.getReseauAssocie() != null) {
                if (vlan.getReseauAssocie().getCapaciteHotes() > plusGrand.getReseauAssocie().getCapaciteHotes()) {
                    plusGrand = vlan;
                }
            }
        }
        return plusGrand;
    }

    public VLAN rechercherVLAN(int id) {
        for (VLAN vlan : vlans) {
            if (vlan.getId() == id) return vlan;
        }
        return null;
    }

    public int obtenirNombreVLANs() {
        return vlans.size();
    }
}
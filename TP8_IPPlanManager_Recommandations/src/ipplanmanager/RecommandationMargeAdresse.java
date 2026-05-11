/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipplanmanager;

/**
 *
 * @author laura
 */
public class RecommandationMargeAdresse implements RegleRecommandation {
    @Override
    public Recommandation analyser(VLAN vlan) {
        if (vlan.getReseauAssocie() != null) {
            int capacite = vlan.getReseauAssocie().getCapacite();
            int hotesDemandes = vlan.getReseauAssocie().getHotesDemandes();
            int marge = capacite - hotesDemandes;
            if (marge < 10) {
                return new Recommandation(
                    "Marge d'adresses insuffisante",
                    "MOYENNE",
                    "Le VLAN " + vlan.getNom() + " a une marge de seulement "
                    + marge + " hotes. Prevoyez un sous-reseau plus grand."
                );
            }
        }
        return null;
    }
}


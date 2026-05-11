/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipplanmanager;

/**
 *
 * @author laura
 */
public class RecommandationAdministration implements RegleRecommandation {
    @Override
    public Recommandation analyser(VLAN vlan) {
        if (vlan.getNom().toUpperCase().contains("ADMIN")) {
            return new Recommandation(
                    "Restriction du VLAN Administration",
                    "ELEVEE",
                    "Le VLAN " + vlan.getNom() + " doit etre accessible aux administrateurs reseau uniquement."
            );
        }
        return null;
    }   
}

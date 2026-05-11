/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipplanmanager;

/**
 *
 * @author laura
 */
public class AdresseIP {
        private String valeur;
     
    public AdresseIP(String valeur) {
         setValeur(valeur);
     }
    
    public String getValeur() {
    return valeur;
     }
    
    public void setValeur(String valeur) {
        if (valeur == null || valeur.isEmpty()) {
            System.out.println("Erreur : adresse IP invalide.");
            this.valeur = "0.0.0.0";
        } else {
            this.valeur = valeur;
        }
    }
    
    public void afficher() {
        System.out.println("Adresse IP : " + valeur);
    }
    
}

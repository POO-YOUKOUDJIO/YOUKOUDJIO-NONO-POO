
package ipplanmanager;

/**
 *
 * @author Nono Sarah
 */
public class ReseauIP {
    String adresseReseau;
    int masqueCidr;
    String description;
    public ReseauIP(String adresseReseau, int masqueCidr, String description){
        this.adresseReseau=adresseReseau;
        this.masqueCidr=masqueCidr;
        this.description=description;
    }
    public void afficher(){
        System.out.println("Réseau : "+ adresseReseau + "/" + masqueCidr);      System.out.println("Description : "+ description); 
 
    System.out.println("Classe réseau : " 
            + CalculateurReseau.obtenirClasseReseau(adresseReseau));  
    System.out.println("Masque décimal : " 
            + CalculateurReseau.obtenirMasqueDecimal(masqueCidr));  
    System.out.println("Capacité maximale : " 
            + CalculateurReseau.calculerNombreHotes(masqueCidr) 
            + " hôtes"); 
    String type = CalculateurReseau.estReseauPrive(adresseReseau) ? "Privé" : "Public" ;
    System.out.println("Type d’adresse : "  + type) ;
    System.out.println("-------------------------------------------") ;

} 
 

}

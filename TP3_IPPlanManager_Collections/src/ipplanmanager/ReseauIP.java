
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
        System.out.println("Reseau : " + adresseReseau + "/" + masqueCidr);
        System.out.println("Descrition :" + description);
    }
}

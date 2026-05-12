
import java.util.ArrayList;

public class MS implements Appelable {

    protected String nom;
    protected String prenom;
    protected String motDePasse;
    protected String msisdn;
    protected String imsi;

    protected ArrayList<String> appelsRecus;

    public MS(String nom, String prenom, String motDePasse, String msisdn, String imsi) {
        this.nom = nom;
        this.prenom = prenom;
        this.motDePasse = motDePasse;
        this.msisdn = msisdn;
        this.imsi = imsi;

        appelsRecus = new ArrayList<>();
    }

    public void afficherInfos() {
        System.out.println("Nom : " + nom);
        System.out.println("Prénom : " + prenom);
        System.out.println("MSISDN : " + msisdn);
        System.out.println("IMSI : " + imsi);
    }

    public boolean peutSattacher(BTS bts) {
        return !bts.estSature();
    }

    @Override
    public void appeler(MS destinataire) {
        destinataire.appelsRecus.add("Appel reçu de " + this.nom + " " + this.prenom);
        System.out.println(this.nom + " appelle " + destinataire.nom);
    }

    public void afficherAppelsRecus() {
        System.out.println("Appels reçus par " + nom);

        for (String appel : appelsRecus) {
            System.out.println(appel);
        }
    }

    public String getMsisdn() {
        return msisdn;
    }

    public String getNom() {
        return nom;
    }
}
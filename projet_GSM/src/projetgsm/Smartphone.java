
public class Smartphone extends MS {

    private String systeme;

    public Smartphone(String nom, String prenom, String motDePasse,
                      String msisdn, String imsi, String systeme) {

        super(nom, prenom, motDePasse, msisdn, imsi);
        this.systeme = systeme;
    }

    @Override
    public void afficherInfos() {
        super.afficherInfos();
        System.out.println("Système : " + systeme);
    }
}

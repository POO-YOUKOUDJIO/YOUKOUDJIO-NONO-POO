
import java.util.ArrayList;

public class BTS {

    private int numero;
    private String emplacement;
    private double hauteur;
    private String typeMilieu;
    private double rayonCouverture;
    private double puissanceEmission;
    private int nombreMaxUtilisateurs;

    private ArrayList<MS> utilisateurs;

    public BTS(int numero, String emplacement, double hauteur,
               String typeMilieu, double rayonCouverture,
               double puissanceEmission, int nombreMaxUtilisateurs) {

        this.numero = numero;
        this.emplacement = emplacement;
        this.hauteur = hauteur;
        this.typeMilieu = typeMilieu;
        this.rayonCouverture = rayonCouverture;
        this.puissanceEmission = puissanceEmission;
        this.nombreMaxUtilisateurs = nombreMaxUtilisateurs;

        utilisateurs = new ArrayList<>();
    }

    public void ajouterMS(MS ms) throws Exception {

        if (estSature()) {
            throw new Exception("BTS saturée");
        }

        utilisateurs.add(ms);
    }

    public void supprimerMS(String msisdn) {

        utilisateurs.removeIf(ms -> ms.getMsisdn().equals(msisdn));
    }

    public MS rechercherMS(String msisdn) {

        for (MS ms : utilisateurs) {
            if (ms.getMsisdn().equals(msisdn)) {
                return ms;
            }
        }

        return null;
    }

    public boolean estSature() {
        return utilisateurs.size() >= nombreMaxUtilisateurs;
    }

    public void afficherInfos() {

        System.out.println("Numéro BTS : " + numero);
        System.out.println("Emplacement : " + emplacement);
        System.out.println("Type milieu : " + typeMilieu);
        System.out.println("Nombre utilisateurs : " + utilisateurs.size());
    }

    public int getNombreUtilisateurs() {
        return utilisateurs.size();
    }

    public String getEmplacement() {
        return emplacement;
    }
}
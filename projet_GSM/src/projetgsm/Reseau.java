
import java.util.ArrayList;

public class Reseau {

    private String nom;
    private double bandeUplink;
    private double bandeDownlink;
    private String typeAcces;
    private double debitMaxUplink;
    private double debitMaxDownlink;
    private double delaiMax;

    private ArrayList<BTS> listeBTS;

    public Reseau(String nom, double bandeUplink,
                  double bandeDownlink, String typeAcces,
                  double debitMaxUplink,
                  double debitMaxDownlink,
                  double delaiMax) {

        this.nom = nom;
        this.bandeUplink = bandeUplink;
        this.bandeDownlink = bandeDownlink;
        this.typeAcces = typeAcces;
        this.debitMaxUplink = debitMaxUplink;
        this.debitMaxDownlink = debitMaxDownlink;
        this.delaiMax = delaiMax;

        listeBTS = new ArrayList<>();
    }

    public void ajouterBTS(BTS bts) {
        listeBTS.add(bts);
    }

    public void supprimerBTS(BTS bts) {
        listeBTS.remove(bts);
    }

    public BTS rechercherBTS(String emplacement) {

        for (BTS bts : listeBTS) {
            if (bts.getEmplacement().equalsIgnoreCase(emplacement)) {
                return bts;
            }
        }

        return null;
    }

    public int nombreBTS() {
        return listeBTS.size();
    }

    public int nombreAbonnes() {

        int total = 0;

        for (BTS bts : listeBTS) {
            total += bts.getNombreUtilisateurs();
        }

        return total;
    }

    public void afficherPerformances() {

        System.out.println("Nom réseau : " + nom);
        System.out.println("Débit UL : " + debitMaxUplink);
        System.out.println("Débit DL : " + debitMaxDownlink);
        System.out.println("Délai max : " + delaiMax);
    }
}
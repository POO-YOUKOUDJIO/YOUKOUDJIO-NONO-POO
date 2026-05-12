
public class TestReseau {

    public static void main(String[] args) {

        Reseau reseau = new Reseau(
                "Orange GSM",
                900,
                1800,
                "TDMA",
                50,
                100,
                10
        );

        BTS bts1 = new BTS(
                1,
                "Yaoundé",
                30,
                "Urbain",
                5,
                20,
                2
        );

        reseau.ajouterBTS(bts1);

        MS ms1 = new Smartphone(
                "Jonathan",
                "GuiGui",
                "1234",
                "690000001",
                "IMSI001",
                "Android"
        );

        MS ms2 = new Smartphone(
                "Kevin",
                "Junior",
                "5678",
                "690000002",
                "IMSI002",
                "iOS"
        );

        try {

            bts1.ajouterMS(ms1);
            bts1.ajouterMS(ms2);

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }

        ms1.appeler(ms2);

        System.out.println();

        ms2.afficherAppelsRecus();

        System.out.println();

        bts1.afficherInfos();

        System.out.println();

        reseau.afficherPerformances();

        System.out.println();

        System.out.println("Nombre total abonnés : " + reseau.nombreAbonnes());
    }
}
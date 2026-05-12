package ipplanmanager.service;

import ipplanmanager.exception.AdresseIPInvalideException;

public class CalculateurReseau {

    public static int convertirIpEnEntier(String adresseIp) {
        String[] octets = adresseIp.split("\\.");
        int resultat = 0;
        for (int i = 0; i < 4; i++) {
            resultat |= (Integer.parseInt(octets[i]) << (24 - (8 * i)));
        }
        return resultat;
    }

    public static String convertirEntierEnIp(int ipEntier) {
        return String.format("%d.%d.%d.%d",
                (ipEntier >> 24) & 0xFF,
                (ipEntier >> 16) & 0xFF,
                (ipEntier >> 8) & 0xFF,
                ipEntier & 0xFF);
    }

    public static int calculerCidrPourHotes(int nombreHotes) {
        int bitsHotes = (int) Math.ceil(Math.log(nombreHotes + 2) / Math.log(2));
        return 32 - bitsHotes;
    }

    public static int calculerNombreHotes(int cidr) {
        return (int) Math.pow(2, 32 - cidr) - 2;
    }

    public static int calculerTailleBloc(int cidr) {
        return 1 << (32 - cidr);
    }

    public static String obtenirMasqueDecimal(int cidr) {
        int masque = 0xFFFFFFFF << (32 - cidr);
        return convertirEntierEnIp(masque);
    }

    public static String obtenirClasseReseau(String adresseIp) {
        String[] octets = adresseIp.split("\\.");
        int premierOctet = Integer.parseInt(octets[0]);
        if (premierOctet >= 1 && premierOctet <= 126) return "Classe A";
        if (premierOctet >= 128 && premierOctet <= 191) return "Classe B";
        if (premierOctet >= 192 && premierOctet <= 223) return "Classe C";
        return "Classe Spéciale";
    }

    public static boolean reseauxSeChevauchent(String ip1, int cidr1, String ip2, int cidr2) {
        int addr1 = convertirIpEnEntier(ip1);
        int addr2 = convertirIpEnEntier(ip2);
        int taille1 = calculerTailleBloc(cidr1);
        int taille2 = calculerTailleBloc(cidr2);
        return (addr1 < addr2 + taille2) && (addr2 < addr1 + taille1);
    }

    public static void verifierAdresseIP(String ip) throws AdresseIPInvalideException {
        if (ip == null || !ip.matches("^(\\d{1,3}\\.){3}\\d{1,3}$")) {
            throw new AdresseIPInvalideException("Format IP invalide : " + ip);
        }
        String[] octets = ip.split("\\.");
        for (String octet : octets) {
            int valeur = Integer.parseInt(octet);
            if (valeur < 0 || valeur > 255) {
                throw new AdresseIPInvalideException("Octet hors limite : " + valeur);
            }
        }
    }
}
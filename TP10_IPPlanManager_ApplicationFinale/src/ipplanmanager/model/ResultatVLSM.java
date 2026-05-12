package ipplanmanager.model;

import ipplanmanager.service.CalculateurReseau;

public class ResultatVLSM {
    private String nomBesoin;
    private String adresseReseau;
    private int cidr;
    private int capaciteHotes;
    private int hotesDemandes;

    public ResultatVLSM(String nomBesoin, String adresseReseau, int cidr, int capaciteHotes, int hotesDemandes) {
        this.nomBesoin = nomBesoin;
        this.adresseReseau = adresseReseau;
        this.cidr = cidr;
        this.capaciteHotes = capaciteHotes;
        this.hotesDemandes = hotesDemandes;
    }

    public String getNomBesoin() { return nomBesoin; }
    public String getAdresseReseau() { return adresseReseau; }
    public int getCidr() { return cidr; }
    public int getCapaciteHotes() { return capaciteHotes; }
    public int getHotesDemandes() { return hotesDemandes; }

    // AJOUT : Pour calculer le masque (ex: 255.255.255.0) à partir du CIDR
    public String getMasqueDecimal() {
        return CalculateurReseau.obtenirMasqueDecimal(this.cidr);
    }

    public int getMarge() {
        return capaciteHotes - hotesDemandes;
    }

    public void afficher() {
        System.out.println("-------------------------------------------");
        System.out.println("  > Nom : " + nomBesoin);
        System.out.println("  > Sous-réseau : " + adresseReseau + "/" + cidr);
        System.out.println("  > Masque : " + getMasqueDecimal());
        System.out.println("  > Capacité : " + capaciteHotes + " hôtes (Demandés : " + hotesDemandes + ")");
        System.out.println("  > Marge : " + getMarge() + " adresses libres");
    }
}
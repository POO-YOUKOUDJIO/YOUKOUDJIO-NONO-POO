/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipplanmanager;

/**
 *
 * @author laura
 */
public class ResultatVLSM {
    private String nomBesoin;
    private String adresseReseau;
    private int cidr;
    private String masqueDecimal;
    private int capacite;
    private String premiereAdresseUtilisable;
    private String derniereAdresseUtilisable;
    private int hotesDemandes;

    public ResultatVLSM(String nomBesoin, String adresseReseau, int cidr,
                        String masqueDecimal, int capacite,
                        String premiereAdresseUtilisable, String derniereAdresseUtilisable) {
        this.nomBesoin = nomBesoin;
        this.adresseReseau = adresseReseau;
        this.cidr = cidr;
        this.masqueDecimal = masqueDecimal;
        this.capacite = capacite;
        this.premiereAdresseUtilisable = premiereAdresseUtilisable;
        this.derniereAdresseUtilisable = derniereAdresseUtilisable;
        this.hotesDemandes = 0;
    }

    public String getNomBesoin() { return nomBesoin; }
    public String getAdresseReseau() { return adresseReseau; }
    public int getCidr() { return cidr; }
    public int getCapacite() { return capacite; }
    public int getHotesDemandes() { return hotesDemandes; }
    public void setHotesDemandes(int hotesDemandes) { this.hotesDemandes = hotesDemandes; }

    public void afficher() {
        System.out.println(nomBesoin + " -> " + adresseReseau + "/" + cidr
                + " | Plage : " + premiereAdresseUtilisable
                + " - " + derniereAdresseUtilisable
                + " | Capacite : " + capacite + " hotes");
    }
}



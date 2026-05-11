/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipplanmanager;
/**
 *
 * @author NONO Sarah
 */
class Main { 
    public static void main(String[] args) { 
        System.out.println("===== IPPlan-Manager : TP1 =====");       
        System.out.println("Découverte des premières classes du projet"); 
        System.out.println(); 
 
        AdresseIP ipRouteur = new AdresseIP("192.168.1.1"); 
        AdresseIP ipServeur = new AdresseIP("192.168.1.10"); 
        AdresseIP ipClient = new AdresseIP("192.168.1.50"); 
 
        InterfaceReseau interfaceRouteur = new InterfaceReseau("eth0", ipRouteur); 
        InterfaceReseau interfaceServeur = new InterfaceReseau("eth0", ipServeur); 
        InterfaceReseau interfaceClient = new InterfaceReseau("wlan0", ipClient); 
 
        interfaceRouteur.activer();         interfaceServeur.activer(); 
 
        Equipement routeur = new Equipement("R1_EDGE", "Routeur", interfaceRouteur); 
        Equipement serveur = new Equipement("SRV_DNS", "Serveur", interfaceServeur); 
        Equipement client = new Equipement("PC_ADMIN", "Poste client", interfaceClient); 
 
        ReseauIP reseauPrincipal = new ReseauIP( 
                "192.168.1.0", 
                24, 
                "Réseau principal du laboratoire IRT" 
        ); 
 
        System.out.println("----- Réseau créé -----");         reseauPrincipal.afficher(); 
 
        System.out.println(); 
        System.out.println("----- Équipements créés -----"); 
 
        System.out.println();         routeur.afficher(); 
 
        System.out.println();         serveur.afficher(); 
 
        System.out.println();         client.afficher(); 
       // 1. Un deuxième réseau
ReseauIP reseauSecondaire=new ReseauIP("192.168.2.0", 24, "Réseau de management");

// 2. Un Switch et un Point d’accès WiFi
InterfaceReseau intSwitch=new InterfaceReseau("Port1", null);
// Interface sans IP
Equipement mySwitch=new Equipement("SW-CORE", "Switch", intSwitch);

InterfaceReseau intAP=new InterfaceReseau("Giga0/1", new AdresseIP("10.0.0.2"));
intAP.activer();
Equipement apWifi=new Equipement("AP-LOUNGE", "Point d’accès WiFi", intAP);

// 3. Un poste client supplémentaire avec interface inactive
InterfaceReseau intClient2=new InterfaceReseau("eth1", new AdresseIP("192.168.1.51"));
// On ne l’active pas pour tester l’état « inactive »
Equipement client2=new Equipement("PC-STAGE", "Poste client", intClient2) ;

// Affichage des nouveaux objets
System.out.println("\nNouveaux objets ajoutés ");
reseauSecondaire.afficher();
mySwitch.afficher();
apWifi.afficher();
client2.afficher(); //envoi dt tp
 
    }
} 

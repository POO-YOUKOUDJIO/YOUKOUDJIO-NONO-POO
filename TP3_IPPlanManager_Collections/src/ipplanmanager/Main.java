package ipplanmanager; 
 

/**
 *
 * @author Nono Sarah
 */
public class Main {
    public static void main(String[] args) { 
 
        InfrastructureReseau infrastructure = 
                new InfrastructureReseau("Infrastructure YFY"); 
        ReseauIP reseauAdmin =              
                new ReseauIP(                    
                        "192.168.1.0", 
                        24, 
                        "Réseau administration" 
                ); 
        ReseauIP reseauTech =              
                new ReseauIP(                
                        "192.168.2.0", 
                        24, 
                        "Réseau technique" 
                ); 
        SousReseau admin =           
                new SousReseau(              
                        "ADMIN",                  
                        reseauAdmin 
                ); 
        SousReseau tech =               
                new SousReseau(              
                        "TECH",                      
                        reseauTech 
                ); 
        infrastructure.ajouterSousReseau(admin);      
        infrastructure.ajouterSousReseau(tech); 
 
        AdresseIP ip1 = new AdresseIP("192.168.1.1"); 
        AdresseIP ip2 = new AdresseIP("10.0.0.1"); 
 
        InterfaceReseau eth0 = new InterfaceReseau("eth0", ip1); 
 
        InterfaceReseau eth1 = new InterfaceReseau("eth1", ip2); 
 
        eth0.activer();       
        eth1.activer(); 
 
        Equipement routeur = new Equipement( 
                        "R1_EDGE", 
                        "Routeur" 
                ); 
 
        routeur.ajouterInterface(eth0);     
        routeur.ajouterInterface(eth1); 
 
        infrastructure.ajouterEquipement(routeur); 
 
       
        
           // --- Étape 14 : Ajouts d’objets ---
        
        // Ajout du sous-réseau WiFi
        ReseauIP reseauWifi = new ReseauIP("192.168.3.0", 24, "Réseau WiFi") ;
        SousReseau wifi = new SousReseau("WIFI", reseauWifi) ;
        infrastructure.ajouterSousReseau(wifi) ;

        // Ajout du Serveur (2 interfaces)
        Equipement serveur = new Equipement("SRV-DATA", "Serveur") ;
        serveur.ajouterInterface(new InterfaceReseau("eth0", new AdresseIP("192.168.1.10"))) ;
        serveur.ajouterInterface(new InterfaceReseau("eth1", new AdresseIP("10.0.0.10"))) ;
        infrastructure.ajouterEquipement(serveur) ;

        // Ajout du Switch
        Equipement switchPrincipal = new Equipement("SW-CORE", "Switch") ;
        switchPrincipal.ajouterInterface(new InterfaceReseau("vlan1", new AdresseIP("192.168.1.2"))) ;
        infrastructure.ajouterEquipement(switchPrincipal) ;

        // Ajout de 3 PC supplémentaires
        for(int i = 1 ; i <= 3 ; i++) {
            Equipement pc = new Equipement("PC-Client-" + i, "Poste de travail") ;
            pc.ajouterInterface(new InterfaceReseau("eth0", new AdresseIP("192.168.3." + (20 + i)))) ;
            infrastructure.ajouterEquipement(pc) ;
        }

        // --- Étape 15 : Test de la recherche ---
        System.out.println("\n--- TEST DE RECHERCHE ---") ;
        infrastructure.rechercherEquipement("SRV-DATA") ; 
        infrastructure.rechercherEquipement("Inconnu") ;

        // Affichage final
        System.out.println("\n--- INFRASTRUCTURE COMPLÈTE ---") ;
        infrastructure.afficher() ;


     
    } 

}

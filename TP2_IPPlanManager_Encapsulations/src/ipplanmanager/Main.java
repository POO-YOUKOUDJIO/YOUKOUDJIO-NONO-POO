package ipplanmanager;
/**
 *
 * @author Nono Sarah
 */
public class Main { 
 
    public static void main(String[] args) { 
        System.out.println("===== TP2 : Encapsulation ====="); 
        AdresseIP ip1 = new AdresseIP("192.168.1.1"); 
        AdresseIP ip2 = new AdresseIP(""); 
        AdresseIP ip3 = new AdresseIP(null); 
        InterfaceReseau interface1 = new InterfaceReseau("eth0", ip1); 
        InterfaceReseau interface2 = new InterfaceReseau("", ip2); 
 
        interface1.activer(); 
        Equipement routeur = new Equipement(   
                "R1_EDGE",                      
                "Routeur",                       
                interface1 
                ); 
        Equipement serveur =            
                new Equipement( 
                        "", 
                        "",                  
                        interface2 
                ); 
 
        ReseauIP reseau1 =              
                new ReseauIP( 
                        "192.168.1.0", 
                        24, 
                        "Réseau principal" 
                ); 
 
        ReseauIP reseau2 =             
                new ReseauIP(                
                        "", 
                        55, 
                        "" 
                ); 
 
        System.out.println(); 
        System.out.println("----- Réseau 1 -----");      
        reseau1.afficher(); 
 
        System.out.println(); 
        System.out.println("----- Réseau 2 -----");       
        reseau2.afficher(); 
 
        System.out.println(); 
        System.out.println("----- Équipement 1 -----");     
        routeur.afficher(); 
 
        System.out.println(); 
        System.out.println("----- Équipement 2 -----");      
        serveur.afficher(); 
      
       // --- ÉTAPE 13 : AJOUT D’ÉQUIPEMENTS ---
       // Il faut d’abord créer les adresses et interfaces
       AdresseIP ip4=new AdresseIP("192.168.2.2") ;
       InterfaceReseau eth0 = new InterfaceReseau("eth1", ip4) ;
    
        // Déclaration des objets qui étaient en rouge
       Equipement Routeur=new Equipement("R1", "Routeur", eth0) ;
       Equipement Serveur=new Equipement("SRV-1", "Serveur", null) ;
       Equipement client2=new Equipement("PC-02", "Client", new InterfaceReseau("eth1", new AdresseIP("192.168.2.50"))) ;

        // 4. Test d’un équipement avec une interface vide
       InterfaceReseau intVide=new InterfaceReseau("eth2", null) ;
        Equipement serveurStockage=new Equipement("NAS-DATA", "Serveur", intVide) ;
    
        // --- ÉTAPE 14 : TESTS DE MODIFICATION ---
       System.out.println("\n--- Tests de modification ---") ;
       client2.afficher() ; 
    
       System.out.println("Activation manuelle du Client 2…") ;
       // Correction : on passe par le getter de l’équipement pour activer l’interface
       client2.getInterfacePrincipale().activer() ; 
    
        // --- AFFICHAGE FINAL ---
       System.out.println("\n===== ÉTAT FINAL DU PARC INFORMATIQUE =====") ;
       Routeur.afficher() ;
       Serveur.afficher() ;
       client2.afficher() ;
       serveurStockage.afficher() ;

       // Test de la méthode locale (après l’avoir ajoutée dans la classe AdresseIP)
       System.out.println("\n--- Test de localisation IP ---") ;
       System.out.println("L’adresse IP1 est-elle locale ? " + ip1.estAdresseLocale()) ;

 


        }
    }
       
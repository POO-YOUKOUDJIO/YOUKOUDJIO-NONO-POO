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
                new ReseauIP("192.168.1.0",24,"Réseau Administration" 
                ); 
 
        ReseauIP reseauTechnique = new ReseauIP( 
                        "172.16.0.0", 
                        16, 
                        "Réseau Technique" 
                ); 
 
        ReseauIP reseauWifi =              
                new ReseauIP( 
                        "10.0.0.0", 
                        8, 
                        "Réseau WiFi" 
                ); 
 
        SousReseau admin =                
                new SousReseau(                    
                        "ADMIN",                    
                        reseauAdmin              
                ); 
 
        SousReseau tech =               
                new SousReseau(                
                        "TECH",                 
                        reseauTechnique 
                ); 
 
        SousReseau wifi =               
                new SousReseau(                
                        "WIFI",                 
                        reseauWifi 
                ); 
 
        infrastructure.ajouterSousReseau(admin);     
        infrastructure.ajouterSousReseau(tech);       
        infrastructure.ajouterSousReseau(wifi); 
 
        infrastructure.afficher(); 
        InfrastructureReseau infra = new InfrastructureReseau("Infrastructure Complète") ;

    // Test Classe A (Privé)
    ReseauIP r4 = new ReseauIP("10.0.0.0", 8, "Réseau Interne A") ;
    // Test Classe B (Public)
    ReseauIP r5 = new ReseauIP("145.10.0.0", 16, "Réseau Universitaire") ;
    // Test CIDR spécifique (/27)
    ReseauIP r3 = new ReseauIP("192.168.1.32", 27, "Sous-réseau WiFi") ;

    infra.ajouterSousReseau(new SousReseau("SR_A", r4)) ;
    infra.ajouterSousReseau(new SousReseau("SR_B", r5)) ;
    infra.ajouterSousReseau(new SousReseau("SR_WIFI", r3)) ;

    infra.afficher() ;
}

    } 
 


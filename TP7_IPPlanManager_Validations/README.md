 TP7 - Validations avancées et détection des conflits

Etudiante: YOUKOUDJIO NONO Sarah

 Objectif: Ajouter des validations avancées pour détecter les incohérences dans un plan d'adressage.

 Notions étudiées
Exceptions personnalisées, try/catch, throw, validation réseau, détection de chevauchement, conflit VLAN, robustesse logicielle.

 Scénarios testés:

 Cas normal
- TECHNIQUE : 120 hôtes, WIFI : 80 hôtes, ADMINISTRATION : 50 hôtes, SERVEURS : 20 hôtes
- Adresse de départ : 192.168.1.0
- Résultat : validation réussie, aucun conflit détecté

 Test adresse invalide
- Adresse testée : 192.168.300.0
- Résultat : AdresseIPInvalideException déclenchée

 Test chevauchement
- RESEAU1 : 192.168.1.0/25 et RESEAU2 : 192.168.1.64/26
- Résultat : ChevauchementReseauException déclenchée

 Test conflit VLAN
- Deux VLANs avec l'identifiant 10
- Résultat : ConflitVLANException déclenchée

 Test réseau insuffisant
- 3 services de 100 hôtes dans un /24 (254 disponibles)
- Résultat : ReseauInsuffisantException déclenchée


Résultats obtenus:


run:
===== IPPlan-Manager : TP7 - Validations avancees =====

Plan genere :
TECHNIQUE -> 192.168.1.0/25 | Plage : 192.168.1.1 - 192.168.1.126 | Capacite : 126 hotes
WIFI -> 192.168.1.128/25 | Plage : 192.168.1.129 - 192.168.1.254 | Capacite : 126 hotes
ADMINISTRATION -> 192.168.2.0/26 | Plage : 192.168.2.1 - 192.168.2.62 | Capacite : 62 hotes
SERVEURS -> 192.168.2.64/27 | Plage : 192.168.2.65 - 192.168.2.94 | Capacite : 30 hotes
Validation termin�e : aucun conflit critique d�tect�.

Test adresse invalide :
Erreur detectee : Adresse IP invalide : 192.168.300.0

Test chevauchement :
Erreur detectee : Chevauchement d�tect� entre RESEAU1 et RESEAU2

Test conflit VLAN :
Erreur VLAN : Conflit VLAN : l'identifiant 10 est deja utilise.

Test reseau insuffisant :
Erreur detectee : Reseau insuffisant : 300 hotes demandes mais seulement 254 disponibles dans 192.168.1.0/24
BUILD SUCCESSFUL (total time: 1 second)


Difficultés rencontrées:

Les accolades de la méthode estAdresseIPValide() étaient mal placées, ce qui causait des erreurs de compilation. La classe
ReseauInsuffisantException avait extends Exception mal positionné à l'intérieur de la classe au lieu de la déclaration.

19- Réponses aux questions

1. Les validations avancées sont indispensables car un plan incorrect peut provoquer des conflits d'adresses, des pannes réseau ou des erreurs
de routage réelles dans une infrastructure d'entreprise.

2. Une erreur simple est une valeur incorrecte qu'on peut ignorer.
Une exception en Java est un mécanisme formel qui interrompt le flux
normal du programme et force le traitement de l'erreur via try/catch.

3. On crée des exceptions personnalisées pour donner des messages d'erreur précis adaptés au contexte métier (réseau, VLAN, adresse IP)
plutôt que des messages génériques Java.

4. Le bloc try/catch permet d'exécuter du code risqué dans le try, et de récupérer proprement l'erreur dans le catch sans que le programme
ne s'arrête brutalement.

5. Deux VLANs avec le même identifiant provoqueraient des conflits de configuration sur les switches. Les équipements réseau utilisent
l'ID VLAN pour router correctement les trames entre les ports.

6. Deux sous-réseaux qui se chevauchent provoquent des conflits d'adresses IP. Les machines auraient des routes ambiguës et ne
pourraient pas communiquer correctement.

7. On transforme les adresses IP en entiers car les comparaisons de plages réseau nécessitent des opérations arithmétiques. 
Il est impossible de comparer des chaînes de caractères pour détecter
des chevauchements.

8. ValidateurPlanAdressage doit être séparée du moteur VLSM car chaque classe doit avoir une seule responsabilité. Le moteur calcule,
le validateur vérifie. Cette séparation facilite la maintenance et l'évolution du projet.
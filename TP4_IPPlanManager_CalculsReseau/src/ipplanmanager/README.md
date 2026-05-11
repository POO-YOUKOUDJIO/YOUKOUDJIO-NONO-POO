TP4 : Calcul automatique des masques, capacités réseau et premières analyses intelligentes dans IPPlan-Manager 
 
1. Pourquoi a-t-on créé une classe utilitaire ?
Une classe utilitaire regroupe des méthodes de calcul ou de transformation qui ne dépendent pas d’un objet particulier.
👉 Dans ce TP, elle permet de centraliser les fonctions de calcul réseau (CIDR, masques, conversions IP) afin d’éviter la duplication de code et de rendre le projet plus clair et maintenable.

2. Quel est le rôle du mot-clé static ?
Le mot-clé static permet de définir des méthodes ou attributs accessibles sans créer d’instance de la classe.
👉 Exemple : CalculateurReseau.calculerNombreHotes(24) peut être appelé directement, car il s’agit d’un calcul générique qui ne dépend pas d’un objet spécifique.

3. Pourquoi les calculs réseau sont-ils importants dans un outil IPAM ?
Un IPAM (IP Address Management) doit gérer efficacement l’adressage IP.
👉 Les calculs réseau (nombre d’hôtes, masques, sous-réseaux) sont indispensables pour :éviter les conflits d’adresses,optimiser l’utilisation des plages IP,ir la cohérence des plans d’adressage.

4. Quelle est l’utilité du CIDR ?
Le CIDR (Classless Inter-Domain Routing) permet de définir la taille d’un sous-réseau de manière flexible.
👉 Il indique combien de bits sont réservés pour le réseau et combien pour les hôtes.
Cela permet d’adapter précisément un sous-réseau aux besoins exprimés (ex. /26 pour 50 hôtes).

5. Pourquoi le nombre d’hôtes dépend-il du masque réseau ?
Le masque réseau détermine le nombre de bits disponibles pour les adresses des hôtes.Les deux adresses retirées correspondent au réseau et au broadcast.

6. Pourquoi certaines adresses IP sont-elles privées ?
Les adresses IP privées (ex. 192.168.x.x, 10.x.x.x) sont réservées pour les réseaux internes.
👉 Elles ne sont pas routées sur Internet et permettent aux entreprises ou particuliers de créer des réseaux locaux sécurisés sans consommer d’adresses publiques.

7. Pourquoi la séparation entre logique métier et logique de calcul améliore-t-elle le projet ?
La logique métier gère les besoins exprimés par l’utilisateur (ex. nombre d’hôtes par service).

La logique de calcul effectue les opérations techniques (CIDR, masques, conversions).
👉 Cette séparation rend le projet plus lisible, facilite la maintenance et permet de réutiliser les calculs dans différents contextes.

8. Pourquoi les outils de planification réseau doivent-ils automatiser les calculs ?
Dans une infrastructure complexe, effectuer les calculs manuellement serait long et source d’erreurs.
👉 L’automatisation permet : de. gagner du temps, d’éviter les erreurs humaines,de proposer rapidement un plan d’adressage cohérent et optimisé C’est une exigence pour tout outil IPAM professionnel.

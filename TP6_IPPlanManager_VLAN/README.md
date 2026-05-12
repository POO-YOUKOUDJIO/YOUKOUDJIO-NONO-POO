 TP6 - VLAN et segmentation logique

Etudiante: Youkoudjio Nono Sarah

    Objectif
Mettre en place la gestion des VLANs et associer automatiquement les sous-réseaux générés aux VLANs.

 Notions étudiées
Segmentation logique, VLAN, gestionnaires métier, collections, associations entre objets, architecture métier.

 Scénarios testés

Scénario 1 : Entreprise (192.168.1.0)
- TECHNIQUE : 120 hôtes
- WIFI : 80 hôtes
- ADMINISTRATION : 50 hôtes
- SERVEURS : 20 hôtes

 Scénario 2 : Université (172.16.0.0)
- ETUDIANTS : 500 hôtes
- WIFI_PUBLIC : 200 hôtes
- ENSEIGNANTS : 120 hôtes
- LABORATOIRES : 60 hôtes
- SERVEURS : 30 hôtes

 Résultats obtenus

run:

===== SCENARIO 1 : Entreprise =====
VLAN ID : 10
Nom : TECHNIQUE
Description : VLAN du service TECHNIQUE
TECHNIQUE -> 192.168.1.0/25 | Plage : 192.168.1.1 - 192.168.1.126 | Capacite : 126 hotes

VLAN ID : 20
Nom : WIFI
Description : VLAN du service WIFI
WIFI -> 192.168.1.128/25 | Plage : 192.168.1.129 - 192.168.1.254 | Capacite : 126 hotes

VLAN ID : 30
Nom : ADMINISTRATION
Description : VLAN du service ADMINISTRATION
ADMINISTRATION -> 192.168.2.0/26 | Plage : 192.168.2.1 - 192.168.2.62 | Capacite : 62 hotes

VLAN ID : 40
Nom : SERVEURS
Description : VLAN du service SERVEURS
SERVEURS -> 192.168.2.64/27 | Plage : 192.168.2.65 - 192.168.2.94 | Capacite : 30 hotes

Nombre total de VLANs : 4
===== VLANs CRITIQUES (capacite > 100 hotes) =====
VLAN critique detecte :
VLAN 10 - TECHNIQUE - 126 hotes
VLAN critique detecte :
VLAN 20 - WIFI - 126 hotes

===== SCENARIO 2 : Universite =====
VLAN ID : 10
Nom : ETUDIANTS
Description : VLAN du service ETUDIANTS
ETUDIANTS -> 172.16.0.0/23 | Plage : 172.16.0.1 - 172.16.1.254 | Capacite : 510 hotes

VLAN ID : 20
Nom : WIFI_PUBLIC
Description : VLAN du service WIFI_PUBLIC
WIFI_PUBLIC -> 172.16.2.0/24 | Plage : 172.16.2.1 - 172.16.2.254 | Capacite : 254 hotes

VLAN ID : 30
Nom : ENSEIGNANTS
Description : VLAN du service ENSEIGNANTS
ENSEIGNANTS -> 172.16.3.0/25 | Plage : 172.16.3.1 - 172.16.3.126 | Capacite : 126 hotes

VLAN ID : 40
Nom : LABORATOIRES
Description : VLAN du service LABORATOIRES
LABORATOIRES -> 172.16.3.128/26 | Plage : 172.16.3.129 - 172.16.3.190 | Capacite : 62 hotes

VLAN ID : 50
Nom : SERVEURS
Description : VLAN du service SERVEURS
SERVEURS -> 172.16.3.192/27 | Plage : 172.16.3.193 - 172.16.3.222 | Capacite : 30 hotes

Nombre total de VLANs : 5
===== VLANs CRITIQUES (capacite > 100 hotes) =====
VLAN critique detecte :
VLAN 10 - ETUDIANTS - 510 hotes
VLAN critique detecte :
VLAN 20 - WIFI_PUBLIC - 254 hotes
VLAN critique detecte :
VLAN 30 - ENSEIGNANTS - 126 hotes

BUILD SUCCESSFUL (total time: 0 seconds)


 Difficultés rencontrées

La méthode afficherVLANsCritiques() n'avait pas été ajoutée dans GestionnaireVLAN, ce qui causait une erreur dans Main. Elle a été
ajoutée conformément à la section 16 du TP.

    17- Réponses aux questions

1. Les VLANs sont importants car ils permettent de segmenter logiquement le réseau, d'améliorer la sécurité, de réduire les domaines de broadcast
et de contrôler les communications entre départements.

2. Un VLAN est associé à un sous-réseau spécifique car chaque segment logique doit avoir sa propre plage d'adresses IP pour que le routage
entre VLANs soit possible et contrôlé.

3. La séparation logique améliore la sécurité car les machines d'un VLAN ne peuvent pas communiquer directement avec celles d'un autre VLAN sans
passer par un routeur, ce qui permet de filtrer et contrôler les flux.

4. GestionnaireVLAN est une classe de service métier chargée de gérer la collection de VLANs. Elle offre des opérations spécialisées : ajout,
recherche, affichage, et détection des VLANs critiques.

5. La classe VLAN contient un objet ResultatVLSM car un VLAN est directement lié à un sous-réseau calculé par le moteur VLSM. Cela
modélise la relation réelle entre un VLAN et son réseau IP associé.

6. On utilise ArrayList car le nombre de VLANs n'est pas connu à l'avance. ArrayList permet d'ajouter dynamiquement autant de VLANs
que nécessaire sans définir de taille fixe.

7. Les responsabilités des classes doivent être séparées pour respecter le principe de responsabilité unique de la POO. Chaque classe fait une
seule chose bien définie, ce qui facilite la maintenance et l'évolution
du code.

8. Le projet ressemble maintenant à une application professionnelle car il possède une architecture métier claire avec des classes spécialisées
qui collaborent entre elles pour produire un résultat concret et utile.
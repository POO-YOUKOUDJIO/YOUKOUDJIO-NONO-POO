 TP8 - Moteur de recommandations
Etudiante: YOUKOUDJIO NONO Sarah

 Objectif: Ajouter un moteur de recommandations capable d'analyser un plan VLAN et de proposer des conseils techniques.

Notions étudiées:
Interfaces Java, polymorphisme, règles métier, moteur de recommandations, séparation des responsabilités, extensibilité
logicielle.

 Scénarios testés:

 Scénario 1 : Université (10.10.0.0)
- ETUDIANTS : 500 hôtes
- WIFI_INVITES : 200 hôtes
- ENSEIGNANTS : 120 hôtes
- LABORATOIRES : 60 hôtes
- SERVEURS : 30 hôtes

 Scénario 2 : Entreprise (192.168.1.0)
- ADMINISTRATION : 50 hôtes
- WIFI_INVITES : 120 hôtes
- SERVEURS : 20 hôtes
- CAMERAS : 80 hôtes
- VOIP : 60 hôtes

 Recommandations obtenues:

 Scénario 1
- [MOYENNE] VLAN de grande taille : ETUDIANTS (510 hotes)
- [ELEVEE] Isolation du WiFi : WIFI_INVITES
- [MOYENNE] VLAN de grande taille : WIFI_INVITES (254 hotes)
- [ELEVEE] Protection du VLAN Serveurs : SERVEURS

 Scénario 2
- [ELEVEE] Isolation du WiFi : WIFI_INVITES
- [MOYENNE] Marge d'adresses insuffisante : WIFI_INVITES (6 hotes)
- [MOYENNE] Marge d'adresses insuffisante : VOIP (2 hotes)
- [ELEVEE] Restriction du VLAN Administration : ADMINISTRATION
- [ELEVEE] Protection du VLAN Serveurs : SERVEURS

 Difficultés rencontrées:

Les accolades de MoteurRecommandation fermaient la classe trop tôt.
Les parenthèses manquaient sur new RecommandationMargeAdresse().
ResultatVLSM a été enrichi avec getHotesDemandes() et setHotesDemandes() pour permettre le calcul de la marge.

 Réponses aux questions

1. Un moteur de recommandations analyse le plan généré et produit des conseils techniques concrets. 
Il transforme l'application en assistant qui aide l'administrateur à prendre de meilleures décisions.

2. On utilise une interface pour imposer un contrat commun à toutes les règles.
Chaque règle doit implémenter analyser(VLAN vlan), ce qui garantit que le moteur peut les appeler de la même façon.

3. Une classe concrète contient du code et peut être instanciée. Une interface définit uniquement des méthodes sans implémentation.
Elle impose un comportement sans en définir les détails.

4. La méthode analyser() peut retourner null quand aucune condition
n'est remplie pour ce VLAN. Cela évite de créer des recommandations
inutiles et permet au moteur de filtrer les résultats.

5. Le moteur manipule des objets de type RegleRecommandation sans connaître leur implémentation réelle. Chaque règle exécute sa propre
logique via la même méthode analyser(). C'est le polymorphisme.

6. Créer une classe par règle respecte le principe de responsabilité unique. 
Chaque classe est facile à lire, tester et modifier. Ajouter une nouvelle règle ne nécessite pas de modifier le moteur principal.

7. Un VLAN WiFi invité doit être isolé car les invités ne doivent pas
accéder aux ressources internes. Sans isolation, un invité pourrait accéder aux serveurs ou aux postes de travail internes.

8. Les VLANs de grande taille génèrent beaucoup de broadcasts, ce qui
dégrade les performances. Ils sont aussi plus difficiles à superviser et à sécuriser.

 RESULTATS OBTENUS:

run:
===== IPPlan-Manager : TP8 - Recommandations =====

===== SCENARIO 1 : Universite =====

Plan VLAN genere :
VLAN ID : 10
Nom : ETUDIANTS
Description : VLAN ETUDIANTS
ETUDIANTS -> 10.10.0.0/23 | Plage : 10.10.0.1 - 10.10.1.254 | Capacite : 510 hotes

VLAN ID : 20
Nom : WIFI_INVITES
Description : VLAN WIFI_INVITES
WIFI_INVITES -> 10.10.2.0/24 | Plage : 10.10.2.1 - 10.10.2.254 | Capacite : 254 hotes

VLAN ID : 30
Nom : ENSEIGNANTS
Description : VLAN ENSEIGNANTS
ENSEIGNANTS -> 10.10.3.0/25 | Plage : 10.10.3.1 - 10.10.3.126 | Capacite : 126 hotes

VLAN ID : 40
Nom : LABORATOIRES
Description : VLAN LABORATOIRES
LABORATOIRES -> 10.10.3.128/26 | Plage : 10.10.3.129 - 10.10.3.190 | Capacite : 62 hotes

VLAN ID : 50
Nom : SERVEURS
Description : VLAN SERVEURS
SERVEURS -> 10.10.3.192/27 | Plage : 10.10.3.193 - 10.10.3.222 | Capacite : 30 hotes


Recommandations proposees :
[MOYENNE] VLAN de grande taille : Le VLAN ETUDIANTS poss�de une grande capacit�. Il faut surveiller les broadcasts.
[�LEV�E] Isolation du WiFi : Le VLAN WIFI_INVITES doit �tre isol� des VLANs internes sensibles.
[MOYENNE] VLAN de grande taille : Le VLAN WIFI_INVITES poss�de une grande capacit�. Il faut surveiller les broadcasts.
[MOYENNE] Marge d'adresses insuffisante : Le VLAN ENSEIGNANTS a une marge de seulement 6 hotes. Prevoyez un sous-reseau plus grand.
[MOYENNE] Marge d'adresses insuffisante : Le VLAN LABORATOIRES a une marge de seulement 2 hotes. Prevoyez un sous-reseau plus grand.
[�LEV�E] Protection du VLAN Serveurs : Le VLAN SERVEURS doit �tre prot�g� par des ACL et surveill� en priorit�.
[MOYENNE] Marge d'adresses insuffisante : Le VLAN SERVEURS a une marge de seulement 0 hotes. Prevoyez un sous-reseau plus grand.

===== SCENARIO 2 : Entreprise =====

Plan VLAN genere :
VLAN ID : 10
Nom : WIFI_INVITES
Description : VLAN WIFI_INVITES
WIFI_INVITES -> 192.168.1.0/25 | Plage : 192.168.1.1 - 192.168.1.126 | Capacite : 126 hotes

VLAN ID : 20
Nom : CAMERAS
Description : VLAN CAMERAS
CAMERAS -> 192.168.1.128/25 | Plage : 192.168.1.129 - 192.168.1.254 | Capacite : 126 hotes

VLAN ID : 30
Nom : VOIP
Description : VLAN VOIP
VOIP -> 192.168.2.0/26 | Plage : 192.168.2.1 - 192.168.2.62 | Capacite : 62 hotes

VLAN ID : 40
Nom : ADMINISTRATION
Description : VLAN ADMINISTRATION
ADMINISTRATION -> 192.168.2.64/26 | Plage : 192.168.2.65 - 192.168.2.126 | Capacite : 62 hotes

VLAN ID : 50
Nom : SERVEURS
Description : VLAN SERVEURS
SERVEURS -> 192.168.2.128/27 | Plage : 192.168.2.129 - 192.168.2.158 | Capacite : 30 hotes


Recommandations proposees :
[�LEV�E] Isolation du WiFi : Le VLAN WIFI_INVITES doit �tre isol� des VLANs internes sensibles.
[MOYENNE] Marge d'adresses insuffisante : Le VLAN WIFI_INVITES a une marge de seulement 6 hotes. Prevoyez un sous-reseau plus grand.
[MOYENNE] Marge d'adresses insuffisante : Le VLAN VOIP a une marge de seulement 2 hotes. Prevoyez un sous-reseau plus grand.
[ELEVEE] Restriction du VLAN Administration : Le VLAN ADMINISTRATION doit etre accessible aux administrateurs reseau uniquement.
[�LEV�E] Protection du VLAN Serveurs : Le VLAN SERVEURS doit �tre prot�g� par des ACL et surveill� en priorit�.
BUILD SUCCESSFUL (total time: 1 second)

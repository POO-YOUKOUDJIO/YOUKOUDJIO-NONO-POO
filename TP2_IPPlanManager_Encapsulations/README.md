TP2: ENCAPSULATION ET VALIDATION DES DONNEES RESEAU DANS IPLAN-MANAGER

14.1 Pourquoi une adresse IP est une classe ?
Une adresse IP est une entité réseau à part entière.

En la représentant par une classe, on peut ajouter des méthodes (validation, conversion, affichage).

Cela prépare le terrain pour des fonctionnalités avancées (calcul de sous-réseaux, vérification de chevauchements).
👉 Une simple String ne permettrait pas cette extensibilité.

14.2 Différence entre classe et objet
Une classe est un modèle abstrait (ex. Equipement).

Un objet est une instance concrète de ce modèle (ex. new Equipement("SRV DNS", "Serveur", interfaceServeur)).
👉 La classe définit la structure, l’objet est la réalisation pratique.

14.3 Rôle du constructeur
Le constructeur initialise les attributs d’un objet dès sa création.
Exemple : new ReseauIP("192.168.1.0", 24, "Réseau principal") configure directement les valeurs nécessaires.
👉 Il garantit que l’objet est utilisable immédiatement après instanciation.

14.4 Pourquoi InterfaceReseau contient un objet AdresseIP ?
Parce qu’une interface réseau est liée à une adresse IP réelle.

Cela permet de manipuler l’adresse comme un objet complet.

On peut afficher, valider ou modifier l’adresse IP directement via la classe AdresseIP.
👉 C’est une modélisation fidèle à la réalité technique.

14.5 Pourquoi Equipement contient un objet InterfaceReseau ?
Un équipement réseau est toujours relié à une interface.

L’objet InterfaceReseau représente cette connexion.

Cela reflète la réalité : un routeur ou un serveur n’existe pas isolé, il est relié au réseau via une interface.
👉 C’est une relation de composition entre objets.

14.6 Limite actuelle de la classe Equipement
Dans le TP1, un équipement ne peut avoir qu’une seule interface principale.
👉 Limitation volontaire pour simplifier, mais insuffisante pour représenter des équipements complexes (routeurs multi-interfaces, serveurs multi-cartes réseau).

14.7 Pourquoi cette version n’est pas suffisante pour un plan IP automatique ?
Parce que le programme ne fait que stocker et afficher des objets.

Il n’y a pas encore de logique pour calculer automatiquement les sous-réseaux.

Pas de vérification des chevauchements ou génération d’un plan complet.
👉 C’est une base, mais pas encore un outil opérationnel.

15. README.md et livrable attendu
Le fichier README.md est essentiel car il :

Documente l’objectif du TP.

Liste les classes créées (AdresseIP, ReseauIP, InterfaceReseau, Equipement, Main).

Décrit le travail réalisé (objets créés, tests effectués).

Contient les réponses aux questions de compréhension (13 et 14).

👉 Le livrable attendu inclut :

Le projet NetBeans complet.

Le fichier README.md rempli.

Au moins cinq objets réseau créés.

Une exécution correcte du programme.

Le lien du dépôt Git contenant le projet.

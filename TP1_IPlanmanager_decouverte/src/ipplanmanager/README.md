TP1:DECOUVERTE DU PROJET IPPLAN-MANAGER ET CREATION DES PREMIERES CLASSES JAVA

Pourquoi une adresse IP est une classe ?
Une adresse IP est représentée par une classe pour mieux modéliser le domaine réseau.

Si on utilisait uniquement une String, on perdrait la possibilité d’ajouter des méthodes spécifiques (validation, conversion, affichage).

Avec une classe, on peut enrichir progressivement la logique (par ex. vérifier si l’adresse est valide, calculer le réseau, etc.).

14.2 Différence entre classe et objet
Une classe est un modèle ou une définition (ex. Equipement).

Un objet est une instance concrète de cette classe (ex. new Equipement("R1_EDGE", "Routeur", interfaceRouteur)).

14.3 Rôle du constructeur
Le constructeur initialise un objet au moment de sa création.
Exemple : new AdresseIP("192.168.1.1") assigne directement la valeur à l’attribut valeur.

14.4 Pourquoi InterfaceReseau contient un objet AdresseIP ?
Parce qu’une interface réseau est liée à une adresse IP réelle.

Cela permet de manipuler l’adresse comme un objet complet, et non comme une simple chaîne de caractères.

On peut ainsi afficher, valider ou modifier l’adresse IP directement via la classe AdresseIP.

14.5 Pourquoi Equipement contient un objet InterfaceReseau ?
Un équipement réseau (routeur, serveur, client) est toujours relié à au moins une interface réseau.

L’objet InterfaceReseau permet de représenter cette connexion.

Cela reflète la réalité technique : un équipement n’existe pas isolé, il est relié au réseau via une interface.

14.6 Limite actuelle de la classe Equipement
Dans ce TP, un équipement ne peut avoir qu’une seule interface principale.
👉 Limitation volontaire pour simplifier, mais insuffisante pour représenter des équipements complexes (routeurs multi-interfaces, serveurs avec plusieurs cartes réseau).

14.7 Pourquoi cette version n’est pas suffisante pour un plan IP automatique ?
Parce que le programme ne fait que stocker et afficher des objets.

Il n’y a pas encore de logique pour calculer automatiquement les sous-réseaux, vérifier les chevauchements ou générer un plan complet.

Ce sera introduit dans les TPs suivants.
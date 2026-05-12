TP10 - Application finale IPPlan-Manager
## Objectif

L'objectif de ce projet est d'assembler toutes les briques logicielles dÃ©veloppÃ©es au cours des travaux pratiques prÃ©cÃ©dents pour produire une application console complÃ¨te et professionnelle. **IPPlan-Manager** permet de planifier l'adressage IP (VLSM) et de gÃ©rer les VLANs d'une infrastructure rÃ©seau de maniÃ¨re automatisÃ©e.

## FonctionnalitÃ©s rÃ©alisÃ©es

* **Saisie interactive** : Collecte des besoins par service via la console.
* **Chargement CSV** : Importation automatique des besoins depuis un fichier externe pour gagner du temps.
* **Moteur VLSM** : Calcul prÃ©cis des adresses rÃ©seaux, masques de sous-rÃ©seau, adresses de diffusion et marges.
* **Gestion des VLANs** : Attribution automatique d'IDs et de noms aux segments rÃ©seau.
* **Validation de sÃ©curitÃ©** : VÃ©rification de la validitÃ© des IPs et dÃ©tection des chevauchements.
* **Audit technique** : Analyse automatique et gÃ©nÃ©ration de recommandations de sÃ©curitÃ©.
* **Persistance** : Exportation de 4 fichiers de sortie (CSV et TXT) pour une utilisation rÃ©elle.

## Organisation du projet

Le projet est structurÃ© en **6 packages** distincts pour respecter les principes de la POO :

* **`ipplanmanager.model`** : DÃ©finit les objets mÃ©tier (`VLAN`, `BesoinReseau`, `ResultatVLSM`).
* **`ipplanmanager.service`** : Contient la logique de calcul (`MoteurVLSM`), de gestion (`GestionnaireVLAN`) et de rapport (`RapportService`).
* **`ipplanmanager.repository`** : GÃ¨re la lecture et l'Ã©criture des fichiers (`FichierPlanRepository`, `BesoinRepository`).
* **`ipplanmanager.exception`** : Centralise les erreurs personnalisÃ©es pour une meilleure robustesse.
* **`ipplanmanager.console`** : GÃ¨re toute l'interface textuelle et les saisies utilisateur.
* **`ipplanmanager.main`** : Contient la classe de dÃ©marrage qui lance l'orchestrateur.

## ScÃ©narios testÃ©s

1. **Campus IRT** : Test de montÃ©e en charge avec 5 rÃ©seaux dont un segment de 500 hÃ´tes (nÃ©cessitant un /23).
2. **PME** : Test d'un environnement mixte avec des services critiques comme la VOIP et les SERVEURS.
3. **Entreprise multi-services** : Validation de la segmentation avec des objets hÃ©tÃ©rogÃ¨nes (CAMERAS, TECHNIQUE).

## Fichiers gÃ©nÃ©rÃ©s

Tous les fichiers sont regroupÃ©s dans le dossier `/exports` avec le nom du projet en prÃ©fixe :

* `Projet_plan.csv` : Le tableau d'adressage dÃ©taillÃ©.
* `Projet_vlans.csv` : La base de donnÃ©es des VLANs.
* `Projet_recommandations.txt` : Conseils d'optimisation et alertes de sÃ©curitÃ©.
* `Projet_rapport.txt` : SynthÃ¨se complÃ¨te destinÃ©e Ã  l'administrateur rÃ©seau.

## DifficultÃ©s rencontrÃ©es

La principale difficultÃ© a Ã©tÃ© la gestion des flux d'entrÃ©e/sortie lors du passage d'une saisie manuelle Ã  un chargement par fichier CSV, notamment la gestion des erreurs de formatage (`NumberFormatException`) dans les fichiers sources. La coordination entre le `GestionnaireVLAN` et le `RapportService` a Ã©galement demandÃ© une attention particuliÃ¨re sur la visibilitÃ© des mÃ©thodes (getters).

## RÃ©ponses aux questions

1. **Application complÃ¨te** : Car elle intÃ¨gre la persistance des donnÃ©es (fichiers) et une architecture modulaire qui traite un problÃ¨me rÃ©el de A Ã  Z.
2. **RÃ´le de `ApplicationIPPlanManager**` : C'est l'orchestrateur principal. Elle fait le lien entre l'utilisateur, les moteurs de calcul et le systÃ¨me de fichiers.
3. **Classe `Main` courte** : Pour assurer que le point d'entrÃ©e ne s'occupe que du dÃ©marrage. La logique doit rester dans les services dÃ©diÃ©s pour Ãªtre testable.
4. **SÃ©paration des packages** : Elle permet de modifier une partie du code (ex: changer l'interface console pour du Web) sans casser la logique de calcul.
5. **Saisie dans `ConsoleService**` : Pour centraliser l'interaction homme-machine et isoler les erreurs de saisie du reste de l'application.
6. **Validation d'adresse** : Pour garantir que les calculs VLSM partent d'une base saine. Une IP de dÃ©part invalide corromprait tout le plan.
7. **Recommandations aprÃ¨s VLANs** : Car elles ont besoin d'analyser la structure finale complÃ¨te (capacitÃ©s, noms de VLANs, marges) pour Ãªtre pertinentes.
8. **Sauvegarde des rÃ©sultats** : Elle permet de transmettre les donnÃ©es Ã  d'autres outils (Excel, scripts de config) et de garder une trace historique.
9. **Rapport technique** : C'est le livrable professionnel indispensable pour justifier l'architecture auprÃ¨s d'un client ou d'une direction technique.
10. **AmÃ©liorations futures** : Support de l'IPv6, interface graphique Swing/JavaFX, et exportation de scripts de configuration pour switchs Cisco/Huawei.



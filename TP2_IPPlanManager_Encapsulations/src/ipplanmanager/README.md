TP2 : Encapsulation et validation des données réseau dans IPPlan-Manager 

1. Pourquoi utilise-t-on private dans les classes ?
On utilise le modificateur private pour protéger les données internes d’un objet.
Cela empêche une modification directe depuis l’extérieur de la classe et garantit que les données ne peuvent être changées qu’à travers des méthodes contrôlées (getters/setters).
👉 Cela évite les incohérences et renforce la sécurité du programme.

2. Quelle différence existe entre un attribut public et un attribut privé ?
Attribut public : accessible et modifiable directement depuis n’importe quelle partie du programme. Cela peut provoquer des erreurs ou incohérences.

Attribut privé : uniquement accessible à l’intérieur de la classe. Les modifications doivent passer par des méthodes prévues (setters), ce qui permet de valider et contrôler les données.
👉 Le privé apporte une couche de protection supplémentaire.

3. Pourquoi utilise-t-on des getters et setters ?
Les getters permettent de lire la valeur d’un attribut privé, et les setters permettent de la modifier en appliquant des règles de validation.
👉 Ils assurent un accès contrôlé aux données et garantissent que les valeurs restent cohérentes et valides.

4. Pourquoi les validations sont-elles importantes dans un logiciel réseau ?
Dans un logiciel réseau, une donnée incorrecte (ex. une adresse IP vide ou un masque CIDR invalide) peut provoquer :des erreurs de configuration,
conflits réseau, voire des pannes.
👉 Les validations empêchent l’enregistrement de valeurs incohérentes et assurent la fiabilité du système.

5. Quel est le rôle du mot-clé this ?
Le mot-clé this fait référence à l’instance courante de la classe.
👉 Il permet de distinguer les variables locales des attributs de l’objet et d’indiquer clairement que l’on modifie l’attribut de l’instance en cours.

6. Pourquoi le constructeur appelle-t-il les setters ?
Le constructeur appelle les setters pour profiter des validations déjà définies.
👉 Ainsi, dès la création d’un objet, les données sont automatiquement contrôlées et corrigées si nécessaire (par exemple, une adresse IP vide devient 0.0.0.0).

7. Pourquoi la validation du masque CIDR est-elle importante ?
Un masque CIDR incorrect (ex. 45) rendrait le réseau inutilisable.
👉 La validation garantit que la valeur reste dans l’intervalle [0,32]. En cas d’erreur, une valeur par défaut (ex. /24) est appliquée, ce qui assure la cohérence du réseau.

8. Pourquoi l’encapsulation améliore-t-elle la sécurité logicielle ?
L’encapsulation empêche l’accès direct aux données sensibles et impose un passage par des méthodes contrôlées.
👉 Cela réduit les risques d’erreurs, protège l’intégrité des objets et rend le logiciel plus robuste et fiable, ce qui est essentiel dans un contexte réseau.
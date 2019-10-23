# Ressources
Project for Architecture Logicielle for M.Petit


Exigences non fonctionnelles
Id	Texte	Priorité
Req-doc-01	La documentation doit décrire la procédure d'installation.	1
Req-doc-02	La documentation doit décrire la procédure de désinstallation.	1
Req-doc-03	La documentation doit décrire Les entrées et sorties de chaque composant.	3
Req-doc-04	La documentation doit décrire l'environnement matériel nécessaire à l'installation.	2
Req-doc-05	La documentation doit décrire l'environnement logiciel nécessaire à l'installation.	2
Req-doc-06	La documentation doit décrire le cheminement des appels de fonctions pour 2 fonctionnalités.	4
Req-doc-07	La documentation doit lister les exigences satisfaites.	4
Req-doc-08	La documentation doit lister les exigences non satisfaites.	4
Req-doc-09	La documentation doit lister les anomalies.	3
Req-doc-10	La documentation doit décrire la procédure de compilation.	2
		
Req-exp-01	Le système doit fonctionner sous Linux.	2
Req-exp-02	Le système doit fonctionner sous Windows.	2
Req-exp-03	Le système doit fonctionner sous Mac OS.	2
Req-exp-04	Le système doit être développé en Java 8	2
		
Req-liv-01	La livraison doit contenir tous les éléments nécessaires à la génération de la version binaire.	1
Req-liv-02	La livraison doit contenir la version binaire du système.	1
Req-liv-03	La livraison doit contenir toute la documentation.	3
		
Req-arc-01	Le système est constitué d'un seul exécutable.	1
Req-arc-02	Le programme principal instancie deux objets : l'un implémentant l'IHM et l'autre exposant les fonctionnalités.	1
Req-arc-03	Les communications entre IHM et fonctions passent par une unique interface Java.	1
Req-arc-04	Les requêtes vont uniquement de l'IHM vers l'objet exposant les fonctions.	1
Req-arc-05	Les opérations exposées par l'interface sont de 4 types : lecture, création, modification, suppression d'un objet.	2
Req-arc-06	Les classes implémentant l'IHM et les fonctionnalités sont packagées dans des jar distincts.	2
Req-arc-07	L'identifiant des objets créés est attribuée par l'IHM.	2
		
Req-fct-01	Après re-démarrage du système il est dans le même état qu'avant son arrêt (données).	3
Req-fct-02	Il est possible de mettre à jour l'IHM (automatiquement ou à la demande de l'utilisateur)	3


Exigences fonctionnelles
Id	Texte	Priorité	Groupe
Req-res-01	L'utilisateur peut créer une salle.	1	Ressources
Req-res-02	L'utilisateur peut supprimer une salle.	2	Ressources
Req-res-03	L'utilisateur peut créer une personne.	1	Ressources
Req-res-04	L'utilisateur peut supprimer une personne.	2	Ressources
Req-res-05	L'utilisateur peut créer un créneau (temporel).	1	Ressources
Req-res-06	L'utilisateur peut supprimer un créneau (temporel).	2	Ressources
Req-res-07	L'utilisateur peut créer une réservation (personne – salle - créneau).	3	Ressources
Req-res-08	L'utilisateur peut supprimer une réservation (personne – salle – créneau).	3	Ressources
Req-res-09	L'utilisateur peut changer une réservation de créneau temporel.	4	Ressources
Req-res-10	L'utilisateur peut créer des réservations sur un ensemble de créneaux.	4	Ressources


# Concevez une application web Java de A à Z
## Dans le cadre de la formation OpenClassrooms "Développeur d'application Java" 


Il s’agit du 6ème projet de la formation

### Objectif du projet

En partant de zéro, développer toutes les couches d'une application Java sécurisée et fonctionnelle.

### Fonctionnalités de l’application

Application qui, en finalité, doit servir à effectuer des transactions entre utilisateurs. Les pricipales fonctionnalités actuellement implémentés sont : 
- La création d’un compte sécurisé avec Id et Mdp
- La connexion à l’application
- L’ajout d’un montant sur son compte
- l’ajout d’un contact
- l’envoi d’un montant à son contact
- l’affichage en temps réel de la liste des transactions effectuée avec pagination
- le stockage de la totalité des données en base de données (mote de passe des utilisateurs sont stockés de manière cryptée)

L’application n’est pas encore livrée dans sa version finale, les transfert d’argents sont donc fictifs.
Progression
En plus de renforcer mes compétences sur les aspects étudiés pendant mes précédents projets, ce projet m'a permis de pratiquer l'intégration d'une base de données dans le circuit de l'application de sa conception jusqu'à la gestion des transactions, et de prendre en main d'autres extensions de Spring, notamment Spring Security, avec la création d’un fichier de configuration et l’encodage d’un mot de passe en base de données

### Réalisation

Utilisation de SpringDataJPA pour la communication avec la base de données
Utilisation de SpringSecurity pour réalisér une configuration sécurisée
Utilisation de MySQL pour stocker les données 
Utilisation de Thymeleaf et Bootstrap pour la partie Front
Réalisation de tests unitaires et d’intégration
Le projet poursuit les bonnes pratiques de développement, avec l'isolation et l'indépendance des tests, la séparation des interfaces, l'injection des dépendances, ou encore l'utilisation du Data Transfert Object Pattern.
Des données sont déjà présentes dans le Script SQL de base de données. Ce dernier est divisé en un fichier de STRUCTURE et un fichier de DATA. Ces 2 fichiers sont disponibles dans le fichier RESOURCES

### Lancement du projet

Projet à lancer sur IDE (Eclipse ou Intellij)
Fichier de configuration contient des variables d’environnement pour l’ID et le MDP de connexion à mySql. Configurez vos variables d’environnement qui correspondent à votre ID et MDP avant de lancer le projet
Lancer la vue en http://localhost:8080/
Vous pouvez créer votre Utilisateur via le formulaire d’enregistrement, puis vous connecter à l’application, ajouter un autre utilisateurs dans vos contact, créditer votre compte, et réaliser des transactions.



# Diagramme de classe

![Diagramme de classe!](./docs/diagram.png "Diagram")

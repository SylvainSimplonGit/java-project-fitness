# Projet JAVA Fitness

- [Rappels](#rappels)
  - [Contexte](#contexte)
  - [MVP](#minimum-vailable-product)
  - [Installation](#installation)
  - [Execution](#execution)
  
## Rappels

### Contexte

L'idée est de développer un petit programme Java en mode console pour enregistrer des séances de musculation dans un fichier csv et faire des statistiques sur les exercices.

### Minimum Vailable Product

Le code du programme Java devra être disponible sur Github.

Il s'agit de réaliser un programme en mode console qui permet de :

- Ajouter un set de musculation en spécifiant : le type d'exercice, le nombre de répétition, le poids utilisé pour le set
- Sauver tous les sets dans un même fichier csv
- Demander des statistiques sur un type exercice particulier : 
  - par rapport au poids,
  - par rapport aux répétitions
  
Le menu du programme aura cette forme :<br/>
<p align="center">
 <img width="60%" src="resources/brief/Menu du programme.png">
</p>

Ce programme utilisera un fichier CSV pour stocker les données.

## Installation

### Pré requis

Logiciels necessaires :

- Git **v2.21.0** ou supérieure
  - ([Installation](https://git-scm.com/downloads))
- JDK Java **v11** ou supérieure
  - ([Installation sur Linux](https://docs.oracle.com/en/java/javase/11/install/installation-jdk-linux-platforms.html#GUID-737A84E4-2EFF-4D38-8E60-3E29D1B884B8))
  - ([Installation sur Windows](https://docs.oracle.com/en/java/javase/11/install/installation-jdk-microsoft-windows-platforms.html#GUID-A7E27B90-A28D-4237-9383-A58B416071CA))
  - ([Installation sur MacOs](https://docs.oracle.com/en/java/javase/11/install/installation-jdk-macos.html#GUID-2FE451B0-9572-4E38-A1A5-568B77B146DE))

Dans un répertoire vide dénommé dans la suite **\<repertoire\>**, executer la commande suivante :

```git
git clone https://github.com/SylvainSimplonGit/java-project-fitness.git
```

Cette commande fera une copie de ce repository sur votre poste local.

## Execution

### Via IntelliJ

#### Execution du programme principal

- Sélectionnez dans le menu d'IntelliJ, ***Run*** puis le sous menu ***Edit Configurations...***

- Dans ***Main Class*** choisir *Main*

- Ensuite parcourez les menus pour afficher ce que vous souhaitez !

## Etapes

 - [X] Définition des différents menus

 - [X] Création de la Class Menu et définition des routes des menus

 - [X] Création de la Class FileCSV et définition de la méthode writeLineInCsvFile

 - [X] Création de la Class Exercise et de la Class Set

 - [X] Modification de la documentation au format JavaDoc

 ### Améliorations à prévoir
 
 - [ ] Ajouter les tests unitaires avec JUnit
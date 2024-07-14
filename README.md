# E-Bank Solution

## But du Projet
L'application E-Bank Solution vise à digitaliser les services bancaires pour offrir une expérience fluide et accessible aux utilisateurs. Elle permet aux clients de gérer leurs comptes bancaires, leurs cartes et d'effectuer des opérations financières en ligne de manière sécurisée.

## Fonctionnalités Principales
- **Gestion des comptes** : Création, consultation des soldes, historiques de transactions, et fermeture de comptes.
- **Gestion des cartes bancaires** : Consultation des informations, activation, désactivation, et blocage en cas de perte ou vol.
- **Transferts d'argent** : Transferts internes/externes et gestion des bénéficiaires.

## Technologies Utilisées
- **Backend** : Spring Boot
- **Base de données** : MySQL
- **Documentation API** : Springfox (Swagger), Postman
- **Tests** : JUnit
- **Build** : Maven
- **Sécurité** : Authentification et autorisation avec Spring Security, JWT pour la gestion des tokens.

## Sécurité avec Spring Security
- **Authentification des Utilisateurs** : Utilisation de Spring Security pour l'authentification via nom d'utilisateur et mot de passe hashé.
- **Génération de JWT** : JWT généré lors de l'authentification réussie, utilisé pour autoriser les accès aux endpoints API sécurisés.
- **Contrôle d'Accès** : Basé sur les rôles (utilisateur, administrateur) pour limiter l'accès aux fonctionnalités sensibles comme les transferts d'argent.

## Déploiement avec Docker
- **Dockerisation de l'Application** : Utilisation d'un Dockerfile pour containeriser l'application Spring Boot.
- **Tests après Déploiement** : Assurez-vous que l'application fonctionne correctement dans un environnement Docker containerisé.

## Documentation des Requêtes Postman

### Gestion des Comptes

1. **Création de compte bancaire**
   - **URL** : `api/accounts`
   - **Méthode** : `POST`
   - **Body** :
     ```json
     {
       "type": "Épargne",
       "soldeInitial": 1000
     }
     ```
   - **Réponse** :
     ```json
     {
       "id": 1,
       "type": "Épargne",
       "solde": 1000,
       "dateCreation": "2024-07-01"
     }
     ```

2. **Consultation des soldes et historiques de transactions**
   - **URL** : `api/accounts/{accountId}/transactions`
   - **Méthode** : `GET`
   - **Réponse** :
     ```json
     {
       "accountId": 1,
       "solde": 1000,
       "transactions": [
         {
           "id": 1,
           "date": "2024-07-02T10:00:00",
           "montant": 500,
           "type": "Crédit",
           "description": "Salaire"
         },
         {
           "id": 2,
           "date": "2024-07-03T14:00:00",
           "montant": -200,
           "type": "Débit",
           "description": "Courses"
         }
       ]
     }
     ```

3. **Fermeture de compte bancaire**
   - **URL** : `api/accounts/{accountId}`
   - **Méthode** : `DELETE`
   - **Body** :
     ```json
     {
       "raison": "Compte inactif"
     }
     ```
   - **Réponse** :
     ```json
     {
       "message": "Compte fermé avec succès"
     }
     ```

### Gestion des Cartes Bancaires

1. **Consultation des informations de la carte**
   - **URL** : `api/cards/{cardId}`
   - **Méthode** : `GET`
   - **Réponse** :
     ```json
     {
       "cardId": 1,
       "numero": "1234-5678-9101-1121",
       "dateExpiration": "2026-12",
       "type": "Crédit"
     }
     ```

2. **Activation et désactivation de la carte**
   - **URL** : `api/cards/{cardId}/status`
   - **Méthode** : `PUT`
   - **Body** :
     ```json
     {
       "status": "active"  // ou "inactive"
     }
     ```
   - **Réponse** :
     ```json
     {
       "message": "Statut de la carte mis à jour avec succès"
     }
     ```

3. **Blocage de la carte en cas de perte ou de vol**
   - **URL** : `api/cards/{cardId}/block`
   - **Méthode** : `PUT`
   - **Body** :
     ```json
     {
       "raison": "Carte perdue"
     }
     ```
   - **Réponse** :
     ```json
     {
       "message": "Carte bloquée avec succès"
     }
     ```

### Transferts d'Argent

1. **Transferts internes entre comptes du même utilisateur**
   - **URL** : `api/transfers/internal`
   - **Méthode** : `POST`
   - **Body** :
     ```json
     {
       "sourceAccountId": 1,
       "destinationAccountId": 2,
       "montant": 300,
       "description": "Épargne mensuelle"
     }
     ```
   - **Réponse** :
     ```json
     {
       "message": "Transfert interne effectué avec succès"
     }
     ```

2. **Transferts externes vers des comptes dans d'autres banques**
   - **URL** : `api/transfers/external`
   - **Méthode** : `POST`
   - **Body** :
     ```json
     {
       "sourceAccountId": 1,
       "destinationCompteExterne": {
         "numero": "9876-5432-1098-7654",
         "banque": "Autre Banque"
       },
       "montant": 500,
       "description": "Paiement facture"
     }
     ```
   - **Réponse** :
     ```json
     {
       "message": "Transfert externe effectué avec succès"
     }
     ```

3. **Gestion des bénéficiaires**
   - **URL** : `api/beneficiaries`
   - **Méthode** : `POST`
   - **Body** :
     ```json
     {
       "nom": "Jean Dupont",
       "detailsCompte": {
         "numero": "8765-4321-0987-6543",
         "banque": "Banque Nationale"
       }
     }
     ```
   - **Réponse** :
     ```json
     {
       "message": "Bénéficiaire ajouté avec succès"
     }
     ```

   - **URL** : `api/beneficiaries/{beneficiaryId}`
   - **Méthode** : `PUT`
   - **Body** :
     ```json
     {
       "nom": "Jean Dupont",
       "detailsCompte": {
         "numero": "8765-4321-0987-6543",
         "banque": "Banque Nationale"
       }
     }
     ```
   - **Réponse** :
     ```json
     {
       "message": "Bénéficiaire mis à jour avec succès"
     }
     ```

   - **URL** : `/beneficiaries/{beneficiaryId}`
   - **Méthode** : `DELETE`
   - **Réponse** :
     ```json
     {
       "message": "Bénéficiaire supprimé avec succès"
     }
     ```
     
     ```

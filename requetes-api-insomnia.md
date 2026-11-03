# Requêtes API Fin Manager — Insomnia

Base URL par défaut : `http://localhost:8080`
Si tu lances l'application avec `SERVER_PORT=8081 ./run.sh`, remplace par `http://localhost:8081`.

> **Toutes les routes sont sous `/api/v1`** : `/api/v1/users`, `/api/v1/accounts`, `/api/v1/agencies`, `/api/v1/roles`, `/api/v1/permissions`, `/api/v1/transactions`, `/api/v1/loans`, `/api/v1/notifications`.

Toutes les requêtes avec un body doivent avoir l'en-tête :

```http
Content-Type: application/json
```

> **Format des dates**
> - **En entrée** (body JSON) : format ISO → `2026-11-03` pour une date, `2026-11-03T10:00:00` pour une date-heure.
> - **En sortie** (réponse JSON) : `03-11-2026` pour une date, `03-11-2026 10:00:00` pour une date-heure.

---

## 1. Variables d'environnement Insomnia

Dans Insomnia : **Manage Environments** → colle ce JSON, puis sélectionne l'environnement.

```json
{
  "base_url": "http://localhost:8080",
  "permissionId": "",
  "roleId": "",
  "agencyId": "",
  "userId": "",
  "accountId": "",
  "transactionId": "",
  "loanId": "",
  "notificationId": ""
}
```

Après chaque création, recopie l'identifiant retourné dans la variable correspondante :

| Ressource | Champ retourné | Variable |
|---|---|---|
| Permission | `idPer` | `permissionId` |
| Role | `idRole` | `roleId` |
| Agency | `numAg` | `agencyId` |
| User | `idUser` | `userId` |
| Account | `numCompte` | `accountId` |
| Transaction | `numTransac` | `transactionId` |
| Loan | `idCredit` | `loanId` |
| Notification | `idNotification` | `notificationId` |

> La CNI de l'utilisateur est générée automatiquement par le serveur (format `USER_XXXXX`).  
> Ne la saisis pas : récupère `idUser` dans la réponse du POST.

---

## 2. Ordre de test conseillé

1. Permission
2. Role (utilise `permissionId`)
3. Agency
4. User
5. Account (utilise `userId` et `agencyId`)
6. Transaction (utilise `accountId`)
7. Loan (utilise `accountId`)
8. Notification (utilise `transactionId`)
9. Suppressions dans l'ordre inverse

---

## 3. Permissions

### Créer une permission

```http
POST {{ _.base_url }}/api/v1/permissions
```

```json
{
  "nomPer": "CREATE_USER",
  "descriptionPer": "Permet de créer un utilisateur"
}
```

Réponse attendue : `201 Created`  
→ recopier `idPer` dans `permissionId`.

### Lister les permissions

```http
GET {{ _.base_url }}/api/v1/permissions
```

Réponse attendue : `200 OK`

### Lire une permission

```http
GET {{ _.base_url }}/api/v1/permissions/{{ _.permissionId }}
```

Réponse attendue : `200 OK`

### Modifier une permission

```http
PUT {{ _.base_url }}/api/v1/permissions/{{ _.permissionId }}
```

```json
{
  "nomPer": "CREATE_USER_UPDATED",
  "descriptionPer": "Permission mise à jour"
}
```

Réponse attendue : `200 OK`

### Supprimer une permission

```http
DELETE {{ _.base_url }}/api/v1/permissions/{{ _.permissionId }}
```

Réponse attendue : `204 No Content`

---

## 4. Roles

### Créer un rôle

```http
POST {{ _.base_url }}/api/v1/roles
```

```json
{
  "nomRole": "ADMIN",
  "description": "Administrateur de l'application",
  "permissionIds": [{{ _.permissionId }}]
}
```

Réponse attendue : `201 Created`  
→ recopier `idRole` dans `roleId`.

### Lister les rôles

```http
GET {{ _.base_url }}/api/v1/roles
```

### Lire un rôle

```http
GET {{ _.base_url }}/api/v1/roles/{{ _.roleId }}
```

### Modifier un rôle

```http
PUT {{ _.base_url }}/api/v1/roles/{{ _.roleId }}
```

```json
{
  "nomRole": "ADMIN_UPDATED",
  "description": "Rôle administrateur mis à jour",
  "permissionIds": [{{ _.permissionId }}]
}
```

### Supprimer un rôle

```http
DELETE {{ _.base_url }}/api/v1/roles/{{ _.roleId }}
```

Réponse attendue : `204 No Content`

---

## 5. Agences

### Créer une agence

```http
POST {{ _.base_url }}/api/v1/agencies
```

```json
{
  "longitude": 11.5021,
  "latitude": 3.8480,
  "nomAg": "Agence Yaoundé Centre",
  "telAg": "222222222",
  "emailAg": "yaounde.centre@finmanager.cm"
}
```

Réponse attendue : `201 Created`  
→ recopier `numAg` dans `agencyId`.

### Lister les agences

```http
GET {{ _.base_url }}/api/v1/agencies
```

### Lire une agence

```http
GET {{ _.base_url }}/api/v1/agencies/{{ _.agencyId }}
```

### Modifier une agence

```http
PUT {{ _.base_url }}/api/v1/agencies/{{ _.agencyId }}
```

```json
{
  "longitude": 11.5100,
  "latitude": 3.8500,
  "nomAg": "Agence Yaoundé Centre (MAJ)",
  "telAg": "233333333",
  "emailAg": "yaounde.centre.upd@finmanager.cm"
}
```

### Supprimer une agence

```http
DELETE {{ _.base_url }}/api/v1/agencies/{{ _.agencyId }}
```

Réponse attendue : `204 No Content`

---

## 6. Utilisateurs

> Toutes les routes utilisent le préfixe `/api/v1`. L'ancienne route `/api/users` n'existe plus et renvoie `404`.

### Créer un utilisateur

```http
POST {{ _.base_url }}/api/v1/users
```

```json
{
  "nomUser": "Nkondjo",
  "prenomUser": "Olivier",
  "email": "olivier.nkondjo@test.cm",
  "phone": "690000000",
  "dateN": "1995-05-15T00:00:00",
  "sexe": "MASCULIN",
  "profession": "Ingénieur",
  "password": "secret123"
}
```

Réponse attendue : `201 Created`  
→ recopier `idUser` dans `userId`.  
La CNI est générée automatiquement, par exemple `USER_A1B2C`.

> `roleIds` est accepté dans le body (ex. `"roleIds": [1]`) mais **n'est pas traité** par le service utilisateur. Les rôles se gèrent via `/api/v1/roles`.

### Lister les utilisateurs

```http
GET {{ _.base_url }}/api/v1/users
```

### Lire un utilisateur

```http
GET {{ _.base_url }}/api/v1/users/{{ _.userId }}
```

### Modifier un utilisateur

```http
PUT {{ _.base_url }}/api/v1/users/{{ _.userId }}
```

```json
{
  "nomUser": "Nkondjo",
  "prenomUser": "Olivier",
  "email": "olivier.nkondjo.upd@test.cm",
  "phone": "691111111",
  "dateN": "1995-05-15T00:00:00",
  "sexe": "MASCULIN",
  "profession": "Ingénieur logiciel",
  "password": "secret123"
}
```

### Supprimer un utilisateur

```http
DELETE {{ _.base_url }}/api/v1/users/{{ _.userId }}
```

Réponse attendue : `204 No Content`

### Cas d'erreur : utilisateur inexistant

```http
GET {{ _.base_url }}/api/v1/users/CNI_INEXISTANTE
```

Réponse attendue : `404 Not Found`

```json
{
  "erreur": "Utilisateur introuvable : CNI_INEXISTANTE"
}
```

---

## 7. Comptes

### Créer un compte

```http
POST {{ _.base_url }}/api/v1/accounts
```

```json
{
  "solde": 50000,
  "intituleCompte": "Compte courant Olivier",
  "typeCompte": "COURANT",
  "dateOuverture": "2026-11-03",
  "statutCompte": "ACTIVE",
  "userId": "{{ _.userId }}",
  "agencyId": {{ _.agencyId }}
}
```

Réponse attendue : `201 Created`  
→ recopier `numCompte` dans `accountId`.

### Lister les comptes

```http
GET {{ _.base_url }}/api/v1/accounts
```

### Lire un compte

```http
GET {{ _.base_url }}/api/v1/accounts/{{ _.accountId }}
```

### Modifier un compte

```http
PUT {{ _.base_url }}/api/v1/accounts/{{ _.accountId }}
```

```json
{
  "solde": 75000,
  "intituleCompte": "Compte épargne Olivier",
  "typeCompte": "EPARGNE",
  "dateOuverture": "2026-11-03",
  "statutCompte": "ACTIVE",
  "userId": "{{ _.userId }}",
  "agencyId": {{ _.agencyId }}
}
```

### Supprimer un compte

```http
DELETE {{ _.base_url }}/api/v1/accounts/{{ _.accountId }}
```

Réponse attendue : `204 No Content`

---

## 8. Transactions

### Créer une transaction

```http
POST {{ _.base_url }}/api/v1/transactions
```

```json
{
  "typeTransac": "DEPOT",
  "dateTransac": "2026-11-03T10:00:00",
  "montantTransac": 10000,
  "statutTransac": "VALIDEE",
  "accountId": {{ _.accountId }}
}
```

Réponse attendue : `201 Created`  
→ recopier `numTransac` dans `transactionId`.

Valeurs possibles :

- `typeTransac` : `DEPOT`, `RETRAIT`, `VIREMENT`
- `statutTransac` : `PENDING`, `VALIDEE`, `ANNULEE`, `ECHOUEE`

### Lister les transactions

```http
GET {{ _.base_url }}/api/v1/transactions
```

### Lire une transaction

```http
GET {{ _.base_url }}/api/v1/transactions/{{ _.transactionId }}
```

### Modifier une transaction

```http
PUT {{ _.base_url }}/api/v1/transactions/{{ _.transactionId }}
```

```json
{
  "typeTransac": "RETRAIT",
  "dateTransac": "2026-11-03T11:00:00",
  "montantTransac": 5000,
  "statutTransac": "VALIDEE",
  "accountId": {{ _.accountId }}
}
```

### Supprimer une transaction

```http
DELETE {{ _.base_url }}/api/v1/transactions/{{ _.transactionId }}
```

Réponse attendue : `204 No Content`

---

## 9. Crédits (loans)

### Créer un crédit

```http
POST {{ _.base_url }}/api/v1/loans
```

```json
{
  "montantCredit": 200000,
  "dateCredit": "2026-11-03",
  "typeCredit": "CONSOMMATION",
  "motifCredit": "Achat de matériel",
  "accountId": {{ _.accountId }}
}
```

Réponse attendue : `201 Created`  
→ recopier `idCredit` dans `loanId`.

### Lister les crédits

```http
GET {{ _.base_url }}/api/v1/loans
```

### Lire un crédit

```http
GET {{ _.base_url }}/api/v1/loans/{{ _.loanId }}
```

### Modifier un crédit

```http
PUT {{ _.base_url }}/api/v1/loans/{{ _.loanId }}
```

```json
{
  "montantCredit": 250000,
  "dateCredit": "2026-11-03",
  "typeCredit": "IMMOBILIER",
  "motifCredit": "Achat de terrain",
  "accountId": {{ _.accountId }}
}
```

### Supprimer un crédit

```http
DELETE {{ _.base_url }}/api/v1/loans/{{ _.loanId }}
```

Réponse attendue : `204 No Content`

---

## 10. Notifications

### Créer une notification

```http
POST {{ _.base_url }}/api/v1/notifications
```

```json
{
  "typeNot": "TRANSACTION",
  "contenuNot": "Dépôt de 10 000 FCFA effectué avec succès",
  "dateNot": "2026-11-03T10:01:00",
  "transactionId": {{ _.transactionId }}
}
```

Réponse attendue : `201 Created`  
→ recopier `idNotification` dans `notificationId`.

### Lister les notifications

```http
GET {{ _.base_url }}/api/v1/notifications
```

### Lire une notification

```http
GET {{ _.base_url }}/api/v1/notifications/{{ _.notificationId }}
```

### Modifier une notification

```http
PUT {{ _.base_url }}/api/v1/notifications/{{ _.notificationId }}
```

```json
{
  "typeNot": "TRANSACTION",
  "contenuNot": "Retrait de 5 000 FCFA effectué",
  "dateNot": "2026-11-03T11:01:00",
  "transactionId": {{ _.transactionId }}
}
```

### Supprimer une notification

```http
DELETE {{ _.base_url }}/api/v1/notifications/{{ _.notificationId }}
```

Réponse attendue : `204 No Content`

---

## 11. Nettoyage (ordre à respecter)

Supprime dans cet ordre pour éviter les erreurs de clés étrangères :

```http
DELETE {{ _.base_url }}/api/v1/notifications/{{ _.notificationId }}
DELETE {{ _.base_url }}/api/v1/loans/{{ _.loanId }}
DELETE {{ _.base_url }}/api/v1/transactions/{{ _.transactionId }}
DELETE {{ _.base_url }}/api/v1/accounts/{{ _.accountId }}
DELETE {{ _.base_url }}/api/v1/users/{{ _.userId }}
DELETE {{ _.base_url }}/api/v1/agencies/{{ _.agencyId }}
DELETE {{ _.base_url }}/api/v1/roles/{{ _.roleId }}
DELETE {{ _.base_url }}/api/v1/permissions/{{ _.permissionId }}
```

---

## 12. Résumé des endpoints

| Ressource | Méthode | URL |
|---|---|---|
| Permissions | GET / POST | `/api/v1/permissions` |
| Permissions | GET / PUT / DELETE | `/api/v1/permissions/{idPer}` |
| Roles | GET / POST | `/api/v1/roles` |
| Roles | GET / PUT / DELETE | `/api/v1/roles/{idRole}` |
| Agences | GET / POST | `/api/v1/agencies` |
| Agences | GET / PUT / DELETE | `/api/v1/agencies/{numAg}` |
| Utilisateurs | GET / POST | `/api/v1/users` |
| Utilisateurs | GET / PUT / DELETE | `/api/v1/users/{idUser}` |
| Comptes | GET / POST | `/api/v1/accounts` |
| Comptes | GET / PUT / DELETE | `/api/v1/accounts/{numCompte}` |
| Transactions | GET / POST | `/api/v1/transactions` |
| Transactions | GET / PUT / DELETE | `/api/v1/transactions/{numTransac}` |
| Crédits | GET / POST | `/api/v1/loans` |
| Crédits | GET / PUT / DELETE | `/api/v1/loans/{idCredit}` |
| Notifications | GET / POST | `/api/v1/notifications` |
| Notifications | GET / PUT / DELETE | `/api/v1/notifications/{idNotification}` |

> Les anciennes routes sans version (`/api/users`, `/api/accounts`, etc.) sont supprimées et renvoient `404 Not Found`.

Statuts attendus :

- `200 OK` : lecture / modification
- `201 Created` : création
- `204 No Content` : suppression
- `400 Bad Request` : donnée invalide (ex. `userId` manquant pour un compte)
- `404 Not Found` : identifiant inexistant

---

## 13. Contraintes de validation

Les champs suivants sont **obligatoires** dans les bodies JSON. Un champ manquant, vide ou invalide renvoie `400 Bad Request`.

| Ressource | Champs obligatoires | Règles |
|---|---|---|
| Permission | `nomPer` | non vide, max 100 caractères |
| Role | `nomRole` | non vide, max 50 caractères |
| Agence | `longitude`, `latitude`, `nomAg`, `telAg`, `emailAg` | nom non vide (max 150), téléphone max 20, email valide (max 150) |
| Utilisateur | `nomUser`, `email`, `phone`, `dateN`, `sexe`, `profession`, `password` | email valide (max 150), téléphone max 20, dates dans le passé, max 100 pour nom/prénom/profession, mot de passe max 255 |
| Compte | `solde`, `intituleCompte`, `typeCompte`, `dateOuverture`, `statutCompte`, `userId` | solde >= 0, intitulé non vide (max 150) |
| Transaction | `typeTransac`, `dateTransac`, `montantTransac`, `statutTransac`, `accountId` | montant > 0 |
| Crédit | `montantCredit`, `dateCredit`, `typeCredit`, `accountId` | montant > 0, type non vide (max 50), motif max 255 |
| Notification | `typeNot`, `contenuNot`, `dateNot`, `transactionId` | type non vide (max 50), contenu non vide (max 1000) |

`idUser`, `numCompte`, `numTransac`, `idCredit`, `idNotification`, `idPer`, `idRole`, `numAg` ne doivent **pas** être envoyés : ils sont générés par le serveur.

### Exemple d'erreur de validation

Requête :

```http
POST {{ _.base_url }}/api/v1/users
```

```json
{
  "prenomUser": "Api",
  "email": "pas-un-email",
  "phone": "699000000",
  "dateN": "1990-01-01T00:00:00",
  "sexe": "MASCULIN",
  "profession": "Etudiant",
  "password": "secret123"
}
```

Réponse `400 Bad Request` :

```json
{
  "erreur": "Validation echouee",
  "champs": {
    "nomUser": "Le nom est obligatoire",
    "email": "L'email doit etre valide"
  }
}
```

---

## 14. Exemples de réponses complètes

Ces exemples correspondent exactement à ce que l'API renvoie (identifiants et CNI générés côté serveur).

### Permission créée — `201 Created`

```json
{
  "idPer": 1,
  "nomPer": "CREATE_USER",
  "descriptionPer": "Permet de creer un utilisateur"
}
```

### Role créé — `201 Created`

```json
{
  "idRole": 1,
  "nomRole": "ADMIN",
  "description": "Administrateur",
  "permissionIds": [1]
}
```

### Agence créée — `201 Created`

```json
{
  "numAg": 1,
  "longitude": 11.5021,
  "latitude": 3.848,
  "nomAg": "Agence Yaounde Centre",
  "telAg": "222222222",
  "emailAg": "yaounde.centre@finmanager.cm"
}
```

### Utilisateur créé — `201 Created`

```json
{
  "idUser": "USER_A1B2C",
  "nomUser": "Nkondjo",
  "prenomUser": "Olivier",
  "email": "olivier.nkondjo@test.cm",
  "phone": "690000000",
  "dateN": "15-05-1995 00:00:00",
  "sexe": "MASCULIN",
  "profession": "Ingenieur",
  "password": "secret123",
  "roleIds": []
}
```

> `idUser` correspond à la CNI (`USER_XXXXX`) : c'est cet identifiant qui sert dans `/api/v1/users/{idUser}`.

### Compte créé — `201 Created`

```json
{
  "numCompte": 1,
  "solde": 50000,
  "intituleCompte": "Compte courant Olivier",
  "typeCompte": "COURANT",
  "dateOuverture": "03-11-2026",
  "statutCompte": "ACTIVE",
  "userId": "USER_A1B2C",
  "agencyId": 1
}
```

### Transaction créée — `201 Created`

```json
{
  "numTransac": 1,
  "typeTransac": "DEPOT",
  "dateTransac": "03-11-2026 10:00:00",
  "montantTransac": 10000,
  "statutTransac": "VALIDEE",
  "accountId": 1
}
```

### Crédit créé — `201 Created`

```json
{
  "idCredit": 1,
  "montantCredit": 200000,
  "dateCredit": "03-11-2026",
  "typeCredit": "CONSOMMATION",
  "motifCredit": "Achat de materiel",
  "accountId": 1
}
```

### Notification créée — `201 Created`

```json
{
  "idNotification": 1,
  "typeNot": "TRANSACTION",
  "contenuNot": "Depot de 10 000 FCFA effectue",
  "dateNot": "03-11-2026 10:01:00",
  "transactionId": 1
}
```

---

## 15. Checklist de test dans Insomnia

1. Créer une permission → récupérer `idPer` → `permissionId`
2. Créer un rôle avec `permissionIds: [{{ _.permissionId }}]` → récupérer `idRole` → `roleId`
3. Créer une agence → récupérer `numAg` → `agencyId`
4. Créer un utilisateur → récupérer `idUser` → `userId`
5. Créer un compte avec `userId` et `agencyId` → récupérer `numCompte` → `accountId`
6. Créer une transaction avec `accountId` → récupérer `numTransac` → `transactionId`
7. Créer un crédit avec `accountId` → récupérer `idCredit` → `loanId`
8. Créer une notification avec `transactionId` → récupérer `idNotification` → `notificationId`
9. Tester `GET` liste + `GET` par id sur chaque ressource
10. Tester `PUT` sur chaque ressource
11. Tester un `GET` sur un id inexistant → doit renvoyer `404`
12. Tester une ancienne route (`/api/users`, `/api/accounts`, etc.) → doit renvoyer `404`
13. Tester un body invalide (ex. email `pas-un-email`) → doit renvoyer `400`
14. Supprimer dans l'ordre : notification → crédit → transaction → compte → utilisateur → agence → rôle → permission

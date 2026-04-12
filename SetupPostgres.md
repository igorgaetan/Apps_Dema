
# Guide d'installation et de configuration de PostgreSQL (Local)

Ce guide récapitule les étapes pour installer PostgreSQL sur Ubuntu, configurer l'utilisateur par défaut et créer une base de données pour vos projets de développement.

## 1. Installation de PostgreSQL
Si PostgreSQL n'est pas encore installé ou si vous souhaitez réinstaller les composants nécessaires :

```bash
# Mise à jour des dépôts
sudo apt update

# Installation de PostgreSQL et des contributions (outils additionnels)
sudo apt install postgresql postgresql-contrib
```

## 2. Configuration de l'accès et de la base de données
L'installation crée un utilisateur système `postgres`. Nous l'utilisons pour configurer l'environnement.

```bash
# 1. Se connecter au shell PostgreSQL en tant que super-utilisateur
# Note : On précise le port 5433 car c'est celui détecté sur votre machine
sudo -u postgres psql -p 5433

# 2. Dans le shell psql (postgres=#), modifier le mot de passe de l'utilisateur
ALTER USER postgres WITH PASSWORD 'postgres';

# 3. Créer la base de données pour votre projet
CREATE DATABASE devops_demo;

# 4. Accorder tous les privilèges sur cette base à l'utilisateur postgres
GRANT ALL PRIVILEGES ON DATABASE devops_demo TO postgres;

# 5. Quitter le shell
\q
```

## 3. Test de la connexion
Une fois la configuration terminée, testez la connexion comme le ferait votre application Spring Boot :

```bash
# Connexion directe à la nouvelle base de données
# Le système vous demandera le mot de passe défini plus haut ('postgres')
psql -h localhost -p 5433 -U postgres -d devops_demo
```

## 4. Diagnostic des services et ports
Si vous avez un doute sur la version active ou le port utilisé :

```bash
# Lister les clusters PostgreSQL, leur version, port et statut
pg_lsclusters

# Vérifier quel processus écoute précisément sur les ports réseau
ss -nltp | grep postgres
```
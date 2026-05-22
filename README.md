## 🚀 DevOps Demo – API REST Java + H2 (In-Memory)

Projet pédagogique pour illustrer les notions de **ports**, **API REST**, et **cycle de vie d'une application**.

### 🏗️ Architecture

```
┌─────────────────┐      HTTP/JSON     ┌──────────────────┐      SQL/JPA      ┌─────────────────┐
│  Front HTML/JS  │  ←─────────────→   │  Spring Boot API │  ←─────────────→  │  H2 Database    │
│   port 5500     │                    │    port 8080      │                   │   (En mémoire)  │
└─────────────────┘                    └──────────────────┘                   └─────────────────┘
```



### 📋 Prérequis
- **Java 17+**
- **Maven 3.8+**
- **Navigateur Web** (Chrome/Firefox)

### ☕ 1. Démarrer le Back (Spring Boot)
Plus besoin d'installer PostgreSQL ! H2 est intégré au projet.

```bash
cd devops-demo
mvn spring-boot:run
```

> 💡 **Concept DevOps** : La base de données H2 démarre **en même temps** que l'application. Elle est stockée en mémoire vive (RAM). Si vous arrêtez l'application, les données sont réinitialisées.

### 🔍 2. Accéder à l'interface de la base (H2 Console)
Pour voir vos tables sans psql :
1. Allez sur : `http://localhost:8080/h2-console`
2. **JDBC URL** : `jdbc:h2:mem:devops_demo`
3. Cliquez sur **Connect** (pas de mot de passe).

### 🌐 3. Démarrer le Front (Port 5500)
Utilisez l'extension **Live Server** de VS Code sur `index.html`.

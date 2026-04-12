# 🚀 DevOps Demo – API REST Java + PostgreSQL

Projet pédagogique pour illustrer les notions **ports**, **API REST**, **communication application ↔ base de données**.

## 🏗️ Architecture

```
┌─────────────────┐      HTTP/JSON     ┌──────────────────┐      SQL/JPA      ┌─────────────────┐
│  Front HTML/JS  │  ←─────────────→   │  Spring Boot API │  ←─────────────→  │   PostgreSQL    │
│   port 5500     │                    │    port 8080      │                   │   port 5432     │
└─────────────────┘                    └──────────────────┘                   └─────────────────┘
```

## 📋 Prérequis

- Java 17+
- Maven 3.8+
- PostgreSQL 14+

Vérifier les installations :
```bash
java -version
mvn -version
psql --version
```

## 🐘 1. Configurer PostgreSQL

```bash
# Démarrer PostgreSQL
# Ubuntu :
sudo systemctl start postgresql

# macOS (Homebrew) :
brew services start postgresql@14

# Se connecter en tant que superuser
psql -U postgres

# Dans le shell PostgreSQL :
CREATE DATABASE devops_demo;
\q
```

> 💡 **Concept DevOps** : PostgreSQL écoute sur le **port 5432** par défaut.
> Ce port est configuré dans `src/main/resources/application.properties`.

## ☕ 2. Démarrer le Back (Spring Boot)

```bash
cd devops-demo

# Compiler et lancer
mvn spring-boot:run
```

Le serveur démarre sur **http://localhost:8080**

> 💡 **Concept DevOps** : Le port `8080` est configuré via `server.port=8080` dans `application.properties`.
> En production, on utilise souvent le port `80` (HTTP) ou `443` (HTTPS).

Vous verrez dans les logs :
```
✅ Base de données initialisée avec 5 produits de démo.
Tomcat started on port(s): 8080 (http)
Started DemoApplication in X.XXX seconds
```

## 🌐 3. Démarrer le Front

Option A – VS Code avec l'extension **Live Server** :
- Clic droit sur `frontend/index.html` → "Open with Live Server"
- S'ouvre automatiquement sur `http://localhost:5500`

Option B – Python (simple) :
```bash
cd frontend
python3 -m http.server 5500
# Ouvrir http://localhost:5500
```

> 💡 **Concept DevOps** : Le front tourne sur le **port 5500**, différent du back (**port 8080**).
> Le navigateur bloque par défaut les requêtes entre ports différents (CORS).
> C'est pourquoi on a configuré `CorsConfig.java`.

## 🧪 4. Tester l'API

### Avec le front HTML
Ouvrir `http://localhost:5500` et utiliser les formulaires.

### Avec curl (ligne de commande)

```bash
# GET – Lister tous les produits
curl -s http://localhost:8080/api/produits | python3 -m json.tool

# GET – Un produit par ID
curl -s http://localhost:8080/api/produits/1

# POST – Créer un produit
curl -s -X POST http://localhost:8080/api/produits \
  -H "Content-Type: application/json" \
  -d '{"nom": "Webcam HD", "prix": 59.99, "quantite": 15}'

# PUT – Modifier un produit
curl -s -X PUT http://localhost:8080/api/produits/1 \
  -H "Content-Type: application/json" \
  -d '{"nom": "Laptop Dell XPS 15", "prix": 1399.99, "quantite": 8}'

# DELETE – Supprimer un produit
curl -s -X DELETE http://localhost:8080/api/produits/1

# GET – Rechercher par nom
curl -s "http://localhost:8080/api/produits/search?q=laptop"
```

### Avec Postman / Insomnia
Importer ces endpoints :
- `GET    http://localhost:8080/api/produits`
- `GET    http://localhost:8080/api/produits/1`
- `POST   http://localhost:8080/api/produits`
- `PUT    http://localhost:8080/api/produits/1`
- `DELETE http://localhost:8080/api/produits/1`

## 📁 Structure du projet

```
devops-demo/
├── pom.xml                          # Dépendances Maven
├── frontend/
│   └── index.html                   # Interface web (port 5500)
└── src/main/
    ├── java/com/devops/demo/
    │   ├── DemoApplication.java     # Point d'entrée Spring Boot
    │   ├── model/
    │   │   └── Produit.java         # Entité JPA (table PostgreSQL)
    │   ├── repository/
    │   │   └── ProduitRepository.java  # Accès à la BD
    │   ├── service/
    │   │   └── ProduitService.java  # Logique métier
    │   ├── controller/
    │   │   └── ProduitController.java  # Routes HTTP (port 8080)
    │   └── config/
    │       ├── CorsConfig.java      # Autorise port 5500 → 8080
    │       └── DataLoader.java      # Données de démo
    └── resources/
        └── application.properties  # Config ports & BD
```

## 🎓 Concepts DevOps illustrés

| Concept | Où le voir dans le code |
|---------|------------------------|
| **Ports réseau** | `server.port=8080`, `datasource.url=...5432`, Live Server port 5500 |
| **API REST** | `ProduitController.java` – annotations `@GetMapping`, `@PostMapping`... |
| **Codes HTTP** | `ResponseEntity.ok()` (200), `.status(CREATED)` (201), `.notFound()` (404) |
| **JSON** | Corps des requêtes POST/PUT, toutes les réponses |
| **CORS** | `CorsConfig.java` – communication cross-origin entre ports |
| **Architecture 3 tiers** | Controller → Service → Repository → PostgreSQL |
| **ORM / JPA** | `Produit.java` avec `@Entity`, `@Table`, Spring génère le SQL |
| **Variables d'environnement** | `application.properties` – config externalisée |

## 🔧 Changer le port du back

Dans `application.properties` :
```properties
server.port=9090   # Changer ici
```

Mettre à jour aussi dans `frontend/index.html` :
```javascript
const BASE_URL = 'http://localhost:9090/api/produits';
```

Et dans `CorsConfig.java` :
```java
.allowedOrigins("http://localhost:5500", "http://localhost:3000")
```

> 💡 C'est exactement ce qu'on fait en DevOps : on externalise la config pour ne pas hardcoder les ports/URLs dans le code.

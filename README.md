# api

# Architecture serveur

Cette application Spring-boot est déployée sur la VM et accessible à l'adresse https://dome6.ensicaen.fr/api

Nginx est utilisé en tant que reverse proxy et envoie toutes les requêtes de /api vers le port 8080 dédiée à l'application Spring-boot.
Le certificat TLS est généré par Let's Encrypt (https://letsencrypt.org/) et géré ensuite par Nginx.

Lien vers le Swagger : https://dome6.ensicaen.fr/api/v1/swagger-ui.html

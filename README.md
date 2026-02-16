# Library Assignment

Ett digitalt bibliotek för att hantera böcker, ljudböcker, filmer och spel.

## Gruppmedlemmar
Fabian Berencreutz, Lukas Lundgren, Oscar Petterson, Erik Thorell.

## Starta projektet
Kör följande kommando i terminalen:
```bash
./mvnw spring-boot:run
```

## Miljövariabler
Följande variabler krävs för databaskopplingen:
* `DB_URL` (t.ex. `jdbc:postgresql://localhost:5432/db`)
* `DB_USERNAME`
* `DB_PASSWORD`

### Hur man sätter dem
* **Terminal:** Använd `export VAR_NAMN=värde`.
* **IntelliJ:** Lägg till under *Environment variables* i din Run Configuration.

# eb7401u202319440

Backend REST API for the **DCMonitor Platform** (Equinix operational monitoring). This project covers the **Assignments and Tracking** slice: managing assets and asset records for temperature monitoring in data center rooms.

**Author:** Dhilsen Armil Mallqui Vilca

## Overview

The API exposes two endpoints:

- `GET /api/v1/assets` — list all assets
- `POST /api/v1/asset-records` — register a new asset record

Assets are seeded automatically on startup. When a valid asset record is created, an integration event updates the related asset `preferredTemperature` if it differs from the record `targetTemperature`.

## Tech stack

- Java 25
- Spring Boot 3.5.8
- Spring Data JPA
- MySQL (schema: `equinix`)
- Lombok, springdoc-openapi

## Architecture

The solution follows **DDD + CQRS** with three bounded contexts:

| Context | Responsibility |
|---------|----------------|
| `shared` | Result pattern, snake_case naming strategy, global exception handler, `AssetTag` value object |
| `elite` | `Asset` aggregate, asset queries, seed data, event handler for preferred temperature updates |
| `monitoring` | `AssetRecord` aggregate, record creation, ACL to elite for asset existence checks |

Layers per context: `domain`, `application`, `infrastructure`, `interfaces.rest`.

## Database setup

1. Install and start MySQL locally.
2. The app creates the `equinix` database automatically (`createDatabaseIfNotExist=true`).
3. Update credentials in `src/main/resources/application.properties` if needed:

```properties
spring.datasource.username=root
spring.datasource.password=admin123
```

Tables are created/updated by Hibernate on startup. Initial assets are loaded via `ApplicationReadyEvent`.

## Run the project

```bash
./mvnw spring-boot:run
```

On Windows:

```bash
mvnw.cmd spring-boot:run
```

Default URL: `http://localhost:8080`

Swagger UI: `http://localhost:8080/swagger-ui.html`

## Endpoints

### GET /api/v1/assets

Returns all stored assets (without audit fields).

**Example response (200):**

```json
[
  {
    "id": 1,
    "assetTag": "SRV-EQX-SV-0001",
    "roomId": 12,
    "preferredTemperature": 22.0
  }
]
```

### POST /api/v1/asset-records

Creates an asset record. Returns `201 Created` with the generated record.

**Example request:**

```json
{
  "assetTag": "SRV-EQX-SV-0001",
  "operationMode": "ACTIVE",
  "targetTemperature": 22.5,
  "currentTemperature": 23.0,
  "assetState": "OPERATING",
  "generatedAt": "2026-07-12 10:30:00"
}
```

**Example response (201):**

```json
{
  "id": 1,
  "assetTag": "SRV-EQX-SV-0001",
  "operationMode": "ACTIVE",
  "targetTemperature": 22.5,
  "currentTemperature": 23.0,
  "assetState": "OPERATING",
  "generatedAt": "2026-07-12 10:30:00"
}
```

### Business rules (summary)

- `assetTag` must match Equinix format (e.g. `SRV-EQX-SV-0001`) and reference an existing asset
- `targetTemperature` between 18.0 and 27.0 °C; `currentTemperature` must be positive
- `generatedAt` format: `yyyy-MM-dd HH:mm:ss`, not in the future
- `operationMode`: `STAND_BY` or `ACTIVE`
- `assetState`: `OPERATING` or `IDLE`

### Error responses

Send `Accept-Language: en` or `Accept-Language: es` for English or Spanish validation messages.

## Project structure

```
src/main/java/com/equinix/platform/u202319440/
├── shared/          # Common infrastructure
├── elite/           # Assets bounded context
└── monitoring/      # Asset records bounded context
```

## Notes

- CORS, security, and automated tests are out of scope for this exam deliverable.
- Package the solution as `eb7401u202319440.zip` before submission.

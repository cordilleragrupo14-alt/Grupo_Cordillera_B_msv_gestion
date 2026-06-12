# ms-gestion-kpi
Microservicio de Gestión de Indicadores KPI — Grupo Cordillera | Puerto: 8082

## Variables de entorno (Render)
| Variable | Valor |
|----------|-------|
| `SPRING_DATA_MONGODB_URI` | `mongodb+srv://tomazamora_db_user:M8uMKBcal26Yzi1K@cluster0.ho4nu6m.mongodb.net/?appName=Cluster0` |
| `SPRING_DATA_MONGODB_DATABASE` | `cordillera_kpi` |

## Estados de un KPI
| Estado | Condición |
|--------|-----------|
| `EN_META` | >= 100% de la meta |
| `EN_PROGRESO` | >= 75% de la meta |
| `EN_RIESGO` | >= 50% de la meta |
| `CRITICO` | < 50% de la meta |

## Endpoints
| Método | Endpoint |
|--------|----------|
| GET | `/api/kpis` |
| GET | `/api/kpis/{id}` |
| GET | `/api/kpis/area/{area}` |
| GET | `/api/kpis/estado/{estado}` |
| POST | `/api/kpis` |
| PUT | `/api/kpis/{id}` |
| PATCH | `/api/kpis/{id}/valor` |
| DELETE | `/api/kpis/{id}` |

## Patrones implementados
- **Repository Pattern**: `KpiRepository`
- **Factory Method**: `KpiFactory`

## Ejecución local
```bash
mvn clean install && mvn spring-boot:run
```

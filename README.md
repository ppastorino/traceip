Ejercicio de Programación
==========================

La aplicación es es un servicio REST que expone dos endpoints

- Consulta de informacion asociada a una IP (datos del pais origen y moneda asociada) 
- Consulta de Estadísticas sobre las consultas realizadas

Fue desarrollada  utilizando:

- SpringBoot (https://spring.io/projects/spring-boot)


- APIs
    - Geolocalización de IPs: https://ip2country.info/
    - Información de paises: http://restcountries.eu/
    - Información sobre monedas: http://fixer.io/

- Redis (https://redis.io/)

Se utiliza para persistir los resultados de las invocaciones a las APIS
Se almacenan los resultados de países y monedas.

# Diseño

La aplicación está dividida en las capas

## Dominio
Package com.challenge.traceip.domain
POJOs (Plain Old Java Objects) con la informacion que maneja la aplicación.

## Persistencia
Package  com.challenge.traceip.repository
Implementación de los repostirios basados en Redis.
  
- Repositorio para informacion de paises (CountryRepository)
  Utiliza operaciones de get y set con Strings
       
- Repositorio para informacion de paises (CurrencyRepository)
  Utiliza operaciones de get y set con Strings
        
- Repositorio para contadores de consultas(QueryCounterRepository)
  Utiliza operaciones de incremento y scan
      
## Servicio
Package com.challenge.traceip.service

- El servicio principal esta implementado en la clase *com.challenge.traceip.service.TraceIpService*
- Se utiliza RestTemplate para la invocación a las diferentes APIs
- Se minimizan las llamadas a las APIs mediante caching (ver com.challenge.traceip.service.CachedValueService) 
       
## Rest
Package com.challenge.traceip.rest

La clase *com.challenge.traceip.rest.ApiResource* implementa el API de consulta


# Generación de Imagen Docker

Ejecutar desde el directorio raiz del proyecto

> docker build -t traceip .


# Ejecución

Se provee un archivo docker-compose para levantar la aplicación junto con el servicio Redis

Ejecutar desde el directorio raiz del proyecto

> docker-compose up

# Uso 

- Consulta de informacion asociada a una IP 

Ejecutar:

> curl -s localhost:8001/api/trace?ip={ip-address}

Ejemplos

> curl -s localhost:8001/api/trace?ip=200.147.36.65
> curl -s localhost:8001/api/trace?ip=5.6.7.8
> curl -s localhost:8001/api/trace?ip=23.205.127.43

Resultado:

{
  "country" : {
    "code" : "US",
    "timeZones" : [ "UTC-12:00", "UTC-11:00", "UTC-10:00", "UTC-09:00", "UTC-08:00", "UTC-07:00", "UTC-06:00", "UTC-05:00", "UTC-04:00", "UTC+10:00", "UTC+12:00" ],
    "languages" : [ {
      "code" : "en",
      "name" : "English"
    } ],
    "distance" : 9002
  },
  "currency" : {
    "code" : "USD",
    "base" : "EUR",
    "rate" : 0.916
  }
 }


- Consulta de Estadísticas sobre las consultas realizadas

Ejecutar

> curl localhost:8001/api/stats




# TODO

- Manejo de errores
  No se están manejando errores (problemas de comunicación con APIs, respuesta de error de las APIs, problemas con persistencia)

- Logging

- Configuracion de Redis (persistencia, TTL, etc)

- La información de monedas incluye la tasa de conversión que es una información relativamente dinámica.
  Una mejora a futuro es configurar el TTL específico en esas entradas para 'refrescar' la información.  

- Usar cache local (en memoria) para evitar acceso a Redis (para paises y monedas)
  Esto podría mejorar la consulta de estadísticas.

- Usar cache para la consulta de IP (ip). Esto se justificaría si se detectan muchas invocaciones con las mismas ips.

- Tests Unitarios y de Integración
 
- Analizar si es correcto utilizar método GET para la consulta de IP (tiene efecto secundario)
 

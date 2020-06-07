IP Tracing using APIs + Redis
===============================

The application is a REST service exposing two endpoints

- Query IP related info: country, currency, distance to Buenos Aires 
- Query stats about API usage 

# Technologies

- SpringBoot (https://spring.io/projects/spring-boot)

- APIs

    - Ge Location: https://ip2country.info/
    - Country Info: http://restcountries.eu/
    - Currency Info: http://fixer.io/

- Redis (https://redis.io/)

I use Redis to persist API invocation results


# Docker Image

Execute

> docker build -t traceip .


# Running the application


Execute

> docker-compose up

# Usage 

- Query IP info 


> curl -s localhost:8001/api/trace?ip={ip-address}

Samples

> curl -s localhost:8001/api/trace?ip=200.147.36.65

> curl -s localhost:8001/api/trace?ip=5.6.7.8

> curl -s localhost:8001/api/trace?ip=23.205.127.43

Result:

```
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
```

- Query API usage stats

> curl -s localhost:8001/api/stats

Resut:

```
{
  "maxDistance" : 11566,
  "maxDistanceCountry" : "DE",
  "minDistance" : 520,
  "minDistanceCountry" : "AR",
  "avg" : 8177.692
}
```



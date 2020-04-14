https://spring.io/guides/gs/spring-boot-docker/

https://spring.io/guides/gs/consuming-rest/

Get-Content Dockerfile | docker build -t traceip .

docker run traceip

docker run -p 8888:8888 -e JAVA_OPTS=-Dserver.port=8888 traceip
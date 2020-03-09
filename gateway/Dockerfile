FROM java:8
LABEL MAINTAINER="smazconnect@gmail.com"

ADD target/gateway.jar /app/

EXPOSE 4000
ENTRYPOINT ["java", "-Xmx200m", "-jar", "/app/gateway.jar"]



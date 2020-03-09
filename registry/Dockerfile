FROM java:8
LABEL MAINTAINER="smazconnect@gmail.com"

ADD target/registry.jar /app/

CMD curl -f http://ms-config:8000/actuator/health

ENTRYPOINT ["java", "-Xmx200m", "-jar", "/app/registry.jar"]

EXPOSE 8761

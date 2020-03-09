FROM java:8
LABEL MAINTAINER="smazconnect@gmail.com"

ADD target/config.jar /app/

ENTRYPOINT ["java", "-Xmx200m", "-jar", "/app/config.jar"]

HEALTHCHECK --interval=30s --timeout=30s CMD curl -f http://localhost:8000/actuator/health || exit 1

EXPOSE 8000

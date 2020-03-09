# spring-ms

- Start with config, regitsry and gateway dokcer build and run image. Create docker network so applicaton can access it by name and attach host machine volume to docker network for cofig service native 
 
 ```
 docker build --no-cache -t config:v1.0 .
 docker run -v <path to project>/ms-practice/config/src/main/resources/shared/:/app/shared --name config --network my-net -p 8000:8000 -t config:v1.0 
 

docker build --no-cache -t registry:v1.0 .
docker run --name registry --network my-net -p 8761:8761 -t registry:v1.0

docker build -t gateway:v1.0 .
docker run --name gateway --network my-net -p 4000:4000 -t gateway:v1.0 

 ```


- Kafka setting in local. 
```
zookeeper-server-start <kafka_home>/etc/zookeeper.properties
kafka-server-start.sh <kafka_home>/etc/config/server.properties
schema-registry-start <kafka_home>/etc/schema-registry/schema-registry.properties
```

- For Avro user class will be created with below maven plugin
```
  <plugin>
                <groupId>org.apache.avro</groupId>
                <artifactId>avro-maven-plugin</artifactId>
                <version>1.8.2</version>
                <executions>
                    <execution>
                        <phase>generate-sources</phase>
                        <goals>
                            <goal>schema</goal>
                        </goals>
                        <configuration>
                            <sourceDirectory>src/main/resources/avro</sourceDirectory>
                            <outputDirectory>${project.build.directory}/generated-sources</outputDirectory>
                            <stringType>String</stringType>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
```

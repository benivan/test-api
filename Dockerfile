FROM openjdk:16
WORKDIR /spring-security


#COPY .mvn/ .mvn
#COPY mvnw pom.xml ./
#RUN ./mvnw dependency:go-offline
#
#COPY src ./src

COPY . .

#RUN ./mvnw package
#
#RUN cd target

ENTRYPOINT ["java","-jar" ,"target/security.jar"]
FROM openjdk:16
WORKDIR /app

#COPY .mvn/ .mvn
#COPY mvnw pom.xml ./
#RUN ./mvnw dependency:go-offline
#
#COPY src ./src

COPY . .

RUN ./mvnw package

RUN cd target

CMD ["java","-jar" ,"security.jar"]
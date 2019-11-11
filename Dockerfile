FROM openjdk:8-jre-slim
  
RUN mkdir /app

WORKDIR /app

ADD ./api/target/image-uploader-api-1.0.0-SNAPSHOT.jar /app

EXPOSE 8081

CMD java -jar image-uploader-api-1.0.0-SNAPSHOT.jar


FROM openjdk:8-alpine

COPY . /opt/petclinic

CMD java -Dapp.home=/opt/petclinic-home -Dspring.profiles.active=docker -jar /opt/petclinic/clinic.jar

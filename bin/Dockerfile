FROM tomcat:9.0.16-jre8-alpine

LABEL maintainer="ciputra.pangestu@soluix.ai"

RUN apk add nano

COPY /some-app/target/some-app.war /usr/local/tomcat/webapps/
COPY ./tomcat-users.xml  /usr/local/tomcat/conf/
COPY ./web.xml /usr/local/tomcat/webapps/manager/WEB-INF
COPY ./context.xml /usr/local/tomcat/webapps/manager/META-INF
COPY ./database.properties /usr/local/tomcat/webapps/recon-app/WEB-INF/classes

EXPOSE 8080

CMD ["catalina.sh","run"]
FROM adam/java
MAINTAINER Adam Bien, adam-bien.com
ENV PAYARA_ARCHIVE payara-micro
ENV INSTALL_DIR /opt
RUN curl -o ${INSTALL_DIR}/${PAYARA_ARCHIVE}.jar -L "https://s3-eu-west-1.amazonaws.com/payara.fish/Payara+Downloads/Payara+4.1.2.172/payara-micro-4.1.2.172.jar"
ENV PAYARA_HOME ${INSTALL_DIR}
ENV DEPLOYMENT_DIR ${PAYARA_HOME}
ENTRYPOINT java -jar ${PAYARA_ARCHIVE}.jar --deploy ${ARCHIVE_NAME}
WORKDIR ${INSTALL_DIR}
EXPOSE 8080 5900

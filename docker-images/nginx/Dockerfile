FROM adam/java
MAINTAINER Adam Gamboa
RUN yum -y install epel-release
RUN yum -y install nginx \
    && yum clean all
ENTRYPOINT nginx -g "daemon off;"
EXPOSE 80

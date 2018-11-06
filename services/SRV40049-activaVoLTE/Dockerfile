FROM 10.120.1.131:5000/fuse7-java-openshift-appdynamics:latest

LABEL maintainer="WOM" \
    author="VSTS"

USER jboss

ADD target/*.jar /deployments/

EXPOSE 8888

USER 185

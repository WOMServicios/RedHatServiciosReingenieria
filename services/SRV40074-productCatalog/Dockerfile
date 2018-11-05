FROM cllnxsatellite.novalte.corp:5000/wom-ocp_docker_images-fuse7_fuse-java-openshift:1.1

LABEL maintainer="WOM" \
    author="VSTS"

USER jboss

ADD target/*.jar /deployments/

EXPOSE 8888 8889

USER 185

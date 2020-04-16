#
#  Copyright Pnoker. All Rights Reserved.
#

FROM maven:3.6-jdk-8 AS build
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
    && mkdir -p /boot/project

ADD ./src /boot/project/src
ADD ./pom.xml /boot/project/
COPY ./project/settings.xml /usr/share/maven/ref/settings-docker.xml

RUN cd /boot/project \
    && mvn -s /usr/share/maven/ref/settings-docker.xml package \

FROM anapsix/alpine-java:8_server-jre_unlimited AS base
MAINTAINER pnoker <pnokers.icloud.com>

ENV JAVA_OPS -server -Xms128m -Xmx512m -XX:CompressedClassSpaceSize=128m -XX:MetaspaceSize=200m -XX:MaxMetaspaceSize=200m

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
    && mkdir -p /boot/project

WORKDIR /boot/project

COPY --from=build /boot/project/target/boot-project.jar ./

CMD java ${JAVA_OPS} -Djava.security.egd=file:/dev/./urandom -jar ./boot-project.jar

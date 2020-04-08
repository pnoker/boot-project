#
#  Copyright Pnoker. All Rights Reserved.
#

FROM maven:3.6-jdk-8 AS build
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
    && mkdir -p /yoi/emulator

ADD ./src /yoi/emulator/src
ADD ./pom.xml /yoi/emulator/
COPY ./emulator/settings.xml /usr/share/maven/ref/settings-docker.xml

RUN cd /yoi/emulator \
    && mvn -s /usr/share/maven/ref/settings-docker.xml package \

FROM anapsix/alpine-java:8_server-jre_unlimited AS base
MAINTAINER pnoker <pnokers.icloud.com>

ENV JAVA_OPS -server -Xms128m -Xmx512m -XX:CompressedClassSpaceSize=128m -XX:MetaspaceSize=200m -XX:MaxMetaspaceSize=200m

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
    && mkdir -p /yoi/emulator

WORKDIR /yoi/emulator

COPY --from=build /yoi/emulator/target/test-data-emulator-worker.jar ./

CMD java ${JAVA_OPS} -Djava.security.egd=file:/dev/./urandom -jar ./test-data-emulator-worker.jar

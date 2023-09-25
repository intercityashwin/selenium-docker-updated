FROM bellsoft/liberica-openjdk-alpine:17.0.8

# install curl and jq
RUN apk add curl jq

# workspace
WORKDIR /home/selenium-docker

# add / copy files
ADD target/docker-resources ./

ADD runner.sh runner.sh

#start the runner sh file

ENTRYPOINT sh runner.sh
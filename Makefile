projects := $(shell ls ${MYDIR} | grep fj33)
java-projects := $(shell ls ${MYDIR} | grep fj33 | awk '!/ui/')
MVNC := cd $${f} && mvn clean && cd ..
MVNCI := cd $${f} && mvn clean install && cd ..
MYDIR := .
MAINTAINER := alefh
IMAGE_NAME := $(shell basename $(PWD))
DOCKER_IMAGE_IDS := $(shell docker images | grep fj33 | awk '{print $$3}')

up:
	docker-compose up -d

down:
	docker-compose down

build: clean mvn/build ng/build docker/images

clean: ng/clean mvn/clean
	@echo "finish cleanup for all projects"

mvn/clean:
	@for f in $(java-projects); do $(MVNC); done

mvn/build:
	@for f in $(java-projects); do $(MVNCI); done

ng/build:
	cd fj33-eats-ui && npm run build

ng/clean:
	cd fj33-eats-ui && rm -rf dist

docker/images:
	@for f in $(projects); do cd $${f}; docker build . -t $(MAINTAINER)/$${f} && cd .. ; done

docker/images/remove:
	@echo "deleting all images from disk"
	@for id in $(DOCKER_IMAGE_IDS); do docker rmi $${id}; done

docker/images/push:
	@for f in $(projects); do cd $${f}; docker push $(MAINTAINER)/$${f} && cd .. ; done

docker/images/pull:
	@for f in $(projects); do cd $${f}; docker pull $(MAINTAINER)/$${f} && cd .. ; done
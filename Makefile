projects := $(shell ls ${MYDIR} | grep fj33)
java-projects := $(shell ls ${MYDIR} | grep fj33 | awk '!/ui/')
MVNC := cd $${f} && mvn clean && cd ..
MVNCI := cd $${f} && mvn clean install && cd ..
MYDIR = .

list: $(MYDIR)/*
	for file in $^ ; do \
        echo "Hello" $${file} ; \
    done

mvn-clean:
	@for f in $(java-projects); do $(MVNC); done

mvn-build:
	@for f in $(java-projects); do $(MVNCI); done

ng-build:
	cd fj33-eats-ui && npm run build

ng-clean:
	cd fj33-eats-ui && rm -rf dist
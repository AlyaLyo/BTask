#SOURCES = $(shell find src -type f -name "*.java")
SOURCES = src/Main.java
CLASSES = $(patsubst src/%.java,out/%.class,$(SOURCES))
MAINCLASS = Main

all: build

run:
	java -cp out ${MAINCLASS}

pack:
	zip task_c.zip -r Makefile src lib

clean:
	rm -rf out

build:
	mkdir "out"
	javac -cp src ${SOURCES} -d out
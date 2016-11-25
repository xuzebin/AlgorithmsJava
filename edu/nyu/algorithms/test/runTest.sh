#!/bin/bash
green='\033[0;32m'
white='\033[0m'

filename=$(basename $1)
ext="${filename##*.}"

if [ $# -eq 1 ]
then
    if [ $ext == "java" ]
    then
	echo -e "${green}Compiling the test...${whtie}"
	javac -Xlint -cp .:../graph:junit-4.12.jar $1

	echo -e "${green}Running the test:${white}"
	java -cp .:../graph:junit-4.12.jar:hamcrest-core-1.3.jar org.junit.runner.JUnitCore ${1%.java}
    else
	echo "extension should be .java"
    fi
else
    echo "Please specify the name of the test file(*.java) as an argument"
fi

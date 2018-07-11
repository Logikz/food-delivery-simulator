# CS665 Summer 2018 Assignment 2

This is assignment 2 for CS665 Summer 2018.  The following readme can be used to guide the user 
to install and run the application using Java.  

-Nick Cuneo


# Program Assumptions

TBD

# Implementation Description


# Setup
 
You need to install Apache Maven (https://maven.apache.org/)  on your system.

# Compilation 

Compilation and packaging is done with maven, and is easily ran from windows or linux with one of
the following scripts.

## Windows
```bash
compile.bat
```

## Linux

```bash
./compile.sh
```


# Running

The application can be ran by executing one of the following scripts on windows or linux.

## Windows

```bash
run.bat
```

## Linux

```bash
./run.sh 
```

# Using Findbugs 

To see bug detail using the Findbugs GUI, use the following command "mvn findbugs:gui"

Or you can create a XML report by using  


```bash
mvn findbugs:gui 
```

or 


```bash
mvn findbugs:findbugs
```

# Run Checkstyle 

To analyse this example using CheckStyle run 

```bash
mvn checkstyle:check
```


CheckStyle code styling configuration files are in config/ directory. Maven checkstyle plugin is set to use google code style. You can change it to other styles like sun checkstyle. 

# Unit Tests

A sampling of unit tests are included to show functionality and to illustrate the design patterns
ability to make testing easier.  The tests themselves are by no means complete nor offer full coverage
of the assignment as it was not requested.





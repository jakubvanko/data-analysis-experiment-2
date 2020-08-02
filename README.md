# **Data analysis experiment**

This repository contains code and a compiled jar file for a simple data analysis prototype created as a part of a scientific experiment.

#### Table of Contents

- [Introduction](#introduction)
- [How to use the prototype](#instructions)
- [Documentation](#documentation)

## Introduction

The prototype was developed in IntelliJ IDEA Ultimate. Maven was used as the build and compilation tool. The jar file contains all dependencies needed to run the prototype. This repository contains all the custom maven code, however, since I used the maven tool integrated in my IDE, there is a possibility that it somehow interacted with the maven configuration. I do not have enough knowledge of maven to determine whether or not that is a case.

The prototype was thoroughly tested, but only manually, as I believe that unit tests would be an overkill for such a small prototype. Even though the prototype contains some argument checking procedures, it presumes that users are not going to try to break its security and are going to supply mostly valid arguments.

The prototype should meet all of the requirements specified in the assignment.

## Instructions

The tool has a help screen that can be triggered by using it without any arguments:
```
java -jar da-tool.jar
```
![](https://ctrlv.cz/shots/2020/08/02/56NS.png)

### Options and parameters

The options can be specified almost in any order, however, it is advised to state them in the order as specified in the asignment file.

##### -d

Represents a path to a local or remote CSV file. The only remote path supported is an HTTP/HTTPS link. It is advised to wrap the path in double quotes ("). If a remote dataset is specified, it will be downloaded before other options and parameters are checked. It is vital to only use csv files without header!

##### -m

Represents the manipulation methods. There are currently 5 supported manipulation methods:
- REMOVE_EMPTY_EMAIL
- REMOVE_EMPTY_ADDRESS 
- TOTAL_PRICE_YEAR 
- AVERAGE_PRICE_YEAR 
- TOP_THREE_CUSTOMERS

The methods will be chained one after another in the order specified in the command line. At least one method has to be specified for the tool to work.

##### -o

Represents the output type of the output file (the extension). The only type currently supported is "plain" and it will be chosen even if a different option is specified.

##### Positional parameter \<outputFile\>

Specifies the path of the output file. It should be specified as the last parameter following the -o flag. It is advised to wrap the path in double quotes (").

### Examples

The following are tested examples of the prototype usage:

```
java -jar da-tool.jar -d "https://is.muni.cz/el/fi/jaro2020/PV260/um/seminars/java_groups/initial_assignment/orders_data.csv" -m REMOVE_EMPTY_EMAIL TOTAL_PRICE_YEAR TOP_THREE_CUSTOMERS -o plain "target/result.txt"
```
```
java -jar da-tool.jar -d "C:\Users\main\Desktop\Test\temp.bla" -m REMOVE_EMPTY_EMAIL REMOVE_EMPTY_ADDRESS AVERAGE_PRICE_YEAR -o plain "result"
```
```
java -jar da-tool.jar -d "C:\Users\main\Desktop\Test\temp.bla" -m REMOVE_EMPTY_EMAIL REMOVE_EMPTY_ADDRESS AVERAGE_PRICE_YEAR TOP_THREE_CUSTOMERS TOTAL_PRICE_YEAR -o plain "result.txt"
```

## Documentation

I did my best to write self-documenting code. I made use of a lot of libraries (most notably picocli and tablesaw) and most of the logic was outsorced to stated dependencies. I made use of some simpler design patterns (Strategy, Factory) to make the quality of the prototype code as great as possible.
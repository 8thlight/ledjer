#Ledjer
To get started, clone it, then create your own branch with a unique name.

```
git clone https://github.com/8thlight/ledjer
git checkout -b <your username>
```

## Requirements
Make sure you have the follwoing installed:
  * at least Java 8
  * Maven 3 http://maven.apache.org/download.cgi
  * Eclipse IDE for Java Developers - Luna

To test that you have the requirements installed and configured correctly, make sure you get an output similar to the following:

Java:
```
$ java -version
java version "1.8.0_40"
Java(TM) SE Runtime Environment (build 1.8.0_40-b27)
Java HotSpot(TM) 64-Bit Server VM (build 25.40-b25, mixed mode)
```

Maven:
```
$ mvn -v
Apache Maven 3.2.3 (33f8c3e1027c3ddde99d3cdebad2656a31e8fdf4; 2014-08-11T21:58:10+01:00)
Maven home: /usr/local/Cellar/maven/3.2.3/libexec
Java version: 1.8.0_40, vendor: Oracle Corporation
Java home: /Library/Java/JavaVirtualMachines/jdk1.8.0_40.jdk/Contents/Home/jre
Default locale: en_US, platform encoding: US-ASCII
OS name: "mac os x", version: "10.9.5", arch: "x86_64", family: "mac"
```

Gradle:
```
$ gradle -v

------------------------------------------------------------
Gradle 2.4
------------------------------------------------------------

Build time:   2015-05-05 08:09:24 UTC
Build number: none
Revision:     5c9c3bc20ca1c281ac7972643f1e2d190f2c943c

Groovy:       2.3.10
Ant:          Apache Ant(TM) version 1.9.4 compiled on April 29 2014
JVM:          1.8.0_40 (Oracle Corporation 25.40-b25)
OS:           Mac OS X 10.9.5 x86_64
```

The versions on your system should differ only in their _minor version_ (that's the last number, i.e., 0_40 for Java above).

## Importing ledjer into Eclipse
When you open Eclipse for the first time it will ask you where to place its "workspace".
This is only relevant when creating new projects from within Eclipse.
For this excercise its precise location is not critical, so simply accept Eclipse's default.

To import ledjer into Eclipse:

### From maven
File > Import > Maven > Existing Maven Project
In the input field for the _Root Directory_ navigate to the ledjer folder.
Then proceed to select the pom.xml file in _Projects:_ box.
Hit _Finish_ as the last step.

### From Gradle
First run `gradle eclipse` in the terminal inside the ledjer folder.
File > Import > General > Existing Projects into Workspace
In the input field for the _Select root directory_ navigate to the ledjer folder.
_ledjer_ should appear selected in the _Projects_ list.
Hit _Finish_ as the last step.

## Build and run tests using Eclipse
To run all the tests, right-click on the top-level _ledjer_ folder and click _Run as_ and then _JUnit test_.
That will recompile the classes and, assuming there are no compile errors, run the JUnit tests and Cucumber features.

## Run Cucumber features from Gradle
`gradle cucumber`

## Cucumber plugin for Eclipse
This plugin is not required for the exercises.
It provides syntax highlighting and code jumping from cucumber features to java step definitions (right-click and select _Find Step_).
More information can be found in its [homepage](http://cucumber.github.io/cucumber-eclipse/).

To install it in Eclipse, go to Help > Install New Software.
In the install dialog, copy the update site (`http://cucumber.github.com/cucumber-eclipse/update-site`) in the field _Work with_.
That will show a list with the available software from that site, select _Cucumber Eclipse Plugin_, then continue clicking _Next_ and accept the license terms if you agree.
After finishing, a security warning about unsigned software might appear. Click _OK_ to continue installing.
Finally, Eclipse will ask to restart to apply the changes.

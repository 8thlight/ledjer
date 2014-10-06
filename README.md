ledjer
======

Ledjer - exercises for Java training course

== Requirements
Make sure you have the follwoing installed:
  * at least Java 7
  * Maven 3 http://maven.apache.org/download.cgi
  * Eclipse IDE for Java Developers - Luna

To tests that you have the requirements installed and configured correctly, make sure you get the following outputs:

Java:
$ java -version
java version "1.7.0_45"
Java(TM) SE Runtime Environment (build 1.7.0_45-b18)
Java HotSpot(TM) 64-Bit Server VM (build 24.45-b08, mixed mode)

Maven:
$ mvn -v
Apache Maven 3.2.3 (33f8c3e1027c3ddde99d3cdebad2656a31e8fdf4; 2014-08-11T21:58:10+01:00)
Maven home: /usr/local/Cellar/maven/3.2.3/libexec
Java version: 1.7.0_45, vendor: Oracle Corporation
Java home: /Library/Java/JavaVirtualMachines/jdk1.7.0_45.jdk/Contents/Home/jre
Default locale: en_US, platform encoding: US-ASCII
OS name: "mac os x", version: "10.9.5", arch: "x86_64", family: "mac"

The versions on your system should at most differ in their _minor version_.


== Importing ledjer into Eclipse
When you open Eclipse for the first time it will ask you where to place its "workspace".
This is only relevant when creating new projects from within Eclipse.
For this excercise its precise location is not critical so simply accept Eclipe's default.

Importing ledjer into Eclipse is fairly easy:
File > Import > Maven > Existing Maven Project
In the input field for the _Root Directory_ navigate to the ledjer folder.
Then proceed to select the pom.xml file in _Projects:_ box.
Hit _Finish_ as the last step.

== Build and run tests using Eclipse
To run all the tests, right-click on the top-leve _ledjer_ folder and click _Run as_ and then _JUnit test_.
That will recompile the classes and, assuming there are no compile errors, run the JUnit tests.



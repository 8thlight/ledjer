ledjer
======

Ledjer - exercises for Java training course

## Opening in Eclipse
* Create a workspace.
* Clone the Github repository
* Copy the cloned repository into the directory that your workspace points to (include hidden files / directories, if possible, i.e., .git)

## Adding JUnit
* if after opening the project for the first time in eclipse, you may be missing the JUnit dependency
 * Properties->Java Build Path->Add Library..., choose JUnit 4

## Build and Run Tests using Maven
`$ mvn test`


## Opening in IntelliJ
* Clone the Github repository
* Open IntelliJ and navigate to File > New Project
* In project location, choose the location where the cloned repo is located

## Adding JUnit with IntelliJ
* Navigate to the fixtures directory in the src folder and open a Test file
* Inside of the test file you'll see red text in the imports, for example
  `import org.junit.Before;` might be red, if it is, continue reading, if not go
  to the next section

* Now, click on the red text, and press `Alt` `Enter` and select `Create Test`
  from the options that pop up at your cursor
* When you're inside of the create tests dialog, select JUnit 4, and you should
  see a message that says the library isn't included in the modules, so press the button
  that says 'Fix', for more information check out [this link](http://www.jetbrains.com/idea/webhelp/configuring-testing-libraries.html).

## Running Your Tests using Maven
* If you already have maven, then just run `$ mvn test` in the ledjer directory
* If not, `$ brew install maven`, or check out the [maven website](http://maven.apache.org/what-is-maven.html)






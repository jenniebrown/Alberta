# Introduction
Alberta is a light weight point of sale system intended for use in small retail stores. This manual is intended for those with Java skills who wish to extend the current code base. It does not go into detail about how the system works and into the specifics of each function.

# Setup
#### System requirements
  - JDK 7
  - sqlite3
    - sqlite ```.db``` file such as the included ```PoS.db```
    - ```sqlite-jdbc-3.8.11.1.jar```
#### Building from source
Use the included Makefile
  - `make` to build
  - `make run` to build and run
``` makefile
default:
	javac -cp ./:sqlite-jdbc-3.8.11.1.jar alberta/*.java
run: default
	java -cp ./:sqlite-jdbc-3.8.11.1.jar alberta/UserInterface
clean: 
	rm -f ./alberta/*.class
```
# Changes
All user interactions with the code are through `UserInterface.java`. If you would like to change the look or additional features, changes will need to be made there.

`DatabaseHandler.java` is responable for all direct interaction with the **sqlite** database. There is where you should look if you would like to make changes to the code that would effect the database.
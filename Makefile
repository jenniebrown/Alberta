default:
	javac -cp ./:sqlite-jdbc-3.8.11.1.jar alberta/*.java
run: default
	java -cp ./:sqlite-jdbc-3.8.11.1.jar alberta/UserInterface
clean: 
	rm -f ./alberta/*.class

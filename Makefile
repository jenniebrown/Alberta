default: alberta.class Register.class ProductCatalog.class DatabaseHandler.class Order.class Item.class SalesLineItem.class Payment.class

alberta.class: alberta.java
	javac -d . -classpath . alberta.java

Register.class: Register.java
	javac -d . -classpath . Register.java

ProductCatalog.class: ProductCatalog.java
	javac -d . -classpath . ProductCatalog.java

DatabaseHandler.class: DatabaseHandler.java
	javac -d . -classpath . DatabaseHandler.java

Order.class: Order.java
	javac -d . -classpath . Order.java

Item.class: Item.java
	javac -d . -classpath . Item.java

SalesLineItem.class: SalesLineItem.java
	javac -d . -classpath . SalesLineItem.java

Payment.class: Payment.java
	javac -d . -classpath . Payment.java

clean: 
	rm -f *.class

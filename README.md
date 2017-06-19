## Payara-micro-demo

Payara is a Java EE Application Server. Initially created as a fork of Glassfish server after the end of its commercial support by Oracle. Payara Server offers differents profiles: Payara Server full, Payara Server Web, Payara Server Embedded and Payara Micro. 

# What's Payara Micro?
Payara Micro is designed for running Java EE applications in a modern containerized/ virtualized infrastructure. It allows you to create micro-service applications in the Java EE environment using modern and lightweight technologies.

It’s a JAR file that enables you to run WAR files from command line without any installation. Once you have downloaded the payara-micro jar file, and you have created your Java EE web application war, you only need to use the command `java -jar payara-micro.jar --deploy my_application.war` in order to run your application.

Payara Micro server will automatically cluster with other servers on the network, your Java EE application can be elastically scaled horizontally by adding and removing containers based on demand.

## This repository
In this repository you will find 3 folders: 

* *Presentation:* It contains the Key Note file with the slides and information showed during the Lunch & Learn presentation.

* *Docker Images:* Some base docker images used in the code. 

* *Code:* This folder has the two projects used during the demo to show the different features of payara-micro. One of them to show how easy is to develop a Rest-Api and a WebSocket endpoint in Java EE and how payara micro is able to provide this JSR. The other one allows you to check how the automatic clustering feature of payara micro works, sharing the session id through all the instances of the same application.

#More Info
If you want to know more about payara-micro and its features, you can visit the [Payara Micro Web site](http://www.payara.fish/payara_micro).

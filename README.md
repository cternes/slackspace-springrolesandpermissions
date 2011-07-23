Roles and Permissions with Spring-Security 3
=========================================

Run with standalone Jetty
-------------------------

To run the project with maven and Jetty simply type the following on the command line:  

    mvn clean compile package jetty:run

After the deployment you can access the project with your browser at http://localhost:8080

Run with another container
----------------------------
When running with another container than Jetty, you have to deploy the war-file manually.
You can generate the war-file by typing on the command line:
 
    mvn clean compile package

Now you will find the war-file in the target directory of the project.



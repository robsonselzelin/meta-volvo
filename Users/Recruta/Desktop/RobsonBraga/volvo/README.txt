1 - Download the Java Development Kit (JDK) 8:

* http://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html
* Assuming that the computer that is going to run the JDK is a Windows 32 or 64 bits (it must match the Operation System version), choose the appropriate JDK version
* Run and install the downloaded package.

2 - Download the Eclipse IDE (Integrated Development Environment):

* https://www.eclipse.org/downloads/eclipse-packages/
* Download the version matching the JDK's word size (32 or 64 bits)
* Unzip Eclipse package for a folder called eclipse-neon, for example, and move that folder to c:\
* The result must be something like this: c:\eclipse-neon
* Create a shortcut to eclipse.exe on the Windows Desktop to ease Eclipse access

3 - Clone repository:

* Open Eclipse IDE using the shortcut created in step 2;
* Create a workspace to the Example, like: c:\Users\Your_User\workspace\Volvo
* Open the following menu: File -> Import -> Projects from git -> Clone URI -> https://github.ibm.com/robsonselzelin/meta-volvo.git -> Import as general project
* Go to the 'Package Explorer': right click on 'volvo' -> 'Configure' -> 'Convert to Maven Project'
* Now import the project using: right click on 'volvo' -> 'Import...' -> 'Existing Maven Projects' 
* Wait for all the dependencies to be downloaded


5 - Download and install Tomcat Container
* Download Tomcat v8 matching the JDK's word size (32 or 64 bits) at: https://tomcat.apache.org/download-80.cgi
* Extract the content to a folder. Copy the tomcat folder to generate a new folder
* Go to Eclipse, File -> New -> Server
* Select Tomcat 8 in the local directory where tomcat was decompressed
* Add the project volvo to it during the server registration process

6 - Download and install MySQL Database Server
* Downloand MySQL server last version in: https://dev.mysql.com/get/Downloads/MySQLInstaller/mysql-installer-community-5.7.20.0.msi
* After the download has finished, run and install the package
* Open MySQL Command Line Client and execute volvo_ddl.sql that comes with volvo Java project

7 - Run Integration Test
* Open FullServicesTest.java, right click on it -> Run As -> JUnit Test
* All the results can be seen in the Console tab.

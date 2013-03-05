robocode-ayj-Arobot
=====================

Example project showing how to develop robocode robots using the Maven build system.  To install this package:

1. Install robocode
-------------------

Install both the robocode basic distribution and the robocode testing plugin.  Both of these setup.jar files are available for version 1.7.4.4 [here](https://sourceforge.net/projects/robocode/files/robocode/1.7.4.4/). 

Be sure to run the system manually from the command line to ensure that you have the appropriate Java installed and so forth. 

2. Install Maven
----------------

Start by following the [directions on installing Maven](http://maven.apache.org/download.cgi).

Be sure to run mvn --version to verify that it is correctly installed.  This package has been tested using Maven 3.0.4.

3. Download this robocode-ayj-Arobot package
----------------------------------------------

For those who do not know about git, the easiest way is to click the "ZIP" button at the top of this page, which will download the latest version of this repository as a .zip file. 

For those who know about git, you will want to clone it. 

4. Install robocode jar files into your local Maven repository
--------------------------------------------------------------

Robocode binaries are not provided as part of public Maven repositories, so the next step is to install the seven jar files needed for compilation and testing into your local repository.   You accomplish this by changing directory to robocode/libs and executing the following seven commands:

```
mvn install:install-file -Dfile=robocode.jar -DartifactId=robocode  -DgroupId=net.sourceforge.robocode -Dversion=1.7.4.4 -Dpackaging=jar -DgeneratePom=true
mvn install:install-file -Dfile=robocode.testing.jar -DartifactId=robocode.testing -DgroupId=net.sourceforge.robocode  -Dversion=1.7.4.4 -Dpackaging=jar -DgeneratePom=true
mvn install:install-file -Dfile=robocode.battle-1.7.4.4.jar -DartifactId=robocode.battle -DgroupId=net.sourceforge.robocode -Dversion=1.7.4.4 -Dpackaging=jar -DgeneratePom=true
mvn install:install-file -Dfile=robocode.core-1.7.4.4.jar   -DartifactId=robocode.core   -DgroupId=net.sourceforge.robocode -Dversion=1.7.4.4 -Dpackaging=jar -DgeneratePom=true
mvn install:install-file -Dfile=robocode.host-1.7.4.4.jar   -DartifactId=robocode.host   -DgroupId=net.sourceforge.robocode -Dversion=1.7.4.4 -Dpackaging=jar -DgeneratePom=true
mvn install:install-file -Dfile=robocode.repository-1.7.4.4.jar -DartifactId=robocode.repository   -DgroupId=net.sourceforge.robocode -Dversion=1.7.4.4 -Dpackaging=jar -DgeneratePom=true
mvn install:install-file -Dfile=picocontainer-2.14.2.jar -DgroupId=net.sourceforge.robocode -DartifactId=picocontainer -Dversion=2.14.2 -Dpackaging=jar -DgeneratePom=true
```

All installation should pass and build successfully. 

5.  Build and test the system
-----------------------------

Now that everything is installed, build and test the system. You use the standard Maven 'test' target.  There are two special aspects of this process of which you should be aware.  

First, the RobotTestBed class and the Maven POM file requires the definition of a System Property called "robocode.home", which should point to the directory where Robocode is installed. To define this property and its value, use the -Drobocode.home=robocodeHomeDirectory command line option, as illustrated below.

Second, the Robocode runtime system needs your newly developed robot to be known to the system during testing.  To accomplish this, the pom.xml file defines a "copy-resource" goal that copies your robot binary from the target/classes directory to the robocode.home/robots directory after completing compilation. Take a look at the pom.xml file to see how this is done.  

Note that this approach does not remove these files from the robocode installation after the testing process is done. 

Here is an example of the command line used to build and test the system.

```shell
C:\Users\ID3\GitHub\robocode-ayj-ARobot> mvn -Drobocode.home=C:\robocode test
```

If all test cases passed, the build should succeed. 

6.  Install robocode-ayj-Arobot into Eclipse
----------------------------------------------

Now that the system is running from the command line, you'll want to also run it from Eclipse.  To do so, bring up Eclipse, and select File | Import | Maven | Existing Maven Projects, and then complete the dialog boxes to import your project.  Eclipse will read the POM file in order to determine the libraries to include on the build path.  

To run the ayj.Arobot robot within Eclipse, you must configure Eclipse and Robocode in the normal way:
  * In the Run configuration, change the working directory to your Robocode installation directory. 
  * In the Robocode window, select Options | Preferences | Development Options to add the target/classes directory so that Robocode will see your robot.

To run the test cases, edit the Run configuration for each test to include -Drobocode.home=robocodeHomeDirectory as a VM argument. 
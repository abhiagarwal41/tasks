# User Guide

This guide details how the cucumber BDD framework works

## Java and maven setup

1. Install java (jdk8) by visiting following link 

   ```
   https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html
   ```
   
2. Install maven by visiting following link

   ```
   https://maven.apache.org/install.html
   ```
   
3. Set JAVA_HOME, M2_HOME and update PATH



## Runnning UI tests

1. Supported platforms - Windows & Mac OS

2. Supported browsers - Chrome & Firefox (make sure atleast one of them is installed and updated to latest version)

3. From the root directory of the project, run following cmd:

      ```
     mvn clean test
     ```
4. By default tests would run on Chrome. To run on firefox, use following cmd:
   
     ```
     mvn clean test -Dbrowser=firefox
   ```

5. After test execution, a html test report is generated and can be accessed by going into 'reports' folder

6. A log file is also generated under 'logs' folder


## Runnning UI tests using selenium grid + docker

1. Install docker by visiting following link
   
      ```
      https://docs.docker.com/get-docker/
      ```
2. Execute following commands to setup selenium grid using docker images (make sure docker daemon is running)

    ```
     docker pull selenium/hub
     docker pull selenium/node-firefox-debug
     docker pull selenium/node-chrome-debug
     docker run -d -p 4444:4444 --name selenium-hub selenium/hub
     docker run -d -P --link  selenium-hub:hub -v /dev/shm:/dev/shm -e NODE_MAX_INSTANCES=5 -e NODE_MAX_SESSION=5 selenium/node-chrome-debug
     docker run -d -P --link  selenium-hub:hub -v /dev/shm:/dev/shm -e NODE_MAX_INSTANCES=5 -e NODE_MAX_SESSION=5 selenium/node-firefox-debug
    ```
3. To execute test, run following cmd:
    
      ```
       mvn clean test -Dremote=true
     ```
   
## Running task 1 test

1. From the root directory of the project, run following cmd:
   
    ```
     mvn clean test -Dtest=LogErrorCount
    ```   
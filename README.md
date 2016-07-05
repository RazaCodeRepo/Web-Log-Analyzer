# Web-Log-Analyzer
This software reads the logs of a web server and offers a range of analytical data on the network traffic received by the server.

The logs of the server are spread across multiple files on the filesystem and range in the thousands. Each log contains an entry
with a similar format. A class named LogEntry is created to represent each web log. All the weblogs are then read from the
filesystem into a LogEntry Array List in the memory. Multiple Hashmaps are then created from this array list to perform a 
series of different analysis. These analysis include determining various details of the users visiting and logging onto the server
such as their IP addresses, number, time and activity during each visit.

The source code for the software is spread across four different files
-LogEntry.java
  A class for representing a web log
-LogAnalyzer.java
  A class containing a series of different analytical logic
-Tester.java
  A class containg test cases for testing all the other classes
-WebLogParser.java
  A class for reading files into memory and parsing information out of them
  
Sample web logs are also attached in the form of multiple files containing the name 'weblogX'



A demo video of the software in action can be found here:

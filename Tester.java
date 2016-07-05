
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        
        LogAnalyzer obj = new LogAnalyzer();
        obj.readFile("short-test_log");
        obj.printAll();
    }
    
    public void testUniqueIp(){
        LogAnalyzer obj = new LogAnalyzer();
        
        obj.readFile("weblog2_log");
        System.out.println("Number of Unique Ip addresses: " + obj.countUniqueIPs());
        
        obj.printAllHigherThanNum(400);
        
        ArrayList<String> uniqueIpAddresses = obj.uniqueIPVisitsOnDay("Sep 27   ");
        System.out.println(" unique ip addresses that visited : " + uniqueIpAddresses.size());
        
        int numberOfUniqueIps = obj.countUniqueIPsInRange(200,299);
        System.out.println(" unique ips are : " + numberOfUniqueIps);
        
    }
        
    public void testCountingWebsites(){
        LogAnalyzer obj = new LogAnalyzer();
        obj.readFile("weblog2_log");
        
        HashMap<String,Integer> myMap = obj.countVisitsPerIP();
        System.out.println("Number of times ip addresses appear in record: " +  myMap);
        
        int maxNumber = obj.mostNumberVisitsByIP(myMap);
        System.out.println("Maximum number of visits by a single IP : " + maxNumber);
        
        ArrayList<String> maxVisits = obj.iPsMostVisits(myMap);
        System.out.println("Ips with maximum number of visits: " + maxVisits);
        
        HashMap<String,ArrayList<String>> myMap_2 = obj.iPsForDays();
        System.out.println("Hash map of days to ip addresses: " + myMap_2);
        
        String day = obj.dayWithMostIPVisits(myMap_2);
        System.out.println("Day with most ip address visits: " + day);
        
        ArrayList<String> maxIps_day = obj.iPsWithMostVisitsOnDay(myMap_2,"Sep 30");
        System.out.println("Most visits by ip on a day:  " + maxIps_day); 
    }
}


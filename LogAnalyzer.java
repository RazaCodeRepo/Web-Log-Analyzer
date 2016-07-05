
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         
         records = new ArrayList<LogEntry>();   
     }
        
     public void readFile(String filename) {
         
         FileResource f = new FileResource(filename);
         for(String line : f.lines()){

             LogEntry le = WebLogParser.parseEntry(line);
             records.add(le);
         }
     }
     
     public int countUniqueIPs(){
     
         ArrayList<String> copy = new ArrayList<String>();
         for(LogEntry le : records){
             String ip = le.getIpAddress();
             if(!copy.contains(ip)){
                 copy.add(ip);
             }   
         }
         return copy.size();
     }
     
     public void printAllHigherThanNum(int num){
         int temp = 0;
         System.out.println("Higher than " + num + ": ");
         for(LogEntry le : records){
             temp = le.getStatusCode();
             if(temp > num){
                 System.out.println("Number of IP addresses higher than " + num + ": " + le);
             }
         }
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay(String someday){
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for(LogEntry le : records){
             String date_str = le.getAccessTime().toString(); 
             String ip_addr = le.getIpAddress();
             int index = date_str.indexOf(someday);
             if(index != -1){
                 String sub = date_str.substring(index,index+6);
                 if(sub.equals(someday)){
                     if(!uniqueIPs.contains(ip_addr)){
                         uniqueIPs.add(ip_addr);
                     }
                 }
             }            
             
         }
         System.out.print("On " + someday); 
         return uniqueIPs;
     }
     
     public int countUniqueIPsInRange(int low,int high){
         ArrayList<String> unique_ip = new ArrayList<String>();
         for(LogEntry le : records){
             String ip_addr = le.getIpAddress();
             int temp = le.getStatusCode();
             if(temp >= low && temp<= high){
                 if(!unique_ip.contains(ip_addr)){
                     unique_ip.add(ip_addr);
                 }
             }
         }
         System.out.print("between range "+ low + " and " + high); 
         return unique_ip.size();
     }
     
     public HashMap<String,Integer> countVisitsPerIP(){
         HashMap<String,Integer> counts = new HashMap<String,Integer>();
         for(LogEntry le : records){
             String ip_addr = le.getIpAddress();
             if(!counts.containsKey(ip_addr)){
                 counts.put(ip_addr,1);
             }
             else{
                 counts.put(ip_addr,counts.get(ip_addr)+1);
             }
         }
         return counts;
     }
     
     public int mostNumberVisitsByIP(HashMap<String,Integer> map){
         int max =0;
         for(String key : map.keySet()){
             int temp = map.get(key);
             if(max<temp){
                 max = temp;
             }
         }
         return max;
     }
     
     public ArrayList<String> iPsMostVisits(HashMap<String,Integer> map){
         ArrayList<String> ipList = new ArrayList<String>();
         int value = mostNumberVisitsByIP(map);
         for(String str : map.keySet()){
             int temp = map.get(str);
             if(temp == value){
                 ipList.add(str);
             }
         }
         return ipList;
     }
     
     public HashMap<String,ArrayList<String>> iPsForDays(){
         HashMap<String,ArrayList<String>> daysToIp = new HashMap<String,ArrayList<String>>();
         for(LogEntry le : records){
             String date_str = le.getAccessTime().toString();
             String sub_date = date_str.substring(4,10);
             String ipAddr = le.getIpAddress();
             if(daysToIp.containsKey(sub_date)){
                 ArrayList<String> temp = daysToIp.get(sub_date);
                 temp.add(ipAddr);
                 daysToIp.put(sub_date,temp);
             }
             else{
                 ArrayList<String> temp = new ArrayList<String>();
                 temp.add(ipAddr);
                 daysToIp.put(sub_date,temp);
             }
             
             
         }
         return daysToIp;
     }
     
     public String dayWithMostIPVisits(HashMap<String,ArrayList<String>> map){
         String maxDay = "";
         int max = 0;
         for(String key : map.keySet()){
             ArrayList<String> temp = map.get(key);
             int size = temp.size();
             if(max < size){
                 max = size;
                 maxDay = key;
             }
         }
         return maxDay;
     }
     
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String,ArrayList<String>> map,String day){
         ArrayList<String> ans = new ArrayList<String>();
         ArrayList<String> temp = new ArrayList<String>();
         temp = map.get(day);
         HashMap<String,Integer> temp2 = new HashMap<String,Integer>();
         if( temp != null){
             
             for(int i=0;i<temp.size();i++){
                 String str = temp.get(i);
                 if(!temp2.containsKey(str)){
                     temp2.put(str,1);
                 }
                 else{
                     temp2.put(str,temp2.get(str)+1);
                     
                 }
             }
         }    
         
         ans = iPsMostVisits(temp2);
         
         
         return ans;
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     
}

package week3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import edu.duke.FileResource;

public class LogAnalyzer {

	WebLogParser wp = new WebLogParser();

	private ArrayList<LogEntry> records;
	public LogAnalyzer(){
		records = new ArrayList<LogEntry>();
	}

	public void readFile(String filename){
		FileResource fn = new FileResource(filename);
		for(String line: fn.lines()){
			LogEntry le = wp.parseEntry(line);
			records.add(le);
		}

	}

	public int countUniqueIPs() {
		// HashMap<String, Integer> counts = countVisitsPerIp();
		//  return counts.size();
		
		ArrayList<String> uniqueIps = new ArrayList<String>();
		for(LogEntry le: records){
			String ipAddress = le.getIpAddress();
			if(!uniqueIps.contains(ipAddress)){
				uniqueIps.add(ipAddress);
			}
		}
		//		for(int i =0; i < uniqueIps.size(); i++ ){
		//			System.out.println(uniqueIps.get(i));
		//		}
		return uniqueIps.size();
	}
	
	public HashMap<String, Integer> countVisitsPerIp() {
		HashMap<String, Integer> counts = new HashMap<String, Integer>();
		for(LogEntry le: records){
			String ipAddress = le.getIpAddress();
			if(counts.containsKey(ipAddress)){
				counts.put(ipAddress, counts.get(ipAddress)+1);
			}
			else {
				counts.put(ipAddress, 1);
			}
		}
		return counts;
	}

	public int mostNumberVisitsByIP(HashMap<String, Integer> counts) {
		int maxNum = 0;
		for(String ip: counts.keySet()){
			if(maxNum < counts.get(ip)){
				maxNum = counts.get(ip);
			}
		}
		return maxNum;
	}
	
	public ArrayList<String> iPsMostVisits(HashMap<String, Integer> counts) {
		int maxNum = mostNumberVisitsByIP(counts);
		ArrayList<String> mostIpsVisited = new ArrayList<String>();
		for(String ip: counts.keySet()){
			if(maxNum == counts.get(ip)){
				mostIpsVisited.add(ip);
			}
		}
		return mostIpsVisited;
	}
	
	public HashMap<String, ArrayList<String>> iPsForDays(){
		
		HashMap<String, ArrayList<String>> countEachDay = new HashMap<String, ArrayList<String>>();
		
		for(LogEntry le: records){
			String accessTime = le.getAccessTime().toString();
			String accessMonth = accessTime.substring(4,7);
		    String accessDate = accessTime.substring(8,10);
		    String countDay = accessMonth +" "+ accessDate;
		    String ip = le.getIpAddress();
		    
		    if(!countEachDay.containsKey(countDay)){
		    	countEachDay.put(countDay, new ArrayList<String>());
		    }
		    countEachDay.get(countDay).add(ip);
		}
		return countEachDay;
	}
	
	public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> countEachDay) {
		String dayMostVisited = "";
		int maxVisted = 0;
		for(String time: countEachDay.keySet()){
			int current = countEachDay.get(time).size();
			if(maxVisted < current){
				maxVisted = current;
				dayMostVisited = time;
			}
		}
		return dayMostVisited;
	}
	
	
	public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> countEachDay, String someday){
		ArrayList<String> mostIpsVisited = new ArrayList<String>();
		HashMap<String, Integer> eachIpVisited = new HashMap<String, Integer>();
		ArrayList<String> ip = new ArrayList<String>();
		
		int maxNum = 0;
		int current = 0;
		 for(String time: countEachDay.keySet()){
			 if(time.equals(someday)){
				 ip = countEachDay.get(time);
			 }
		 }
		 for(int i =0; i < ip.size(); i++){
			 if(!eachIpVisited.containsKey(ip.get(i))){
				 eachIpVisited.put(ip.get(i), 1);
			 }
			 else{
				 eachIpVisited.put(ip.get(i), eachIpVisited.get(ip.get(i))+1);
			 }
		 }
		 mostIpsVisited = iPsMostVisits(eachIpVisited);
		 return mostIpsVisited;
	}
	
	public void printAllHigherThanNum(int num){
		for(LogEntry le: records){
			int statusCode = le.getStatusCode();
			if(statusCode > num){
				System.out.println(le);
			}
		}
	}

	public int countUniqueIPsInRange(int low, int high){
		ArrayList<String> uniqueIps = new ArrayList<String>();
		for(LogEntry le: records){
			int statusCode = le.getStatusCode();
			if( low <= statusCode && statusCode <= high){	   

				String ipAddress = le.getIpAddress();
				if(!uniqueIps.contains(ipAddress)){
					uniqueIps.add(ipAddress);
				}
			}
		}

		return uniqueIps.size();
	}
	
	public int uniqueIPVisitsOnDay(String someday) {
		ArrayList<String> uniqueIps = new ArrayList<String>();
		String month = someday.substring(0,3);
		String date = someday.substring(4,6);
		for(LogEntry le: records){
			String accessTime = le.getAccessTime().toString();
		    String accessMonth = accessTime.substring(4,7);
		    String accessDate = accessTime.substring(8,10);
		    if(month.equals(accessMonth) && date.equals(accessDate)){
		    	String ip = le.getIpAddress();
		    	if (!uniqueIps.contains(ip)){
		    		uniqueIps.add(ip);
		    	}
		    }
			
		}
        
		return uniqueIps.size();
		
	}
	public void printAll(){
		for(LogEntry le: records){
			System.out.println(le);
		}
	}
	public static void main(String[] args) {
		LogAnalyzer la = new LogAnalyzer();
		la.readFile("weblogData/weblog2_log");
		int uniqueIPs = la.countUniqueIPs();
		System.out.println("There are " + uniqueIPs + " UNIQUE IPS");
		
		System.out.println("\n");
		la.printAll();
		
		System.out.println("\n");
		la.printAllHigherThanNum(400);
		
		System.out.println("\n");
		int uniqueIPsInRange = la.countUniqueIPsInRange(400,499);
		System.out.println("There are " + uniqueIPsInRange + " UNIQUE Ips in Range ");
		
		System.out.println("\n");
		String day = "Sep 27";
		int uniqueIpVisitsOnDay = la.uniqueIPVisitsOnDay(day);
		System.out.println("There are " + uniqueIpVisitsOnDay + " unique ips visited on " + day );
		
		System.out.println("\n");
		HashMap<String, Integer> counts = la.countVisitsPerIp();
		System.out.println(counts);
		
		int maxVisits = la.mostNumberVisitsByIP(counts);
		System.out.println("Max visits to ip .....  " + maxVisits);
		
		ArrayList<String> mostVisitedIP = la.iPsMostVisits(counts);
		for(int i=0; i < mostVisitedIP.size(); i++){
			System.out.println("iPsMostVisits  " + mostVisitedIP.get(i));
		}
		
		HashMap<String, ArrayList<String>> eachday = la.iPsForDays();
        for (String time: eachday.keySet()) {
            if (time.equals("Sep 21")) {
            	System.out.println(eachday.get(time).size());
            	
            }
        }
        
        
        String mostVistedDay = la.dayWithMostIPVisits(eachday);
        System.out.println("Most Visited day " + mostVistedDay);
        
        
        ArrayList<String> ipsMostVisited = la.iPsWithMostVisitsOnDay(eachday, "Sep 29");
        for(int i=0; i < ipsMostVisited.size(); i++){
			System.out.println(ipsMostVisited.get(i));
		}
		
	}

	

}

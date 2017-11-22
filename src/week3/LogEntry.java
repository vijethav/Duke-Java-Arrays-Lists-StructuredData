package week3;

import java.util.Date;



public class LogEntry {
	    private String ipAddress;
	    private Date accessTime;
	    private String request;
	    private int statusCode;
	    private int bytesReturned;
	  
	    
	    public LogEntry(String ip, Date time, String req, int status, int bytes) {
			super();
			ipAddress = ip;
			this.accessTime = time;
			this.request = req;
			this.statusCode = status;
			this.bytesReturned = bytes;
		}

		public LogEntry(String line){
	    	
	    }

	    public String getIpAddress() {
			return ipAddress;
		}


		public Date getAccessTime() {
			return accessTime;
		}


		public String getRequest() {
			return request;
		}


		public int getStatusCode() {
			return statusCode;
		}


		public int getBytesReturned() {
			return bytesReturned;
		}


		public String toString() {
			return ipAddress + " " + accessTime + " " + request
					+ " " + statusCode + " " + bytesReturned ;
		}

	public static void main(String[] args) {
		 
          LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
          System.out.println(le);
          LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
          System.out.println(le2);
		
	}

}

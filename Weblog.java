import java.io.*;
import java.net.*;

public class Weblog {

  public static void main(String[] args) {
  
  
    try (FileInputStream fin =  new FileInputStream("access.log");
      Reader in = new InputStreamReader(fin);
      BufferedReader bin = new BufferedReader(in);) {
      // command line parameter
	if(args.length != 0) {
	System.err.println("Invalid command line, exactly one argument required");
	System.exit(1);
	}
	
	for (String entry = bin.readLine();entry != null;entry = bin.readLine()) {
	int index = entry.indexOf(' ');
	String ip = entry.substring(0, index);
        String theRest = entry.substring(index);
        
        try {
          InetAddress address = InetAddress.getByName(ip);
          System.out.println(address.getHostName() + theRest);
        } catch (UnknownHostException ex) {
          System.err.println(entry);
        }
      }
    } catch (IOException ex) {
      System.out.println("Exception: " + ex);
    }
  }
}

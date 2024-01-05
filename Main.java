import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.InputStreamReader;
import java.io.BufferedReader;
// import java.io.IOException;

class Main {
    File myObj = new File("ScanResults.txt");
    public static void main(String[] args) {
        // System.out.println("Hello world.");
        try {
            Scanner myScanner = new Scanner(System.in);
            BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));
            Nmap n = new Nmap();
            Main m = new Main();
            ArrayList<String> test = m.IPs();
            if (test.size()>0) {
                // something is in list
                for (int i=0; i<test.size(); i++) {
                    System.out.println(test.get(i));
                }
                System.out.println("Which IP would you like to target? ");
                String selectedIP = myScanner.next();
                if (!test.contains(selectedIP)) {
                    System.out.println("I've never heard of that IP address...");
                    System.exit(0);
                }
                n.basicScansList();
                String[] collecter = bi.readLine().split(" ");
                // System.out.println(Arrays.toString(collecter));
                String[] x = new String[collecter.length+1];
                x[0] = selectedIP;
                for (int i=1; i<x.length; i++) {
                    x[i] = collecter[i-1];
                }
                String selection = n.userSelected(x);
                n.userSelectedScan(selection);
            } else {
                System.out.println("Nothing saved, start new scan.");
                String[] myString = n.getIfconfig();
                String ip = n.findIP(myString);
                System.out.println(ip);

                String[] myIP = ip.split("\\.");
                ArrayList<String> networkIPs = n.getNetworkIPs(myIP);
                File f = new File();
                f.saveMe(networkIPs);
            }
            // String[] ipList = n.addToArray(); // what was this supposed to do
            // String[] myString = n.getIfconfig();
            // String ip = n.findIP(myString);
            // System.out.println(ip);

            // String[] myIP = ip.split("\\.");
            // System.out.println(Arrays.toString(myIP));
            // ArrayList<String> networkIPs = n.getNetworkIPs(myIP);
            // System.out.println(networkIPs);
            // File f = new File();
            // f.saveMe(networkIPs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // used to open/load a file of IP addresses
    public ArrayList<String> IPs() {
        // File myObj = new File("ScanResults1.txt");
        ArrayList<String> listOfIPSLoaded = new ArrayList<String>();
        try {
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();

                String[] splitString = data.split(" "); 
                // System.out.println(Arrays.toString(splitString));
                // System.out.println(Arrays.toString(splitString[splitString.length-1].split("\\.")));
                if (splitString[splitString.length-1].split("\\.").length>1) {
                    // System.out.println(Arrays.toString(splitString[splitString.length-1].split("\\.")));
                    String specificIP = Arrays.toString(splitString[splitString.length-1].split("\\."));
                    // System.out.println(specificIP.replace(", ",".").replaceAll("\\[","").replaceAll("\\]",""));
                    listOfIPSLoaded.add(specificIP.replace(", ",".").replaceAll("\\[","").replaceAll("\\]",""));
                }
            }
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
            return null;
        } 
        return listOfIPSLoaded;
    }
}
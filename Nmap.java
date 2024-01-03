import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.ArrayList;

public class Nmap {
    // method only finds ifconfig
    public String[] getIfconfig() throws Exception {
        try {
            // InetAddress ip = InetAddress.getLocalHost();
            // System.out.println(ip.getHostAddress());
            Process process = Runtime.getRuntime().exec("ifconfig wlan0"); //wlan0
            process.waitFor();
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String theReadBuffer = "";
            String entireString = "";
            int num = 1;
            while (br.ready()) {
                theReadBuffer = br.readLine();
                entireString = entireString + "\n" + theReadBuffer;
            }
            String[] entireStringSplit = entireString.split(" ");
            // System.out.println(entireString);
            // System.out.println(Arrays.toString(entireStringSplit));
            // find index of inet, the ip is the one after that on array
            return entireStringSplit;
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return null;
        }
    }

    // method returns ip for network
    public static String findIP(String[] myList) {
        String ip = "";
        for (int i=0; i<myList.length; i++) {
            // System.out.println(myList[i].replaceAll("\\s+",""));
            if (myList[i].replaceAll("\\s+","").equals("inet")) {
                // System.out.println("Made it.");
                ip = myList[i+1];
                break;
            }
        }
        return ip;
    }

    // private IP addresses: 10.x.x.x/24, 192.168.x.x/16, 172.16-31.x.x/20 depending on private ip in use
    // scans private IP range and returns result
    public ArrayList<String> getNetworkIPs(String[] myIP) {
            System.out.println(myIP);
            ArrayList<String> myList = new ArrayList<String>();
            if (myIP[0].equals("10")) { // scan 10.0.0.0/24
                try {
                    String command = "nmap -sn 10.0.0.0/24";
                    Process process = Runtime.getRuntime().exec(command);
                    BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    String line = "";

                    while ((line = br.readLine()) != null) {
                        // System.out.println(line + "\n");
                        myList.add(line + "\n");
                    }
                    return myList;
                } catch (IOException ioe){
                    ioe.printStackTrace();
                    return null;
                }

            } else if (myIP[0].equals("192") && myIP[1].equals("168")) {
                try {
                    String command = "nmap -sn 192.168.0.0/16";
                    Process process = Runtime.getRuntime().exec(command);
                    BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    String line = "";

                    while ((line = br.readLine()) != null) {
                        // System.out.println(line + "\n");
                        myList.add(line + "\n");
                    }
                    return myList;
                } catch (IOException ioe){
                    ioe.printStackTrace();
                    return null;
                }
            } else if (myIP[0].equals("172") && Integer.parseInt(myIP[1])>15 && Integer.parseInt(myIP[1])<32) {
                try {
                    String command = "nmap -sn 172." + myIP[1] + ".0.0/20";
                    Process process = Runtime.getRuntime().exec(command);
                    BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    String line = "";

                    while ((line = br.readLine()) != null) {
                        // System.out.println(line + "\n");
                        myList.add(line + "\n");
                    }
                    return myList;
                } catch (IOException ioe){
                    ioe.printStackTrace();
                    return null;
                }
            } else {
                System.out.println("Thats not a private IP address mate.");
                return null;
            }

    }

    // what was this method supposed to be again?
    public String[] addToArray(ArrayList<String> networkIPs) {
        System.out.println(networkIPs.get(0)); // should print out first  line
        String[] splitString = networkIPs.get(0).split(" ");
        // if (splitString)


        return null;
    }

    // list of basic nmap scans
    public static void basicScansList() {
        System.out.println("Here are a few basic scans you can use.");
        System.out.println("Just enter the abbreviation(s) seperated by a space and enter when done.");
        // Scan techniques
        System.out.println("-sS TCP SYN port scan");
        System.out.println("-sT TCP Connect port scan");
        System.out.println("-sU UDP port scan");
        System.out.println("-sA TCP ACK port scan");
        // Host discovery
        System.out.println("-Pn Only port scan");
        System.out.println("-sn Only host discovery");
        System.out.println("-PR ARP discovery on local network");
        System.out.println("-n Disable DNS resolutions");
        // System.out.println("-p port (-p- for all ports)"); // maybe just auto scan all ports or fast scan -F fast scan
        // detect version, service, and OS
        System.out.println("-sV Detect the version of services");
        System.out.println("-A Enable OS/version detection, script scanning, and traceroute");
        System.out.println("-O Identify OS using TCP/IP fingerprinting");
        // use normal timing and performance -T3
        // NSE script
        System.out.println("-sC Default script scan");
        // Firewall/IDS evasion
        // System.out.println("-f Use fragmented IP packets");
        // System.out.println("-D Decoy scan");
        // System.out.println("-g Use given source port number");

    }

    // scan with whatever the user enters
    public String userSelected(String[] userSelections) {
        String myString = "nmap";
        // System.out.println(Arrays.toString(userSelections));
        for (int i=1; i<userSelections.length; i++) {
            myString = myString + " " + userSelections[i];
        }
        myString = myString + " " + userSelections[0];
        // System.out.println(myString);
        return myString;
    }

    public void userSelectedScan(String selection) {
        Process process = Runtime.getRuntime().exec(selection);
        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));

        String line = "";
        while ((line =br.readLine()) != null) {
            System.out.println(line + "\n");
        }
    }
}
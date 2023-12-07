import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.Arrays;

public class Nmap {
    // method only finds ifconfig
    public String[] getIfconfig() throws Exception {
        try {
            // InetAddress ip = InetAddress.getLocalHost();
            // System.out.println(ip.getHostAddress());
            Process process = Runtime.getRuntime().exec("ifconfig en0"); //wlan0
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
    public String[] getNetworkIPs(String[] myIP) {
            System.out.println(myIP);
            if (myIP[0].equals("10")) { // scan 10.0.0.0/24
                try {
                    String command = "nmap -sn 10.0.0.0/24";
                    Process process = Runtime.getRuntime().exec(command);
                    BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    String line = "";
                    while ((line = br.readLine()) != null) {
                        System.out.println(line + "\n");
                    }
                } catch (IOException ioe){
                    ioe.printStackTrace();
                }
                return myIP;
            } else if (myIP[0].equals("192") && myIP[1].equals("168")) {

                return myIP;
            } else if (myIP[0].equals("172") && Integer.parseInt(myIP[1])>15 && Integer.parseInt(myIP[1])<32) {

                return myIP;
            } else {
                System.out.println("You're fucked.");
                return null;
            }
    }
}
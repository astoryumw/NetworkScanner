import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.Arrays;

public class Nmap {
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

    public String[] getNetworkIPs(String[] myIP) {
            System.out.println(myIP);
        return null;
    }
}
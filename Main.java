import java.util.Arrays;
import java.util.ArrayList;

class Main {
    public static void main(String[] args) {
        System.out.println("Hello world.");
        try {
            Nmap n = new Nmap();
            String[] myString = n.getIfconfig();
            String ip = n.findIP(myString);
            System.out.println(ip);

            String[] myIP = ip.split("\\.");
            System.out.println(Arrays.toString(myIP));
            ArrayList<String> networkIPs = n.getNetworkIPs(myIP);
            System.out.println(networkIPs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
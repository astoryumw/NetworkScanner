import java.util.Arrays;

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
            n.getNetworkDevices(myIP);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}